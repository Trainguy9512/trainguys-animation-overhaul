package com.trainguy9512.animationoverhaul.animation;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.UUID;

public class EntityJointAnimatorDispatcher {
    public static final EntityJointAnimatorDispatcher INSTANCE = new EntityJointAnimatorDispatcher();

    private final HashMap<UUID, AnimationDriverContainer> entityAnimationDataContainerStorage;
    private final HashMap<UUID, BakedAnimationPose> entityBakedAnimationPoseStorage;
    private final HashMap<UUID, PoseSamplerStateContainer> entityPoseSamplerStateContainerStorage;

    public EntityJointAnimatorDispatcher(){
        this.entityAnimationDataContainerStorage = Maps.newHashMap();
        this.entityBakedAnimationPoseStorage = Maps.newHashMap();
        this.entityPoseSamplerStateContainerStorage = Maps.newHashMap();
    }

    public <T extends Entity> void tick(T entity){
        UUID entityUUID = entity.getUUID();

        @SuppressWarnings("unchecked")
        EntityJointAnimator<T, ?> entityJointAnimator = (EntityJointAnimator<T, ?>) AnimationOverhaulMain.ENTITY_ANIMATORS.get(entity.getType());
        JointSkeleton jointSkeleton = entityJointAnimator.getJointSkeleton();

        BakedAnimationPose bakedPose = this.getEntityBakedAnimationPose(entityUUID, jointSkeleton);
        AnimationDriverContainer animationDriverContainer = this.getEntityAnimationDataContainer(entityUUID);
        PoseSamplerStateContainer poseSamplerStateContainer = this.getEntityPoseSamplerStateContainer(entityUUID, jointSkeleton);

        // Step 1: Extract animation driver data
        entityJointAnimator.extractAnimationData(entity, animationDriverContainer);

        // Step 2: Update pose samplers using animation driver data
        poseSamplerStateContainer.tick(animationDriverContainer);

        // Step 3: Calculate pose
        AnimationPose calculatedAnimationPose = entityJointAnimator.calculatePose(animationDriverContainer, poseSamplerStateContainer);
        if (calculatedAnimationPose == null){
            calculatedAnimationPose = AnimationPose.of(jointSkeleton);
        }

        // Step 4: Push the local space pose to the baked pose, and save the baked pose.
        bakedPose.pushPose(calculatedAnimationPose.getConvertedToLocalSpace());
        this.saveBakedPose(entityUUID, bakedPose);


    }

    public <L extends Enum<L>> void saveBakedPose(UUID uuid, BakedAnimationPose bakedPose){
        this.entityBakedAnimationPoseStorage.put(uuid, bakedPose);
    }

    /**
     * Returns a baked animation pose for the specified entity's UUID. If one does not exist for the entity, a new one is created and returned.
     * @param uuid Entity UUID
     * @return Animation data container
     */
    public PoseSamplerStateContainer getEntityPoseSamplerStateContainer(UUID uuid, JointSkeleton jointSkeleton){
        return this.entityPoseSamplerStateContainerStorage.computeIfAbsent(uuid, (uuid1 -> new PoseSamplerStateContainer(jointSkeleton)));
    }

    /**
     * Returns a baked animation pose for the specified entity's UUID. If one does not exist for the entity, a new one is created and returned.
     * @param uuid Entity UUID
     * @return Animation data container
     */
    public <L extends Enum<L>> BakedAnimationPose getEntityBakedAnimationPose(UUID uuid, JointSkeleton jointSkeleton){
        return this.entityBakedAnimationPoseStorage.getOrDefault(uuid, new BakedAnimationPose(jointSkeleton));
    }

    /**
     * Returns an animation data container for the specified entity's UUID. If one does not exist for the entity, a new one is created and returned.
     * @param uuid Entity UUID
     * @return Animation data container
     */
    private AnimationDriverContainer getEntityAnimationDataContainer(UUID uuid){
        return this.entityAnimationDataContainerStorage.getOrDefault(uuid, new AnimationDriverContainer());
    }

    /**
     * Returns whether the entity has a baked pose or not.
     * @param uuid Entity UUID
     */
    public boolean entityHasBakedAnimationPose(UUID uuid){
        return this.entityAnimationDataContainerStorage.containsKey(uuid);
    }
}
