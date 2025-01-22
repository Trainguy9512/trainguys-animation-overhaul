package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;

/**
 * Uses a data reference and a joint skeleton to calculate a pose once per tick.
 * @param <T> Object used for data reference
 */
public abstract class JointAnimator<T> {

    protected final JointSkeleton jointSkeleton;

    protected JointAnimator() {
        this.jointSkeleton = buildSkeleton();
    }

    /**
     * Returns the joint skeleton used by the animator as reference.
     * @return Joint skeleton
     */
    public JointSkeleton getJointSkeleton(){
        return this.jointSkeleton;
    }

    /**
     * Retrieves a joint skeleton created upon class construction
     * @return Built joint skeleton
     */
    protected abstract JointSkeleton buildSkeleton();

    /**
     * Uses an object for data reference and updates the animation data container. Called once per tick, prior to pose samplers updating and pose calculation.
     * @param dataReference Object used as reference for updating the animation data container
     * @param animationDataContainer Data container from the previous tick
     * @return Resulting data container
     */
    public abstract AnimationDataContainer extractAnimationData(T dataReference, AnimationDataContainer animationDataContainer);

    /**
     * Calculates and returns an animation pose once per tick, after pose sampler update and animation data extraction
     * @param animationDataContainer Data container containing extracted animation variable data.
     * @param poseSamplerStateContainer Data container containing pose sampler states, used for sampling poses.
     * @return Calculated animation pose to be passed off to the baked animation pose
     */
    public abstract AnimationPose calculatePose(AnimationDataContainer animationDataContainer, PoseSamplerStateContainer poseSamplerStateContainer);
}
