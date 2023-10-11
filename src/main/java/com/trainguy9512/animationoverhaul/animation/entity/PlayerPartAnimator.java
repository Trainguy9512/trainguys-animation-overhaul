package com.trainguy9512.animationoverhaul.animation.entity;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationBlendSpacePlayer;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationSequencePlayer;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationStateMachine;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.util.time.TickTimeUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class PlayerPartAnimator extends LivingEntityAnimator<Player, PlayerModel<Player>, PlayerPartAnimator.ModelPartLocators> {

    private static final String MODEL_PART_ROOT = "root";
    private static final String MODEL_PART_HEAD = "head";
    private static final String MODEL_PART_BODY = "body";
    private static final String MODEL_PART_LEFT_LEG = "left_leg";
    private static final String MODEL_PART_RIGHT_LEG = "right_leg";
    private static final String MODEL_PART_LEFT_ARM = "left_arm";
    private static final String MODEL_PART_RIGHT_ARM = "right_arm";
    private static final String MODEL_PART_CAPE = "cloak";

    public enum ModelPartLocators{
        root,
        head,
        body,
        leftLeg,
        rightLeg,
        leftArm,
        rightArm,
        cape,
        leftHand,
        rightHand
    }

    public static final ResourceLocation ANIM_TEST_IDLE = TimelineGroupData.getNativeResourceLocation("player/idle_normal");
    public static final ResourceLocation ANIM_TEST_WALK = TimelineGroupData.getNativeResourceLocation("player/sprint_normal");

    private static final AnimationSequencePlayer ANIM_TEST_IDLE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("anim_test_idle_sequence_player", ANIM_TEST_IDLE)
            .setDefaultPlayRate(0.2F);
    private static final AnimationSequencePlayer ANIM_TEST_WALK_SEQUENCE_PLAYER = AnimationSequencePlayer.of("anim_test_walk_sequence_player", ANIM_TEST_WALK)
            .setDefaultPlayRate(1F);

    enum TestStates {
        IDLE,
        WALKING
    }
    private static final AnimationStateMachine<TestStates> TEST_STATE_MACHINE = AnimationStateMachine.of("main_hand_state_machine", TestStates.values())
            .addStateTransition(TestStates.IDLE, TestStates.WALKING, TickTimeUtils.ticksFromMayaFrames(3))
            .addStateTransition(TestStates.WALKING, TestStates.IDLE, TickTimeUtils.ticksFromMayaFrames(3));



    public PlayerPartAnimator(){
        super();
    }

    // Building the locator rig
    @Override
    protected LocatorSkeleton<ModelPartLocators> buildRig() {
        return LocatorSkeleton.of(ModelPartLocators.root)
                .addChildLocator(ModelPartLocators.body)
                .addChildLocator(ModelPartLocators.cape)
                .addChildLocator(ModelPartLocators.leftArm)
                .addChildLocator(ModelPartLocators.rightArm)
                .addChildLocator(ModelPartLocators.leftLeg)
                .addChildLocator(ModelPartLocators.rightLeg)
                .addChildLocator(ModelPartLocators.head)
                .setLocatorModelPart(ModelPartLocators.head, MODEL_PART_HEAD)
                .setLocatorModelPart(ModelPartLocators.leftArm, MODEL_PART_LEFT_ARM)
                .setLocatorModelPart(ModelPartLocators.rightArm, MODEL_PART_RIGHT_ARM)
                .setLocatorModelPart(ModelPartLocators.leftLeg, MODEL_PART_LEFT_LEG)
                .setLocatorModelPart(ModelPartLocators.rightLeg, MODEL_PART_RIGHT_LEG)
                .setLocatorModelPart(ModelPartLocators.body, MODEL_PART_BODY)
                .setLocatorModelPart(ModelPartLocators.cape, MODEL_PART_CAPE)
                .setLocatorDefaultPose(ModelPartLocators.leftLeg, PartPose.offset(1.9f, 12.0f, 0.0f))
                .setLocatorDefaultPose(ModelPartLocators.rightLeg, PartPose.offset(-1.9f, 12.0f, 0.0f))
                .setLocatorDefaultPose(ModelPartLocators.leftArm, PartPose.offset(5.0f, 2.0f, 0.0f))
                .setLocatorDefaultPose(ModelPartLocators.rightArm, PartPose.offset(-5.0f, 2.0f, 0.0f))
                .setLocatorDefaultPose(ModelPartLocators.cape, PartPose.offsetAndRotation(0, 0, 2, 0, Mth.PI, 0))
                .setLocatorMirror(ModelPartLocators.leftArm, ModelPartLocators.rightArm)
                .setLocatorMirror(ModelPartLocators.rightArm, ModelPartLocators.leftArm)
                .setLocatorMirror(ModelPartLocators.leftLeg, ModelPartLocators.rightLeg)
                .setLocatorMirror(ModelPartLocators.rightLeg, ModelPartLocators.leftLeg);
        /*
        locatorRig.addLocator(LOCATOR_ROOT);
        locatorRig.addLocatorModelPart(LOCATOR_HEAD, "head");
        locatorRig.addLocatorModelPart(LOCATOR_BODY, "body");
        locatorRig.addLocatorModelPart(LOCATOR_LEFT_LEG, LOCATOR_RIGHT_LEG, "left_leg", PartPose.offset(1.9f, 12.0f, 0.0f));
        locatorRig.addLocatorModelPart(LOCATOR_RIGHT_LEG, LOCATOR_LEFT_LEG, "right_leg", PartPose.offset(-1.9f, 12.0f, 0.0f));
        locatorRig.addLocatorModelPart(LOCATOR_LEFT_ARM, LOCATOR_RIGHT_ARM, "left_arm", PartPose.offset(5.0f, 2.0f, 0.0f));
        locatorRig.addLocatorModelPart(LOCATOR_RIGHT_ARM, LOCATOR_LEFT_ARM, "right_arm", PartPose.offset(-5.0f, 2.0f, 0.0f));
        locatorRig.addLocatorModelPart(LOCATOR_CAPE, "cloak", PartPose.offsetAndRotation(0, 0, 2, 0, Mth.PI, 0));
        locatorRig.addLocator(LOCATOR_LEFT_HAND, LOCATOR_RIGHT_HAND);
        locatorRig.addLocator(LOCATOR_RIGHT_HAND, LOCATOR_LEFT_HAND);

         */
    }

    // Ticking every sampleable animation state, in this case updating the state machine conditions
    @Override
    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData) {

        boolean isWalking = this.getWalkAnimationSpeed() > 0.1;
        getAnimationState(TEST_STATE_MACHINE)
                .setTransitionCondition(TestStates.IDLE, TestStates.WALKING, isWalking)
                .setTransitionCondition(TestStates.WALKING, TestStates.IDLE, !isWalking);
        //AnimationOverhaulMain.LOGGER.info(this.getAnimationState(ANIM_TEST_IDLE_SEQUENCE_PLAYER).getPlayRate());

    }

    // This is the function for getting the final pose every tick
    @Override
    protected AnimationPose<ModelPartLocators> calculatePose() {

        // Set the poses for each state in the machine, sampling each simple sequence player animation state
        getAnimationState(TEST_STATE_MACHINE)
                .setPose(TestStates.IDLE, sampleAnimationState(ANIM_TEST_IDLE_SEQUENCE_PLAYER))
                .setPose(TestStates.WALKING, sampleAnimationState(ANIM_TEST_WALK_SEQUENCE_PLAYER));

        // Sample the state machine animation state into a pose variable
        return sampleAnimationState(TEST_STATE_MACHINE);
    }

    // Post-processing on the animation, copying stuff to the second layer and whatnot
    @Override
    protected void finalizeModelParts(ModelPart rootModelPart) {
        rootModelPart.getChild("left_pants").copyFrom(rootModelPart.getChild("left_leg"));
        rootModelPart.getChild("right_pants").copyFrom(rootModelPart.getChild("right_leg"));
        rootModelPart.getChild("left_sleeve").copyFrom(rootModelPart.getChild("left_arm"));
        rootModelPart.getChild("right_sleeve").copyFrom(rootModelPart.getChild("right_arm"));
        rootModelPart.getChild("jacket").copyFrom(rootModelPart.getChild("body"));
        rootModelPart.getChild("hat").copyFrom(rootModelPart.getChild("head"));
        rootModelPart.getChild("cloak").xRot *= -1F;
        // Removes the vanilla transformation done for the crouch pose
        if(this.entityModel.crouching){
            for(ModelPart modelPart : rootModelPart.getAllParts().toList()){
                modelPart.y -= 2;
            }
        }
    }
}
