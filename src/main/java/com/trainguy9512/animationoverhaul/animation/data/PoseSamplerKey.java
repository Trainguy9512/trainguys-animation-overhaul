package com.trainguy9512.animationoverhaul.animation.data;

import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;

import java.util.function.Supplier;

public class PoseSamplerKey<P extends PoseSampler> {

    private final Supplier<P> defaultValue;
    private final String debugIdentifier;

    private PoseSamplerKey(Builder<P> builder) {
        this.defaultValue = builder.defaultValue;
        this.debugIdentifier = builder.debugIdentifier;
    }

    /**
     * Creates a builder for a key
     *
     * @param defaultValue  The default value of the animation variable
     */
    public static <P extends PoseSampler> Builder<P> of(Supplier<P> defaultValue){
        return new Builder<>(defaultValue);
    }

    public String getDebugIdentifier() {
        return debugIdentifier;
    }

    public Supplier<P> getDefaultValue(){
        return this.defaultValue;
    }

    public static class Builder<P extends PoseSampler> {

        private final Supplier<P> defaultValue;
        private String debugIdentifier = "null";


        private Builder(Supplier<P> defaultValue) {
            this.defaultValue = defaultValue;
        }

        /**
         * Sets the debug identifier for the key builder. This is used in in-game value debugging for identifying pose samplers and printing them to the screen.
         *
         * @param debugIdentifier  The string name used in the identifier
         */
        public Builder<P> setDebugIdentifier(String debugIdentifier){
            this.debugIdentifier = debugIdentifier;
            return this;
        }

        public PoseSamplerKey<P> build(){
            return new PoseSamplerKey<>(this);
        }
    }
}
