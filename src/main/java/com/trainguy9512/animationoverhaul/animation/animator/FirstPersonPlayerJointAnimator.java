package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.animator.entity.LivingEntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerKey;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverKey;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationSequenceData;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

import java.util.List;
import java.util.function.BiFunction;

public class FirstPersonPlayerJointAnimator extends LivingEntityJointAnimator<LocalPlayer, PlayerRenderState, PlayerModel> {

    public static FirstPersonPlayerJointAnimator INSTANCE = new FirstPersonPlayerJointAnimator();

    public AnimationDriverContainer localAnimationDriverContainer = new AnimationDriverContainer();
    public BakedAnimationPose<FPPlayerJoints> localBakedPose;


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


    public static final AnimationDriverKey<Float> TIME_TEST = AnimationDriverKey.of(() -> 0F).setIdentifier("time_test").build();



    public static final AnimationDriverKey<Vector3f> CAMERA_ROTATION = AnimationDriverKey.of(() -> new Vector3f(0, 0, 0)).setIdentifier("camera_rotation").build();
    public static final AnimationDriverKey<Vector3f> DAMPENED_CAMERA_ROTATION = AnimationDriverKey.of(() -> new Vector3f(0, 0, 0)).setIdentifier("dampened_camera_rotation").build();
    public static final AnimationDriverKey<ItemStack> MAIN_HAND_ITEM = AnimationDriverKey.of(() -> ItemStack.EMPTY).setIdentifier("main_hand_item_stack").build();
    public static final AnimationDriverKey<Boolean> IS_ATTACKING = AnimationDriverKey.of(() -> false).setIdentifier("is_attacking").build();
    public static final AnimationDriverKey<Boolean> IS_USING_ITEM = AnimationDriverKey.of(() -> false).setIdentifier("is_using_item").build();
    public static final AnimationDriverKey<Boolean> IS_MINING = AnimationDriverKey.of(() -> false).setIdentifier("is_mining").build();
    public static final AnimationDriverKey<Boolean> IS_FALLING = AnimationDriverKey.of(() -> false).setIdentifier("is_falling").build();
    public static final AnimationDriverKey<Boolean> IS_JUMPING = AnimationDriverKey.of(() -> false).setIdentifier("is_jumping").build();
    public static final AnimationDriverKey<Float> WALK_SPEED = AnimationDriverKey.of(() -> 0f).setIdentifier("walk_speed").build();

