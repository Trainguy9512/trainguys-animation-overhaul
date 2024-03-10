package com.trainguy9512.animationoverhaul.animation.data;

import java.util.function.Supplier;

public class AnimationVariableKey<D> {

    private final Supplier<D> defaultValue;
    private final String debugIdentifier;

    private AnimationVariableKey(Supplier<D> defaultValue, String debugIdentifier) {
        this.defaultValue = defaultValue;
        this.debugIdentifier = debugIdentifier;
    }

    /**
     * Creates a builder for a key
     *
     * @param defaultValue  The default value of the animation variable
     */
    public static <D> Builder<D> of(Supplier<D> defaultValue){
        return new Builder<>(defaultValue);
    }

    public String getDebugIdentifier() {
        return debugIdentifier;
    }

    public Supplier<D> getDefaultValue(){
        return this.defaultValue;
    }

    public static class Builder<D> {

        private final Supplier<D> defaultValue;
        private String debugIdentifier = "null";


        private Builder(Supplier<D> defaultValue) {
            this.defaultValue = defaultValue;
        }

        /**
         * Sets the debug identifier for the key builder. This is used in in-game value debugging for identifying animation variables and printing them to the screen.
         *
         * @param debugIdentifier  The string name used in the identifier
         */
        public Builder<D> setDebugIdentifier(String debugIdentifier){
            this.debugIdentifier = debugIdentifier;
            return this;
        }

        public AnimationVariableKey<D> build(){
            return new AnimationVariableKey<>(this.defaultValue, this.debugIdentifier);
        }
    }
}
