package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.data.driver.Driver;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.PoseSampler;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.Sampleable;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.SampleableFromInput;
import com.trainguy9512.animationoverhaul.util.Interpolator;

import java.util.HashMap;

public class AnimationDataContainer implements PoseCalculationDataContainer {

    private final HashMap<AnimationDataKey<? extends Driver<?>>, Driver<?>> drivers;
    private final HashMap<AnimationDataKey<? extends PoseSampler>, PoseSampler> poseSamplers;

    private final JointSkeleton jointSkeleton;
    private final AnimationDataKey<Driver<AnimationPose>> perTickCalculatedPoseDriverKey;

    public AnimationDataContainer(JointSkeleton jointSkeleton){
        this.drivers = Maps.newHashMap();
        this.poseSamplers = Maps.newHashMap();
        this.jointSkeleton = jointSkeleton;
        this.perTickCalculatedPoseDriverKey = AnimationDataKey.of("per_tick_calculated_pose", () -> Driver.ofInterpolatable(() -> AnimationPose.of(jointSkeleton), Interpolator.ANIMATION_POSE));
    }

    @SuppressWarnings("unchecked")
    private <D> D getOrCreateDriver(AnimationDataKey<D> dataKey){
        return (D) this.drivers.computeIfAbsent(dataKey, (animationDataKey -> animationDataKey.createInstance()));
    }

    /**
     * Returns the value of a driver during the current tick.
     * @param driverKey         {@link AnimationDataKey<>} associated with driver.
     * @return                  Value
     */
    public <D> D getDriverValue(AnimationDataKey<Driver<D>> driverKey) {
        return (D) this.getOrCreateDriver(driverKey).getValueCurrent();
    }

    /**
     * Returns the value of a driver during the previous tick.
     * @param driverKey         {@link AnimationDataKey<>} associated with driver.
     * @return                  Value
     */
    public <D> D getPreviousDriverValue(AnimationDataKey<Driver<D>> driverKey) {
        return (D) this.getOrCreateDriver(driverKey).getValuePrevious();
    }

    /**
     * Loads a driver with a new value for the current tick.
     * @param driverKey         {@link AnimationDataKey<>} of the driver to load
     * @param newValue          Value to load.
     * @implNote                Ensure that any mutable values inputted here are copies of themselves!
     */
    public <D> void loadValueIntoDriver(AnimationDataKey<Driver<D>> driverKey, D newValue) {
        this.getOrCreateDriver(driverKey).loadValue(newValue);

        this.resetDriverValue(driverKey);
    }

    /**
     * Resets the value of a driver to the default, which is specified by the key.
     * @param driverKey         {@link AnimationDataKey<>} of the driver being reset.
     */
    public <D> void resetDriverValue(AnimationDataKey<Driver<D>> driverKey) {
        this.getOrCreateDriver(driverKey).resetValue();
    }

    /**
     * Retrieves a pose sampler from the given key.
     *
     * @param poseSamplerKey    The {@link AnimationDataKey<>} attached to the desired pose sampler.
     *
     * @return a {@link PoseSampler} object reference
     */
    public <P extends PoseSampler> P getPoseSampler(AnimationDataKey<P> poseSamplerKey){

    }

    @Override
    public <D> D getDriverValueInterpolated(AnimationDataKey<Driver<D>> driverKey, float partialTicks) {
        return this.getOrCreateDriver(driverKey).getValueInterpolated(partialTicks);
    }

    @Override
    public <P extends PoseSampler & Sampleable> AnimationPose sample(AnimationDataKey<P> poseSamplerKey) {
        return null;
    }

    @Override
    public <P extends PoseSampler & SampleableFromInput> AnimationPose sample(AnimationDataKey<P> poseSamplerKey, AnimationPose animationPose) {
        return null;
    }

    public void pushDriverValuesToPrevious(){
        this.drivers.forEach((dataKey, dataObject) -> {
            if(dataObject instanceof Driver<?>){
                ((Driver<?>) dataObject).pushValueToPrevious();
            }
        });
    }

    /**
     * Iterates over every currently loaded pose sampler and executes the {@link PoseSampler#tick(DriverAnimationContainer, PoseSamplerStateContainer)} method
     * <p>
     * The update order is as follows: State machines first, then all others.
     *
     * @param driverContainer Extracted animation data
     * @implNote Only do this once per game tick! For entities, this is handled in the entity joint animator dispatcher.
     */
    public void tick(DriverAnimationContainer driverContainer){
        this.tickUpdateOrderGroup(driverContainer, PoseSampler.UpdateCategory.STATE_MACHINES);
        this.tickUpdateOrderGroup(driverContainer, PoseSampler.UpdateCategory.OTHER);
    }

    private void tickUpdateOrderGroup(DriverAnimationContainer driverContainer, PoseSampler.UpdateCategory updateOrder){
        this.poseSamplers.values().stream()
                .filter((poseSampler -> poseSampler.getUpdateCategory() == updateOrder))
                .sorted()
                .forEach((poseSampler -> poseSampler.tick(driverContainer, this)));
    }
}
