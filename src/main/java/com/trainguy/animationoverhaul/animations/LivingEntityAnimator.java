package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.util.animation.LocatorRig;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.time.Easing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.FlyingAnimal;

public class LivingEntityAnimator<T extends LivingEntity, M extends EntityModel<T>> extends AbstractEntityAnimator<T, M> {

    protected T livingEntity;
    protected M model;
    protected LocatorRig locatorRig;

    protected float tickProgress;
    protected float delta;
    protected float tickAtFrame;
    protected float headXRot;
    protected float headYRot;

    protected static final String DELTA_Y = "delta_y";
    protected static final String DELTA_Y_OLD = "delta_y_old";
    protected static final String ANIMATION_SPEED = "animation_speed";
    protected static final String ANIMATION_SPEED_Y = "animation_speed_y";
    protected static final String ANIMATION_POSITION = "animation_position";
    protected static final String ANIMATION_POSITION_Y = "animation_position_y";

    public LivingEntityAnimator(){
    }

    public void setProperties(T livingEntity, M model, float tickProgress){
        this.model = model;
        this.livingEntity = livingEntity;
        this.tickProgress = tickProgress;
        this.delta = Minecraft.getInstance().getDeltaFrameTime();
        this.tickAtFrame = livingEntity.tickCount + tickProgress;
        this.locatorRig = new LocatorRig();
        setHeadVariables(tickProgress);
    }

    public void animate(){
        this.locatorRig.resetRig();
        if(((LivingEntityAccess)livingEntity).getUseInventoryRenderer()){
            this.adjustTimersInventory();
            this.animatePartsInventory();
            ((LivingEntityAccess)livingEntity).setUseInventoryRenderer(false);
        } else {
            this.adjustTimers();
            this.animateParts();
        }
        this.bakeLocatorRig();
        this.finalizeModel();
    }

    @Override
    protected void adjustTimers() {
        adjustGeneralMovementTimers();
        adjustAnimationSpeedTimers();
    }

    @Override
    protected void animateParts() {

    }

    @Override
    protected void finalizeModel() {

    }

    @Override
    protected void adjustTimersInventory() {

    }

    @Override
    protected void animatePartsInventory() {

    }

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

    private void adjustAnimationSpeedTimers(){
        boolean useVerticalVector = livingEntity instanceof FlyingAnimal;

        float previousAnimationSpeed = getAnimationTimer(ANIMATION_SPEED);
        float previousAnimationSpeedY = getAnimationTimer(ANIMATION_SPEED_Y);
        float previousAnimationPosition = getAnimationTimer(ANIMATION_POSITION);
        float previousAnimationPositionY = getAnimationTimer(ANIMATION_POSITION_Y);

        double deltaX = livingEntity.getX() - livingEntity.xo;
        double deltaY = livingEntity.getY() - livingEntity.yo;
        double deltaZ = livingEntity.getZ() - livingEntity.zo;
        float movementSquared = (float)Math.sqrt(deltaX * deltaX + deltaZ * deltaZ) * 4.0F;
        float movementSquaredY = (float)Math.sqrt(deltaY * deltaY) * 4.0F;
        if (movementSquared > 1.0F) {
            movementSquared = 1.0F;
        }
        if (movementSquaredY > 1.0F) {
            movementSquaredY = 1.0F;
        }

        float finalAnimationSpeed = previousAnimationSpeed + ((movementSquared - previousAnimationSpeed) * 0.4F * this.delta);
        float finalAnimationSpeedY = previousAnimationSpeedY + ((movementSquaredY - previousAnimationSpeedY) * 0.4F * this.delta);
        setAnimationTimer(ANIMATION_SPEED, finalAnimationSpeed);
        setAnimationTimer(ANIMATION_SPEED_Y, finalAnimationSpeedY);
        setAnimationTimer(ANIMATION_POSITION, previousAnimationPosition + finalAnimationSpeed * this.delta);
        setAnimationTimer(ANIMATION_POSITION_Y, previousAnimationPositionY + finalAnimationSpeedY * this.delta);
    }

    private void setHeadVariables(float tickAtFrame){
        float h = Mth.rotLerp(tickAtFrame, livingEntity.yBodyRotO, livingEntity.yBodyRot);
        float j = Mth.rotLerp(tickAtFrame, livingEntity.yHeadRotO, livingEntity.yHeadRot);
        float k = j - h;
        float o;
        if (livingEntity.isPassenger() && livingEntity.getVehicle() instanceof LivingEntity livingEntity2) {
            h = Mth.rotLerp(tickAtFrame, livingEntity2.yBodyRotO, livingEntity2.yBodyRot);
            k = j - h;
            o = Mth.wrapDegrees(k);
            if (o < -85.0F) {
                o = -85.0F;
            }

            if (o >= 85.0F) {
                o = 85.0F;
            }

            h = j - o;
            if (o * o > 2500.0F) {
                h += o * 0.2F;
            }

            k = j - h;
        }

        this.headXRot = (float) Math.toRadians(Mth.lerp(tickAtFrame, livingEntity.xRotO, livingEntity.getXRot()));
        this.headYRot = (float) Math.toRadians(k);
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

    protected float getAnimationTimerEasedQuad(String identifier){
        return getEasedAnimationTimer(identifier, Easing.CubicBezier.bezierInOutQuad());
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
        return Mth.clamp((this.headYRot / Mth.HALF_PI) + 0.5F, 0, 1);
    }

    protected float getLookUpDownTimer(){
        return Mth.clamp((this.headXRot / Mth.PI) + 0.5F, 0, 1);
    }

    protected boolean isLeftHanded(){
        return livingEntity.getMainArm() == HumanoidArm.LEFT;
    }
}
