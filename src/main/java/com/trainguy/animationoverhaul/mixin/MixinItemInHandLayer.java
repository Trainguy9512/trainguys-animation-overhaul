package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.animation.Locator;
import com.trainguy.animationoverhaul.util.animation.LocatorRig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandLayer.class)
public class MixinItemInHandLayer {

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"))
    private void removeArmItemTranslation(PoseStack instance, double d, double e, double f, LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm){
        boolean bl = humanoidArm == HumanoidArm.LEFT;
        if(!shouldUseAlternateHandAnimation(livingEntity)){
            instance.translate((double)((float)(bl ? -1 : 1) / 16.0F), 0.125D, -0.625D);
        }
    }

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ArmedModel;translateToHand(Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void removeArmItemParent(ArmedModel instance, HumanoidArm humanoidArm, PoseStack poseStack, LivingEntity livingEntity){
        if(shouldUseAlternateHandAnimation(livingEntity)){
            LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
            String identifier = humanoidArm == HumanoidArm.LEFT ? "leftHand" : "rightHand";
            locatorRig.getLocator(identifier, livingEntity.getMainArm() == HumanoidArm.LEFT).translateAndRotate(poseStack);
        } else {
            instance.translateToHand(humanoidArm, poseStack);
        }
    }

    private boolean shouldUseAlternateHandAnimation(LivingEntity livingEntity){
        LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
        if(locatorRig != null){
            if(locatorRig.containsLocator("leftHand") && locatorRig.containsLocator("rightHand")){
                return true;
            }
        }
        return false;
    }
}
