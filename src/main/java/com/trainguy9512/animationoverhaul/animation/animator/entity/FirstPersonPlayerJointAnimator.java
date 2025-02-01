package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.animation.data.*;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointTransform;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

import java.util.List;

public class FirstPersonPlayerJointAnimator implements LivingEntityJointAnimator<LocalPlayer, PlayerRenderState> {

    public static final String ROOT_JOINT = "root";
    public static final String CAMERA_JOINT = "camera";
    public static final String ARM_BUFFER_JOINT = "armBuffer";
    public static final String RIGHT_ARM_BUFFER_JOINT = "rightArmBuffer";
    public static final String LEFT_ARM_BUFFER_JOINT = "leftArmBuffer";
    public static final String RIGHT_ARM_JOINT = "rightArm";
    public static final String LEFT_ARM_JOINT = "leftArm";
    public static final String RIGHT_HAND_JOINT = "rightHand";
    public static final String LEFT_HAND_JOINT = "leftHand";

    public static final List<String> ARM_BUFFER_JOINTS = List.of(
            RIGHT_ARM_BUFFER_JOINT,
            LEFT_ARM_BUFFER_JOINT
    );

    public static final List<String> ARM_JOINTS = List.of(
            RIGHT_ARM_JOINT,
            LEFT_ARM_JOINT,
            RIGHT_ARM_BUFFER_JOINT,
            LEFT_ARM_BUFFER_JOINT,
            RIGHT_HAND_JOINT,
            LEFT_HAND_JOINT
    );

    public static final List<String> ARM_POSE_JOINTS = List.of(
            RIGHT_ARM_JOINT,
            LEFT_ARM_JOINT
    );

    public static final List<String> HAND_JOINTS = List.of(
            RIGHT_HAND_JOINT,
            LEFT_HAND_JOINT
    );


    public static final ResourceLocation ANIMATION_FP_PLAYER_IDLE = AnimationSequenceData.getNativeResourceLocation(AnimationSequenceData.FIRST_PERSON_PLAYER_KEY, "fp_player_idle");


    public static final AnimationDriverKey<Float> TIME_TEST = AnimationDriverKey.builder(() -> 0F).setIdentifier("time_test").build();



    public static final AnimationDriverKey<Vector3f> CAMERA_ROTATION = AnimationDriverKey.builder(() -> new Vector3f(0, 0, 0)).setIdentifier("camera_rotation").build();
    public static final AnimationDriverKey<Vector3f> DAMPENED_CAMERA_ROTATION = AnimationDriverKey.builder(() -> new Vector3f(0, 0, 0)).setIdentifier("dampened_camera_rotation").build();
    public static final AnimationDriverKey<ItemStack> MAIN_HAND_ITEM = AnimationDriverKey.builder(() -> ItemStack.EMPTY).setIdentifier("main_hand_item_stack").build();
    public static final AnimationDriverKey<Boolean> IS_ATTACKING = AnimationDriverKey.builder(() -> false).setIdentifier("is_attacking").build();
    public static final AnimationDriverKey<Boolean> IS_USING_ITEM = AnimationDriverKey.builder(() -> false).setIdentifier("is_using_item").build();
    public static final AnimationDriverKey<Boolean> IS_MINING = AnimationDriverKey.builder(() -> false).setIdentifier("is_mining").build();
    public static final AnimationDriverKey<Boolean> IS_FALLING = AnimationDriverKey.builder(() -> false).setIdentifier("is_falling").build();
    public static final AnimationDriverKey<Boolean> IS_JUMPING = AnimationDriverKey.builder(() -> false).setIdentifier("is_jumping").build();
    public static final AnimationDriverKey<Float> WALK_SPEED = AnimationDriverKey.builder(() -> 0f).setIdentifier("walk_speed").build();

    public enum TestStates {
        IDLE,
        MOVING
    }

    public static final PoseSamplerKey<AnimationStateMachine<TestStates>> TEST_STATE_MACHINE = PoseSamplerKey.builder(() -> AnimationStateMachine.of(TestStates.values())
            .addStateTransition(TestStates.IDLE, TestStates.MOVING, AnimationStateMachine.StateTransition.builder(
                            animationDataContainer -> animationDataContainer.get(WALK_SPEED) > 0.1F)
                    .setTransitionDuration(5)
                    .setEasing(Easing.CubicBezier.SINE_IN_OUT())
                    .build())
            .addStateTransition(TestStates.MOVING, TestStates.IDLE, AnimationStateMachine.StateTransition.builder(
                            animationDataContainer -> animationDataContainer.get(WALK_SPEED) <= 0.1F)
                    .setTransitionDuration(10)
                    .setEasing(Easing.CubicBezier.SINE_OUT())
                    .build())
            .build()).build();

    public static final PoseSamplerKey<AnimationSequencePlayer> IDLE_SEQUENCE_PLAYER = PoseSamplerKey.builder(
            () -> AnimationSequencePlayer.of(ANIMATION_FP_PLAYER_IDLE)
            .setPlayRate(0)
            .setStartTime(0)
            .build()).setIdentifier("idle_sequence_player").build();
    public static final PoseSamplerKey<AnimationSequencePlayer> IDLE_SEQUENCE_PLAYER_ALT = PoseSamplerKey.builder(
            () -> AnimationSequencePlayer.of(ANIMATION_FP_PLAYER_IDLE)
            .setPlayRate(1)
            .setStartTime(20)
            .addProgressTimeOnActiveStates(TEST_STATE_MACHINE, TestStates.MOVING)
            .build()).setIdentifier("idle_sequence_player").build();

