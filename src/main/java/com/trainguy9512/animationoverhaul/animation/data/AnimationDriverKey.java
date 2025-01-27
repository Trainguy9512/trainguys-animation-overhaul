package com.trainguy9512.animationoverhaul.animation.data;

import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;

import java.util.function.Supplier;

/**
 * Represents a key for associating with animation variables in animation data containers
 * <p>
 * Animation Variable Keys are used in the static definition of {@link AnimationDriverContainer.AnimationDriver} objects
 * in {@link JointAnimator} classes, which are created at runtime using the {@link AnimationDriverKey#defaultValue} as
 * the template. These keys are then referenced when accessing those variables from {@link AnimationDriverContainer}
 * objects, with a key-and-value design structure.
 * <p>
 * Rather than creating animation variable objects in the class and referencing them directly,
 * these static keys are used instead to reference animation variables from the animation data
 * container to ensure that each object is unique per-entity, while
 * still having referencable objects being usable across the class.
 *
 * @param <D> the type of data being stored
 *
 * @see PoseSampler
 * @see AnimationDriverContainer
 */
public class AnimationDriverKey<D> {

    /**
     * The supplier used for providing a template for when new animation variable
     * objects are assigned to each individual animation data container.
     * <p>
     *
     * @implNote This {@link Supplier} supplies a value upon each access, so a default value can be variable
     * if the input involves things like random number generation
     */
    private final Supplier<D> defaultValue;
    private final String identifier;

    protected AnimationDriverKey(Supplier<D> defaultValue, String identifier) {
        this.defaultValue = defaultValue;
        this.identifier = identifier;
    }

    /**
     * Creates a builder for an animation driver key
     *
     * @param defaultValue The default value of the animation variable
     */
    public static <D> Builder<D> builder(Supplier<D> defaultValue){
        return new Builder<>(defaultValue);
    }

    public Supplier<D> getDefaultValue() {
        return this.defaultValue;
    }

    public String getIdentifier() {
        return identifier;
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
            return new AnimationDriverKey<>(this.defaultValue, this.identifier);
        }
    }
}
