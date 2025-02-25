package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public abstract class TimeBasedPoseFunction<P extends AnimationPose> implements PoseFunction<P> {

    Function<FunctionEvaluationState, Float> playRateFunction;
    Function<FunctionEvaluationState, Boolean> isPlayingFunction;

    protected float timeTicksElapsed;
    protected float playRate;
    protected boolean isPlaying;

    private TimeBasedPoseFunction(){
        this.playRateFunction = (interpolationContext) -> 1f;
        this.isPlayingFunction = (interpolationContext) -> true;
        this.timeTicksElapsed = 0;
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        this.playRate = playRateFunction.apply(evaluationState);
        this.isPlaying = isPlayingFunction.apply(evaluationState);

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
}
