package com.trainguy9512.animationoverhaul.util.time;

public class TickTimeUtils {

    private static final float TICKS_PER_SECOND = 20;

    public static float ticksFromSeconds(float x){
        return x * TICKS_PER_SECOND;
    }
}
