package com.trainguy9512.animationoverhaul.animation.entity;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.util.time.TickTimeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;

import java.util.List;

public class FirstPersonPlayerAnimator extends LivingEntityAnimator<LocalPlayer, PlayerModel<LocalPlayer>>{

    public static FirstPersonPlayerAnimator INSTANCE = new FirstPersonPlayerAnimator();

    public AnimationDataContainer localAnimationDataContainer = new AnimationDataContainer();
    public BakedAnimationPose localBakedPose;

    public static final String LOCATOR_ROOT = "root";
    public static final String LOCATOR_CAMERA = "camera";
    public static final String LOCATOR_RIGHT_ARM = "rightArm";
    public static final String LOCATOR_LEFT_ARM = "leftArm";
    public static final String LOCATOR_RIGHT_HAND = "rightHand";
    public static final String LOCATOR_LEFT_HAND = "leftHand";

    public static final ResourceLocation ANIMATION_FP_RIGHT_EMPTY_RAISE = TimelineGroupData.getNativeResourceLocation("player/fp_right_empty_raise");
    public static final ResourceLocation ANIMATION_FP_RIGHT_EMPTY_LOWER = TimelineGroupData.getNativeResourceLocation("player/fp_right_empty_lower");
    public static final ResourceLocation ANIMATION_FP_RIGHT_EMPTY_IDLE = TimelineGroupData.getNativeResourceLocation("player/fp_right_empty_idle");
    public static final ResourceLocation ANIMATION_FP_RIGHT_EMPTY_PUNCH = TimelineGroupData.getNativeResourceLocation("player/fp_right_empty_punch");
    public static final ResourceLocation ANIMATION_FP_RIGHT_EMPTY_WALK = TimelineGroupData.getNativeResourceLocation("player/fp_right_empty_walk");
    public static final ResourceLocation ANIMATION_FP_RIGHT_EMPTY_JUMP = TimelineGroupData.getNativeResourceLocation("player/fp_right_empty_jump");
    public static final ResourceLocation ANIMATION_FP_RIGHT_EMPTY_FALLING = TimelineGroupData.getNativeResourceLocation("player/fp_right_empty_falling");
    public static final ResourceLocation ANIMATION_FP_RIGHT_BASICITEM_POSE = TimelineGroupData.getNativeResourceLocation("player/fp_right_basicitem_pose");

    public static final AnimationDataContainer.DataKey<ItemStack> MAIN_HAND_ITEM = new AnimationDataContainer.DataKey<ItemStack>("main_hand_item_stack", ItemStack.EMPTY);
    public static final AnimationDataContainer.DataKey<Boolean> IS_ATTACKING = new AnimationDataContainer.DataKey<Boolean>("is_attacking", false);
    public static final AnimationDataContainer.DataKey<Boolean> IS_USING_ITEM = new AnimationDataContainer.DataKey<Boolean>("is_using_item", false);
    public static final AnimationDataContainer.DataKey<Boolean> IS_MINING = new AnimationDataContainer.DataKey<Boolean>("is_mining", false);
    public static final AnimationDataContainer.DataKey<Float> WALK_WEIGHT = new AnimationDataContainer.DataKey<Float>("walk_weight", 0F);

    public static final AnimationDataContainer.DataKey<Boolean> IS_FALLING = new AnimationDataContainer.DataKey<Boolean>("is_falling", false);
    public static final AnimationDataContainer.DataKey<Boolean> IS_JUMPING = new AnimationDataContainer.DataKey<Boolean>("is_jumping", false);

