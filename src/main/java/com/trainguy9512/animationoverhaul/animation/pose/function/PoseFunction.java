package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.data.OnTickDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PoseFunction {

    @NotNull AnimationPose compute(PoseFunction.FunctionInterpolationContext context);

    void tick(FunctionEvaluationState evaluationState);

    public record FunctionEvaluationState(OnTickDataContainer dataContainer, boolean isRelevant, boolean shouldReset){
        public static FunctionEvaluationState of(OnTickDataContainer dataContainer, boolean isRelevant, boolean shouldReset){
            return new FunctionEvaluationState(dataContainer, isRelevant, shouldReset);
        }

        public FunctionEvaluationState modify(boolean isRelevant, boolean shouldReset){
            return FunctionEvaluationState.of(this.dataContainer, isRelevant, shouldReset);
        }
    }


    public interface FunctionInterpolationContext {
        PoseCalculationDataContainer dataContainer();
        float partialTicks();
    }


}
