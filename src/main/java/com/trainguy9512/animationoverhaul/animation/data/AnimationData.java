package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;

import java.util.*;
import java.util.function.Supplier;

/**
 * Represents a container for data kept track of and updated by a part animator, such as:
 * <ul>
 *     <li>Animation Variables</li>
 *     <li>Pose Samplers</li>
 * </ul>
 *
 * @see AnimationVariableKey
 * @see PoseSamplerKey
 */
public class AnimationData {

    private final HashMap<AnimationVariableKey<?>, AnimationVariable<?>> animationVariableMap;

    public AnimationData(){
        this.animationVariableMap = Maps.newHashMap();
    }


    /**
     * Returns this animation data container's hash map of animation variable keys to animation variables currently loaded.
     *
     * @return {@link HashMap} of {@link AnimationVariableKey} keys to {@link AnimationVariable} values.
     */
    public HashMap<AnimationVariableKey<?>, AnimationVariable<?>> getAnimationVariableMap(){
        return this.animationVariableMap;
    }

    /**
     * Returns a collection of every animation variable currently loaded into this animation data container.
     *
     * @return {@link Collection} of {@link PoseSampler} values.
     */
    public Collection<AnimationVariable<?>> getAnimationVariables(){
        return this.getAnimationVariableMap().values();
    }

    /**
     * Returns an animation variable from the given key. If one is not currently loaded into
     * this animation data container, then a new one is created from the key's default
     * value and loaded into this animation data container and returned.
     *
     * @param dataKey the {@link AnimationVariableKey} attached to the desired {@link AnimationVariable}
     *
     * @return an {@link AnimationVariable} object reference
     */
    @SuppressWarnings("unchecked")
    public <D> AnimationVariable<D> getAnimationVariable(AnimationVariableKey<D> dataKey){
        if(!this.getAnimationVariableMap().containsKey(dataKey)){
            this.getAnimationVariableMap().put(dataKey, new AnimationVariable<>(dataKey));
        }
        return (AnimationVariable<D>) this.getAnimationVariableMap().get(dataKey);
    }


    public static class AnimationVariable<D>{

        private D value;
        private D valueOld;
        private final Supplier<D> defaultValue;

        private AnimationVariable(AnimationVariableKey<D> dataKey){
            this.defaultValue = dataKey.getDefaultValueSupplier();
            this.value = dataKey.getDefaultValueSupplier().get();
            this.valueOld = dataKey.getDefaultValueSupplier().get();
        }

        /**
         * Returns the value of this animation variable.
         *
         * @return - value of the {@link AnimationVariable} instance's type
         */
        public D get(){
            return this.value;
        }

        /**
         * Returns the value of this animation variable prior to the last time it was set.
         *
         * @return - value of the {@link AnimationVariable} instance's type
         */
        public D getOld(){
            return this.valueOld;
        }

        /**
         * Sets the value of this animation variable. Also updates the old value, setting it
         * to what it was prior to this method call.
         *
         * @param value new value of the {@link AnimationVariable} instance's type
         */
        public void set(D value){
            this.valueOld = this.value;
            if(value != null){
                this.value = value;
            } else {
                this.value = defaultValue.get();
            }
        }

        /**
         * Sets this animation variable's value to the default value, supplied from the
         * default value supplier.
         */
        public void setDefaultValue(){
            this.set(this.defaultValue.get());
        }

        /**
         * Returns whether the animation variable's type is that of a float or not.
         * @return {@link Boolean} true if the type is float.
         */
        public boolean isFloat(){
            return this.value instanceof Float;
        }
    }
}