    private static final String ITEM_SWITCH_NOTIFY = "item_switch_notify";
    private static final AnimationSequencePlayer MAIN_EMPTY_RAISE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_raise_sequence_player", ANIMATION_FP_RIGHT_EMPTY_RAISE)
            .setLooping(false)
            .setDefaultPlayRate(1.5F);
    private static final AnimationSequencePlayer MAIN_EMPTY_LOWER_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_lower_sequence_player", ANIMATION_FP_RIGHT_EMPTY_LOWER)
            .addAnimNotify(ITEM_SWITCH_NOTIFY, 7)
            .setLooping(false)
            .setDefaultPlayRate(1.6F);
    private static final AnimationSequencePlayer MAIN_EMPTY_IDLE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_idle_sequence_player", ANIMATION_FP_RIGHT_EMPTY_IDLE)
            .setDefaultPlayRate(0.8F);
    private static final AnimationSequencePlayer MAIN_EMPTY_JUMP_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_jump_sequence_player", ANIMATION_FP_RIGHT_EMPTY_JUMP)
            .setDefaultPlayRate(1F)
            .setLooping(false)
            .setStartTime(TickTimeUtils.ticksFromMayaFrames(1))
            .setEndTime(TickTimeUtils.ticksFromMayaFrames(15));
    private static final AnimationSequencePlayer MAIN_EMPTY_LAND_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_land_sequence_player", ANIMATION_FP_RIGHT_EMPTY_JUMP)
            .setDefaultPlayRate(1F)
            .setLooping(false)
            .setStartTime(TickTimeUtils.ticksFromMayaFrames(20))
            .setEndTime(TickTimeUtils.ticksFromMayaFrames(30));
    private static final AnimationSequencePlayer MAIN_EMPTY_FALLING_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_falling_sequence_player", ANIMATION_FP_RIGHT_EMPTY_FALLING)
            .setDefaultPlayRate(1F);

    private static final String STATE_MAINITEMSWITCH_EMPTY = "empty";
    private static final String STATE_MAINITEMSWITCH_EMPTY_RAISING = "empty_raising";
    private static final String STATE_MAINITEMSWITCH_BASICITEM = "basic_item";
    private static final String STATE_MAINITEMSWITCH_BASICITEM_RAISING = "basic_item_raising";
    private static final String STATE_MAINITEMSWITCH_LOWERING = "lowering";
    private static final String STATE_TRANSITION_MAINITEMSWITCH_EMPTY_TO_LOWERING = "empty_to_lowering";
    private static final String STATE_TRANSITION_MAINITEMSWITCH_LOWERING_TO_EMPTY_RAISING = "lowering_to_empty_raising";
    private static final String STATE_TRANSITION_MAINITEMSWITCH_EMPTY_RAISING_TO_EMPTY = "empty_raising_to_empty";
    private static final String STATE_TRANSITION_MAINITEMSWITCH_BASICITEM_TO_LOWERING = "basic_item_to_lowering";
    private static final String STATE_TRANSITION_MAINITEMSWITCH_LOWERING_TO_BASICITEM_RAISING = "lowering_to_basic_item_raising";
    private static final String STATE_TRANSITION_MAINITEMSWITCH_BASICITEM_RAISING_TO_BASICITEM= "basic_item_raising_to_basic_item";
    private static final AnimationStateMachine MAINITEMSWITCH_STATE_MACHINE = AnimationStateMachine.of("main_hand_state_machine")
            .addStates(List.of(
                    STATE_MAINITEMSWITCH_EMPTY,
                    STATE_MAINITEMSWITCH_LOWERING,
                    STATE_MAINITEMSWITCH_EMPTY_RAISING,
                    STATE_MAINITEMSWITCH_BASICITEM,
                    STATE_MAINITEMSWITCH_BASICITEM_RAISING
            ))
            .setDefaultState(STATE_MAINITEMSWITCH_EMPTY)
            .addStateTransition(STATE_TRANSITION_MAINITEMSWITCH_EMPTY_TO_LOWERING, STATE_MAINITEMSWITCH_EMPTY, STATE_MAINITEMSWITCH_LOWERING, TickTimeUtils.ticksFromMayaFrames(1))
            .addStateTransition(STATE_TRANSITION_MAINITEMSWITCH_LOWERING_TO_EMPTY_RAISING, STATE_MAINITEMSWITCH_LOWERING, STATE_MAINITEMSWITCH_EMPTY_RAISING, TickTimeUtils.ticksFromMayaFrames(1), 2)
            .addStateTransition(STATE_TRANSITION_MAINITEMSWITCH_EMPTY_RAISING_TO_EMPTY, STATE_MAINITEMSWITCH_EMPTY_RAISING, STATE_MAINITEMSWITCH_EMPTY, TickTimeUtils.ticksFromMayaFrames(4))
            .addStateTransition(STATE_TRANSITION_MAINITEMSWITCH_BASICITEM_TO_LOWERING, STATE_MAINITEMSWITCH_BASICITEM, STATE_MAINITEMSWITCH_LOWERING, TickTimeUtils.ticksFromMayaFrames(3))
            .addStateTransition(STATE_TRANSITION_MAINITEMSWITCH_LOWERING_TO_BASICITEM_RAISING, STATE_MAINITEMSWITCH_LOWERING, STATE_MAINITEMSWITCH_BASICITEM_RAISING, TickTimeUtils.ticksFromMayaFrames(1), 1)
            .addStateTransition(STATE_TRANSITION_MAINITEMSWITCH_BASICITEM_RAISING_TO_BASICITEM, STATE_MAINITEMSWITCH_BASICITEM_RAISING, STATE_MAINITEMSWITCH_BASICITEM, TickTimeUtils.ticksFromMayaFrames(4));


