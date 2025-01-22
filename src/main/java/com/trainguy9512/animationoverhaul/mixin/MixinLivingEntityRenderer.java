package com.trainguy9512.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.access.LivingEntityRenderStateAccess;
import com.trainguy9512.animationoverhaul.access.ModelAccess;
import com.trainguy9512.animationoverhaul.animation.EntityJointAnimatorDispatcher;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<S extends EntityRenderState, R extends LivingEntityRenderState, T extends LivingEntity, M extends EntityModel<S>> extends EntityRenderer<T, S> implements RenderLayerParent<S, M> {
    protected MixinLivingEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    private final String ROOT = "root";

    @Shadow protected M model;
    @Shadow public abstract M getModel();

    //@Shadow protected abstract void setupRotations(T livingEntity, PoseStack poseStack, float f, float g, float h);


    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("HEAD"))
    private <L extends Enum<L>> void extractAnimationPoseToRenderState(T livingEntity, R livingEntityRenderState, float partialTicks, CallbackInfo ci){
        EntityJointAnimatorDispatcher entityJointAnimatorDispatcher = EntityJointAnimatorDispatcher.INSTANCE;

        // If the entity joint animator dispatcher has animation data for this specific entity under its UUID, and it's registered in the main class.
        if(entityJointAnimatorDispatcher.entityHasBakedAnimationPose(livingEntity.getUUID()) && AnimationOverhaulMain.ENTITY_ANIMATORS.contains(livingEntity.getType())){
            EntityJointAnimator<?, ?, ?, ?> livingEntityJointAnimator = AnimationOverhaulMain.ENTITY_ANIMATORS.get(livingEntity.getType());
            JointSkeleton<?> jointSkeleton = livingEntityJointAnimator.getJointSkeleton();

            // Get the blended animation pose, get the interpolated pose at the current frame, and then save it to the entity render state.
            ((LivingEntityRenderStateAccess)livingEntityRenderState).animationOverhaul$setInterpolatedAnimationPose(entityJointAnimatorDispatcher.getEntityBakedAnimationPose(livingEntity.getUUID(), jointSkeleton).getBlendedPose(partialTicks)
            );
            // Get the joint animator from the registry and save it to the entity render state. This is used for model part application later on.
            ((LivingEntityRenderStateAccess)livingEntityRenderState).animationOverhaul$setEntityJointAnimator(livingEntityJointAnimator);
        }
    }

    @Redirect(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;setupAnim(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;)V"))
    private <L extends Enum<L>> void redirectSetupAnim(EntityModel<S> entityModel, S livingEntityRenderState){
        // Unchecked cast, but I can make assumptions given this is always called after extractRenderState within the same renderer class.
        AnimationPose<L> animationPose = (AnimationPose<L>) ((LivingEntityRenderStateAccess)livingEntityRenderState).animationOverhaul$getInterpolatedAnimationPose();
        EntityJointAnimator<T, S, M, L> entityJointAnimator = (EntityJointAnimator<T, S, M, L>) ((LivingEntityRenderStateAccess)livingEntityRenderState).animationOverhaul$getEntityJointAnimator();


        // If the supplied animation pose is valid and the entity model implements ModelAccess, apply the animation pose. If not, then run the vanilla functionality
        if(animationPose != null && entityModel instanceof ModelAccess){
            ModelPart rootModelPart = ((ModelAccess)entityModel).getRootModelPart();

            for(Enum<L> locator : animationPose.getSkeleton().getJoints()){
                if((animationPose).getSkeleton().getLocatorUsesModelPart(locator)){
                    ModelPart modelPart = rootModelPart;
                    for(String individualPartString : animationPose.getSkeleton().getLocatorModelPartIdentifier(locator).split("\\.")){
                        modelPart = modelPart.getChild(individualPartString);
                    }
                    modelPart.loadPose(animationPose.getJointPoseCopy(locator).asPartPose());
                }
            }
        } else {
            entityModel.setupAnim(livingEntityRenderState);
        }
    }

    @Inject(method = "setupRotations", at = @At("HEAD"), cancellable = true)
    private void overrideSetupRotation(R livingEntityRenderState, PoseStack poseStack, float f, float g, CallbackInfo ci){

        //poseStack.translate(Mth.sin(bob / 6), 0, 0);
        //poseStack.mulPose(Vector3f.ZP.rotation(Mth.sin(bob / 6) / 4));

        AnimationPose<?> animationPose = ((LivingEntityRenderStateAccess)livingEntityRenderState).animationOverhaul$getInterpolatedAnimationPose();

        //BakedAnimationPose<?> bakedPose = EntityJointAnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());

        if(shouldUseAlternateRotations(animationPose)){

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

    @Redirect(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V", ordinal = 0))
    private void removeBedTranslation(PoseStack instance, float f, float g, float h, LivingEntityRenderState livingEntityRenderState){
        AnimationPose<?> animationPose = ((LivingEntityRenderStateAccess)livingEntityRenderState).animationOverhaul$getInterpolatedAnimationPose();
        if(shouldUseAlternateRotations(animationPose)){

        } else {
            instance.translate(f, g, h);
        }
    }




    @Inject(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V"))
    private <L extends Enum<L>> void translateAndRotateAfterScale(R livingEntityRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        AnimationPose<L> animationPose = (AnimationPose<L>) ((LivingEntityRenderStateAccess)livingEntityRenderState).animationOverhaul$getInterpolatedAnimationPose();

        if(shouldUseAlternateRotations(animationPose)){
            poseStack.translate(0, -1.5, 0);

            Enum<L> root = animationPose.getSkeleton().getRootJoint();

            animationPose.getJointPoseCopy(root).translateAndRotatePoseStack(poseStack);
            poseStack.translate(0, 1.5, 0);
        }


    }



    @Unique
    private boolean shouldUseAlternateRotations(AnimationPose<?> animationPose){
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
