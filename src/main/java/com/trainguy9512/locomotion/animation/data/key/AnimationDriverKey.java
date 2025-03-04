package com.trainguy9512.locomotion.animation.data.key;

import com.trainguy9512.locomotion.animation.data.driver.Driver;

import java.util.function.Supplier;

public class AnimationDriverKey<D> extends AnimationDataKey<Driver<D>> {

    protected AnimationDriverKey(String identifier, Supplier<Driver<D>> defaultValue){
        super(identifier, defaultValue);
    }

    public static <D> AnimationDriverKey<D> driverKeyOf(String identifier, Supplier<Driver<D>> defaultValue){
        return new AnimationDriverKey<>(identifier, defaultValue);
    }
}
