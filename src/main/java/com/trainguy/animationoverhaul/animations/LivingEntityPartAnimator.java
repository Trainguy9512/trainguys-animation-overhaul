package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.time.Easing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public abstract class LivingEntityPartAnimator<T extends LivingEntity, M extends EntityModel<T>> {

    protected final T livingEntity;
    protected M model;
    protected final LivingEntityAnimParams animationParameters;

    protected final String DELTA_X_MOVEMENT = "delta_x_movement";
    protected final String DELTA_Y_MOVEMENT = "delta_y_movement";
    protected final String DELTA_Z_MOVEMENT = "delta_z_movement";

    public LivingEntityPartAnimator(T livingEntity, M model, LivingEntityAnimParams livingEntityAnimParams){
        this.model = model;
        this.livingEntity = livingEntity;
        this.animationParameters = livingEntityAnimParams;
    }

    protected abstract void initModel();
    protected abstract void adjustTimers();
    protected abstract void animateParts();
    protected abstract void finalizeModel();

    protected abstract void adjustTimersInventory();
    protected abstract void animatePartsInventory();

    protected AnimationData.TimelineGroup getTimelineGroup(String animationKey){
        String entityKey = livingEntity.getType().toString().split("\\.")[2];
        return AnimationData.loadedData.get(entityKey, animationKey);
    }

    protected float getEasedAnimationTimer(String identifier, Easing easement){
        return easement.ease(getAnimationTimer(identifier));
    }

    protected float getAnimationTimerEasedSine(String identifier){
        return getEasedAnimationTimer(identifier, Easing.CubicBezier.bezierInOutSine());
    }

    protected float getAnimationTimer(String identifier){
        return ((EntityAccess)livingEntity).getAnimationTimer(identifier);
    }

    protected void setAnimationTimer(String identifier, float value){
        ((EntityAccess)livingEntity).setAnimationTimer(identifier, value);
    }

    protected void incrementAnimationTimer(String identifier, boolean isIncreasing, int ticksToIncrement, int ticksToDecrement) {
        if(ticksToIncrement != 0 && ticksToDecrement != 0){
            incrementAnimationTimer(identifier, isIncreasing, (1/(float)ticksToIncrement), (1/(float)ticksToDecrement));
        }
    }

    protected void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement){
        incrementAnimationTimer(identifier, isIncreasing, increment, decrement, 0, 1);
    }

    protected void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement, float min, float max){
        float previousTimerValue = getAnimationTimer(identifier);
        float delta = Minecraft.getInstance().getDeltaFrameTime();
        setAnimationTimer(identifier, Mth.clamp(previousTimerValue + (isIncreasing ? increment * delta : decrement * delta), min, max));
    }

    protected void resetTimerOnCondition(String identifier, boolean condition, int ticksToIncrement){
        if(condition){
            setAnimationTimer(identifier, 0);
        } else {
            ((EntityAccess)livingEntity).initAnimationTimer(identifier, 1);
            incrementAnimationTimer(identifier, true, ticksToIncrement, 10);
        }
    }
}
