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

    public <T extends Entity> void tickThirdPersonEntityAnimation(T entity){

        @SuppressWarnings("unchecked")
        EntityType<T> type = (EntityType<T>) entity.getType();
        UUID entityUUID = entity.getUUID();

        JointAnimatorRegistry.getThirdPersonJointAnimator(type).ifPresent(jointAnimator -> {

        });

    }

    public <T extends Entity> void tick()

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

    private <T extends Entity> Optional<AnimationDataContainer> getEntityAnimationDataContainer(T entity){
        UUID uuid = entity.getUUID();
        if(!this.entityAnimationDataContainerStorage.containsKey(uuid)){
            JointAnimatorRegistry.getThirdPersonJointAnimator(entity.getType()).ifPresent(jointAnimator ->
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
