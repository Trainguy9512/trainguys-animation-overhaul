package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;

public class AnimationMontageTrack extends TimeBasedAnimationState {

    public AnimationMontageTrack(String identifier) {
        super(identifier);
    }

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    public AnimationPose sample(LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer){
        return new AnimationPose(locatorSkeleton);
    }
}
