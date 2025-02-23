package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
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

public class JointAnimatorDispatcher {
    private static final JointAnimatorDispatcher INSTANCE = new JointAnimatorDispatcher();

    private final WeakHashMap<UUID, AnimationDataContainer> entityAnimationDataContainerStorage;
    private AnimationDataContainer firstPersonPlayerDataContainer;

    public JointAnimatorDispatcher(){
        this.entityAnimationDataContainerStorage = new WeakHashMap<>();
    }

    public static JointAnimatorDispatcher getInstance(){
        return INSTANCE;
    }

    public <T extends Entity> void tickEntityJointAnimators(Iterable<T> entitiesForRendering){
        entitiesForRendering.forEach(entity ->
                JointAnimatorRegistry.getThirdPersonJointAnimator(entity).ifPresent(jointAnimator ->
                        this.getEntityAnimationDataContainer(entity).ifPresent(dataContainer ->
                                this.tickJointAnimator(jointAnimator, entity, dataContainer)
                        )
                )
        );
    }

    public void tickFirstPersonPlayerJointAnimator(){
        JointAnimatorRegistry.getFirstPersonPlayerJointAnimator().ifPresent(jointAnimator ->
                this.getFirstPersonPlayerDataContainer().ifPresent(dataContainer ->
                        this.tickJointAnimator(jointAnimator, Minecraft.getInstance().player, dataContainer)
                )
        );
    }

    /**
     * Updates the data container every tick and if the joint animator is set to calculate once per tick, samples the animation pose and loads it into the data container.
     * @param jointAnimator         Joint animator
     * @param dataReference         Animation data reference
     * @param dataContainer         Animation data container
     */
    private <T> void tickJointAnimator(JointAnimator<T> jointAnimator, T dataReference, AnimationDataContainer dataContainer){
        dataContainer.pushDriverValuesToPrevious();
        jointAnimator.extractAnimationData(dataReference, dataContainer);
        dataContainer.tickPoseSamplers();
        if(jointAnimator.getPoseCalulationFrequency() == JointAnimator.PoseCalculationFrequency.CALCULATE_ONCE_PER_TICK){
            dataContainer.loadValueIntoDriver(dataContainer.getPerTickCalculatedPoseDriverKey(), jointAnimator.calculatePose(dataContainer, dataContainer.getJointSkeleton(), 1));
        }
    }

    private <T extends Entity> Optional<AnimationDataContainer> getEntityAnimationDataContainer(T entity){
        UUID uuid = entity.getUUID();
        if(!this.entityAnimationDataContainerStorage.containsKey(uuid)){
            JointAnimatorRegistry.getThirdPersonJointAnimator(entity).ifPresent(jointAnimator ->
                    this.entityAnimationDataContainerStorage.put(uuid, this.createDataContainer(jointAnimator))
            );
        }
        return Optional.ofNullable(this.entityAnimationDataContainerStorage.get(uuid));
    }

    public Optional<AnimationDataContainer> getFirstPersonPlayerDataContainer(){
        if(this.firstPersonPlayerDataContainer == null){
            JointAnimatorRegistry.getFirstPersonPlayerJointAnimator().ifPresent(jointAnimator ->
                this.firstPersonPlayerDataContainer = this.createDataContainer(jointAnimator)
            );
        }
        return Optional.ofNullable(this.firstPersonPlayerDataContainer);
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
