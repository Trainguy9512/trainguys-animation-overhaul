package com.trainguy9512.animationoverhaul.animation.pose.function.cache;

import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.function.PoseFunction;
import org.jetbrains.annotations.NotNull;

public class CachedPoseFunction implements PoseFunction<LocalSpacePose> {

    private final PoseFunction<LocalSpacePose> input;

    LocalSpacePose poseCache;
    FunctionEvaluationState evaluationStateCache;

    private CachedPoseFunction(PoseFunction<LocalSpacePose> input){
        this.input = input;
        this.poseCache = null;
        this.evaluationStateCache = null;
    }

    protected static CachedPoseFunction of(PoseFunction<LocalSpacePose> input){
        return new CachedPoseFunction(input);
    }

    @Override
    public @NotNull LocalSpacePose compute(FunctionInterpolationContext context) {
        if(this.poseCache == null){
            this.poseCache = this.input.compute(context);
        }
        return LocalSpacePose.of(this.poseCache);
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        if(this.evaluationStateCache == null){
            this.evaluationStateCache = evaluationState;
        } else {
            if(evaluationState.isResetting()){
                this.evaluationStateCache = this.evaluationStateCache.modify(true);
            }
        }
    }

    public void tickInput(){
        if(this.evaluationStateCache != null){
            this.input.tick(this.evaluationStateCache);
        }
    }

    public void clearCache(){
        this.poseCache = null;
        this.evaluationStateCache = null;

    }
}
