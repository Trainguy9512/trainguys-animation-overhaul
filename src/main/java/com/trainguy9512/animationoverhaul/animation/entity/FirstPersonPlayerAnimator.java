package com.trainguy9512.animationoverhaul.animation.entity;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationSequencePlayer;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class FirstPersonPlayerAnimator extends LivingEntityAnimator<LocalPlayer, PlayerModel<LocalPlayer>>{

    public static FirstPersonPlayerAnimator INSTANCE = new FirstPersonPlayerAnimator();

    public AnimationDataContainer localAnimationDataContainer = new AnimationDataContainer();
    public BakedAnimationPose localBakedPose;

    public static final String LOCATOR_ROOT = "root";
    public static final String LOCATOR_CAMERA = "camera";
    public static final String LOCATOR_RIGHT_ARM = "rightArm";
    public static final String LOCATOR_LEFT_ARM = "leftArm";
    public static final String LOCATOR_RIGHT_HAND = "rightHand";
    public static final String LOCATOR_LEFT_HAND = "leftHand";

    private static final AnimationSequencePlayer TEST_SEQUENCE_PLAYER = AnimationSequencePlayer.of("test_camera_anim", new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/test_camera_anim"));

    public FirstPersonPlayerAnimator(){
        super();
    }

    @Override
    protected void buildRig(LocatorSkeleton locatorRig) {
        locatorRig.addLocator(LOCATOR_ROOT);
        locatorRig.addLocator(LOCATOR_CAMERA);
        locatorRig.addLocator(LOCATOR_RIGHT_ARM, LOCATOR_LEFT_ARM);
        locatorRig.addLocator(LOCATOR_LEFT_ARM, LOCATOR_RIGHT_ARM);
        locatorRig.addLocator(LOCATOR_RIGHT_HAND, LOCATOR_LEFT_HAND);
        locatorRig.addLocator(LOCATOR_LEFT_HAND, LOCATOR_RIGHT_HAND);
    }

    @Override
    protected AnimationPose calculatePose() {
        return getEntityAnimationData().sampleAnimationState(this.locatorSkeleton, TEST_SEQUENCE_PLAYER);
    }

    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData){

    }

    public void tickExternal(){
        LocalPlayer player = Minecraft.getInstance().player;

        this.tick(player, getEntityAnimationData());
        getEntityAnimationData().tickAnimationStates();

        if(this.localBakedPose == null){
            this.localBakedPose = new BakedAnimationPose();
            this.localBakedPose.setPose(new AnimationPose(this.locatorSkeleton));
        }
        if(!this.localBakedPose.hasPose){
            this.localBakedPose.setPose(new AnimationPose(this.locatorSkeleton));
            this.localBakedPose.hasPose = true;
        }
        this.localBakedPose.pushToOld();

        AnimationPose animationPose = this.calculatePose();
        if (animationPose == null){
            animationPose = new AnimationPose(this.locatorSkeleton);
        }
        animationPose.applyDefaultPoseOffset();

        this.localBakedPose.setPose(animationPose.getCopy());
    }

    @Override
    protected AnimationDataContainer getEntityAnimationData() {
        return this.localAnimationDataContainer;
    }
}
