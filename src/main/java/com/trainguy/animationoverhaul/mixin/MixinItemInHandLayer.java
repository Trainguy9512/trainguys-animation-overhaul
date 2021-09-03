package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.AnimCurveUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.util.sat4j.core.Vec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandLayer.class)
public class MixinItemInHandLayer {

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getInstance()Lnet/minecraft/client/Minecraft;"))
    private void transformItemInHand(LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){

        HumanoidArm swingingArm = livingEntity.getMainArm();
        HumanoidArm interactionArm = livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? livingEntity.getMainArm() == HumanoidArm.RIGHT ? HumanoidArm.RIGHT : HumanoidArm.LEFT : livingEntity.getMainArm() != HumanoidArm.RIGHT ? HumanoidArm.RIGHT : HumanoidArm.LEFT;
        swingingArm = livingEntity.swingingArm == InteractionHand.MAIN_HAND ? swingingArm : swingingArm.getOpposite();

        if(itemStack.getItem().toString().contains("sword") && swingingArm == humanoidArm){
            float entityAttackAmount = ((LivingEntityAccess)livingEntity).getAnimationVariable("attackAmount");
            float inOutSine = Mth.sin(entityAttackAmount * Mth.PI * 4 - Mth.PI / 2) * 0.5F + 0.5F;
            float entityAttackWeight = AnimCurveUtils.LinearToEaseInOutWeight(entityAttackAmount, 2);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90 * entityAttackWeight));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(90 * entityAttackWeight));
            poseStack.mulPose(Vector3f.XP.rotationDegrees(10 * entityAttackWeight));
            poseStack.translate(0.125 * entityAttackWeight, 0.125 * entityAttackWeight, -0.125 * entityAttackWeight);
        }
        float entityEatingAmount = ((LivingEntityAccess)livingEntity).getAnimationVariable("eatingAmount");
        if(entityEatingAmount > 0 && interactionArm == humanoidArm){
            float eatingAmount = Mth.sin(entityEatingAmount * Mth.PI - Mth.PI * 0.5F) * 0.5F + 0.5F;
            poseStack.mulPose(Vector3f.XP.rotationDegrees(70 * eatingAmount));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees((humanoidArm == HumanoidArm.RIGHT ? 20 : -20) * eatingAmount));
            poseStack.translate((humanoidArm == HumanoidArm.RIGHT ? -0.15 : 0.15) * eatingAmount, -0.125 * eatingAmount, -0.0625 * eatingAmount);
        }
    }
}
