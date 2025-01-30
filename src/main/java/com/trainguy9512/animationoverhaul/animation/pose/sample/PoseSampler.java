package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import org.jetbrains.annotations.NotNull;

public class PoseSampler implements Comparable<PoseSampler> {

    private String identifier;
    private int updateOrder;

    protected PoseSampler(Builder<?> builder){
        this.identifier = builder.identifier;
        this.updateOrder = builder.updateOrder;
    }

    public static Builder<?> of(){
        return new Builder<>();
    }

    @Override
    public int compareTo(@NotNull PoseSampler poseSampler) {
        return Integer.compare(this.updateOrder, poseSampler.updateOrder);
    }

    public static class Builder<B extends Builder<B>> {

        private String identifier;
        private int updateOrder = 50;

        protected Builder() {
        }

        @SuppressWarnings("unchecked")
        public B setUpdateOrder(int updateOrder){
            this.updateOrder = updateOrder;
            return (B) this;
        }

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

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public UpdateCategory getUpdateCategory(){
        return UpdateCategory.OTHER;
    }

    public enum UpdateCategory {
        STATE_MACHINES,
        OTHER
    }
}
