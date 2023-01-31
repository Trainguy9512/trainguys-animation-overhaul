package com.trainguy9512.animationoverhaul.util.time;

public class TickTimeUtils {

    private static final float TICKS_PER_SECOND = 20;
    private static final float MAYA_FRAMES_PER_SECOND = 24;

    public static float ticksFromSeconds(float x){
        return x * TICKS_PER_SECOND;
    }

    public static float secondsFromTicks(float x){
        return x / TICKS_PER_SECOND;
    }

    public static float mayaFramesFromSeconds(float x){
        return x * MAYA_FRAMES_PER_SECOND;
    }

    public static float secondsFromMayaFrames(float x){
        return x / MAYA_FRAMES_PER_SECOND;
    }

    public static float ticksFromMayaFrames(float x){
        return ticksFromSeconds(secondsFromMayaFrames(x));
    }

    public static float mayaFramesFromTicks(float x){
        return mayaFramesFromSeconds(secondsFromTicks(x));
    }
}
