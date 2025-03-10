package com.trainguy9512.locomotion.animation.data.key;

import com.trainguy9512.locomotion.animation.data.AnimationDataContainer;
import com.trainguy9512.locomotion.animation.data.driver.VariableDriver;

import java.util.function.Supplier;

/**
 * Key for animation drivers that are stored once per data container.
 * <p>
 * These keys are used for storing the default value of a driver object, to initialize every time a
 * driver is instanced from a new data container.
 * <p>
 *
 * @see AnimationDataContainer
 * @author James Pelter
 */
public class AnimationDriverKey<D> extends AnimationDataKey<VariableDriver<D>> {

    protected AnimationDriverKey(String identifier, Supplier<VariableDriver<D>> defaultValue){
        super(identifier, defaultValue);
    }

    public static <D> AnimationDriverKey<D> driverKeyOf(String identifier, Supplier<VariableDriver<D>> defaultValue){
        return new AnimationDriverKey<>(identifier, defaultValue);
    }
}
