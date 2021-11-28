package com.trainguy.animationoverhaul.animations;

import com.google.common.collect.Lists;
import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.animation.LocatorRig;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.animation.LivingEntityAnimParams;
import com.trainguy.animationoverhaul.util.time.Easing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public abstract class LivingEntityPartAnimator<T extends LivingEntity, M extends EntityModel<T>> {

    protected final T livingEntity;
    protected M model;
    protected final LivingEntityAnimParams animationParameters;
    protected final LocatorRig locatorRig;

    protected final String DELTA_Y = "delta_y";
    protected final String DELTA_Y_OLD = "delta_y_old";

    public LivingEntityPartAnimator(T livingEntity, M model, LivingEntityAnimParams livingEntityAnimParams){
        this.model = model;
        this.livingEntity = livingEntity;
        this.animationParameters = livingEntityAnimParams;
        this.locatorRig = new LocatorRig();
    }

    protected abstract void initModel();
    protected abstract void adjustTimers();
    protected abstract void animateParts();
    protected abstract void finalizeModel();

    protected abstract void adjustTimersInventory();
    protected abstract void animatePartsInventory();

    public void bakeLocatorRig(){
        ((LivingEntityAccess)livingEntity).storeLocatorRig(this.locatorRig);
        this.locatorRig.bakeRig();
    }

    protected void adjustGeneralMovementTimers(){
        float deltaY = (float) (livingEntity.getY() - livingEntity.yo);
        float deltaYOld = getAnimationTimer(DELTA_Y);
        setAnimationTimer(DELTA_Y, deltaY);
        setAnimationTimer(DELTA_Y_OLD, deltaYOld);
    }

    protected AnimationData.TimelineGroup getTimelineGroup(String animationKey){
        String entityKey = livingEntity.getType().toString().split("\\.")[2];
        return AnimationData.loadedData.get(entityKey, animationKey);
    }

    protected float getEasedAnimationTimer(String identifier, Easing easement){
        return easement.ease(getAnimationTimer(identifier));
    }

    protected float getEasedConditionAnimationTimer(String identifier, Easing easementTrue, Easing easementFalse, boolean bl){
        Easing easement = bl ? easementTrue : easementFalse;
        return getEasedAnimationTimer(identifier, easement);
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

    protected float getLookLeftRightTimer(){
        return Mth.clamp((animationParameters.getHeadYRot() / Mth.HALF_PI) + 0.5F, 0, 1);
    }

    protected float getLookUpDownTimer(){
        return Mth.clamp((animationParameters.getHeadXRot() / Mth.PI) + 0.5F, 0, 1);
    }

    protected boolean isLeftHanded(){
        return livingEntity.getMainArm() == HumanoidArm.LEFT;
    }
}
