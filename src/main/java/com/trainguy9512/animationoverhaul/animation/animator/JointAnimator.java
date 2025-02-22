package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.data.OnTickDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;

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
     * Calculates and returns an animation pose once per tick, after pose sampler update and animation data extraction
     * @param dataContainer                 Data container containing extracted animation variable data.
     * @param jointSkeleton                 Joint skeleton used by the animator.
     * @param partialTicks                  The percentage of a tick since the previous tick.
     * @return                              Calculated animation pose to be passed off to the baked animation pose.
     */
    AnimationPose calculatePose(PoseCalculationDataContainer dataContainer, JointSkeleton jointSkeleton, float partialTicks);

    default PoseCalculationFrequency getPoseCalulationFrequency(){
        return PoseCalculationFrequency.CALCULATE_ONCE_PER_TICK;
    }

    public enum PoseCalculationFrequency {
        CALCULATE_EVERY_FRAME,
        CALCULATE_ONCE_PER_TICK
    }
}
