package com.trainguy9512.locomotion.animation.data.key;

import com.trainguy9512.locomotion.animation.data.AnimationDataContainer;

import java.util.function.Supplier;

/**
 * Represents a key for associating with data within pose samplers and animation driver containers
 * <p>
 * These keys are used for storing the default value of a type of data, and then accessing it
 * whenever an instance is needed.
 * <p>
 *
 * @see AnimationDataContainer
 */
public class AnimationDataKey<D> {

    private final String identifier;
    private final Supplier<D> defaultValue;

    protected AnimationDataKey(String identifier, Supplier<D> defaultValue){
        this.identifier = identifier;
        this.defaultValue = defaultValue;
    }

    public static <D> AnimationDataKey<D> dataKeyOf(String identifier, Supplier<D> defaultValue){
        return new AnimationDataKey<>(identifier, defaultValue);
    }



    /**
     * Returns the string identifier for this animation data key.
     */
    public String getIdentifier(){
        return this.identifier;
    }

    /**
     * Creates a new instance from the data key's default supplier.
     */
    public D createInstance(){
        return this.defaultValue.get();
    }
}
