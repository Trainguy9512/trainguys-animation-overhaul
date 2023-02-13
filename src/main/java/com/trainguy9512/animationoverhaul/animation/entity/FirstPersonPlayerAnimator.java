package com.trainguy9512.animationoverhaul.animation.entity;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.time.TickTimeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

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

    public static final AnimationDataContainer.DataKey<ItemStack> MAIN_HAND_ITEM = new AnimationDataContainer.DataKey<ItemStack>("main_hand_item_stack", ItemStack.EMPTY);
    public static final AnimationDataContainer.DataKey<Boolean> IS_ATTACKING = new AnimationDataContainer.DataKey<Boolean>("is_attacking", false);
    public static final AnimationDataContainer.DataKey<Boolean> IS_USING_ITEM = new AnimationDataContainer.DataKey<Boolean>("is_using_item", false);
    public static final AnimationDataContainer.DataKey<Boolean> IS_MINING = new AnimationDataContainer.DataKey<Boolean>("is_mining", false);
    public static final AnimationDataContainer.DataKey<Float> WALK_WEIGHT = new AnimationDataContainer.DataKey<Float>("walk_weight", 0F);

    private static final String ITEM_SWITCH_NOTIFY = "item_switch_notify";
    private static final AnimationSequencePlayer MAIN_EMPTY_RAISE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_raise_sequence_player", new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/fp_right_empty_raise"))
            .setLooping(false)
            .setDefaultPlayRate(1.5F);
    private static final AnimationSequencePlayer MAIN_EMPTY_LOWER_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_lower_sequence_player", new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/fp_right_empty_lower"))
            .addAnimNotify(ITEM_SWITCH_NOTIFY, 7)
            .setLooping(false)
            .setDefaultPlayRate(1.6F);
    private static final AnimationSequencePlayer MAIN_EMPTY_IDLE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("main_empty_idle_sequence_player", new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/fp_right_empty_idle"))
            .setDefaultPlayRate(0.8F);

    private static final String STATE_EMPTY = "empty";
    private static final String STATE_LOWERING = "lowering";
    private static final String STATE_EMPTY_RAISING = "empty_raising";
    private static final String STATE_TRANSITION_EMPTY_TO_LOWERING = "empty_to_lowering";
    private static final String STATE_TRANSITION_LOWERING_TO_EMPTY_RAISING = "lowering_to_empty_raising";
    private static final String STATE_TRANSITION_EMPTY_RAISING_TO_EMPTY = "empty_raising_to_empty";
    private static final AnimationStateMachine MAIN_HAND_STATE_MACHINE = AnimationStateMachine.of("main_hand_state_machine")
            .addStates(List.of(
                    STATE_EMPTY,
                    STATE_LOWERING,
                    STATE_EMPTY_RAISING
            ))
            .setDefaultState(STATE_EMPTY)
            .addStateTransition(STATE_TRANSITION_EMPTY_TO_LOWERING, STATE_EMPTY, STATE_LOWERING, TickTimeUtils.ticksFromMayaFrames(1))
            .addStateTransition(STATE_TRANSITION_LOWERING_TO_EMPTY_RAISING, STATE_LOWERING, STATE_EMPTY_RAISING, TickTimeUtils.ticksFromMayaFrames(1))
            .addStateTransition(STATE_TRANSITION_EMPTY_RAISING_TO_EMPTY, STATE_EMPTY_RAISING, STATE_EMPTY, TickTimeUtils.ticksFromMayaFrames(4));

    private static final AnimationMontageTrack MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK = AnimationMontageTrack.of("main_hand_empty_punch_montage_track");
    private static final AnimationMontage MAIN_HAND_EMPTY_PUNCH_MONTAGE = AnimationMontage.of(TickTimeUtils.ticksFromMayaFrames(12F), new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/fp_right_empty_punch"))
            .setBlendInDuration(1)
            .setBlendOutDuration(TickTimeUtils.ticksFromMayaFrames(8F));

    private static final AnimationBlendSpacePlayer MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER = AnimationBlendSpacePlayer.of("main_hand_empty_walk_blendspace_player")
            .addEntry(0, new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/fp_right_empty_walk"), 0F)
            .addEntry(1, new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/fp_right_empty_walk"), 1.3F);

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
        if(getAnimationSequencePlayer(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER).isAnimNotityActive(ITEM_SWITCH_NOTIFY)){
            setEntityAnimationVariable(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem());
        }

        AnimationPose mainEmptyPose = sampleAnimationStateFromInputPose(
                MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK,
                sampleAnimationState(MAIN_EMPTY_IDLE_SEQUENCE_PLAYER).blendLinear(
                        sampleAnimationState(MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER),
                        getEntityAnimationVariable(WALK_WEIGHT)
                ));

        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setPose(STATE_EMPTY, mainEmptyPose);
        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setPose(STATE_EMPTY_RAISING, sampleAnimationState(MAIN_EMPTY_RAISE_SEQUENCE_PLAYER));
        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setPose(STATE_LOWERING, sampleAnimationState(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER));

        AnimationPose raiseLowerStatePose = sampleAnimationState(MAIN_HAND_STATE_MACHINE);
        raiseLowerStatePose.getLocatorPose(LOCATOR_RIGHT_ARM);
        raiseLowerStatePose.setLocatorPose(LOCATOR_RIGHT_ARM, raiseLowerStatePose.getLocatorPose(LOCATOR_RIGHT_ARM).translate(new Vector3f(0, 0, -20F), false));


        setEntityAnimationVariable(TEST_VALUE, getEntityAnimationVariable(TEST_VALUE) + 0.15F);
        AnimationPose testAnimPose = new AnimationPose(this.locatorSkeleton);
        testAnimPose.setLocatorPose(LOCATOR_RIGHT_ARM, testAnimPose.getLocatorPose(LOCATOR_RIGHT_ARM).translate(new Vector3f(0F, 0F, -20F), false));
        testAnimPose.setLocatorPose(LOCATOR_LEFT_ARM, testAnimPose.getLocatorPose(LOCATOR_LEFT_ARM).translate(new Vector3f(0F, -20F, 0F), false));
        testAnimPose.setLocatorPose(LOCATOR_RIGHT_ARM, testAnimPose.getLocatorPose(LOCATOR_RIGHT_ARM).rotate(new Vector3f(0F, (getEntityAnimationVariable(TEST_VALUE) % Mth.TWO_PI) - Mth.PI, 0F), false));


        return raiseLowerStatePose;
    }

    public static final AnimationDataContainer.DataKey<Float> TEST_VALUE = new AnimationDataContainer.DataKey<>("test_value", 0F);

    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData){
        getAnimationSequencePlayer(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER).playFromStartOnStateActive(getAnimationStateMachine(MAIN_HAND_STATE_MACHINE), STATE_LOWERING);
        getAnimationSequencePlayer(MAIN_EMPTY_RAISE_SEQUENCE_PLAYER).playFromStartOnStateActive(getAnimationStateMachine(MAIN_HAND_STATE_MACHINE), STATE_EMPTY_RAISING);

        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_EMPTY_TO_LOWERING, getEntityAnimationVariable(MAIN_HAND_ITEM).getItem() != this.livingEntity.getMainHandItem().getItem());
        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_EMPTY_RAISING_TO_EMPTY, getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4));
        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_LOWERING_TO_EMPTY_RAISING, getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4)/* && getEntityAnimationVariable(MAIN_HAND_ITEM_STACK) == ItemStack.EMPTY*/);

        /*
        if(this.livingEntity.attackAnim > 0.16 && this.livingEntity.attackAnim < 0.17){
            getAnimationMontageTrack(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK).playMontage(MAIN_HAND_EMPTY_PUNCH_MONTAGE);
        }
         */
        if(getEntityAnimationVariable(IS_ATTACKING) && !getEntityAnimationVariable(IS_MINING) && !getEntityAnimationData().get(IS_MINING).getOld()){
            setEntityAnimationVariable(IS_ATTACKING, false);
            getAnimationMontageTrack(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK).playMontage(MAIN_HAND_EMPTY_PUNCH_MONTAGE);
        }
        //AnimationOverhaulMain.LOGGER.info(getEntityAnimationVariable(IS_MINING));

        boolean isInputtingMovement =
                this.livingEntity.input.forwardImpulse != 0 ||
                this.livingEntity.input.leftImpulse != 0;
        //AnimationOverhaulMain.LOGGER.info("{}, {}", this.livingEntity.input.forwardImpulse, this.livingEntity.animationSpeed);
        getEntityAnimationData().incrementInFramesFromCondition(WALK_WEIGHT, isInputtingMovement, 2, 4);
        getAnimationBlendSpacePlayer(MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER).setValue(this.livingEntity.animationSpeed);
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
