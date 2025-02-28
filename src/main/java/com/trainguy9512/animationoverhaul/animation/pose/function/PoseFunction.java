package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.mojang.blaze3d.Blaze3D;
import com.trainguy9512.animationoverhaul.animation.data.OnTickDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import org.jetbrains.annotations.NotNull;

public interface PoseFunction<P extends AnimationPose> {

    @NotNull P compute(PoseFunction.FunctionInterpolationContext context);

    void tick(FunctionEvaluationState evaluationState);

    PoseFunction<P> wrapUnique();

    record FunctionEvaluationState(OnTickDataContainer dataContainer, boolean isResetting, long currentTick){

        public static FunctionEvaluationState of(OnTickDataContainer dataContainer, boolean isResetting, long currentTick){
            return new FunctionEvaluationState(dataContainer, isResetting, currentTick);
        }

        public FunctionEvaluationState markedForReset(){
            return FunctionEvaluationState.of(this.dataContainer, true, this.currentTick);
        }

        public FunctionEvaluationState cancelMarkedForReset(){
            return FunctionEvaluationState.of(this.dataContainer, false, this.currentTick);
        }
    }

    record FunctionInterpolationContext(PoseCalculationDataContainer dataContainer, float partialTicks, double gameTime) {
        public static FunctionInterpolationContext of(PoseCalculationDataContainer dataContainer, float partialTicks){
            return new FunctionInterpolationContext(dataContainer, partialTicks, Blaze3D.getTime());
        }
    }
}