    private static final String STATE_JUMP_JUMPING = "jumping";
    private static final String STATE_JUMP_FALLING = "falling";
    private static final String STATE_JUMP_LANDING = "landing";
    private static final String STATE_JUMP_ON_GROUND = "on_ground";
    private static final String STATE_TRANSITION_JUMP_GROUND_TO_JUMPING = "ground_to_jumping";
    private static final String STATE_TRANSITION_JUMP_GROUND_TO_FALLING = "ground_to_falling";
    private static final String STATE_TRANSITION_JUMP_JUMPING_TO_FALLING = "jumping_to_falling";
    private static final String STATE_TRANSITION_JUMP_JUMPING_TO_LANDING = "jumping_to_landing";
    private static final String STATE_TRANSITION_JUMP_FALLING_TO_LANDING = "falling_to_landing";
    private static final String STATE_TRANSITION_JUMP_LANDING_TO_JUMPING = "landing_to_jumping";
    private static final String STATE_TRANSITION_JUMP_LANDING_TO_FALLING = "landing_to_falling";
    private static final String STATE_TRANSITION_JUMP_LANDING_TO_GROUND = "landing_to_ground";
    private static final AnimationStateMachine JUMP_STATE_MACHINE = AnimationStateMachine.of("jump_state_machine")
            .addStates(List.of(
                    STATE_JUMP_FALLING,
                    STATE_JUMP_JUMPING,
                    STATE_JUMP_LANDING,
                    STATE_JUMP_ON_GROUND
            ))
            .addStateTransition(STATE_TRANSITION_JUMP_GROUND_TO_JUMPING, STATE_JUMP_ON_GROUND, STATE_JUMP_JUMPING, TickTimeUtils.ticksFromMayaFrames(1), 1)
            .addStateTransition(STATE_TRANSITION_JUMP_GROUND_TO_FALLING, STATE_JUMP_ON_GROUND, STATE_JUMP_FALLING, TickTimeUtils.ticksFromMayaFrames(3), 2)
            .addStateTransition(STATE_TRANSITION_JUMP_JUMPING_TO_FALLING, STATE_JUMP_JUMPING, STATE_JUMP_FALLING, TickTimeUtils.ticksFromMayaFrames(4), 1)
            .addStateTransition(STATE_TRANSITION_JUMP_JUMPING_TO_LANDING, STATE_JUMP_JUMPING, STATE_JUMP_LANDING, TickTimeUtils.ticksFromMayaFrames(1), 2)
            .addStateTransition(STATE_TRANSITION_JUMP_FALLING_TO_LANDING, STATE_JUMP_FALLING, STATE_JUMP_LANDING, TickTimeUtils.ticksFromMayaFrames(1), 1)
            .addStateTransition(STATE_TRANSITION_JUMP_LANDING_TO_JUMPING, STATE_JUMP_LANDING, STATE_JUMP_JUMPING, TickTimeUtils.ticksFromMayaFrames(1), 2)
            .addStateTransition(STATE_TRANSITION_JUMP_LANDING_TO_FALLING, STATE_JUMP_LANDING, STATE_JUMP_FALLING, TickTimeUtils.ticksFromMayaFrames(1), 3)
            .addStateTransition(STATE_TRANSITION_JUMP_LANDING_TO_GROUND, STATE_JUMP_LANDING, STATE_JUMP_ON_GROUND, TickTimeUtils.ticksFromMayaFrames(4), 1);


    private static final AnimationMontageTrack MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK = AnimationMontageTrack.of("main_hand_empty_punch_montage_track");
    private static final AnimationMontage MAIN_HAND_EMPTY_PUNCH_MONTAGE = AnimationMontage.of(TickTimeUtils.ticksFromMayaFrames(12F), ANIMATION_FP_RIGHT_EMPTY_PUNCH)
            .setBlendInDuration(1)
            .setBlendOutDuration(TickTimeUtils.ticksFromMayaFrames(8F));

    private static final AnimationBlendSpacePlayer MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER = AnimationBlendSpacePlayer.of("main_hand_empty_walk_blendspace_player")
            .addEntry(0, ANIMATION_FP_RIGHT_EMPTY_WALK, 0F)
            .addEntry(2, ANIMATION_FP_RIGHT_EMPTY_WALK, 2.9F);

