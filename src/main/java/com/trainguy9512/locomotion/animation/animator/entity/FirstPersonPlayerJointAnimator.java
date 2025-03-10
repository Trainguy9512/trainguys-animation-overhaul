package com.trainguy9512.locomotion.animation.animator.entity;

import com.mojang.math.Axis;
import com.trainguy9512.locomotion.animation.data.*;
import com.trainguy9512.locomotion.animation.data.driver.Driver;
import com.trainguy9512.locomotion.animation.data.key.AnimationDriverKey;
import com.trainguy9512.locomotion.animation.pose.AnimationPose;
import com.trainguy9512.locomotion.animation.joint.JointChannel;
import com.trainguy9512.locomotion.animation.pose.LocalSpacePose;
import com.trainguy9512.locomotion.animation.pose.function.*;
import com.trainguy9512.locomotion.animation.pose.function.cache.SavedCachedPoseContainer;
import com.trainguy9512.locomotion.animation.joint.JointSkeleton;
import com.trainguy9512.locomotion.util.Easing;
import com.trainguy9512.locomotion.util.TimeSpan;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

import java.util.Random;
import java.util.Set;

public class FirstPersonPlayerJointAnimator implements LivingEntityJointAnimator<LocalPlayer, PlayerRenderState> {

    public static final String ROOT_JOINT = "root_jnt";
    public static final String CAMERA_JOINT = "camera_jnt";
    public static final String ARM_BUFFER_JOINT = "arm_buffer_jnt";
    public static final String RIGHT_ARM_BUFFER_JOINT = "arm_R_buffer_jnt";
    public static final String RIGHT_ARM_JOINT = "arm_R_jnt";
    public static final String RIGHT_HAND_JOINT = "hand_R_jnt";
    public static final String RIGHT_ITEM_JOINT = "item_R_jnt";
    public static final String LEFT_ARM_BUFFER_JOINT = "arm_L_buffer_jnt";
    public static final String LEFT_ARM_JOINT = "arm_L_jnt";
    public static final String LEFT_HAND_JOINT = "hand_L_jnt";
    public static final String LEFT_ITEM_JOINT = "item_L_jnt";

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
    public static final AnimationDriverKey<Float> BLEND_TEST = AnimationDriverKey.driverKeyOf("time_test", () -> Driver.floatDriver(() -> 0f));

