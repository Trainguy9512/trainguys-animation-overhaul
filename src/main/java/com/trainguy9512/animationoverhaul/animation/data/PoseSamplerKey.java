package com.trainguy9512.animationoverhaul.animation.data;

import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;

import java.util.function.Supplier;

/**
 * Represents a key for associating with pose samplers in animation data containers
 * <p>
 * Pose Sampler Keys are used in the static definition of {@link PoseSampler} objects in {@link JointAnimator} classes, which are
 * created at runtime using the {@link PoseSamplerKey#defaultValue} as the template. These keys are then referenced
 * when accessing those pose samplers from {@link AnimationDataContainer} objects, with a key-and-value design structure.
 * <p>
 * Rather than creating pose sampler
 * objects in the class and referencing them directly, these static keys are used instead to reference
 * pose samplers from the animation data container to ensure that each object is unique per-entity, while
 * still having referencable objects being usable across the class.
 *
 * @param <P> the type of {@link PoseSampler}
 *
 * @see PoseSampler
 * @see AnimationDataContainer
 */
public class PoseSamplerKey<P extends PoseSampler> {

    /**
     * The supplier used for providing a template for when new pose sampler
     * objects are assigned to each individual animation data container.
     * <p>
     *
     * @implNote This {@link Supplier} supplies a pose sampler upon each access, so a default value can be variable
     * if the input involves things like random number generation
     */
    private final Supplier<P> defaultValue;

    /**
     * The string identifier, used primarily in debugging.
     */
    private final String identifier;

    private PoseSamplerKey(Builder<P> builder) {
        this.defaultValue = builder.defaultValue;
        this.identifier = builder.identifier;
    }

    /**
     * Creates a {@link Builder} for building a pose sampler key.
     *
     * @param defaultValue The default value supplier that provides the template for each animation data container instance
     */
    public static <P extends PoseSampler> Builder<P> of(Supplier<P> defaultValue){
        return new Builder<>(defaultValue);
    }

    /**
     * Returns the identifier for this key, utilized for debugging
     *
     * @return the {@link String} identifier attached to this key
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Returns the default value {@link Supplier} for this key
     * @return default value {@link Supplier}
     */
    private Supplier<P> getDefaultValueSupplier(){
        return this.defaultValue;
    }


    /**
     * Returns a new {@link PoseSampler} created from the {@link PoseSamplerKey#defaultValue}
     *  {@link Supplier}, and then sets its identifier to be the same as this key's identifier and
     *  sets its animation data container parent to be that of the supplied animation data container
     *
     * @param animationDataContainer the {@link AnimationDataContainer} parent
     *
     * @return a pose sample of type {@link P}
     */
    public P getSuppliedDefaultValue(AnimationDataContainer animationDataContainer){
        P poseSampler = this.getDefaultValueSupplier().get();
        poseSampler.setIdentifier(this.getIdentifier());
        poseSampler.setAnimationDataContainer(animationDataContainer);
        return poseSampler;
    }

    /**
     * A mutable builder for {@link PoseSamplerKey} objects.
     *
     * @param <P> the type of {@link PoseSampler}
     */
    public static class Builder<P extends PoseSampler> {

        private final Supplier<P> defaultValue;

        private String identifier = "null";

        protected Builder(Supplier<P> defaultValue) {
            this.defaultValue = defaultValue;
        }

        /**
         * Sets the {@link Builder#identifier} for this key builder
         * <p>
         * This is used in in-game value debugging for
         * identifying pose samplers and printing them to the screen.
         *
         * @param identifier the string name used in the identifier
         * @return this key builder
         */
        public Builder<P> setIdentifier(String identifier){
            this.identifier = identifier;
            return this;
        }

        /**
         * Returns a new {@link PoseSamplerKey}
         * @return an {@link PoseSamplerKey}
         */
        public PoseSamplerKey<P> build(){
            return new PoseSamplerKey<>(this);
        }
    }
}
