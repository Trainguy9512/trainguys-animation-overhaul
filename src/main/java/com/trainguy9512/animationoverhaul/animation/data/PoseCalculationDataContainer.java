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
     * Returns the joint skeleton for the data container.
     */
    public JointSkeleton getJointSkeleton();

}
