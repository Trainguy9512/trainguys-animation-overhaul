package com.trainguy.animationoverhaul.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Unique
@Mixin(PlayerModel.class)
public class MixinPlayerModel<T extends LivingEntity> extends HumanoidModel<T> {
    @Shadow @Final private List<ModelPart> parts;
    @Shadow @Final public ModelPart leftPants;
    @Shadow @Final public ModelPart rightPants;
    @Shadow @Final public ModelPart leftSleeve;
    @Shadow @Final public ModelPart rightSleeve;
    @Shadow @Final public ModelPart jacket;
    @Shadow @Final private ModelPart cloak;
    private float tick = 0;
    //private float crouchAmountLinear = 0;
    //private float crouchAmount = 0;
    //private float sprintAmountLinear = 0;
    //private float sprintAmount = 0;
    //private float directionShift = 0;
    //private boolean movingForward = true;

    public MixinPlayerModel(ModelPart modelPart) {
        super(modelPart);
    }

    // unused old code
    private void tick(T livingEntity, float delta, float speed){
        //this.crouchAmountLinear = this.crouching ? Math.min(this.crouchAmountLinear + 0.25F * delta, 1) : Math.max(this.crouchAmountLinear - 0.25F * delta, 0);
        //this.crouchAmount = this.crouching ? Mth.sqrt(Mth.cos((float) (this.crouchAmountLinear * Math.PI * 0.5F - Math.PI * 0.5F))) : 1 - Mth.sqrt(Mth.cos((float) (this.crouchAmountLinear * Math.PI * 0.5F)));
        //this.sprintAmountLinear = speed > 0.9 ? Math.min(this.sprintAmountLinear + 0.0625F * delta, 1) : Math.max(this.sprintAmountLinear - 0.125F * delta, 0);
        //this.sprintAmount = speed > 0.9 ? Mth.sqrt(Mth.cos((float) (this.sprintAmountLinear * Math.PI * 0.5F - Math.PI * 0.5F))) : 1 - Mth.sqrt(Mth.cos((float) (this.sprintAmountLinear * Math.PI * 0.5F)));
        //this.directionShift = !this.movingForward ? Math.min(this.directionShift + 0.25F * delta, 1) : Math.max(this.directionShift - 0.25F * delta, 0);
    }

