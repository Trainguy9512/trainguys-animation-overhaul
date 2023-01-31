package com.trainguy9512.animationoverhaul.animation.entity;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationSequencePlayer;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.time.TickTimeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

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

    private static final String TEST_NOTIFY = "test_notify";
    private static final AnimationSequencePlayer TEST_SEQUENCE_PLAYER = AnimationSequencePlayer.of("test_camera_anim", new ResourceLocation(AnimationOverhaulMain.MOD_ID, "player/fp_right_empty_idle"))
            .setDefaultPlayRate(1F)
            .addAnimNotify(TEST_NOTIFY, 40);

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
        /*
        if(getEntityAnimationData().getAnimationSequencePlayer(TEST_SEQUENCE_PLAYER).isAnimNotityActive(TEST_NOTIFY)){
            Random random = new Random();
            AnimationOverhaulMain.LOGGER.info("Notify hit on frame 40! Here's a random number: {}", random.nextInt(100));
        }
         */

        return getEntityAnimationData().sampleAnimationState(this.locatorSkeleton, TEST_SEQUENCE_PLAYER);
    }

    private float testFloat = 0;

    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData){
        /*
        testFloat++;
        getEntityAnimationData().getAnimationSequencePlayer(TEST_SEQUENCE_PLAYER).setPlayRate((Mth.sin(testFloat * 4) * 0.5F + 0.5F) * 3 + 0.5F);
        AnimationOverhaulMain.LOGGER.info("Current frame: {}", TickTimeUtils.mayaFramesFromTicks(getEntityAnimationData().getAnimationSequencePlayer(TEST_SEQUENCE_PLAYER).getTimeElapsedLooped()));

         */
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
