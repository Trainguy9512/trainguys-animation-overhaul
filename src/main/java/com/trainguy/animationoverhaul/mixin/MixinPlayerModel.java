package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.AnimCurve;
import com.trainguy.animationoverhaul.util.AnimCurveUtils;
import com.trainguy.animationoverhaul.util.Easing;
import com.trainguy.animationoverhaul.util.timeline.Timeline;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(PlayerModel.class)
public abstract class MixinPlayerModel<T extends LivingEntity> extends HumanoidModel<T> {
    @Shadow @Final public ModelPart leftPants;
    @Shadow @Final public ModelPart rightPants;
    @Shadow @Final public ModelPart leftSleeve;
    @Shadow @Final public ModelPart rightSleeve;
    @Shadow @Final public ModelPart jacket;
    @Shadow @Final private ModelPart cloak;

    private static final Timeline<Float> crouchDownAnimation =
            Timeline.floatTimeline()
                    .addKeyframe(0, 0F)
                    .addKeyframe(1, 1F, new Easing.CubicBezier(0.4F, 1.52F, 0.74F, 1));

    private static final Timeline<Float> crouchUpAnimation =
            Timeline.floatTimeline()
                    .addKeyframe(0, 0F)
                    .addKeyframe(1, 1F, Easing.CubicBezier.bezierInOutCubic());

    public MixinPlayerModel(ModelPart modelPart) {
        super(modelPart);
    }

    /**
     * @author James Pelter
     */
    @Inject(method = "setupAnim", at = @At("HEAD"), cancellable = true)
    public void setupAnim(T livingEntity, float distanceMoved, float movementSpeed, float tickFrame, float headYRot, float headXRot, CallbackInfo ci){
        // Disable hand animations
        if(headXRot == 0.0F){
            return;
        }
        // Make it so this only applies to player animations, not mobs that use player animations as a base.
        if(livingEntity.getType() != EntityType.PLAYER){
            super.setupAnim(livingEntity, distanceMoved, movementSpeed, tickFrame, headYRot, headXRot);
            return;
        }

        float delta = Minecraft.getInstance().getDeltaFrameTime();
        float tickDifference = (float) (tickFrame - Math.floor(tickFrame));

        // Slow down the limb speed if the entity is a baby
        if(livingEntity.isBaby()){
            distanceMoved /= 2;
        }

        setupVariables(livingEntity, delta, movementSpeed);
        setupBasePose(livingEntity, (float) Math.toRadians(headXRot), (float) Math.toRadians(headYRot));

        addCrouchPoseLayer(livingEntity);

        ci.cancel();
    }

    private List<ModelPart> getPartListAll(){
        return Arrays.asList(this.rightArm, this.leftArm, this.body, this.head, this.rightLeg, this.leftLeg);
    }
    private List<ModelPart> getPartListBody(){
        return Arrays.asList(this.rightArm, this.leftArm, this.body, this.head);
    }
    private List<ModelPart> getPartListArms(){
        return Arrays.asList(this.rightArm, this.leftArm);
    }
    private List<ModelPart> getPartListLegs(){
        return Arrays.asList(this.rightLeg, this.leftLeg);
    }

    private float getCrouchWeight(T livingEntity){
        float crouchTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("crouchAmount");
        return livingEntity.isCrouching() ? crouchDownAnimation.getValueAt(crouchTimer) : crouchUpAnimation.getValueAt(crouchTimer);
    }

    private void addCrouchPoseLayer(T livingEntity){
        float crouchWeight = getCrouchWeight(livingEntity);
        if(crouchWeight > 0){
            this.body.xRot += 0.5F * crouchWeight;
            this.rightArm.xRot += 0.4F * crouchWeight;
            this.leftArm.xRot += 0.4F * crouchWeight;
            this.rightLeg.z += 3.9F * crouchWeight + 0.1F;
            this.leftLeg.z += 3.9F * crouchWeight + 0.1F;
            this.rightLeg.y += 0.2F * crouchWeight;
            this.leftLeg.y += 0.2F * crouchWeight;
            this.head.y += 4.2F * crouchWeight;
            this.body.y += 3.2F * crouchWeight;
            this.leftArm.y += 3.2F * crouchWeight;
            this.rightArm.y += 3.2F * crouchWeight;
        }
        if(livingEntity.isCrouching()){
            for(ModelPart part : getPartListAll()){
                part.y -= 2;
            }
        }
    }

