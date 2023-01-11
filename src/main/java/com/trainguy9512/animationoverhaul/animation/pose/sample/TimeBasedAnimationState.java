package com.trainguy9512.animationoverhaul.animation.pose.sample;

public class TimeBasedAnimationState extends SampleableAnimationState {

    private float timeElapsed = 0;
    private float playRate = 1;

    public TimeBasedAnimationState(String identifier) {
        super(identifier);
    }

    public void setPlayRate(float playRate) {
        this.playRate = playRate;
    }

    public float getPlayRate(){
        return this.playRate;
    }

    public void setTimeElapsed(float timeElapsed){
        this.timeElapsed = timeElapsed;
    }

    public void resetTime(){
        this.setTimeElapsed(0);
    }

    public float getTimeElapsed(){
        return this.timeElapsed;
    }

    @Override
    public void tick(){
        this.timeElapsed += this.playRate;
    }
}
