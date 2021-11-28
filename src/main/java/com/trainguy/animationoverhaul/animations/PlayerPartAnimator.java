package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.util.animation.Locator;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.animation.PartAnimationUtils;
import com.trainguy.animationoverhaul.util.time.Easing;
import com.trainguy.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlayerPartAnimator<T extends LivingEntity, M extends EntityModel<T>> extends LivingEntityPartAnimator<T, M> {

    private final PlayerModel<T> playerModel;

    private final List<ModelPart> partListAll;
    public final HashMap<ModelPart, String[]> modelPartDictionary;

    private final List<Locator> locatorListAll;

    private static final String SPRINT_WEIGHT = "sprint_weight";
    private static final String CROUCH_WEIGHT = "crouch_weight";
    private static final String DIRECTION_SHIFT = "direction_shift";
    private static final String TICKS_AFTER_HITTING_GROUND = "ticks_after_hitting_ground";
    private static final String WALK_JUMP_WEIGHT = "walk_jump_weight";
    private static final String SPRINT_JUMP_WEIGHT = "sprint_jump_weight";
    private static final String SPRINT_JUMP_TIMER = "sprint_jump_timer";
    private static final String SPRINT_JUMP_REVERSER = "sprint_jump_reverser";
    private static final String TICKS_AFTER_SWITCHING_LEGS = "ticks_after_switching_legs";

    private final Locator locatorHead;
    private final Locator locatorBody;
    private final Locator locatorLeftLeg;
    private final Locator locatorRightLeg;
    private final Locator locatorLeftArm;
    private final Locator locatorRightArm;
    private final Locator locatorCloak;
    private final Locator locatorLeftHand;
    private final Locator locatorRightHand;

    //TODO: add cases for handling inventory and hand animations
    //TODO: pass animation parameters to these

    public PlayerPartAnimator(T livingEntity, M model, LivingEntityAnimParams livingEntityAnimParams){
        super(livingEntity, model, livingEntityAnimParams);
        playerModel = (PlayerModel<T>) model;

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


        this.locatorHead = new Locator("head");
        this.locatorBody = new Locator("body");
        this.locatorLeftLeg = new Locator("leftLeg");
        this.locatorRightLeg = new Locator("rightLeg");
        this.locatorLeftArm = new Locator("leftArm");
        this.locatorRightArm = new Locator("rightArm");
        this.locatorCloak = new Locator("cloak");
        this.locatorLeftHand = new Locator("leftHand");
        this.locatorRightHand = new Locator("rightHand");

        locatorListAll = Arrays.asList(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorLeftHand, locatorRightHand);

        this.locatorRig.addLocatorModelPart(locatorHead, playerModel.head);
        this.locatorRig.addLocatorModelPart(locatorBody, playerModel.body);
        this.locatorRig.addLocatorModelPart(locatorLeftLeg, locatorRightLeg, playerModel.leftLeg);
        this.locatorRig.addLocatorModelPart(locatorRightLeg, locatorLeftLeg, playerModel.rightLeg);
        this.locatorRig.addLocatorModelPart(locatorLeftArm, locatorRightArm, playerModel.leftArm);
        this.locatorRig.addLocatorModelPart(locatorRightArm, locatorLeftArm, playerModel.rightArm);
        this.locatorRig.addLocatorModelPart(locatorCloak, playerModel.cloak);
        this.locatorRig.addLocator(locatorLeftHand, locatorRightHand);
        this.locatorRig.addLocator(locatorRightHand, locatorLeftHand);
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


        incrementAnimationTimer(SPRINT_WEIGHT, livingEntity.isCrouching(), 10, -10);
        incrementAnimationTimer(CROUCH_WEIGHT, livingEntity.isCrouching(), 5, -5);

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

        // Begin sprint jump timers
        // Ticks after hitting ground
        float previousTicksAfterHittingGround = getAnimationTimer(TICKS_AFTER_HITTING_GROUND);
        float ticksAfterHittingGround = livingEntity.isOnGround() ? previousTicksAfterHittingGround + animationParameters.getDelta() : 0;
        setAnimationTimer(TICKS_AFTER_HITTING_GROUND, ticksAfterHittingGround);

        boolean shouldResetSprintJumpTimer =
                (getAnimationTimer(DELTA_Y_OLD) < 0 && getAnimationTimer(DELTA_Y) > 0)
                || (getAnimationTimer(DELTA_Y_OLD) == 0 && getAnimationTimer(DELTA_Y) > 0);
        resetTimerOnCondition(SPRINT_JUMP_TIMER, shouldResetSprintJumpTimer, 12);

        // Ticks after switching legs
        float previousTicksAfterSwitchingLegs = getAnimationTimer(TICKS_AFTER_SWITCHING_LEGS);
        float ticksAfterSwitchingLegs = shouldResetSprintJumpTimer ? 0 : previousTicksAfterSwitchingLegs + animationParameters.getDelta();
        setAnimationTimer(TICKS_AFTER_SWITCHING_LEGS, ticksAfterSwitchingLegs);

        // Sprint jump weight
        boolean isSprintJumping =
                (ticksAfterHittingGround < 1 || !livingEntity.isOnGround())
                && livingEntity.isSprinting()
                && getAnimationTimer(DELTA_Y) != 0
                && ticksAfterSwitchingLegs < 15;
        incrementAnimationTimer(SPRINT_JUMP_WEIGHT, isSprintJumping, 4, -4);

        // Walk jump weight
        boolean isWalkJumping =
                (ticksAfterHittingGround < 1 || !livingEntity.isOnGround())
                && !livingEntity.isSprinting()
                && getAnimationTimer(DELTA_Y) != 0
                && ticksAfterSwitchingLegs < 15;
        incrementAnimationTimer(WALK_JUMP_WEIGHT, isWalkJumping, 4, -4);

        // Switch the legs for sprint jumping
        float previousSprintJumpReverser = getAnimationTimer(SPRINT_JUMP_REVERSER);
        setAnimationTimer(SPRINT_JUMP_REVERSER, shouldResetSprintJumpTimer ? 1 - previousSprintJumpReverser : previousSprintJumpReverser);

        //End sprint jump timers
    }

    @Override
    protected void animateParts() {
        addPoseLayerLook();
        addPoseLayerWalk();
        addPoseLayerSprint();
        addPoseLayerIdle();
        addPoseLayerJump();
    }

    private void addPoseLayerLook(){
        playerModel.head.xRot = animationParameters.getHeadXRot();
        playerModel.head.yRot = animationParameters.getHeadYRot();

        float lookHorizontalTimer = 1 - getLookLeftRightTimer();
        float lookVerticalTimer = 1 - getLookUpDownTimer();

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
                * (1 - getAnimationTimerEasedSine(WALK_JUMP_WEIGHT))
                * (1 - getAnimationTimerEasedSine(CROUCH_WEIGHT));
        float walkCrouchWeight = Math.min(animationParameters.getAnimationSpeed() / 0.26F, 1)
                * (1 - getAnimationTimerEasedSine(WALK_JUMP_WEIGHT))
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
                * (1 - getSprintJumpWeight())
                * (1 - getAnimationTimerEasedSine(CROUCH_WEIGHT));

        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, sprintNormalTimelineGroup, modelPartDictionary, sprintNormalTimer, sprintNormalWeight, false);
    }

    private void addPoseLayerJump(){
        AnimationData.TimelineGroup sprintJumpTimelineGroup = getTimelineGroup("sprint_jump");
        float sprintJumpWeight = getSprintJumpWeight();
        float sprintJumpReverser = Mth.lerp(getAnimationTimer(SPRINT_JUMP_REVERSER), 1, -1);
        float sprintJumpTimer = getAnimationTimer(SPRINT_JUMP_TIMER);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, sprintJumpTimelineGroup, sprintJumpTimer, sprintJumpWeight, sprintJumpReverser == 1);
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

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleNormalTimelineGroup, idleNormalTimer, idleNormalWeight, false);
        PartAnimationUtils.animateMultiplePartsAdditive(partListAll, crouchPoseTimelineGroup, modelPartDictionary, getAnimationTimer(CROUCH_WEIGHT), idleCrouchWeight, livingEntity.getMainArm() == HumanoidArm.LEFT);
    }

    private float getSprintJumpWeight(){
        boolean isSprintJumping =
                (getAnimationTimer(TICKS_AFTER_HITTING_GROUND) < 1 || !livingEntity.isOnGround())
                && livingEntity.isSprinting()
                && getAnimationTimer(DELTA_Y) != 0
                && getAnimationTimer(TICKS_AFTER_SWITCHING_LEGS) < 15;

        return getEasedConditionAnimationTimer(SPRINT_JUMP_WEIGHT, Easing.CubicBezier.bezierOutQuad(), Easing.CubicBezier.bezierInQuad(), isSprintJumping);
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
