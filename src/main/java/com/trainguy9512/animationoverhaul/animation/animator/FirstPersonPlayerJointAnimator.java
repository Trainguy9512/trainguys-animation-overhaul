package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.animator.entity.LivingEntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.data.AnimationPoseSamplerKey;
import com.trainguy9512.animationoverhaul.animation.data.AnimationVariableKey;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.TimelineGroupData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

public class FirstPersonPlayerJointAnimator extends LivingEntityJointAnimator<LocalPlayer, PlayerModel<LocalPlayer>, FirstPersonPlayerJointAnimator.FPPlayerLocators> {

    public static FirstPersonPlayerJointAnimator INSTANCE = new FirstPersonPlayerJointAnimator();

    public AnimationDataContainer localAnimationDataContainer = new AnimationDataContainer();
    public BakedAnimationPose<FPPlayerLocators> localBakedPose;


    public enum FPPlayerLocators {
        root,
        camera,
        armBuffer,
        rightArmBuffer,
        leftArmBuffer,
        rightArm,
        leftArm,
        rightHand,
        leftHand;

        public static final FPPlayerLocators[] arms = new FPPlayerLocators[] {
                rightArm,
                leftArm,
                rightArmBuffer,
                leftArmBuffer,
                rightHand,
                leftHand
        };

        public static final FPPlayerLocators[] armBufferLocators = new FPPlayerLocators[] {
                rightArmBuffer,
                leftArmBuffer
        };

        public static final FPPlayerLocators[] armPoseLocators = new FPPlayerLocators[] {
                rightArm,
                leftArm
        };

        public static final FPPlayerLocators[] handLocators = new FPPlayerLocators[] {
                rightHand,
                leftHand
        };
    }


    public static final ResourceLocation ANIMATION_FP_PLAYER_IDLE = TimelineGroupData.getNativeResourceLocation(TimelineGroupData.FIRST_PERSON_PLAYER_KEY, "fp_player_idle");


    public static final AnimationVariableKey<Float> TIME_TEST = AnimationVariableKey.of(() -> 0F).setIdentifier("time_test").build();



    public static final AnimationVariableKey<Vector3f> CAMERA_ROTATION = AnimationVariableKey.of(() -> new Vector3f(0, 0, 0)).setIdentifier("camera_rotation").build();
    public static final AnimationVariableKey<Vector3f> DAMPENED_CAMERA_ROTATION = AnimationVariableKey.of(() -> new Vector3f(0, 0, 0)).setIdentifier("dampened_camera_rotation").build();
    public static final AnimationVariableKey<ItemStack> MAIN_HAND_ITEM = AnimationVariableKey.of(() -> ItemStack.EMPTY).setIdentifier("main_hand_item_stack").build();
    public static final AnimationVariableKey<Boolean> IS_ATTACKING = AnimationVariableKey.of(() -> false).setIdentifier("is_attacking").build();
    public static final AnimationVariableKey<Boolean> IS_USING_ITEM = AnimationVariableKey.of(() -> false).setIdentifier("is_using_item").build();
    public static final AnimationVariableKey<Boolean> IS_MINING = AnimationVariableKey.of(() -> false).setIdentifier("is_mining").build();
    public static final AnimationVariableKey<Boolean> IS_FALLING = AnimationVariableKey.of(() -> false).setIdentifier("is_falling").build();
    public static final AnimationVariableKey<Boolean> IS_JUMPING = AnimationVariableKey.of(() -> false).setIdentifier("is_jumping").build();

    public static final AnimationPoseSamplerKey<AnimationSequencePlayer> IDLE_SEQUENCE_PLAYER = AnimationPoseSamplerKey.of(() -> AnimationSequencePlayer.of(ANIMATION_FP_PLAYER_IDLE).build()).setIdentifier("idle_sequence_player").build();



    enum TestStates {
        IDLE,
        MOVING
    }






