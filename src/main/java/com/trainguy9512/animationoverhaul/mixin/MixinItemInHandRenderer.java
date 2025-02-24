package com.trainguy9512.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimatorDispatcher;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimatorRegistry;
import com.trainguy9512.animationoverhaul.animation.animator.entity.FirstPersonPlayerJointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class MixinItemInHandRenderer {
    @Shadow @Final private EntityRenderDispatcher entityRenderDispatcher;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private ItemRenderer itemRenderer;

    //@Shadow public abstract void renderItem(LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, boolean bl, PoseStack poseStack, MultiBufferSource multiBufferSource, int i);

    @Shadow public abstract void renderItem(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext itemDisplayContext, boolean bl, PoseStack poseStack, MultiBufferSource multiBufferSource, int i);

    @Inject(method = "renderHandsWithItems", at = @At("HEAD"), cancellable = true)
    private void overwriteItemInHandRendering(float partialTicks, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, LocalPlayer localPlayer, int i, CallbackInfo ci){
        JointAnimatorDispatcher jointAnimatorDispatcher = JointAnimatorDispatcher.getInstance();

        JointAnimatorDispatcher.getInstance().getFirstPersonPlayerDataContainer().ifPresent(
                dataContainer -> JointAnimatorRegistry.getFirstPersonPlayerJointAnimator().ifPresent(
                        jointAnimator -> {
                            jointAnimatorDispatcher.calculateInterpolatedFirstPersonPlayerPose(jointAnimator, dataContainer, partialTicks);
                            jointAnimatorDispatcher.getInterpolatedFirstPersonPlayerPose().ifPresent(
                                    animationPose -> {
                                        JointTransform rightArmPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.RIGHT_ARM_JOINT);
                                        JointTransform leftArmPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.LEFT_ARM_JOINT);
                                        JointTransform rightHandPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.RIGHT_HAND_JOINT);
                                        JointTransform leftHandPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.LEFT_HAND_JOINT);

                                        poseStack.pushPose();
                                        poseStack.mulPose(Axis.ZP.rotationDegrees(180));
                                        //poseStack.pushPose();



                                        AbstractClientPlayer abstractClientPlayer = this.minecraft.player;
                                        //RenderSystem.setShaderTexture(0, abstractClientPlayer.getSkin().texture());
                                        PlayerRenderer playerRenderer = (PlayerRenderer)this.entityRenderDispatcher.getRenderer(abstractClientPlayer);
                                        PlayerModel playerModel = playerRenderer.getModel();


                                        playerModel.rightArm.loadPose(rightArmPose.asPartPose());
                                        playerModel.leftArm.loadPose(leftArmPose.asPartPose());

                                        ResourceLocation resourceLocation = abstractClientPlayer.getSkin().texture();

                                        playerModel.rightArm.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);
                                        playerModel.leftArm.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);

                                        this.renderItemInHand(abstractClientPlayer, dataContainer.getDriverValueInterpolated(FirstPersonPlayerJointAnimator.MAIN_HAND_ITEM, partialTicks), poseStack, HumanoidArm.RIGHT, animationPose, bufferSource, i);
                                        //this.renderItemInHand(abstractClientPlayer, ItemStack.EMPTY, poseStack, HumanoidArm.LEFT, animationPose, bufferSource, i);


                                        //playerRenderer.renderRightHand(poseStack, bufferSource, i, abstractClientPlayer);
                                        //poseStack.popPose();
                                        poseStack.popPose();
                                    }
                            );
                        })
                );

        bufferSource.endBatch();
        ci.cancel();
    }

    @Unique
    private void renderItemInHand(AbstractClientPlayer abstractClientPlayer, ItemStack itemStack, PoseStack poseStack, HumanoidArm humanoidArm, AnimationPose animationPose, MultiBufferSource multiBufferSource, int i){

        JointTransform armPose = animationPose.getJointTransform(humanoidArm == HumanoidArm.LEFT ? FirstPersonPlayerJointAnimator.LEFT_ARM_JOINT : FirstPersonPlayerJointAnimator.RIGHT_ARM_JOINT);
        JointTransform handPose = animationPose.getJointTransform(humanoidArm == HumanoidArm.LEFT ? FirstPersonPlayerJointAnimator.LEFT_HAND_JOINT : FirstPersonPlayerJointAnimator.RIGHT_HAND_JOINT);



        poseStack.pushPose();
        //armPose.transformPoseStack(poseStack);
        //poseStack.translate((humanoidArm == HumanoidArm.LEFT ? 1 : -1) /16F, 9/16F, 0);
        handPose.transformPoseStack(poseStack, 16F);
        //poseStack.mulPose(Axis.XP.rotationDegrees(180.0f));
        //poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0f));

        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        boolean bl = humanoidArm == HumanoidArm.LEFT;
        poseStack.translate((bl ? -0.5F : 0.5F) / 16.0F, 2F/16F, -10F/16F);
        this.renderItem(abstractClientPlayer, itemStack, humanoidArm == HumanoidArm.LEFT ? ItemDisplayContext.THIRD_PERSON_LEFT_HAND : ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, humanoidArm == HumanoidArm.LEFT, poseStack, multiBufferSource, i);
        //this.renderItem(abstractClientPlayer, itemStack, humanoidArm == HumanoidArm.LEFT ? ItemDisplayContext.THIRD_PERSON_LEFT_HAND : ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, humanoidArm == HumanoidArm.LEFT, poseStack, multiBufferSource, i);
        poseStack.popPose();
    }
}
