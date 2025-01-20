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

    private final HashMap<UUID, AnimationDataContainer> entityAnimationDataContainerStorage;
    private final HashMap<UUID, BakedAnimationPose<?>> entityBakedAnimationPoseStorage;
    private final HashMap<UUID, PoseSamplerStateContainer> entityPoseSamplerStateContainerStorage;

    public EntityJointAnimatorDispatcher(){
        this.entityAnimationDataContainerStorage = Maps.newHashMap();
        this.entityBakedAnimationPoseStorage = Maps.newHashMap();
        this.entityPoseSamplerStateContainerStorage = Maps.newHashMap();
    }

    public <T extends Entity, S extends EntityRenderState, L extends Enum<L>> void tick(T entity){
        UUID entityUUID = entity.getUUID();

        EntityJointAnimator<T, S, ?, L> entityJointAnimator = (EntityJointAnimator<T, S, ?, L>) AnimationOverhaulMain.ENTITY_ANIMATORS.get(entity.getType());
        JointSkeleton<L> jointSkeleton = entityJointAnimator.getJointSkeleton();

        BakedAnimationPose<L> bakedPose = (BakedAnimationPose<L>) this.getEntityBakedAnimationPose(entityUUID, jointSkeleton);
        AnimationDataContainer animationDataContainer = this.getEntityAnimationDataContainer(entityUUID);


        // First tick the entity part animator
        entityJointAnimator.extractAnimationData(entity, animationDataContainer);

        animationDataContainer.tickAllPoseSamplers();


        /*
        if(bakedPose == null){
            bakedPose = new BakedAnimationPose<>();
        }
         */


        AnimationPose<L> calculatedAnimationPose = entityJointAnimator.calculatePose(animationDataContainer);
        if (calculatedAnimationPose == null){
            calculatedAnimationPose = AnimationPose.of(jointSkeleton);
        }
        calculatedAnimationPose.convertSpaceEntityToLocal();

        //TODO: Rewrite how pose offsets are done
        calculatedAnimationPose.applyDefaultPoseOffset();

        bakedPose.pushPose(calculatedAnimationPose);

        //TODO: Should this be a reference to the static instance and not itself?
        EntityJointAnimatorDispatcher.INSTANCE.saveBakedPose(entityUUID, bakedPose);


        //livingEntityPartAnimator.overallTick(livingEntity);
    }

    public <L extends Enum<L>> void saveBakedPose(UUID uuid, BakedAnimationPose<L> bakedPose){
        this.entityBakedAnimationPoseStorage.put(uuid, bakedPose);
    }

    /**
     * Returns a baked animation pose for the specified entity's UUID. If one does not exist for the entity, a new one is created and returned.
     * @param uuid Entity UUID
     * @return Animation data container
     */
    public PoseSamplerStateContainer getEntityPoseSamplerStateContainer(UUID uuid){
        return this.entityPoseSamplerStateContainerStorage.getOrDefault(uuid, new PoseSamplerStateContainer());
    }

    /**
     * Returns a baked animation pose for the specified entity's UUID. If one does not exist for the entity, a new one is created and returned.
     * @param uuid Entity UUID
     * @return Animation data container
     */
    public <L extends Enum<L>> BakedAnimationPose<?> getEntityBakedAnimationPose(UUID uuid, JointSkeleton<L> jointSkeleton){
        return this.entityBakedAnimationPoseStorage.getOrDefault(uuid, new BakedAnimationPose<>(jointSkeleton));
    }

    /**
     * Returns an animation data container for the specified entity's UUID. If one does not exist for the entity, a new one is created and returned.
     * @param uuid Entity UUID
     * @return Animation data container
     */
    private AnimationDataContainer getEntityAnimationDataContainer(UUID uuid){
        return this.entityAnimationDataContainerStorage.getOrDefault(uuid, new AnimationDataContainer());
    }

    /**
     * Returns whether the entity has a baked pose or not.
     * @param uuid Entity UUID
     */
    public boolean entityHasBakedAnimationPose(UUID uuid){
        return this.entityAnimationDataContainerStorage.containsKey(uuid);
    }
}
