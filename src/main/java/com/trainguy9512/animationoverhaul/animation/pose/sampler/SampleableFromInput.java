package com.trainguy9512.animationoverhaul.animation.pose.sampler;

import com.trainguy9512.animationoverhaul.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;

public interface SampleableFromInput {
    /**
     * Creates an animation pose from an input.
     * @param dataContainer     Animation data container for interpolated data.
     * @param jointSkeleton     Reference joint skeleton.
     * @param inputPose         Input animation pose.
     * @param partialTicks      Percentage of a tick since the previous tick.
     * @return                  New animation pose
     */
    AnimationPose sample(PoseCalculationDataContainer dataContainer, JointSkeleton jointSkeleton, AnimationPose inputPose, float partialTicks);
}
