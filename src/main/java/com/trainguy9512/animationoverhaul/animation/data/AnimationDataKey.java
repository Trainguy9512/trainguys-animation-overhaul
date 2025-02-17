package com.trainguy9512.animationoverhaul.animation.data;

import com.trainguy9512.animationoverhaul.animation.pose.sampler.PoseSampler;

import java.util.function.Supplier;

/**
 * Represents a key for associating with data within pose samplers and animation driver containers
 * <p>
 * These keys are used for storing the default value of a type of data, and then accessing it
 * whenever an instance is needed.
 * <p>
 *
 * @param <D>           The type of data associated with the key
 * @param identifier    String identifier used for debugging
 * @param dataSupplier  The default value of the key
 *
 * @see PoseSampler
 * @see AnimationDriverContainer
 */
public record AnimationDataKey<D>(String identifier, Supplier<D> dataSupplier) {

    public static <D> AnimationDataKey<D> of(String identifier, Supplier<D> dataSupplier){
        return new AnimationDataKey<>(identifier, dataSupplier);
    }

    public D createInstance(){
        return this.dataSupplier.get();
    }
}
