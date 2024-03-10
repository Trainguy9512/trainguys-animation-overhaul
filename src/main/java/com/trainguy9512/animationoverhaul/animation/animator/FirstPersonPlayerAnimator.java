package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.animator.entity.LivingEntityAnimator;
import com.trainguy9512.animationoverhaul.animation.data.AnimationVariableKey;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.TimelineGroupData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

public class FirstPersonPlayerAnimator extends LivingEntityAnimator<LocalPlayer, PlayerModel<LocalPlayer>, FirstPersonPlayerAnimator.FPPlayerLocators> {

    public static FirstPersonPlayerAnimator INSTANCE = new FirstPersonPlayerAnimator();

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


    public static final AnimationVariableKey<Float> TIME_TEST = AnimationVariableKey.of(() -> 0F).setDebugIdentifier("time_test").build();



    public static final AnimationVariableKey<Vector3f> CAMERA_ROTATION = AnimationVariableKey.of(() -> new Vector3f(0, 0, 0)).setDebugIdentifier("camera_rotation").build();
    public static final AnimationVariableKey<Vector3f> DAMPENED_CAMERA_ROTATION = AnimationVariableKey.of(() -> new Vector3f(0, 0, 0)).setDebugIdentifier("dampened_camera_rotation").build();

    private static final AnimationSequencePlayer IDLE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("idle_sequence_player", ANIMATION_FP_PLAYER_IDLE).setDefaultPlayRate(0F);
    private static final AnimationSequencePlayer TEST_IDLE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("test_idle_sequence_player", ANIMATION_FP_PLAYER_IDLE).setDefaultPlayRate(1F).setStartTime(70);



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


    private static final AnimationMontageTrack MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK = AnimationMontageTrack.of("main_hand_empty_punch_montage_track");
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


    public FirstPersonPlayerAnimator(){
        super();
    }

