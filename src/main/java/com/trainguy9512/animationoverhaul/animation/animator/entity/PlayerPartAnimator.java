package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

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


    public PlayerPartAnimator(){
        super();
    }

    // Building the locator rig
    @Override
    protected LocatorSkeleton<ModelPartLocators> buildRig() {

        //TODO: Adjust rig with proper parenting and no more offsets on the legs.

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



    }

    // This is the function for getting the final pose every tick
    @Override
    protected AnimationPose<ModelPartLocators> calculatePose() {
        return AnimationPose.of(this.locatorSkeleton);
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
