package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.AnimationSequencePlayer;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.PoseSampler;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public abstract class TimeBasedPoseFunction<P extends AnimationPose> implements PoseFunction<P> {

    Function<FunctionEvaluationState, Boolean> isPlayingFunction;
    Function<FunctionEvaluationState, Float> playRateFunction;

    protected float timeTicksElapsed;
    protected float playRate;
    protected boolean isPlaying;

    protected TimeBasedPoseFunction(Builder<?> builder){
        this.playRateFunction = builder.playRateFunction;
        this.isPlayingFunction = builder.isPlayingFunction;
        this.timeTicksElapsed = 0;
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        this.isPlaying = isPlayingFunction.apply(evaluationState);
        this.playRate = this.isPlaying ? playRateFunction.apply(evaluationState) : 0;

        if(evaluationState.isResetting()){
            this.timeTicksElapsed = 0;
        }
        if(this.isPlaying){
            this.timeTicksElapsed += this.playRate;
        }
    }

    protected float getInterpolatedTimeElapsed(FunctionInterpolationContext context){
        return this.timeTicksElapsed - (1 - context.partialTicks()) * this.playRate;
    }

    public static class Builder<B> {

        Function<FunctionEvaluationState, Float> playRateFunction;
        Function<FunctionEvaluationState, Boolean> isPlayingFunction;

        protected Builder() {
            this.playRateFunction = (interpolationContext) -> 1f;
            this.isPlayingFunction = (interpolationContext) -> true;
        }

        @SuppressWarnings("unchecked")
        public B setPlayRate(Function<FunctionEvaluationState, Float> playRateFunction) {
            this.playRateFunction = playRateFunction;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setIsPlaying(Function<FunctionEvaluationState, Boolean> isPlayingFunction) {
            this.isPlayingFunction = isPlayingFunction;
            return (B) this;
        }
    }
}
