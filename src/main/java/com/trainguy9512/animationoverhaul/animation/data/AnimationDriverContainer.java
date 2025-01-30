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
public class AnimationDriverContainer {

    private final HashMap<AnimationDriverKey<?>, AnimationDriver<?>> animationDrivers;

    public AnimationDriverContainer(){
        this.animationDrivers = Maps.newHashMap();
    }

    /**
     * Retrieves value of animation driver from the given key.
     *
     * @param dataKey   the {@link AnimationDriverKey} attached to the desired {@link AnimationDriver}
     *
     * @return an {@link AnimationDriver} object reference
     */
    public <D> D get(AnimationDriverKey<D> dataKey) {
        return this.getDriver(dataKey).get();
    }

    /**
     * Sets the value of the animation driver from the given key.
     *
     * @param dataKey   Data key value of param
     * @param value     New value to set
     */
    public <D> void set(AnimationDriverKey<D> dataKey, D value){
        this.getDriver(dataKey).set(value);
    }

    @SuppressWarnings("unchecked")
    private <D> AnimationDriver<D> getDriver(AnimationDriverKey<D> dataKey) {
        return (AnimationDriver<D>) this.animationDrivers.computeIfAbsent(dataKey, AnimationDriver::new);
    }

    private static class AnimationDriver<D>{

        private D value;
        private final Supplier<D> defaultValue;

        private AnimationDriver(AnimationDriverKey<D> key){
            this.value = key.defaultValue().get();
            this.defaultValue = key.defaultValue();
        }

        public D get(){
            return this.value;
        }

        public void set(D value){
            this.value = value != null ? value : this.defaultValue.get();
        }

        public void reset(){
            this.set(this.defaultValue.get());
        }
    }
}
