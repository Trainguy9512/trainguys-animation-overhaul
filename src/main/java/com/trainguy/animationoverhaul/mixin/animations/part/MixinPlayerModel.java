package com.trainguy.animationoverhaul.mixin.animations.part;

import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.Easing;
import com.trainguy.animationoverhaul.util.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.TimerProcessor;
import com.trainguy.animationoverhaul.util.TransformChannel;
import com.trainguy.animationoverhaul.util.timeline.ChannelTimeline;
import com.trainguy.animationoverhaul.util.timeline.Timeline;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

    private static final Timeline<Float> sprintJumpWeightAnimation = Timeline.floatTimeline()
            .addKeyframe(0, 0F)
            .addKeyframe(10, 1F, Easing.CubicBezier.bezierOutQuart());

    private static final ChannelTimeline<Float> testChannelTimeline = ChannelTimeline.floatChannelTimeline()
            .addKeyframe(TransformChannel.x, 0, -3F)
            .addKeyframe(TransformChannel.x, 4, 3F)
            .addKeyframe(TransformChannel.x, 6, 3F)
            .addKeyframe(TransformChannel.x, 10, -3F)

            .addKeyframe(TransformChannel.zRot, 0, Mth.HALF_PI / 2)
            .addKeyframe(TransformChannel.zRot, 5, Mth.HALF_PI / -2)
            .addKeyframe(TransformChannel.zRot, 10, Mth.HALF_PI / 2);

    public MixinPlayerModel(ModelPart modelPart) {
        super(modelPart);
    }

    /**
     * @author James Pelter
     */
    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("HEAD"), cancellable = true)
    public void setupAnim(T livingEntity, float animationPosition, float animationSpeed, float tickFrame, float headYRot, float headXRot, CallbackInfo ci){

        // TODO: look back into making an inventory player animation!

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

        // Don't adjust the normal timers if the inventory model is being animated, use separate timers for separate animations
        if(getAnimationParameters(livingEntity).getLightInt() != 15728881){
            adjustTimers(livingEntity);
        }

        // Initial setup
        setupBasePose(livingEntity);

        // Locomotion pose layers
        addWalkPoseLayer(livingEntity);
        addSprintPoseLayer(livingEntity);
        addSprintJumpPoseLayer(livingEntity);
        addCrouchPoseLayer(livingEntity);
        addCreativeFlyPoseLayer(livingEntity);
        addFallingAndImpactPoseLayer(livingEntity);

        // WIP: sprint jumping, creative flying, falling
        // TODO: swimming, fall flying, redo crouching, crawling, wading in water, riding in boat, sleeping, riding horse (temp),
        // TODO: riding minecart

        // Idle pose layers
        // TODO: idling normally, idling while flying, idling underwater, idling sleeping, idle battle pose?

        // Item interaction pose layers
        // TODO: holding item, using bow, using crossbow, using shield, using spyglass, eating and drinking, throwing trident, equipping armor
        // TODO: fishing rod reeling, direction-based damage, death animation?, mining with pickaxe, interacting with fist?

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

    private float getCreativeFlyingWeight(T livingEntity){
        float creativeFlyWeight = ((EntityAccess) livingEntity).getAnimationTimer("creative_flying");
        return Easing.CubicBezier.bezierInOutQuad().ease(creativeFlyWeight);
    }

    private float getFallFlyingWeight(T livingEntity){
        float fallFlyingTicks = livingEntity.getFallFlyingTicks() + getAnimationParameters(livingEntity).getTickDifference();
        return Easing.CubicBezier.bezierInOutQuad().ease(Mth.clamp(fallFlyingTicks * fallFlyingTicks / 100F, 0, 1));
    }

    private float getSprintJumpWeight(T livingEntity){
        return Easing.CubicBezier.bezierInOutQuad().ease(((EntityAccess)livingEntity).getAnimationTimer("sprint_jump_weight"));
    }

    private float getMovingUpWeight(T livingEntity){
        return Easing.CubicBezier.bezierInOutQuad().ease(((EntityAccess)livingEntity).getAnimationTimer("moving_up_weight"));
    }

    private float getMovingDownWeight(T livingEntity){
        return Easing.CubicBezier.bezierInOutQuad().ease(((EntityAccess)livingEntity).getAnimationTimer("moving_down_weight"));
    }

    private float getFallingWeight(T livingEntity){
        return Easing.CubicBezier.bezierInOutQuad().ease(((EntityAccess)livingEntity).getAnimationTimer("falling_weight"));
    }

    private void addWalkPoseLayer(T livingEntity){
        float sprintWeight = getSprintWeight(livingEntity);
        float walkCancelWeight = (1 - getSwimWeight(livingEntity)) * (1 - getFallFlyingWeight(livingEntity)) * (1 - getCreativeFlyingWeight(livingEntity)) * (1 - getSprintJumpWeight(livingEntity)) * (1 - getFallingWeight(livingEntity));
        if(sprintWeight < 1){
            float animationPosition = getAnimationParameters(livingEntity).getAnimationPosition();
            float animationSpeed = getAnimationParameters(livingEntity).getAnimationSpeed();

            float leftLegWalkTimer = new TimerProcessor(animationPosition).repeat(10, 0).getValue();
            float rightLegWalkTimer = new TimerProcessor(animationPosition).repeat(10, 0.5F).getValue();
            float bodyLiftTimer = new TimerProcessor(animationPosition).repeat(5, 0.3F).getValue();

            float directionShift = getDirectionShift(livingEntity);
            float directionShiftMultiplier = Mth.lerp(directionShift, 1, -1);
            float directionShiftArmMultiplier = Mth.lerp(directionShift, 1, -0.3F);
            float walkingWeight = walkCancelWeight * (1 - sprintWeight) * Math.min(animationSpeed * 3, 1);
            float walkingWeightAffectedBySpeed = walkCancelWeight * (1 - sprintWeight) * Math.min(animationSpeed * 2, 1);

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
        float sprintCancelWeight = (1 - getSwimWeight(livingEntity)) * (1 - getFallFlyingWeight(livingEntity)) * (1 - getCreativeFlyingWeight(livingEntity)) * (1 - getFallingWeight(livingEntity));
        sprintWeight *= sprintCancelWeight;
        if(sprintWeight > 0){
            float sprintJumpCancelWeight =  (1 - getSprintJumpWeight(livingEntity));
            float animationPosition = getAnimationParameters(livingEntity).getAnimationPosition();

            float leftLegSprintTimer = new TimerProcessor(animationPosition).repeat(10, 0).getValue();
            float rightLegSprintTimer = new TimerProcessor(animationPosition).repeat(10, 0.5F).getValue();
            float bodyLiftTimer = new TimerProcessor(animationPosition).repeat(5, 0.25F).getValue();

            float leftLegRotation = sprintLegRotationAnimation.getValueAt(leftLegSprintTimer) * sprintWeight * sprintJumpCancelWeight;
            float leftLegForwardMovement = sprintLegForwardMovementAnimation.getValueAt(leftLegSprintTimer) * sprintWeight * sprintJumpCancelWeight;
            float leftLegYMovement = sprintLegLiftMovementAnimation.getValueAt(leftLegSprintTimer) * sprintWeight * sprintJumpCancelWeight;
            float rightLegRotation = sprintLegRotationAnimation.getValueAt(rightLegSprintTimer) * sprintWeight * sprintJumpCancelWeight;
            float rightLegForwardMovement = sprintLegForwardMovementAnimation.getValueAt(rightLegSprintTimer) * sprintWeight * sprintJumpCancelWeight;
            float rightLegYMovement = sprintLegLiftMovementAnimation.getValueAt(rightLegSprintTimer) * sprintWeight * sprintJumpCancelWeight;
            float bodyYMovement = sprintBodyLiftMovementAnimation.getValueAt(bodyLiftTimer) * sprintWeight * sprintJumpCancelWeight;
            float leftArmRotation = sprintArmSwingAnimation.getValueAt(leftLegSprintTimer) * (Mth.HALF_PI * 3 / 5F) * sprintWeight * sprintJumpCancelWeight;
            float leftArmForwardMovement = sprintArmSwingAnimation.getValueAt(leftLegSprintTimer) * 2 * sprintWeight * sprintJumpCancelWeight;
            float rightArmRotation = sprintArmSwingAnimation.getValueAt(rightLegSprintTimer) * (Mth.HALF_PI * 3 / 5F) * sprintWeight * sprintJumpCancelWeight;
            float rightArmForwardMovement = sprintArmSwingAnimation.getValueAt(rightLegSprintTimer) * 2 * sprintWeight * sprintJumpCancelWeight;

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
            this.leftArm.xRot += Mth.HALF_PI * 1/6F * sprintWeight * sprintJumpCancelWeight;
            this.rightArm.xRot += Mth.HALF_PI * 1/6F * sprintWeight * sprintJumpCancelWeight;
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
        float creativeFlyingWeight = getCreativeFlyingWeight(livingEntity);
        if(creativeFlyingWeight > 0){
            // Part variable definitions
            ModelPart mainArm = livingEntity.getMainArm() == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
            ModelPart offArm = livingEntity.getMainArm() == HumanoidArm.LEFT ? this.rightArm : this.leftArm;
            ModelPart mainLeg = livingEntity.getMainArm() == HumanoidArm.LEFT ? this.leftLeg : this.rightLeg;
            ModelPart offLeg = livingEntity.getMainArm() == HumanoidArm.LEFT ? this.rightLeg : this.leftLeg;

            // Animation variables
            float animationSpeed = getAnimationParameters(livingEntity).getAnimationSpeed();
            float forwardMovementOnlyMultiplier = 1 - getDirectionShift(livingEntity);
            float tickAtFrame = getAnimationParameters(livingEntity).getTickAtFrame();
            float movingInAirMultiplier = animationSpeed * forwardMovementOnlyMultiplier * creativeFlyingWeight;
            float movingUpWeight = getMovingUpWeight(livingEntity) * creativeFlyingWeight;
            float movingDownWeight = getMovingDownWeight(livingEntity) * creativeFlyingWeight;

            // Timer fields
            float primaryMovementTimer = new TimerProcessor(tickAtFrame).repeat(30, 0.0F).getValue();
            float secondaryMovementTimer = new TimerProcessor(tickAtFrame).repeat(30, 0.15F).getValue();
            float tertiaryMovementTimer = new TimerProcessor(tickAtFrame).repeat(30, 0.3F).getValue();

            // Oscillation curves from the timer fields
            float primaryMovementLerp = Timeline.oscillateLerpTimeline(Easing.CubicBezier.bezierInOutSine()).getValueAt(primaryMovementTimer);
            float secondaryMovementLerp = Timeline.oscillateLerpTimeline(Easing.CubicBezier.bezierInOutSine()).getValueAt(secondaryMovementTimer);
            float tertiaryMovementLerp = Timeline.oscillateLerpTimeline(Easing.CubicBezier.bezierInOutSine()).getValueAt(tertiaryMovementTimer);

            // Body, leg, and arm idle movements
            float bodyPrimaryBob = Mth.lerp(primaryMovementLerp, -0.5F, 0.5F) * creativeFlyingWeight;
            float bodySecondaryBobRotation = Mth.lerp(secondaryMovementLerp, Mth.HALF_PI / -32, Mth.HALF_PI / 32) * creativeFlyingWeight;
            float bodySecondaryBobPosition = Mth.lerp(secondaryMovementLerp, 0.5F, -0.5F) * creativeFlyingWeight;
            float mainLegLiftMovement = Mth.lerp(primaryMovementLerp, -3F, -2.5F) * creativeFlyingWeight;
            float mainLegForwardMovement = Mth.lerp(primaryMovementLerp, -3F, -2.5F) * creativeFlyingWeight;
            float mainLegRotation = Mth.lerp(secondaryMovementLerp, Mth.HALF_PI / -16, Mth.HALF_PI / 8) * creativeFlyingWeight;
            float offLegLiftMovement = Mth.lerp(secondaryMovementLerp, -0.5F, 0) * creativeFlyingWeight;
            float offLegForwardMovement = Mth.lerp(secondaryMovementLerp, 0.5F, 0.75F) * creativeFlyingWeight;
            float offLegRotation = Mth.lerp(tertiaryMovementLerp, Mth.HALF_PI / -20, Mth.HALF_PI / 16) * creativeFlyingWeight;
            float mainArmZRotation = Mth.lerp(primaryMovementLerp, Mth.HALF_PI / 6, Mth.HALF_PI / 30) * creativeFlyingWeight;
            float mainArmXRotation = Mth.lerp(secondaryMovementLerp, Mth.HALF_PI / -8, Mth.HALF_PI / 20) * creativeFlyingWeight;
            float offArmZRotation = Mth.lerp(secondaryMovementLerp, Mth.HALF_PI / 6, Mth.HALF_PI / 30) * creativeFlyingWeight;
            float offArmXRotation = Mth.lerp(tertiaryMovementLerp, Mth.HALF_PI / -8, Mth.HALF_PI / 20) * creativeFlyingWeight;

            // Additional movements when moving forwards
            float mainLegLiftMovementMovingForward = 2F * movingInAirMultiplier;
            float mainLegForwardMovementMovingForward = -0.5F * movingInAirMultiplier;
            float mainLegRotationMovingForward = Mth.HALF_PI / 3F * movingInAirMultiplier;
            float offLegLiftMovementMovingForward = 0.75F * movingInAirMultiplier;
            float offLegForwardMovementMovingForward = 1.25F * movingInAirMultiplier;
            float offLegRotationMovingForward = Mth.HALF_PI / 2F * movingInAirMultiplier;

            mainLeg.xRot += mainLegRotation + mainLegRotationMovingForward;
            mainLeg.y += mainLegLiftMovement + mainLegLiftMovementMovingForward;
            mainLeg.z += mainLegForwardMovement + mainLegForwardMovementMovingForward;
            offLeg.xRot += offLegRotation + offLegRotationMovingForward;
            offLeg.y += offLegLiftMovement + offLegLiftMovementMovingForward;
            offLeg.z += offLegForwardMovement + offLegForwardMovementMovingForward;
            mainArm.xRot += mainArmXRotation + mainLegRotationMovingForward;
            mainArm.zRot += mainArmZRotation;
            offArm.xRot += offArmXRotation + offLegRotationMovingForward;
            offArm.zRot += -offArmZRotation;
            this.body.xRot += bodySecondaryBobRotation;
            this.head.xRot += -0.2F * movingInAirMultiplier;
            for(ModelPart part : getPartListAll()){
                part.y += bodyPrimaryBob;
                if(getPartListBody().contains(part)){
                    part.z += bodySecondaryBobPosition;
                }
            }
        }
    }

    private void addSprintJumpPoseLayer(T livingEntity){
        float sprintJumpWeight = getSprintJumpWeight(livingEntity) * (1 - getCreativeFlyingWeight(livingEntity)) * (1 - getFallFlyingWeight(livingEntity)) * getAnimationParameters(livingEntity).getAnimationSpeed() * (1 - getFallFlyingWeight(livingEntity)) * (1 - getSwimWeight(livingEntity)) * (1 - getFallingWeight(livingEntity));
        if(sprintJumpWeight > 0){
            float sprintJumpReverser = Mth.lerp(((EntityAccess)livingEntity).getAnimationTimer("sprint_jump_reverser"), 1, -1);
            float sprintJumpTimer = ((EntityAccess) livingEntity).getAnimationTimer("sprint_jump_timer");
            float sprintJumpLegRotation = Mth.lerp(sprintJumpWeightAnimation.getValueAt(sprintJumpTimer), Mth.HALF_PI / 2, Mth.HALF_PI / -2) * sprintJumpWeight * sprintJumpReverser;
            this.leftLeg.xRot += sprintJumpLegRotation;
            this.rightLeg.xRot += -sprintJumpLegRotation;
            this.leftArm.xRot += -sprintJumpLegRotation;
            this.rightArm.xRot += sprintJumpLegRotation;
        }
    }

    private void addFallingAndImpactPoseLayer(T livingEntity){

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

    private void adjustTimers(T livingEntity){
        AbstractClientPlayer abstractClientPlayer = ((AbstractClientPlayer)livingEntity);
        float roughEntitySpeed = (float) (Math.abs(livingEntity.getDeltaMovement().x) + Math.abs(livingEntity.getDeltaMovement().z));
        float animationSpeed = getAnimationParameters(livingEntity).getAnimationSpeed();
        float delta = getAnimationParameters(livingEntity).getDelta();

        // Direction shift
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
        // Crouch and sprint
        ((EntityAccess)livingEntity).incrementAnimationTimer("crouch", livingEntity.isCrouching(), 0.25F, -0.3F);
        ((EntityAccess)livingEntity).incrementAnimationTimer("sprint", animationSpeed > 0.9, 0.0625F, -0.125F);
        // Creative flying
        ((EntityAccess) livingEntity).incrementAnimationTimer("creative_flying", abstractClientPlayer.getAbilities().flying, 20, -20);

        // Ticks after hitting ground
        float previousTicksAfterHittingGround = ((EntityAccess) livingEntity).getAnimationTimer("ticks_after_hitting_ground");
        float ticksAfterHittingGround = livingEntity.isOnGround() ? previousTicksAfterHittingGround + delta : 0;
        ((EntityAccess) livingEntity).setAnimationTimer("ticks_after_hitting_ground", ticksAfterHittingGround);

        float previousYMovement = ((EntityAccess) livingEntity).getAnimationTimer("y_movement");
        ((EntityAccess) livingEntity).setAnimationTimer("y_movement", (float) livingEntity.getDeltaMovement().y);

        // TODO: Repurpose the sprint jump stuff as jumping animations

        boolean shouldResetSprintJumpTimer = previousYMovement < 0 && livingEntity.getDeltaMovement().y > 0;
        ((EntityAccess) livingEntity).resetTimerOnCondition("sprint_jump_timer", shouldResetSprintJumpTimer, 15);

        // Ticks after switching legs
        float previousTicksAfterSwitchingLegs = ((EntityAccess) livingEntity).getAnimationTimer("ticks_after_switching_legs");
        float ticksAfterSwitchingLegs = shouldResetSprintJumpTimer ? 0 : previousTicksAfterSwitchingLegs + delta;
        ((EntityAccess) livingEntity).setAnimationTimer("ticks_after_switching_legs", ticksAfterSwitchingLegs);

        // Sprint jump weight
        boolean isSprintJumping = (ticksAfterHittingGround < 1 || !livingEntity.isOnGround()) && animationSpeed > 0.4 && ticksAfterSwitchingLegs < 10;
        ((EntityAccess) livingEntity).incrementAnimationTimer("sprint_jump_weight", isSprintJumping, 8, -8);

        // Switch the legs for sprint jumping
        float previousSprintJumpReverser = ((EntityAccess) livingEntity).getAnimationTimer("sprint_jump_reverser");
        ((EntityAccess) livingEntity).setAnimationTimer("sprint_jump_reverser", shouldResetSprintJumpTimer ? 1 - previousSprintJumpReverser : previousSprintJumpReverser);

        // Moving up and down weight
        ((EntityAccess) livingEntity).incrementAnimationTimer("moving_up_weight", livingEntity.getDeltaMovement().y > 0.1, 10, -10);
        ((EntityAccess) livingEntity).incrementAnimationTimer("moving_down_weight", livingEntity.getDeltaMovement().y < -0.1, 10, -10);

        // Falling
        ((EntityAccess) livingEntity).setAnimationTimer("fall_distance", livingEntity.fallDistance);
        boolean isFalling = livingEntity.fallDistance > 2 && !livingEntity.isInWater() && !livingEntity.isOnGround();
        ((EntityAccess) livingEntity).incrementAnimationTimer("falling_weight", isFalling, 10, -5);

        // Hitting the ground
        ((EntityAccess) livingEntity).resetTimerOnCondition("falling_impact", ticksAfterHittingGround < 1 && ((EntityAccess) livingEntity).getAnimationTimer("falling_weight") > 0.5F, 10);
    }

    // UNUSED

    private void adjustTimersOld(T livingEntity){

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
