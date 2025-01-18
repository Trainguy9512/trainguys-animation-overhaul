package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;

/**
 * Uses a data reference and a joint skeleton to calculate a pose once per tick.
 * @param <L> Enum joint structure
 * @param <T> Object used for data reference
 */
public abstract class JointAnimator<T, L extends Enum<L>> {

    protected final JointSkeleton<L> jointSkeleton;

    protected JointAnimator() {
        this.jointSkeleton = buildSkeleton();
    }

    /**
     * Returns the joint skeleton used by the animator as reference.
     * @return
     */
    public JointSkeleton<L> getJointSkeleton(){
        return this.jointSkeleton;
    }

    /**
     * Creates a joint skeleton upon class construction
     * @return
     */
    protected abstract JointSkeleton<L> buildSkeleton();

    /**
     * Uses an object for data reference and updates the animation data container. Called once per tick, prior to pose samplers updating and pose calculation.
     * @param dataReference Object used as reference for updating the animation data container
     * @param animationDataContainer Data container from the previous tick
     * @return Resulting data container
     */
    public abstract AnimationDataContainer extractAnimationData(T dataReference, AnimationDataContainer animationDataContainer);

    /**
     *
     * @param entityAnimationData
     * @return
     */
    public abstract AnimationPose<L> calculatePose(AnimationDataContainer entityAnimationData);
}
