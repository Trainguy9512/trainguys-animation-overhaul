package com.trainguy9512.animationoverhaul.animation.entity;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationBlendSpacePlayer;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationSequencePlayer;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationStateMachine;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.time.TickTimeUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class PlayerPartAnimator extends LivingEntityAnimator<Player, PlayerModel<Player>> {

    private static final String LOCATOR_ROOT = "root";
    private static final String LOCATOR_HEAD = "head";
    private static final String LOCATOR_BODY = "body";
    private static final String LOCATOR_LEFT_LEG = "leftLeg";
    private static final String LOCATOR_RIGHT_LEG = "rightLeg";
    private static final String LOCATOR_LEFT_ARM = "leftArm";
    private static final String LOCATOR_RIGHT_ARM = "rightArm";
    private static final String LOCATOR_CAPE = "cape";
    private static final String LOCATOR_LEFT_HAND = "leftHand";
    private static final String LOCATOR_RIGHT_HAND = "rightHand";

    private static final List<String> LOCATOR_LIST_ALL = List.of(
            LOCATOR_HEAD,
            LOCATOR_BODY,
            LOCATOR_LEFT_LEG,
            LOCATOR_RIGHT_LEG,
            LOCATOR_LEFT_ARM,
            LOCATOR_RIGHT_ARM,
            LOCATOR_CAPE,
            LOCATOR_LEFT_HAND,
            LOCATOR_RIGHT_HAND
    );
    private static final List<String> LOCATOR_LIST_NO_HANDS = List.of(
            LOCATOR_HEAD,
            LOCATOR_BODY,
            LOCATOR_LEFT_LEG,
            LOCATOR_RIGHT_LEG,
            LOCATOR_LEFT_ARM,
            LOCATOR_RIGHT_ARM,
            LOCATOR_CAPE
    );
    private static final List<String> LOCATOR_LIST_MASTER = List.of(
            LOCATOR_ROOT,
            LOCATOR_HEAD,
            LOCATOR_BODY,
            LOCATOR_LEFT_LEG,
            LOCATOR_RIGHT_LEG,
            LOCATOR_LEFT_ARM,
            LOCATOR_RIGHT_ARM,
            LOCATOR_CAPE,
            LOCATOR_LEFT_HAND,
            LOCATOR_RIGHT_HAND
    );

    public PlayerPartAnimator(){
        super();
    }

    @Override
    protected void buildRig(LocatorSkeleton locatorRig) {
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
    }

    @Override
    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData) {

    }

    @Override
    protected AnimationPose calculatePose() {
        return new AnimationPose(this.locatorSkeleton);
    }

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
