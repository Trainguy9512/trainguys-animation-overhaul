package com.trainguy.animationoverhaul.util;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;

public class AnimCurveUtils {
    public static float linearToEaseInOutQuadratic(float x){
        return Mth.sin((x) * Mth.PI - Mth.PI * 0.5F) * 0.5F + 0.5F;
    }
    public static float linearToEaseInOutCubic(float x){
        return x < 0.5 ? linearToEaseCondition(2 * x, false) * 0.5F : linearToEaseCondition(2 * (x - 0.5F), true) * 0.5F + 0.5F;
    }
    public static float linearToEaseInOutWeight(float x, int w){
        return x < (1F / w / 2F) || x > (1 - 1F / w / 2F) ? Mth.sin(x * Mth.PI * w * 2 - Mth.PI / 2) * 0.5F + 0.5F : 1;
    }
    public static float linearToEaseCondition(float x, boolean bl){
        return bl ? Mth.sqrt(Mth.sin(x * Mth.PI / 2)) : 1 - Mth.sqrt(Mth.sin(x * Mth.PI / 2 + Mth.PI / 2));
    }
    public static float linearToInOutInQuadratic(float x){
        return Mth.sin(Mth.PI * x * 2);
    }
    public static float linearToBounce(float x){
        return Mth.sin(Mth.PI * x);
    }
    public static float linearToInOutFollowThroughDecay(float x){
        return Mth.sin(Mth.PI * x * 2) * (1 - x);
    }
    public static float linearToBezier(float x, float x0, float y0, float x1, float y1){
        // Having these exceed the bounds of 0-1 could result in overlapping curves, making it not 1D!
        x0 = Mth.clamp(x0, 0, 1);
        x1 = Mth.clamp(x1, 0, 1);

        return (float) ((x0 * Math.pow(1 - x, 3)) + (3 * x * Math.pow(1 - x, 2) * y0) + (3 * x * x * (1 - x) * x1) + (x * x * x *y1));
    }
}
