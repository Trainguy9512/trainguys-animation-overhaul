package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.data.AnimationData;

public class PoseSampler {

    private String identifier;

    protected PoseSampler(Builder<?> builder){
    }

    public static Builder<?> of(){
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> {

        protected Builder() {
        }

        public PoseSampler build(){
            return new PoseSampler(this);
        }
    }

    /**
     * Updates the pose sampler using information from the data container. Called once per tick after animation data is extracted by the joint animator but prior to pose calculation.
     *
     * @param animationData Extracted animation data
     * @param poseSamplerStateContainer Pose sampler state container used for referencing information from other pose samplers. Only
     */
    public void tick(AnimationData animationData, PoseSamplerStateContainer poseSamplerStateContainer){
    }

    public String getIdentifier(){
        return this.identifier;
    }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public UpdateOrder getUpdateOrder(){
        return UpdateOrder.OTHER;
    }

    public enum UpdateOrder {
        STATE_MACHINES,
        OTHER
    }
}
