package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.data.OnTickDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PoseFunction<P extends AnimationPose> {

    @NotNull P compute(PoseFunction.FunctionInterpolationContext context);

    void tick(FunctionEvaluationState evaluationState);

    public record FunctionEvaluationState(OnTickDataContainer dataContainer, boolean isResetting){
        public static FunctionEvaluationState of(OnTickDataContainer dataContainer, boolean isResetting){
            return new FunctionEvaluationState(dataContainer, isResetting);
        }

        public FunctionEvaluationState modify(boolean isResetting){
            return FunctionEvaluationState.of(this.dataContainer, isResetting);
        }
    }


    public interface FunctionInterpolationContext {
        PoseCalculationDataContainer dataContainer();
        float partialTicks();
    }


}
