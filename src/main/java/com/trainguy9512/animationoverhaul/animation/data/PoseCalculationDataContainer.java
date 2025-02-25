package com.trainguy9512.animationoverhaul.animation.data;

import com.trainguy9512.animationoverhaul.animation.data.driver.Driver;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDataKey;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDriverKey;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.PoseSampler;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.Sampleable;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.SampleableFromInput;

/**
 * Interface for retrieving the interpolated value of a driver during the sampling stage.
 */
public interface PoseCalculationDataContainer {

    /**
     * Returns the interpolated value of an animation driver.
     * @param driverKey         {@link AnimationDataKey <>} of the driver to return a value for.
     * @param partialTicks      Percentage of a tick since the previous tick.
     * @return                  Interpolated value
     */
    public <D> D getDriverValueInterpolated(AnimationDriverKey<D> driverKey, float partialTicks);

    /**
     * Returns an animation pose sampled from the given pose sampler
     * @param poseSamplerKey    {@link AnimationDataKey<>} of the pose sampler to sample.
     * @param partialTicks      Percentage of a tick since the previous tick.
     */
    public <P extends PoseSampler & Sampleable> AnimationPose sample(AnimationDataKey<P> poseSamplerKey, float partialTicks);

    /**
     * Returns an animation pose sampled from the given pose sampler that takes an input.
     * @param poseSamplerKey    {@link AnimationDataKey<>} of the pose sampler to sample.
     * @param animationPose     Input animation pose.
     * @param partialTicks      Percentage of a tick since the previous tick.
     */
    public <P extends PoseSampler & SampleableFromInput> AnimationPose sample(AnimationDataKey<P> poseSamplerKey, AnimationPose animationPose, float partialTicks);

    /**
     * Returns the joint skeleton for the data container.
     */
    public JointSkeleton getJointSkeleton();

}
