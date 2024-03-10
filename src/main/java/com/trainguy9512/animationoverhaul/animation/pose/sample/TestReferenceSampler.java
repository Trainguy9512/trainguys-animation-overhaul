package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;

public class TestReferenceSampler extends SampleableAnimationState {

    private String cachedPoseIdentifier;

    private TestReferenceSampler(String identifier, String cachedPoseIdentifier) {
        super(identifier);
        this.cachedPoseIdentifier = cachedPoseIdentifier;
    }

    public static TestReferenceSampler of(String identifier, String cachedPoseIdentifier){
        return new TestReferenceSampler(identifier, cachedPoseIdentifier);
    }

    @Override
    public AnimationPose sample(LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        return cachedPoseContainer.getCachedPose(this.cachedPoseIdentifier, locatorSkeleton);
    }

    public void tick(){

    }
}
