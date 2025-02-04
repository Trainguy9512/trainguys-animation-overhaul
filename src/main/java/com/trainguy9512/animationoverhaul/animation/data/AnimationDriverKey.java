package com.trainguy9512.animationoverhaul.animation.data;

import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;

import java.util.function.Supplier;

/**
 * Represents a key for associating with animation variables in animation data containers
 * <p>
 * Animation Variable Keys are used in the static definition of Animation Driver objects
 * in {@link JointAnimator} classes, which are created at runtime using the {@link AnimationDriverKey#defaultValue} as
 * the template. These keys are then referenced when accessing those variables from {@link AnimationDriverContainer}
 * objects, with a key-and-value design structure.
 * <p>
 * Rather than creating animation driver objects in the class and referencing them directly,
 * these static keys are used instead to reference animation drivers from the animation driver
 * container to ensure that each object is unique per-entity, while
 * still having referencable objects being usable across the class.
 *
 * @param <D> the type of data being stored
 * @param defaultValue The supplier used as a template for when a new animation driver is needed for a specific driver container.
 * @param identifier String identifier used for debugging.
 *
 * @see PoseSampler
 * @see AnimationDriverContainer
 */
public record AnimationDriverKey<D>(Supplier<D> defaultValue, String identifier) {

    public AnimationDriverKey(Builder<D> builder) {
        this(builder.defaultValue, builder.identifier);
    }

    /**
     * Creates a builder for an animation driver key
     *
     * @param defaultValue The default value of the animation variable
     */
    public static <D> Builder<D> builder(Supplier<D> defaultValue){
        return new Builder<>(defaultValue);
    }

    public static class Builder<D> {

        private final Supplier<D> defaultValue;
        private String identifier = "null";

        protected Builder(Supplier<D> defaultValue) {
            this.defaultValue = defaultValue;
        }

        /**
         * Sets the {@link AnimationDriverKey.Builder#identifier} for this key builder
         * <p>
         * This is used in in-game value debugging for
         * identifying pose samplers and printing them to the screen.
         *
         * @param identifier the string name used in the identifier
         * @return this key builder
         */
        public Builder<D> setIdentifier(String identifier){
            this.identifier = identifier;
            return this;
        }

        public AnimationDriverKey<D> build(){
            return new AnimationDriverKey<>(this);
        }
    }
}
