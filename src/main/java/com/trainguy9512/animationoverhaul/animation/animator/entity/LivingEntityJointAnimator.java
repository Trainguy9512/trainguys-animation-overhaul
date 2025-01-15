package com.trainguy9512.animationoverhaul.animation.animator.entity;

import com.trainguy9512.animationoverhaul.animation.animator.JointAnimator;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public abstract class LivingEntityJointAnimator<R extends LivingEntityRenderState, M extends EntityModel<R>, L extends Enum<L>> extends EntityJointAnimator<R, M, L> {
    @Deprecated
    protected float getWalkAnimationSpeed(R livingEntityRenderState){
        return livingEntityRenderState.walkAnimationSpeed;
    }
    @Deprecated
    protected float getWalkAnimationPosition(R livingEntityRenderState){
        return livingEntityRenderState.walkAnimationPos;
    }
}
