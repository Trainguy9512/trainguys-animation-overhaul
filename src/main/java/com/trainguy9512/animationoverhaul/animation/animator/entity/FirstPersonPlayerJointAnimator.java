package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.animation.data.*;
import com.trainguy9512.animationoverhaul.animation.data.driver.Driver;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDriverKey;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import com.trainguy9512.animationoverhaul.animation.pose.ComponentSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.function.*;
import com.trainguy9512.animationoverhaul.animation.pose.function.cache.SavedCachedPoseContainer;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

import java.util.Random;
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

    public static final AnimationDriverKey<Float> TIME_TEST = AnimationDriverKey.driverKeyOf("time_test", () -> Driver.floatDriver(() -> 0f));

    public static final AnimationDriverKey<Vector3f> CAMERA_ROTATION = AnimationDriverKey.driverKeyOf("camera_rotation", () -> Driver.vectorDriver(() -> new Vector3f(0)));
    public static final AnimationDriverKey<Vector3f> DAMPENED_CAMERA_ROTATION = AnimationDriverKey.driverKeyOf("dampened_camera_rotation", () -> Driver.vectorDriver(() -> new Vector3f(0)));
    public static final AnimationDriverKey<ItemStack> MAIN_HAND_ITEM = AnimationDriverKey.driverKeyOf("main_hand_item", () -> Driver.ofConstant(() -> ItemStack.EMPTY));
    public static final AnimationDriverKey<Boolean> IS_ATTACKING = AnimationDriverKey.driverKeyOf("is_attacking", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Boolean> IS_USING_ITEM = AnimationDriverKey.driverKeyOf("is_using_item", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Boolean> IS_MINING = AnimationDriverKey.driverKeyOf("is_mining", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Boolean> IS_FALLING = AnimationDriverKey.driverKeyOf("is_falling", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Boolean> IS_JUMPING = AnimationDriverKey.driverKeyOf("is_jumping", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Float> WALK_SPEED = AnimationDriverKey.driverKeyOf("walk_speed", () -> Driver.floatDriver(() -> 0f));

    @Override
    public void postProcessModelParts(EntityModel<PlayerRenderState> entityModel, PlayerRenderState entityRenderState) {

    }


    public static final ResourceLocation ANIMATION_FP_PLAYER_IDLE = AnimationSequenceData.getNativeResourceLocation(AnimationSequenceData.FIRST_PERSON_PLAYER_KEY, "fp_player_idle");


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
    public PoseCalculationFrequency getPoseCalulationFrequency() {
        return PoseCalculationFrequency.CALCULATE_EVERY_FRAME;
    }

    public enum TestStates {
        IDLE,
        MOVING
    }

    @Override
    public PoseFunction<LocalSpacePose> constructPoseFunction(SavedCachedPoseContainer cachedPoseContainer) {
        Random random = new Random();
        PoseFunction<LocalSpacePose> testSequencePlayer = SequencePlayerFunction.builder(ANIMATION_FP_PLAYER_IDLE)
                .setLooping(true)
                .setPlayRate((context) -> random.nextFloat(3))
                .build();
        //cachedPoseContainer.register("TEST_SEQ_PLAYER", testSequencePlayer);


        PoseFunction<LocalSpacePose> testTransformer = LocalPoseConversionFunction.of(
                JointTransformerFunction.componentSpaceBuilder(ComponentPoseConversionFunction.of(testSequencePlayer), LEFT_ARM_JOINT)
                .setTranslationConfiguration(JointTransformerFunction.TransformChannelConfiguration.of(
                        (context) -> new Vector3f((float) Math.sin(context.gameTime() * 8f) * 0.6f, 0, 0),
                        JointTransform.TransformType.ADD,
                        JointTransform.TransformSpace.COMPONENT))
                .build());

        //PoseFunction<LocalSpacePose> cached = cachedPoseContainer.getOrThrow("TEST_SEQ_PLAYER");
        PoseFunction<LocalSpacePose> blendMultipleFunction = BlendMultipleFunction.builder(testSequencePlayer).addBlendInput(testSequencePlayer, (evaluationState) -> 0.5f).build();

        return blendMultipleFunction;
    }



    /*
    Get the pose with the added dampened camera rotation
     */
    private void dampenArmRotation(AnimationPose pose, PoseCalculationDataContainer dataContainer, float partialTicks){
        Vector3f cameraRotation = dataContainer.getDriverValueInterpolated(CAMERA_ROTATION, partialTicks);
        Vector3f dampenedCameraRotation = dataContainer.getDriverValueInterpolated(DAMPENED_CAMERA_ROTATION, partialTicks);

        Vector3f cameraDampWeight = new Vector3f(0.6F, 0.3F, 0.1F);

        JointTransform jointTransform = pose.getJointTransform(ARM_BUFFER_JOINT);
        jointTransform.rotate(
                new Vector3f(
                        (dampenedCameraRotation.x() - cameraRotation.x()) * (cameraDampWeight.x() * 0.01F),
                        (dampenedCameraRotation.y() - cameraRotation.y()) * (cameraDampWeight.y() * 0.01F),
                        (dampenedCameraRotation.z() - cameraRotation.z()) * (cameraDampWeight.z() * 0.01F)
                ),
                JointTransform.TransformSpace.COMPONENT, JointTransform.TransformType.ADD
        );
        pose.setJointTransform(ARM_BUFFER_JOINT, jointTransform);
    }


    @Override
    public void extractAnimationData(LocalPlayer dataReference, OnTickDataContainer driverContainer){

        driverContainer.loadValueIntoDriver(WALK_SPEED, dataReference.walkAnimation.speed());
        driverContainer.loadValueIntoDriver(TIME_TEST, driverContainer.getPreviousDriverValue(TIME_TEST) + 1);
        driverContainer.loadValueIntoDriver(MAIN_HAND_ITEM, dataReference.getMainHandItem());


        //Tick the dampened camera rotation.
        Vector3f dampenSpeed = new Vector3f(0.5F, 0.5F, 0.2F);

        // First, set the target camera rotation from the living entity.
        Vector3f targetRotation = new Vector3f(dataReference.getXRot(), dataReference.getYRot(), dataReference.getYRot());
        driverContainer.loadValueIntoDriver(CAMERA_ROTATION, targetRotation);


        Vector3f dampenedCameraRotation = new Vector3f(driverContainer.getPreviousDriverValue(DAMPENED_CAMERA_ROTATION));

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
        driverContainer.loadValueIntoDriver(DAMPENED_CAMERA_ROTATION, dampenedCameraRotation);

    }
}