    @Override
    public void postProcessModelParts(PlayerRenderState entityRenderState, ModelPart rootModelPart) {

    }







    public FirstPersonPlayerJointAnimator(){
        super();
    }

    protected JointSkeleton.Builder buildSkeleton() {
        return JointSkeleton.of()
        return JointSkeleton.of(FPPlayerJoints.root)
                .addChildToRoot(FPPlayerJoints.camera)
                .addChildToRoot(FPPlayerJoints.armBuffer)
                .addChildToParent(FPPlayerJoints.leftArmBuffer, FPPlayerJoints.armBuffer)
                .addChildToParent(FPPlayerJoints.rightArmBuffer, FPPlayerJoints.armBuffer)
                .addChildToParent(FPPlayerJoints.leftArm, FPPlayerJoints.leftArmBuffer)
                .addChildToParent(FPPlayerJoints.rightArm, FPPlayerJoints.rightArmBuffer)
                .addChildToParent(FPPlayerJoints.leftHand, FPPlayerJoints.leftArm)
                .addChildToParent(FPPlayerJoints.rightHand, FPPlayerJoints.rightArm)
                .setDefaultJointTransform(FPPlayerJoints.leftHand, PartPose.offsetAndRotation(1, 10, -2, -Mth.HALF_PI, 0, Mth.PI))
                .setDefaultJointTransform(FPPlayerJoints.rightHand, PartPose.offsetAndRotation(-1, 10, -2, -Mth.HALF_PI, 0, Mth.PI))
                .setLocatorMirror(FPPlayerJoints.rightArm, FPPlayerJoints.leftArm)
                .setLocatorMirror(FPPlayerJoints.rightHand, FPPlayerJoints.leftHand);

    }

    @Override
    public AnimationPose calculatePose(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton) {
        // Update main hand item based on the anim notify
        //animationDataContainer.getAnimationVariable(MAIN_HAND_ITEM).set(localPlayer.getMainHandItem().copy());


        //setEntityAnimationVariable(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem().copy());

        AnimationPose pose = poseSamplerStateContainer.sample(TEST_STATE_MACHINE);


        pose = dampenArmRotation(pose, animationDriverContainer);


        Vector3f rotation = new Vector3f(Mth.sin(animationDriverContainer.get(TIME_TEST) * 0.2F) * Mth.HALF_PI * 0.7f, 0, 0);
        //Vector3f translation = new Vector3f(Mth.sin(getEntityAnimationVariable(TIME_TEST) * 1.3F) * 3F, 0, 0);
        //pose.translateJoint(FPPlayerLocators.rightArm, translation, AnimationPose.TransformSpace.ENTITY, false);
        //pose.rotateJoint(FPPlayerLocators.rightArm, rotation, AnimationPose.TransformSpace.ENTITY, false);


        return pose;
    }

    /*
    Get the pose with the added dampened camera rotation
     */
    private AnimationPose dampenArmRotation(AnimationPose pose, AnimationDriverContainer animationDriverContainer){
        Vector3f cameraRotation = animationDriverContainer.get(CAMERA_ROTATION);
        Vector3f dampenedCameraRotation = animationDriverContainer.get(DAMPENED_CAMERA_ROTATION);

        Vector3f cameraDampWeight = new Vector3f(0.6F, 0.3F, 0.1F);

        pose.setJointTransform(
                ARM_BUFFER_JOINT,
                pose.getJointTransform(ARM_BUFFER_JOINT).rotate(
                        new Vector3f(
                                (dampenedCameraRotation.x() - cameraRotation.x()) * (cameraDampWeight.x() * 0.01F),
                                (dampenedCameraRotation.y() - cameraRotation.y()) * (cameraDampWeight.y() * 0.01F),
                                (dampenedCameraRotation.z() - cameraRotation.z()) * (cameraDampWeight.z() * 0.01F)
                        ),
                        JointTransform.TransformSpace.ENTITY
                ));
        return pose;
    }


    @Override
    public void extractAnimationData(LocalPlayer dataReference, AnimationDriverContainer animationDriverContainer){


        animationDriverContainer.set(WALK_SPEED, dataReference.walkAnimation.speed());
        animationDriverContainer.set(TIME_TEST, animationDriverContainer.get(TIME_TEST) + 1);


        //Tick the dampened camera rotation.
        Vector3f dampenSpeed = new Vector3f(0.5F, 0.5F, 0.2F);

        // First, set the target camera rotation from the living entity.
        Vector3f targetRotation = new Vector3f(dataReference.getXRot(), dataReference.getYRot(), dataReference.getYRot());
        animationDriverContainer.set(CAMERA_ROTATION, targetRotation);


        Vector3f dampenedCameraRotation = animationDriverContainer.get(DAMPENED_CAMERA_ROTATION);

        // If the dampened camera rotation is 0 (which is what it is upon initialization), set it to the target
        if(dampenedCameraRotation.x() == 0F && dampenedCameraRotation.y() == 0F){
            dampenedCameraRotation = targetRotation;
        } else {
            // Lerp the dampened camera rotation towards the normal camera rotation
            dampenedCameraRotation.set(
                    Mth.lerp(dampenSpeed.x(), dampenedCameraRotation.x(), targetRotation.x()),
                    Mth.lerp(dampenSpeed.y(), dampenedCameraRotation.y(), targetRotation.y()),
                    Mth.lerp(dampenSpeed.z(), dampenedCameraRotation.z(), targetRotation.z())
            );
            //dampenedCameraRotation.lerp(targetRotation, 0.5F);
        }
        animationDriverContainer.set(DAMPENED_CAMERA_ROTATION, dampenedCameraRotation);

    }
}
