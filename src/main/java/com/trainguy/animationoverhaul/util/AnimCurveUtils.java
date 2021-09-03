package com.trainguy.animationoverhaul.util;

import net.minecraft.util.Mth;

public class AnimCurveUtils {
    public static float LinearToEaseInOut(float x){
        return Mth.sin((x) * Mth.PI - Mth.PI * 0.5F) * 0.5F + 0.5F;
    }
    public static float LinearToEaseInOutWeight(float x, int w){
        return x < (1F / w / 2F) || x > (1 - w * 0.5F) ? Mth.sin(x * Mth.PI * w * 2 - Mth.PI / 2) * 0.5F + 0.5F : 1;
    }
    public static float LinearToEaseCondition(float x, boolean bl){
        return bl ? Mth.sqrt(Mth.sin(x * Mth.PI / 2)) : 1 - Mth.sqrt(Mth.sin(x * Mth.PI / 2 + Mth.PI / 2));
    }
}
