package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;

public class PoseSampler {

    private final String identifier;

    public PoseSampler(String identifier){
        this.identifier = identifier;
    }

    public <L extends Enum<L>> AnimationPose<L> sample(JointSkeleton<L> jointSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
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
