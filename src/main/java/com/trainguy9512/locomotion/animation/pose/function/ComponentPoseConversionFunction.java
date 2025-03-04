package com.trainguy9512.locomotion.animation.pose.function;

import com.trainguy9512.locomotion.animation.pose.ComponentSpacePose;
import com.trainguy9512.locomotion.animation.pose.LocalSpacePose;
import org.jetbrains.annotations.NotNull;

public record ComponentPoseConversionFunction(PoseFunction<LocalSpacePose> input) implements PoseFunction<ComponentSpacePose> {
    @Override
    public @NotNull ComponentSpacePose compute(FunctionInterpolationContext context) {
        return this.input.compute(context).convertedToComponentSpace();
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        this.input.tick(evaluationState);
    }

    @Override
    public PoseFunction<ComponentSpacePose> wrapUnique() {
        return ComponentPoseConversionFunction.of(this.input.wrapUnique());
    }

    public static ComponentPoseConversionFunction of(PoseFunction<LocalSpacePose> input){
        return new ComponentPoseConversionFunction(input);
    }
}