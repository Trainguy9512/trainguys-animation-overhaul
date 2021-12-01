package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.util.animation.Locator;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.Camera;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;

import java.util.Arrays;
import java.util.List;

public class PlayerPartAnimator extends LivingEntityPartAnimator<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private final List<Locator> locatorListAll;
    private final List<Locator> locatorListBody;

    private static final String SPRINT_WEIGHT = "sprint_weight";
    private static final String CROUCH_WEIGHT = "crouch_weight";
    private static final String DIRECTION_SHIFT = "direction_shift";
    private static final String TICKS_AFTER_HITTING_GROUND = "ticks_after_hitting_ground";
    private static final String JUMP_WEIGHT = "jump_weight";
    private static final String JUMP_TIMER = "jump_timer";
    private static final String JUMP_REVERSER = "jump_reverser";
    private static final String TICKS_AFTER_SWITCHING_LEGS = "ticks_after_switching_legs";
    private static final String FALL_WEIGHT = "fall_weight";
    private static final String FAST_FALL_WEIGHT = "fast_fall_weight";
    private static final String ON_GROUND_WEIGHT = "on_ground_weight";
    private static final String CLIMBING_UP_WEIGHT = "climbing_up_weight";
    private static final String CLIMBING_DOWN_WEIGHT = "climbing_down_weight";

    private final Locator locatorMaster;
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

    public PlayerPartAnimator(AbstractClientPlayer abstractClientPlayer, PlayerModel<AbstractClientPlayer> playerModel, LivingEntityAnimParams livingEntityAnimParams){
        super(abstractClientPlayer, playerModel, livingEntityAnimParams);

        this.locatorMaster = new Locator("master");
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
        locatorListBody = Arrays.asList(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak);

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

    @Override
    protected void adjustTimers() {
        super.adjustTimers();

        boolean isCrouching = livingEntity.isCrouching();
        boolean isSprinting = livingEntity.isSprinting();
        if(isCrouching && isSprinting){
            isSprinting = false;
        }
        incrementAnimationTimer(SPRINT_WEIGHT, isSprinting, 10, -10);
        incrementAnimationTimer(CROUCH_WEIGHT, isCrouching, 3, -3);

        // Legacy direction shift
        float moveAngleX = -Mth.sin(livingEntity.yBodyRotO * Mth.PI / 180);
        float moveAngleZ = Mth.cos(livingEntity.yBodyRotO * Mth.PI / 180);
        float deltaMovementX = (float) (livingEntity.getX() - livingEntity.xo) / getAnimationTimer(ANIMATION_SPEED);
        float deltaMovementZ = (float) (livingEntity.getZ() - livingEntity.zo) / getAnimationTimer(ANIMATION_SPEED);
        boolean isMovingForwards = true;
        if(getAnimationTimer(ANIMATION_SPEED) > 0.01){
            if(
                    (moveAngleX >= 0 && deltaMovementX < 0 - 0.1) ||
                    (moveAngleX <= 0 && deltaMovementX > 0 + 0.1) ||
                    (moveAngleZ >= 0 && deltaMovementZ < 0 - 0.1) ||
                    (moveAngleZ <= 0 && deltaMovementZ > 0 + 0.1)
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

        boolean shouldResetJumpTimer =
                (getAnimationTimer(DELTA_Y_OLD) < 0 && getAnimationTimer(DELTA_Y) > 0)
                || (getAnimationTimer(DELTA_Y_OLD) == 0 && getAnimationTimer(DELTA_Y) > 0);
        resetTimerOnCondition(JUMP_TIMER, shouldResetJumpTimer, 12);

        // Ticks after switching legs
        float previousTicksAfterSwitchingLegs = getAnimationTimer(TICKS_AFTER_SWITCHING_LEGS);
        float ticksAfterSwitchingLegs = shouldResetJumpTimer ? 0 : previousTicksAfterSwitchingLegs + animationParameters.getDelta();
        setAnimationTimer(TICKS_AFTER_SWITCHING_LEGS, ticksAfterSwitchingLegs);

        // Sprint jump weight
        boolean isJumping =
                (ticksAfterHittingGround < 1 || !livingEntity.isOnGround())
                && getAnimationTimer(DELTA_Y) != 0
                && ticksAfterSwitchingLegs < 8;
        incrementAnimationTimer(JUMP_WEIGHT, isJumping, 4, -8);

        // Switch the legs for sprint jumping
        float previousSprintJumpReverser = getAnimationTimer(JUMP_REVERSER);
        setAnimationTimer(JUMP_REVERSER, shouldResetJumpTimer ? 1 - previousSprintJumpReverser : previousSprintJumpReverser);

        // End sprint jump timers

        // Falling weight
        incrementAnimationTimer(FALL_WEIGHT, livingEntity.fallDistance > 0, 8, -4);
        incrementAnimationTimer(FAST_FALL_WEIGHT, livingEntity.fallDistance > 6, 12, -2);

        incrementAnimationTimer(ON_GROUND_WEIGHT, livingEntity.isOnGround(), 6, -6);

        //setAnimationTimer("vertical_blocks_per_second", (float) (livingEntity.getY() - livingEntity.yo) * 20);
        incrementAnimationTimer(CLIMBING_UP_WEIGHT, livingEntity.onClimbable() && getAnimationTimer(DELTA_Y) >= 0, 8, -8);
        incrementAnimationTimer(CLIMBING_DOWN_WEIGHT, livingEntity.onClimbable() && getAnimationTimer(DELTA_Y) < 0, 8, -8);
    }

    @Override
    protected void animateParts() {
        addPoseLayerLook();
        addPoseLayerWalk();
        addPoseLayerSprint();
        addPoseLayerIdle();
        addPoseLayerJump();
        addPoseLayerFall();
        addPoseLayerClimbing();

        // done: walking, sprinting, jump, sprint jump, idle, crouch idle
        // scrapped: creative flying
        // TODO: swimming, fall flying, crawling, wading in water, riding in boat, sleeping, riding minecart, levitating, trident spinning, damage, death

        // to be done later with respective entities: riding entities

        // Item interaction pose layers
        // TODO: holding item, using bow, mining with pickaxe, using crossbow, using shield, using spyglass, eating and drinking, throwing trident, equipping armor
        // TODO: fishing rod reeling, mining with pickaxe, interacting with fist?
    }

    private void addPoseLayerLook(){
        locatorHead.xRot = animationParameters.getHeadXRot();
        locatorHead.yRot = animationParameters.getHeadYRot();
        float lookHorizontalTimer = 1 - getLookLeftRightTimer();
        float lookVerticalTimer = 1 - getLookUpDownTimer();
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, getTimelineGroup("look_horizontal"), lookHorizontalTimer, 1, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, getTimelineGroup("look_vertical"), lookVerticalTimer, 1, false);
    }

    private void addPoseLayerWalk(){
        AnimationData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup("walk_normal");
        AnimationData.TimelineGroup walkCrouchTimelineGroup = getTimelineGroup("walk_crouch");

        float walkNormalTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION))
                .speedUp(1.4F)
                .repeat(walkNormalTimelineGroup)
                .getValue();
        float walkCrouchTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION))
                .speedUp(2.5F)
                .repeat(walkCrouchTimelineGroup)
                .getValue();
        //walkNormalTimer = Mth.lerp(getAnimationTimerEasedSine(DIRECTION_SHIFT), walkNormalTimer, 1 - walkNormalTimer);
        //walkCrouchTimer = Mth.lerp(getAnimationTimerEasedSine(DIRECTION_SHIFT), walkCrouchTimer, 1 - walkCrouchTimer);

        float walkNormalWeight = (1 - getAnimationTimerEasedQuad(SPRINT_WEIGHT))
                * Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.86F, 1)
                * (1 - getAnimationTimerEasedQuad(JUMP_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(CROUCH_WEIGHT))
                * getAnimationTimerEasedQuad(ON_GROUND_WEIGHT)
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));
        float walkCrouchWeight = Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.26F, 1)
                * (1 - getAnimationTimerEasedQuad(JUMP_WEIGHT))
                * getAnimationTimerEasedQuad(ON_GROUND_WEIGHT)
                * getAnimationTimerEasedQuad(CROUCH_WEIGHT)
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));

        List<Locator> legLocators = Arrays.asList(locatorLeftLeg, locatorRightLeg);
        List<Locator> nonReversibleWalkLocators = Arrays.asList(locatorBody, locatorHead, locatorCloak, locatorLeftArm, locatorRightArm, locatorLeftHand, locatorRightHand);

        // Non reversible stuff
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocators, walkNormalTimelineGroup, walkNormalTimer, walkNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocators, walkCrouchTimelineGroup, walkCrouchTimer, walkCrouchWeight, false);

        // Legs
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkNormalTimelineGroup, walkNormalTimer, walkNormalWeight * (1 - getAnimationTimerEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkCrouchTimelineGroup, walkCrouchTimer, walkCrouchWeight * (1 - getAnimationTimerEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkNormalTimelineGroup, 1 - walkNormalTimer, walkNormalWeight * getAnimationTimerEasedQuad(DIRECTION_SHIFT), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkCrouchTimelineGroup, 1 - walkCrouchTimer, walkCrouchWeight * getAnimationTimerEasedQuad(DIRECTION_SHIFT), false);
    }

    private void addPoseLayerSprint(){
        // Referencing the walk's timeline length so that the walk and sprint cycles sync properly
        AnimationData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup("walk_normal");
        AnimationData.TimelineGroup sprintNormalTimelineGroup = getTimelineGroup("sprint_normal");

        float sprintNormalTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION))
                .speedUp(1.4F)
                .repeat(walkNormalTimelineGroup)
                .getValue();

        float sprintNormalWeight = getAnimationTimerEasedQuad(SPRINT_WEIGHT)
                * Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.86F, 1)
                * (1 - getAnimationTimerEasedQuad(JUMP_WEIGHT))
                * getAnimationTimerEasedQuad(ON_GROUND_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(CROUCH_WEIGHT))
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, sprintNormalTimelineGroup, sprintNormalTimer, sprintNormalWeight, false);
    }

    private void addPoseLayerJump(){
        AnimationData.TimelineGroup sprintJumpTimelineGroup = getTimelineGroup("sprint_jump");
        AnimationData.TimelineGroup walkJumpTimelineGroup = getTimelineGroup("walk_jump");

        float jumpWeight = getAnimationTimerEasedQuad(JUMP_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(CLIMBING_UP_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(CLIMBING_DOWN_WEIGHT));
        float sprintJumpWeight = jumpWeight * getAnimationTimerEasedQuad(SPRINT_WEIGHT);
        float walkJumpWeight = jumpWeight * (1 - getAnimationTimerEasedQuad(SPRINT_WEIGHT));
        float jumpReverser = Mth.lerp(getAnimationTimer(JUMP_REVERSER), 1, -1);
        float jumpTimer = getAnimationTimer(JUMP_TIMER);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, sprintJumpTimelineGroup, jumpTimer, sprintJumpWeight, jumpReverser == 1);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, walkJumpTimelineGroup, jumpTimer, walkJumpWeight, jumpReverser == 1);
    }

    private void addPoseLayerFall(){
        AnimationData.TimelineGroup fallSlowTimelineGroup = getTimelineGroup("fall_short");
        AnimationData.TimelineGroup fallFastTimelineGroup = getTimelineGroup("fall_fast");

        float fastFallLerp = getAnimationTimer(FAST_FALL_WEIGHT);
        float fallWeight = getAnimationTimerEasedQuad(FALL_WEIGHT)
                * getAnimationTimer(ANIMATION_SPEED_Y)
                * (1 - getAnimationTimerEasedQuad(CLIMBING_UP_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(CLIMBING_DOWN_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(JUMP_WEIGHT));
        float slowFallWeight = fallWeight * (1 - fastFallLerp);
        float fastFallWeight = fallWeight * fastFallLerp;

        float fallSlowTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION_Y))
                .speedUp(1)
                .repeat(fallSlowTimelineGroup)
                .getValue();
        float fallFastTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION_Y))
                .speedUp(1.5F)
                .repeat(fallFastTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, fallSlowTimelineGroup, fallSlowTimer, slowFallWeight, getAnimationTimer(JUMP_REVERSER) != 1);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, fallFastTimelineGroup, fallFastTimer, fastFallWeight, getAnimationTimer(JUMP_REVERSER) != 1);
    }

    private void addPoseLayerClimbing(){
        AnimationData.TimelineGroup climbUpTimelineGroup = getTimelineGroup("climb_up");
        AnimationData.TimelineGroup climbDownTimelineGroup = getTimelineGroup("climb_down");

        float climbUpWeight = getAnimationTimerEasedQuad(CLIMBING_UP_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(ON_GROUND_WEIGHT));
        float climbDownWeight = getAnimationTimerEasedQuad(CLIMBING_DOWN_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(ON_GROUND_WEIGHT));
        float climbUpTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION_Y))
                .speedUp(1.75F)
                .repeat(climbUpTimelineGroup)
                .getValue();

        float climbDownTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION_Y))
                .speedUp(1.5F)
                .repeat(climbDownTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, climbUpTimelineGroup, climbUpTimer, climbUpWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, climbDownTimelineGroup, 1 - climbDownTimer, climbDownWeight, false);
    }

    private void addPoseLayerIdle(){
        AnimationData.TimelineGroup idleNormalTimelineGroup = getTimelineGroup("idle_normal");
        AnimationData.TimelineGroup idleCrouchTimelineGroup = getTimelineGroup("idle_crouch");

        float idleNormalTimer = new TimerProcessor(animationParameters.getTickAtFrame())
                .repeat(idleNormalTimelineGroup)
                .getValue();

        float idleNormalWeight = (1 - Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.5F, 1))
                * (1 - (getAnimationTimerEasedQuad(CROUCH_WEIGHT)))
                * (getAnimationTimerEasedQuad(ON_GROUND_WEIGHT))
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));
        float idleCrouchWeight = (1 - Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.25F, 1))
                * getAnimationTimerEasedQuad(CROUCH_WEIGHT)
                * (getAnimationTimerEasedQuad(ON_GROUND_WEIGHT))
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleNormalTimelineGroup, idleNormalTimer, idleNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleCrouchTimelineGroup, idleNormalTimer, idleCrouchWeight, false);

        // Removes the vanilla transformation done for the crouch pose
        if(livingEntity.isCrouching()){
            for(Locator locator : locatorListBody){
                locator.y -= 2;
            }
        }
    }

    @Override
    protected void finalizeModel() {
        this.model.leftArm.x += 5;
        this.model.leftArm.y += 2;
        this.model.rightArm.x -= 5;
        this.model.rightArm.y += 2;
        this.model.leftLeg.x += 1.95;
        this.model.leftLeg.y += 12;
        this.model.rightLeg.x -= 1.95;
        this.model.rightLeg.y += 12;
        this.model.cloak.z += 2;
        this.model.cloak.yRot += Mth.PI;
        this.model.cloak.xRot = -this.model.cloak.xRot;

        this.model.hat.copyFrom(this.model.head);
        this.model.leftPants.copyFrom(this.model.leftLeg);
        this.model.rightPants.copyFrom(this.model.rightLeg);
        this.model.leftSleeve.copyFrom(this.model.leftArm);
        this.model.rightSleeve.copyFrom(this.model.rightArm);
        this.model.jacket.copyFrom(this.model.body);
    }

    @Override
    protected void adjustTimersInventory() {
    }

    @Override
    protected void animatePartsInventory() {
        addPoseLayerInventoryIdle();
    }

    private void addPoseLayerInventoryIdle(){
        AnimationData.TimelineGroup idleNormalTimelineGroup = getTimelineGroup("idle_normal");

        float idleNormalTimer = new TimerProcessor(animationParameters.getTickAtFrame())
                .repeat(idleNormalTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleNormalTimelineGroup, idleNormalTimer, 1, false);
    }
}
