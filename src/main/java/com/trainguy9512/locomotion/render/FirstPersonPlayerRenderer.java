package com.trainguy9512.locomotion.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.trainguy9512.locomotion.access.MatrixModelPart;
import com.trainguy9512.locomotion.animation.animator.JointAnimatorDispatcher;
import com.trainguy9512.locomotion.animation.animator.entity.FirstPersonPlayerJointAnimator;
import com.trainguy9512.locomotion.animation.joint.JointChannel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

public class FirstPersonPlayerRenderer {

    private final Minecraft minecraft;
    private final EntityRenderDispatcher entityRenderDispatcher;
    private final ItemRenderer itemRenderer;
    private final ItemModelResolver itemModelResolver;
    private final JointAnimatorDispatcher jointAnimatorDispatcher;

    public FirstPersonPlayerRenderer(Minecraft minecraft, EntityRenderDispatcher entityRenderDispatcher, ItemRenderer itemRenderer, ItemModelResolver itemModelResolver) {
        this.minecraft = minecraft;
        this.entityRenderDispatcher = entityRenderDispatcher;
        this.itemRenderer = itemRenderer;
        this.itemModelResolver = itemModelResolver;
        this.jointAnimatorDispatcher = JointAnimatorDispatcher.getInstance();
    }

    public void render(float partialTicks, PoseStack poseStack, MultiBufferSource.BufferSource buffer, LocalPlayer playerEntity, int combinedLight) {
        JointAnimatorDispatcher jointAnimatorDispatcher = JointAnimatorDispatcher.getInstance();

        JointAnimatorDispatcher.getInstance().getFirstPersonPlayerDataContainer().ifPresent(
                dataContainer -> jointAnimatorDispatcher.getInterpolatedFirstPersonPlayerPose().ifPresent(
                        animationPose -> {

                            JointChannel rightArmPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.RIGHT_ARM_JOINT);
                            JointChannel leftArmPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.LEFT_ARM_JOINT);
                            JointChannel rightItemPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.RIGHT_ITEM_JOINT);
                            JointChannel leftItemPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.LEFT_ITEM_JOINT);

                            poseStack.pushPose();
                            poseStack.mulPose(Axis.ZP.rotationDegrees(180));


                            AbstractClientPlayer abstractClientPlayer = this.minecraft.player;
                            PlayerRenderer playerRenderer = (PlayerRenderer)this.entityRenderDispatcher.getRenderer(abstractClientPlayer);
                            PlayerModel playerModel = playerRenderer.getModel();
                            playerModel.resetPose();

                            ((MatrixModelPart)(Object) playerModel.rightArm).locomotion$setMatrix(rightArmPose.getTransform());
                            ((MatrixModelPart)(Object) playerModel.leftArm).locomotion$setMatrix(leftArmPose.getTransform());


                            playerModel.rightArm.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), combinedLight, OverlayTexture.NO_OVERLAY);
                            playerModel.leftArm.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), combinedLight, OverlayTexture.NO_OVERLAY);

                            this.renderItem(abstractClientPlayer, dataContainer.getDriverValueInterpolated(FirstPersonPlayerJointAnimator.MAIN_HAND_ITEM, partialTicks), ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, false, poseStack, rightItemPose, buffer, combinedLight);
                            //this.renderItemInHand(abstractClientPlayer, ItemStack.EMPTY, poseStack, HumanoidArm.LEFT, animationPose, bufferSource, i);


                            //playerRenderer.renderRightHand(poseStack, bufferSource, i, abstractClientPlayer);
                            //poseStack.popPose();
                            poseStack.popPose();
                        }
                )
        );

        buffer.endBatch();
    }

    public void transformCamera(PoseStack poseStack){
        if(this.minecraft.options.getCameraType().isFirstPerson()){
            this.jointAnimatorDispatcher.getInterpolatedFirstPersonPlayerPose().ifPresent(animationPose -> {
                JointChannel cameraPose = animationPose.getJointTransform(FirstPersonPlayerJointAnimator.CAMERA_JOINT);

                Vector3f cameraRot = cameraPose.getEulerRotationZYX();
                cameraRot.z *= -1;
                cameraPose.rotate(cameraRot, JointChannel.TransformSpace.LOCAL, JointChannel.TransformType.REPLACE);

                poseStack.mulPose(cameraPose.getTransform().setTranslation(cameraPose.getTransform().getTranslation(new Vector3f().div(16f))));
            });
        }
    }



    public void renderItem(LivingEntity entity, ItemStack itemStack, ItemDisplayContext displayContext, boolean isLeftHand, PoseStack poseStack, JointChannel jointChannel, MultiBufferSource buffer, int combinedLight) {
        if (!itemStack.isEmpty()) {
            poseStack.pushPose();
            jointChannel.transformPoseStack(poseStack, 16f);

            //TODO: this code needs to be replaced with proper joint transforms
            //poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            //poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            //poseStack.translate((isLeftHand ? -0.5F : 0.5F) / 16.0F, 2F/16F, -10F/16F);


            this.itemRenderer.renderStatic(entity, itemStack, displayContext, isLeftHand, poseStack, buffer, entity.level(), combinedLight, OverlayTexture.NO_OVERLAY, entity.getId() + displayContext.ordinal());
            poseStack.popPose();
        }
    }
}