    public FirstPersonPlayerAnimator(){
        super();
    }

    @Override
    protected void buildRig(LocatorSkeleton locatorRig) {
        locatorRig.addLocator(LOCATOR_ROOT);
        locatorRig.addLocator(LOCATOR_CAMERA);
        locatorRig.addLocator(LOCATOR_RIGHT_ARM, LOCATOR_LEFT_ARM);
        locatorRig.addLocator(LOCATOR_LEFT_ARM, LOCATOR_RIGHT_ARM);
        locatorRig.addLocator(LOCATOR_RIGHT_HAND, LOCATOR_LEFT_HAND);
        locatorRig.addLocator(LOCATOR_LEFT_HAND, LOCATOR_RIGHT_HAND);
    }

    @Override
    protected AnimationPose calculatePose() {
        /*
        if(getEntityAnimationData().getAnimationSequencePlayer(TEST_SEQUENCE_PLAYER).isAnimNotityActive(TEST_NOTIFY)){
            Random random = new Random();
            AnimationOverhaulMain.LOGGER.info("Notify hit on frame 40! Here's a random number: {}", random.nextInt(100));
        }
         */
        if(getAnimationState(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER).isAnimNotityActive(ITEM_SWITCH_NOTIFY)){
            setEntityAnimationVariable(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem());
        }

        AnimationPose mainEmptyPose = sampleAnimationStateFromInputPose(
                MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK,
                sampleAnimationState(MAIN_EMPTY_IDLE_SEQUENCE_PLAYER).getBlendedLinear(
                        sampleAnimationState(MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER),
                        getEntityAnimationVariable(WALK_WEIGHT)
                ));

        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setPose(STATE_MAINITEMSWITCH_EMPTY, getMainEmptyLocomotionPose(getStaticMainEmptyPose(), true));
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setPose(STATE_MAINITEMSWITCH_EMPTY_RAISING, getMainHandRaisePose(getStaticMainEmptyPose()));
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setPose(STATE_MAINITEMSWITCH_BASICITEM, getMainEmptyLocomotionPose(getStaticMainBasicItemPose(), true));
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setPose(STATE_MAINITEMSWITCH_BASICITEM_RAISING, getMainHandRaisePose(getStaticMainBasicItemPose()));
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setPose(STATE_MAINITEMSWITCH_LOWERING, getMainHandLowerPose(getStaticMainEmptyPose()));

        AnimationPose raiseLowerStatePose = sampleAnimationState(MAINITEMSWITCH_STATE_MACHINE);


        /*
        AnimationPose emptyRightHandPose = AnimationPose.fromChannelTimeline(this.locatorSkeleton, ANIMATION_FP_RIGHT_EMPTY_LOWER, 0);
        AnimationPose additiveRightHandPose = raiseLowerStatePose.getSubtracted(emptyRightHandPose);
        additiveRightHandPose.setLocatorPose(LOCATOR_RIGHT_ARM, additiveRightHandPose.getLocatorPose(LOCATOR_RIGHT_ARM).rotate(new Vector3f(Mth.PI/-2F, 0, 0), false));

        raiseLowerStatePose = raiseLowerStatePose.blendByLocators(additiveRightHandPose, List.of(LOCATOR_RIGHT_ARM));
        raiseLowerStatePose.setLocatorPose(LOCATOR_RIGHT_ARM, raiseLowerStatePose.getLocatorPose(LOCATOR_RIGHT_ARM).translate(new Vector3f(0, 0, -20F), false));
         */

        return raiseLowerStatePose;
    }