    /*
    enum ItemSwitchStates {
        EMPTY,
        EMPTY_RAISING,
        BASIC_ITEM,
        BASIC_ITEM_RAISING,
        LOWERING
    }
    private static final AnimationStateMachine<ItemSwitchStates> MAINHAND_ITEMSWITCH_STATE_MACHINE = AnimationStateMachine.of("main_hand_state_machine", ItemSwitchStates.values())
            .addStateTransition(ItemSwitchStates.EMPTY, ItemSwitchStates.LOWERING, 2)
            .addStateTransition(ItemSwitchStates.LOWERING, ItemSwitchStates.EMPTY_RAISING, 1, 2)
            .addStateTransition(ItemSwitchStates.EMPTY_RAISING, ItemSwitchStates.EMPTY, 2)
            .addStateTransition(ItemSwitchStates.BASIC_ITEM, ItemSwitchStates.LOWERING, 2)
            .addStateTransition(ItemSwitchStates.LOWERING, ItemSwitchStates.BASIC_ITEM_RAISING, 1, 1)
            .addStateTransition(ItemSwitchStates.BASIC_ITEM_RAISING, ItemSwitchStates.BASIC_ITEM, 2);



    enum JumpingStates {
        JUMPING,
        FALLING,
        LANDING,
        ON_GROUND
    }
    private static final AnimationStateMachine<JumpingStates> JUMP_STATE_MACHINE = AnimationStateMachine.of("jump_state_machine", JumpingStates.values())
            .addStateTransition(JumpingStates.ON_GROUND, JumpingStates.JUMPING, 1, 1)
            .addStateTransition(JumpingStates.ON_GROUND, JumpingStates.FALLING, 3, 2)
            .addStateTransition(JumpingStates.JUMPING, JumpingStates.FALLING, 4, 1)
            .addStateTransition(JumpingStates.JUMPING, JumpingStates.LANDING, 1, 2)
            .addStateTransition(JumpingStates.FALLING, JumpingStates.LANDING, 1, 1)
            .addStateTransition(JumpingStates.LANDING, JumpingStates.JUMPING, 1, 2)
            .addStateTransition(JumpingStates.LANDING, JumpingStates.FALLING, 3, 3)
            .addStateTransition(JumpingStates.LANDING, JumpingStates.ON_GROUND, 4, 1);


    enum MiningStates {
        IDLE,
        BEGIN,
        LOOPING
    }
    private static final AnimationStateMachine<MiningStates> MINING_STATE_MACHINE = AnimationStateMachine.of("mining_state_machine", MiningStates.values())
            .addStateTransition(MiningStates.IDLE, MiningStates.BEGIN, 2)
            .addStateTransition(MiningStates.BEGIN, MiningStates.LOOPING, 1)
            .addStateTransition(MiningStates.BEGIN, MiningStates.IDLE, 2)
            .addStateTransition(MiningStates.LOOPING, MiningStates.IDLE, 2);


     */


    //private static final AnimationMontageTrack MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK = AnimationMontageTrack.of("main_hand_empty_punch_montage_track");
    /*
    private static final AnimationMontage MAIN_HAND_EMPTY_PUNCH_MONTAGE = AnimationMontage.of(ANIMATION_FP_RIGHT_EMPTY_PUNCH)
            .setLength(TickTimeUtils.ticksFromMayaFrames(10F))
            .setBlendInDuration(1)
            .setBlendOutDuration(TickTimeUtils.ticksFromMayaFrames(8F));
    private static final AnimationMontage MAIN_HAND_EMPTY_USE_ITEM_MONTAGE = AnimationMontage.of(ANIMATION_FP_RIGHT_EMPTY_USE_ITEM)
            .setLength(TickTimeUtils.ticksFromMayaFrames(9F))
            .setBlendInDuration(1)
            .setBlendOutDuration(TickTimeUtils.ticksFromMayaFrames(5F));

     */



    /*
    private static final AnimationBlendSpacePlayer MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER = AnimationBlendSpacePlayer.of("main_hand_empty_walk_blendspace_player")
            .addEntry(0, ANIMATION_FP_RIGHT_EMPTY_WALK, 0F)
            .addEntry(2, ANIMATION_FP_RIGHT_EMPTY_WALK, 2.9F);

     */


    public FirstPersonPlayerJointAnimator(){
        super();
    }

    protected JointSkeleton<FPPlayerLocators> buildRig() {
        return JointSkeleton.of(FPPlayerLocators.root)
                .addChildLocator(FPPlayerLocators.camera)
                .addChildLocator(FPPlayerLocators.armBuffer)
                .addChildLocator(FPPlayerLocators.leftArmBuffer, FPPlayerLocators.armBuffer)
                .addChildLocator(FPPlayerLocators.rightArmBuffer, FPPlayerLocators.armBuffer)
                .addChildLocator(FPPlayerLocators.leftArm, FPPlayerLocators.leftArmBuffer)
                .addChildLocator(FPPlayerLocators.rightArm, FPPlayerLocators.rightArmBuffer)
                .addChildLocator(FPPlayerLocators.leftHand, FPPlayerLocators.leftArm)
                .addChildLocator(FPPlayerLocators.rightHand, FPPlayerLocators.rightArm)
                .setLocatorDefaultPose(FPPlayerLocators.leftHand, PartPose.offsetAndRotation(1, 10, -2, -Mth.HALF_PI, 0, Mth.PI))
                .setLocatorDefaultPose(FPPlayerLocators.rightHand, PartPose.offsetAndRotation(-1, 10, -2, -Mth.HALF_PI, 0, Mth.PI))
                .setLocatorMirror(FPPlayerLocators.rightArm, FPPlayerLocators.leftArm)
                .setLocatorMirror(FPPlayerLocators.rightHand, FPPlayerLocators.leftHand);

    }

