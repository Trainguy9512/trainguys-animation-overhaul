package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.AnimCurveUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
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
    @Inject(method = "scale", at = @At("HEAD"))
    private void addRunningRotation(AbstractClientPlayer abstractClientPlayer, PoseStack poseStack, float f, CallbackInfo ci){
        float bodyRot = Mth.lerp(f, abstractClientPlayer.yBodyRotO, abstractClientPlayer.yBodyRot);
        float headRot = Mth.lerp(f, abstractClientPlayer.yHeadRotO, abstractClientPlayer.yHeadRot);
        float differenceRot = headRot - bodyRot;
        //TODO: this shouldn't be lerped based on speed, there should be a separate variable to ensure a smooth transition!
        //poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(Mth.clamp(abstractClientPlayer.animationSpeed * 10 - 9, 0, 1), 0, k / -4)));

        boolean isLeftHanded = abstractClientPlayer.getMainArm() == HumanoidArm.LEFT;
        boolean holdingBowInRightHand = !isLeftHanded ? abstractClientPlayer.getMainHandItem().getUseAnimation() == UseAnim.BOW : abstractClientPlayer.getOffhandItem().getUseAnimation() == UseAnim.BOW;
        boolean holdingBowInLeftHand = isLeftHanded ? abstractClientPlayer.getMainHandItem().getUseAnimation() == UseAnim.BOW : abstractClientPlayer.getOffhandItem().getUseAnimation() == UseAnim.BOW;
        if(holdingBowInRightHand && holdingBowInLeftHand){
            holdingBowInRightHand = !isLeftHanded;
            holdingBowInLeftHand = isLeftHanded;
        }

        boolean usingBow = abstractClientPlayer.getUseItem().getItem() == Items.BOW;
        float bowAmount = ((LivingEntityAccess)abstractClientPlayer).getAnimationVariable("leftArmBowPoseAmount");
        float bowPoseAmount = AnimCurveUtils.LinearToEaseInOut(Mth.clamp(usingBow ? bowAmount * 1.5F - 0.5F : bowAmount * 1.5F, 0, 1));
        poseStack.mulPose(Vector3f.YP.rotationDegrees((differenceRot + (holdingBowInRightHand ? -70 : 70)) * bowPoseAmount));

        boolean isRidingInMinecart = abstractClientPlayer.isPassenger() && abstractClientPlayer.getRootVehicle().getType() == EntityType.MINECART;
        if(isRidingInMinecart){
            poseStack.translate(0, -0.5, 0);
        }
    }
}
