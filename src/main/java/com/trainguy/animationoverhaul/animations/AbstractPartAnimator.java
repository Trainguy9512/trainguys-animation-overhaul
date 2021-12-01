package com.trainguy.animationoverhaul.animations;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractPartAnimator<T extends LivingEntity, M extends EntityModel<T>>{
    protected abstract void adjustTimers();
    protected abstract void animateParts();
    protected abstract void finalizeModel();
    protected abstract void adjustTimersInventory();
    protected abstract void animatePartsInventory();
}
