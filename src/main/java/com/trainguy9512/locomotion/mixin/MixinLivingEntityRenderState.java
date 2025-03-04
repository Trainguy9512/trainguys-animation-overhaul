package com.trainguy9512.locomotion.mixin;

import com.trainguy9512.locomotion.access.LivingEntityRenderStateAccess;
import com.trainguy9512.locomotion.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.locomotion.animation.pose.AnimationPose;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

@Mixin(LivingEntityRenderState.class)
public class MixinLivingEntityRenderState implements LivingEntityRenderStateAccess {

    @Unique
    private AnimationPose interpolatedAnimationPose;

    @Unique
    private EntityJointAnimator<?, ?> entityJointAnimator;

    @Unique
    @Override
    public void animationOverhaul$setInterpolatedAnimationPose(AnimationPose interpolatedAnimationPose) {
        this.interpolatedAnimationPose = interpolatedAnimationPose;
    }

    @Override
    public Optional<AnimationPose> animationOverhaul$getInterpolatedAnimationPose() {
        return Optional.ofNullable(this.interpolatedAnimationPose);
    }

    @Override
    public void animationOverhaul$setEntityJointAnimator(EntityJointAnimator<?, ?> entityJointAnimator) {
        this.entityJointAnimator = entityJointAnimator;
    }

    @Override
    public Optional<EntityJointAnimator<?, ?>> animationOverhaul$getEntityJointAnimator() {
        return Optional.ofNullable(this.entityJointAnimator);
    }
}
