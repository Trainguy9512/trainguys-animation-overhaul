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
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class FirstPersonPlayerRenderer implements RenderLayerParent<PlayerRenderState, PlayerModel> {

    private final Minecraft minecraft;
    private final EntityRenderDispatcher entityRenderDispatcher;
    private final ItemRenderer itemRenderer;
    private final ItemModelResolver itemModelResolver;
    private final JointAnimatorDispatcher jointAnimatorDispatcher;

    public FirstPersonPlayerRenderer(EntityRendererProvider.Context context) {
        this.minecraft = Minecraft.getInstance();
        this.entityRenderDispatcher = context.getEntityRenderDispatcher();
        this.itemRenderer = minecraft.getItemRenderer();
        this.itemModelResolver = context.getItemModelResolver();
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

                            playerModel.body.visible = false;

                            this.renderArm(abstractClientPlayer, playerModel, HumanoidArm.LEFT, poseStack, buffer, combinedLight);
                            this.renderArm(abstractClientPlayer, playerModel, HumanoidArm.RIGHT, poseStack, buffer, combinedLight);

                            //this.entityRenderDispatcher.render(abstractClientPlayer, 0, 0, 0, partialTicks, poseStack, buffer, combinedLight);

                            this.renderItem(abstractClientPlayer, dataContainer.getDriverValue(FirstPersonPlayerJointAnimator.MAIN_HAND_ITEM, partialTicks), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, poseStack, rightItemPose, buffer, combinedLight);
                            this.renderItem(abstractClientPlayer, dataContainer.getDriverValue(FirstPersonPlayerJointAnimator.OFF_HAND_ITEM, partialTicks), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, true, poseStack, leftItemPose, buffer, combinedLight);
                            //this.renderItemInHand(abstractClientPlayer, ItemStack.EMPTY, poseStack, HumanoidArm.LEFT, animationPose, bufferSource, i);


                            //playerRenderer.renderRightHand(poseStack, bufferSource, i, abstractClientPlayer);
                            //poseStack.popPose();
                            poseStack.popPose();
                        }
                )
        );

        buffer.endBatch();
    }

    private void renderArm(AbstractClientPlayer abstractClientPlayer, PlayerModel playerModel, HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int combinedLight) {
        switch(arm){
            case LEFT -> {
                playerModel.leftSleeve.visible = abstractClientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
                playerModel.leftArm.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), combinedLight, OverlayTexture.NO_OVERLAY);
            }
            case RIGHT -> {
                playerModel.rightSleeve.visible = abstractClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
                playerModel.rightArm.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkin().texture())), combinedLight, OverlayTexture.NO_OVERLAY);
            }
        }
    }

    public void renderItem(
            LivingEntity entity,
            ItemStack itemStack,
            ItemDisplayContext displayContext,
            boolean isLeftHand,
            PoseStack poseStack,
            JointChannel jointChannel,
            MultiBufferSource buffer,
            int combinedLight)
    {
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

    @Override
    public @NotNull PlayerModel getModel() {
        return ((PlayerRenderer)entityRenderDispatcher.getRenderer(minecraft.player)).getModel();
    }
}