    @Override
    public AnimationPose<FPPlayerLocators> calculatePose(LocalPlayer localPlayer, AnimationDataContainer animationDataContainer) {
        // Update main hand item based on the anim notify

        animationDataContainer.getAnimationVariable(MAIN_HAND_ITEM).set(localPlayer.getMainHandItem().copy());
        //setEntityAnimationVariable(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem().copy());

        AnimationPose<FPPlayerLocators> pose = sampleAnimationState(TEST_IDLE_SEQUENCE_PLAYER);



        pose = dampenArmRotation(pose);


        Vector3f rotation = new Vector3f(Mth.sin(getEntityAnimationVariable(TIME_TEST) * 0.2F) * Mth.HALF_PI * 0.7f, 0, 0);
        //Vector3f translation = new Vector3f(Mth.sin(getEntityAnimationVariable(TIME_TEST) * 1.3F) * 3F, 0, 0);
        //pose.translateJoint(FPPlayerLocators.rightArm, translation, AnimationPose.TransformSpace.ENTITY, false);
        //pose.rotateJoint(FPPlayerLocators.rightArm, rotation, AnimationPose.TransformSpace.ENTITY, false);


        return pose;
    }

    /*
    Get the pose with the added dampened camera rotation
     */
    private AnimationPose<FPPlayerLocators> dampenArmRotation(AnimationPose<FPPlayerLocators> pose){
        Vector3f cameraRotation = getEntityAnimationVariable(CAMERA_ROTATION);
        Vector3f dampenedCameraRotation = getEntityAnimationVariable(DAMPENED_CAMERA_ROTATION);

        Vector3f cameraDampWeight = new Vector3f(0.6F, 0.3F, 0.1F);

        pose.setJointPose(
                FPPlayerLocators.armBuffer,
                pose.getJointPoseCopy(FPPlayerLocators.armBuffer).rotate(
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
    public void tick(LocalPlayer localPlayer, AnimationDataContainer entityAnimationData){




        this.setEntityAnimationVariable(TIME_TEST, this.getEntityAnimationVariable(TIME_TEST) + 1);



        /*
        Tick the dampened camera rotation.
         */

        Vector3f dampenSpeed = new Vector3f(0.5F, 0.5F, 0.2F);

        // First, set the target camera rotation from the living entity.
        Vector3f targetRotation = new Vector3f(this.livingEntity.getXRot(), this.livingEntity.getYRot(), this.livingEntity.getYRot());
        setEntityAnimationVariable(CAMERA_ROTATION, targetRotation);

        Vector3f dampenedCameraRotation = getEntityAnimationVariable(DAMPENED_CAMERA_ROTATION);

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
        setEntityAnimationVariable(DAMPENED_CAMERA_ROTATION, dampenedCameraRotation);


    }


    //TODO: Move this elsewhere

    public void tickExternal(){
        LocalPlayer player = Minecraft.getInstance().player;
        AnimationDataContainer animationDataContainer = this.localAnimationDataContainer;

        this.tick(player, animationDataContainer);
        animationDataContainer.tickAllPoseSamplers();

        if(this.localBakedPose == null){
            this.localBakedPose = new BakedAnimationPose();
            this.localBakedPose.setPose(AnimationPose.of(this.jointSkeleton));
        }
        if(!this.localBakedPose.hasPose){
            this.localBakedPose.setPose(AnimationPose.of(this.jointSkeleton));
            this.localBakedPose.hasPose = true;
        }
        this.localBakedPose.pushToOld();

        AnimationPose animationPose = this.calculatePose(player, animationDataContainer);
        if (animationPose == null){
            animationPose = AnimationPose.of(this.jointSkeleton);
        }
        animationPose.applyDefaultPoseOffset();




        this.localBakedPose.setPose(new AnimationPose(animationPose));
    }

    private boolean compareVariableItemStackWithEntityItemStack(AnimationVariableKey<ItemStack> itemStackDataKey, ItemStack entityItemStack){
        ItemStack currentItemStack = getEntityAnimationVariable(itemStackDataKey);
        if(currentItemStack.getItem() != null && entityItemStack.getItem() == null || currentItemStack.getItem() == null && entityItemStack.getItem() != null) {
            return true;
        }
        return currentItemStack.getItem() != entityItemStack.getItem();
    }
}
