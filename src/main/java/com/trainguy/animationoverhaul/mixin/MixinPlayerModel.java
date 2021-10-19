package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.Easing;
import com.trainguy.animationoverhaul.util.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.TimerProcessor;
import com.trainguy.animationoverhaul.util.timeline.Timeline;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
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

    private static final Timeline<Float> crouchWeightAnimation = Timeline.floatTimeline()
            .addKeyframe(0, 0F)
            .addKeyframe(1, 1F, Easing.CubicBezier.bezierOutCubic())
            .addKeyframe(2, 0F, Easing.CubicBezier.bezierOutCubic());

    private static final Timeline<Float> walkLegRotationAnimation = Timeline.floatTimeline()
            .addKeyframe(0, Mth.HALF_PI * -(2/5F))
            .addKeyframe(12, Mth.HALF_PI * (2/5F), new Easing.CubicBezier(0.34F, 0, 0.72F, 1))
            .addKeyframe(20, Mth.HALF_PI * -(2/5F), new Easing.CubicBezier(0.5F,0,0.66F,1));

    private static final Timeline<Float> walkLegForwardMovementAnimation = Timeline.floatTimeline()
            .addKeyframe(0, -1.5F)
            .addKeyframe(12, 1F, new Easing.CubicBezier(0.54F,0.23F,0.52F,1))
            .addKeyframe(20, -1.5F, new Easing.CubicBezier(0.48F,0,0.29F,1.64F));

    private static final Timeline<Float> walkLegLiftMovementAnimation = Timeline.floatTimeline()
            .addKeyframe(0, 0F)
            .addKeyframe(12, 0F, new Easing.Linear())
            .addKeyframe(16, -2F, new Easing.CubicBezier(0.23F, 0, 0.52F, 1))
            .addKeyframe(20, 0F, Easing.CubicBezier.bezierInOutQuad());

    private static final Timeline<Float> walkBodyLiftMovementAnimation = Timeline.floatTimeline()
            .addKeyframe(0, 0.5F)
            .addKeyframe(5, -0.5F, Easing.CubicBezier.bezierInOutQuad())
            .addKeyframe(10, 0.5F, new Easing.CubicBezier(0.8F, 0, 0.5F, 1));

    private static final Timeline<Float> walkArmSwingAnimation = Timeline.floatTimeline()
            .addKeyframe(0, 1F)
            .addKeyframe(12, -1F, Easing.CubicBezier.bezierInOutQuad())
            .addKeyframe(20, 1F, Easing.CubicBezier.bezierInOutQuad());

    private static final Timeline<Float> sprintLegRotationAnimation = Timeline.floatTimeline()
            .addKeyframe(0, Mth.HALF_PI * -0.5F)
            .addKeyframe(5, Mth.HALF_PI * 0.3F, Easing.CubicBezier.bezierInQuad())
            .addKeyframe(12, Mth.HALF_PI * 0.5F, Easing.CubicBezier.bezierOutQuad())
            .addKeyframe(16, Mth.HALF_PI * 0.3F, Easing.CubicBezier.bezierInQuad())
            .addKeyframe(20, Mth.HALF_PI * -0.5F, Easing.CubicBezier.bezierOutQuad());

    /*
            .addKeyframe(12, Mth.HALF_PI * (3/5F), new Easing.CubicBezier(0.3F, 0, 0.7F, 1))
            .addKeyframe(20, Mth.HALF_PI * -(1/3F), new Easing.CubicBezier(0.3F, 0, 0.3F, 1));
    */

    private static final Timeline<Float> sprintLegForwardMovementAnimation = Timeline.floatTimeline()
            .addKeyframe(0, -2.5F)
            .addKeyframe(10, 2.5F, Easing.CubicBezier.bezierInOutQuad())
            .addKeyframe(20, -2.5F, new Easing.CubicBezier(0.4F,0,0F,1F));

    private static final Timeline<Float> sprintLegLiftMovementAnimation = Timeline.floatTimeline()
            .addKeyframe(0, 0.5F)
            .addKeyframe(5, 0F, Easing.CubicBezier.bezierInOutQuad())
            .addKeyframe(10, -1F, Easing.CubicBezier.bezierInOutQuad())
            .addKeyframe(15, -2F, new Easing.CubicBezier(0.23F, 0, 0.52F, 1))
            .addKeyframe(20, 0.5F, Easing.CubicBezier.bezierInOutQuad());

    private static final Timeline<Float> sprintBodyLiftMovementAnimation = Timeline.floatTimeline()
            .addKeyframe(0, 0.5F)
            .addKeyframe(5, -1F, Easing.CubicBezier.bezierInOutQuad())
            .addKeyframe(10, 0.5F, Easing.CubicBezier.bezierInOutQuad());

    private static final Timeline<Float> sprintArmSwingAnimation = Timeline.floatTimeline()
            .addKeyframe(0, 1F)
            .addKeyframe(12, -1F, new Easing.CubicBezier(0.4F, 0, 0.2F, 1))
            .addKeyframe(20, 1F, new Easing.CubicBezier(0.4F, 0, 0.2F, 1));

    public MixinPlayerModel(ModelPart modelPart) {
        super(modelPart);
    }

    /**
     * @author James Pelter
     */
    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("HEAD"), cancellable = true)
    public void setupAnim(T livingEntity, float animationPosition, float animationSpeed, float tickFrame, float headYRot, float headXRot, CallbackInfo ci){

        // Disable hand animations
        if(headXRot == 0.0F){
            return;
        }

        // Make it so this only applies to player animations, not mobs that use player animations as a base.
        // Mobs that use player animations as a base will be given a simplified version.
        if(livingEntity.getType() != EntityType.PLAYER){
            super.setupAnim(livingEntity, animationPosition, animationSpeed, tickFrame, headYRot, headXRot);
            return;
        }

        // Initial setup
        setupVariables(livingEntity);
        setupBasePose(livingEntity);

        // Locomotion pose layers
        addWalkPoseLayer(livingEntity);
        addSprintPoseLayer(livingEntity);
        addCrouchPoseLayer(livingEntity);

        // Idle pose layers

        // Item interaction pose layers

        // Final stuff
        parentSecondLayerToModel();
        ci.cancel();
    }

    private LivingEntityAnimParams getAnimationParameters(T livingEntity){
        return ((LivingEntityAccess)livingEntity).getAnimationParameters();
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

    private float getDirectionShift(T livingEntity){
        float directionShift = ((EntityAccess)livingEntity).getAnimationTimer("direction_shift");
        return Easing.CubicBezier.bezierInOutQuad().ease(directionShift);
    }

    private float getCrouchWeight(T livingEntity){
        float crouchTimer = ((EntityAccess)livingEntity).getAnimationTimer("crouch");
        return livingEntity.isCrouching() ? crouchWeightAnimation.getValueAt(crouchTimer * 0.5F) : crouchWeightAnimation.getValueAt(crouchTimer * -0.5F + 1);
    }

    private float getSprintWeight(T livingEntity){
        float sprintTimer = ((EntityAccess)livingEntity).getAnimationTimer("sprint");
        return Easing.CubicBezier.bezierInOutQuad().ease(sprintTimer);
    }

    private float getSwimWeight(T livingEntity){
        return Easing.CubicBezier.bezierOutQuad().ease(livingEntity.getSwimAmount(getAnimationParameters(livingEntity).getTickDifference()));
    }

    private float getFallFlyingWeight(T livingEntity){
        float fallFlyingTicks = livingEntity.getFallFlyingTicks() + getAnimationParameters(livingEntity).getTickDifference();
        return Easing.CubicBezier.bezierInOutQuad().ease(Mth.clamp(fallFlyingTicks * fallFlyingTicks / 100F, 0, 1));
    }

    private void addWalkPoseLayer(T livingEntity){
        float sprintWeight = getSprintWeight(livingEntity);
        float swimmingOrFallFlyingWeight = (1 - getSwimWeight(livingEntity)) * (1 - getFallFlyingWeight(livingEntity));
        if(sprintWeight < 1){
            float animationPosition = getAnimationParameters(livingEntity).getAnimationPosition();
            float animationSpeed = getAnimationParameters(livingEntity).getAnimationSpeed();

            float leftLegWalkTimer = new TimerProcessor(animationPosition).repeat(10, 0).getValue();
            float rightLegWalkTimer = new TimerProcessor(animationPosition).repeat(10, 0.5F).getValue();
            float bodyLiftTimer = new TimerProcessor(animationPosition).repeat(5, 0.3F).getValue();

            float directionShift = getDirectionShift(livingEntity);
            float directionShiftMultiplier = Mth.lerp(directionShift, 1, -1);
            float directionShiftArmMultiplier = Mth.lerp(directionShift, 1, -0.3F);
            float walkingWeight = swimmingOrFallFlyingWeight * (1 - sprintWeight) * Math.min(animationSpeed * 3, 1);
            float walkingWeightAffectedBySpeed = swimmingOrFallFlyingWeight * (1 - sprintWeight) * Math.min(animationSpeed * 2, 1);

            float leftLegRotation = walkLegRotationAnimation.getValueAt(leftLegWalkTimer) * walkingWeightAffectedBySpeed * directionShiftMultiplier;
            float leftLegForwardMovement = walkLegForwardMovementAnimation.getValueAt(leftLegWalkTimer) * walkingWeightAffectedBySpeed * directionShiftMultiplier;
            float leftLegLiftMovement = walkLegLiftMovementAnimation.getValueAt(leftLegWalkTimer) * walkingWeight;
            float rightLegRotation = walkLegRotationAnimation.getValueAt(rightLegWalkTimer) * walkingWeightAffectedBySpeed * directionShiftMultiplier;
            float rightLegForwardMovement = walkLegForwardMovementAnimation.getValueAt(rightLegWalkTimer) * walkingWeightAffectedBySpeed * directionShiftMultiplier;
            float rightLegLiftMovement = walkLegLiftMovementAnimation.getValueAt(rightLegWalkTimer) * walkingWeight;
            float bodyLiftMovement = walkBodyLiftMovementAnimation.getValueAt(bodyLiftTimer) * walkingWeight;
            float leftArmRotation = walkArmSwingAnimation.getValueAt(leftLegWalkTimer) * (Mth.HALF_PI * 2 / 5F) * walkingWeightAffectedBySpeed * directionShiftArmMultiplier;
            float leftArmForwardMovement = walkArmSwingAnimation.getValueAt(leftLegWalkTimer) * 1 * walkingWeightAffectedBySpeed * directionShiftArmMultiplier;
            float rightArmRotation = walkArmSwingAnimation.getValueAt(rightLegWalkTimer) * (Mth.HALF_PI * 2 / 5F) * walkingWeightAffectedBySpeed * directionShiftArmMultiplier;
            float rightArmForwardMovement = walkArmSwingAnimation.getValueAt(rightLegWalkTimer) * 1 * walkingWeightAffectedBySpeed * directionShiftArmMultiplier;

            this.leftLeg.xRot += leftLegRotation;
            this.leftLeg.z += leftLegForwardMovement;
            this.leftLeg.y += leftLegLiftMovement;
            this.rightLeg.xRot += rightLegRotation;
            this.rightLeg.z += rightLegForwardMovement;
            this.rightLeg.y += rightLegLiftMovement;
            this.leftArm.xRot += leftArmRotation;
            this.leftArm.z += leftArmForwardMovement;
            this.rightArm.xRot += rightArmRotation;
            this.rightArm.z += rightArmForwardMovement;

            for(ModelPart part : getPartListBody()){
                part.y += bodyLiftMovement;
            }
        }
    }

    private void addSprintPoseLayer(T livingEntity){
        float animationSpeed = getAnimationParameters(livingEntity).getAnimationSpeed();
        float sprintWeight = getSprintWeight(livingEntity) * animationSpeed;
        float swimmingOrFallFlyingWeight = (1 - getSwimWeight(livingEntity)) * (1 - getFallFlyingWeight(livingEntity));
        sprintWeight *= swimmingOrFallFlyingWeight;
        if(sprintWeight > 0){
            float animationPosition = getAnimationParameters(livingEntity).getAnimationPosition();

            float leftLegSprintTimer = new TimerProcessor(animationPosition).repeat(10, 0).getValue();
            float rightLegSprintTimer = new TimerProcessor(animationPosition).repeat(10, 0.5F).getValue();
            float bodyLiftTimer = new TimerProcessor(animationPosition).repeat(5, 0.25F).getValue();

            float leftLegRotation = sprintLegRotationAnimation.getValueAt(leftLegSprintTimer) * sprintWeight;
            float leftLegForwardMovement = sprintLegForwardMovementAnimation.getValueAt(leftLegSprintTimer) * sprintWeight;
            float leftLegYMovement = sprintLegLiftMovementAnimation.getValueAt(leftLegSprintTimer) * sprintWeight;
            float rightLegRotation = sprintLegRotationAnimation.getValueAt(rightLegSprintTimer) * sprintWeight;
            float rightLegForwardMovement = sprintLegForwardMovementAnimation.getValueAt(rightLegSprintTimer) * sprintWeight;
            float rightLegYMovement = sprintLegLiftMovementAnimation.getValueAt(rightLegSprintTimer) * sprintWeight;
            float bodyYMovement = sprintBodyLiftMovementAnimation.getValueAt(bodyLiftTimer) * sprintWeight;
            float leftArmRotation = sprintArmSwingAnimation.getValueAt(leftLegSprintTimer) * (Mth.HALF_PI * 3 / 5F) * sprintWeight;
            float leftArmForwardMovement = sprintArmSwingAnimation.getValueAt(leftLegSprintTimer) * 2 * sprintWeight;
            float rightArmRotation = sprintArmSwingAnimation.getValueAt(rightLegSprintTimer) * (Mth.HALF_PI * 3 / 5F) * sprintWeight;
            float rightArmForwardMovement = sprintArmSwingAnimation.getValueAt(rightLegSprintTimer) * 2 * sprintWeight;

            this.leftLeg.xRot += leftLegRotation;
            this.leftLeg.z += leftLegForwardMovement;
            this.leftLeg.y += leftLegYMovement;
            this.rightLeg.xRot += rightLegRotation;
            this.rightLeg.z += rightLegForwardMovement;
            this.rightLeg.y += rightLegYMovement;
            this.leftArm.xRot += leftArmRotation;
            this.leftArm.z += leftArmForwardMovement;
            this.rightArm.xRot += rightArmRotation;
            this.rightArm.z += rightArmForwardMovement;
            this.leftArm.xRot += Mth.HALF_PI * 1/6F * sprintWeight;
            this.rightArm.xRot += Mth.HALF_PI * 1/6F * sprintWeight;
            this.body.xRot += Mth.HALF_PI * 1/6F * sprintWeight;

            for(ModelPart part : getPartListAll()){
                part.y += bodyYMovement;
            }
            for(ModelPart part : getPartListBody()){
                part.z += -3F * sprintWeight;
            }
        }
    }

    private void addCreativeFlyPoseLayer(T livingEntity){

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

    private void setupBasePose(T livingEntity){
        initPartTransforms();

        float headXRot = getAnimationParameters(livingEntity).getHeadXRot();
        float headYRot = getAnimationParameters(livingEntity).getHeadYRot();
        float crouchWeight = getCrouchWeight(livingEntity);

        this.head.xRot = headXRot;
        this.head.yRot = headYRot;

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

    private void parentSecondLayerToModel(){
        // Parent the second layer to the main meshes
        this.hat.copyFrom(this.head);
        this.jacket.copyFrom(this.body);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
    }

    private void setupVariables(T livingEntity){

        float delta = getAnimationParameters(livingEntity).getDelta();
        float animationSpeed = getAnimationParameters(livingEntity).getAnimationSpeed();

        // Minecart sitting
        boolean isRidingInMinecart = livingEntity.isPassenger() && livingEntity.getRootVehicle().getType() == EntityType.MINECART;
        ((EntityAccess)livingEntity).incrementAnimationTimer("sitting_into_minecart", isRidingInMinecart, 0.125F, -10);

        // Eating/Drinking weight
        List<Item> drinkableItems = Arrays.asList(Items.HONEY_BOTTLE, Items.POTION, Items.MILK_BUCKET);
        boolean isEating = livingEntity.getUseItem().isEdible() && livingEntity.getTicksUsingItem() != 0;
        boolean isDrinking = false;
        for(Item item : drinkableItems){
            if(livingEntity.getUseItem().getItem() == item){
                isDrinking = livingEntity.getTicksUsingItem() != 0;
            }
        }
        ((EntityAccess)livingEntity).incrementAnimationTimer("eating", isEating, 0.125F, -0.125F);
        ((EntityAccess)livingEntity).incrementAnimationTimer("drinking", isDrinking, 0.125F, -0.125F);

        // Shield pose weight
        boolean isUsingShield = livingEntity.getUseItem().getItem() == Items.SHIELD && livingEntity.isUsingItem();
        ((EntityAccess)livingEntity).incrementAnimationTimer("shield_block", isUsingShield, 0.1875F, -0.1875F);

        // Attack timer
        // the fun one :)
        float previousAttackTimer = ((EntityAccess)livingEntity).getAnimationTimer("attack_progress");
        float previousAttackIndex = ((EntityAccess)livingEntity).getAnimationTimer("attack_index");
        float currentAttackTimer = this.attackTime < 0.5 && this.attackTime > 0 && previousAttackTimer > 0.3F ? 0 : previousAttackTimer;

        if(currentAttackTimer == 0){
            if(animationSpeed < 0.9 && livingEntity.isOnGround()){
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
        ((EntityAccess)livingEntity).setAnimationTimer("attack_progress", currentAttackTimer);
        ((EntityAccess)livingEntity).setAnimationTimer("attack_index", previousAttackIndex);

        // Armor equip variable
        String currentEquippedArmor = livingEntity.getArmorSlots().toString();
        String previousEquippedArmor = ((LivingEntityAccess)livingEntity).getPreviousEquippedArmor();

        float previousArmorChangeTimer = ((EntityAccess)livingEntity).getAnimationTimer("armor_equip_progress");
        float currentArmorChangeTimer = !previousEquippedArmor.equals(currentEquippedArmor) ? 1 : previousArmorChangeTimer;
        if(!previousEquippedArmor.equals(currentEquippedArmor)){
            ((LivingEntityAccess)livingEntity).setEquippedArmor(currentEquippedArmor);
        }
        currentArmorChangeTimer = Math.max(currentArmorChangeTimer - 0.125F * delta, 0);
        ((EntityAccess)livingEntity).setAnimationTimer("armor_equip_progress", currentArmorChangeTimer);

        // Dancing weight
        boolean songPlaying = ((LivingEntityAccess)livingEntity).getIsSongPlaying();
        BlockPos songOrigin = ((LivingEntityAccess)livingEntity).getSongOrigin();
        float previousDancingTimer = ((EntityAccess)livingEntity).getAnimationTimer("dancing_weight");
        float currentDancingTimer = Mth.clamp(previousDancingTimer + (livingEntity.blockPosition().distManhattan(new Vec3i(songOrigin.getX(), songOrigin.getY(), songOrigin.getZ())) < 10 && songPlaying && !crouching ? 0.125F * delta : -0.125F * delta), 0, 1);
        ((EntityAccess)livingEntity).setAnimationTimer("dancing_weight", currentDancingTimer);

        // Idle weight
        boolean isIdle = animationSpeed <= 0.05 && livingEntity.getDeltaMovement().y < 0.1 && livingEntity.getDeltaMovement().y > -0.1 && !livingEntity.isSleeping() && !livingEntity.isPassenger();
        ((EntityAccess)livingEntity).incrementAnimationTimer("idle_standing", isIdle, 0.125F, -0.25F);

        // Holding item or block in hand
        ((EntityAccess)livingEntity).incrementAnimationTimer("holding_item_right", this.rightArmPose == ArmPose.BLOCK || this.rightArmPose == ArmPose.ITEM, 0.25F, -0.25F);
        ((EntityAccess)livingEntity).incrementAnimationTimer("holding_item_left", this.leftArmPose == ArmPose.BLOCK || this.leftArmPose == ArmPose.ITEM, 0.25F, -0.25F);

        // Bow pull
        boolean usingBow = this.rightArmPose == ArmPose.BOW_AND_ARROW || this.leftArmPose == ArmPose.BOW_AND_ARROW;
        ((EntityAccess)livingEntity).incrementAnimationTimer("bow_pose", usingBow, 0.1F, -0.1F);
        ((EntityAccess)livingEntity).incrementAnimationTimer("bow_pull", usingBow, 0.06F, -6F);

        // Crouch variable
        ((EntityAccess)livingEntity).incrementAnimationTimer("crouch", livingEntity.isCrouching(), 0.25F, -0.3F);

        // Spear (Trident) pose variable
        ((EntityAccess)livingEntity).incrementAnimationTimer("spear", this.leftArmPose == ArmPose.THROW_SPEAR || this.rightArmPose == ArmPose.THROW_SPEAR, 0.0625F, -0.125F);

        // Crossbow Holding
        // TODO: redo this when revisiting crossbow animations
        float previousCrossbowPoseTimer = ((EntityAccess)livingEntity).getAnimationTimer("crossbow");
        boolean holdingOrChargingCrossbow = (livingEntity.getTicksUsingItem() > 0 && livingEntity.getUseItem().getUseAnimation() == UseAnim.CROSSBOW) || this.rightArmPose == ArmPose.CROSSBOW_HOLD || this.leftArmPose == ArmPose.CROSSBOW_HOLD;
        boolean holdingUnloadedCrossbow = (livingEntity.getTicksUsingItem() == 0 && ((livingEntity.getMainHandItem().getUseAnimation() == UseAnim.CROSSBOW && !CrossbowItem.isCharged(livingEntity.getMainHandItem())) || (livingEntity.getOffhandItem().getUseAnimation() == UseAnim.CROSSBOW && !CrossbowItem.isCharged(livingEntity.getOffhandItem()))));
        float currentCrossbowPoseTimer = holdingOrChargingCrossbow ? Mth.clamp(previousCrossbowPoseTimer + 0.25F * delta, 0.0F, 1.0F) : holdingUnloadedCrossbow ? Mth.clamp(previousCrossbowPoseTimer - 0.125F * delta, 0.0F, 1.0F) : Mth.clamp(previousCrossbowPoseTimer - 0.25F * delta, 0.0F, 1.0F);
        ((EntityAccess)livingEntity).setAnimationTimer("crossbow", currentCrossbowPoseTimer);

        // Simpler sprint :)
        ((EntityAccess)livingEntity).incrementAnimationTimer("sprint", animationSpeed > 0.9, 0.0625F, -0.125F);

        // In water
        ((EntityAccess)livingEntity).incrementAnimationTimer("wading_in_water", livingEntity.isInWater() && !livingEntity.isOnGround(), 0.0625F, -0.0625F);
        ((EntityAccess)livingEntity).incrementAnimationTimer("wading_under_water", livingEntity.isUnderWater() && !livingEntity.isOnGround(), 0.0625F, -0.0625F);
        //float inWaterWeight = AnimCurveUtils.linearToEaseInOutQuadratic(currentInWaterTimer);
        //float underWaterWeight = AnimCurveUtils.linearToEaseInOutQuadratic(currentUnderWaterTimer) * 0.9F + 0.1F * inWaterWeight;

        float previousDirectionShift = ((EntityAccess)livingEntity).getAnimationTimer("direction_shift");
        float moveAngleX = -Mth.sin(livingEntity.yBodyRot * Mth.PI / 180);
        float moveAngleZ = Mth.cos(livingEntity.yBodyRot * Mth.PI / 180);

        if(animationSpeed > 0.01){
            if(
                    (moveAngleX >= 0 && livingEntity.getDeltaMovement().x < 0 - 0.02 - animationSpeed * 0.03) ||
                    (moveAngleX <= 0 && livingEntity.getDeltaMovement().x > 0 + 0.02 + animationSpeed * 0.03) ||
                    (moveAngleZ >= 0 && livingEntity.getDeltaMovement().z < 0 - 0.02 - animationSpeed * 0.03) ||
                    (moveAngleZ <= 0 && livingEntity.getDeltaMovement().z > 0 + 0.02 + animationSpeed * 0.03)
            ){
                previousDirectionShift = Mth.clamp(previousDirectionShift + 0.125F * delta, 0, 1);
            } else {
                previousDirectionShift = Mth.clamp(previousDirectionShift - 0.125F * delta, 0, 1);;
            }
        }
        ((EntityAccess)livingEntity).setAnimationTimer("direction_shift", previousDirectionShift);
    }
}
