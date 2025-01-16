package com.trainguy9512.animationoverhaul.mixin;

import com.trainguy9512.animationoverhaul.access.LivingEntityRenderStateAccess;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.animator.entity.LivingEntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class MixinLivingEntityRenderState implements LivingEntityRenderStateAccess {

    @Unique
    private AnimationPose<?> animationOverhaul$interpolatedAnimationPose;

    @Unique
    private EntityJointAnimator<?, ?, ?> animationOverhaul$entityJointAnimator;

    @Override
    public void animationOverhaul$setInterpolatedAnimationPose(AnimationPose<?> interpolatedAnimationPose) {
        this.animationOverhaul$interpolatedAnimationPose = interpolatedAnimationPose;
    }

    @Override
    public AnimationPose<?> animationOverhaul$getInterpolatedAnimationPose() {
        return this.animationOverhaul$interpolatedAnimationPose;
    }

    @Override
    public void animationOverhaul$setEntityJointAnimator(EntityJointAnimator<?, ?, ?> entityJointAnimator) {
        this.animationOverhaul$entityJointAnimator = entityJointAnimator;
    }

    @Override
    public EntityJointAnimator<?, ?, ?> animationOverhaul$getEntityJointAnimator() {
        return this.animationOverhaul$entityJointAnimator;
    }
}
