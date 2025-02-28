package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.mojang.blaze3d.Blaze3D;
import com.trainguy9512.animationoverhaul.animation.data.OnTickDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseCalculationDataContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import org.jetbrains.annotations.NotNull;

public interface PoseFunction<P extends AnimationPose> {

    /**
     * Computes and returns an animation pose using its inputs.
     * @param context           Interpolation context, containing the driver container, partial ticks float, and elapsed game time for calculating values every frame.
     * @implNote                Called every frame for joint animators that compute a new pose every frame, or once per tick.
     */
    @NotNull P compute(PoseFunction.FunctionInterpolationContext context);

    /**
     * Updates the function and then updates the function's inputs.
     * @param evaluationState   Current state of the evaluation, with the data container as well as
     * @implNote                If an input is deemed irrelevant, or not necessary during pose calculation, the input does not need to be ticked.
     * @implNote                Called once per tick, with the assumption that per-frame values can be interpolated.
     */
    void tick(FunctionEvaluationState evaluationState);

    /**
     * Recursive method that creates and returns a new copy of the function with its inputs also copied.
     * This ensures that no pose function is referenced twice, besides cached pose functions.
     * If a pose were referenced as an input twice, then it would tick and compute twice, which can lead to undesirable results.
     * @implNote                Called after the joint animator's pose function is constructed.
     * @return                  Clean copy of the pose function with its inputs being clean copies
     */
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
