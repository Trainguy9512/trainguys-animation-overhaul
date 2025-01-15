package com.trainguy9512.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.trainguy9512.animationoverhaul.animation.EntityJointAnimatorDispatcher;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<S extends EntityRenderState, L extends LivingEntityRenderState, T extends LivingEntity, M extends EntityModel<S>> extends EntityRenderer<T, S> implements RenderLayerParent<S, M> {
    protected MixinLivingEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    private final String ROOT = "root";

    @Shadow protected M model;
    @Shadow public abstract M getModel();

    @Shadow protected abstract void setupRotations(T livingEntity, PoseStack poseStack, float f, float g, float h);



    @Redirect(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;setupAnim(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;)V"))
    private void redirectSetupAnim(EntityModel<S> instance, S entityRenderState){
        if(!EntityJointAnimatorDispatcher.INSTANCE.animateEntity(livingEntity, instance, poseStack, g)){
            instance.setupAnim(entityRenderState);
        }
    }

    @Inject(method = "setupRotations", at = @At("HEAD"), cancellable = true)
    private void overrideSetupRotation(L livingEntityRenderState, PoseStack poseStack, float f, float g, CallbackInfo ci){

        //poseStack.translate(Mth.sin(bob / 6), 0, 0);
        //poseStack.mulPose(Vector3f.ZP.rotation(Mth.sin(bob / 6) / 4));

        BakedAnimationPose<?> bakedPose = EntityJointAnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());

        if(shouldUseAlternateRotations(bakedPose)){

            poseStack.popPose();
            poseStack.pushPose();


            if(livingEntityRenderState.pose == Pose.SLEEPING){

                Direction i = livingEntityRenderState.bedOrientation;
                float j = i != null ? sleepDirectionToRotation(i) : livingEntityRenderState.bodyRot;
                poseStack.mulPose(Axis.YP.rotationDegrees(j - 90));
            } else {

                /*

                float bodyRot = 0 ;//Mth.rotLerp(frameTime, ((LivingEntity)livingEntity).yHeadRotO, ((LivingEntity)livingEntity).yHeadRot);
                //bodyRot = AnimatorDispatcher.INSTANCE.getEntityAnimationData(livingEntity).getLerped(LivingEntityPartAnimator.BODY_Y_ROT, frameTime);
                if(livingEntityRenderState.passengerOffset != null){
                    bodyRot = Mth.rotLerp(frameTime, ((LivingEntity)livingEntity).yHeadRotO, ((LivingEntity)livingEntity).yHeadRot);
                }

                 */

                //poseStack.mulPose(Axis.YP.rotationDegrees(180 - bodyRot));
            }
            ci.cancel();
        }
    }

    @Redirect(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;setupRotations(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;FF)V"))
    private void overwriteSetupRotations(LivingEntityRenderer<T, L, M> instance, L livingEntityRenderState, PoseStack poseStack, float f, float g){


    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V", ordinal = 0))
    private void removeBedTranslation(PoseStack instance, float d, float e, float f, T livingEntity){
        BakedAnimationPose<?> bakedPose = EntityJointAnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());
        if(shouldUseAlternateRotations(bakedPose)){

        } else {
            instance.translate(d, e, f);
        }
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V"))
    private void translateAndRotateAfterScale(T livingEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        /*
        BakedAnimationPose bakedPose = AnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());
        if(shouldUseAlternateRotations(bakedPose)){
            poseStack.translate(0, -1.5, 0);
            bakedPose.getBlendedPose(g).getLocatorPose(ROOT).translateAndRotatePoseStack(poseStack);
            poseStack.translate(0, 1.5, 0);
        }

         */
    }

    @Unique
    private boolean shouldUseAlternateRotations(BakedAnimationPose<?> bakedPose){
        /*
        if(bakedPose != null){
            if(bakedPose.containsLocator(ROOT)){
                return true;
            }
        }
        return false;

         */
        return false;
    }

    @Unique
    private static float sleepDirectionToRotation(Direction direction) {
        return switch (direction) {
            case SOUTH -> 90.0f;
            case WEST -> 0.0f;
            case NORTH -> 270.0f;
            case EAST -> 180.0f;
            default -> 0.0f;
        };
    }
}
