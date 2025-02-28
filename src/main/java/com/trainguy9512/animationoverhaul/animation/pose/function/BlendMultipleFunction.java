package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.data.driver.Driver;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class BlendMultipleFunction implements PoseFunction<LocalSpacePose> {

    private final PoseFunction<LocalSpacePose> baseFunction;
    private final Map<BlendInput, Driver<Float>> inputs;

    public BlendMultipleFunction(PoseFunction<LocalSpacePose> baseFunction, Map<BlendInput, Driver<Float>> inputs){
        this.baseFunction = baseFunction;
        this.inputs = inputs;
    }

    @Override
    public @NotNull LocalSpacePose compute(FunctionInterpolationContext context) {
        LocalSpacePose pose = this.baseFunction.compute(context);
        for(BlendInput blendInput : this.inputs.keySet()) {
            float weight = this.inputs.get(blendInput).getValueInterpolated(context.partialTicks());
            if(weight != 0f){
                if(blendInput.jointMask.isPresent()) {
                    pose = pose.interpolatedFilteredByJoints(blendInput.inputFunction.compute(context), weight, blendInput.jointMask.get());
                } else {
                    pose = pose.interpolated(blendInput.inputFunction.compute(context), weight);
                }
            }
        }
        return pose;
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        this.baseFunction.tick(evaluationState);
        this.inputs.forEach((blendInput, weightDriver) -> {
            weightDriver.pushValueToPrevious();
            float weight = blendInput.weightFunction.apply(evaluationState);
            weightDriver.loadValue(weight);

            if(weight != 0f) {
                blendInput.inputFunction.tick(evaluationState);
            }
        });
    }

    @Override
    public PoseFunction<LocalSpacePose> wrapUnique() {
        Builder builder = BlendMultipleFunction.builder(this.baseFunction.wrapUnique());
        for(BlendInput blendInput : this.inputs.keySet()){
            builder.addBlendInput(blendInput.inputFunction.wrapUnique(), blendInput.weightFunction, blendInput.jointMask.orElse(null));
        }
        return builder.build();
    }

    public static Builder builder(PoseFunction<LocalSpacePose> base){
        return new Builder(base);
    }

    public static class Builder {

        private final PoseFunction<LocalSpacePose> baseFunction;
        private final Map<BlendInput, Driver<Float>> inputs;

        private Builder(PoseFunction<LocalSpacePose> baseFunction){
            this.baseFunction = baseFunction;
            this.inputs = Maps.newHashMap();
        }

        public Builder addBlendInput(PoseFunction<LocalSpacePose> inputFunction, Function<FunctionEvaluationState, Float> weightFunction, @Nullable Set<String> jointMask){
            this.inputs.put(new BlendInput(inputFunction, weightFunction, Optional.ofNullable(jointMask)), Driver.floatDriver(() -> 0f));
            return this;
        }

        public Builder addBlendInput(PoseFunction<LocalSpacePose> inputFunction, Function<FunctionEvaluationState, Float> weightFunction){
            return this.addBlendInput(inputFunction, weightFunction, null);
        }

        public BlendMultipleFunction build(){
            return new BlendMultipleFunction(this.baseFunction, this.inputs);
        }
    }

    public record BlendInput(PoseFunction<LocalSpacePose> inputFunction, Function<FunctionEvaluationState, Float> weightFunction, Optional<Set<String>> jointMask){

    }
}
