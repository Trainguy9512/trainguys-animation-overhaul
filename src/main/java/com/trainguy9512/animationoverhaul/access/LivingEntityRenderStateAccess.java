package com.trainguy9512.animationoverhaul.access;

import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface LivingEntityRenderStateAccess {

    void animationOverhaul$setInterpolatedAnimationPose(AnimationPose interpolatedAnimationPose);
    Optional<AnimationPose> animationOverhaul$getInterpolatedAnimationPose();

    void animationOverhaul$setEntityJointAnimator(EntityJointAnimator<?, ?> livingEntityJointAnimator);
    Optional<EntityJointAnimator<?, ?>> animationOverhaul$getEntityJointAnimator();
}
