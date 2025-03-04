package com.trainguy9512.locomotion.access;

import com.trainguy9512.locomotion.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.locomotion.animation.pose.AnimationPose;

import java.util.Optional;

public interface LivingEntityRenderStateAccess {

    void animationOverhaul$setInterpolatedAnimationPose(AnimationPose interpolatedAnimationPose);
    Optional<AnimationPose> animationOverhaul$getInterpolatedAnimationPose();

    void animationOverhaul$setEntityJointAnimator(EntityJointAnimator<?, ?> livingEntityJointAnimator);
    Optional<EntityJointAnimator<?, ?>> animationOverhaul$getEntityJointAnimator();
}
