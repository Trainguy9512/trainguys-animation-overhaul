package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import com.trainguy9512.animationoverhaul.animation.animator.entity.FirstPersonPlayerJointAnimator;
import com.trainguy9512.animationoverhaul.animation.data.driver.Driver;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDataKey;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDriverKey;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.function.PoseFunction;
import com.trainguy9512.animationoverhaul.animation.pose.function.cache.SavedCachedPoseContainer;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.PoseSampler;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.Sampleable;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.SampleableFromInput;
import com.trainguy9512.animationoverhaul.util.Interpolator;

import java.util.HashMap;
import java.util.Map;

public class AnimationDataContainer implements PoseCalculationDataContainer, OnTickDataContainer {

    private final Map<AnimationDataKey<? extends Driver<?>>, Driver<?>> drivers;
    private final SavedCachedPoseContainer savedCachedPoseContainer;
    private final PoseFunction<LocalSpacePose> poseFunction;
    private long currentTick;

    private final JointSkeleton jointSkeleton;
    private final AnimationDriverKey<LocalSpacePose> perTickCalculatedPoseDriverKey;

    private AnimationDataContainer(JointAnimator<?> jointAnimator){
        this.drivers = Maps.newHashMap();
        this.savedCachedPoseContainer = SavedCachedPoseContainer.of();
        this.poseFunction = jointAnimator.constructPoseFunction(savedCachedPoseContainer).wrapUnique();
        this.currentTick = 0;

        this.jointSkeleton = jointAnimator.buildSkeleton();
        this.perTickCalculatedPoseDriverKey = AnimationDriverKey.driverKeyOf("per_tick_calculated_pose", () -> Driver.ofInterpolatable(() -> LocalSpacePose.of(jointSkeleton), Interpolator.LOCAL_SPACE_POSE));
    }

    public static AnimationDataContainer of(JointAnimator<?> jointAnimator){
        return new AnimationDataContainer(jointAnimator);
    }

    @Override
    public JointSkeleton getJointSkeleton(){
        return this.jointSkeleton;
    }

    public AnimationDriverKey<LocalSpacePose> getPerTickCalculatedPoseDriverKey(){
        return this.perTickCalculatedPoseDriverKey;
    }

    public void tick(){
        this.poseFunction.tick(PoseFunction.FunctionEvaluationState.of(this, false, this.currentTick));
        this.currentTick++;
    }

    public LocalSpacePose computePose(float partialTicks){
        this.savedCachedPoseContainer.clearCaches();
        return this.poseFunction.compute(PoseFunction.FunctionInterpolationContext.of(this, partialTicks));
    }

    @SuppressWarnings("unchecked")
    private <D> Driver<D> getOrCreateDriver(AnimationDriverKey<D> driverKey){
        return (Driver<D>) this.drivers.computeIfAbsent(driverKey, AnimationDataKey::createInstance);
    }

    @Override
    public <D> D getDriverValue(AnimationDriverKey<D> driverKey) {
        return this.getOrCreateDriver(driverKey).getValueCurrent();
    }

    @Override
    public <D> D getPreviousDriverValue(AnimationDriverKey<D> driverKey) {
        return (D) this.getOrCreateDriver(driverKey).getValuePrevious();
    }

    @Override
    public <D> void loadValueIntoDriver(AnimationDriverKey<D> driverKey, D newValue) {
        this.getOrCreateDriver(driverKey).loadValue(newValue);
    }

    @Override
    public <D> void resetDriverValue(AnimationDriverKey<D> driverKey) {
        this.getOrCreateDriver(driverKey).resetValue();
    }

    @Override
    public <D> D getDriverValueInterpolated(AnimationDriverKey<D> driverKey, float partialTicks) {
        return this.getOrCreateDriver(driverKey).getValueInterpolated(partialTicks);
    }

    public void pushDriverValuesToPrevious(){
        this.drivers.values().forEach(Driver::pushValueToPrevious);
    }
}
