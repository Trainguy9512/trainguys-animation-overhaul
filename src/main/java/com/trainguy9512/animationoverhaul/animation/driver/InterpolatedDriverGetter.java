package com.trainguy9512.animationoverhaul.animation.driver;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDataKey;

public interface InterpolatedDriverGetter {

    /**
     * Returns the interpolated value of the animation driver.
     * @param driverKey         Key of the {@link Driver} to return a value for.
     * @param partialTicks      Percentage of a tick since the previous tick.
     * @return                  Interpolated Driver Value
     */
    public <D> D get(AnimationDataKey<Driver<D>> driverKey, float partialTicks);
}
