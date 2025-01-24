package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerKey;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;

public class PoseSampler {

    private String identifier;

    protected PoseSampler(Builder<?> builder){
    }

    public static Builder<?> of(){
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> {

        protected Builder() {
            PoseSamplerKey<PoseSampler> b = PoseSamplerKey.builder(() -> PoseSampler.of().build()).build();
        }

        public PoseSampler build(){
            return new PoseSampler(this);
        }
    }

    public AnimationPose sample(JointSkeleton jointSkeleton){
        return AnimationPose.of(jointSkeleton);
    }

    public AnimationPose sampleFromInputPose(AnimationPose inputPose, JointSkeleton jointSkeleton){
        return this.sample(jointSkeleton);
    }

    /**
     * Updates the pose sampler using information from the data container. Called once per tick after animation data is extracted by the joint animator but prior to pose calculation.
     *
     * @param animationDataContainer Extracted animation data
     * @param poseSamplerStateContainer Pose sampler state container used for referencing information from other pose samplers. Only
     */
    public void tick(AnimationDataContainer animationDataContainer, PoseSamplerStateContainer poseSamplerStateContainer){
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
