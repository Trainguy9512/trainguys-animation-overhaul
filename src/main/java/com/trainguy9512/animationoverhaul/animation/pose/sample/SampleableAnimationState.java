package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;

public class SampleableAnimationState {

    private final String identifier;

    public SampleableAnimationState(String identifier){
        this.identifier = identifier;
    }

    public <L extends Enum<L>> AnimationPose<L> sample(LocatorSkeleton<L> locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        return AnimationPose.of(locatorSkeleton);
    }

    public <L extends Enum<L>> AnimationPose<L> sampleFromInputPose(AnimationPose<L> inputPose, LocatorSkeleton<L> locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer){
        return this.sample(locatorSkeleton, cachedPoseContainer);
    }

    public void tick(){

    }

    public String getIdentifier(){
        return this.identifier;
    }

}
