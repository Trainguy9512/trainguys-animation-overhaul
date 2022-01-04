package gg.moonflower.animationoverhaul.util.time;

import gg.moonflower.animationoverhaul.util.data.TimelineGroupData;
import net.minecraft.util.Mth;

public class TimerProcessor {
    private float time;
    private boolean isIncreasing;

    public TimerProcessor(float t){
        this.time = t;
    }

    // float value = new AnimCurve(<timer goes here>).oscillate(4).applyCurve(<bezier here>).getValue

    public TimerProcessor repeat(float period, float offset){
        this.time = getRepeatValue(this.time, period, offset);
        return this;
    }

    public TimerProcessor speedUp(float amount){
        this.time *= amount;
        return this;
    }

    public TimerProcessor repeat(float period){
        return repeat(period, 0);
    }

    public TimerProcessor repeat(TimelineGroupData.TimelineGroup timelineGroup){
        return repeat(framesToTicks(timelineGroup.getFrameLength()), 0);
    }

    public TimerProcessor shortenAndOffsetForward(float offset){
        this.time = Mth.clamp(this.time * (1 + offset) - offset, 0, 1);
        return this;
    }

    public TimerProcessor shortenAndOffsetBackward(float offset){
        this.time = Mth.clamp(this.time * (1 + offset), 0, 1);
        return this;
    }

    public TimerProcessor setValue(float value){
        this.time = value;
        return this;
    }

    public float getValue(){
        return this.time;
    }

    private static float getRepeatValue(float time, float period, float offset){
        return (((time + period) - (offset * period)) % period) / period;
    }

    public static float framesToTicks(float x){
        return x / 1.2F;
    }
}
