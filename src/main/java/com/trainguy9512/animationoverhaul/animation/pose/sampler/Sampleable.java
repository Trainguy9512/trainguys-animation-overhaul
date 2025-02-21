package com.trainguy9512.animationoverhaul.animation.pose.sampler;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;

public interface Sampleable {
    AnimationPose sample(DriverAnimationContainer driverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton);
}
