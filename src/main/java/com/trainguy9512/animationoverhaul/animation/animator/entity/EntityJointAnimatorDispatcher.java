package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimatorRegistry;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.BakedAnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

public class EntityJointAnimatorDispatcher {
    public static final EntityJointAnimatorDispatcher INSTANCE = new EntityJointAnimatorDispatcher();

    private final WeakHashMap<UUID, AnimationDataContainer> entityAnimationDataContainerStorage;
    private AnimationDataContainer firstPersonPlayerDataContainer;

    public EntityJointAnimatorDispatcher(){
        this.entityAnimationDataContainerStorage = new WeakHashMap<>();
    }

    public static EntityJointAnimatorDispatcher getInstance(){
        return INSTANCE;
    }

    public <T extends Entity> void tickThirdPersonEntityAnimation(T entity){

        @SuppressWarnings("unchecked")
        EntityType<T> type = (EntityType<T>) entity.getType();
        UUID entityUUID = entity.getUUID();

        JointAnimatorRegistry.getThirdPersonJointAnimator(type).ifPresent(jointAnimator -> {

        });

    }

    public <T extends Entity> void tickThirdPersonJointAnimators(T entity){

        @SuppressWarnings("unchecked")
        EntityType<T> entityType = (EntityType<T>) entity.getType();
        UUID entityUUID = entity.getUUID();

        JointAnimatorRegistry.getThirdPersonJointAnimator(entityType).ifPresent(jointAnimator ->
                JointAnimatorRegistry.getThirdPersonJointSkeleton(entityType).ifPresent(jointSkeleton -> {

                    BakedAnimationPose bakedPose = this.getEntityBakedAnimationPose(entityUUID, jointSkeleton);
                    DriverAnimationContainer driverContainer = this.getEntityAnimationDataContainer(entityUUID);
                    PoseSamplerStateContainer poseSamplerStateContainer = this.getEntityPoseSamplerStateContainer(entityUUID, jointSkeleton);

                    // Step 1: Extract animation driver data
                    jointAnimator.extractAnimationData(entity, driverContainer);

                    // Step 2: Update pose samplers using animation driver data
                    poseSamplerStateContainer.tick(driverContainer);

                    // Step 3: Calculate pose
                    AnimationPose calculatedAnimationPose = jointAnimator.calculatePose(driverContainer, poseSamplerStateContainer, jointSkeleton);
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
                    jointAnimator.extractAnimationData(player, this.firstPersonPlayerDriverContainer);

                    // Step 2: Update pose samplers using animation driver data
                    this.firstPersonPoseSamplerStateContainer.tick(this.firstPersonPlayerDriverContainer);

                    // Step 3: Calculate pose
                    AnimationPose calculatedAnimationPose = jointAnimator.calculatePose(this.firstPersonPlayerDriverContainer, this.firstPersonPoseSamplerStateContainer, jointSkeleton);
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
    private DriverAnimationContainer getEntityAnimationDataContainer(UUID uuid){
        return this.entityAnimationDataContainerStorage.computeIfAbsent(uuid, uuid1 -> new DriverAnimationContainer());
    }

    public BakedAnimationPose getFirstPersonPlayerBakedAnimationPose(){
        return this.firstPersonPlayerBakedAnimationPose;
    }

    public Optional<AnimationDataContainer> getFirstPersonPlayerDataContainer(){
        JointAnimatorRegistry.getFirstPersonPlayerJointAnimator().ifPresent(jointAnimator -> {
            return Optional.of(createDataContainer(jointAnimator));
        });
        return Optional.empty();
        return this.firstPersonPlayerDataContainer;
    }

    private AnimationDataContainer createDataContainer(JointAnimator<?> jointAnimator){
        return AnimationDataContainer.of(jointAnimator.buildSkeleton());
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
