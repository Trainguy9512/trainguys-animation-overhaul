package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;

public class PoseSampler {

    private String identifier;

    protected PoseSampler(Builder<?> builder){
    }

    public static Builder<?> of(){
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> {

        private String identifier;
        private int updateOrder = 50;

        protected Builder() {
        }

        public B setUpdateOrder(int)

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