    @Override
    protected LocatorSkeleton<FPPlayerLocators> buildRig() {
        return LocatorSkeleton.of(FPPlayerLocators.root)
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
    protected AnimationPose<FPPlayerLocators> calculatePose() {
        // Update main hand item based on the anim notify
        //setEntityAnimationVariable(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem().copy());
        /*
        if(getAnimationState(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER).isAnimNotityActive(ITEM_SWITCH_NOTIFY)){
            setEntityAnimationVariable(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem().copy());
        }
         */



        // Set the poses for the main hand item switch state machine
        /*
        getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE)
                .setPose(ItemSwitchStates.EMPTY, getMainEmptyLocomotionPose(getStaticMainEmptyPose(), true, false))
                .setPose(ItemSwitchStates.EMPTY_RAISING, getMainHandRaisePose(getStaticMainEmptyPose()))
                .setPose(ItemSwitchStates.BASIC_ITEM, getMainEmptyLocomotionPose(getStaticMainBasicItemPose(), true, true))
                .setPose(ItemSwitchStates.BASIC_ITEM_RAISING, getMainHandRaisePose(getStaticMainBasicItemPose()))
                .setPose(ItemSwitchStates.LOWERING, getMainHandLowerPose(getStaticMainEmptyPose()));
        AnimationPose<FPPlayerLocators> pose = sampleAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE);
        pose = pose.getSelectedByLocators(getCameraPose(), List.of(FPPlayerLocators.camera));
         */


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

    // Gets the camera pose
    private AnimationPose<FPPlayerLocators> getCameraPose(){
        /*
        return getMainEmptyLocomotionPose(this.getStaticMainEmptyPose(), true, false);

         */
        return AnimationPose.of(this.locatorSkeleton);
    }

    /*
    private AnimationPose<FPPlayerLocators> getMainEmptyLocomotionPose(AnimationPose<FPPlayerLocators> basePose, boolean applyPunch, boolean applyAdditive){
        AnimationPose<FPPlayerLocators> pose = sampleAnimationState(MAIN_EMPTY_IDLE_SEQUENCE_PLAYER);
        pose.blendLinear(sampleAnimationState(MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER), getEntityAnimationVariable(WALK_WEIGHT));

        getAnimationState(JUMP_STATE_MACHINE)
                .setPose(JumpingStates.ON_GROUND, pose)
                .setPose(JumpingStates.JUMPING, sampleAnimationState(MAIN_EMPTY_JUMP_SEQUENCE_PLAYER))
                .setPose(JumpingStates.FALLING,
                        AnimationPose.fromChannelTimeline(this.locatorSkeleton, ANIMATION_FP_RIGHT_EMPTY_JUMP, 0.5F)
                                .getBlendedLinear(
                                        sampleAnimationState(MAIN_EMPTY_FALLING_SEQUENCE_PLAYER),
                                        Mth.clampedMap(
                                                this.livingEntity.fallDistance,
                                                0,
                                                10,
                                                0.5F,
                                                1.2F
                                        )
                                ))
                .setPose(JumpingStates.LANDING, sampleAnimationState(MAIN_EMPTY_LAND_SEQUENCE_PLAYER));
        pose = sampleAnimationState(JUMP_STATE_MACHINE);


        if(applyPunch){
            AnimationPose<FPPlayerLocators> punchPose = sampleAnimationStateFromInputPose(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK, pose);

            AnimationPose<FPPlayerLocators> cameraAdditivePose = sampleAnimationStateFromInputPose(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK, AnimationPose.of(this.locatorSkeleton));
            pose = punchPose.getSelectedByLocators(pose.getAdded(cameraAdditivePose), List.of(FPPlayerLocators.camera));

            getAnimationState(MINING_STATE_MACHINE)
                    .setPose(MiningStates.IDLE, pose)
                    .setPose(MiningStates.BEGIN, sampleAnimationState(MAIN_EMPTY_MINING_BEGIN_SEQUENCE_PLAYER))
                    .setPose(MiningStates.LOOPING, sampleAnimationState(MAIN_EMPTY_MINING_LOOP_SEQUENCE_PLAYER));
            pose = sampleAnimationState(MINING_STATE_MACHINE);
        }

        //AnimationPose additivePose = basePose.getSubtracted(getStaticMainEmptyPose());
        //return pose.getAdded(additivePose);
        if(applyAdditive) {
            AnimationPose<FPPlayerLocators> additivePose = getStaticMainEmptyPose().getSubtracted(basePose);
            pose = pose.getAdded(additivePose);

            //AnimationPose<FPPlayerLocators> unAddedPose = pose.getSubtracted(getStaticMainEmptyPose());
            //pose = unAddedPose.getAdded(basePose);
        }
        return pose;

    }

    private AnimationPose<FPPlayerLocators> getMainHandRaisePose(AnimationPose<FPPlayerLocators> basePose){
        AnimationPose<FPPlayerLocators> pose = sampleAnimationState(MAIN_EMPTY_RAISE_SEQUENCE_PLAYER);
        return pose.getSubtracted(getStaticMainEmptyPose()).getAdded(basePose);
    }

    private AnimationPose<FPPlayerLocators> getMainHandLowerPose(AnimationPose<FPPlayerLocators> basePose){
        AnimationPose<FPPlayerLocators> pose = sampleAnimationState(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER);
        return pose.getSubtracted(getStaticMainEmptyPose()).getAdded(basePose);
    }


    private AnimationPose<FPPlayerLocators> getStaticMainEmptyPose(){
        return AnimationPose.fromChannelTimeline(this.locatorSkeleton, ANIMATION_FP_RIGHT_EMPTY_PUNCH, 0);
    }

    private AnimationPose<FPPlayerLocators> getStaticMainBasicItemPose(){
        return AnimationPose.fromChannelTimeline(this.locatorSkeleton, ANIMATION_FP_RIGHT_BASICITEM_POSE, 0);
    }

     */


    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData){




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




        // Tick the main hand lower/empty sequence players based on active states
        /*
        getAnimationState(MAIN_EMPTY_LOWER_SEQUENCE_PLAYER).playFromStartOnStateActive(getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE),
                ItemSwitchStates.LOWERING);
        getAnimationState(MAIN_EMPTY_RAISE_SEQUENCE_PLAYER).playFromStartOnStateActive(getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE), List.of(
                ItemSwitchStates.EMPTY_RAISING,
                ItemSwitchStates.BASIC_ITEM_RAISING
        ));

        // Update the conditions for the maind hand empty item switching
        getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).setTransitionCondition(ItemSwitchStates.EMPTY, ItemSwitchStates.LOWERING, this.compareVariableItemStackWithEntityItemStack(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem()));
        getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).setTransitionCondition(ItemSwitchStates.EMPTY_RAISING, ItemSwitchStates.EMPTY, getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4));
        getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).setTransitionCondition(ItemSwitchStates.LOWERING, ItemSwitchStates.EMPTY_RAISING, getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4) && getEntityAnimationVariable(MAIN_HAND_ITEM).isEmpty());
        getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).setTransitionCondition(ItemSwitchStates.BASIC_ITEM, ItemSwitchStates.LOWERING, this.compareVariableItemStackWithEntityItemStack(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem()));
        getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).setTransitionCondition(ItemSwitchStates.BASIC_ITEM_RAISING, ItemSwitchStates.BASIC_ITEM, getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4));
        getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).setTransitionCondition(ItemSwitchStates.LOWERING, ItemSwitchStates.BASIC_ITEM_RAISING, getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4) && !getEntityAnimationVariable(MAIN_HAND_ITEM).isEmpty());
        // Debug
        //AnimationOverhaulMain.LOGGER.info("Current: {} New: {} Condition: {}", getEntityAnimationVariable(MAIN_HAND_ITEM).getItem(), this.livingEntity.getMainHandItem().getItem(), this.compareVariableItemStackWithEntityItemStack(MAIN_HAND_ITEM, this.livingEntity.getMainHandItem()));


        // Set variables for jumping and falling
        setEntityAnimationVariable(IS_JUMPING,
                this.livingEntity.input.jumping
                && !this.livingEntity.getAbilities().flying
                && !this.livingEntity.isPassenger()
        );
        setEntityAnimationVariable(IS_FALLING,
                !this.livingEntity.onGround()
                && !this.livingEntity.onClimbable()
        );
        getAnimationState(JUMP_STATE_MACHINE)
                .setTransitionCondition(JumpingStates.ON_GROUND, JumpingStates.JUMPING, getEntityAnimationVariable(IS_FALLING) && getEntityAnimationVariable(IS_JUMPING))
                .setTransitionCondition(JumpingStates.ON_GROUND, JumpingStates.FALLING, getEntityAnimationVariable(IS_FALLING))
                .setTransitionCondition(JumpingStates.JUMPING, JumpingStates.FALLING, getAnimationState(JUMP_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(6) && getEntityAnimationVariable(IS_FALLING))
                .setTransitionCondition(JumpingStates.JUMPING, JumpingStates.LANDING, !getEntityAnimationVariable(IS_FALLING))
                .setTransitionCondition(JumpingStates.FALLING, JumpingStates.LANDING, !getEntityAnimationVariable(IS_FALLING))
                .setTransitionCondition(JumpingStates.LANDING, JumpingStates.JUMPING, getEntityAnimationVariable(IS_FALLING) && getEntityAnimationVariable(IS_JUMPING))
                .setTransitionCondition(JumpingStates.LANDING, JumpingStates.FALLING, getEntityAnimationVariable(IS_FALLING))
                .setTransitionCondition(JumpingStates.LANDING, JumpingStates.ON_GROUND, getAnimationState(JUMP_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(3) && !getEntityAnimationVariable(IS_FALLING));
        getAnimationState(MAIN_EMPTY_JUMP_SEQUENCE_PLAYER).playFromStartOnStateActive(JUMP_STATE_MACHINE, JumpingStates.JUMPING);
        getAnimationState(MAIN_EMPTY_LAND_SEQUENCE_PLAYER).playFromStartOnStateActive(JUMP_STATE_MACHINE, JumpingStates.LANDING);
        getAnimationState(MAIN_EMPTY_FALLING_SEQUENCE_PLAYER).setPlayRate(Mth.clampedMap(this.livingEntity.fallDistance, 0, 20, 1, 2));



        // Set the conditions for the mining state machine, and reset the two animation sequences based on the state status
        getAnimationState(MINING_STATE_MACHINE)
                .setTransitionCondition(MiningStates.IDLE, MiningStates.BEGIN, getEntityAnimationVariable(IS_MINING) && !getAnimationState(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK).isActive())
                .setTransitionCondition(MiningStates.BEGIN, MiningStates.LOOPING, getAnimationState(MINING_STATE_MACHINE).getTimeElapsed() > TickTimeUtils.ticksFromMayaFrames(4))
                .setTransitionCondition(MiningStates.BEGIN, MiningStates.IDLE, !getEntityAnimationVariable(IS_MINING))
                .setTransitionCondition(MiningStates.LOOPING, MiningStates.IDLE, !getEntityAnimationVariable(IS_MINING));
        getAnimationState(MAIN_EMPTY_MINING_BEGIN_SEQUENCE_PLAYER).playFromStartOnStateActive(MINING_STATE_MACHINE, MiningStates.BEGIN);
        getAnimationState(MAIN_EMPTY_MINING_LOOP_SEQUENCE_PLAYER).playFromStartOnStateActive(MINING_STATE_MACHINE, MiningStates.LOOPING);
        getAnimationState(MAIN_EMPTY_MINING_LOOP_SEQUENCE_PLAYER).progressTimeIfStateActive(MINING_STATE_MACHINE, MiningStates.LOOPING);


        if(getEntityAnimationVariable(IS_ATTACKING) && !getEntityAnimationVariable(IS_MINING)){
            setEntityAnimationVariable(IS_ATTACKING, false);

            // Only play the attack animation if not mining
            if(getAnimationState(MINING_STATE_MACHINE).getActiveState() == MiningStates.IDLE){

                // Play the attack montage based on the main hand item switch state. Does not play for raising/lowering states.
                ItemSwitchStates state = getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).getActiveState();
                switch (state){
                    case EMPTY, BASIC_ITEM:
                        getAnimationState(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK).playMontage(MAIN_HAND_EMPTY_PUNCH_MONTAGE);
                }
            }
        }
        if(getEntityAnimationVariable(IS_USING_ITEM)){
            setEntityAnimationVariable(IS_USING_ITEM, false);
            ItemSwitchStates state = getAnimationState(MAINHAND_ITEMSWITCH_STATE_MACHINE).getActiveState();
            switch (state){
                case EMPTY, BASIC_ITEM:
                    getAnimationState(MAIN_HAND_EMPTY_PUNCH_MONTAGE_TRACK).playMontage(MAIN_HAND_EMPTY_USE_ITEM_MONTAGE);
            }
        }

        boolean isInputtingMovement =
                this.livingEntity.input.forwardImpulse != 0 ||
                this.livingEntity.input.leftImpulse != 0;
        //AnimationOverhaulMain.LOGGER.info("{}, {}", this.livingEntity.input.forwardImpulse, this.livingEntity.animationSpeed);
        getEntityAnimationData().incrementInFramesFromCondition(WALK_WEIGHT, isInputtingMovement, 2, 4);
        getAnimationState(MAIN_HAND_EMPTY_WALK_BLENDSPACE_PLAYER).setValue(this.getWalkAnimationSpeed());
         */

    }

    public void tickExternal(){
        LocalPlayer player = Minecraft.getInstance().player;
        this.setEntity(player);

        this.tick(player, getEntityAnimationData());
        getEntityAnimationData().tickAnimationStates();

        if(this.localBakedPose == null){
            this.localBakedPose = new BakedAnimationPose();
            this.localBakedPose.setPose(AnimationPose.of(this.locatorSkeleton));
        }
        if(!this.localBakedPose.hasPose){
            this.localBakedPose.setPose(AnimationPose.of(this.locatorSkeleton));
            this.localBakedPose.hasPose = true;
        }
        this.localBakedPose.pushToOld();

        AnimationPose animationPose = this.calculatePose();
        if (animationPose == null){
            animationPose = AnimationPose.of(this.locatorSkeleton);
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

    @Override
    protected AnimationDataContainer getEntityAnimationData() {
        return this.localAnimationDataContainer;
    }
}