    private void setupBasePose(T livingEntity, float headXRot, float headYRot){
        initPartTransforms();

        float crouchWeight = getCrouchWeight(livingEntity);

        this.head.xRot = headXRot;
        this.head.yRot = headYRot;
        if(crouchWeight < 1){

        }
        for(ModelPart part : getPartListBody()){
            part.z += (float) Math.toDegrees(headXRot) * -0.05F * (1 - crouchWeight);
        }
        this.body.xRot = headXRot * 0.25F * (1 - crouchWeight);
    }

    private void initPartTransforms(){
        for(ModelPart part : getPartListAll()){
            part.setPos(0, 0, 0);
            part.setRotation(0, 0, 0);
        }
        this.leftArm.x = 5;
        this.leftArm.y = 2;
        this.rightArm.x = -5;
        this.rightArm.y = 2;
        this.leftLeg.x = 1.95F;
        this.leftLeg.y = 12;
        this.rightLeg.x = -1.95F;
        this.rightLeg.y = 12;
    }

    private void setupVariables(T livingEntity, float delta, float movementSpeed){

        // Minecart sitting
        boolean isRidingInMinecart = livingEntity.isPassenger() && livingEntity.getRootVehicle().getType() == EntityType.MINECART;
        float previousMinecartRidingTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("minecartRidingAmount");
        float currentMinecartRidingTimer = Mth.clamp(previousMinecartRidingTimer + (isRidingInMinecart ? 0.125F * delta : -1), 0, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("minecartRidingAmount", currentMinecartRidingTimer);

        // Eating weight
        List<Item> drinkableItems = Arrays.asList(Items.HONEY_BOTTLE, Items.POTION, Items.MILK_BUCKET);
        float previousEatingTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("eatingAmount");
        boolean isEating = livingEntity.getUseItem().isEdible() && livingEntity.getTicksUsingItem() != 0;
        for(Item item : drinkableItems){
            if(livingEntity.getUseItem().getItem() == item){
                isEating = livingEntity.getTicksUsingItem() != 0;
            }
        }
        float currentEatingTimer = Mth.clamp(previousEatingTimer + (isEating ? 0.125F * delta : -0.125F * delta), 0, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("eatingAmount", currentEatingTimer);

        // Shield pose weight
        float previousShieldPoseTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("shieldPoseAmount");
        boolean isHoldingShield = livingEntity.getUseItem().getItem() == Items.SHIELD && livingEntity.isUsingItem();
        float currentShieldPoseTimer = Mth.clamp(previousShieldPoseTimer + (isHoldingShield ? 0.1875F * delta : -0.1875F * delta), 0, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("shieldPoseAmount", currentShieldPoseTimer);

        // Attack timer
        float previousAttackTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("attackAmount");
        float previousAttackIndex = ((LivingEntityAccess)livingEntity).getAnimationVariable("attackIndex");
        float currentAttackTimer = this.attackTime < 0.5 && this.attackTime > 0 && previousAttackTimer > 0.3F ? 0 : previousAttackTimer;

        if(currentAttackTimer == 0){
            if(movementSpeed < 0.9 && livingEntity.isOnGround()){
                //System.out.println("swipe!");
                previousAttackIndex = 1;
            } else if(livingEntity.fallDistance > 0){
                //System.out.println("critical!");
                previousAttackIndex = 2;
            } else {
                //System.out.println("smack!");
                previousAttackIndex = 0;
            }
        }

        currentAttackTimer = Math.min(currentAttackTimer + 0.1F * delta, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("attackAmount", currentAttackTimer);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("attackIndex", previousAttackIndex);

        // Armor equip variable
        String currentEquippedArmor = livingEntity.getArmorSlots().toString();
        String previousEquippedArmor = ((LivingEntityAccess)livingEntity).getPreviousEquippedArmor();

        float previousArmorChangeTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("armorEquipAmount");
        float currentArmorChangeTimer = !previousEquippedArmor.equals(currentEquippedArmor) ? 1 : previousArmorChangeTimer;
        if(!previousEquippedArmor.equals(currentEquippedArmor)){
            ((LivingEntityAccess)livingEntity).setEquippedArmor(currentEquippedArmor);
        }
        currentArmorChangeTimer = Math.max(currentArmorChangeTimer - 0.125F * delta, 0);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("armorEquipAmount", currentArmorChangeTimer);

        // Dancing weight
        boolean songPlaying = ((LivingEntityAccess)livingEntity).getIsSongPlaying();
        BlockPos songOrigin = ((LivingEntityAccess)livingEntity).getSongOrigin();
        float previousDancingTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("dancingAmount");
        float currentDancingTimer = Mth.clamp(previousDancingTimer + (livingEntity.blockPosition().distManhattan(new Vec3i(songOrigin.getX(), songOrigin.getY(), songOrigin.getZ())) < 10 && songPlaying && !crouching ? 0.125F * delta : -0.125F * delta), 0, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("dancingAmount", currentDancingTimer);

        // Idle weight
        float previousIdleTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("idleAmount");
        boolean isIdle = movementSpeed <= 0.05 && livingEntity.getDeltaMovement().y < 0.1 && livingEntity.getDeltaMovement().y > -0.1 && !livingEntity.isSleeping() && !livingEntity.isPassenger();
        float currentIdleTimer = Mth.clamp(previousIdleTimer + (isIdle ? 0.125F * delta : -0.25F * delta), 0, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("idleAmount", currentIdleTimer);

        // Right arm item/block arm pose
        float previousRightArmItemPoseTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("rightArmItemPoseAmount");
        float currentRightArmItemPoseTimer = this.rightArmPose == ArmPose.BLOCK || this.rightArmPose == ArmPose.ITEM ? Mth.clamp(previousRightArmItemPoseTimer + 0.25F * delta, 0.0F, 1.0F) : Mth.clamp(previousRightArmItemPoseTimer - 0.25F * delta, 0.0F, 1.0F);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("rightArmItemPoseAmount", currentRightArmItemPoseTimer);

        // Right arm item/block arm pose
        float previousLeftArmItemPoseTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("leftArmItemPoseAmount");
        float currentLeftArmItemPoseTimer = this.leftArmPose == ArmPose.BLOCK || this.leftArmPose == ArmPose.ITEM ? Mth.clamp(previousLeftArmItemPoseTimer + 0.25F * delta, 0.0F, 1.0F) : Mth.clamp(previousLeftArmItemPoseTimer - 0.25F * delta, 0.0F, 1.0F);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("leftArmItemPoseAmount", currentLeftArmItemPoseTimer);

        // Bow pull
        boolean usingBow = this.rightArmPose == ArmPose.BOW_AND_ARROW || this.leftArmPose == ArmPose.BOW_AND_ARROW;
        float previousBowPoseTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("bowPoseAmount");
        float previousBowPullTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("bowPullAmount");
        float currentBowPoseTimer = Mth.clamp(previousBowPoseTimer + (usingBow ? 0.1F : -0.1F) * delta, 0, 1);
        float currentBowPullTimer = Mth.clamp(previousBowPullTimer + (usingBow ? 0.06F : -6F) * delta, 0, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("bowPoseAmount", currentBowPoseTimer);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("bowPullAmount", currentBowPullTimer);

        // Crouch variable
        float previousCrouchTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("crouchAmount");
        float currentCrouchTimer = Mth.clamp(previousCrouchTimer + (livingEntity.isCrouching() ? 0.25F : -0.3F) * delta, 0, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("crouchAmount", currentCrouchTimer);

        // Spear (Trident) pose variable
        float previousSpearPoseTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("spearPoseAmount");
        boolean usingSpear = this.leftArmPose == ArmPose.THROW_SPEAR || this.rightArmPose == ArmPose.THROW_SPEAR;
        float currentSpearPoseTimer = Mth.clamp(previousSpearPoseTimer + (usingSpear ? 0.0625F : -0.125F) * delta, 0, 1);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("spearPoseAmount", currentSpearPoseTimer);

        // Crossbow Holding
        float previousCrossbowPoseTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("leftArmCrossbowPoseAmount");
        boolean holdingOrChargingCrossbow = (livingEntity.getTicksUsingItem() > 0 && livingEntity.getUseItem().getUseAnimation() == UseAnim.CROSSBOW) || this.rightArmPose == ArmPose.CROSSBOW_HOLD || this.leftArmPose == ArmPose.CROSSBOW_HOLD;
        boolean holdingUnloadedCrossbow = (livingEntity.getTicksUsingItem() == 0 && ((livingEntity.getMainHandItem().getUseAnimation() == UseAnim.CROSSBOW && !CrossbowItem.isCharged(livingEntity.getMainHandItem())) || (livingEntity.getOffhandItem().getUseAnimation() == UseAnim.CROSSBOW && !CrossbowItem.isCharged(livingEntity.getOffhandItem()))));
        float currentCrossbowPoseTimer = holdingOrChargingCrossbow ? Mth.clamp(previousCrossbowPoseTimer + 0.25F * delta, 0.0F, 1.0F) : holdingUnloadedCrossbow ? Mth.clamp(previousCrossbowPoseTimer - 0.125F * delta, 0.0F, 1.0F) : Mth.clamp(previousCrossbowPoseTimer - 0.25F * delta, 0.0F, 1.0F);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("leftArmCrossbowPoseAmount", currentCrossbowPoseTimer);

        // Simple sprint
        float previousSprintTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("sprintAmount");
        float currentSprintTimer = movementSpeed > 0.9 ? Mth.clamp(previousSprintTimer + 0.0625F * delta, 0.0F, 1.0F) : Mth.clamp(previousSprintTimer - 0.125F * delta, 0.0F, 1.0F);
        float sprintWeight = AnimCurveUtils.linearToEaseInOutQuadratic(currentSprintTimer);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("sprintAmount", currentSprintTimer);

        // In water
        float previousInWaterTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("inWaterAmount");
        float previousUnderWaterTimer = ((LivingEntityAccess)livingEntity).getAnimationVariable("underWaterAmount");
        float currentInWaterTimer = Mth.clamp(previousInWaterTimer + (livingEntity.isInWater() && !livingEntity.isOnGround() ? 0.0625F : -0.0625F), 0, 1);
        float currentUnderWaterTimer = Mth.clamp(previousUnderWaterTimer + (livingEntity.isUnderWater() && !livingEntity.isOnGround() ? 0.0625F : -0.0625F), 0, 1);
        float inWaterWeight = AnimCurveUtils.linearToEaseInOutQuadratic(currentInWaterTimer);
        float underWaterWeight = AnimCurveUtils.linearToEaseInOutQuadratic(currentUnderWaterTimer) * 0.9F + 0.1F * inWaterWeight;
        ((LivingEntityAccess)livingEntity).setAnimationVariable("inWaterAmount", currentInWaterTimer);
        ((LivingEntityAccess)livingEntity).setAnimationVariable("underWaterAmount", currentUnderWaterTimer);

        float previousDirectionShift = ((LivingEntityAccess)livingEntity).getAnimationVariable("directionAmount");
        float moveAngleX = -Mth.sin(livingEntity.yBodyRot * Mth.PI / 180);
        float moveAngleZ = Mth.cos(livingEntity.yBodyRot * Mth.PI / 180);

        if(movementSpeed > 0.01){
            if(     (moveAngleX >= 0 && livingEntity.getDeltaMovement().x < 0 - 0.02 - movementSpeed * 0.03) ||
                    (moveAngleX <= 0 && livingEntity.getDeltaMovement().x > 0 + 0.02 + movementSpeed * 0.03) ||
                    (moveAngleZ >= 0 && livingEntity.getDeltaMovement().z < 0 - 0.02 - movementSpeed * 0.03) ||
                    (moveAngleZ <= 0 && livingEntity.getDeltaMovement().z > 0 + 0.02 + movementSpeed * 0.03)){
                previousDirectionShift = Mth.clamp(previousDirectionShift + 0.25F * delta, 0, 1);
            } else {
                previousDirectionShift = Mth.clamp(previousDirectionShift - 0.25F * delta, 0, 1);;
            }
        }
        float currentDirectionShift = previousDirectionShift;
        ((LivingEntityAccess)livingEntity).setAnimationVariable("directionAmount", previousDirectionShift);
    }
}