    private AnimationPose getMainEmptyLocomotionPose(AnimationPose basePose, boolean applyPunch){
        AnimationPose pose = sampleAnimationState(MAIN_EMPTY_IDLE_SEQUENCE_PLAYER);
        pose.blendLinear(sampleAnimationState(MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER), getEntityAnimationVariable(WALK_WEIGHT));

        getAnimationState(JUMP_STATE_MACHINE).setPose(STATE_JUMP_ON_GROUND, pose);
        getAnimationState(JUMP_STATE_MACHINE).setPose(STATE_JUMP_JUMPING, sampleAnimationState(MAIN_EMPTY_JUMP_SEQUENCE_PLAYER));
        getAnimationState(JUMP_STATE_MACHINE).setPose(STATE_JUMP_FALLING, AnimationPose.fromChannelTimeline(this.locatorSkeleton, ANIMATION_FP_RIGHT_EMPTY_JUMP, 0.5F).getBlendedLinear(sampleAnimationState(MAIN_EMPTY_FALLING_SEQUENCE_PLAYER), Mth.clampedMap(this.livingEntity.fallDistance, 0, 20, 0.3F, 1)));
        getAnimationState(JUMP_STATE_MACHINE).setPose(STATE_JUMP_LANDING, sampleAnimationState(MAIN_EMPTY_LAND_SEQUENCE_PLAYER));
        pose = sampleAnimationState(JUMP_STATE_MACHINE);


        if(applyPunch){
            pose = sampleAnimationStateFromInputPose(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK, pose);
        }

        //AnimationPose additivePose = basePose.getSubtracted(getStaticMainEmptyPose());
        //return pose.getAdded(additivePose);
        AnimationPose unAddedPose = pose.getSubtracted(getStaticMainEmptyPose());
        return unAddedPose.getAdded(basePose);
    }

    private AnimationPose getMainHandRaisePose(AnimationPose basePose){
        AnimationPose pose = sampleAnimationState(MAIN_EMPTY_RAISE_SEQUENCE_PLAYER);
        return pose.getSubtracted(getStaticMainEmptyPose()).getAdded(basePose);
    }

    private AnimationPose getMainHandLowerPose(AnimationPose basePose){
        AnimationPose pose = sampleAnimationState(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER);
        return pose.getSubtracted(getStaticMainEmptyPose()).getAdded(basePose);
    }


    private AnimationPose getStaticMainEmptyPose(){
        return AnimationPose.fromChannelTimeline(this.locatorSkeleton, ANIMATION_FP_RIGHT_EMPTY_PUNCH, 0);
    }

    private AnimationPose getStaticMainBasicItemPose(){
        return AnimationPose.fromChannelTimeline(this.locatorSkeleton, ANIMATION_FP_RIGHT_BASICITEM_POSE, 0);
    }

