package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.pose.ComponentSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import org.jetbrains.annotations.NotNull;

public record LocalPoseConversionFunction(PoseFunction<ComponentSpacePose> input) implements PoseFunction<LocalSpacePose> {
    @Override
    public @NotNull LocalSpacePose compute(FunctionInterpolationContext context) {
        return this.input.compute(context).convertedToLocalSpace();
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        this.input.tick(evaluationState);
    }

    @Override
    public PoseFunction<LocalSpacePose> wrapUnique() {
        return LocalPoseConversionFunction.of(this.input.wrapUnique());
    }

    public static LocalPoseConversionFunction of(PoseFunction<ComponentSpacePose> input){
        return new LocalPoseConversionFunction(input);
    }
}
