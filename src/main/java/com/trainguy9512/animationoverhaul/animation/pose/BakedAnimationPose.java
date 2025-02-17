package com.trainguy9512.animationoverhaul.animation.pose;


import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;

public class BakedAnimationPose {

    private AnimationPose pose;
    private AnimationPose poseOld;

    public BakedAnimationPose(JointSkeleton jointSkeleton){
        this.pose = AnimationPose.of(jointSkeleton);
        this.poseOld = AnimationPose.of(jointSkeleton);
    }

    /**
     * Pushes the current pose to the previous pose, and sets the provided animation pose to a copy of the new current pose.
     * @param animationPose New current animation pose
     */
    public void pushPose(AnimationPose animationPose){
        this.poseOld = pose;
        this.pose = new AnimationPose(animationPose);
    }

    /**
     * Returns an interpolated animation pose using the provided partial ticks. Used when extracting the render state.
     * @param partialTicks Time since the previous tick
     * @return Interpolated animation pose
     */
    public AnimationPose getBlendedPose(float partialTicks){
        // uncomment this for debugging
        //partialTicks = 1;
        return this.poseOld.interpolated(this.pose, partialTicks).convertedToEntitySpace();
    }
}
