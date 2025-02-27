package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.ComponentSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Function;

public record JointTransformerFunction<P extends AnimationPose>(PoseFunction<P> input, TransformChannelConfiguration<Vector3f> translationConfiguration, TransformChannelConfiguration<Quaternionf> rotationConfiguration, TransformChannelConfiguration<Vector3f> scaleConfiguration) implements PoseFunction<P> {

    private static <P extends AnimationPose> JointTransformerFunction<P> of(Builder<P> builder){
        return new JointTransformerFunction<>(builder.poseFunction, builder.translationConfiguration, builder.rotationConfiguration, builder.scaleConfiguration);
    }

    @Override
    public @NotNull P compute(FunctionInterpolationContext context) {
        return this.input.compute(context);
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        this.input.tick(evaluationState);
    }

    public static Builder<LocalSpacePose> localOrParentSpaceBuilder(PoseFunction<LocalSpacePose> poseFunction){
        return new Builder<>(poseFunction);
    }

    public static Builder<ComponentSpacePose> componentSpaceBuilder(PoseFunction<ComponentSpacePose> poseFunction){
        return new Builder<>(poseFunction);
    }

    public static class Builder<P extends AnimationPose> {

        private final PoseFunction<P> poseFunction;
        private TransformChannelConfiguration<Vector3f> translationConfiguration;
        private TransformChannelConfiguration<Quaternionf> rotationConfiguration;
        private TransformChannelConfiguration<Vector3f> scaleConfiguration;

        private Builder(PoseFunction<P> poseFunction){
            this.poseFunction = poseFunction;
            this.translationConfiguration = TransformChannelConfiguration.of((context) -> new Vector3f(0), TransformType.IGNORE, JointTransform.TransformSpace.LOCAL);
            this.rotationConfiguration = TransformChannelConfiguration.of((context) -> new Quaternionf().identity(), TransformType.IGNORE, JointTransform.TransformSpace.LOCAL);
            this.scaleConfiguration = TransformChannelConfiguration.of((context) -> new Vector3f(0), TransformType.IGNORE, JointTransform.TransformSpace.LOCAL);
        }

        public Builder<P> setTranslationConfiguration(TransformChannelConfiguration<Vector3f> translationConfiguration){
            this.translationConfiguration = translationConfiguration;
            return this;
        }

        public Builder<P> setRotationConfiguration(TransformChannelConfiguration<Quaternionf> rotationConfiguration){
            this.rotationConfiguration = rotationConfiguration;
            return this;
        }

        public Builder<P> setScaleConfiguration(TransformChannelConfiguration<Vector3f> scaleConfiguration){
            this.scaleConfiguration = scaleConfiguration;
            return this;
        }

        public JointTransformerFunction<P> buildLocalOrParent(){
            return JointTransformerFunction.of(this);
        }
    }

    public record TransformChannelConfiguration<X>(Function<FunctionInterpolationContext, X> transformFunction, TransformType transformType, JointTransform.TransformSpace transformSpace){

        public static <X> TransformChannelConfiguration<X> of(Function<FunctionInterpolationContext, X> transformFunction, TransformType transformType, JointTransform.TransformSpace transformSpace){
            return new TransformChannelConfiguration<>(transformFunction, transformType, transformSpace);
        }
    }

    public enum TransformType {
        IGNORE,
        REPLACE,
        ADD
    }
}
