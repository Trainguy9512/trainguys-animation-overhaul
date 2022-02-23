package gg.moonflower.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import gg.moonflower.animationoverhaul.access.LivingEntityAccess;
import gg.moonflower.animationoverhaul.animations.AnimatorDispatcher;
import gg.moonflower.animationoverhaul.util.animation.Locator;
import gg.moonflower.animationoverhaul.util.animation.LocatorRig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandLayer.class)
public abstract class MixinItemInHandLayer {
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

    @Shadow protected abstract void renderArmWithItem(LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int i);

    /*
    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lcom/mojang/math/Quaternion;)V"))
    private void removeBaseRotation(PoseStack instance, Quaternion quaternion){
    }

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"))
    private void removeBaseTranslation(PoseStack instance, double d, double e, double f){
    }

     */

    @Redirect(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/ItemInHandLayer;renderArmWithItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"))
    private void overwriteRenderArmWithItem(ItemInHandLayer instance, LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int i){
        if(shouldUseAlternateHandAnimation(livingEntity)){

            poseStack.pushPose();
            ((ArmedModel)instance.getParentModel()).translateToHand(humanoidArm, poseStack);
            boolean bl = humanoidArm == HumanoidArm.LEFT;
            poseStack.translate((float)(bl ? 1 : -1) / 16.0f, 9F/16F, 0F/16F);

            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0f));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));

            poseStack.pushPose();

            LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
            String identifier = humanoidArm == HumanoidArm.LEFT ? "leftHand" : "rightHand";
            Locator locator = locatorRig.getLocator(identifier, livingEntity.getMainArm() == HumanoidArm.LEFT);

            //locator.reset();
            //locator.xRot = Util.getMillis() / 200F;

            locator.xRot = -locator.xRot;
            locator.translateAndRotatePoseStack(poseStack);

            poseStack.translate(0, 2F/16F, -1F/16F);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntity, itemStack, transformType, bl, poseStack, multiBufferSource, i);

            poseStack.popPose();
            poseStack.popPose();
        } else {
            renderArmWithItem(livingEntity, itemStack, transformType, humanoidArm, poseStack, multiBufferSource, i);
        }
    }

    /*
    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ArmedModel;translateToHand(Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void overwriteTransforms(ArmedModel instance, HumanoidArm humanoidArm, PoseStack poseStack, LivingEntity livingEntity){

        /*
        if(shouldReverseBodyRot(livingEntity)){
            LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
            Locator locator = locatorRig.getLocator("body", false);
            locator.translateAndRotatePoseStackInverse(poseStack);

        }

        instance.translateToHand(humanoidArm, poseStack);

        boolean bl = humanoidArm == HumanoidArm.LEFT;
        poseStack.translate(((float)(bl ? 1 : -1) / 16.0F), 0.625D, -0.125D);

        if(shouldUseAlternateHandAnimation(livingEntity)){
            LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
            String identifier = humanoidArm == HumanoidArm.LEFT ? "leftHand" : "rightHand";
            Locator locator = locatorRig.getLocator(identifier, livingEntity.getMainArm() == HumanoidArm.LEFT);

            locator.rotatePoseStack(poseStack);
        }

        poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0f));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));
    }

     */

    private boolean shouldUseAlternateHandAnimation(LivingEntity livingEntity){
        LocatorRig locatorRig = AnimatorDispatcher.INSTANCE.getLocatorRig(livingEntity.getUUID());
        if(locatorRig != null){
            if(locatorRig.containsLocator("leftHand") && locatorRig.containsLocator("rightHand")){
                return true;
            }
        }
        return false;
    }

}
