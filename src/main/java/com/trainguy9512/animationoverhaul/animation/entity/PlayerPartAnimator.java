package com.trainguy9512.animationoverhaul.animation.entity;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationBlendSpacePlayer;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationSequencePlayer;
import com.trainguy9512.animationoverhaul.animation.pose.sample.TestReferenceSampler;
import com.trainguy9512.animationoverhaul.util.animation.Locator;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class PlayerPartAnimator extends LivingEntityPartAnimator<Player, PlayerModel<Player>> {

    private Locator locatorMaster;
    private Locator locatorHead;
    private Locator locatorBody;
    private Locator locatorLeftLeg;
    private Locator locatorRightLeg;
    private Locator locatorLeftArm;
    private Locator locatorRightArm;
    private Locator locatorCloak;
    private Locator locatorLeftHand;
    private Locator locatorRightHand;

    private List<Locator> locatorListAll;
    private List<Locator> locatorListNoHands;
    private List<Locator> locatorListMaster;

    private static final AnimationSequencePlayer RUN_CYCLE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("run_loop", new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/sprint_normal"))
            .setPlayRate(1.2F);
    private static final AnimationSequencePlayer IDLE_SEQUENCE_PLAYER = AnimationSequencePlayer.of("idle_loop", new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/climb_up"))
            .setPlayRate(0.5F);
    private static final TestReferenceSampler TEST_SAMPLER = TestReferenceSampler.of("testsampler", "test");
    private static final AnimationBlendSpacePlayer TEST_BLEND_SPACE = AnimationBlendSpacePlayer.of("test_blendspace")
            .addEntry(0F, new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/walk_normal"), 0.5F)
            .addEntry(0.8F, new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/walk_normal"), 1F)
            .addEntry(1F, new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/sprint_normal"), 2F);

    public PlayerPartAnimator(){
        super();
    }

    @Override
    protected void buildRig(LocatorSkeleton locatorRig) {
        this.locatorMaster = new Locator("root");
        this.locatorHead = new Locator("head");
        this.locatorBody = new Locator("body");
        this.locatorLeftLeg = new Locator("leftLeg");
        this.locatorRightLeg = new Locator("rightLeg");
        this.locatorLeftArm = new Locator("leftArm");
        this.locatorRightArm = new Locator("rightArm");
        this.locatorCloak = new Locator("cape");
        this.locatorLeftHand = new Locator("leftHand");
        this.locatorRightHand = new Locator("rightHand");

        this.locatorListAll = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorLeftHand, locatorRightHand);
        this.locatorListNoHands = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak);
        this.locatorListMaster = List.of(locatorLeftArm, locatorRightArm, locatorLeftLeg, locatorRightLeg, locatorBody, locatorHead, locatorCloak, locatorLeftHand, locatorRightHand, locatorMaster);

        locatorRig.addLocator(locatorMaster);
        locatorRig.addLocatorModelPart(locatorHead, "head");
        locatorRig.addLocatorModelPart(locatorBody, "body");
        locatorRig.addLocatorModelPart(locatorLeftLeg, locatorRightLeg, "left_leg", PartPose.offset(1.9f, 12.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorRightLeg, locatorLeftLeg, "right_leg", PartPose.offset(-1.9f, 12.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorLeftArm, locatorRightArm, "left_arm", PartPose.offset(5.0f, 2.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorRightArm, locatorLeftArm, "right_arm", PartPose.offset(-5.0f, 2.0f, 0.0f));
        locatorRig.addLocatorModelPart(locatorCloak, "cloak", PartPose.offsetAndRotation(0, 0, 2, 0, Mth.PI, 0));
        locatorRig.addLocator(locatorLeftHand, locatorRightHand);
        locatorRig.addLocator(locatorRightHand, locatorLeftHand);
    }

    @Override
    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData) {
        this.entityAnimationData.getAnimationBlendSpacePlayer(TEST_BLEND_SPACE).setValue(Mth.sin(this.livingEntity.tickCount / 12F) * 0.5F + 0.5F);
    }

    @Override
    protected AnimationPose calculatePose() {
        AnimationPose animationPoseRun = this.entityAnimationData.sampleAnimationState(this.locatorSkeleton, RUN_CYCLE_SEQUENCE_PLAYER);
        AnimationPose animationPoseIdle = this.entityAnimationData.sampleAnimationState(this.locatorSkeleton, IDLE_SEQUENCE_PLAYER);
        this.entityAnimationData.saveCachedPose("test", animationPoseRun);
        AnimationPose animationPose = this.entityAnimationData.sampleAnimationState(this.locatorSkeleton, TEST_BLEND_SPACE);
        //AnimationPose animationPose = AnimationPose.blendBoolean(animationPoseIdle, animationPoseRun, this.livingEntity.animationSpeed > 0.5);
                //AnimationPose.fromChannelTimeline(this.locatorSkeleton, TimelineGroupData.INSTANCE.get(AnimationOverhaulMain.MOD_ID, "player/sprint_normal"), 0, false);

        return animationPose;
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
