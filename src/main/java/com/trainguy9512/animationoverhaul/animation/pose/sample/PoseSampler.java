package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.AnimationPoseSamplerKey;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.SoftOverride;

public class PoseSampler {

    private String identifier;

    private AnimationDataContainer animationDataContainer;

    protected PoseSampler(Builder<?> builder){
    }

    public static Builder<?> of(){
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> {

        protected Builder() {
            AnimationPoseSamplerKey<PoseSampler> b = AnimationPoseSamplerKey.of(() -> PoseSampler.of().build()).build();
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

    public void tick(){
    }

    public String getIdentifier(){
        return this.identifier;
    }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    protected AnimationDataContainer getAnimationDataContainer(){
        return this.animationDataContainer;
    }

    public void setAnimationDataContainer(AnimationDataContainer animationDataContainer){
        this.animationDataContainer = animationDataContainer;
    }

}