    public static final AnimationDataContainer.DataKey<Float> TEST_VALUE = new AnimationDataContainer.DataKey<>("test_value", 0F);

    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData){
        getAnimationState(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER).playFromStartOnStateActive(getAnimationState(MAINITEMSWITCH_STATE_MACHINE), STATE_MAINITEMSWITCH_LOWERING);
        getAnimationState(MAIN_EMPTY_RAISE_SEQUENCE_PLAYER).playFromStartOnStateActive(getAnimationState(MAINITEMSWITCH_STATE_MACHINE), STATE_MAINITEMSWITCH_EMPTY_RAISING);

        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_MAINITEMSWITCH_EMPTY_TO_LOWERING, getEntityAnimationVariable(MAIN_HAND_ITEM).getItem() != this.livingEntity.getMainHandItem().getItem());
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_MAINITEMSWITCH_EMPTY_RAISING_TO_EMPTY, getAnimationState(MAINITEMSWITCH_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4));
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_MAINITEMSWITCH_LOWERING_TO_EMPTY_RAISING, getAnimationState(MAINITEMSWITCH_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4) && getEntityAnimationVariable(MAIN_HAND_ITEM).isEmpty());
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_MAINITEMSWITCH_BASICITEM_TO_LOWERING, getEntityAnimationVariable(MAIN_HAND_ITEM).getItem() != this.livingEntity.getMainHandItem().getItem());
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_MAINITEMSWITCH_BASICITEM_RAISING_TO_BASICITEM, getAnimationState(MAINITEMSWITCH_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4));
        getAnimationState(MAINITEMSWITCH_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_MAINITEMSWITCH_LOWERING_TO_BASICITEM_RAISING, getAnimationState(MAINITEMSWITCH_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4) && !getEntityAnimationVariable(MAIN_HAND_ITEM).isEmpty());




        /*
        private static final String STATE_TRANSITION_JUMP_GROUND_TO_JUMPING = "ground_to_jumping";
        private static final String STATE_TRANSITION_JUMP_GROUND_TO_FALLING = "ground_to_falling";
        private static final String STATE_TRANSITION_JUMP_JUMPING_TO_FALLING = "jumping_to_falling";
        private static final String STATE_TRANSITION_JUMP_FALLING_TO_LANDING = "falling_to_landing";
        private static final String STATE_TRANSITION_JUMP_LANDING_TO_JUMPING = "landing_to_jumping";
        private static final String STATE_TRANSITION_JUMP_LANDING_TO_FALLING = "landing_to_falling";
        private static final String STATE_TRANSITION_JUMP_LANDING_TO_GROUND = "landing_to_ground";

         */
        setEntityAnimationVariable(IS_JUMPING,
                this.livingEntity.input.jumping
                && !this.livingEntity.getAbilities().flying
                && !this.livingEntity.isPassenger()
        );
        setEntityAnimationVariable(IS_FALLING,
                !this.livingEntity.isOnGround()
        );
        getAnimationState(JUMP_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_JUMP_GROUND_TO_JUMPING, getEntityAnimationVariable(IS_FALLING) && getEntityAnimationVariable(IS_JUMPING));
        getAnimationState(JUMP_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_JUMP_GROUND_TO_FALLING, getEntityAnimationVariable(IS_FALLING));
        getAnimationState(JUMP_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_JUMP_JUMPING_TO_FALLING, getAnimationState(JUMP_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(6) && getEntityAnimationVariable(IS_FALLING));
        getAnimationState(JUMP_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_JUMP_JUMPING_TO_LANDING, !getEntityAnimationVariable(IS_FALLING));
        getAnimationState(JUMP_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_JUMP_FALLING_TO_LANDING, !getEntityAnimationVariable(IS_FALLING));
        getAnimationState(JUMP_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_JUMP_LANDING_TO_JUMPING, getEntityAnimationVariable(IS_FALLING) && getEntityAnimationVariable(IS_JUMPING));
        getAnimationState(JUMP_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_JUMP_LANDING_TO_FALLING, getEntityAnimationVariable(IS_FALLING));
        getAnimationState(JUMP_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_JUMP_LANDING_TO_GROUND, getAnimationState(JUMP_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(3) && !getEntityAnimationVariable(IS_FALLING));
        getAnimationState(MAIN_EMPTY_JUMP_SEQUENCE_PLAYER).playFromStartOnStateActive(JUMP_STATE_MACHINE, STATE_JUMP_JUMPING);
        getAnimationState(MAIN_EMPTY_LAND_SEQUENCE_PLAYER).playFromStartOnStateActive(JUMP_STATE_MACHINE, STATE_JUMP_LANDING);
        getAnimationState(MAIN_EMPTY_FALLING_SEQUENCE_PLAYER).setPlayRate(Mth.clampedMap(this.livingEntity.fallDistance, 0, 20, 1, 2));


        if(getEntityAnimationVariable(IS_ATTACKING) && !getEntityAnimationVariable(IS_MINING) && !getEntityAnimationData().get(IS_MINING).getOld()){
            setEntityAnimationVariable(IS_ATTACKING, false);
            getAnimationState(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK).playMontage(MAIN_HAND_EMPTY_PUNCH_MONTAGE);
        }
        //AnimationOverhaulMain.LOGGER.info(getEntityAnimationVariable(IS_MINING));

        boolean isInputtingMovement =
                this.livingEntity.input.forwardImpulse != 0 ||
                this.livingEntity.input.leftImpulse != 0;
        //AnimationOverhaulMain.LOGGER.info("{}, {}", this.livingEntity.input.forwardImpulse, this.livingEntity.animationSpeed);
        getEntityAnimationData().incrementInFramesFromCondition(WALK_WEIGHT, isInputtingMovement, 2, 4);
        getAnimationState(MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER).setValue(this.livingEntity.animationSpeed);
    }

    public void tickExternal(){
        LocalPlayer player = Minecraft.getInstance().player;
        this.setEntity(player);

        this.tick(player, getEntityAnimationData());
        getEntityAnimationData().tickAnimationStates();

        if(this.localBakedPose == null){
            this.localBakedPose = new BakedAnimationPose();
            this.localBakedPose.setPose(new AnimationPose(this.locatorSkeleton));
        }
        if(!this.localBakedPose.hasPose){
            this.localBakedPose.setPose(new AnimationPose(this.locatorSkeleton));
            this.localBakedPose.hasPose = true;
        }
        this.localBakedPose.pushToOld();

        AnimationPose animationPose = this.calculatePose();
        if (animationPose == null){
            animationPose = new AnimationPose(this.locatorSkeleton);
        }
        animationPose.applyDefaultPoseOffset();

        this.localBakedPose.setPose(animationPose.getCopy());
    }

    @Override
    protected AnimationDataContainer getEntityAnimationData() {
        return this.localAnimationDataContainer;
    }
}
