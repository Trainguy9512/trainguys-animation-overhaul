package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.SoftOverride;

public class PoseSampler {

    private final String identifier;

    protected PoseSampler(Builder<?> builder){
        this.identifier = builder.identifier == null ? "null" : builder.identifier;
    }

    public static Builder<?> of(String identifier){
        return new Builder<>(identifier);
    }

    public static class Builder<B extends Builder<B>> {

        protected String identifier;

        protected Builder(String identifier) {
            this.identifier = identifier;
        }

        public PoseSampler build(){
            return new PoseSampler(this);
        }
    }

    public <L extends Enum<L>> AnimationPose<L> sample(JointSkeleton<L> jointSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer){
        return AnimationPose.of(jointSkeleton);
    }

    public <L extends Enum<L>> AnimationPose<L> sampleFromInputPose(AnimationPose<L> inputPose, JointSkeleton<L> jointSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer){
        return this.sample(jointSkeleton, cachedPoseContainer);
    }

    public void tick(){
    }

    public String getIdentifier(){
        return this.identifier;
    }

}
