package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import org.jetbrains.annotations.NotNull;

public class PoseSampler implements Comparable<PoseSampler> {

    private String identifier;
    private int updateOrder;

    protected PoseSampler(Builder<?> builder){
        this.identifier = builder.identifier;
        this.updateOrder = builder.updateOrder;
    }

    public static Builder<?> builder(){
        return new Builder<>();
    }

    @Override
    public int compareTo(@NotNull PoseSampler poseSampler) {
        return Integer.compare(this.updateOrder, poseSampler.updateOrder);
    }

    public static class Builder<B extends Builder<B>> {

        private String identifier = "null";
        private int updateOrder = 50;

        protected Builder() {
        }

        /**
         * Sets the update order for the pose sampler builder.
         * <p>
         * When pose samplers are being ticked, the update order determines the order
         * in which each pose sampler is updated. This is important for when the ticking
         * of one pose sampler relies on another.
         * @implNote State machines automatically tick prior to everything else, regardless of update order.
         * @return This key builder
         */
        @SuppressWarnings("unchecked")
        public B setUpdateOrder(int updateOrder){
            this.updateOrder = updateOrder;
            return (B) this;
        }

        /**
         * Sets the identifier for this pose sampler builder.
         * <p>
         * This is used in in-game value debugging for
         * identifying pose samplers and printing them to the screen.
         *
         * @param identifier the string name used in the identifier
         * @return This key builder
         */
        @SuppressWarnings("unchecked")
        public B setIdentifier(String identifier){
            this.identifier = identifier;
            return (B) this;
        }

        public PoseSampler build(){
            return new PoseSampler(this);
        }
    }

    /**
     * Updates the pose sampler using information from the data container. Called once per tick after animation data is extracted by the joint animator but prior to pose calculation.
     *
     * @param animationDriverContainer Extracted animation data
     * @param poseSamplerStateContainer Pose sampler state container used for referencing information from other pose samplers. Only
     */
    public void tick(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
    }

    public String getIdentifier(){
        return this.identifier;
    }

    public UpdateCategory getUpdateCategory(){
        return UpdateCategory.OTHER;
    }

    public enum UpdateCategory {
        STATE_MACHINES,
        OTHER
    }
}
