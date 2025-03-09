package com.trainguy9512.locomotion.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.locomotion.access.FirstPersonPlayerRendererGetter;
import com.trainguy9512.locomotion.animation.animator.JointAnimatorDispatcher;
import com.trainguy9512.locomotion.animation.animator.JointAnimatorRegistry;
import com.trainguy9512.locomotion.render.FirstPersonPlayerRenderer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Shadow @Final private Minecraft minecraft;

    /**
     * Computes and saves the interpolated animation pose prior to rendering.
     */
    @Inject(
            method = "renderLevel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setup(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/world/entity/Entity;ZZF)V")
    )
    private void computePosePriorToRendering(DeltaTracker deltaTracker, CallbackInfo ci){
        JointAnimatorDispatcher jointAnimatorDispatcher = JointAnimatorDispatcher.getInstance();
        jointAnimatorDispatcher.getFirstPersonPlayerDataContainer().ifPresent(dataContainer ->
                JointAnimatorRegistry.getFirstPersonPlayerJointAnimator().ifPresent(
                        jointAnimator -> {
                            jointAnimatorDispatcher.calculateInterpolatedFirstPersonPlayerPose(jointAnimator, dataContainer, deltaTracker.getGameTimeDeltaPartialTick(true));
                        }
                ));
    }

    /**
     * Transform the camera pose stack based on the first person player's camera joint, prior to bobHurt and bobView.
     */
    @Inject(
            method = "renderLevel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobHurt(Lcom/mojang/blaze3d/vertex/PoseStack;F)V")
    )
    private void addCameraRotation(DeltaTracker deltaTracker, CallbackInfo ci, @Local PoseStack poseStack){
        ((FirstPersonPlayerRendererGetter)this.minecraft.getEntityRenderDispatcher()).locomotion$getFirstPersonPlayerRenderer().ifPresent(firstPersonPlayerRenderer -> firstPersonPlayerRenderer.transformCamera(poseStack));
    }

    /**
     * Redirects the call to render the vanilla item in hand renderer with Locomotion's first person player renderer.
     */
    @Redirect(
            method = "renderItemInHand",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderHandsWithItems(FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;Lnet/minecraft/client/player/LocalPlayer;I)V")
    )
    private void renderLocomotionFirstPersonPlayer(ItemInHandRenderer instance, float partialTicks, PoseStack poseStack, MultiBufferSource.BufferSource buffer, LocalPlayer playerEntity, int combinedLight){
        ((FirstPersonPlayerRendererGetter)this.minecraft.getEntityRenderDispatcher()).locomotion$getFirstPersonPlayerRenderer().ifPresent(firstPersonPlayerRenderer -> firstPersonPlayerRenderer.render(partialTicks, poseStack, buffer, playerEntity, combinedLight));
    }

    /**
     * Remove the view bobbing animation, as the animation pose provides its own.
     * When config is added to enable/disable custom first person rendering, this should be revisited!
     */
    @Inject(
            method = "bobView",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void removeViewBobbing(PoseStack poseStack, float partialTicks, CallbackInfo ci){
        ci.cancel();
    }
}
