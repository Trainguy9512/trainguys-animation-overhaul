package gg.moonflower.animationoverhaul.animations.entity;

import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import gg.moonflower.animationoverhaul.util.animation.Locator;
import gg.moonflower.animationoverhaul.util.animation.LocatorRig;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import gg.moonflower.animationoverhaul.util.data.TimelineGroupData;
import gg.moonflower.animationoverhaul.util.time.Easing;
import gg.moonflower.animationoverhaul.util.time.TimerProcessor;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PlayerPartAnimator extends LivingEntityPartAnimator<Player, PlayerModel<Player>> {

    private Locator locatorMaster;
    private Locator locatorHead;
    private Locator locatorBody;
    private Locator locatorLeftLeg;
    private Locator locatorRightLeg;
    private Locator locatorLeftArm;
    private Locator locatorRightArm;
    private Locator locatorCloak;
    private Locator locatorLeftHand;
    private Locator locatorRightHand;

    private List<Locator> locatorListAll;
    private List<Locator> locatorListNoHands;
    private List<Locator> locatorListMaster;


    private static final EntityAnimationData.DataKey<Float> SPRINT_WEIGHT = new EntityAnimationData.DataKey<>("sprint_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> CROUCH_WEIGHT = new EntityAnimationData.DataKey<>("crouch_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> DIRECTION_SHIFT = new EntityAnimationData.DataKey<>("direction_shift", 0F);
    private static final EntityAnimationData.DataKey<Float> TICKS_AFTER_HITTING_GROUND = new EntityAnimationData.DataKey<>("ticks_after_hitting_ground", 0F);
    private static final EntityAnimationData.DataKey<Float> JUMP_WEIGHT = new EntityAnimationData.DataKey<>("jump_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> JUMP_TIMER = new EntityAnimationData.DataKey<>("jump_timer", 0F);
    private static final EntityAnimationData.DataKey<Boolean> JUMP_REVERSER = new EntityAnimationData.DataKey<>("jump_reverser", false);
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

    private static final EntityAnimationData.DataKey<Float> ATTACK_TIMER = new EntityAnimationData.DataKey<>("attack_timer", 0F);
    private static final EntityAnimationData.DataKey<PlayerPartAnimator.AttackType> ATTACK_TYPE = new EntityAnimationData.DataKey<>("attack_type", PlayerPartAnimator.AttackType.PUNCH);

    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT = new EntityAnimationData.DataKey<>("attack_weight", 0F);
    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT_PUNCH = new EntityAnimationData.DataKey<>("attack_weight_punch", 0F);
    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT_OFFHAND = new EntityAnimationData.DataKey<>("attack_weight_offhand", 0F);
    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT_PICKAXE = new EntityAnimationData.DataKey<>("attack_weight_pickaxe", 0F);
    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT_SHOVEL = new EntityAnimationData.DataKey<>("attack_weight_shovel", 0F);
    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT_AXE = new EntityAnimationData.DataKey<>("attack_weight_axe", 0F);
    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT_HOE = new EntityAnimationData.DataKey<>("attack_weight_hoe", 0F);
    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT_SWORD = new EntityAnimationData.DataKey<>("attack_weight_sword", 0F);
    private static final EntityAnimationData.DataKey<Float> ATTACK_WEIGHT_PLACE = new EntityAnimationData.DataKey<>("attack_weight_place", 0F);


    public PlayerPartAnimator(){
        super();
    }

    @Override
    protected void buildRig(LocatorRig locatorRig) {
        super.buildRig(locatorRig);


        this.locatorMaster = new Locator("root");
        this.locatorHead = new Locator("head");
        this.locatorBody = new Locator("body");
        this.locatorLeftLeg = new Locator("leftLeg");
        this.locatorRightLeg = new Locator("rightLeg");
        this.locatorLeftArm = new Locator("leftArm");
        this.locatorRightArm = new Locator("rightArm");
        this.locatorCloak = new Locator("cloak");
        this.locatorLeftHand = new Locator("leftHand");
        this.locatorRightHand = new Locator("rightHand");

        this.locatorListAll = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorLeftHand, locatorRightHand);
        this.locatorListNoHands = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak);
        this.locatorListMaster = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorLeftHand, locatorRightHand, locatorMaster);

        locatorRig.addLocator(locatorMaster);
        locatorRig.addLocatorModelPart(locatorHead, "head");
        locatorRig.addLocatorModelPart(locatorBody, "body");
        locatorRig.addLocatorModelPart(locatorLeftLeg, locatorRightLeg, "left_leg", PartPose.offset(1.9f, 12.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorRightLeg, locatorLeftLeg, "right_leg", PartPose.offset(-1.9f, 12.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorLeftArm, locatorRightArm, "left_arm", PartPose.offset(5.0f, 2.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorRightArm, locatorLeftArm, "right_arm", PartPose.offset(-5.0f, 2.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorCloak, "cloak", PartPose.offsetAndRotation(0, 0, 2, 0, Mth.PI, 0));
        locatorRig.addLocator(locatorLeftHand, locatorRightHand);
        locatorRig.addLocator(locatorRightHand, locatorLeftHand);
    }

    @Override
    public void tick(LivingEntity livingEntity, EntityAnimationData entityAnimationData) {
        tickGeneralMovementTimers(livingEntity, entityAnimationData);
        tickHeadTimers(livingEntity, entityAnimationData);

        boolean isCrouching = livingEntity.isCrouching();
        boolean isSprinting = livingEntity.isSprinting();
        if(isCrouching && isSprinting){
            isSprinting = false;
        }
        entityAnimationData.incrementInTicksFromCondition(SPRINT_WEIGHT, isSprinting, 10, 10);
        entityAnimationData.incrementInTicksFromCondition(CROUCH_WEIGHT, isCrouching, 8, 8);

        // Legacy direction shift
        float moveAngleX = -Mth.sin(livingEntity.yBodyRotO * Mth.PI / 180);
        float moveAngleZ = Mth.cos(livingEntity.yBodyRotO * Mth.PI / 180);
        float deltaMovementX = (float) (livingEntity.getX() - livingEntity.xo) / entityAnimationData.getValue(ANIMATION_SPEED);
        float deltaMovementZ = (float) (livingEntity.getZ() - livingEntity.zo) / entityAnimationData.getValue(ANIMATION_SPEED);
        boolean isMovingForwards = true;
        if(entityAnimationData.getValue(ANIMATION_SPEED) > 0.01){
            if(
                    (moveAngleX >= 0 && deltaMovementX < 0 - 0.1) ||
                            (moveAngleX <= 0 && deltaMovementX > 0 + 0.1) ||
                            (moveAngleZ >= 0 && deltaMovementZ < 0 - 0.1) ||
                            (moveAngleZ <= 0 && deltaMovementZ > 0 + 0.1)
            ){
                isMovingForwards = false;
            }
        }
        entityAnimationData.incrementInTicksFromCondition(DIRECTION_SHIFT, !isMovingForwards, 5, 5);

        // Begin sprint jump timers
        // Ticks after hitting ground
        float previousTicksAfterHittingGround = entityAnimationData.get(TICKS_AFTER_HITTING_GROUND).get();
        float ticksAfterHittingGround = livingEntity.isOnGround() ? previousTicksAfterHittingGround + 1 : 0;
        entityAnimationData.setValue(TICKS_AFTER_HITTING_GROUND, ticksAfterHittingGround);

        boolean shouldResetJumpTimer =
                ((entityAnimationData.getValue(DELTA_Y_OLD) <= 0 && entityAnimationData.getValue(DELTA_Y) > 0)
                        || (entityAnimationData.getValue(DELTA_Y_OLD) == 0 && entityAnimationData.getValue(DELTA_Y) > 0))
                        && entityAnimationData.getValue(TICKS_AFTER_SWITCHING_LEGS) > 4;

        entityAnimationData.incrementInTicksOrResetFromCondition(JUMP_TIMER, shouldResetJumpTimer, 12);
        if(shouldResetJumpTimer){
            entityAnimationData.incrementInTicksFromCondition(JUMP_TIMER, true, 12, 12);
        }

        // Ticks after switching legs
        float previousTicksAfterSwitchingLegs = entityAnimationData.getValue(TICKS_AFTER_SWITCHING_LEGS);
        float ticksAfterSwitchingLegs = shouldResetJumpTimer ? 0 : previousTicksAfterSwitchingLegs + 1;
        entityAnimationData.setValue(TICKS_AFTER_SWITCHING_LEGS, ticksAfterSwitchingLegs);

        // Sprint jump weight
        boolean isJumping =
                (ticksAfterHittingGround < 1 || !livingEntity.isOnGround())
                        && entityAnimationData.getValue(DELTA_Y) != 0
                        && ticksAfterSwitchingLegs < 15;
        entityAnimationData.incrementInTicksFromCondition(JUMP_WEIGHT, isJumping, 3, 4);

        // Switch the legs for sprint jumping
        boolean previousSprintJumpReverser = entityAnimationData.getValue(JUMP_REVERSER);
        entityAnimationData.setValue(JUMP_REVERSER, shouldResetJumpTimer ? !previousSprintJumpReverser : previousSprintJumpReverser);

        // End sprint jump timers

        // Falling weight
        entityAnimationData.incrementInTicksFromCondition(FALL_WEIGHT, livingEntity.fallDistance > 0, 20, 4);
        entityAnimationData.incrementInTicksFromCondition(FAST_FALL_WEIGHT, livingEntity.fallDistance > 6, 24, 2);

        entityAnimationData.incrementInTicksFromCondition(ON_GROUND_WEIGHT, livingEntity.isOnGround(), 6, 6);

        //setAnimationTimer("vertical_blocks_per_second", (float) (livingEntity.getY() - livingEntity.yo) * 20);
        entityAnimationData.incrementInTicksFromCondition(CLIMBING_UP_WEIGHT, livingEntity.onClimbable() && entityAnimationData.getValue(DELTA_Y) >= 0, 8, 8);
        entityAnimationData.incrementInTicksFromCondition(CLIMBING_DOWN_WEIGHT, livingEntity.onClimbable() && entityAnimationData.getValue(DELTA_Y) < 0, 8, 8);

        entityAnimationData.incrementInTicksFromCondition(VISUAL_SWIMMING_WEIGHT, livingEntity.isVisuallySwimming(), 16, 16);

        entityAnimationData.incrementInTicksFromCondition(IN_WATER_WEIGHT, livingEntity.isInWater(), 8, 8);
        entityAnimationData.incrementInTicksFromCondition(UNDER_WATER_WEIGHT, livingEntity.isUnderWater(), 8, 8);
        entityAnimationData.incrementInTicksFromCondition(MOVING_UP_WEIGHT, entityAnimationData.getValue(DELTA_Y) > 0.12, 8, 8);

        //entityAnimationData.incrementInTicksFromCondition(TRUDGE_WEIGHT, livingEntity.isInPowderSnow || livingEntity.getFeetBlockState().getBlock() == Blocks.SOUL_SAND ||  livingEntity.getFeetBlockState().getBlock() == Blocks.SLIME_BLOCK ||  livingEntity.getFeetBlockState().getBlock() == Blocks.HONEY_BLOCK, 8, 8);

        tickHurtTimers(livingEntity, entityAnimationData, 4);

        if(livingEntity.deathTime != 0 && entityAnimationData.getValue(DEATH_TIMER) == 0){
            entityAnimationData.setValue(DEATH_INDEX, 0);
            if(entityAnimationData.getValue(FALL_WEIGHT) > 0){
                entityAnimationData.setValue(DEATH_INDEX, 1);
            }
        }

        tickDeathTimer(livingEntity, entityAnimationData);
        tickSleepTimer(livingEntity, entityAnimationData);

        entityAnimationData.incrementInFramesOrResetFromCondition(IS_PASSENGER_TIMER, !livingEntity.isPassenger(), 17);

        entityAnimationData.incrementInTicksFromCondition(FALL_FLYING_WEIGHT, livingEntity.isFallFlying(), 12, 12);

        boolean shouldRepeatAttack = (livingEntity.getAttackAnim(this.partialTicks) != 0 && entityAnimationData.getValue(ATTACK_TIMER) == 1);
        boolean shouldResetAttack = (livingEntity.getAttackAnim(this.partialTicks) == 0 || entityAnimationData.getValue(ATTACK_TIMER) == 1) && (entityAnimationData.getValue(ATTACK_TIMER) == 0 || entityAnimationData.getValue(ATTACK_TIMER) == 1);
        entityAnimationData.incrementInTicksOrResetFromCondition(ATTACK_TIMER, shouldResetAttack, 6);
        if(shouldRepeatAttack){
            entityAnimationData.setValue(ATTACK_TIMER, 0.001F);
        }
        if(entityAnimationData.getValue(ATTACK_TIMER) <= 0.001F){
            PlayerPartAnimator.AttackType attackType;
            if(livingEntity.swingingArm == InteractionHand.MAIN_HAND){
                Item mainHandItem = livingEntity.getMainHandItem().getItem();
                if(mainHandItem instanceof PickaxeItem){
                    attackType = PlayerPartAnimator.AttackType.PICKAXE;
                } else if(mainHandItem instanceof AxeItem){
                    attackType = PlayerPartAnimator.AttackType.AXE;
                } else if(mainHandItem instanceof ShovelItem){
                    attackType = PlayerPartAnimator.AttackType.SHOVEL;
                } else if(mainHandItem instanceof HoeItem){
                    attackType = PlayerPartAnimator.AttackType.HOE;
                } else if(mainHandItem instanceof SwordItem){
                    attackType = PlayerPartAnimator.AttackType.SWORD;
                } else if(mainHandItem instanceof BlockItem){
                    attackType = PlayerPartAnimator.AttackType.PLACE;
                } else {
                    attackType = PlayerPartAnimator.AttackType.PUNCH;
                }
            } else {
                attackType = PlayerPartAnimator.AttackType.OFFHAND;
            }
            entityAnimationData.setValue(ATTACK_TYPE, attackType);
        }

        boolean isAttacking = entityAnimationData.getValue(ATTACK_TIMER) != 0 || livingEntity.attackAnim != 0;
        PlayerPartAnimator.AttackType attackType = entityAnimationData.getValue(ATTACK_TYPE);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT, isAttacking, 3, 3);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT_PUNCH, isAttacking && attackType == PlayerPartAnimator.AttackType.PUNCH, 3, 6);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT_OFFHAND, isAttacking && attackType == PlayerPartAnimator.AttackType.OFFHAND, 3, 6);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT_PICKAXE, isAttacking && attackType == PlayerPartAnimator.AttackType.PICKAXE, 3, 6);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT_SHOVEL, isAttacking && attackType == PlayerPartAnimator.AttackType.SHOVEL, 3, 6);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT_AXE, isAttacking && attackType == PlayerPartAnimator.AttackType.AXE, 3, 6);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT_HOE, isAttacking && attackType == PlayerPartAnimator.AttackType.HOE, 3, 6);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT_SWORD, isAttacking && attackType == PlayerPartAnimator.AttackType.SWORD, 3, 6);
        entityAnimationData.incrementInTicksFromCondition(ATTACK_WEIGHT_PLACE, isAttacking && attackType == PlayerPartAnimator.AttackType.PLACE, 3, 6);

        entityAnimationData.incrementInTicksFromCondition(HOLD_NORMAL_MAIN_WEIGHT, getArmPose(livingEntity, InteractionHand.MAIN_HAND) == HumanoidModel.ArmPose.ITEM, 10, 10);
        entityAnimationData.incrementInTicksFromCondition(HOLD_NORMAL_OFF_WEIGHT, getArmPose(livingEntity, InteractionHand.OFF_HAND) == HumanoidModel.ArmPose.ITEM, 10, 10);

    }

    @Override
    protected void poseLocatorRig() {
        super.poseLocatorRig();
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
        addPoseLayerAttack();

        addPoseLayerFallFlying();
        addPoseLayerSleep();
        addPoseLayerDeath();
        addPoseLayerHurt(List.of(
                getTimelineGroup(AnimationOverhaulMain.MOD_ID, "hurt_0"),
                getTimelineGroup(AnimationOverhaulMain.MOD_ID, "hurt_1"),
                getTimelineGroup(AnimationOverhaulMain.MOD_ID, "hurt_2"),
                getTimelineGroup(AnimationOverhaulMain.MOD_ID, "hurt_3")
        ), locatorListAll);


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
        TimelineGroupData.TimelineGroup holdNormalMainHandTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "hold_normal_mainhand");
        TimelineGroupData.TimelineGroup holdNormalOffHandTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "hold_normal_offhand");

        float holdNormalMainHandTimer = getDataValueLerped(HOLD_NORMAL_MAIN_WEIGHT) * (1 - getDataValueEasedQuad(ATTACK_WEIGHT));
        float holdNormalOffHandTimer = getDataValueLerped(HOLD_NORMAL_OFF_WEIGHT) * (1 - getDataValueEasedQuad(ATTACK_WEIGHT));

        float holdNormalMainHandWeight = 1 - getDataValueEasedQuad(ATTACK_WEIGHT);
        float holdNormalOffHandWeight = 1 - getDataValueEasedQuad(ATTACK_WEIGHT_OFFHAND);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, holdNormalMainHandTimelineGroup, holdNormalMainHandTimer, holdNormalMainHandWeight, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, holdNormalOffHandTimelineGroup, holdNormalOffHandTimer, holdNormalOffHandWeight, isLeftHanded());
    }

    private void addPoseLayerAttack(){
        Map<EntityAnimationData.DataKey<Float>, TimelineGroupData.TimelineGroup> attackTimelineGroups = Map.of(
                ATTACK_WEIGHT_PUNCH, getTimelineGroup(AnimationOverhaulMain.MOD_ID, "attack_punch"),
                ATTACK_WEIGHT_PICKAXE, getTimelineGroup(AnimationOverhaulMain.MOD_ID, "attack_pickaxe")
        );
        float attackTimer = getDataValueLerped(ATTACK_TIMER);
        boolean isAttacking = getDataValueLerped(ATTACK_TIMER) != 0 || livingEntity.attackAnim != 0;
        for(EntityAnimationData.DataKey<Float> dataKey : attackTimelineGroups.keySet()){
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, attackTimelineGroups.get(dataKey), attackTimer, getDataValueEasedCondition(dataKey, Easing.CubicBezier.bezierOutQuart(), Easing.CubicBezier.bezierInQuart(), isAttacking), isLeftHanded());
        }
    }

    private void addPoseLayerTurn(){
        TimelineGroupData.TimelineGroup turnTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "turn_left");

        float turnLeftWeight = getDataValueEasedQuad(TURNING_LEFT_WEIGHT);
        float turnRightWeight = getDataValueEasedQuad(TURNING_RIGHT_WEIGHT);

        float turnLeftTimer = (getDataValueLerped(BODY_Y_ROT) % 90F) / 90F;
        turnLeftTimer = turnLeftTimer < 0 ? 1 + turnLeftTimer : turnLeftTimer;
        float turnRightTimer = 1 - turnLeftTimer;

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, turnTimelineGroup, turnRightTimer, turnLeftWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, turnTimelineGroup, turnLeftTimer, turnRightWeight, true);
    }

    private void addPoseLayerSleep(){
        TimelineGroupData.TimelineGroup sleepMasterTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "sleep_master");
        TimelineGroupData.TimelineGroup sleepStartTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "sleep_start");

        float sleepIdleTimer = new TimerProcessor(this.livingEntity.tickCount + this.partialTicks)
                .speedUp(1)
                .repeat(sleepMasterTimelineGroup)
                .getValue();
        float sleepStartTimer = getDataValueLerped(SLEEP_TIMER);

        float sleepWeight = livingEntity.getPose() == Pose.SLEEPING ? 1 : 0;

        this.locatorRig.weightedClearTransforms(locatorListMaster, sleepWeight);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListMaster, sleepMasterTimelineGroup, sleepIdleTimer, sleepWeight);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, sleepStartTimelineGroup, sleepStartTimer, sleepWeight);
    }

    private void addPoseLayerMinecart(){
        if(livingEntity.isPassenger() && livingEntity.getVehicle() instanceof AbstractMinecart){
            TimelineGroupData.TimelineGroup minecartIdleTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "minecart_master");
            TimelineGroupData.TimelineGroup minecartStartTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "minecart_start");

            float minecartIdleTimer = new TimerProcessor(this.livingEntity.tickCount + this.partialTicks)
                    .repeat(minecartIdleTimelineGroup)
                    .getValue();
            float minecartStartTimer = getDataValueLerped(IS_PASSENGER_TIMER);
            float lookVerticalTimer = 1 - getLookUpDownTimer();

            this.locatorRig.weightedClearTransforms(locatorListMaster, 1);

            locatorHead.xRot = getDataValueLerped(HEAD_X_ROT);
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, getTimelineGroup(AnimationOverhaulMain.MOD_ID, "look_vertical"), lookVerticalTimer, 1, false);
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, minecartIdleTimelineGroup, minecartIdleTimer, 1, isLeftHanded());
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, minecartStartTimelineGroup, minecartStartTimer, 1, isLeftHanded());
        }
    }

    private void addPoseLayerBoat(){
        TimelineGroupData.TimelineGroup boatIdleTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "minecart_master");
        TimelineGroupData.TimelineGroup boatStartTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "minecart_start");
        if(livingEntity.isPassenger() && livingEntity.getVehicle() instanceof Boat){

            float boatIdleTimer = new TimerProcessor(this.livingEntity.tickCount + this.partialTicks)
                    .repeat(boatIdleTimelineGroup)
                    .getValue();
            float boatStartTimer = getDataValueLerped(IS_PASSENGER_TIMER);

            this.locatorRig.weightedClearTransforms(locatorListMaster, 1);
            addPoseLayerLook();
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, boatIdleTimelineGroup, boatIdleTimer, 1, isLeftHanded());
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, boatStartTimelineGroup, boatStartTimer, 1, isLeftHanded());
        }
    }

    private void addPoseLayerMount(){
        TimelineGroupData.TimelineGroup mountIdleTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "mount_master");
        TimelineGroupData.TimelineGroup mountStartTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "minecart_start");
        if(livingEntity.isPassenger() && livingEntity.getVehicle() instanceof LivingEntity){

            float mountIdleTimer = new TimerProcessor(this.livingEntity.tickCount + this.partialTicks)
                    .repeat(mountIdleTimelineGroup)
                    .getValue();
            float mountStartTimer = getDataValueLerped(IS_PASSENGER_TIMER);

            this.locatorRig.weightedClearTransforms(locatorListMaster, 1);
            addPoseLayerLook();
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, mountIdleTimelineGroup, mountIdleTimer, 1, isLeftHanded());
            this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, mountStartTimelineGroup, mountStartTimer, 1, isLeftHanded());
        }
    }

    private void addPoseLayerDeath(){
        List<TimelineGroupData.TimelineGroup> timelineGroups = List.of(getTimelineGroup(AnimationOverhaulMain.MOD_ID, "death_normal"), getTimelineGroup(AnimationOverhaulMain.MOD_ID, "death_fall"));
        addPoseLayerDeath(timelineGroups.get(getDataValue(DEATH_INDEX)), locatorListAll);
    }

    private void addPoseLayerLook(){
        locatorHead.xRot = getDataValueLerped(HEAD_X_ROT);
        locatorHead.yRot = getDataValueLerped(HEAD_Y_ROT);

        float lookWeight =
                (1 - getDataValueEasedQuad(CLIMBING_DOWN_WEIGHT))
                * (1 - getDataValueEasedQuad(CLIMBING_UP_WEIGHT))
                * (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT));

        float lookHorizontalTimer = 1 - getLookLeftRightTimer();
        float lookVerticalTimer = 1 - getLookUpDownTimer();
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, getTimelineGroup(AnimationOverhaulMain.MOD_ID, "look_horizontal"), lookHorizontalTimer, lookWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, getTimelineGroup(AnimationOverhaulMain.MOD_ID, "look_vertical"), lookVerticalTimer, lookWeight, false);
    }

    private void addPoseLayerWalk(){
        TimelineGroupData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "walk_normal");
        TimelineGroupData.TimelineGroup walkCrouchTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "walk_crouch");
        TimelineGroupData.TimelineGroup walkTrudgeTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "walk_trudge");

        float walkNormalTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION))
                .speedUp(1.4F)
                .repeat(walkNormalTimelineGroup)
                .getValue();
        float walkCrouchTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION))
                .speedUp(2.5F)
                .repeat(walkCrouchTimelineGroup)
                .getValue();
        float walkTrudgeTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION))
                .speedUp(2.25F)
                .repeat(walkCrouchTimelineGroup)
                .getValue();
        //walkNormalTimer = Mth.lerp(getAnimationTimerEasedSine(DIRECTION_SHIFT), walkNormalTimer, 1 - walkNormalTimer);
        //walkCrouchTimer = Mth.lerp(getAnimationTimerEasedSine(DIRECTION_SHIFT), walkCrouchTimer, 1 - walkCrouchTimer);

        float walkNormalWeight = (1 - getDataValueEasedQuad(SPRINT_WEIGHT))
                * Math.min(getDataValueLerped(ANIMATION_SPEED) / 0.86F, 1)
                * (1 - getDataValueEasedQuad(JUMP_WEIGHT))
                * (1 - getDataValueEasedQuad(CROUCH_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getDataValueEasedQuad(TRUDGE_WEIGHT))
                * (1 - getDataValueLerped(ANIMATION_SPEED_Y));
        float walkCrouchWeight = Math.min(getDataValueLerped(ANIMATION_SPEED) / 0.2F, 1)
                * (1 - getDataValueEasedQuad(JUMP_WEIGHT))
                * (1 - getDataValueEasedQuad(TRUDGE_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * getDataValueEasedQuad(CROUCH_WEIGHT)
                * (1 - getDataValueLerped(ANIMATION_SPEED_Y));

        float walkTrudgeWeight = Math.min(getDataValueLerped(ANIMATION_SPEED) / 0.1F, 1)
                * (1 - getDataValueEasedQuad(JUMP_WEIGHT))
                * getDataValueEasedQuad(ON_GROUND_WEIGHT)
                * getDataValueEasedQuad(TRUDGE_WEIGHT)
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getDataValueLerped(ANIMATION_SPEED_Y));

        List<Locator> legLocators = Arrays.asList(locatorLeftLeg, locatorRightLeg);
        List<Locator> nonReversibleWalkLocatorsNoArms = Arrays.asList(locatorBody, locatorHead, locatorCloak);

        // Non reversible stuff
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsNoArms, walkNormalTimelineGroup, walkNormalTimer, walkNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsNoArms, walkCrouchTimelineGroup, walkCrouchTimer, walkCrouchWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(nonReversibleWalkLocatorsNoArms, walkTrudgeTimelineGroup, walkTrudgeTimer, walkTrudgeWeight, false);

        // Legs
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkNormalTimelineGroup, walkNormalTimer, walkNormalWeight * (1 - getDataValueEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkCrouchTimelineGroup, walkCrouchTimer, walkCrouchWeight * (1 - getDataValueEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkTrudgeTimelineGroup, walkTrudgeTimer, walkTrudgeWeight * (1 - getDataValueEasedQuad(DIRECTION_SHIFT)), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkNormalTimelineGroup, 1 - walkNormalTimer, walkNormalWeight * getDataValueEasedQuad(DIRECTION_SHIFT), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkCrouchTimelineGroup, 1 - walkCrouchTimer, walkCrouchWeight * getDataValueEasedQuad(DIRECTION_SHIFT), false);
        this.locatorRig.animateMultipleLocatorsAdditive(legLocators, walkTrudgeTimelineGroup, 1 - walkTrudgeTimer, walkTrudgeWeight * getDataValueEasedQuad(DIRECTION_SHIFT), false);

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
        return (1 - (getDataValueEasedQuad(ATTACK_WEIGHT) * (isLeftHanded() ? 1 : 0)));
    }

    private float rightArmCancelWeight(){
        return (1 - (getDataValueEasedQuad(ATTACK_WEIGHT) * (isLeftHanded() ? 0 : 1)));
    }

    private void addPoseLayerSprint(){
        // Referencing the walk's timeline length so that the walk and sprint cycles sync properly
        TimelineGroupData.TimelineGroup walkNormalTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "walk_normal");
        TimelineGroupData.TimelineGroup sprintNormalTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "sprint_normal");

        float sprintNormalTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION))
                .speedUp(1.4F)
                .repeat(walkNormalTimelineGroup)
                .getValue();

        float sprintNormalWeight = getDataValueEasedQuad(SPRINT_WEIGHT)
                * Math.min(getDataValueLerped(ANIMATION_SPEED) / 0.86F, 1)
                * (1 - getDataValueEasedQuad(JUMP_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getDataValueEasedQuad(IN_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(TRUDGE_WEIGHT))
                * (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(CROUCH_WEIGHT))
                * (1 - getDataValueLerped(ANIMATION_SPEED_Y));


        List<Locator> locatorsRightArm = List.of(locatorRightArm, locatorRightHand);
        List<Locator> locatorsLeftArm = List.of(locatorLeftArm, locatorLeftHand);
        List<Locator> locatorsNoArms = List.of(locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorsNoArms, sprintNormalTimelineGroup, sprintNormalTimer, sprintNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorsLeftArm, sprintNormalTimelineGroup, sprintNormalTimer, sprintNormalWeight * leftArmCancelWeight(), sprintNormalWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorsRightArm, sprintNormalTimelineGroup, sprintNormalTimer, sprintNormalWeight * rightArmCancelWeight(), sprintNormalWeight, false);
    }

    private void addPoseLayerJump(){
        TimelineGroupData.TimelineGroup sprintJumpTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "sprint_jump");
        TimelineGroupData.TimelineGroup walkJumpTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "walk_jump");

        float jumpWeight = getDataValueEasedQuad(JUMP_WEIGHT)
                * (1 - getDataValueEasedQuad(CLIMBING_UP_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getDataValueEasedQuad(IN_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(CLIMBING_DOWN_WEIGHT))
                * (1 - getDataValueEasedQuad(FALL_WEIGHT));
        float sprintJumpWeight = jumpWeight * getDataValueEasedQuad(SPRINT_WEIGHT);
        float walkJumpWeight = jumpWeight * (1 - getDataValueEasedQuad(SPRINT_WEIGHT));
        float jumpTimer = getDataValueLerped(JUMP_TIMER);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, sprintJumpTimelineGroup, jumpTimer, sprintJumpWeight, getDataValue(JUMP_REVERSER));
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, walkJumpTimelineGroup, jumpTimer, walkJumpWeight, getDataValue(JUMP_REVERSER));
    }

    private void addPoseLayerFall(){
        TimelineGroupData.TimelineGroup fallSlowTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "fall_short");
        TimelineGroupData.TimelineGroup fallFastTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "fall_fast");

        float fastFallLerp = getDataValueLerped(FAST_FALL_WEIGHT);
        float fallWeight = getDataValueEasedQuad(FALL_WEIGHT)
                * getDataValueLerped(ANIMATION_SPEED_Y)
                * (1 - getDataValueEasedQuad(CLIMBING_UP_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getDataValueEasedQuad(IN_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(CLIMBING_DOWN_WEIGHT));
        float slowFallWeight = fallWeight * (1 - fastFallLerp);
        float fastFallWeight = fallWeight * fastFallLerp;

        float fallSlowTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION_Y))
                .speedUp(1)
                .repeat(fallSlowTimelineGroup)
                .getValue();
        float fallFastTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION_Y))
                .speedUp(1.5F)
                .repeat(fallFastTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, fallSlowTimelineGroup, fallSlowTimer, slowFallWeight, getDataValue(JUMP_REVERSER));
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, fallFastTimelineGroup, fallFastTimer, fastFallWeight, getDataValue(JUMP_REVERSER));
    }

    private void addPoseLayerClimbing(){
        TimelineGroupData.TimelineGroup climbUpTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "climb_up");
        TimelineGroupData.TimelineGroup climbDownTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "climb_down");

        float climbUpWeight = getDataValueEasedQuad(CLIMBING_UP_WEIGHT)
                * (1 - getDataValueEasedQuad(IN_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getDataValueEasedQuad(ON_GROUND_WEIGHT));
        float climbDownWeight = getDataValueEasedQuad(CLIMBING_DOWN_WEIGHT)
                * (1 - getDataValueEasedQuad(IN_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getDataValueEasedQuad(ON_GROUND_WEIGHT));
        float climbUpTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION_Y))
                .speedUp(1.75F)
                .repeat(climbUpTimelineGroup)
                .getValue();

        float climbDownTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION_Y))
                .speedUp(1.5F)
                .repeat(climbDownTimelineGroup)
                .getValue();

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, climbUpTimelineGroup, climbUpTimer, climbUpWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, climbDownTimelineGroup, 1 - climbDownTimer, climbDownWeight, false);
    }

    private void addPoseLayerCrawling(){
        TimelineGroupData.TimelineGroup crawlMasterTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "crawl_master");
        TimelineGroupData.TimelineGroup crawlTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "crawl");

        float visualCrawlTimer = getDataValueLerped(VISUAL_SWIMMING_WEIGHT)
                * (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))
                * getDataValueEasedQuad(ON_GROUND_WEIGHT);


        float crawlWeight = Easing.CubicBezier.bezierInOutSine().ease(getDataValueLerped(VISUAL_SWIMMING_WEIGHT) * 4 - 3)
                * (1 - getDataValueEasedQuad(IN_WATER_WEIGHT))
                * (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT));
        float crawlForwardWeight = Mth.lerp(getDataValueEasedQuad(DIRECTION_SHIFT), crawlWeight, 0);
        float crawlBackwardsWeight = Mth.lerp(getDataValueEasedQuad(DIRECTION_SHIFT), 0, crawlWeight);

        float crawlTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION))
                .speedUp(5)
                .repeat(crawlTimelineGroup)
                .getValue();
        float crawlBackwardsTimer = 1 - crawlTimer;

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListMaster, crawlMasterTimelineGroup, visualCrawlTimer, 1, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, crawlTimelineGroup, crawlTimer, crawlForwardWeight, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, crawlTimelineGroup, crawlBackwardsTimer, crawlBackwardsWeight, isLeftHanded());
    }

    private void addPoseLayerSwimming(){
        TimelineGroupData.TimelineGroup swimIdleTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "swim_idle");
        TimelineGroupData.TimelineGroup swimIdleForwardTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "swim_idle_forward");
        TimelineGroupData.TimelineGroup swimIdleBackwardsTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "swim_idle_backwards");

        float swimUpWeight =
                (1 - getDataValueEasedQuad(ON_GROUND_WEIGHT))
                        * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                        * (1 - Math.min(getDataValueLerped(ANIMATION_SPEED) / 0.25F, 1))
                        * getDataValueEasedQuad(IN_WATER_WEIGHT)
                        * (Math.min(1, getDataValueEasedQuad(MOVING_UP_WEIGHT) + (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))));

        float swimUpMoveWeight =
                (1 - getDataValueEasedQuad(ON_GROUND_WEIGHT))
                        * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                        * getDataValueEasedQuad(IN_WATER_WEIGHT)
                        * Math.min(getDataValueLerped(ANIMATION_SPEED) * 4, 1)
                        * (Math.min(1, getDataValueEasedQuad(MOVING_UP_WEIGHT) + (1 - getDataValueEasedQuad(UNDER_WATER_WEIGHT))));

        float swimIdleWeight =
                (1 - getDataValueEasedQuad(ON_GROUND_WEIGHT))
                        * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                        * (1 - Math.min(getDataValueLerped(ANIMATION_SPEED) / 0.25F, 1))
                        * getDataValueEasedQuad(UNDER_WATER_WEIGHT);

        float swimIdleMoveWeight =
                (1 - getDataValueEasedQuad(ON_GROUND_WEIGHT))
                        * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                        * Math.min(getDataValueLerped(ANIMATION_SPEED) * 4, 1)
                        * getDataValueEasedQuad(UNDER_WATER_WEIGHT);


        float swimIdleForwardWeight = Mth.lerp(getDataValueEasedQuad(DIRECTION_SHIFT), swimIdleMoveWeight, 0);
        float swimIdleBackwardsWeight = Mth.lerp(getDataValueEasedQuad(DIRECTION_SHIFT), 0, swimIdleMoveWeight);

        float swimIdleUpForwardWeight = Mth.lerp(getDataValueEasedQuad(DIRECTION_SHIFT), swimUpMoveWeight, 0);
        float swimIdleUpBackwardsWeight = Mth.lerp(getDataValueEasedQuad(DIRECTION_SHIFT), 0, swimUpMoveWeight);

        float swimIdleSlowTimer = new TimerProcessor(this.livingEntity.tickCount + this.partialTicks)
                .repeat(swimIdleTimelineGroup)
                .getValue();

        float swimIdleUpTimer = new TimerProcessor(this.livingEntity.tickCount + this.partialTicks)
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

        TimelineGroupData.TimelineGroup swimMasterTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "swim_master");
        TimelineGroupData.TimelineGroup swimFastTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "swim_fast");

        float swimMasterWeight = getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT)
                * getDataValueEasedQuad(IN_WATER_WEIGHT);

        float swimMasterTimer = (float) Mth.clamp((Math.toRadians(Mth.lerp(this.partialTicks, livingEntity.xRotO, livingEntity.getXRot())) / Mth.PI) + 0.5F, 0, 1);
        float swimFastTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION_XYZ))
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
        TimelineGroupData.TimelineGroup fallFlyingMasterTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "swim_master");
        TimelineGroupData.TimelineGroup fallFlyingFastTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "elytra_fast");
        TimelineGroupData.TimelineGroup fallFlyingSlowTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "fall_fast");

        float fallFlyingMasterWeight = getDataValueEasedQuad(FALL_FLYING_WEIGHT);
        float fallFlyingFastWeight = getDataValueLerped(FLYING_SPEED);

        float fallFlyingMasterTimer = (float) Mth.clamp((Math.toRadians(Mth.lerp(this.partialTicks, livingEntity.xRotO, livingEntity.getXRot())) / Mth.PI) + 0.5F, 0, 1);
        float fallFlyingSlowTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION_XYZ))
                .speedUp(1.5F)
                .repeat(fallFlyingSlowTimelineGroup)
                .getValue();
        float fallFlyingFastTimer = new TimerProcessor(getDataValueLerped(ANIMATION_POSITION_XYZ))
                .speedUp(1.5F)
                .repeat(fallFlyingFastTimelineGroup)
                .getValue();

        this.locatorRig.weightedClearTransforms(locatorListMaster, fallFlyingMasterWeight);
        this.locatorHead.xRot -= Mth.HALF_PI * fallFlyingMasterWeight;
        this.locatorMaster.yRot += getDataValueLerped(HEAD_Y_ROT) * -fallFlyingMasterWeight * fallFlyingFastWeight;
        this.locatorRig.animateMultipleLocatorsAdditive(List.of(locatorMaster), fallFlyingMasterTimelineGroup, fallFlyingMasterTimer, fallFlyingMasterWeight, false);
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, fallFlyingSlowTimelineGroup, fallFlyingSlowTimer, (1 - fallFlyingFastWeight) * fallFlyingMasterWeight, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, fallFlyingFastTimelineGroup, fallFlyingFastTimer, (fallFlyingFastWeight) * fallFlyingMasterWeight, isLeftHanded());

    }

    private void addPoseLayerIdle(){
        TimelineGroupData.TimelineGroup idleNormalTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "idle_normal");
        TimelineGroupData.TimelineGroup idleCrouchTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "idle_crouch");
        TimelineGroupData.TimelineGroup idleCrouchTransitionTimelineGroup = getTimelineGroup(AnimationOverhaulMain.MOD_ID, "idle_crouch_transition");

        float idleNormalTimer = new TimerProcessor(this.livingEntity.tickCount + this.partialTicks)
                .repeat(idleNormalTimelineGroup)
                .getValue();

        float idleWeight = (1 - Math.min(getDataValueLerped(ANIMATION_SPEED) / 0.2F, 1))
                * (getDataValueEasedQuad(ON_GROUND_WEIGHT))
                * (1 - getDataValueEasedQuad(VISUAL_SWIMMING_WEIGHT))
                * (1 - getDataValueLerped(ANIMATION_SPEED_Y));

        float idleNormalWeight = Mth.lerp(getDataValueEasedQuad(CROUCH_WEIGHT), idleWeight, 0);
        float idleCrouchWeight = Mth.lerp(getDataValueEasedQuad(CROUCH_WEIGHT), 0, idleWeight);

        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleNormalTimelineGroup, idleNormalTimer, idleNormalWeight, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleCrouchTimelineGroup, idleNormalTimer, idleCrouchWeight, isLeftHanded());
        this.locatorRig.animateMultipleLocatorsAdditive(locatorListAll, idleCrouchTransitionTimelineGroup, getDataValueLerped(CROUCH_WEIGHT), idleWeight, false);

        // Removes the vanilla transformation done for the crouch pose
        if(this.entityModel.crouching){
            for(Locator locator : locatorListAll){
                locator.y -= 2;
            }
        }
    }

    @Override
    protected void finalizeModelParts(ModelPart rootModelPart) {
        rootModelPart.getChild("left_pants").copyFrom(rootModelPart.getChild("left_leg"));
        rootModelPart.getChild("right_pants").copyFrom(rootModelPart.getChild("right_leg"));
        rootModelPart.getChild("left_sleeve").copyFrom(rootModelPart.getChild("left_arm"));
        rootModelPart.getChild("right_sleeve").copyFrom(rootModelPart.getChild("right_arm"));
        rootModelPart.getChild("jacket").copyFrom(rootModelPart.getChild("body"));
        rootModelPart.getChild("hat").copyFrom(rootModelPart.getChild("head"));
        rootModelPart.getChild("cloak").xRot *= -1F;
    }

    private static HumanoidModel.ArmPose getArmPose(LivingEntity livingEntity, InteractionHand interactionHand) {
        ItemStack itemStack = livingEntity.getItemInHand(interactionHand);
        if (itemStack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        }
        if (livingEntity.getUsedItemHand() == interactionHand && livingEntity.getUseItemRemainingTicks() > 0) {
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
            if (useAnim == UseAnim.CROSSBOW && interactionHand == livingEntity.getUsedItemHand()) {
                return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            }
            if (useAnim == UseAnim.SPYGLASS) {
                return HumanoidModel.ArmPose.SPYGLASS;
            }
        } else if (!livingEntity.swinging && itemStack.is(Items.CROSSBOW) && CrossbowItem.isCharged(itemStack)) {
            return HumanoidModel.ArmPose.CROSSBOW_HOLD;
        }
        return HumanoidModel.ArmPose.ITEM;
    }

    private enum AttackType{
        PUNCH,
        PLACE,
        PICKAXE,
        SHOVEL,
        AXE,
        HOE,
        SWORD,
        OFFHAND
    }
}
