package com.trainguy9512.animationoverhaul.animation.driver;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDataKey;

public interface DriverLoader {
    public <D> D getPreviousValue(AnimationDataKey<Driver<D>> driverKey);

    public <D> void loadValue(AnimationDataKey<Driver<D>> driverKey, D newValue);

    public <D> void resetValueToDefault(AnimationDataKey<Driver<D>> driverKey);
}
