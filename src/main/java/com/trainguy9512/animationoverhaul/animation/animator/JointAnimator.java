package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;

/**
 * Structure used for associating locator enums with data such as transform hierarchy, default offset poses, model parts, and mirrors.
 * @param <L> Enum joint structure
 * @param <T> Object used for data reference
 */
public abstract class JointAnimator<T, L extends Enum<L>> {

    protected final JointSkeleton<L> jointSkeleton;

    protected JointAnimator() {
        this.jointSkeleton = buildSkeleton();
    }

    public JointSkeleton<L> getJointSkeleton(){
        return this.jointSkeleton;
    }

    protected abstract JointSkeleton<L> buildSkeleton();

    public abstract void tick(T animatedObjectReference, AnimationDataContainer entityAnimationData);

    public abstract AnimationPose<L> calculatePose(AnimationDataContainer entityAnimationData);
}
