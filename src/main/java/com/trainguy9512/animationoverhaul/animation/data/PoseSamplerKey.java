package com.trainguy9512.animationoverhaul.animation.data;

import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;

import java.util.function.Supplier;

/**
 * Represents a key for associating with pose samplers in animation data containers
 * <p>
 * Pose Sampler Keys are used in the static definition of {@link PoseSampler} objects in {@link JointAnimator} classes, which are
 * created at runtime using the {@link PoseSamplerKey#defaultValue} as the template. These keys are then referenced
 * when accessing those pose samplers from {@link AnimationDriverContainer} objects, with a key-and-value design structure.
 * <p>
 * Rather than creating pose sampler
 * objects in the class and referencing them directly, these static keys are used instead to reference
 * pose samplers from individual pose sampler state containers to ensure that each object is unique per-entity, while
 * still having referencable objects being usable across the class.
 *
 * @param <P> the type of {@link PoseSampler}
 *
 * @param defaultValue The supplier that is accessed each time a new pose sampler needs to be constructed.
 *
 * @see PoseSampler
 * @see AnimationDriverContainer
 */
public record PoseSamplerKey<P extends PoseSampler>(Supplier<P> defaultValue) {

    /**
     * Creates a {@link Builder} for building a pose sampler key.
     *
     * @param defaultValue The default value supplier that provides the template for each animation data container instance
     */
    public static <P extends PoseSampler> Builder<P> builder(Supplier<P> defaultValue){
        return new Builder<>(defaultValue);
    }


    /**
     * Returns a new {@link PoseSampler} created from the {@link PoseSamplerKey#defaultValue}
     *  {@link Supplier}, and then sets its identifier to be the same as this key's identifier.
     *
     * @return a pose sample of type {@link P}
     */
    public P constructPoseSampler(){
        return this.defaultValue.get();
    }

    /**
     * A mutable builder for {@link PoseSamplerKey} objects.
     *
     * @param <P> the type of {@link PoseSampler}
     */
    public static class Builder<P extends PoseSampler> {

        private final Supplier<P> defaultValue;

        protected Builder(Supplier<P> defaultValue) {
            this.defaultValue = defaultValue;
        }

        /**
         * Returns a new {@link PoseSamplerKey}
         * @return an {@link PoseSamplerKey}
         */
        public PoseSamplerKey<P> build(){
            return new PoseSamplerKey<>(this.defaultValue);
        }
    }
}
