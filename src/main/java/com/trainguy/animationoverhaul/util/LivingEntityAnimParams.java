package com.trainguy.animationoverhaul.util;

public class LivingEntityAnimParams {

    private final float animationPosition;
    private final float animationSpeed;
    private final float tickAtFrame;
    private final float tickDifference;
    private final float delta;
    private final float headYRot;
    private final float headXRot;

    public LivingEntityAnimParams(float animationPosition, float animationSpeed, float tickAtFrame, float tickDifference, float delta, float headYRot, float headXRot){
        this.animationPosition = animationPosition;
        this.animationSpeed = animationSpeed;
        this.tickAtFrame = tickAtFrame;
        this.tickDifference = tickDifference;
        this.delta = delta;
        this.headYRot = headYRot;
        this.headXRot = headXRot;
    }

    public float getAnimationPosition(){
        return this.animationPosition;
    }
    public float getAnimationSpeed(){
        return this.animationSpeed;
    }
    public float getTickAtFrame(){
        return this.tickAtFrame;
    }
    public float getTickDifference(){
        return this.tickDifference;
    }
    public float getDelta(){
        return this.delta;
    }
    public float getHeadYRot(){
        return this.headYRot;
    }
    public float getHeadXRot(){
        return this.headXRot;
    }
}
