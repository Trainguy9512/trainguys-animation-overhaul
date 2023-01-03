package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;

public class SampleableAnimationState {

    private final String identifier;

    public SampleableAnimationState(String identifier){
        this.identifier = identifier;
    }

    public AnimationPose sample(LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        return new AnimationPose(locatorSkeleton);
    }

    public void tick(){

    }

    public String getIdentifier(){
        return this.identifier;
    }

}
