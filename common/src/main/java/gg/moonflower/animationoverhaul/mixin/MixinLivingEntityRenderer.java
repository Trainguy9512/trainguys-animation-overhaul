package gg.moonflower.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import gg.moonflower.animationoverhaul.animations.AnimatorDispatcher;
import gg.moonflower.animationoverhaul.animations.entity.LivingEntityPartAnimator;
import gg.moonflower.animationoverhaul.util.animation.LocatorRig;
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
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    protected MixinLivingEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    private final String ROOT = "root";

    @Shadow protected M model;
    @Shadow public abstract M getModel();

    @Shadow protected abstract void setupRotations(T livingEntity, PoseStack poseStack, float f, float g, float h);

    @Redirect(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V"))
    private void redirectSetupAnim(M entityModel, Entity t, float a, float b, float c, float d, float e, T livingEntity, float f, float g, PoseStack poseStack){
        if(!AnimatorDispatcher.INSTANCE.animateEntity(livingEntity, entityModel, poseStack, g)){
            entityModel.setupAnim(livingEntity, a, b, c, d, e);
        }
    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;setupRotations(Lnet/minecraft/world/entity/LivingEntity;Lcom/mojang/blaze3d/vertex/PoseStack;FFF)V"))
    private void overwriteSetupRotations(LivingEntityRenderer<T,M> instance, T livingEntity, PoseStack poseStack, float bob, float bodyRot, float frameTime){

        //poseStack.translate(Mth.sin(bob / 6), 0, 0);
        //poseStack.mulPose(Vector3f.ZP.rotation(Mth.sin(bob / 6) / 4));

        LocatorRig locatorRig = AnimatorDispatcher.INSTANCE.getLocatorRig(livingEntity.getUUID());

        if(shouldUseAlternateRotations(locatorRig)){

            poseStack.popPose();
            poseStack.pushPose();

            if(livingEntity.getPose() == Pose.SLEEPING){
                Direction i = ((LivingEntity)livingEntity).getBedOrientation();
                float j = i != null ? sleepDirectionToRotation(i) : bodyRot;
                poseStack.mulPose(Vector3f.YP.rotationDegrees(j - 90));
            } else {
                float lerpedBodyYRot = AnimatorDispatcher.INSTANCE.getEntityAnimationData(livingEntity).getLerped(LivingEntityPartAnimator.BODY_Y_ROT, frameTime);
                if(livingEntity.isPassenger() && livingEntity.getVehicle() instanceof AbstractMinecart){
                    bodyRot = Mth.rotLerp(frameTime, ((LivingEntity)livingEntity).yHeadRotO, ((LivingEntity)livingEntity).yHeadRot);
                }
                poseStack.mulPose(Vector3f.YP.rotationDegrees(180 - bodyRot));
            }

        } else {
            this.setupRotations(livingEntity, poseStack, bob, bodyRot, frameTime);
        }
    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 0))
    private void removeBedTranslation(PoseStack instance, double d, double e, double f, T livingEntity){
        LocatorRig locatorRig = AnimatorDispatcher.INSTANCE.getLocatorRig(livingEntity.getUUID());
        if(shouldUseAlternateRotations(locatorRig)){

        } else {
            instance.translate(d, e, f);
        }
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"))
    private void translateAndRotateAfterScale(T livingEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){

        LocatorRig locatorRig = AnimatorDispatcher.INSTANCE.getLocatorRig(livingEntity.getUUID());
        if(shouldUseAlternateRotations(locatorRig)){
            poseStack.translate(0, -1.5, 0);
            locatorRig.getLocator(ROOT, false).translateAndRotatePoseStack(poseStack);
            poseStack.translate(0, 1.5, 0);
        }
    }

    private boolean shouldUseAlternateRotations(LocatorRig locatorRig){
        if(locatorRig != null){
            if(locatorRig.containsLocator(ROOT)){
                return true;
            }
        }
        return false;
    }

    private static float sleepDirectionToRotation(Direction direction) {
        switch (direction) {
            case SOUTH: {
                return 90.0f;
            }
            case WEST: {
                return 0.0f;
            }
            case NORTH: {
                return 270.0f;
            }
            case EAST: {
                return 180.0f;
            }
        }
        return 0.0f;
    }
}
