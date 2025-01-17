package com.trainguy9512.animationoverhaul.access;

import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.animator.entity.LivingEntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;

public interface LivingEntityRenderStateAccess {

    void animationOverhaul$setInterpolatedAnimationPose(AnimationPose<?> interpolatedAnimationPose);
    AnimationPose<?> animationOverhaul$getInterpolatedAnimationPose();

    void animationOverhaul$setEntityJointAnimator(EntityJointAnimator<?, ?, ?, ?> livingEntityJointAnimator);
    EntityJointAnimator<?, ?, ?, ?> animationOverhaul$getEntityJointAnimator();
}
