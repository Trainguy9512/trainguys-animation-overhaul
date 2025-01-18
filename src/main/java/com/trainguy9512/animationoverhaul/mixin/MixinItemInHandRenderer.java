package com.trainguy9512.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.trainguy9512.animationoverhaul.animation.animator.FirstPersonPlayerJointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointPose;
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
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
    private void overwriteItemInHandRendering(float f, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, LocalPlayer localPlayer, int i, CallbackInfo ci){

        if(FirstPersonPlayerJointAnimator.INSTANCE.localBakedPose != null){
            AnimationPose<FirstPersonPlayerJointAnimator.FPPlayerJoints> animationPose = FirstPersonPlayerJointAnimator.INSTANCE.localBakedPose.getBlendedPose(f);
            JointPose rightArmPose = animationPose.getJointPoseCopy(FirstPersonPlayerJointAnimator.FPPlayerJoints.rightArm);
            JointPose leftArmPose = animationPose.getJointPoseCopy(FirstPersonPlayerJointAnimator.FPPlayerJoints.leftArm);
            JointPose rightHandPose = animationPose.getJointPoseCopy(FirstPersonPlayerJointAnimator.FPPlayerJoints.rightHand);
            JointPose leftHandPose = animationPose.getJointPoseCopy(FirstPersonPlayerJointAnimator.FPPlayerJoints.leftHand);

            poseStack.pushPose();
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));
            //poseStack.pushPose();



            AbstractClientPlayer abstractClientPlayer = this.minecraft.player;
            //RenderSystem.setShaderTexture(0, abstractClientPlayer.getSkin().texture());
            PlayerRenderer playerRenderer = (PlayerRenderer)this.entityRenderDispatcher.getRenderer(abstractClientPlayer);
            PlayerModel playerModel = playerRenderer.getModel();


            rightArmPose.transformModelPart(playerModel.rightArm);
            playerModel.rightSleeve.copyFrom(playerModel.rightArm);
            leftArmPose.transformModelPart(playerModel.leftArm);
            playerModel.leftSleeve.copyFrom(playerModel.leftArm);

            /*

            OLD POSE STACK PUSH STUFF

            playerModel.rightArm.setRotation(0,0,0);
            playerModel.rightArm.setPos(0,0,0);
            playerModel.rightSleeve.copyFrom(playerModel.rightArm);
            playerModel.leftArm.setRotation(0,0,0);
            playerModel.leftArm.setPos(0,0,0);
            playerModel.leftSleeve.copyFrom(playerModel.leftArm);

            poseStack.pushPose();
            animationPose.getLocatorPose(FirstPersonPlayerAnimator.FPPlayerLocators.armBuffer).transformPoseStack(poseStack);
            poseStack.pushPose();
            animationPose.getLocatorPose(FirstPersonPlayerAnimator.FPPlayerLocators.leftArmBuffer).transformPoseStack(poseStack);
            poseStack.pushPose();
            animationPose.getLocatorPose(FirstPersonPlayerAnimator.FPPlayerLocators.leftArm).transformPoseStack(poseStack);
            playerModel.leftArm.render(poseStack, bufferSource.getBuffer(RenderType.entitySolid(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);
            playerModel.leftSleeve.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);

            poseStack.popPose();
            poseStack.popPose();


            poseStack.pushPose();
            animationPose.getLocatorPose(FirstPersonPlayerAnimator.FPPlayerLocators.rightArmBuffer).transformPoseStack(poseStack);
            poseStack.pushPose();
            animationPose.getLocatorPose(FirstPersonPlayerAnimator.FPPlayerLocators.rightArm).transformPoseStack(poseStack);
            playerModel.rightArm.render(poseStack, bufferSource.getBuffer(RenderType.entitySolid(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);
            playerModel.rightSleeve.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);



            poseStack.popPose();


             */

            playerModel.rightArm.render(poseStack, bufferSource.getBuffer(RenderType.entitySolid(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);
            playerModel.rightSleeve.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);
            playerModel.leftArm.render(poseStack, bufferSource.getBuffer(RenderType.entitySolid(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);
            playerModel.leftSleeve.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), i, OverlayTexture.NO_OVERLAY);


            /*
            poseStack.pushPose();
            rightArmPose.transformPoseStack(poseStack);
            poseStack.translate((/*humanoidArm == HumanoidArm.LEFTfalse ? 1 : -1) /16F, 9/16F, 0);
            rightHandPose.transformPoseStack(poseStack);
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0f));
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
            poseStack.translate(0F, 2F/16F, -1F/16F);
            renderItem(abstractClientPlayer, Items.DIAMOND.getDefaultInstance(), ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, true, poseStack, bufferSource, i);
            poseStack.popPose();

             */



            this.renderItemInHand(abstractClientPlayer, FirstPersonPlayerJointAnimator.INSTANCE.localAnimationDataContainer.getAnimationVariable(FirstPersonPlayerJointAnimator.MAIN_HAND_ITEM).get(), poseStack, HumanoidArm.RIGHT, animationPose, bufferSource, i);
            //this.renderItemInHand(abstractClientPlayer, ItemStack.EMPTY, poseStack, HumanoidArm.LEFT, animationPose, bufferSource, i);


            //playerRenderer.renderRightHand(poseStack, bufferSource, i, abstractClientPlayer);
            //poseStack.popPose();
            poseStack.popPose();
        }

        bufferSource.endBatch();
        ci.cancel();
    }

    private void renderItemInHand(AbstractClientPlayer abstractClientPlayer, ItemStack itemStack, PoseStack poseStack, HumanoidArm humanoidArm, AnimationPose<FirstPersonPlayerJointAnimator.FPPlayerJoints> animationPose, MultiBufferSource multiBufferSource, int i){

        JointPose armPose = animationPose.getJointPoseCopy(humanoidArm == HumanoidArm.LEFT ? FirstPersonPlayerJointAnimator.FPPlayerJoints.leftArm : FirstPersonPlayerJointAnimator.FPPlayerJoints.rightArm);
        JointPose handPose = animationPose.getJointPoseCopy(humanoidArm == HumanoidArm.LEFT ? FirstPersonPlayerJointAnimator.FPPlayerJoints.leftHand : FirstPersonPlayerJointAnimator.FPPlayerJoints.rightHand);



        poseStack.pushPose();
        //armPose.transformPoseStack(poseStack);
        //poseStack.translate((humanoidArm == HumanoidArm.LEFT ? 1 : -1) /16F, 9/16F, 0);
        handPose.transformPoseStack(poseStack, 16F);
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0f));

        //poseStack.mulPose(Axis.XP.rotationDegrees(-90.0f));
        //poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
        //poseStack.translate(0F, 2F/16F, -1F/16F);
        this.renderItem(abstractClientPlayer, itemStack, humanoidArm == HumanoidArm.LEFT ? ItemDisplayContext.THIRD_PERSON_LEFT_HAND : ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, humanoidArm == HumanoidArm.LEFT, poseStack, multiBufferSource, i);
        //this.renderItem(abstractClientPlayer, itemStack, humanoidArm == HumanoidArm.LEFT ? ItemDisplayContext.THIRD_PERSON_LEFT_HAND : ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, humanoidArm == HumanoidArm.LEFT, poseStack, multiBufferSource, i);
        poseStack.popPose();
    }
}
