package com.trainguy9512.animationoverhaul.animation.data;

import com.trainguy9512.animationoverhaul.animation.data.driver.Driver;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDataKey;
import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDriverKey;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.PoseSampler;

public interface OnTickDataContainer {

    /**
     * Returns the value of a driver during the current tick.
     * @param driverKey         {@link AnimationDataKey <>} associated with driver.
     * @return                  Value
     */
    public <D> D getDriverValue(AnimationDriverKey<D> driverKey);

    /**
     * Returns the value of a driver during the previous tick.
     * @param driverKey         {@link AnimationDataKey<>} associated with driver.
     * @return                  Value
     */
    public <D> D getPreviousDriverValue(AnimationDriverKey<D> driverKey);

    /**
     * Loads a driver with a new value for the current tick.
     * @param driverKey         {@link AnimationDataKey<>} of the driver to load
     * @param newValue          Value to load.
     * @implNote                Ensure that any mutable values inputted here are copies of themselves!
     */
    public <D> void loadValueIntoDriver(AnimationDriverKey<D> driverKey, D newValue);

    /**
     * Resets the value of a driver to the default, which is specified by the key.
     * @param driverKey         {@link AnimationDataKey<>} of the driver being reset.
     */
    public <D> void resetDriverValue(AnimationDriverKey<D> driverKey);

    /**
     * Retrieves a pose sampler from the given key.
     *
     * @param poseSamplerKey    The {@link AnimationDataKey<>} attached to the desired pose sampler.
     *
     * @return a {@link PoseSampler} object reference
     */
    public <P extends PoseSampler> P getPoseSampler(AnimationDataKey<P> poseSamplerKey);
}
