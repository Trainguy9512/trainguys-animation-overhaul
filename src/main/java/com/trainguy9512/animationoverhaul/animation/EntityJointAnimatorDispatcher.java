package com.trainguy9512.animationoverhaul.animation;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.UUID;

public class EntityJointAnimatorDispatcher {
    public static final EntityJointAnimatorDispatcher INSTANCE = new EntityJointAnimatorDispatcher();

    private final HashMap<UUID, AnimationDataContainer> entityAnimationData;
    private final HashMap<UUID, BakedAnimationPose<?>> bakedPoseMap;
    private final HashMap<UUID, PoseSamplerStateContainer> poseSamplerStates;

    public EntityJointAnimatorDispatcher(){
        this.entityAnimationData = Maps.newHashMap();
        this.bakedPoseMap = Maps.newHashMap();
        this.poseSamplerStates = Maps.newHashMap();
    }

    public <T extends Entity, S extends EntityRenderState, L extends Enum<L>> void tick(T entity){
        UUID entityUUID = entity.getUUID();

        EntityJointAnimator<T, S, ?, L> entityJointAnimator = (EntityJointAnimator<T, S, ?, L>) AnimationOverhaulMain.ENTITY_ANIMATORS.get(entity.getType());

        BakedAnimationPose<L> bakedPose = (BakedAnimationPose<L>) this.getBakedPose(entityUUID);
        AnimationDataContainer animationDataContainer = this.getEntityAnimationDataContainer(entityUUID);


        JointSkeleton<L> jointSkeleton = entityJointAnimator.getJointSkeleton();

        // First tick the entity part animator
        entityJointAnimator.extractAnimationData(entity, animationDataContainer);

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


        AnimationPose<L> calculatedAnimationPose = entityJointAnimator.calculatePose(animationDataContainer);
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

    public <L extends Enum<L>> void saveBakedPose(UUID uuid, BakedAnimationPose<L> bakedPose){
        this.bakedPoseMap.put(uuid, bakedPose);
    }

    public BakedAnimationPose<?> getBakedPose(UUID uuid){
        return this.bakedPoseMap.getOrDefault(uuid, new BakedAnimationPose<>());
        /*
        if(this.bakedPoseMap.containsKey(uuid)){
            return this.bakedPoseMap.get(uuid);
        }
        return null;
         */
    }

    /**
     * Returns an animation data container for the specified entity's UUID. If one does not exist for the entity, a new one is created and returned
     * @param uuid Universal Unique Identifier for entity
     * @return Animation data container
     */
    private AnimationDataContainer getEntityAnimationDataContainer(UUID uuid){
        if(entityAnimationData.containsKey(uuid)){
            return entityAnimationData.get(uuid);
        }
        return new AnimationDataContainer();
    }

    public boolean entityHasAnimationData(UUID uuid){
        return this.entityAnimationData.containsKey(uuid);
    }
}
