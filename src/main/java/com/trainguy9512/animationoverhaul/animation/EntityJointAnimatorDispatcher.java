package com.trainguy9512.animationoverhaul.animation;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.UUID;

public class EntityJointAnimatorDispatcher {
    public static final EntityJointAnimatorDispatcher INSTANCE = new EntityJointAnimatorDispatcher();

    private final HashMap<UUID, AnimationDataContainer> entityAnimationDataMap = Maps.newHashMap();
    private final HashMap<UUID, BakedAnimationPose<?>> bakedPoseMap = Maps.newHashMap();

    public EntityJointAnimatorDispatcher(){
    }

    public <T extends Entity, L extends Enum<L>> void tickEntity(T entity, EntityJointAnimator<T, ?, L> entityJointAnimator){

        UUID entityUUID = entity.getUUID();
        if(!entityAnimationDataMap.containsKey(entityUUID)){
            entityAnimationDataMap.put(entityUUID, new AnimationDataContainer());
        }

        BakedAnimationPose<L> bakedPose = (BakedAnimationPose<L>) INSTANCE.getBakedPose(entityUUID);
        AnimationDataContainer animationDataContainer = EntityJointAnimatorDispatcher.INSTANCE.getEntityAnimationDataReference(entityUUID);
        JointSkeleton<L> jointSkeleton = (JointSkeleton<L>) entityJointAnimator.getJointSkeleton();

        // First tick the entity part animator
        entityJointAnimator.tick(entity, animationDataContainer);

        animationDataContainer.tickAllPoseSamplers();


        /*
        if(bakedPose == null){
            bakedPose = new BakedAnimationPose<>();
        }
         */
        if(!bakedPose.hasPose){
            bakedPose.setPose(AnimationPose.of(jointSkeleton));
            bakedPose.hasPose = true;
        }
        bakedPose.pushToOld();


        AnimationPose<L> calculatedAnimationPose = entityJointAnimator.calculatePose(entity, animationDataContainer);
        if (calculatedAnimationPose == null){
            calculatedAnimationPose = AnimationPose.of(jointSkeleton);
        }
        calculatedAnimationPose.convertSpaceEntityToLocal();

        //TODO: Rewrite how pose offsets are done
        calculatedAnimationPose.applyDefaultPoseOffset();

        bakedPose.setPose(new AnimationPose<L>(calculatedAnimationPose));

        //TODO: Should this be a reference to the static instance and not itself?
        EntityJointAnimatorDispatcher.INSTANCE.saveBakedPose(entityUUID, bakedPose);


        //livingEntityPartAnimator.overallTick(livingEntity);
    }

    public <T extends Entity, M extends EntityModel<T>, L extends Enum<L>> boolean animateEntity(T livingEntity, M entityModel, PoseStack poseStack, float partialTicks){
        if(entityAnimationDataMap.containsKey(livingEntity.getUUID())){
            if(AnimationOverhaulMain.ENTITY_ANIMATORS.contains(livingEntity.getType())){
                EntityJointAnimator<T, M, L> entityJointAnimator = (EntityJointAnimator<T, M, L>) AnimationOverhaulMain.ENTITY_ANIMATORS.get(livingEntity.getType());
                applyBakedPose(livingEntity, entityModel, entityJointAnimator, partialTicks);
                return true;
            }
        }
        return false;
    }

    private <T extends Entity, M extends EntityModel<T>, L extends Enum<L>> void applyBakedPose(T entity, M entityModel, EntityJointAnimator<T, M, L> entityJointAnimator, float partialTicks){
        BakedAnimationPose<?> bakedPose = EntityJointAnimatorDispatcher.INSTANCE.getBakedPose(entity.getUUID());

        if(bakedPose != null){
            ModelPart rootModelPart = entityJointAnimator.getRoot(entityModel);

            bakedPose.bakeToModelParts(rootModelPart, partialTicks);
            entityJointAnimator.finalizeModelParts(entityModel, rootModelPart);
        }
    }

    public <L extends Enum<L>> void saveBakedPose(UUID uuid, BakedAnimationPose<L> bakedPose){
        this.bakedPoseMap.put(uuid, bakedPose);
    }

    public  BakedAnimationPose<?> getBakedPose(UUID uuid){
        return this.bakedPoseMap.getOrDefault(uuid, new BakedAnimationPose<>());
        /*
        if(this.bakedPoseMap.containsKey(uuid)){
            return this.bakedPoseMap.get(uuid);
        }
        return null;
         */
    }

    public boolean hasAnimationData(UUID uuid){
        return this.entityAnimationDataMap.containsKey(uuid);
    }

    public AnimationDataContainer getEntityAnimationDataReference(UUID uuid){
        if(entityAnimationDataMap.containsKey(uuid)){
            return entityAnimationDataMap.get(uuid);
        }
        return new AnimationDataContainer();
    }

    public <T extends Entity> AnimationDataContainer getEntityAnimationData(T entity){
        return getEntityAnimationDataReference(entity.getUUID());
    }
}
