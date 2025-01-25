package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;

public interface SampleableFromInput {
    AnimationPose sample(JointSkeleton jointSkeleton, AnimationPose inputPose);
}
