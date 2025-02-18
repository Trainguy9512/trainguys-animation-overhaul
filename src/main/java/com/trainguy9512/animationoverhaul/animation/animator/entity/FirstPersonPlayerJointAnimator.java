package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.animation.data.*;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.*;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.notify.NotifyListeners;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

import java.util.Set;

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

    public static final Set<String> ARM_BUFFER_JOINTS = Set.of(
            RIGHT_ARM_BUFFER_JOINT,
            LEFT_ARM_BUFFER_JOINT
    );

    public static final Set<String> ARM_JOINTS = Set.of(
            RIGHT_ARM_JOINT,
            LEFT_ARM_JOINT,
            RIGHT_ARM_BUFFER_JOINT,
            LEFT_ARM_BUFFER_JOINT,
            RIGHT_HAND_JOINT,
            LEFT_HAND_JOINT
    );

    public static final Set<String> ARM_POSE_JOINTS = Set.of(
            RIGHT_ARM_JOINT,
            LEFT_ARM_JOINT
    );

    public static final Set<String> HAND_JOINTS = Set.of(
            RIGHT_HAND_JOINT,
            LEFT_HAND_JOINT
    );

    public static final AnimationDataKey<Float> TIME_TEST = AnimationDataKey.of("time_test", () -> 0f);

    public static final AnimationDataKey<Vector3f> CAMERA_ROTATION = AnimationDataKey.of("camera rotation", () -> new Vector3f(0));
    public static final AnimationDataKey<Vector3f> DAMPENED_CAMERA_ROTATION = AnimationDataKey.of("dampened_camera", () -> new Vector3f(0));
    public static final AnimationDataKey<ItemStack> MAIN_HAND_ITEM = AnimationDataKey.of("main_hand_item", () -> ItemStack.EMPTY);
    public static final AnimationDataKey<Boolean> IS_ATTACKING = AnimationDataKey.of("is_attacking", () -> false);
    public static final AnimationDataKey<Boolean> IS_USING_ITEM = AnimationDataKey.of("is_using_item", () -> false);
    public static final AnimationDataKey<Boolean> IS_MINING = AnimationDataKey.of("is_mining", () -> false);
    public static final AnimationDataKey<Boolean> IS_FALLING = AnimationDataKey.of("is_falling", () -> false);
    public static final AnimationDataKey<Boolean> IS_JUMPING = AnimationDataKey.of("is_jumping", () -> false);
    public static final AnimationDataKey<Float> WALK_SPEED = AnimationDataKey.of("walk_speed", () -> 0f);

    @Override
    public void postProcessModelParts(EntityModel<PlayerRenderState> entityModel, PlayerRenderState entityRenderState) {

    }

    public enum TestStates {
        IDLE,
        MOVING
    }


    public static final ResourceLocation ANIMATION_FP_PLAYER_IDLE = AnimationSequenceData.getNativeResourceLocation(AnimationSequenceData.FIRST_PERSON_PLAYER_KEY, "fp_player_idle");
    public static final AnimationDataKey<AnimationSequencePlayer> IDLE_SEQUENCE_PLAYER_FROZEN = AnimationDataKey.of("idle_sequence_player", () -> AnimationSequencePlayer.builder(ANIMATION_FP_PLAYER_IDLE)
            .setPlayRate(0)
            .setStartTime(0)
            .build()
    );
    public static final AnimationDataKey<AnimationSequencePlayer> IDLE_SEQUENCE_PLAYER = AnimationDataKey.of("idle_sequence_player", () -> AnimationSequencePlayer.builder(ANIMATION_FP_PLAYER_IDLE)
            .setPlayRate(0)
            .setStartTime(40)
            .build()
    );


    public static final AnimationDataKey<AnimationStateMachine<TestStates>> TEST_STATE_MACHINE = AnimationDataKey.of("test_state_machine", () -> AnimationStateMachine.builder(TestStates.values())
                    .addState(TestStates.IDLE,
                            ((animationDriverContainer, poseSamplerStateContainer, jointSkeleton) -> poseSamplerStateContainer.sample(IDLE_SEQUENCE_PLAYER_FROZEN, animationDriverContainer)),
                            AnimationStateMachine.StateTransition.builder(TestStates.MOVING, ((animationDriverContainer, ticksElapsedInCurrentState) -> animationDriverContainer.get(WALK_SPEED) > 0.1F))
                                    .setTransitionDuration(20)
                                    .setEasing(Easing.BOUNCE_OUT)
                                    .build())
                    .addState(TestStates.MOVING,
                            ((animationDriverContainer, poseSamplerStateContainer, jointSkeleton) -> poseSamplerStateContainer.sample(IDLE_SEQUENCE_PLAYER, animationDriverContainer)),
                            AnimationStateMachine.StateTransition.builder(TestStates.IDLE, ((animationDriverContainer, ticksElapsedInCurrentState) -> animationDriverContainer.get(WALK_SPEED) <= 0.1F))
                                    .setTransitionDuration(20)
                                    .setEasing(Easing.ELASTIC_OUT)
                                    .build())
                    .bindNotifyToOnStateRelevant(TestStates.MOVING, NotifyListeners.resetTimeNotifyListener(IDLE_SEQUENCE_PLAYER))
                    .build()
            );


    public JointSkeleton buildSkeleton() {
        return JointSkeleton.of(ROOT_JOINT)
                .addJointUnderRoot(CAMERA_JOINT)
                .addJointUnderRoot(ARM_BUFFER_JOINT)
                .addJointUnderParent(LEFT_ARM_BUFFER_JOINT, ARM_BUFFER_JOINT)
                .addJointUnderParent(RIGHT_ARM_BUFFER_JOINT, ARM_BUFFER_JOINT)
                .addJointUnderParent(LEFT_ARM_JOINT, LEFT_ARM_BUFFER_JOINT)
                .addJointUnderParent(RIGHT_ARM_JOINT, RIGHT_ARM_BUFFER_JOINT)
                .addJointUnderParent(LEFT_HAND_JOINT, LEFT_ARM_JOINT)
                .addJointUnderParent(RIGHT_HAND_JOINT, RIGHT_ARM_JOINT)
                .setModelPartOffset(LEFT_HAND_JOINT, PartPose.offsetAndRotation(1, 10, -2, -Mth.HALF_PI, 0, Mth.PI))
                .setModelPartOffset(RIGHT_HAND_JOINT, PartPose.offsetAndRotation(-1, 10, -2, -Mth.HALF_PI, 0, Mth.PI))
                .setMirrorJoint(RIGHT_ARM_BUFFER_JOINT, LEFT_ARM_BUFFER_JOINT)
                .setMirrorJoint(RIGHT_ARM_JOINT, LEFT_ARM_JOINT)
                .setMirrorJoint(RIGHT_HAND_JOINT, LEFT_HAND_JOINT)
                .build();

    }

    @Override
    public AnimationPose calculatePose(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton) {
        // Update main hand item based on the anim notify
        //animationDataContainer.getAnimationVariable(MAIN_HAND_ITEM).set(localPlayer.getMainHandItem().copy());


        //setEntityAnimationVariable(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem().copy());

        AnimationPose pose = poseSamplerStateContainer.sample(TEST_STATE_MACHINE, animationDriverContainer);


        dampenArmRotation(pose, animationDriverContainer);


        Vector3f rotation = new Vector3f(Mth.sin(animationDriverContainer.get(TIME_TEST) * 0.2F) * Mth.HALF_PI * 0.7f, 0, 0);
        //Vector3f translation = new Vector3f(Mth.sin(getEntityAnimationVariable(TIME_TEST) * 1.3F) * 3F, 0, 0);
        //pose.translateJoint(FPPlayerLocators.rightArm, translation, AnimationPose.TransformSpace.ENTITY, false);
        //pose.rotateJoint(FPPlayerLocators.rightArm, rotation, AnimationPose.TransformSpace.ENTITY, false);


        return pose;
    }

    /*
    Get the pose with the added dampened camera rotation
     */
    private void dampenArmRotation(AnimationPose pose, AnimationDriverContainer animationDriverContainer){
        Vector3f cameraRotation = animationDriverContainer.get(CAMERA_ROTATION);
        Vector3f dampenedCameraRotation = animationDriverContainer.get(DAMPENED_CAMERA_ROTATION);

        Vector3f cameraDampWeight = new Vector3f(0.6F, 0.3F, 0.1F);

        JointTransform jointTransform = pose.getJointTransform(ARM_BUFFER_JOINT);
        jointTransform.rotate(
                new Vector3f(
                        (dampenedCameraRotation.x() - cameraRotation.x()) * (cameraDampWeight.x() * 0.01F),
                        (dampenedCameraRotation.y() - cameraRotation.y()) * (cameraDampWeight.y() * 0.01F),
                        (dampenedCameraRotation.z() - cameraRotation.z()) * (cameraDampWeight.z() * 0.01F)
                ),
                JointTransform.TransformSpace.ENTITY
        );
        pose.setJointTransform(ARM_BUFFER_JOINT, jointTransform);
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
