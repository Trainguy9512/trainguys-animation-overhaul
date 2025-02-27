package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.animator.entity.FirstPersonPlayerJointAnimator;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

public class JointAnimatorDispatcher {
    private static final JointAnimatorDispatcher INSTANCE = new JointAnimatorDispatcher();

    private final WeakHashMap<UUID, AnimationDataContainer> entityAnimationDataContainerStorage;

    private AnimationDataContainer firstPersonPlayerDataContainer;
    private AnimationPose interpolatedFirstPersonPlayerPose;

    public JointAnimatorDispatcher(){
        this.entityAnimationDataContainerStorage = new WeakHashMap<>();
    }

    public static JointAnimatorDispatcher getInstance(){
        return INSTANCE;
    }

    public <T extends Entity> void tickEntityJointAnimators(Iterable<T> entitiesForRendering){
        entitiesForRendering.forEach(entity ->
                JointAnimatorRegistry.getThirdPersonJointAnimator(entity).ifPresent(
                        jointAnimator -> this.getEntityAnimationDataContainer(entity).ifPresent(
                                dataContainer -> this.tickJointAnimator(jointAnimator, entity, dataContainer)
                        )
                )
        );
    }

    public void tickFirstPersonPlayerJointAnimator(){
        JointAnimatorRegistry.getFirstPersonPlayerJointAnimator().ifPresent(
                jointAnimator -> this.getFirstPersonPlayerDataContainer().ifPresent(
                        dataContainer -> this.tickJointAnimator(jointAnimator, Minecraft.getInstance().player, dataContainer)
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
        dataContainer.tick();
        if(jointAnimator.getPoseCalulationFrequency() == JointAnimator.PoseCalculationFrequency.CALCULATE_ONCE_PER_TICK){
            //AnimationPose animationPose = jointAnimator.calculatePose(dataContainer, dataContainer.getJointSkeleton(), 1);
            //AnimationOverhaulMain.LOGGER.info("{}", animationPose.getJointTransform(FirstPersonPlayerJointAnimator.RIGHT_ARM_JOINT).getRotation());

            dataContainer.loadValueIntoDriver(dataContainer.getPerTickCalculatedPoseDriverKey(), dataContainer.computePose(1));
        }
    }

    public <T extends Entity> Optional<AnimationDataContainer> getEntityAnimationDataContainer(T entity){
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

    public Optional<AnimationPose> getInterpolatedFirstPersonPlayerPose(){
        return Optional.ofNullable(this.interpolatedFirstPersonPlayerPose);
    }

    public void calculateInterpolatedFirstPersonPlayerPose(JointAnimator<?> jointAnimator, AnimationDataContainer dataContainer, float partialTicks){
        this.interpolatedFirstPersonPlayerPose = this.getInterpolatedAnimationPose(jointAnimator, dataContainer, partialTicks);
    }

    private AnimationDataContainer createDataContainer(JointAnimator<?> jointAnimator){
        return AnimationDataContainer.of(jointAnimator);
    }

    public AnimationPose getInterpolatedAnimationPose(JointAnimator<?> jointAnimator, AnimationDataContainer dataContainer, float partialTicks){
        return switch (jointAnimator.getPoseCalulationFrequency()) {
            case CALCULATE_EVERY_FRAME -> dataContainer.computePose(partialTicks).convertedToComponentSpace();
            case CALCULATE_ONCE_PER_TICK -> dataContainer.getDriverValueInterpolated(dataContainer.getPerTickCalculatedPoseDriverKey(), partialTicks).convertedToComponentSpace();
        };
    }

    public <S extends EntityRenderState> void setupAnimWithAnimationPose(EntityModel<S> entityModel, S entityRenderState, AnimationPose animationPose, EntityJointAnimator<?, S> entityJointAnimator){
        JointSkeleton jointSkeleton = animationPose.getJointSkeleton();
        jointSkeleton.getJoints()
                .forEach(joint -> {
                    if(jointSkeleton.getJointConfiguration(joint).usesModelPart()){
                        entityModel.getAnyDescendantWithName(jointSkeleton.getJointConfiguration(joint).modelPartIdentifier()).ifPresent(
                                modelPart -> modelPart.loadPose(animationPose.getJointTransform(joint).asPartPose())
                        );
                    }
                });
        entityJointAnimator.postProcessModelParts(entityModel, entityRenderState);

    }
}
