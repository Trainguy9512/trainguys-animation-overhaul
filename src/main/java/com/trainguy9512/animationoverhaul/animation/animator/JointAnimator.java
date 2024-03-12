package com.trainguy9512.animationoverhaul.animation.animator;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import net.minecraft.world.entity.Entity;

public abstract class JointAnimator<T, L extends Enum<L>> {

    protected final JointSkeleton<L> jointSkeleton;

    protected JointAnimator() {
        this.jointSkeleton = buildRig();
    }

    public JointSkeleton<L> getJointSkeleton(){
        return this.jointSkeleton;
    }

    protected abstract JointSkeleton<L> buildRig();

    public abstract void tick(T animatedObjectReference, AnimationDataContainer entityAnimationData);

    public abstract AnimationPose<L> calculatePose(T animatedObjectReference, AnimationDataContainer entityAnimationData);
}
