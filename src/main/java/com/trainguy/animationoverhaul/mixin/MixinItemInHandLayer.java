package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.animation.LocatorRig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandLayer.class)
public class MixinItemInHandLayer {
    /*
    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ArmedModel;translateToHand(Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void removeArmItemParent(ArmedModel instance, HumanoidArm humanoidArm, PoseStack poseStack, LivingEntity livingEntity){
        instance.translateToHand(humanoidArm, poseStack);
        if(shouldUseAlternateHandAnimation(livingEntity)){
            LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
            String identifier = humanoidArm == HumanoidArm.LEFT ? "leftHand" : "rightHand";
            locatorRig.getLocator(identifier, livingEntity.getMainArm() == HumanoidArm.LEFT).rotate(poseStack);
        }
    }

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"))
    private void removeArmItemTranslation(PoseStack instance, double d, double e, double f, LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack){
        boolean bl = humanoidArm == HumanoidArm.LEFT;

        if(shouldUseAlternateHandAnimation(livingEntity)){
            LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
            String identifier = humanoidArm == HumanoidArm.LEFT ? "leftHand" : "rightHand";
            Locator locator = locatorRig.getLocator(identifier, livingEntity.getMainArm() == HumanoidArm.LEFT);

            poseStack.translate((locator.x / 16.0F) + ((float)(bl ? -1 : 1) / 16.0F), (locator.y / 16.0F) + 0.125D, (locator.z / 16.0F) - 0.625D);
        } else {
            instance.translate(((float)(bl ? -1 : 1) / 16.0F), 0.125D, -0.625D);
        }
    }


    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getInstance()Lnet/minecraft/client/Minecraft;"))
    private void overwriteItemInHandTransform(LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        poseStack.popPose();
        poseStack.pushPose();

        boolean bl = humanoidArm == HumanoidArm.LEFT;
        poseStack.translate(((float)(bl ? -1 : 1) / 16.0F), 0.125D, -0.625D);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
    }

     */

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lcom/mojang/math/Quaternion;)V"))
    private void removeBaseRotation(PoseStack instance, Quaternion quaternion){
        instance.mulPose(Vector3f.ZERO.rotation(0));
    }

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"))
    private void removeBaseTranslation(PoseStack instance, double d, double e, double f){
        instance.translate(0, 0, 0);
    }

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ArmedModel;translateToHand(Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void overwriteTransforms(ArmedModel instance, HumanoidArm humanoidArm, PoseStack poseStack, LivingEntity livingEntity){

        instance.translateToHand(humanoidArm, poseStack);

        boolean bl = humanoidArm == HumanoidArm.LEFT;
        poseStack.translate(((float)(bl ? 1 : -1) / 16.0F), 0.625D, -0.125D);

        if(shouldUseAlternateHandAnimation(livingEntity)){
            LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
            String identifier = humanoidArm == HumanoidArm.LEFT ? "leftHand" : "rightHand";
            locatorRig.getLocator(identifier, livingEntity.getMainArm() == HumanoidArm.LEFT).translateAndRotatePoseStack(poseStack);
        }
        poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
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
