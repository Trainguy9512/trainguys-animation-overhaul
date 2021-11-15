package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.AnimationData;
import com.trainguy.animationoverhaul.util.LivingEntityAnimParams;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;

public abstract class LivingEntityPartAnimator<T extends LivingEntity, M extends EntityModel<T>> {

    protected T livingEntity;
    protected M model;

    public LivingEntityPartAnimator(T livingEntity, M model){
        this.livingEntity = livingEntity;
        this.model = model;
    }

    protected abstract void initModel();
    protected abstract void adjustTimers();
    protected abstract void animateParts();
    protected abstract void finalizeModel();

    protected abstract HashMap<ModelPart, String[]> getModelPartDictionary();

    protected AnimationData.TimelineGroup getTimelineGroup(String entityKey, String animationKey){
        return AnimationData.loadedData.get(entityKey, animationKey);
    }

    protected LivingEntityAnimParams getAnimationParameters(){
        return ((LivingEntityAccess)livingEntity).getAnimationParameters();
    }
}
