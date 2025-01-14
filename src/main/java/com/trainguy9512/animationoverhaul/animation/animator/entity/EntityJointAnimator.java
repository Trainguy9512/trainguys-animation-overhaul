package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.access.ModelAccess;
import com.trainguy9512.animationoverhaul.animation.EntityJointAnimatorDispatcher;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public abstract class EntityJointAnimator<R extends EntityRenderState, M extends EntityModel<R>, L extends Enum<L>> extends JointAnimator<T, L> {


    //protected AnimationDataContainer entityAnimationData;

    public EntityJointAnimator(){
        super();
    }

    /*
    protected WalkAnimationState getWalkAnimationState(){
        return this.livingEntity != null ? this.livingEntity.walkAnimation : new WalkAnimationState();
    }

     */

    /*
    public void overallTick(LivingEntity livingEntity){
        BakedAnimationPose<L> bakedPose = EntityJointAnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());
        AnimationDataContainer entityAnimationData = EntityJointAnimatorDispatcher.INSTANCE.getEntityAnimationData(livingEntity.getUUID());

        this.tick(livingEntity, entityAnimationData);

        entityAnimationData.tickAnimationStates();

        if(bakedPose == null){
            bakedPose = new BakedAnimationPose<L>();
        }
        if(!bakedPose.hasPose){
            bakedPose.setPose(AnimationPose.of(this.jointSkeleton));
            bakedPose.hasPose = true;
        }
        bakedPose.pushToOld();

        //this.locatorRig.resetRig();
        AnimationPose<L> animationPose = this.calculatePose();
        if (animationPose == null){
            animationPose = AnimationPose.of(this.jointSkeleton);
        }
        animationPose.applyDefaultPoseOffset();

        bakedPose.setPose(animationPose.getCopy());
        EntityJointAnimatorDispatcher.INSTANCE.saveBakedPose(livingEntity.getUUID(), bakedPose);
    }

     */



    public void finalizeModelParts(M entityModel, ModelPart rootModelPart){
    }

    public ModelPart getRoot(M entityModel){
        return ((ModelAccess)entityModel).getRootModelPart();
    }
}
