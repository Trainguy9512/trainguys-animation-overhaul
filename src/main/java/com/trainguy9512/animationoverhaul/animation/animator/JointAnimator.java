package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;

/**
 * Structure used for associating locator enums with data such as transform hierarchy, default offset poses, model parts, and mirrors.
 * @param <L> Enum joint structure
 * @param <R> Object used as render state for reference
 */
public abstract class JointAnimator<R, L extends Enum<L>> {

    protected final JointSkeleton<L> jointSkeleton;

    protected JointAnimator() {
        this.jointSkeleton = buildSkeleton();
    }

    public JointSkeleton<L> getJointSkeleton(){
        return this.jointSkeleton;
    }

    protected abstract JointSkeleton<L> buildSkeleton();

    public abstract void tick(R animatedObjectReference, AnimationDataContainer entityAnimationData);

    public abstract AnimationPose<L> calculatePose(R animatedObjectReference, AnimationDataContainer entityAnimationData);
}