    public static final PoseSamplerKey<AnimationStateMachine<TestStates>> TEST_STATE_MACHINE = PoseSamplerKey.builder(() -> AnimationStateMachine.of("test_state_machine", TestStates.values())
            .addStateTransition(TestStates.IDLE, TestStates.MOVING, AnimationStateMachine.StateTransition.of(
                            animationDataContainer -> animationDataContainer.getAnimationVariable(WALK_SPEED).get() > 0.1F)
                    .setTransitionTime(5)
                    .setEasing(Easing.CubicBezier.bezierInOutSine())
                    .build())
            .addStateTransition(TestStates.MOVING, TestStates.IDLE, AnimationStateMachine.StateTransition.of(
                            animationDataContainer -> animationDataContainer.getAnimationVariable(WALK_SPEED).get() < 0.1F)
                    .setTransitionTime(10)
                    .setEasing(Easing.CubicBezier.bezierOutSine())
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


    public enum TestStates implements AnimationStateMachine.StateEnum {
        IDLE {
            @Override
            public <L extends Enum<L>> BiFunction<AnimationDriverContainer, JointSkeleton<L>, AnimationPose<L>> getStatePose() {
                return (animationDataContainer, fpPlayerLocatorsJointSkeleton) -> animationDataContainer.getPoseSampler(IDLE_SEQUENCE_PLAYER).sample(fpPlayerLocatorsJointSkeleton);
            }
        },
        MOVING {
            @Override
            public <L extends Enum<L>> BiFunction<AnimationDriverContainer, JointSkeleton<L>, AnimationPose<L>> getStatePose() {
                return (animationDataContainer, fpPlayerLocatorsJointSkeleton) -> animationDataContainer.getPoseSampler(IDLE_SEQUENCE_PLAYER_ALT).sample(fpPlayerLocatorsJointSkeleton);
            }
        }
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
    public AnimationPose<FPPlayerJoints> calculatePose(AnimationDriverContainer animationDriverContainer) {
        // Update main hand item based on the anim notify
        //animationDataContainer.getAnimationVariable(MAIN_HAND_ITEM).set(localPlayer.getMainHandItem().copy());


        //setEntityAnimationVariable(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem().copy());

        AnimationPose<FPPlayerJoints> pose = animationDriverContainer.getPoseSampler(TEST_STATE_MACHINE).sample(this.getJointSkeleton());



        pose = dampenArmRotation(pose, animationDriverContainer);


        Vector3f rotation = new Vector3f(Mth.sin(animationDriverContainer.getAnimationVariable(TIME_TEST).get() * 0.2F) * Mth.HALF_PI * 0.7f, 0, 0);
        //Vector3f translation = new Vector3f(Mth.sin(getEntityAnimationVariable(TIME_TEST) * 1.3F) * 3F, 0, 0);
        //pose.translateJoint(FPPlayerLocators.rightArm, translation, AnimationPose.TransformSpace.ENTITY, false);
        //pose.rotateJoint(FPPlayerLocators.rightArm, rotation, AnimationPose.TransformSpace.ENTITY, false);


        return pose;
    }

    /*
    Get the pose with the added dampened camera rotation
     */
    private AnimationPose<FPPlayerJoints> dampenArmRotation(AnimationPose<FPPlayerJoints> pose, AnimationDriverContainer animationDriverContainer){
        Vector3f cameraRotation = animationDriverContainer.getAnimationVariable(CAMERA_ROTATION).get();
        Vector3f dampenedCameraRotation = animationDriverContainer.getAnimationVariable(DAMPENED_CAMERA_ROTATION).get();

        Vector3f cameraDampWeight = new Vector3f(0.6F, 0.3F, 0.1F);

        pose.setJointTransform(
                FPPlayerJoints.armBuffer,
                pose.getJointTransform(FPPlayerJoints.armBuffer).rotate(
                        new Vector3f(
                                (dampenedCameraRotation.x() - cameraRotation.x()) * (cameraDampWeight.x() * 0.01F),
                                (dampenedCameraRotation.y() - cameraRotation.y()) * (cameraDampWeight.y() * 0.01F),
                                (dampenedCameraRotation.z() - cameraRotation.z()) * (cameraDampWeight.z() * 0.01F)
                        ),
                        AnimationPose.TransformSpace.ENTITY
                ));
        return pose;
    }


    @Override
    public AnimationDriverContainer extractAnimationData(LocalPlayer dataReference, AnimationDriverContainer animationDriverContainer){



        animationDriverContainer.getAnimationVariable(WALK_SPEED).set(this.getWalkAnimationSpeed(dataReference));
        animationDriverContainer.getAnimationVariable(TIME_TEST).set(animationDriverContainer.getAnimationVariable(TIME_TEST).get() + 1);




        //Tick the dampened camera rotation.
        Vector3f dampenSpeed = new Vector3f(0.5F, 0.5F, 0.2F);

        // First, set the target camera rotation from the living entity.
        Vector3f targetRotation = new Vector3f(dataReference.getXRot(), dataReference.getYRot(), dataReference.getYRot());
        animationDriverContainer.getAnimationVariable(CAMERA_ROTATION).set(targetRotation);


        Vector3f dampenedCameraRotation = animationDriverContainer.getAnimationVariable(DAMPENED_CAMERA_ROTATION).get();

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
        animationDriverContainer.getAnimationVariable(DAMPENED_CAMERA_ROTATION).set(dampenedCameraRotation);

    }


    //TODO: Move this elsewhere

    public void tickExternal(){
        LocalPlayer player = Minecraft.getInstance().player;
        AnimationDriverContainer animationDriverContainer = this.localAnimationDriverContainer;

        this.extractAnimationData(player, animationDriverContainer);
        animationDriverContainer.tickAllPoseSamplers();

        if(this.localBakedPose == null){
            this.localBakedPose = new BakedAnimationPose<>(this.jointSkeleton);
        }

        AnimationPose<FPPlayerJoints> animationPose = this.calculatePose(animationDriverContainer);
        if (animationPose == null){
            animationPose = AnimationPose.of(this.jointSkeleton);
        }


        this.localBakedPose.pushPose(animationPose);
    }

    private boolean compareVariableItemStackWithEntityItemStack(AnimationDriverKey<ItemStack> itemStackDataKey, ItemStack entityItemStack, AnimationDriverContainer animationDriverContainer){
        ItemStack currentItemStack = animationDriverContainer.getAnimationVariable(itemStackDataKey).get();
        if(currentItemStack.getItem() != null && entityItemStack.getItem() == null || currentItemStack.getItem() == null && entityItemStack.getItem() != null) {
            return true;
        }
        return currentItemStack.getItem() != entityItemStack.getItem();
    }
}
