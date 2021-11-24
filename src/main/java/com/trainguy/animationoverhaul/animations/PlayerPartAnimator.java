package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.animation.PartAnimationUtils;
import com.trainguy.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlayerPartAnimator<T extends LivingEntity, M extends EntityModel<T>> extends LivingEntityPartAnimator<T, M> {

    private final Player playerEntity;

    private final PlayerModel<T> playerModel;

    private final List<ModelPart> partListAll;
    private final HashMap<ModelPart, String[]> modelPartDictionary;

    private static final String SPRINT_WEIGHT = "sprint_weight";
    private static final String CROUCH_WEIGHT = "crouch_weight";
    private static final String DIRECTION_SHIFT = "direction_shift";
    private static final String TICKS_AFTER_HITTING_GROUND = "ticks_after_hitting_ground";
    private static final String SPRINT_JUMP_WEIGHT = "sprint_jump_weight";

    //TODO: add cases for handling inventory and hand animations
    //TODO: pass animation parameters to these

    public PlayerPartAnimator(T livingEntity, M model, LivingEntityAnimParams livingEntityAnimParams){
        super(livingEntity, model, livingEntityAnimParams);
        playerModel = (PlayerModel<T>)model;
        playerEntity = ((AbstractClientPlayer)livingEntity);
        partListAll = Arrays.asList(playerModel.leftArm, playerModel.rightArm, playerModel.leftLeg, playerModel.rightLeg, playerModel.body, playerModel.head, playerModel.cloak);

        modelPartDictionary = new HashMap<>(){{
            put(playerModel.head, new String[]{"head", "head"});
            put(playerModel.body, new String[]{"body", "body"});
            put(playerModel.leftLeg, new String[]{"leftLeg", "rightLeg"});
            put(playerModel.rightLeg, new String[]{"rightLeg", "leftLeg"});
            put(playerModel.leftArm, new String[]{"leftArm", "rightArm"});
            put(playerModel.rightArm, new String[]{"rightArm", "leftArm"});
            put(playerModel.cloak, new String[]{"cloak", "cloak"});
        }};
    }

    //TODO: local translation from the body to offset the cloak when armor is being worn

    @Override
    protected void initModel() {
        for(ModelPart part : partListAll){
            part.setPos(0, 0, 0);
            part.setRotation(0, 0, 0);
        }
    }

    @Override
    protected void adjustTimers() {
        incrementAnimationTimer(SPRINT_WEIGHT, playerEntity.isSprinting(), 10, -10);
        incrementAnimationTimer(CROUCH_WEIGHT, playerEntity.isCrouching(), 5, -5);

        // Legacy direction shift
        float moveAngleX = -Mth.sin(livingEntity.yBodyRot * Mth.PI / 180);
        float moveAngleZ = Mth.cos(livingEntity.yBodyRot * Mth.PI / 180);
        float deltaMovementX = (float) (livingEntity.getX() - livingEntity.xo) / animationParameters.getAnimationSpeed();
        float deltaMovementZ = (float) (livingEntity.getZ() - livingEntity.zo) / animationParameters.getAnimationSpeed();
        boolean isMovingForwards = true;
        if(animationParameters.getAnimationSpeed() > 0.01){
            if(
                    (moveAngleX >= 0 && deltaMovementX < 0 - 0.05) ||
                    (moveAngleX <= 0 && deltaMovementX > 0 + 0.05) ||
                    (moveAngleZ >= 0 && deltaMovementZ < 0 - 0.05) ||
                    (moveAngleZ <= 0 && deltaMovementZ > 0 + 0.05)
            ){
                isMovingForwards = false;
            }
        }
        incrementAnimationTimer(DIRECTION_SHIFT, !isMovingForwards, 5, -5);

        float deltaY = (float) (livingEntity.getY() - livingEntity.yo);


        // Ticks after hitting ground
        float previousTicksAfterHittingGround = getAnimationTimer(TICKS_AFTER_HITTING_GROUND);
        float ticksAfterHittingGround = livingEntity.isOnGround() ? previousTicksAfterHittingGround + animationParameters.getDelta() : 0;
        setAnimationTimer(TICKS_AFTER_HITTING_GROUND, ticksAfterHittingGround);

        boolean shouldResetSprintJumpTimer = (getAnimationTimer("previous_delta_y") < 0 && deltaY > 0) || (getAnimationTimer("previous_delta_y") == 0 && deltaY > 0);
        resetTimerOnCondition("sprint_jump_timer", shouldResetSprintJumpTimer, 15);
        setAnimationTimer("previous_delta_y", deltaY);

        // Ticks after switching legs
        float previousTicksAfterSwitchingLegs = getAnimationTimer("ticks_after_switching_legs");
        float ticksAfterSwitchingLegs = shouldResetSprintJumpTimer ? 0 : previousTicksAfterSwitchingLegs + animationParameters.getDelta();
        setAnimationTimer("ticks_after_switching_legs", ticksAfterSwitchingLegs);

        // Sprint jump weight
        boolean isSprintJumping = (ticksAfterHittingGround < 1 || !livingEntity.isOnGround()) && animationParameters.getAnimationSpeed() > 0.4 && ticksAfterSwitchingLegs < 10;
        incrementAnimationTimer("sprint_jump_weight", isSprintJumping, 8, -8);

        // Switch the legs for sprint jumping
        float previousSprintJumpReverser = getAnimationTimer("sprint_jump_reverser");
        setAnimationTimer("sprint_jump_reverser", shouldResetSprintJumpTimer ? 1 - previousSprintJumpReverser : previousSprintJumpReverser);

    }

    @Override
    protected void animateParts() {
        addPoseLayerLook();
        addPoseLayerWalk();
        addPoseLayerSprint();
        addPoseLayerIdle();
    }

    private void addPoseLayerLook(){
        playerModel.head.xRot = animationParameters.getHeadXRot();
        playerModel.head.yRot = animationParameters.getHeadYRot();

        float lookHorizontalTimer = 1 - Mth.clamp((animationParameters.getHeadYRot() / Mth.HALF_PI) + 0.5F, 0, 1);
        float lookVerticalTimer = 1 - Mth.clamp((animationParameters.getHeadXRot() / Mth.PI) + 0.5F, 0, 1);

        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, getTimelineGroup("look_horizontal"), modelPartDictionary, lookHorizontalTimer, 1, false);
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, getTimelineGroup("look_vertical"), modelPartDictionary, lookVerticalTimer, 1, false);
    }

    private void addPoseLayerWalk(){
        // Walking          0.85
        // Sprinting        1
        // Crouching        0.25

        AnimationData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup("walk_normal");
        AnimationData.TimelineGroup walkCrouchTimelineGroup = getTimelineGroup("walk_crouch");

        float walkNormalTimer = new TimerProcessor(animationParameters.getAnimationPosition())
                .speedUp(1.4F)
                .repeat(walkNormalTimelineGroup)
                .getValue();
        float walkCrouchTimer = new TimerProcessor(animationParameters.getAnimationPosition())
                .speedUp(2.5F)
                .repeat(walkCrouchTimelineGroup)
                .getValue();

        //walkNormalTimer = Mth.lerp(getAnimationTimerEasedSine(DIRECTION_SHIFT), walkNormalTimer, 1 - walkNormalTimer);
        //walkCrouchTimer = Mth.lerp(getAnimationTimerEasedSine(DIRECTION_SHIFT), walkCrouchTimer, 1 - walkCrouchTimer);

        float walkNormalWeight = (1 - getAnimationTimerEasedSine(SPRINT_WEIGHT))
                * Math.min(animationParameters.getAnimationSpeed() / 0.86F, 1)
                * (1 - getAnimationTimerEasedSine(CROUCH_WEIGHT));
        float walkCrouchWeight = Math.min(animationParameters.getAnimationSpeed() / 0.26F, 1)
                * getAnimationTimerEasedSine(CROUCH_WEIGHT);

        // TODO: Backwards walking animations
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, walkNormalTimelineGroup, modelPartDictionary, walkNormalTimer, walkNormalWeight, false);
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, walkCrouchTimelineGroup, modelPartDictionary, walkCrouchTimer, walkCrouchWeight, false);
    }

    private void addPoseLayerSprint(){
        // Referencing the walk's timeline length so that the walk and sprint cycles sync properly
        AnimationData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup("walk_normal");
        AnimationData.TimelineGroup sprintNormalTimelineGroup = getTimelineGroup("sprint_normal");

        float sprintNormalTimer = new TimerProcessor(animationParameters.getAnimationPosition())
                .speedUp(1.4F)
                .repeat(walkNormalTimelineGroup)
                .getValue();

        float sprintNormalWeight = getAnimationTimerEasedSine(SPRINT_WEIGHT)
                * Math.min(animationParameters.getAnimationSpeed() / 0.86F, 1)
                * (1 - getAnimationTimer("sprint_jump_weight"))
                * (1 - getAnimationTimerEasedSine(CROUCH_WEIGHT));

        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, sprintNormalTimelineGroup, modelPartDictionary, sprintNormalTimer, sprintNormalWeight, false);




        AnimationData.TimelineGroup sprintJumpTimelineGroup = getTimelineGroup("sprint_jump");
        float sprintJumpWeight = getAnimationTimer("sprint_jump_weight");
        if(sprintJumpWeight > 0){
            float sprintJumpReverser = Mth.lerp(getAnimationTimer("sprint_jump_reverser"), 1, -1);
            float sprintJumpTimer = getAnimationTimer("sprint_jump_timer");

            PartAnimationUtils.animateMultiplePartsAdditive(partListAll, sprintJumpTimelineGroup, modelPartDictionary, sprintJumpTimer, sprintJumpWeight, sprintJumpReverser == 1);

        }
    }

    private void addPoseLayerIdle(){
        AnimationData.TimelineGroup idleNormalTimelineGroup = getTimelineGroup("idle_normal");
        AnimationData.TimelineGroup crouchPoseTimelineGroup = getTimelineGroup("crouch");

        float idleNormalTimer = new TimerProcessor(animationParameters.getTickAtFrame())
                .repeat(idleNormalTimelineGroup)
                .getValue();

        float idleNormalWeight = (1 - Math.min(animationParameters.getAnimationSpeed() / 0.5F, 1))
            * (1 - (getAnimationTimerEasedSine(CROUCH_WEIGHT) * 0.75F));
        float idleCrouchWeight = (1 - Math.min(animationParameters.getAnimationSpeed() / 0.25F, 1));

        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, idleNormalTimelineGroup, modelPartDictionary, idleNormalTimer, idleNormalWeight, livingEntity.getMainArm() == HumanoidArm.LEFT);
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, crouchPoseTimelineGroup, modelPartDictionary, getAnimationTimer(CROUCH_WEIGHT), idleCrouchWeight, livingEntity.getMainArm() == HumanoidArm.LEFT);
    }

    @Override
    protected void finalizeModel() {

        if(livingEntity.isCrouching()){
            for(ModelPart part : partListAll){
                part.y -= 2;
            }
        }

        playerModel.leftArm.x += 5;
        playerModel.leftArm.y += 2;
        playerModel.rightArm.x -= 5;
        playerModel.rightArm.y += 2;
        playerModel.leftLeg.x += 1.95;
        playerModel.leftLeg.y += 12;
        playerModel.rightLeg.x -= 1.95;
        playerModel.rightLeg.y += 12;
        playerModel.cloak.z += 2;
        playerModel.cloak.yRot += Mth.PI;
        playerModel.cloak.xRot = -playerModel.cloak.xRot;

        playerModel.hat.copyFrom(playerModel.head);
        playerModel.leftPants.copyFrom(playerModel.leftLeg);
        playerModel.rightPants.copyFrom(playerModel.rightLeg);
        playerModel.leftSleeve.copyFrom(playerModel.leftArm);
        playerModel.rightSleeve.copyFrom(playerModel.rightArm);
        playerModel.jacket.copyFrom(playerModel.body);
    }

    @Override
    protected void adjustTimersInventory() {

    }

    @Override
    protected void animatePartsInventory() {

    }
}
