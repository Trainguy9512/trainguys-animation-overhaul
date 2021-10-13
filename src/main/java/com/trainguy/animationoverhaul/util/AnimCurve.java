package com.trainguy.animationoverhaul.util;

import net.minecraft.util.Mth;

public class AnimCurve {
    private float time;
    private boolean isIncreasing;

    public AnimCurve(float t){
        this.time = t;
    }

    public AnimCurve oscillate(float period, float offset){
        this.time = 1 - (Math.abs(getRepeatValue(this.time, period, offset) - 0.5F) * 2F);
        this.isIncreasing = Math.sin(this.time * Mth.PI * 2 - offset * Mth.PI * 2) > 0;
        return this;
    }

    // float value = new AnimCurve(<timer goes here>).oscillate(4).applyCurve(<bezier here>).getValue

    public AnimCurve oscillate(float period){
        return oscillate(period, 0);
    }

    public AnimCurve repeat(float period, float offset){
        this.time = getRepeatValue(this.time, period, offset);
        return this;
    }

    public AnimCurve repeat(float period){
        return repeat(period, 0);
    }

    public AnimCurve shortenAndOffsetForward(float offset){
        this.time = Mth.clamp(this.time * (1 + offset) - offset, 0, 1);
        return this;
    }

    public AnimCurve shortenAndOffsetBackward(float offset){
        this.time = Mth.clamp(this.time * (1 + offset), 0, 1);
        return this;
    }

    public AnimCurve setValue(float value){
        this.time = value;
        return this;
    }

    public float getValue(){
        return this.time;
    }

    private float getRepeatValue(float time, float period, float offset){
        return ((time - offset * period) % period) / period;
    }
}
