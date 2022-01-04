package gg.moonflower.animationoverhaul.animations;

import gg.moonflower.animationoverhaul.access.ModelAccess;
import gg.moonflower.animationoverhaul.util.animation.Locator;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import gg.moonflower.animationoverhaul.util.data.TimelineGroupData;
import gg.moonflower.animationoverhaul.util.time.Easing;
import gg.moonflower.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.List;

public class PlayerAnimator extends LivingEntityAnimator<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private static final EntityAnimationData.DataKey<Float> SPRINT_WEIGHT = new EntityAnimationData.DataKey<>("sprint_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> CROUCH_WEIGHT = new EntityAnimationData.DataKey<>("crouch_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> DIRECTION_SHIFT = new EntityAnimationData.DataKey<>("direction_shift", 0F);
    private static final EntityAnimationData.DataKey<Float> TICKS_AFTER_HITTING_GROUND = new EntityAnimationData.DataKey<>("ticks_after_hitting_ground", 0F);
    private static final EntityAnimationData.DataKey<Float> JUMP_WEIGHT = new EntityAnimationData.DataKey<>("jump_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> JUMP_TIMER = new EntityAnimationData.DataKey<>("jump_timer", 0F);
    private static final EntityAnimationData.DataKey<Float> JUMP_REVERSER = new EntityAnimationData.DataKey<>("jump_reverser", 0F);
    private static final EntityAnimationData.DataKey<Float> TICKS_AFTER_SWITCHING_LEGS = new EntityAnimationData.DataKey<>("ticks_after_switching_legs", 0F);
    private static final EntityAnimationData.DataKey<Float> FALL_WEIGHT = new EntityAnimationData.DataKey<>("fall_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> FAST_FALL_WEIGHT = new EntityAnimationData.DataKey<>("fast_fall_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> ON_GROUND_WEIGHT = new EntityAnimationData.DataKey<>("on_ground_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> CLIMBING_UP_WEIGHT = new EntityAnimationData.DataKey<>("climbing_up_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> CLIMBING_DOWN_WEIGHT = new EntityAnimationData.DataKey<>("climbing_down_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> VISUAL_SWIMMING_WEIGHT = new EntityAnimationData.DataKey<>("visual_swimming_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> IN_WATER_WEIGHT = new EntityAnimationData.DataKey<>("in_water_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> UNDER_WATER_WEIGHT = new EntityAnimationData.DataKey<>("under_water_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> MOVING_UP_WEIGHT = new EntityAnimationData.DataKey<>("moving_up_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> TRUDGE_WEIGHT = new EntityAnimationData.DataKey<>("trudge_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> IS_PASSENGER_TIMER = new EntityAnimationData.DataKey<>("is_passenger_timer", 0F);
    private static final EntityAnimationData.DataKey<Float> FALL_FLYING_WEIGHT = new EntityAnimationData.DataKey<>("fall_flying_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> HOLD_NORMAL_MAIN_WEIGHT = new EntityAnimationData.DataKey<>("hold_normal_main_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> HOLD_NORMAL_OFF_WEIGHT = new EntityAnimationData.DataKey<>("hold_normal_off_weight", 0F);

    private static final EntityAnimationData.DataKey<Float> PUNCH_WEIGHT = new EntityAnimationData.DataKey<>("punch_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> PUNCH_TIMER = new EntityAnimationData.DataKey<>("punch_timer", 0F);

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

    private ModelPart cloakModelPart;

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

        this.cloakModelPart = ((ModelAccess)model).getModelPart("cloak");

        this.locatorRig.addLocator(locatorMaster);
        this.locatorRig.addLocatorModelPart(locatorHead, this.model.head);
        this.locatorRig.addLocatorModelPart(locatorBody, this.model.body);
        this.locatorRig.addLocatorModelPart(locatorLeftLeg, locatorRightLeg, this.model.leftLeg);
        this.locatorRig.addLocatorModelPart(locatorRightLeg, locatorLeftLeg, this.model.rightLeg);
        this.locatorRig.addLocatorModelPart(locatorLeftArm, locatorRightArm, this.model.leftArm);
        this.locatorRig.addLocatorModelPart(locatorRightArm, locatorLeftArm, this.model.rightArm);
        this.locatorRig.addLocatorModelPart(locatorCloak, this.cloakModelPart);
        this.locatorRig.addLocator(locatorLeftHand, locatorRightHand);
        this.locatorRig.addLocator(locatorRightHand, locatorLeftHand);
    }

    @Override
    public void adjustTimers(AbstractClientPlayer abstractClientPlayer) {
        super.adjustTimers(abstractClientPlayer);

        boolean isCrouching = abstractClientPlayer.isCrouching();
        boolean isSprinting = abstractClientPlayer.isSprinting();
        if(isCrouching && isSprinting){
            isSprinting = false;
        }
        incrementAnimationTimer(SPRINT_WEIGHT, isSprinting, 10, -10);
        incrementAnimationTimer(CROUCH_WEIGHT, isCrouching, 3, -3);

        // Legacy direction shift
        float moveAngleX = -Mth.sin(abstractClientPlayer.yBodyRotO * Mth.PI / 180);
        float moveAngleZ = Mth.cos(abstractClientPlayer.yBodyRotO * Mth.PI / 180);
        float deltaMovementX = (float) (abstractClientPlayer.getX() - abstractClientPlayer.xo) / getAnimationTimer(ANIMATION_SPEED);
        float deltaMovementZ = (float) (abstractClientPlayer.getZ() - abstractClientPlayer.zo) / getAnimationTimer(ANIMATION_SPEED);
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
        float ticksAfterHittingGround = abstractClientPlayer.isOnGround() ? previousTicksAfterHittingGround + this.delta : 0;
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
                (ticksAfterHittingGround < 1 || !abstractClientPlayer.isOnGround())
                && getAnimationTimer(DELTA_Y) != 0
                && ticksAfterSwitchingLegs < 15;
        incrementAnimationTimer(JUMP_WEIGHT, isJumping, 4, -4);

        // Switch the legs for sprint jumping
        float previousSprintJumpReverser = getAnimationTimer(JUMP_REVERSER);
        setAnimationTimer(JUMP_REVERSER, shouldResetJumpTimer ? 1 - previousSprintJumpReverser : previousSprintJumpReverser);

        // End sprint jump timers

        // Falling weight
        incrementAnimationTimer(FALL_WEIGHT, abstractClientPlayer.fallDistance > 0, 20, -4);
        incrementAnimationTimer(FAST_FALL_WEIGHT, abstractClientPlayer.fallDistance > 6, 24, -2);

        incrementAnimationTimer(ON_GROUND_WEIGHT, abstractClientPlayer.isOnGround(), 6, -6);

        //setAnimationTimer("vertical_blocks_per_second", (float) (livingEntity.getY() - livingEntity.yo) * 20);
        incrementAnimationTimer(CLIMBING_UP_WEIGHT, abstractClientPlayer.onClimbable() && getAnimationTimer(DELTA_Y) >= 0, 8, -8);
        incrementAnimationTimer(CLIMBING_DOWN_WEIGHT, abstractClientPlayer.onClimbable() && getAnimationTimer(DELTA_Y) < 0, 8, -8);

        incrementAnimationTimer(VISUAL_SWIMMING_WEIGHT, abstractClientPlayer.isVisuallySwimming(), 16, -16);

        incrementAnimationTimer(IN_WATER_WEIGHT, abstractClientPlayer.isInWater(), 8, -8);
        incrementAnimationTimer(UNDER_WATER_WEIGHT, abstractClientPlayer.isUnderWater(), 8, -8);
        incrementAnimationTimer(MOVING_UP_WEIGHT, getAnimationTimer(DELTA_Y) > 0.12, 8, -8);

        incrementAnimationTimer(TRUDGE_WEIGHT, abstractClientPlayer.isInPowderSnow || abstractClientPlayer.getFeetBlockState().getBlock() == Blocks.SOUL_SAND ||  abstractClientPlayer.getFeetBlockState().getBlock() == Blocks.SLIME_BLOCK ||  abstractClientPlayer.getFeetBlockState().getBlock() == Blocks.HONEY_BLOCK, 8, -8);

        adjustHurtTimers(4);

        if(abstractClientPlayer.deathTime != 0 && getAnimationTimer(DEATH_TIMER) == 0){
            setAnimationTimer(DEATH_INDEX, 0);
            if(getAnimationTimer(FALL_WEIGHT) > 0){
                setAnimationTimer(DEATH_INDEX, 1);
            }
        }
        adjustDeathTimer();
        adjustSleepTimer();

        resetTimerOnCondition(IS_PASSENGER_TIMER, !abstractClientPlayer.isPassenger(), (int) TimerProcessor.framesToTicks(17));

        incrementAnimationTimer(FALL_FLYING_WEIGHT, abstractClientPlayer.isFallFlying(), 12, -12);

        resetTimerOnCondition(PUNCH_TIMER, abstractClientPlayer.attackAnim == 0 && (getAnimationTimer(PUNCH_TIMER) == 1 || getAnimationTimer(PUNCH_TIMER) == 0), 8);
        incrementAnimationTimer(PUNCH_WEIGHT, getAnimationTimer(PUNCH_TIMER) != 0, 3, -4);


        incrementAnimationTimer(HOLD_NORMAL_MAIN_WEIGHT, getArmPose(abstractClientPlayer, InteractionHand.MAIN_HAND) == HumanoidModel.ArmPose.ITEM, 10, -10);
        incrementAnimationTimer(HOLD_NORMAL_OFF_WEIGHT, getArmPose(abstractClientPlayer, InteractionHand.OFF_HAND) == HumanoidModel.ArmPose.ITEM, 10, -10);
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

        addPoseLayerTurn();
        addPoseLayerIdle();

        addPoseLayerMinecart();
        addPoseLayerBoat();
        addPoseLayerMount();

        // Item interactions
        addPoseLayerHold();

        addPoseLayerFallFlying();
        addPoseLayerSleep();
        addPoseLayerDeath();
        addPoseLayerHurt(List.of(getTimelineGroup("hurt_0"), getTimelineGroup("hurt_1"), getTimelineGroup("hurt_2"), getTimelineGroup("hurt_3")), locatorListAll);


        // done: walking, sprinting, jump, sprint jump, idle, crouch idle, climbing
        // scrapped: creative flying
        // TODO: fall flying, levitating, trident spinning

        // to be done later with respective entities: riding entities

        // Item interaction pose layers
        // holding items
        // firework elytra flying
        // pickaxe, shovel, axe
        // TODO: holding item, using bow, mining with pickaxe, using crossbow, using shield, using spyglass, eating and drinking, throwing trident, equipping armor
        // TODO: fishing rod reeling, mining with pickaxe, interacting with fist?
    }

    private void addPoseLayerHold(){
        TimelineGroupData.TimelineGroup holdNormalMainHandTimelineGroup = getTimelineGroup("hold_normal_mainhand");
        TimelineGroupData.TimelineGroup holdNormalOffHandTimelineGroup = getTimelineGroup("hold_normal_offhand");

        float holdNormalMainHandTimer = getAnimationTimer(HOLD_NORMAL_MAIN_WEIGHT);
        float holdNormalOffHandTimer = getAnimationTimer(HOLD_NORMAL_OFF_WEIGHT);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, holdNormalMainHandTimelineGroup, holdNormalMainHandTimer, 1, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, holdNormalOffHandTimelineGroup, holdNormalOffHandTimer, 1, isLeftHanded());
    }

    private void addPoseLayerTurn(){
        TimelineGroupData.TimelineGroup turnTimelineGroup = getTimelineGroup("turn_left");

        float turnLeftWeight = getAnimationTimerEasedQuad(TURNING_LEFT_WEIGHT);
        float turnRightWeight = getAnimationTimerEasedQuad(TURNING_RIGHT_WEIGHT);

        float turnLeftTimer = (getAnimationTimer(BODY_Y_ROT) % 90F) / 90F;
        turnLeftTimer = turnLeftTimer < 0 ? 1 + turnLeftTimer : turnLeftTimer;
        float turnRightTimer = 1 - turnLeftTimer;

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, turnTimelineGroup, turnRightTimer, turnLeftWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, turnTimelineGroup, turnLeftTimer, turnRightWeight, true);
    }

    private void addPoseLayerSleep(){
        TimelineGroupData.TimelineGroup sleepMasterTimelineGroup = getTimelineGroup("sleep_master");
        TimelineGroupData.TimelineGroup sleepStartTimelineGroup = getTimelineGroup("sleep_start");

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
            TimelineGroupData.TimelineGroup minecartIdleTimelineGroup = getTimelineGroup("minecart_master");
            TimelineGroupData.TimelineGroup minecartStartTimelineGroup = getTimelineGroup("minecart_start");

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
        TimelineGroupData.TimelineGroup boatIdleTimelineGroup = getTimelineGroup("minecart_master");
        TimelineGroupData.TimelineGroup boatStartTimelineGroup = getTimelineGroup("minecart_start");
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
        TimelineGroupData.TimelineGroup mountIdleTimelineGroup = getTimelineGroup("mount_master");
        TimelineGroupData.TimelineGroup mountStartTimelineGroup = getTimelineGroup("minecart_start");
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
        List<TimelineGroupData.TimelineGroup> timelineGroups = List.of(getTimelineGroup("death_fall"), getTimelineGroup("death_fall"));
        addPoseLayerDeath(timelineGroups.get(getAnimationTimer(DEATH_INDEX)), locatorListAll);
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
        TimelineGroupData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup("walk_normal");
        TimelineGroupData.TimelineGroup walkCrouchTimelineGroup = getTimelineGroup("walk_crouch");
        TimelineGroupData.TimelineGroup walkTrudgeTimelineGroup = getTimelineGroup("walk_trudge");

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
        List<Locator> nonReversibleWalkLocatorsNoArms = Arrays.asList(locatorBody, locatorHead, locatorCloak);

        // Non reversible stuff
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsNoArms, walkNormalTimelineGroup, walkNormalTimer, walkNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsNoArms, walkCrouchTimelineGroup, walkCrouchTimer, walkCrouchWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsNoArms, walkTrudgeTimelineGroup, walkTrudgeTimer, walkTrudgeWeight, false);

        // Legs
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkNormalTimelineGroup, walkNormalTimer, walkNormalWeight * (1 - getAnimationTimerEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkCrouchTimelineGroup, walkCrouchTimer, walkCrouchWeight * (1 - getAnimationTimerEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkTrudgeTimelineGroup, walkTrudgeTimer, walkTrudgeWeight * (1 - getAnimationTimerEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkNormalTimelineGroup, 1 - walkNormalTimer, walkNormalWeight * getAnimationTimerEasedQuad(DIRECTION_SHIFT), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkCrouchTimelineGroup, 1 - walkCrouchTimer, walkCrouchWeight * getAnimationTimerEasedQuad(DIRECTION_SHIFT), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkTrudgeTimelineGroup, 1 - walkTrudgeTimer, walkTrudgeWeight * getAnimationTimerEasedQuad(DIRECTION_SHIFT), false);

        // Non reversible stuff (Arms)

        List<Locator> nonReversibleWalkLocatorsRightArm = Arrays.asList(locatorRightArm, locatorRightHand);
        List<Locator> nonReversibleWalkLocatorsLeftArm = Arrays.asList(locatorLeftArm, locatorLeftHand);

        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsRightArm, walkNormalTimelineGroup, walkNormalTimer, rightArmCancelWeight() * walkNormalWeight, walkNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsRightArm, walkCrouchTimelineGroup, walkCrouchTimer, rightArmCancelWeight() * walkCrouchWeight, walkCrouchWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsRightArm, walkTrudgeTimelineGroup, walkTrudgeTimer, rightArmCancelWeight() * walkTrudgeWeight, walkTrudgeWeight, false);

        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsLeftArm, walkNormalTimelineGroup, walkNormalTimer, leftArmCancelWeight() * walkNormalWeight, walkNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsLeftArm, walkCrouchTimelineGroup, walkCrouchTimer, leftArmCancelWeight() * walkCrouchWeight, walkCrouchWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsLeftArm, walkTrudgeTimelineGroup, walkTrudgeTimer, leftArmCancelWeight() * walkTrudgeWeight, walkTrudgeWeight, false);
    }

    private float leftArmCancelWeight(){
        return (1 - (getAnimationTimerEasedQuad(PUNCH_WEIGHT) * (isLeftHanded() ? 1 : 0)));
    }

    private float rightArmCancelWeight(){
        return (1 - (getAnimationTimerEasedQuad(PUNCH_WEIGHT) * (isLeftHanded() ? 0 : 1)));
    }

    private void addPoseLayerSprint(){
        // Referencing the walk's timeline length so that the walk and sprint cycles sync properly
        TimelineGroupData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup("walk_normal");
        TimelineGroupData.TimelineGroup sprintNormalTimelineGroup = getTimelineGroup("sprint_normal");

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


        List<Locator> locatorsRightArm = List.of(locatorRightArm, locatorRightHand);
        List<Locator> locatorsLeftArm = List.of(locatorLeftArm, locatorLeftHand);
        List<Locator> locatorsNoArms = List.of(locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorsNoArms, sprintNormalTimelineGroup, sprintNormalTimer, sprintNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorsLeftArm, sprintNormalTimelineGroup, sprintNormalTimer, sprintNormalWeight * leftArmCancelWeight(), sprintNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorsRightArm, sprintNormalTimelineGroup, sprintNormalTimer, sprintNormalWeight * rightArmCancelWeight(), sprintNormalWeight, false);
    }

    private void addPoseLayerJump(){
        TimelineGroupData.TimelineGroup sprintJumpTimelineGroup = getTimelineGroup("sprint_jump");
        TimelineGroupData.TimelineGroup walkJumpTimelineGroup = getTimelineGroup("walk_jump");

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
        TimelineGroupData.TimelineGroup fallSlowTimelineGroup = getTimelineGroup("fall_short");
        TimelineGroupData.TimelineGroup fallFastTimelineGroup = getTimelineGroup("fall_fast");

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
        TimelineGroupData.TimelineGroup climbUpTimelineGroup = getTimelineGroup("climb_up");
        TimelineGroupData.TimelineGroup climbDownTimelineGroup = getTimelineGroup("climb_down");

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
        TimelineGroupData.TimelineGroup crawlMasterTimelineGroup = getTimelineGroup("crawl_master");
        TimelineGroupData.TimelineGroup crawlTimelineGroup = getTimelineGroup("crawl");

        float visualCrawlTimer = getAnimationTimer(VISUAL_SWIMMING_WEIGHT)
                * (1 - getAnimationTimerEasedQuad(UNDER_WATER_WEIGHT))
                * getAnimationTimerEasedQuad(ON_GROUND_WEIGHT);


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
        TimelineGroupData.TimelineGroup swimIdleTimelineGroup = getTimelineGroup("swim_idle");
        TimelineGroupData.TimelineGroup swimIdleForwardTimelineGroup = getTimelineGroup("swim_idle_forward");
        TimelineGroupData.TimelineGroup swimIdleBackwardsTimelineGroup = getTimelineGroup("swim_idle_backwards");

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
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListArms, swimIdleForwardTimelineGroup, swimIdleUpTimer, swimIdleUpForwardWeight * 0.25F, swimIdleUpForwardWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListArms, swimIdleBackwardsTimelineGroup, swimIdleUpTimer, swimIdleUpBackwardsWeight * 0.25F, swimIdleUpBackwardsWeight, false);

        TimelineGroupData.TimelineGroup swimMasterTimelineGroup = getTimelineGroup("swim_master");
        TimelineGroupData.TimelineGroup swimFastTimelineGroup = getTimelineGroup("swim_fast");

        float swimMasterWeight = getAnimationTimerEasedQuad(VISUAL_SWIMMING_WEIGHT)
                * getAnimationTimerEasedQuad(IN_WATER_WEIGHT);

        float swimMasterTimer = (float) Mth.clamp((Math.toRadians(Mth.lerp(this.tickProgress, livingEntity.xRotO, livingEntity.getXRot())) / Mth.PI) + 0.5F, 0, 1);
        float swimFastTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION_XYZ))
                .speedUp(1.5F)
                .repeat(swimFastTimelineGroup)
                .getValue();

        List<Locator> locatorListNoLeftArm = Arrays.asList(locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorRightHand);
        List<Locator> locatorListLeftArm = List.of(locatorLeftArm, locatorLeftHand);

        this.locatorRig.weightedClearTransforms(locatorListMaster, swimMasterWeight);
        this.locatorRig.animateMultipleLocatorsAdditive(List.of(locatorMaster), swimMasterTimelineGroup, swimMasterTimer, swimMasterWeight, false);

        //i hate gimbal lock
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListNoLeftArm, swimFastTimelineGroup, swimFastTimer, swimMasterWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListLeftArm, swimFastTimelineGroup, swimFastTimer, swimMasterWeight, true);
    }

    private void addPoseLayerFallFlying(){
        TimelineGroupData.TimelineGroup fallFlyingMasterTimelineGroup = getTimelineGroup("swim_master");
        TimelineGroupData.TimelineGroup fallFlyingFastTimelineGroup = getTimelineGroup("elytra_fast");
        TimelineGroupData.TimelineGroup fallFlyingSlowTimelineGroup = getTimelineGroup("fall_fast");

        float fallFlyingMasterWeight = getAnimationTimerEasedQuad(FALL_FLYING_WEIGHT);
        float fallFlyingFastWeight = getAnimationTimer(FLYING_SPEED);

        float fallFlyingMasterTimer = (float) Mth.clamp((Math.toRadians(Mth.lerp(this.tickProgress, livingEntity.xRotO, livingEntity.getXRot())) / Mth.PI) + 0.5F, 0, 1);
        float fallFlyingSlowTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION_XYZ))
                .speedUp(1.5F)
                .repeat(fallFlyingSlowTimelineGroup)
                .getValue();
        float fallFlyingFastTimer = new TimerProcessor(getAnimationTimer(ANIMATION_POSITION_XYZ))
                .speedUp(1.5F)
                .repeat(fallFlyingFastTimelineGroup)
                .getValue();

        this.locatorRig.weightedClearTransforms(locatorListMaster, fallFlyingMasterWeight);
        this.locatorHead.xRot -= Mth.HALF_PI * fallFlyingMasterWeight;
        this.locatorMaster.yRot += this.headYRot * -fallFlyingMasterWeight * fallFlyingFastWeight;
        this.locatorRig.animateMultipleLocatorsAdditive(List.of(locatorMaster), fallFlyingMasterTimelineGroup, fallFlyingMasterTimer, fallFlyingMasterWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, fallFlyingSlowTimelineGroup, fallFlyingSlowTimer, (1 - fallFlyingFastWeight) * fallFlyingMasterWeight, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, fallFlyingFastTimelineGroup, fallFlyingFastTimer, (fallFlyingFastWeight) * fallFlyingMasterWeight, isLeftHanded());

    }

    private void addPoseLayerIdle(){
        TimelineGroupData.TimelineGroup idleNormalTimelineGroup = getTimelineGroup("idle_normal");
        TimelineGroupData.TimelineGroup idleCrouchTimelineGroup = getTimelineGroup("idle_crouch");

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
            locatorMaster.y -= 2;
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
        this.cloakModelPart.z += 2;
        this.cloakModelPart.yRot += Mth.PI;
        this.cloakModelPart.xRot = -this.cloakModelPart.xRot;

        this.model.hat.copyFrom(this.model.head);
        this.model.leftPants.copyFrom(this.model.leftLeg);
        this.model.rightPants.copyFrom(this.model.rightLeg);
        this.model.leftSleeve.copyFrom(this.model.leftArm);
        this.model.rightSleeve.copyFrom(this.model.rightArm);
        this.model.jacket.copyFrom(this.model.body);
    }

    @Override
    protected void animatePartsInventory() {
        addPoseLayerInventoryIdle();
        // Removes the vanilla transformation done for the crouch pose
        if(livingEntity.isCrouching()){
            locatorMaster.y -= 2;
        }
    }

    private void addPoseLayerInventoryIdle(){
        TimelineGroupData.TimelineGroup idleNormalTimelineGroup = getTimelineGroup("idle_normal");

        float idleNormalTimer = new TimerProcessor(this.tickAtFrame)
                .repeat(idleNormalTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleNormalTimelineGroup, idleNormalTimer, 1, false);
    }

    private static HumanoidModel.ArmPose getArmPose(AbstractClientPlayer abstractClientPlayer, InteractionHand interactionHand) {
        ItemStack itemStack = abstractClientPlayer.getItemInHand(interactionHand);
        if (itemStack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        }
        if (abstractClientPlayer.getUsedItemHand() == interactionHand && abstractClientPlayer.getUseItemRemainingTicks() > 0) {
            UseAnim useAnim = itemStack.getUseAnimation();
            if (useAnim == UseAnim.BLOCK) {
                return HumanoidModel.ArmPose.BLOCK;
            }
            if (useAnim == UseAnim.BOW) {
                return HumanoidModel.ArmPose.BOW_AND_ARROW;
            }
            if (useAnim == UseAnim.SPEAR) {
                return HumanoidModel.ArmPose.THROW_SPEAR;
            }
            if (useAnim == UseAnim.CROSSBOW && interactionHand == abstractClientPlayer.getUsedItemHand()) {
                return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            }
            if (useAnim == UseAnim.SPYGLASS) {
                return HumanoidModel.ArmPose.SPYGLASS;
            }
        } else if (!abstractClientPlayer.swinging && itemStack.is(Items.CROSSBOW) && CrossbowItem.isCharged(itemStack)) {
            return HumanoidModel.ArmPose.CROSSBOW_HOLD;
        }
        return HumanoidModel.ArmPose.ITEM;
    }
}
