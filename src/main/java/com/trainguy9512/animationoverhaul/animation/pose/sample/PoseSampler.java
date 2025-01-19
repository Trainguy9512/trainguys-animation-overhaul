package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerKey;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
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
            PoseSamplerKey<PoseSampler> b = PoseSamplerKey.of(() -> PoseSampler.of().build()).build();
        }

        public PoseSampler build(){
            return new PoseSampler(this);
        }
    }

    public <L extends Enum<L>> AnimationPose<L> sample(JointSkeleton<L> jointSkeleton){
        return AnimationPose.of(jointSkeleton);
    }

    public <L extends Enum<L>> AnimationPose<L> sampleFromInputPose(AnimationPose<L> inputPose, JointSkeleton<L> jointSkeleton){
        return this.sample(jointSkeleton);
    }

    /**
     * Updates the pose sampler using information from the data container. Called once per tick after animation data is extracted by the joint animator but prior to pose calculation.
     * @param animationDataContainer
     */
    public void tick(AnimationDataContainer animationDataContainer){
    }

    public String getIdentifier(){
        return this.identifier;
    }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }
}
