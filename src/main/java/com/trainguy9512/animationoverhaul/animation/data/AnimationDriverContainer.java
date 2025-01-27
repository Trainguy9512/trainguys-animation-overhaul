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
 * @see AnimationDriverKey
 * @see PoseSamplerKey
 */
public class AnimationDriverContainer implements AnimationDriverContainerState {

    private final HashMap<AnimationDriverKey<?>, AnimationDriver<?>> animationDrivers;

    public AnimationDriverContainer(){
        this.animationDrivers = Maps.newHashMap();
    }

    /**
     * Retrieves value of animation animator from the given key. If the driver does not exist, then
     * it is created from the default and then retrieved.
     *
     * @param dataKey the {@link AnimationDriverKey} attached to the desired {@link AnimationDriver}
     *
     * @return an {@link AnimationDriver} object reference
     */
    @Override
    public <D> D get(AnimationDriverKey<D> dataKey) {
        return this.animationDrivers.computeIfAbsent((dataKey) -> {new A});
    }




    public static class AnimationDriver<D>{

        private D value;

        private AnimationDriver(D value){
            this.value = value;
        }

        public static <D> AnimationDriver<D> of(AnimationDriverKey<D> dataKey){
            return new AnimationDriver<>(dataKey.getDefaultValueSupplier());
        }

        /**
         * Returns the value of this animation variable.
         *
         * @return - value of the {@link AnimationDriver} instance's type
         */
        public D get(){
            return this.value;
        }

        /**
         * Sets the value of this animation variable. Also updates the old value, setting it
         * to what it was prior to this method call.
         *
         * @param value new value of the {@link AnimationDriver} instance's type
         */
        public void set(D value){
            this.value = value;
        }

        /**
         * Sets this animation variable's value to the default value, supplied from the
         * default value supplier.
         */
        public void reset(){
            this.set(this.defaultValueSupplier.get());
        }
    }
}
