package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimatorRegistry;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class EntityJointAnimatorDispatcher {
    public static final EntityJointAnimatorDispatcher INSTANCE = new EntityJointAnimatorDispatcher();

    private final HashMap<UUID, AnimationDriverContainer> entityAnimationDataContainerStorage;
    private final HashMap<UUID, PoseSamplerStateContainer> entityPoseSamplerStateContainerStorage;
    private final HashMap<UUID, BakedAnimationPose> entityBakedAnimationPoseStorage;

    private final AnimationDriverContainer firstPersonPlayerAnimationDriverContainer = new AnimationDriverContainer();
    private PoseSamplerStateContainer firstPersonPoseSamplerStateContainer;
    private BakedAnimationPose firstPersonPlayerBakedAnimationPose;

    public EntityJointAnimatorDispatcher(){
        this.entityAnimationDataContainerStorage = Maps.newHashMap();
        this.entityBakedAnimationPoseStorage = Maps.newHashMap();
        this.entityPoseSamplerStateContainerStorage = Maps.newHashMap();
    }

    public <T extends Entity> void tickThirdPersonJointAnimators(T entity){

        @SuppressWarnings("unchecked")
        EntityType<T> entityType = (EntityType<T>) entity.getType();
        UUID entityUUID = entity.getUUID();

        JointAnimatorRegistry.getThirdPersonJointAnimator(entityType).ifPresent(jointAnimator ->
                JointAnimatorRegistry.getThirdPersonJointSkeleton(entityType).ifPresent(jointSkeleton -> {

                    BakedAnimationPose bakedPose = this.getEntityBakedAnimationPose(entityUUID, jointSkeleton);
                    AnimationDriverContainer animationDriverContainer = this.getEntityAnimationDataContainer(entityUUID);
                    PoseSamplerStateContainer poseSamplerStateContainer = this.getEntityPoseSamplerStateContainer(entityUUID, jointSkeleton);

                    // Step 1: Extract animation driver data
                    jointAnimator.extractAnimationData(entity, animationDriverContainer);

                    // Step 2: Update pose samplers using animation driver data
                    poseSamplerStateContainer.tick(animationDriverContainer);

                    // Step 3: Calculate pose
                    AnimationPose calculatedAnimationPose = jointAnimator.calculatePose(animationDriverContainer, poseSamplerStateContainer, jointSkeleton);
                    if (calculatedAnimationPose == null){
                        calculatedAnimationPose = AnimationPose.of(jointSkeleton);
                    }

                    // Step 4: Push the local space pose to the baked pose, and save the baked pose.
                    bakedPose.pushPose(calculatedAnimationPose.convertedToLocalSpace());
                    this.saveBakedPose(entityUUID, bakedPose);
                })
        );
    }

    public void tickFirstPersonJointAnimator(){
        JointAnimatorRegistry.getFirstPersonPlayerJointAnimator().ifPresent(jointAnimator ->
                JointAnimatorRegistry.getFirstPersonPlayerJointSkeleton().ifPresent(jointSkeleton -> {
                    // Initialize the pose sampler state container
                    if(this.firstPersonPoseSamplerStateContainer == null){
                        this.firstPersonPoseSamplerStateContainer = new PoseSamplerStateContainer(jointSkeleton);
                    }

                    // Initialize the baked animation pose
                    if(this.firstPersonPlayerBakedAnimationPose == null){
                        this.firstPersonPlayerBakedAnimationPose = new BakedAnimationPose(jointSkeleton);
                    }

                    LocalPlayer player = Minecraft.getInstance().player;

                    // Step 1: Extract animation driver data
                    jointAnimator.extractAnimationData(player, this.firstPersonPlayerAnimationDriverContainer);

                    // Step 2: Update pose samplers using animation driver data
                    this.firstPersonPoseSamplerStateContainer.tick(this.firstPersonPlayerAnimationDriverContainer);

                    // Step 3: Calculate pose
                    AnimationPose calculatedAnimationPose = jointAnimator.calculatePose(this.firstPersonPlayerAnimationDriverContainer, this.firstPersonPoseSamplerStateContainer, jointSkeleton);
                    if (calculatedAnimationPose == null){
                        calculatedAnimationPose = AnimationPose.of(jointSkeleton);
                    }

                    // Step 4: Push the local space pose to the baked pose, and save the baked pose.
                    this.firstPersonPlayerBakedAnimationPose.pushPose(calculatedAnimationPose.convertedToLocalSpace());
                })
        );
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
        return this.entityAnimationDataContainerStorage.computeIfAbsent(uuid, uuid1 -> new AnimationDriverContainer());
    }

    public BakedAnimationPose getFirstPersonPlayerBakedAnimationPose(){
        return this.firstPersonPlayerBakedAnimationPose;
    }

    public AnimationDriverContainer getFirstPersonPlayerAnimationDriverContainer(){
        return this.firstPersonPlayerAnimationDriverContainer;
    }

    public static <S extends EntityRenderState> void setupAnimWithAnimationPose(EntityModel<S> entityModel, S livingEntityRenderState, AnimationPose animationPose, EntityJointAnimator<?, S> entityJointAnimator){
        JointSkeleton jointSkeleton = animationPose.getJointSkeleton();
        jointSkeleton.getJoints().stream()
                .filter(joint -> jointSkeleton.getJointConfiguration(joint).usesModelPart())
                .forEach(joint -> entityModel.getAnyDescendantWithName(jointSkeleton.getJointConfiguration(joint).modelPartIdentifier())
                        .ifPresent(modelPart -> modelPart.loadPose(animationPose.getJointTransform(joint).asPartPose())
                ));
        entityJointAnimator.postProcessModelParts(entityModel, livingEntityRenderState);

    }
}
