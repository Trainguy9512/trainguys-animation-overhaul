package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;

public interface PoseFunction {

    AnimationPose calculate(PoseFunction.FunctionContext functionContext);

    public interface FunctionContext {
        PoseCalculationDataContainer getDataContainer();
        float getPartialTicks();
    }
}
