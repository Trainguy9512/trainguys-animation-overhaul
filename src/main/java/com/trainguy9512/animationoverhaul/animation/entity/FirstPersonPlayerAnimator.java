package com.trainguy9512.animationoverhaul.animation.entity;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationSequencePlayer;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationStateMachine;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.time.TickTimeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Random;

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

    public static final AnimationDataContainer.DataKey<ItemStack> MAIN_HAND_ITEM_STACK = new AnimationDataContainer.DataKey<ItemStack>("main_hand_item_stack", ItemStack.EMPTY);

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
            setEntityAnimationVariable(MAIN_HAND_ITEM_STACK, this.livingEntity.getMainHandItem());
        }

        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setPose(STATE_EMPTY, sampleAnimationState(MAIN_EMPTY_IDLE_SEQUENCE_PLAYER));
        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setPose(STATE_EMPTY_RAISING, sampleAnimationState(MAIN_EMPTY_RAISE_SEQUENCE_PLAYER));
        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setPose(STATE_LOWERING, sampleAnimationState(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER));
        return sampleAnimationState(MAIN_HAND_STATE_MACHINE);
    }

    private float testFloat = 0;

    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData){
        getAnimationSequencePlayer(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER).playFromStartOnStateActive(getAnimationStateMachine(MAIN_HAND_STATE_MACHINE), STATE_LOWERING);
        getAnimationSequencePlayer(MAIN_EMPTY_RAISE_SEQUENCE_PLAYER).playFromStartOnStateActive(getAnimationStateMachine(MAIN_HAND_STATE_MACHINE), STATE_EMPTY_RAISING);

        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_EMPTY_TO_LOWERING, getEntityAnimationVariable(MAIN_HAND_ITEM_STACK) != this.livingEntity.getMainHandItem());
        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_EMPTY_RAISING_TO_EMPTY, getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4));
        getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).setTransitionCondition(STATE_TRANSITION_LOWERING_TO_EMPTY_RAISING, getAnimationStateMachine(MAIN_HAND_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4)/* && getEntityAnimationVariable(MAIN_HAND_ITEM_STACK) == ItemStack.EMPTY*/);
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
