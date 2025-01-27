package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;

/**
 * Uses a data reference and a joint skeleton to calculate a pose once per tick.
 * @param <T> Object used for data reference
 */
public interface JointAnimator<T> {

    /**
     * Creates a joint skeleton created upon class construction
     * @return                              Built joint skeleton
     */
    public JointSkeleton buildSkeleton();

    /**
     * Uses an object for data reference and updates the animation data container. Called once per tick, prior to pose samplers updating and pose calculation.
     * @param dataReference                 Object used as reference for updating the animation data container
     * @param animationDriverContainer      Data container from the previous tick
     */
    public abstract void extractAnimationData(T dataReference, AnimationDriverContainer animationDriverContainer);

    /**
     * Calculates and returns an animation pose once per tick, after pose sampler update and animation data extraction
     * @param animationDriverContainer      Data container containing extracted animation variable data.
     * @param poseSamplerStateContainer     Data container containing pose sampler states, used for sampling poses.
     * @return                              Calculated animation pose to be passed off to the baked animation pose
     */
    public abstract AnimationPose calculatePose(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton);
}
