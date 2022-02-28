package gg.moonflower.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import gg.moonflower.animationoverhaul.animations.AnimatorDispatcher;
import gg.moonflower.animationoverhaul.util.animation.BakedPose;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandLayer.class)
public abstract class MixinItemInHandLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public MixinItemInHandLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getInstance()Lnet/minecraft/client/Minecraft;"))
    private void transformItemInHandLayer(LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        if(shouldTransformItemInHand(livingEntity)){
            poseStack.popPose();
            poseStack.pushPose();
            ((ArmedModel)this.getParentModel()).translateToHand(humanoidArm, poseStack);
            poseStack.translate((humanoidArm == HumanoidArm.LEFT ? 1 : -1) /16F, 8/16F, 0);

            String locatorIdentifier = humanoidArm == HumanoidArm.LEFT ? "leftHand" : "rightHand";
            AnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID()).getLocator(locatorIdentifier, Minecraft.getInstance().getFrameTime()).translateAndRotatePoseStack(poseStack);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180));

            //poseStack.mulPose(Vector3f.XP.rotationDegrees(Util.getMillis() / 10F));
            poseStack.translate(0, 2/16F, -2/16F);
        }
    }
    private boolean shouldTransformItemInHand(LivingEntity livingEntity){
        BakedPose bakedPose = AnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());
        if(bakedPose != null){
            if(bakedPose.containsLocator("leftHand") && bakedPose.containsLocator("rightHand")){
                return true;
            }
        }
        return false;
    }
}