    @Inject(method = "setupAnim", at = @At("HEAD"), cancellable = true)
    private void animPlayerModel(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci){
        // Make it so this only applies to player animations, not mobs that use player animations as a base.
        if(livingEntity.getType() != EntityType.PLAYER){
            super.setupAnim(livingEntity, f, g, h, i, j);
            return;
        }
        float directionShift = 0;
        float previousTick = this.tick;
        this.tick = h;
        float delta = this.tick - previousTick;

        float tickDifference = (float) (h - Math.floor(h));

        // Slow down the limb speed if the entity is a baby
        if(livingEntity.isBaby()){
            f /= 2;
        }

        // basically the way this works is i store different variables outlined below in the unused transforms of different
        // model parts, because i can't think of any other ways to store variables like this per-entity. it stores it mostly in
        // linear 0-1 float variables by moving the model part between 0.0 and 0.05 on the X axis which is visually undetectable.

        // body.x           kneel               done
        // hat.x            falling             done
        // leftArm.x        L item/block        done
        // rightArm.x       R item/block        done
        // jacket.x         spear throw
        // rightSleeve.x    bow pull            done
        // leftSleeve.x     crossbow hold
        // rightLeg.x       spyglass
        // leftLeg.x        shield
        // rightPants.x

        // Right arm item/block arm pose
        this.rightArm.x = this.rightArmPose == ArmPose.BLOCK || this.rightArmPose == ArmPose.ITEM ? Mth.clamp(this.rightArm.x + 0.0125F * delta, 0.0F - 5, 0.05F - 5) : Mth.clamp(this.rightArm.x - 0.0125F * delta, 0.0F - 5, 0.05F - 5);
        float rightArmItemPoseAmount = Mth.sin(((this.rightArm.x + 5) * 20) * Mth.PI - Mth.PI * 0.5F) * 0.5F + 0.5F;

        // Left arm item/block arm pose
        this.leftArm.x = this.leftArmPose == ArmPose.BLOCK || this.leftArmPose == ArmPose.ITEM ? Mth.clamp(this.leftArm.x + 0.0125F * delta, 0.0F + 5, 0.05F + 5) : Mth.clamp(this.leftArm.x - 0.0125F * delta, 0.0F + 5, 0.05F + 5);
        float leftArmItemPoseAmount = Mth.sin(((this.leftArm.x - 5) * 20) * Mth.PI - Mth.PI * 0.5F) * 0.5F + 0.5F;

        // Bow pull
        boolean usingBow = this.rightArmPose == ArmPose.BOW_AND_ARROW || this.leftArmPose == ArmPose.BOW_AND_ARROW;
        this.rightSleeve.x = usingBow ? Mth.clamp(this.rightSleeve.x + 0.002F * delta, 0.0F - 5, 0.05F - 5) : Mth.clamp(this.rightSleeve.x - 0.005F * delta, 0.0F - 5, 0.05F - 5);
        float bowLinear = (this.rightSleeve.x + 5) * 20;
        float bowHoldingAmount = usingBow ? bowLinear < 0.25 ? Mth.sin(bowLinear * Mth.PI * 4 - Mth.PI * 0.5F) * 0.5F + 0.5F : 1 : bowLinear < 0.5 ? Mth.sin(bowLinear * Mth.PI * 2 - Mth.PI * 0.5F) * 0.5F + 0.5F : 1;
        float bowPullAmount = usingBow ? (float) (Mth.cos(Mth.sqrt(bowLinear) * Mth.PI * 2) * -0.5 + 0.5) : bowLinear < 0.5 ? Mth.sin(bowLinear * Mth.PI * 2 - Mth.PI * 0.5F) * 0.5F + 0.5F : 1;

        // Crouch variable
        this.head.x = livingEntity.isCrouching() ? Mth.clamp(this.head.x + 0.0125F * delta, 0.0F, 0.05F) : Mth.clamp(this.head.x - 0.0125F * delta, 0.0F, 0.05F);
        //float crouchAmount = livingEntity.isCrouching() ? Mth.sqrt(Mth.cos((float) (this.head.x * 20 * Math.PI * 0.5F - Math.PI * 0.5F))) : 1 - Mth.sqrt(Mth.cos((float) (this.head.x * 20 * Math.PI * 0.5F)));
        float crouchAmount = Mth.sin(((this.head.x) * 20) * Mth.PI - Mth.PI * 0.5F) * 0.5F + 0.5F;;

        // Crossbow Holding
        boolean holdingOrChargingCrossbow = (livingEntity.getTicksUsingItem() > 0 && livingEntity.getUseItem().getUseAnimation() == UseAnim.CROSSBOW) || this.rightArmPose == ArmPose.CROSSBOW_HOLD || this.leftArmPose == ArmPose.CROSSBOW_HOLD;
        boolean holdingUnloadedCrossbow = (livingEntity.getTicksUsingItem() == 0 && ((livingEntity.getMainHandItem().getUseAnimation() == UseAnim.CROSSBOW && !CrossbowItem.isCharged(livingEntity.getMainHandItem())) || (livingEntity.getOffhandItem().getUseAnimation() == UseAnim.CROSSBOW && !CrossbowItem.isCharged(livingEntity.getOffhandItem()))));
        this.leftSleeve.x = holdingOrChargingCrossbow ? Mth.clamp(this.leftSleeve.x + 0.0125F * delta, 0.0F + 5, 0.05F + 5) : holdingUnloadedCrossbow ? Mth.clamp(this.leftSleeve.x - 0.00625F * delta, 0.0F + 5, 0.05F + 5) : Mth.clamp(this.leftSleeve.x - 0.0125F * delta, 0.0F + 5, 0.05F + 5);
        float crossbowHoldAmount = !holdingUnloadedCrossbow ? Mth.sin(((this.leftSleeve.x - 5) * 20) * Mth.PI - Mth.PI * 0.5F) * 0.5F + 0.5F : Mth.sin(((this.leftSleeve.x - 5) * 20) * Mth.PI * 1.25F - Mth.PI * 0.5F) * 0.6F + 0.6F;


        // Simple sprint
        float sprintAmount = Mth.clamp(7.69F * (g - 0.87F), 0, 1);

        // When the player hits the ground after being in the air, start the hit ground animation
        if(this.hat.x > 0.0F && (livingEntity.isOnGround() || livingEntity.onClimbable())){
            this.body.x = Mth.clamp(this.hat.x * 2, 0, 0.05F);
        }
        // Get the in air and kneel values
        this.body.x = Mth.clamp(this.body.x - 0.004F * delta, 0.0F, 0.05F);
        this.hat.x = !livingEntity.isOnGround() && !livingEntity.onClimbable() && !livingEntity.isSwimming() && !livingEntity.isPassenger() ? Mth.clamp(this.hat.x + 0.0025F * delta, 0.0F, 0.05F) : 0;
        float inAirAmount = livingEntity.isOnGround() || livingEntity.onClimbable() || livingEntity.isSwimming() || livingEntity.isPassenger() ? 0 : Mth.clamp(this.hat.x * 20, 0, 1);
        float kneelAmount = 2 - Mth.sqrt(Mth.cos((float) (this.body.x * 20 * Math.PI * 0.5F))) * 2;
        sprintAmount *= (1 - inAirAmount);

        // Get the look angle and delta movement to determine whether the character is moving forwards or backwards
        if(g > 0.1){
            if((livingEntity.getLookAngle().x > 0 && livingEntity.getDeltaMovement().x < 0) || (livingEntity.getLookAngle().x < 0 && livingEntity.getDeltaMovement().x > 0) || (livingEntity.getLookAngle().z > 0 && livingEntity.getDeltaMovement().z < 0) || (livingEntity.getLookAngle().z < 0 && livingEntity.getDeltaMovement().z > 0)){
                directionShift = 1;
            } else {
                directionShift = 0;
            }
        }

        //tick(livingEntity, delta, g);
        // 0.25 crouching
        // 0.87 walking
        // 1.0 running

        float limbMotionMultiplier = 0.6662F;
        float headRotationMultiplier = 0.017453292F;
        // f = distance total
        // g = is moving lerp
        // h = tick at frame
        // i = y head rot
        // j = x head rot

        // Math for main movements
        float bodyBob = livingEntity.isOnGround() ? (float) ( ((Mth.abs(Mth.sin(f * limbMotionMultiplier - Mth.PI / 4) * 1) * -1 + 1F) * Mth.clamp(g, 0, 0.25) * 4 * (sprintAmount + 1))): 0;
        float rightLegLift = livingEntity.isOnGround() ? (float) ((Math.min(Mth.sin(f * limbMotionMultiplier + Mth.PI * directionShift) * -3, 0) + 1) * Mth.clamp(g, 0, 0.25) * 4): 0;
        float leftLegLift = livingEntity.isOnGround() ? (float) ((Math.min(Mth.sin(f * limbMotionMultiplier + Mth.PI + Mth.PI * directionShift) * -3, 0) + 1) * Mth.clamp(g, 0, 0.25) * 4): 0;
        float limbRotation = (float) ((Mth.cos(f * limbMotionMultiplier)) * 1.1F * g) * (1 - inAirAmount);
        float inverseLimbRotation = (float) ((Mth.cos(f * limbMotionMultiplier + Mth.PI)) * 1.1F * g) * (1 - inAirAmount);
        float limbForwardMotion = Mth.cos(f * limbMotionMultiplier) * 2.0F * g * (1 - inAirAmount);
        float inverseLimbForwardMotion = Mth.cos(f * limbMotionMultiplier + Mth.PI) * 2.0F * g * (1 - inAirAmount);

        // Main movements
        this.leftArm.z = limbForwardMotion * sprintAmount * 2 * (1 - leftArmItemPoseAmount) * (1 - crossbowHoldAmount);
        this.leftArm.y = bodyBob + 2;
        this.leftArm.xRot = limbRotation * (1 - leftArmItemPoseAmount * 0.75F) - (0.25F * leftArmItemPoseAmount * g);
        this.leftArm.yRot = 0;
        this.rightArm.z = inverseLimbForwardMotion * sprintAmount * 2 * (1 - rightArmItemPoseAmount) * (1 - crossbowHoldAmount);
        this.rightArm.y = bodyBob + 2;
        this.rightArm.xRot = inverseLimbRotation * (1 - rightArmItemPoseAmount * 0.75F) - (0.25F * rightArmItemPoseAmount * g);
        this.rightArm.yRot = 0;
        this.head.y = bodyBob;
        this.head.xRot = j * headRotationMultiplier;
        this.head.yRot = i * headRotationMultiplier;
        this.body.y = bodyBob;
        this.rightLeg.z = limbForwardMotion * sprintAmount;
        this.rightLeg.y = rightLegLift + 12;
        this.rightLeg.xRot = limbRotation;
        this.leftLeg.z = inverseLimbForwardMotion * sprintAmount;
        this.leftLeg.y = leftLegLift + 12;
        this.leftLeg.xRot = inverseLimbRotation;

        // Neck post process
        this.head.z = j * -0.05F * (1 - crouchAmount);
        this.body.z = j * -0.05F * (1 - crouchAmount);
        this.rightArm.z += j * -0.05F * (1 - crouchAmount);
        this.leftArm.z += j * -0.05F * (1 - crouchAmount);
        this.body.xRot = j * headRotationMultiplier * 0.25F * (1 - crouchAmount);

        // Running post process
        this.head.z += -3.0F * sprintAmount;
        this.body.z += -3.0F * sprintAmount;
        this.rightArm.z += -3.0F * sprintAmount;
        this.leftArm.z += -3.0F * sprintAmount;
        this.body.xRot += 0.25F * sprintAmount;

        // Crouch post process
        if(crouchAmount > 0){
            this.body.xRot += 0.5F * crouchAmount;
            this.rightArm.xRot += 0.4F * crouchAmount;
            this.leftArm.xRot += 0.4F * crouchAmount;
            this.rightLeg.z += 3.9F * crouchAmount + 0.1F;
            this.leftLeg.z += 3.9F * crouchAmount + 0.1F;
            this.rightLeg.y += 0.2F * crouchAmount;
            this.leftLeg.y += 0.2F * crouchAmount;
            this.head.y += 4.2F * crouchAmount;
            this.body.y += 3.2F * crouchAmount;
            this.leftArm.y += 3.2F * crouchAmount;
            this.rightArm.y += 3.2F * crouchAmount;
        }
        if(this.crouching){
            this.rightLeg.y -= 2.0F;
            this.leftLeg.y -= 2.0F;
            this.head.y -= 2.0F;
            this.body.y -= 2.0F;
            this.leftArm.y -= 2.0F;
            this.rightArm.y -= 2.0F;
        }

        // In air post process
        this.leftLeg.z += ((Math.cos(h * limbMotionMultiplier * 0.75) * 2) - 1) * inAirAmount * Mth.clamp(1 - g * 0.5F, 0, 1);
        this.leftLeg.xRot += ((Math.cos(h * limbMotionMultiplier * 0.75 - Mth.PI / 4)) * 0.5) * inAirAmount * Mth.clamp(1 - g * 0.5F, 0, 1);
        this.rightLeg.z += ((Math.cos(h * limbMotionMultiplier * 0.75 - Mth.PI) * 2) - 1) * inAirAmount * Mth.clamp(1 - g * 0.5F, 0, 1);
        this.rightLeg.xRot += ((Math.cos(h * limbMotionMultiplier * 0.75 - Mth.PI / 4 - Mth.PI)) * 0.5) * inAirAmount * Mth.clamp(1 - g * 0.5F, 0, 1);

        this.leftArm.xRot += ((Math.cos(h * limbMotionMultiplier * 0.75 - Mth.PI)) * 0.5) * inAirAmount * Mth.clamp(1 - g * 0.5F, 0, 1);
        this.leftArm.zRot = (float) (((Math.cos(h * limbMotionMultiplier * 0.75 + Mth.PI / 2 - Mth.PI)) * 0.25F - 0.25F) * inAirAmount) * Mth.clamp(1 - g * 0.5F, 0, 1);
        this.rightArm.xRot += ((Math.cos(h * limbMotionMultiplier * 0.75)) * 0.5) * inAirAmount * Mth.clamp(1 - g * 0.5F, 0, 1);
        this.rightArm.zRot = (float) (((Math.cos(h * limbMotionMultiplier * 0.75 - Mth.PI / 2)) * 0.25F + 0.25F) * inAirAmount) * Mth.clamp(1 - g * 0.5F, 0, 1);

        this.leftArm.xRot += 1 * g * inAirAmount;
        this.rightArm.xRot += 1 * g * inAirAmount;
        this.leftLeg.xRot += 1 * g * inAirAmount;
        this.rightLeg.xRot += 1 * g * inAirAmount;
        this.body.xRot += 0.5F * g * inAirAmount;
        this.leftLeg.z += 7 * g * inAirAmount;
        this.rightLeg.z += 7 * g * inAirAmount;
        this.leftLeg.y += -2 * g * inAirAmount;
        this.rightLeg.y += -2 * g * inAirAmount;

        // Kneel post process
        this.leftLeg.y += 2 * kneelAmount;
        this.leftLeg.z += -4 * kneelAmount;
        this.leftLeg.xRot += 0.5 * kneelAmount;
        this.rightLeg.y += 2 * kneelAmount;
        this.rightLeg.z += -4 * kneelAmount;
        this.rightLeg.xRot += 0.5 * kneelAmount;
        this.body.y += 2 * kneelAmount;
        this.body.z += -2 * kneelAmount;
        this.body.xRot += 0.25 * kneelAmount;
        this.leftArm.y += 2 * kneelAmount;
        this.leftArm.z += -2 * kneelAmount;
        this.leftArm.zRot += -0.25F * kneelAmount;
        this.rightArm.y += 2 * kneelAmount;
        this.rightArm.z += -2 * kneelAmount;
        this.rightArm.zRot += 0.25F * kneelAmount;
        this.head.y += 2 * kneelAmount;
        this.head.z += -3 * kneelAmount;

        // Right arm pose post process
        this.rightArm.xRot += -0.2F * rightArmItemPoseAmount;

        // Left arm pose post process
        this.leftArm.xRot += -0.2F * leftArmItemPoseAmount;

        // Attack Animation Post Process
        if(this.attackTime > 0){
            // Get the selected arm
            HumanoidArm humanoidArm = livingEntity.getMainArm();
            humanoidArm = livingEntity.swingingArm == InteractionHand.MAIN_HAND ? humanoidArm : humanoidArm.getOpposite();
            ModelPart attackArmPart = humanoidArm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;

            this.body.yRot = Mth.sin(Mth.sqrt(this.attackTime) * 6.2831855F) * 0.2F;
            // Transform the arm using attack time, which starts at 0 and goes up to 1
            attackArmPart.xRot += (Mth.cos((float) (Math.sqrt(this.attackTime) * Mth.PI * 2)) * -0.5F + 0.5F) * (Mth.clamp(this.head.xRot, -1, 0) - 1);
            attackArmPart.yRot += (Mth.cos((float) (Math.sqrt(this.attackTime) * Mth.PI * 3)) * -0.5F + 0.5F - this.attackTime) * 0.5F;

            if (humanoidArm == HumanoidArm.LEFT) {
                this.body.yRot *= -1.0F;
                attackArmPart.yRot *= -1.0F;
            }
        }

        // Bow pull post process
        // Determine which hand should be used as the dominant hand for the bow animation
        boolean isLeftHanded = livingEntity.getMainArm() == HumanoidArm.LEFT;
        boolean holdingBowInRightHand = !isLeftHanded ? livingEntity.getMainHandItem().getUseAnimation() == UseAnim.BOW : livingEntity.getOffhandItem().getUseAnimation() == UseAnim.BOW;
        boolean holdingBowInLeftHand = isLeftHanded ? livingEntity.getMainHandItem().getUseAnimation() == UseAnim.BOW : livingEntity.getOffhandItem().getUseAnimation() == UseAnim.BOW;
        if(holdingBowInRightHand && holdingBowInLeftHand){
            holdingBowInRightHand = !isLeftHanded;
            holdingBowInLeftHand = isLeftHanded;
        }

        if(holdingBowInRightHand){
            this.rightArm.yRot = Mth.lerp(bowHoldingAmount, this.rightArm.yRot , -0.1F + this.head.yRot);
            this.leftArm.yRot = Mth.lerp(bowHoldingAmount, this.leftArm.yRot,0.1F + this.head.yRot + 0.4F);
            this.rightArm.xRot = Mth.lerp(bowHoldingAmount, this.rightArm.xRot, -1.5707964F + this.head.xRot);
            this.leftArm.xRot = Mth.lerp(bowHoldingAmount, this.leftArm.xRot, -1.5707964F + this.head.xRot);

            this.leftArm.zRot = Mth.lerp(bowHoldingAmount, this.leftArm.zRot, this.head.xRot * 0.5F);

            this.leftArm.yRot += Mth.lerp(bowPullAmount, 0, -0.35F);
            if(usingBow){
                this.rightArm.xRot += Mth.lerp(bowPullAmount, 0, 0.3F);
                this.rightArm.yRot += Mth.lerp(bowPullAmount, 0, -0.25F);
            }
        }
        if(holdingBowInLeftHand){
            this.rightArm.yRot = Mth.lerp(bowHoldingAmount, this.rightArm.yRot , -0.1F + this.head.yRot - 0.4F);
            this.leftArm.yRot = Mth.lerp(bowHoldingAmount, this.leftArm.yRot,0.1F + this.head.yRot);
            this.rightArm.xRot = Mth.lerp(bowHoldingAmount, this.rightArm.xRot, -1.5707964F + this.head.xRot);
            this.leftArm.xRot = Mth.lerp(bowHoldingAmount, this.leftArm.xRot, -1.5707964F + this.head.xRot);

            this.rightArm.zRot = Mth.lerp(bowHoldingAmount, this.rightArm.zRot, this.head.xRot * -0.5F);

            this.rightArm.yRot += Mth.lerp(bowPullAmount, 0, 0.35F);
            if(usingBow){
                this.leftArm.xRot += Mth.lerp(bowPullAmount, 0, 0.3F);
                this.leftArm.yRot += Mth.lerp(bowPullAmount, 0, 0.25F);
            }
        }

        // Crossbow hold post process
        // Determine which hand should be used as the dominant hand for the bow animation
        boolean holdingCrossbowInRightHand = !isLeftHanded ? livingEntity.getMainHandItem().getUseAnimation() == UseAnim.CROSSBOW : livingEntity.getOffhandItem().getUseAnimation() == UseAnim.CROSSBOW;
        boolean holdingCrossbowInLeftHand = isLeftHanded ? livingEntity.getMainHandItem().getUseAnimation() == UseAnim.CROSSBOW : livingEntity.getOffhandItem().getUseAnimation() == UseAnim.CROSSBOW;
        if(holdingCrossbowInRightHand && holdingCrossbowInLeftHand){
            holdingCrossbowInRightHand = !isLeftHanded;
            holdingCrossbowInLeftHand = isLeftHanded;
        }
        float crossbowArmBob = livingEntity.isOnGround() ? (Mth.abs(Mth.cos(f * limbMotionMultiplier + Mth.PI / 2)) * 0.125F * g) * -1 : 0;
        ModelPart primaryArm = holdingCrossbowInRightHand ? this.rightArm : this.leftArm;
        ModelPart secondaryArm = holdingCrossbowInRightHand ? this.leftArm : this.rightArm;
        primaryArm.yRot = Mth.lerp(crossbowHoldAmount, primaryArm.yRot, (holdingCrossbowInRightHand ? -0.3F : 0.3F) + this.head.yRot);
        secondaryArm.yRot = Mth.lerp(crossbowHoldAmount, secondaryArm.yRot, (holdingCrossbowInRightHand ? 0.6F : -0.6F) + this.head.yRot);
        primaryArm.xRot = Mth.lerp(crossbowHoldAmount, primaryArm.xRot, -1.5707964F + this.head.xRot + 0.1F + crossbowArmBob);
        secondaryArm.xRot = Mth.lerp(crossbowHoldAmount, secondaryArm.xRot, -1.5F + this.head.xRot + crossbowArmBob);
        // Crossbow pull logic
        if(holdingOrChargingCrossbow){
            if(livingEntity.getTicksUsingItem() > 0){
                float crossbowPullProgress = Mth.clamp(((float)livingEntity.getTicksUsingItem() + tickDifference) / CrossbowItem.getChargeDuration(livingEntity.getUseItem()), 0, 1);
                float crossbowPullCurve = (crossbowPullProgress < 0.75F ? 1 : Mth.sin(crossbowPullProgress * Mth.PI * 4 - Mth.PI / 2) * 0.5F + 0.5F) * crossbowHoldAmount;

                primaryArm.yRot = Mth.lerp(crossbowPullCurve, primaryArm.yRot, holdingCrossbowInRightHand ? -0.8F : 0.8F);
                primaryArm.xRot = Mth.lerp(crossbowPullCurve, primaryArm.xRot, -0.97079635F);

                secondaryArm.yRot = Mth.lerp(crossbowPullCurve, secondaryArm.yRot, Mth.lerp(crossbowPullProgress, 0.4F, 0.85F) * (float)(holdingCrossbowInRightHand ? 1 : -1));
                secondaryArm.xRot = Mth.lerp(crossbowPullCurve, secondaryArm.xRot, Mth.lerp(crossbowPullProgress, primaryArm.xRot, -1.5707964F));
            }
        }

        // debug
        //System.out.println(CrossbowItem.getChargeDuration(livingEntity.getUseItem()));

        // Hat layer parent
        this.hat.y = this.head.y;
        this.hat.z = this.head.z;
        this.hat.xRot = this.head.xRot;
        this.hat.yRot = this.head.yRot;
        this.hat.zRot = this.head.zRot;

        // Jacket layer parent
        this.jacket.y = this.body.y;
        this.jacket.z = this.body.z;
        this.jacket.xRot = this.body.xRot;
        this.jacket.yRot = this.body.yRot;
        this.jacket.zRot = this.body.zRot;

        // Right Sleeve layer parent
        this.rightSleeve.y = this.rightArm.y;
        this.rightSleeve.z = this.rightArm.z;
        this.rightSleeve.xRot = this.rightArm.xRot;
        this.rightSleeve.yRot = this.rightArm.yRot;
        this.rightSleeve.zRot = this.rightArm.zRot;

        // Left Sleeve layer parent
        this.leftSleeve.y = this.leftArm.y;
        this.leftSleeve.z = this.leftArm.z;
        this.leftSleeve.xRot = this.leftArm.xRot;
        this.leftSleeve.yRot = this.leftArm.yRot;
        this.leftSleeve.zRot = this.leftArm.zRot;

        // Right Pants layer parent
        this.rightPants.y = this.rightLeg.y;
        this.rightPants.z = this.rightLeg.z;
        this.rightPants.xRot = this.rightLeg.xRot;
        this.rightPants.yRot = this.rightLeg.yRot;
        this.rightPants.zRot = this.rightLeg.zRot;

        // Left Pants layer parent
        this.leftPants.y = this.leftLeg.y;
        this.leftPants.z = this.leftLeg.z;
        this.leftPants.xRot = this.leftLeg.xRot;
        this.leftPants.yRot = this.leftLeg.yRot;
        this.leftPants.zRot = this.leftLeg.zRot;

        if (livingEntity.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
            if (livingEntity.isCrouching()) {
                this.cloak.z = 1.4F;
                this.cloak.y = 1.85F;
            } else {
                this.cloak.z = 0.0F;
                this.cloak.y = 0.0F;
            }
        } else if (livingEntity.isCrouching()) {
            this.cloak.z = 0.3F;
            this.cloak.y = 0.8F;
        } else {
            this.cloak.z = -1.1F;
            this.cloak.y = -0.85F;
        }
        ci.cancel();
    }
}
