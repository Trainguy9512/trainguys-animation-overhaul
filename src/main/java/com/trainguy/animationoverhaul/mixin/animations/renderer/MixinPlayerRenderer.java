package com.trainguy.animationoverhaul.mixin.animations.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.Easing;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRenderer {
    @Inject(method = "scale", at = @At("HEAD"), cancellable = true)
    private void modifyPlayerRendererTransforms(AbstractClientPlayer abstractClientPlayer, PoseStack poseStack, float f, CallbackInfo ci){
        // Variables
        float bodyRot = Mth.lerp(f, abstractClientPlayer.yBodyRotO, abstractClientPlayer.yBodyRot);
        float headRot = Mth.lerp(f, abstractClientPlayer.yHeadRotO, abstractClientPlayer.yHeadRot);
        float differenceRot = headRot - bodyRot;

        // Bow player rotation
        boolean isLeftHanded = abstractClientPlayer.getMainArm() == HumanoidArm.LEFT;
        boolean holdingBowInRightHand = !isLeftHanded ? abstractClientPlayer.getMainHandItem().getUseAnimation() == UseAnim.BOW : abstractClientPlayer.getOffhandItem().getUseAnimation() == UseAnim.BOW;
        boolean holdingBowInLeftHand = isLeftHanded ? abstractClientPlayer.getMainHandItem().getUseAnimation() == UseAnim.BOW : abstractClientPlayer.getOffhandItem().getUseAnimation() == UseAnim.BOW;
        if(holdingBowInRightHand && holdingBowInLeftHand){
            holdingBowInRightHand = !isLeftHanded;
        }
        boolean usingBow = abstractClientPlayer.getUseItem().getItem() == Items.BOW;

        float bowAmount = ((EntityAccess)abstractClientPlayer).getAnimationTimer("bow_pose");
        float bowPoseAmount = Easing.CubicBezier.bezierInOutQuad().ease(Mth.clamp(usingBow ? bowAmount * 1.5F - 0.5F : bowAmount * 1.5F, 0, 1));
        poseStack.mulPose(Vector3f.YP.rotationDegrees((differenceRot + (holdingBowInRightHand ? -70 : 70)) * bowPoseAmount));

        // Riding in minecart stuff
        boolean isRidingInMinecart = abstractClientPlayer.isPassenger() && abstractClientPlayer.getRootVehicle().getType() == EntityType.MINECART;
        if(isRidingInMinecart){
            poseStack.translate(0, -0.5, 0);
        }

        // Swimming/Crawling rotation
        float swimAmount = abstractClientPlayer.getSwimAmount(f);
        if(swimAmount > 0){
            float staticBodyRotationX = abstractClientPlayer.isInWater() ? -90.0F - abstractClientPlayer.getXRot() : -90.0F;
            float oldBodyRotationX = Mth.lerp(swimAmount, 0.0F, staticBodyRotationX);
            if (abstractClientPlayer.isVisuallySwimming()) {
                poseStack.translate(0.0D, -1, 0);
            }
            poseStack.mulPose(Vector3f.XP.rotationDegrees(oldBodyRotationX));
            float smoothSwimAmount = Easing.CubicBezier.bezierInOutQuad().ease(swimAmount);
            float bodyRotationX = Mth.lerp(smoothSwimAmount, 0.0F, staticBodyRotationX);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-bodyRotationX));
            poseStack.translate(0.0D, 1 * smoothSwimAmount, 0F * smoothSwimAmount);
        }

        // Creative fast flying
        float creativeFlyWeight = ((EntityAccess) abstractClientPlayer).getAnimationTimer("creative_flying");
        float creativeFastFlyWeight = ((EntityAccess) abstractClientPlayer).getAnimationTimer("creative_fast_flying");
        if(creativeFlyWeight * creativeFastFlyWeight > 0){
            float creativeFastFlyWeightEased = Easing.CubicBezier.bezierInOutQuad().ease(creativeFastFlyWeight * creativeFlyWeight);
            float creativeFlyUpWeight = Easing.CubicBezier.bezierInOutQuad().ease(((EntityAccess) abstractClientPlayer).getAnimationTimer("creative_flying_up"));
            float creativeFlyDownWeight = Easing.CubicBezier.bezierInOutQuad().ease(((EntityAccess) abstractClientPlayer).getAnimationTimer("creative_flying_down"));
            creativeFastFlyWeightEased += (0.2F * creativeFlyDownWeight) + (-0.2F * creativeFlyUpWeight);
            poseStack.translate(0.0D, -1 * creativeFastFlyWeightEased, 1 * creativeFastFlyWeightEased);
            poseStack.mulPose(Vector3f.XP.rotationDegrees((90 * creativeFastFlyWeightEased)));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(differenceRot * creativeFastFlyWeightEased * -1.25F));
        }
    }
}
