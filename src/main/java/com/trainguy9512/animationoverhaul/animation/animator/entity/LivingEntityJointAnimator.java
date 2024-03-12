package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public abstract class LivingEntityJointAnimator<T extends LivingEntity, M extends EntityModel<T>, L extends Enum<L>> extends EntityJointAnimator<T, M, L> {
    protected float getWalkAnimationSpeed(T entity){
        return entity.walkAnimation.speed();
    }

    protected float getWalkAnimationPosition(T livingEntity){
        return livingEntity.walkAnimation.position();
    }
}
