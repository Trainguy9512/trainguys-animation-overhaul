package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.AnimationSequencePlayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class SequencePlayerFunction extends TimeBasedPoseFunction<LocalSpacePose> {

    private final ResourceLocation animationSequence;
    private final boolean looping;

    protected SequencePlayerFunction(Function<FunctionEvaluationState, Boolean> isPlayingFunction, Function<FunctionEvaluationState, Float> playRateFunction, float resetStartTimeOffsetTicks, ResourceLocation animationSequence, boolean looping){
        super(isPlayingFunction, playRateFunction, resetStartTimeOffsetTicks);
        this.animationSequence = animationSequence;
        this.looping = looping;
    }

    @Override
    public @NotNull LocalSpacePose compute(FunctionInterpolationContext context) {
        return LocalSpacePose.fromAnimationSequence(
                context.dataContainer().getJointSkeleton(),
                this.animationSequence,
                this.getInterpolatedTimeElapsed(context),
                this.looping
        );
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        super.tick(evaluationState);
    }

    @Override
    public PoseFunction<LocalSpacePose> wrapUnique() {
        return new SequencePlayerFunction(this.isPlayingFunction, this.playRateFunction, this.resetStartTimeOffsetTicks, this.animationSequence, this.looping);
    }

    public static Builder<?> builder(ResourceLocation animationSequence){
        return new Builder<>(animationSequence);
    }

    public static class Builder<B extends Builder<B>> extends TimeBasedPoseFunction.Builder<B>{

        private final ResourceLocation animationSequence;
        private boolean looping;

        protected Builder(ResourceLocation animationSequence){
            super();
            this.animationSequence = animationSequence;
            this.looping = false;
        }

        /**
         * Sets whether the animation sequence function will loop or not when the end of the animation is reached.
         * @implNote                The animation sequence will always be looped in full, the reset start time only
         *                          affects where the animation starts when reset.
         */
        @SuppressWarnings("unchecked")
        public B setLooping(boolean looping){
            this.looping = looping;
            return (B) this;
        }

        public SequencePlayerFunction build(){
            return new SequencePlayerFunction(this.isPlayingFunction, this.playRateFunction, this.resetStartTimeOffsetTicks, this.animationSequence, this.looping);
        }
    }
}
