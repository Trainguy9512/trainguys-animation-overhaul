package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;

public class TestReferenceSampler extends PoseSampler {

    private String cachedPoseIdentifier;

    private TestReferenceSampler(String identifier, String cachedPoseIdentifier) {
        super(identifier);
        this.cachedPoseIdentifier = cachedPoseIdentifier;
    }

    public static TestReferenceSampler of(String identifier, String cachedPoseIdentifier){
        return new TestReferenceSampler(identifier, cachedPoseIdentifier);
    }

    @Override
    public AnimationPose sample(JointSkeleton jointSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        return cachedPoseContainer.getCachedPose(this.cachedPoseIdentifier, jointSkeleton);
    }

    public void tick(){

    }
}
