package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.util.animation.Locator;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.time.Easing;
import com.trainguy.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.List;

public class PlayerAnimator extends LivingEntityAnimator<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

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
    private static final String VISUAL_SWIMMING_WEIGHT = "visual_swimming_weight";
    private static final String IN_WATER_WEIGHT = "in_water_weight";
    private static final String UNDER_WATER_WEIGHT = "under_water_weight";
    private static final String MOVING_UP_WEIGHT = "moving_up_weight";
    private static final String TRUDGE_WEIGHT = "trudge_weight";
    private static final String IS_PASSENGER_TIMER = "is_passenger_timer";
    private static final String MINECART_MOVING_DOWN_WEIGHT = "minecart_moving_down_weight";

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

    private final List<Locator> locatorListAll;
    private final List<Locator> locatorListBody;
    private final List<Locator> locatorListMaster;

    //TODO: add cases for handling inventory and hand animations
    //TODO: pass animation parameters to these

    public PlayerAnimator(){
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
        locatorListMaster = Arrays.asList(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorLeftHand, locatorRightHand, locatorMaster);
    }

    public void setProperties(AbstractClientPlayer abstractClientPlayer, PlayerModel<AbstractClientPlayer> model, float tickProgress){
        super.setProperties(abstractClientPlayer, model, tickProgress);

        this.locatorRig.addLocator(locatorMaster);
        this.locatorRig.addLocatorModelPart(locatorHead, this.model.head);
        this.locatorRig.addLocatorModelPart(locatorBody, this.model.body);
        this.locatorRig.addLocatorModelPart(locatorLeftLeg, locatorRightLeg, this.model.leftLeg);
        this.locatorRig.addLocatorModelPart(locatorRightLeg, locatorLeftLeg, this.model.rightLeg);
        this.locatorRig.addLocatorModelPart(locatorLeftArm, locatorRightArm, this.model.leftArm);
        this.locatorRig.addLocatorModelPart(locatorRightArm, locatorLeftArm, this.model.rightArm);
        this.locatorRig.addLocatorModelPart(locatorCloak, this.model.cloak);
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
        float ticksAfterHittingGround = livingEntity.isOnGround() ? previousTicksAfterHittingGround + this.delta : 0;
        setAnimationTimer(TICKS_AFTER_HITTING_GROUND, ticksAfterHittingGround);

        boolean shouldResetJumpTimer =
                ((getAnimationTimer(DELTA_Y_OLD) < 0 && getAnimationTimer(DELTA_Y) > 0)
                || (getAnimationTimer(DELTA_Y_OLD) == 0 && getAnimationTimer(DELTA_Y) > 0))
                && getAnimationTimer(TICKS_AFTER_SWITCHING_LEGS) > 4;
        resetTimerOnCondition(JUMP_TIMER, shouldResetJumpTimer, 12);

        // Ticks after switching legs
        float previousTicksAfterSwitchingLegs = getAnimationTimer(TICKS_AFTER_SWITCHING_LEGS);
        float ticksAfterSwitchingLegs = shouldResetJumpTimer ? 0 : previousTicksAfterSwitchingLegs + this.delta;
        setAnimationTimer(TICKS_AFTER_SWITCHING_LEGS, ticksAfterSwitchingLegs);

        // Sprint jump weight
        boolean isJumping =
                (ticksAfterHittingGround < 1 || !livingEntity.isOnGround())
                && getAnimationTimer(DELTA_Y) != 0
                && ticksAfterSwitchingLegs < 15;
        incrementAnimationTimer(JUMP_WEIGHT, isJumping, 4, -4);

        // Switch the legs for sprint jumping
        float previousSprintJumpReverser = getAnimationTimer(JUMP_REVERSER);
        setAnimationTimer(JUMP_REVERSER, shouldResetJumpTimer ? 1 - previousSprintJumpReverser : previousSprintJumpReverser);

        // End sprint jump timers

        // Falling weight
        incrementAnimationTimer(FALL_WEIGHT, livingEntity.fallDistance > 0, 20, -4);
        incrementAnimationTimer(FAST_FALL_WEIGHT, livingEntity.fallDistance > 6, 24, -2);

        incrementAnimationTimer(ON_GROUND_WEIGHT, livingEntity.isOnGround(), 6, -6);

        //setAnimationTimer("vertical_blocks_per_second", (float) (livingEntity.getY() - livingEntity.yo) * 20);
        incrementAnimationTimer(CLIMBING_UP_WEIGHT, livingEntity.onClimbable() && getAnimationTimer(DELTA_Y) >= 0, 8, -8);
        incrementAnimationTimer(CLIMBING_DOWN_WEIGHT, livingEntity.onClimbable() && getAnimationTimer(DELTA_Y) < 0, 8, -8);

        incrementAnimationTimer(VISUAL_SWIMMING_WEIGHT, livingEntity.isVisuallySwimming(), 16, -16);

        incrementAnimationTimer(IN_WATER_WEIGHT, livingEntity.isInWater(), 8, -8);
        incrementAnimationTimer(UNDER_WATER_WEIGHT, livingEntity.isUnderWater(), 8, -8);
        incrementAnimationTimer(MOVING_UP_WEIGHT, getAnimationTimer(DELTA_Y) > 0.12, 8, -8);

        incrementAnimationTimer(TRUDGE_WEIGHT, livingEntity.isInPowderSnow || livingEntity.getFeetBlockState().getBlock() == Blocks.SOUL_SAND, 8, -8);

        adjustHurtTimers(4);

        if(livingEntity.deathTime != 0 && getAnimationTimer(DEATH_TIMER) == 0){
            setAnimationTimer(DEATH_TIMER + "_index", 0);
            if(getAnimationTimer(FALL_WEIGHT) > 0){
                setAnimationTimer(DEATH_TIMER + "_index", 1);
            }
        }
        adjustDeathTimer();
        adjustSleepTimer();

        resetTimerOnCondition(IS_PASSENGER_TIMER, !livingEntity.isPassenger(), (int) TimerProcessor.framesToTicks(17));
    }

    @Override
    protected void animateParts() {
        addPoseLayerLook();
        addPoseLayerWalk();
        addPoseLayerSprint();
        addPoseLayerJump();
        addPoseLayerFall();
        addPoseLayerClimbing();
        addPoseLayerCrawling();
        addPoseLayerSwimming();

        addPoseLayerIdle();

        addPoseLayerMinecart();
        addPoseLayerBoat();
        addPoseLayerMount();

        // Item interactions

        addPoseLayerSleep();
        addPoseLayerDeath();
        addPoseLayerHurt(List.of(getTimelineGroup("hurt_0"), getTimelineGroup("hurt_1"), getTimelineGroup("hurt_2"), getTimelineGroup("hurt_3")), locatorListAll);


        // done: walking, sprinting, jump, sprint jump, idle, crouch idle, climbing
        // scrapped: creative flying
        // TODO: fall flying, levitating, trident spinning

        // to be done later with respective entities: riding entities

        // Item interaction pose layers
        // TODO: holding item, using bow, mining with pickaxe, using crossbow, using shield, using spyglass, eating and drinking, throwing trident, equipping armor
        // TODO: fishing rod reeling, mining with pickaxe, interacting with fist?
    }

    private void addPoseLayerSleep(){
        AnimationData.TimelineGroup sleepMasterTimelineGroup = getTimelineGroup("sleep_master");
        AnimationData.TimelineGroup sleepStartTimelineGroup = getTimelineGroup("sleep_start");

        float sleepIdleTimer = new TimerProcessor(tickAtFrame)
                .speedUp(1)
                .repeat(sleepMasterTimelineGroup)
                .getValue();
        float sleepStartTimer = getAnimationTimer(SLEEP_TIMER);

        float sleepWeight = livingEntity.getPose() == Pose.SLEEPING ? 1 : 0;

        this.locatorRig.weightedClearTransforms(locatorListMaster, sleepWeight);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListMaster, sleepMasterTimelineGroup, sleepIdleTimer, sleepWeight);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, sleepStartTimelineGroup, sleepStartTimer, sleepWeight);
    }

    private void addPoseLayerMinecart(){
        if(livingEntity.isPassenger() && livingEntity.getVehicle() instanceof AbstractMinecart){
            AnimationData.TimelineGroup minecartIdleTimelineGroup = getTimelineGroup("minecart_master");
            AnimationData.TimelineGroup minecartStartTimelineGroup = getTimelineGroup("minecart_start");

            float minecartIdleTimer = new TimerProcessor(this.tickAtFrame)
                    .repeat(minecartIdleTimelineGroup)
                    .getValue();
            float minecartStartTimer = getAnimationTimer(IS_PASSENGER_TIMER);
            float lookVerticalTimer = 1 - getLookUpDownTimer();

            this.locatorRig.weightedClearTransforms(locatorListMaster, 1);

            locatorHead.xRot = this.headXRot;
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, getTimelineGroup("look_vertical"), lookVerticalTimer, 1, false);
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, minecartIdleTimelineGroup, minecartIdleTimer, 1, isLeftHanded());
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, minecartStartTimelineGroup, minecartStartTimer, 1, isLeftHanded());
        }
    }

    private void addPoseLayerBoat(){
        AnimationData.TimelineGroup boatIdleTimelineGroup = getTimelineGroup("minecart_master");
        AnimationData.TimelineGroup boatStartTimelineGroup = getTimelineGroup("minecart_start");
        if(livingEntity.isPassenger() && livingEntity.getVehicle() instanceof Boat){

            float boatIdleTimer = new TimerProcessor(this.tickAtFrame)
                    .repeat(boatIdleTimelineGroup)
                    .getValue();
            float boatStartTimer = getAnimationTimer(IS_PASSENGER_TIMER);

            this.locatorRig.weightedClearTransforms(locatorListMaster, 1);
            addPoseLayerLook();
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, boatIdleTimelineGroup, boatIdleTimer, 1, isLeftHanded());
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, boatStartTimelineGroup, boatStartTimer, 1, isLeftHanded());
        }
    }

    private void addPoseLayerMount(){
        AnimationData.TimelineGroup mountIdleTimelineGroup = getTimelineGroup("mount_master");
        AnimationData.TimelineGroup mountStartTimelineGroup = getTimelineGroup("minecart_start");
        if(livingEntity.isPassenger() && livingEntity.getVehicle() instanceof LivingEntity){

            float mountIdleTimer = new TimerProcessor(this.tickAtFrame)
                    .repeat(mountIdleTimelineGroup)
                    .getValue();
            float mounrStartTimer = getAnimationTimer(IS_PASSENGER_TIMER);

            this.locatorRig.weightedClearTransforms(locatorListMaster, 1);
            addPoseLayerLook();
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, mountIdleTimelineGroup, mountIdleTimer, 1, isLeftHanded());
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, mountStartTimelineGroup, mounrStartTimer, 1, isLeftHanded());
        }
    }

    private void addPoseLayerDeath(){
        List<AnimationData.TimelineGroup> timelineGroups = List.of(getTimelineGroup("death_fall"), getTimelineGroup("death_fall"));
        addPoseLayerDeath(timelineGroups.get(getTimerIndex(DEATH_TIMER)), locatorListAll);
    }

    private void addPoseLayerLook(){
        locatorHead.xRot = this.headXRot;
        locatorHead.yRot = this.headYRot;

        float lookWeight =
                (1 - getAnimationTimerEasedQuad(CLIMBING_DOWN_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(CLIMBING_UP_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT));

        float lookHorizontalTimer = 1 - getLookLeftRightTimer();
        float lookVerticalTimer = 1 - getLookUpDownTimer();
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, getTimelineGroup("look_horizontal"), lookHorizontalTimer, lookWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, getTimelineGroup("look_vertical"), lookVerticalTimer, lookWeight, false);
    }

    private void addPoseLayerWalk(){
        AnimationData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup("walk_normal");
        AnimationData.TimelineGroup walkCrouchTimelineGroup = getTimelineGroup("walk_crouch");
        AnimationData.TimelineGroup walkTrudgeTimelineGroup = getTimelineGroup("walk_trudge");

        float walkNormalTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION))
                .speedUp(1.4F)
                .repeat(walkNormalTimelineGroup)
                .getValue();
        float walkCrouchTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION))
                .speedUp(2.5F)
                .repeat(walkCrouchTimelineGroup)
                .getValue();
        float walkTrudgeTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION))
                .speedUp(2.25F)
                .repeat(walkCrouchTimelineGroup)
                .getValue();
        //walkNormalTimer = Mth.lerp(getAnimationTimerEasedSine(DIRECTION_SHIFT), walkNormalTimer, 1 - walkNormalTimer);
        //walkCrouchTimer = Mth.lerp(getAnimationTimerEasedSine(DIRECTION_SHIFT), walkCrouchTimer, 1 - walkCrouchTimer);

        float walkNormalWeight = (1 - getAnimationTimerEasedQuad(SPRINT_WEIGHT))
                * Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.86F, 1)
                * (1 - getAnimationTimerEasedQuad(JUMP_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(CROUCH_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(TRUDGE_WEIGHT))
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));
        float walkCrouchWeight = Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.26F, 1)
                * (1 - getAnimationTimerEasedQuad(JUMP_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(TRUDGE_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * getAnimationTimerEasedQuad(CROUCH_WEIGHT)
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));

        float walkTrudgeWeight = Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.1F, 1)
                * (1 - getAnimationTimerEasedQuad(JUMP_WEIGHT))
                * getAnimationTimerEasedQuad(ON_GROUND_WEIGHT)
                * getAnimationTimerEasedQuad(TRUDGE_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));

        List<Locator> legLocators = Arrays.asList(locatorLeftLeg, locatorRightLeg);
        List<Locator> nonReversibleWalkLocators = Arrays.asList(locatorBody, locatorHead, locatorCloak, locatorLeftArm, locatorRightArm, locatorLeftHand, locatorRightHand);

        // Non reversible stuff
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocators, walkNormalTimelineGroup, walkNormalTimer, walkNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocators, walkCrouchTimelineGroup, walkCrouchTimer, walkCrouchWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocators, walkTrudgeTimelineGroup, walkTrudgeTimer, walkTrudgeWeight, false);

        // Legs
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkNormalTimelineGroup, walkNormalTimer, walkNormalWeight * (1 - getAnimationTimerEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkCrouchTimelineGroup, walkCrouchTimer, walkCrouchWeight * (1 - getAnimationTimerEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkTrudgeTimelineGroup, walkTrudgeTimer, walkTrudgeWeight * (1 - getAnimationTimerEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkNormalTimelineGroup, 1 - walkNormalTimer, walkNormalWeight * getAnimationTimerEasedQuad(DIRECTION_SHIFT), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkCrouchTimelineGroup, 1 - walkCrouchTimer, walkCrouchWeight * getAnimationTimerEasedQuad(DIRECTION_SHIFT), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkTrudgeTimelineGroup, 1 - walkTrudgeTimer, walkTrudgeWeight * getAnimationTimerEasedQuad(DIRECTION_SHIFT), false);
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
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(IN_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(TRUDGE_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(CROUCH_WEIGHT))
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, sprintNormalTimelineGroup, sprintNormalTimer, sprintNormalWeight, false);
    }

    private void addPoseLayerJump(){
        AnimationData.TimelineGroup sprintJumpTimelineGroup = getTimelineGroup("sprint_jump");
        AnimationData.TimelineGroup walkJumpTimelineGroup = getTimelineGroup("walk_jump");

        float jumpWeight = getAnimationTimerEasedQuad(JUMP_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(CLIMBING_UP_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(IN_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(CLIMBING_DOWN_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(FALL_WEIGHT));
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
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(IN_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(CLIMBING_DOWN_WEIGHT));
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
                * (1 - getAnimationTimerEasedQuad(IN_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(ON_GROUND_WEIGHT));
        float climbDownWeight = getAnimationTimerEasedQuad(CLIMBING_DOWN_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(IN_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
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

    private void addPoseLayerCrawling(){
        AnimationData.TimelineGroup crawlMasterTimelineGroup = getTimelineGroup("crawl_master");
        AnimationData.TimelineGroup crawlTimelineGroup = getTimelineGroup("crawl");

        float visualCrawlTimer = getAnimationTimer(VISUAL_SWIMMING_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT));


        float crawlWeight = Easing.CubicBezier.bezierInOutSine().ease(getAnimationTimer(VISUAL_SWIMMING_WEIGHT) * 4 - 3)
                * (1 - getAnimationTimerEasedQuad(IN_WATER_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT));
        float crawlForwardWeight = Mth.lerp(getAnimationTimerEasedQuad(DIRECTION_SHIFT), crawlWeight, 0);
        float crawlBackwardsWeight = Mth.lerp(getAnimationTimerEasedQuad(DIRECTION_SHIFT), 0, crawlWeight);

        float crawlTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION))
                .speedUp(5)
                .repeat(crawlTimelineGroup)
                .getValue();
        float crawlBackwardsTimer = 1 - crawlTimer;

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListMaster, crawlMasterTimelineGroup, visualCrawlTimer, 1, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, crawlTimelineGroup, crawlTimer, crawlForwardWeight, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, crawlTimelineGroup, crawlBackwardsTimer, crawlBackwardsWeight, isLeftHanded());
    }

    private void addPoseLayerSwimming(){
        AnimationData.TimelineGroup swimIdleTimelineGroup = getTimelineGroup("swim_idle");
        AnimationData.TimelineGroup swimIdleForwardTimelineGroup = getTimelineGroup("swim_idle_forward");
        AnimationData.TimelineGroup swimIdleBackwardsTimelineGroup = getTimelineGroup("swim_idle_backwards");

        float swimUpWeight =
                (1 - getAnimationTimerEasedQuad(ON_GROUND_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.25F, 1))
                * getAnimationTimerEasedQuad(IN_WATER_WEIGHT)
                * (Math.min(1, getAnimationTimerEasedQuad(MOVING_UP_WEIGHT) + (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))));

        float swimUpMoveWeight =
                (1 - getAnimationTimerEasedQuad(ON_GROUND_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * getAnimationTimerEasedQuad(IN_WATER_WEIGHT)
                * Math.min(getAnimationTimer(ANIMATION_SPEED) * 4, 1)
                * (Math.min(1, getAnimationTimerEasedQuad(MOVING_UP_WEIGHT) + (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))));

        float swimIdleWeight =
                (1 - getAnimationTimerEasedQuad(ON_GROUND_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.25F, 1))
                * getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT);

        float swimIdleMoveWeight =
                (1 - getAnimationTimerEasedQuad(ON_GROUND_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * Math.min(getAnimationTimer(ANIMATION_SPEED) * 4, 1)
                * getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT);


        float swimIdleForwardWeight = Mth.lerp(getAnimationTimerEasedQuad(DIRECTION_SHIFT), swimIdleMoveWeight, 0);
        float swimIdleBackwardsWeight = Mth.lerp(getAnimationTimerEasedQuad(DIRECTION_SHIFT), 0, swimIdleMoveWeight);

        float swimIdleUpForwardWeight = Mth.lerp(getAnimationTimerEasedQuad(DIRECTION_SHIFT), swimUpMoveWeight, 0);
        float swimIdleUpBackwardsWeight = Mth.lerp(getAnimationTimerEasedQuad(DIRECTION_SHIFT), 0, swimUpMoveWeight);

        float swimIdleSlowTimer = new TimerProcessor(this.tickAtFrame)
                .repeat(swimIdleTimelineGroup)
                .getValue();

        float swimIdleUpTimer = new TimerProcessor(this.tickAtFrame)
                .speedUp(1.5F)
                .repeat(swimIdleTimelineGroup)
                .getValue();

        List<Locator> locatorListNoArms = Arrays.asList(locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak);
        List<Locator> locatorListArms = List.of(locatorLeftArm, locatorLeftHand, locatorRightArm, locatorRightHand);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, swimIdleTimelineGroup, swimIdleSlowTimer, swimIdleWeight * (1 - swimUpWeight), false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, swimIdleForwardTimelineGroup, swimIdleSlowTimer, swimIdleForwardWeight * (1 - swimUpWeight), false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, swimIdleBackwardsTimelineGroup, swimIdleSlowTimer, swimIdleBackwardsWeight * (1 - swimUpWeight), false);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListNoArms, swimIdleTimelineGroup, swimIdleUpTimer, swimUpWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListNoArms, swimIdleForwardTimelineGroup, swimIdleUpTimer, swimIdleUpForwardWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListNoArms, swimIdleBackwardsTimelineGroup, swimIdleUpTimer, swimIdleUpBackwardsWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListArms, swimIdleTimelineGroup, swimIdleUpTimer, swimUpWeight * 0.25F, swimUpWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListArms, swimIdleForwardTimelineGroup, swimIdleUpTimer, swimIdleUpForwardWeight * 0.25F, swimUpWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListArms, swimIdleBackwardsTimelineGroup, swimIdleUpTimer, swimIdleUpBackwardsWeight * 0.25F, swimUpWeight, false);

        AnimationData.TimelineGroup swimMasterTimelineGroup = getTimelineGroup("swim_master");
        AnimationData.TimelineGroup swimFastTimelineGroup = getTimelineGroup("swim_fast");

        float swimMasterWeight = getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT)
                * getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT);

        float swimMasterTimer = (float) Mth.clamp((Math.toRadians(Mth.lerp(this.tickProgress, livingEntity.xRotO, livingEntity.getXRot())) / Mth.PI) + 0.5F, 0, 1);
        float swimFastTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION_XYZ))
                .speedUp(1.5F)
                .repeat(swimFastTimelineGroup)
                .getValue();

        List<Locator> locatorListNoLeftArm = Arrays.asList(locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorRightHand);
        List<Locator> locatorListLeftArm = List.of(locatorLeftArm, locatorLeftHand);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListMaster, swimMasterTimelineGroup, swimMasterTimer, swimMasterWeight, false);

        //i hate gimbal lock
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListNoLeftArm, swimFastTimelineGroup, swimFastTimer, swimMasterWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListLeftArm, swimFastTimelineGroup, swimFastTimer, swimMasterWeight, true);
    }

    private void addPoseLayerIdle(){
        AnimationData.TimelineGroup idleNormalTimelineGroup = getTimelineGroup("idle_normal");
        AnimationData.TimelineGroup idleCrouchTimelineGroup = getTimelineGroup("idle_crouch");

        float idleNormalTimer = new TimerProcessor(this.tickAtFrame)
                .repeat(idleNormalTimelineGroup)
                .getValue();

        float idleWeight = (1 - Math.min(getAnimationTimer(ANIMATION_SPEED) / 0.2F, 1))
                * (getAnimationTimerEasedQuad(ON_GROUND_WEIGHT))
                * (1 - getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getAnimationTimer(ANIMATION_SPEED_Y));

        float idleNormalWeight = Mth.lerp(getAnimationTimerEasedQuad(CROUCH_WEIGHT), idleWeight, 0);
        float idleCrouchWeight = Mth.lerp(getAnimationTimerEasedQuad(CROUCH_WEIGHT), 0, idleWeight);

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
    public void adjustTimersInventory() {
    }

    @Override
    protected void animatePartsInventory() {
        addPoseLayerInventoryIdle();
    }

    private void addPoseLayerInventoryIdle(){
        AnimationData.TimelineGroup idleNormalTimelineGroup = getTimelineGroup("idle_normal");

        float idleNormalTimer = new TimerProcessor(this.tickAtFrame)
                .repeat(idleNormalTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleNormalTimelineGroup, idleNormalTimer, 1, false);
    }
}
