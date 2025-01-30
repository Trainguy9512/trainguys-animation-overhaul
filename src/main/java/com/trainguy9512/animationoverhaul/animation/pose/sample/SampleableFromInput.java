package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;

public interface SampleableFromInput {
    AnimationPose sample(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton, AnimationPose inputPose);
}
