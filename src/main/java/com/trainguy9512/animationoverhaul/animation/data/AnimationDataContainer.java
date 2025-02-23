package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.data.driver.Driver;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDataKey;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDriverKey;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.PoseSampler;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.Sampleable;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.SampleableFromInput;
import com.trainguy9512.animationoverhaul.util.Interpolator;

import java.util.HashMap;

public class AnimationDataContainer implements PoseCalculationDataContainer, OnTickDataContainer {

    private final HashMap<AnimationDataKey<? extends Driver<?>>, Driver<?>> drivers;
    private final HashMap<AnimationDataKey<? extends PoseSampler>, PoseSampler> poseSamplers;

    private final JointSkeleton jointSkeleton;
    private final AnimationDriverKey<AnimationPose> perTickCalculatedPoseDriverKey;

    private AnimationDataContainer(JointSkeleton jointSkeleton){
        this.drivers = Maps.newHashMap();
        this.poseSamplers = Maps.newHashMap();
        this.jointSkeleton = jointSkeleton;
        this.perTickCalculatedPoseDriverKey = AnimationDriverKey.driverKeyOf("per_tick_calculated_pose", () -> Driver.ofInterpolatable(() -> AnimationPose.of(jointSkeleton), Interpolator.ANIMATION_POSE));
    }

    public static AnimationDataContainer of(JointSkeleton jointSkeleton){
        return new AnimationDataContainer(jointSkeleton);
    }

    public JointSkeleton getJointSkeleton(){
        return this.jointSkeleton;
    }

    public AnimationDriverKey<AnimationPose> getPerTickCalculatedPoseDriverKey(){
        return this.perTickCalculatedPoseDriverKey;
    }

    @SuppressWarnings("unchecked")
    private <D> Driver<D> getOrCreateDriver(AnimationDataKey<Driver<D>> driverKey){
        return (Driver<D>) this.drivers.computeIfAbsent(driverKey, (AnimationDataKey::createInstance));
    }

    @SuppressWarnings("unchecked")
    private <P extends PoseSampler> P getOrCreatePoseSampler(AnimationDataKey<P> poseSamplerKey){
        return (P) this.poseSamplers.computeIfAbsent(poseSamplerKey, (AnimationDataKey::createInstance));
    }

    @Override
    public <D> D getDriverValue(AnimationDataKey<Driver<D>> driverKey) {
        return this.getOrCreateDriver(driverKey).getValueCurrent();
    }

    @Override
    public <D> D getPreviousDriverValue(AnimationDataKey<Driver<D>> driverKey) {
        return (D) this.getOrCreateDriver(driverKey).getValuePrevious();
    }

    @Override
    public <D> void loadValueIntoDriver(AnimationDataKey<Driver<D>> driverKey, D newValue) {
        this.getOrCreateDriver(driverKey).loadValue(newValue);

        this.resetDriverValue(driverKey);
    }

    @Override
    public <D> void resetDriverValue(AnimationDataKey<Driver<D>> driverKey) {
        this.getOrCreateDriver(driverKey).resetValue();
    }

    @Override
    public <D> D getDriverValueInterpolated(AnimationDataKey<Driver<D>> driverKey, float partialTicks) {
        return this.getOrCreateDriver(driverKey).getValueInterpolated(partialTicks);
    }

    @Override
    public <P extends PoseSampler> P getPoseSampler(AnimationDataKey<P> poseSamplerKey){
        return this.getOrCreatePoseSampler(poseSamplerKey);
    }

    @Override
    public <P extends PoseSampler & Sampleable> AnimationPose sample(AnimationDataKey<P> poseSamplerKey, float partialTicks) {
        return this.getOrCreatePoseSampler(poseSamplerKey).sample(this, jointSkeleton, partialTicks);
    }

    @Override
    public <P extends PoseSampler & SampleableFromInput> AnimationPose sample(AnimationDataKey<P> poseSamplerKey, AnimationPose animationPose, float partialTicks) {
        return this.getOrCreatePoseSampler(poseSamplerKey).sample(this, jointSkeleton, animationPose, partialTicks);
    }

    public void pushDriverValuesToPrevious(){
        this.drivers.values().forEach(Driver::pushValueToPrevious);
    }

    public void tickPoseSamplers(){
        this.tickUpdateOrderGroup(PoseSampler.UpdateCategory.STATE_MACHINES);
        this.tickUpdateOrderGroup(PoseSampler.UpdateCategory.OTHER);
    }

    private void tickUpdateOrderGroup(PoseSampler.UpdateCategory updateCategory){
        this.poseSamplers.values()
                .stream()
                .sorted()
                .forEach(poseSampler -> {
                    if(poseSampler.getUpdateCategory() == updateCategory){
                        poseSampler.tick(this);
                    }
                });
    }
}
