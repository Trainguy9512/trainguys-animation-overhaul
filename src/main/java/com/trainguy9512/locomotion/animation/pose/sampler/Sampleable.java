package com.trainguy9512.locomotion.animation.pose.sampler;

import com.trainguy9512.locomotion.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.locomotion.animation.pose.AnimationPose;
import com.trainguy9512.locomotion.animation.joint.JointSkeleton;

@Deprecated
public interface Sampleable {
    /**
     * Creates an animation pose
     * @param dataContainer     Animation data container for interpolated data.
     * @param jointSkeleton     Reference joint skeleton.
     * @param partialTicks      Percentage of a tick since the previous tick.
     * @return                  New animation pose
     */
    AnimationPose sample(PoseCalculationDataContainer dataContainer, JointSkeleton jointSkeleton, float partialTicks);
}
