package com.trainguy9512.locomotion.animation.data;

import com.trainguy9512.locomotion.animation.data.key.AnimationDataKey;
import com.trainguy9512.locomotion.animation.data.key.AnimationDriverKey;
import com.trainguy9512.locomotion.animation.joint.JointSkeleton;

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
