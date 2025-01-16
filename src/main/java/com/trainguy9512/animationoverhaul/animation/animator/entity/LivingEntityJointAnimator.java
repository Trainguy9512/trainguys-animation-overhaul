package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public abstract class LivingEntityJointAnimator<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<S>, L extends Enum<L>> extends EntityJointAnimator<T, S, M, L> {
    @Deprecated
    protected float getWalkAnimationSpeed(T livingEntity){
        return livingEntity.walkAnimation.speed();
    }
    @Deprecated
    protected float getWalkAnimationPosition(T livingEntity){
        return livingEntity.walkAnimation.position();
    }
}