    public static final AnimationDriverKey<Vector3f> CAMERA_ROTATION = AnimationDriverKey.driverKeyOf("camera_rotation", () -> Driver.vectorDriver(() -> new Vector3f(0)));
    public static final AnimationDriverKey<Vector3f> DAMPENED_CAMERA_ROTATION = AnimationDriverKey.driverKeyOf("dampened_camera_rotation", () -> Driver.vectorDriver(() -> new Vector3f(0)));
    public static final AnimationDriverKey<ItemStack> MAIN_HAND_ITEM = AnimationDriverKey.driverKeyOf("main_hand_item", () -> Driver.ofConstant(() -> ItemStack.EMPTY));
    public static final AnimationDriverKey<ItemStack> OFF_HAND_ITEM = AnimationDriverKey.driverKeyOf("off_hand_item", () -> Driver.ofConstant(() -> ItemStack.EMPTY));
    public static final AnimationDriverKey<Boolean> IS_ATTACKING = AnimationDriverKey.driverKeyOf("is_attacking", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Boolean> IS_USING_ITEM = AnimationDriverKey.driverKeyOf("is_using_item", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Boolean> IS_MINING = AnimationDriverKey.driverKeyOf("is_mining", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Boolean> IS_FALLING = AnimationDriverKey.driverKeyOf("is_falling", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Boolean> IS_JUMPING = AnimationDriverKey.driverKeyOf("is_jumping", () -> Driver.booleanDriver(() -> false));
    public static final AnimationDriverKey<Float> WALK_SPEED = AnimationDriverKey.driverKeyOf("walk_speed", () -> Driver.floatDriver(() -> 0f));

    @Override
    public void postProcessModelParts(EntityModel<PlayerRenderState> entityModel, PlayerRenderState entityRenderState) {

    }


    public static final ResourceLocation ROM_TEST = AnimationSequenceData.getNativeResourceLocation(AnimationSequenceData.FIRST_PERSON_PLAYER_PATH, "rom_test");
    public static final ResourceLocation POSE_TEST = AnimationSequenceData.getNativeResourceLocation(AnimationSequenceData.FIRST_PERSON_PLAYER_PATH, "pose_test");


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
                .addJointUnderParent(LEFT_ITEM_JOINT, LEFT_HAND_JOINT)
                .addJointUnderParent(RIGHT_ITEM_JOINT, RIGHT_HAND_JOINT)
                .setMirrorJoint(RIGHT_ARM_BUFFER_JOINT, LEFT_ARM_BUFFER_JOINT)
                .setMirrorJoint(RIGHT_ARM_JOINT, LEFT_ARM_JOINT)
                .setMirrorJoint(RIGHT_HAND_JOINT, LEFT_HAND_JOINT)
                .setMirrorJoint(RIGHT_ITEM_JOINT, LEFT_ITEM_JOINT)
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
        PoseFunction<LocalSpacePose> testSequencePlayer = SequencePlayerFunction.builder(POSE_TEST).setLooping(true).setPlayRate((context) -> 1f).build();
        PoseFunction<LocalSpacePose> movingSequencePlayer = SequencePlayerFunction.builder(ROM_TEST).setLooping(true).setPlayRate((context) -> 1f).build();
        //cachedPoseContainer.register("TEST_SEQ_PLAYER", testSequencePlayer);


        PoseFunction<LocalSpacePose> testTransformer = LocalConversionFunction.of(
                JointTransformerFunction.componentSpaceBuilder(ComponentConversionFunction.of(testSequencePlayer), RIGHT_ARM_JOINT)
                        .setTranslation(
                                context -> new Vector3f(Mth.sin(context.gameTimeSeconds() * 8f) * 0f, 0, 0),
                                JointChannel.TransformType.ADD,
                                JointChannel.TransformSpace.COMPONENT
                        )
                        .setRotation(
                                context -> Axis.XP.rotationDegrees(0f),
                                JointChannel.TransformType.ADD,
                                JointChannel.TransformSpace.COMPONENT
                        )
                        .setScale(
                                context -> new Vector3f(1, 1, 1),
                                JointChannel.TransformType.ADD,
                                JointChannel.TransformSpace.COMPONENT
                        )
                        .setWeight(context -> 1f)
                        .build());

        PoseFunction<LocalSpacePose> testStateMachine = StateMachineFunction.builder(TestStates.values())
                .addState(TestStates.IDLE, testTransformer, true,
                        StateMachineFunction.StateTransition.builder(TestStates.MOVING, transitionContext -> transitionContext.dataContainer().getDriverValue(WALK_SPEED) >= 0.5f).setTransitionDuration(TimeSpan.of24FramesPerSecond(6)).setEasing(Easing.SINE_IN_OUT).build()
                )
                .addState(TestStates.MOVING, movingSequencePlayer, true,
                        StateMachineFunction.StateTransition.builder(TestStates.IDLE, transitionContext -> transitionContext.dataContainer().getDriverValue(WALK_SPEED) < 0.5f).setTransitionDuration(TimeSpan.of24FramesPerSecond(10)).setEasing(Easing.easeOut(Easing.Elastic.easeInOf(6, 3))).build()
                )
                .build();

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

        JointChannel jointChannel = pose.getJointTransform(ARM_BUFFER_JOINT);
        jointChannel.rotate(
                new Vector3f(
                        (dampenedCameraRotation.x() - cameraRotation.x()) * (cameraDampWeight.x() * 0.01F),
                        (dampenedCameraRotation.y() - cameraRotation.y()) * (cameraDampWeight.y() * 0.01F),
                        (dampenedCameraRotation.z() - cameraRotation.z()) * (cameraDampWeight.z() * 0.01F)
                ),
                JointChannel.TransformSpace.COMPONENT, JointChannel.TransformType.ADD
        );
        pose.setJointTransform(ARM_BUFFER_JOINT, jointChannel);
    }


    @Override
    public void extractAnimationData(LocalPlayer dataReference, OnTickDataContainer driverContainer){

        driverContainer.loadValueIntoDriver(WALK_SPEED, dataReference.walkAnimation.speed());
        driverContainer.loadValueIntoDriver(TIME_TEST, driverContainer.getPreviousDriverValue(TIME_TEST) + 1);
        driverContainer.loadValueIntoDriver(MAIN_HAND_ITEM, dataReference.getMainHandItem());
        driverContainer.loadValueIntoDriver(OFF_HAND_ITEM, dataReference.getOffhandItem());


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
