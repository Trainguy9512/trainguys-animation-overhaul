package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SequencePlayerFunction extends TimeBasedPoseFunction<LocalSpacePose> {

    private final ResourceLocation resourceLocation;

    private SequencePlayerFunction(){
        super();
    }

    @Override
    public @NotNull LocalSpacePose compute(FunctionInterpolationContext context) {
        return null;
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        super.tick(evaluationState);
    }

    public static class Builder<B extends Builder<B>> extends TimeBasedPoseFunction.Builder<B>{
        protected Builder(){
            super();
        }
    }
}
