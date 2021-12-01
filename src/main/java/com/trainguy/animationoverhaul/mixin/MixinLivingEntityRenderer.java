package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.animations.LivingEntityAnimationController;
import com.trainguy.animationoverhaul.animations.LivingEntityPartAnimator;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.animation.LocatorRig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.SkyLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>, P extends LivingEntityPartAnimator<T, M>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    protected MixinLivingEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Shadow protected abstract float getBob(T livingEntity, float f);
    @Shadow protected M model;
    @Shadow public abstract M getModel();

    @Shadow protected abstract void setupRotations(T livingEntity, PoseStack poseStack, float f, float g, float h);

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;isBodyVisible(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    private void setPartController(T livingEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        float tickAtFrame = this.getBob(livingEntity, g);
        float delta = Minecraft.getInstance().getDeltaFrameTime();


        float h = Mth.rotLerp(g, livingEntity.yBodyRotO, livingEntity.yBodyRot);
        float j = Mth.rotLerp(g, livingEntity.yHeadRotO, livingEntity.yHeadRot);
        float k = j - h;
        float o;
        if (livingEntity.isPassenger() && livingEntity.getVehicle() instanceof LivingEntity livingEntity2) {
            h = Mth.rotLerp(g, livingEntity2.yBodyRotO, livingEntity2.yBodyRot);
            k = j - h;
            o = Mth.wrapDegrees(k);
            if (o < -85.0F) {
                o = -85.0F;
            }

            if (o >= 85.0F) {
                o = 85.0F;
            }

            h = j - o;
            if (o * o > 2500.0F) {
                h += o * 0.2F;
            }

            k = j - h;
        }

        float headXRot = (float) Math.toRadians(Mth.lerp(g, livingEntity.xRotO, livingEntity.getXRot()));
        float headYRot = (float) Math.toRadians(k);
        LivingEntityAnimParams livingEntityAnimParams = new LivingEntityAnimParams(tickAtFrame, g, delta, headYRot, headXRot, i);

        LivingEntityAnimationController<T, P, M> livingEntityAnimationController = new LivingEntityAnimationController<>(livingEntity, this.model, livingEntityAnimParams);
        livingEntityAnimationController.animate();
    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;setupRotations(Lnet/minecraft/world/entity/LivingEntity;Lcom/mojang/blaze3d/vertex/PoseStack;FFF)V"))
    private void overwriteSetupRotations(LivingEntityRenderer<T,M> instance, T livingEntity, PoseStack poseStack, float bob, float bodyRot, float frameTime){


        //poseStack.translate(Mth.sin(bob / 6), 0, 0);
        //poseStack.mulPose(Vector3f.ZP.rotation(Mth.sin(bob / 6) / 4));

        if(shouldUseAlternateRotations(livingEntity)){

            if (livingEntity.getPose() != Pose.SLEEPING) {
                poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f - bodyRot));
            }

            LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
            locatorRig.getLocator("master", false).translateAndRotatePoseStack(poseStack);
        } else {
            this.setupRotations(livingEntity, poseStack, bob, bodyRot, frameTime);
        }
    }

    private boolean shouldUseAlternateRotations(LivingEntity livingEntity){
        LocatorRig locatorRig = ((LivingEntityAccess)livingEntity).getLocatorRig();
        if(locatorRig != null){
            if(locatorRig.containsLocator("master")){
                return true;
            }
        }
        return false;
    }
}
