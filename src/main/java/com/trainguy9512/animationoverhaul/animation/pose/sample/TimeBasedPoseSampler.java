package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerKey;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.sample.notify.AnimNotify;

import java.util.ArrayList;

public class TimeBasedPoseSampler extends PoseSampler {

    protected float timeElapsed;
    private float playRate;
    private boolean playing;
    private boolean resetting;

    protected TimeBasedPoseSampler(Builder<?> builder) {
        super(builder);
        this.timeElapsed = 0;
        this.playRate = builder.playRate;
        this.playing = builder.playing;
        this.resetting = false;
    }

    public static Builder<?> of(){
        return new Builder<>();
    }


    public static class Builder<B extends Builder<B>> extends PoseSampler.Builder<B> {

        private float playRate = 1;
        private boolean playing = true;

        protected Builder() {
            super();
        }

        @SuppressWarnings("unchecked")
        public B setPlayRate(float playRate){
            this.playRate = playRate;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setPlaying(boolean playing){
            this.playing = playing;
            return (B) this;
        }

        @Override
        public TimeBasedPoseSampler build() {
            return new TimeBasedPoseSampler(this);
        }
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

    public float getTimeElapsed(){
        return this.timeElapsed;
    }

    public void resetTime(){
        this.setTimeElapsed(0);
        this.resetting = true;
    }

    public void setPlaying(boolean playing){
        this.playing = playing;
    }

    public boolean getPlaying(){
        return this.playing;
    }


    @Override
    public void tick(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
        if(this.getPlaying() && !this.resetting){
            this.timeElapsed += this.playRate;
        }
        this.resetting = false;
    }
}
