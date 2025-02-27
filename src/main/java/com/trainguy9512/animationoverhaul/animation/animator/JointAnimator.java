package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.data.OnTickDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.function.PoseFunction;
import com.trainguy9512.animationoverhaul.animation.pose.function.cache.SavedCachedPoseContainer;

/**
 * Uses a data reference and a joint skeleton to calculate a pose once per tick.
 * @param <T> Object used for data reference
 */
public interface JointAnimator<T> {

    /**
     * Creates a joint skeleton created upon class construction
     * @return                              Built joint skeleton
     */
    JointSkeleton buildSkeleton();

    /**
     * Uses an object for data reference and updates the animation data container. Called once per tick, prior to pose samplers updating and pose calculation.
     * @param dataReference                 Object used as reference for updating the animation data container
     * @param dataContainer                 Data container from the previous tick
     */
    void extractAnimationData(T dataReference, OnTickDataContainer dataContainer);

    /**
     * Creates the pose function that will return an animation pose for the joint animator.
     * @param cachedPoseContainer           Container for registering and retrieving saved cached poses.
     * @return                              Pose function that returns a pose in local space.
     */
    PoseFunction<LocalSpacePose> constructPoseFunction(SavedCachedPoseContainer cachedPoseContainer);

    default PoseCalculationFrequency getPoseCalulationFrequency(){
        return PoseCalculationFrequency.CALCULATE_ONCE_PER_TICK;
    }

    public enum PoseCalculationFrequency {
        CALCULATE_EVERY_FRAME,
        CALCULATE_ONCE_PER_TICK
    }
}
