package com.trainguy9512.animationoverhaul.animation.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.access.ModelAccess;
import com.trainguy9512.animationoverhaul.animation.AnimatorDispatcher;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public abstract class LivingEntityPartAnimator<T extends LivingEntity, M extends EntityModel<T>> {

    protected T livingEntity;
    protected M entityModel;
    protected final LocatorSkeleton locatorSkeleton;

    protected AnimationDataContainer entityAnimationData;
    protected final Random random = new Random();

    public LivingEntityPartAnimator(){
        this.locatorSkeleton = new LocatorSkeleton();
        buildRig(this.locatorSkeleton);
    }

    public void setEntity(T livingEntity){
        this.livingEntity = livingEntity;
    }

    public void setEntityModel(M entityModel){
        this.entityModel = entityModel;
    }

    protected void buildRig(LocatorSkeleton locatorRig){

    }

    public void tick(LivingEntity livingEntity, AnimationDataContainer entityAnimationData){

    }

    protected AnimationPose calculatePose(){
        return null;
    }

    protected void finalizeModelParts(ModelPart rootModelPart){
    }

    public void tick(LivingEntity livingEntity){
        BakedAnimationPose bakedPose = AnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());
        AnimationDataContainer entityAnimationData = AnimatorDispatcher.INSTANCE.getEntityAnimationData(livingEntity.getUUID());
        this.entityAnimationData = entityAnimationData;
        this.livingEntity = (T)livingEntity;

        this.tick(livingEntity, entityAnimationData);
        this.entityAnimationData.tickAnimationStates();

        if(!bakedPose.hasPose){
            bakedPose.setPose(new AnimationPose(this.locatorSkeleton));
            bakedPose.hasPose = true;
        }
        bakedPose.pushToOld();

        //this.locatorRig.resetRig();
        AnimationPose animationPose = this.calculatePose();
        if (animationPose == null){
            animationPose = new AnimationPose(this.locatorSkeleton);
        }
        animationPose.applyDefaultPoseOffset();

        bakedPose.setPose(animationPose.getCopy());
        AnimatorDispatcher.INSTANCE.saveBakedPose(livingEntity.getUUID(), bakedPose);
    }

    public void applyBakedPose(T livingEntity, M entityModel, PoseStack poseStack, AnimationDataContainer entityAnimationData, float partialTicks){
        setEntity(livingEntity);
        setEntityModel(entityModel);

        BakedAnimationPose bakedPose = AnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());

        ModelPart rootModelPart = getRoot(entityModel);
        bakedPose.bakeToModelParts(rootModelPart, partialTicks);
        finalizeModelParts(rootModelPart);
    }

    protected ModelPart getRoot(M entityModel){
        return ((ModelAccess)entityModel).getRootModelPart();
    }
}
