package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.util.animation.AnimationData;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;

public abstract class LivingEntityPartAnimator<T extends LivingEntity, M extends EntityModel<T>> {

    protected final T livingEntity;
    protected M model;
    protected final LivingEntityAnimParams livingEntityAnimParams;

    public LivingEntityPartAnimator(T livingEntity, M model, LivingEntityAnimParams livingEntityAnimParams){
        this.livingEntity = livingEntity;
        this.model = model;
        this.livingEntityAnimParams = livingEntityAnimParams;
    }

    protected abstract void initModel();
    protected abstract void adjustTimers();
    protected abstract void animateParts();
    protected abstract void finalizeModel();

    protected AnimationData.TimelineGroup getTimelineGroup(String entityKey, String animationKey){
        return AnimationData.loadedData.get(entityKey, animationKey);
    }
}
