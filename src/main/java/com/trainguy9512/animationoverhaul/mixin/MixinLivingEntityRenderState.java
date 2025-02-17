package com.trainguy9512.animationoverhaul.mixin;

import com.trainguy9512.animationoverhaul.access.LivingEntityRenderStateAccess;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

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
    public AnimationPose animationOverhaul$getInterpolatedAnimationPose() {
        return this.interpolatedAnimationPose;
    }

    @Override
    public void animationOverhaul$setEntityJointAnimator(EntityJointAnimator<?, ?> entityJointAnimator) {
        this.entityJointAnimator = entityJointAnimator;
    }

    @Override
    public EntityJointAnimator<?, ?> animationOverhaul$getEntityJointAnimator() {
        return this.entityJointAnimator;
    }
}
