package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.ComponentSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Function;

public record JointTransformerFunction<P extends AnimationPose>(PoseFunction<P> input, String joint, TransformChannelConfiguration<Vector3f> translationConfiguration, TransformChannelConfiguration<Quaternionf> rotationConfiguration, TransformChannelConfiguration<Vector3f> scaleConfiguration) implements PoseFunction<P> {

    private static <P extends AnimationPose> JointTransformerFunction<P> of(Builder<P> builder){
        return new JointTransformerFunction<>(builder.input, builder.joint, builder.translationConfiguration, builder.rotationConfiguration, builder.scaleConfiguration);
    }

    @Override
    public @NotNull P compute(FunctionInterpolationContext context) {
        if(!context.dataContainer().getJointSkeleton().containsJoint(this.joint)){
            throw new IllegalArgumentException("Cannot run joint transformer function on joint " + this.joint + ", for it is not present within the skeleton.");
        }
        P pose = this.input.compute(context);

        JointTransform jointTransform = pose.getJointTransform(this.joint);
        this.transformJoint(jointTransform, context, this.translationConfiguration, JointTransform::translate);
        this.transformJoint(jointTransform, context, this.rotationConfiguration, JointTransform::rotate);
        pose.setJointTransform(this.joint, jointTransform);

        return pose;
    }

    private <X> void transformJoint(JointTransform jointTransform, FunctionInterpolationContext context, TransformChannelConfiguration<X> configuration, Transformer<X> transformer){
        transformer.transform(jointTransform, configuration.transformFunction.apply(context), configuration.transformSpace, configuration.transformType);
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        this.input.tick(evaluationState);
    }

    @Override
    public PoseFunction<P> wrapUnique() {
        return new JointTransformerFunction<>(this.input.wrapUnique(), this.joint, this.translationConfiguration, this.rotationConfiguration, this.scaleConfiguration);
    }

    public static Builder<LocalSpacePose> localOrParentSpaceBuilder(PoseFunction<LocalSpacePose> poseFunction, String joint){
        return new Builder<>(poseFunction, joint);
    }

    public static Builder<ComponentSpacePose> componentSpaceBuilder(PoseFunction<ComponentSpacePose> poseFunction, String joint){
        return new Builder<>(poseFunction, joint);
    }

    public static class Builder<P extends AnimationPose> {

        private final PoseFunction<P> input;
        private final String joint;
        private TransformChannelConfiguration<Vector3f> translationConfiguration;
        private TransformChannelConfiguration<Quaternionf> rotationConfiguration;
        private TransformChannelConfiguration<Vector3f> scaleConfiguration;

        private Builder(PoseFunction<P> poseFunction, String joint){
            this.joint = joint;
            this.input = poseFunction;
            this.translationConfiguration = TransformChannelConfiguration.of((context) -> new Vector3f(0), JointTransform.TransformType.IGNORE, JointTransform.TransformSpace.LOCAL);
            this.rotationConfiguration = TransformChannelConfiguration.of((context) -> new Quaternionf().identity(), JointTransform.TransformType.IGNORE, JointTransform.TransformSpace.LOCAL);
            this.scaleConfiguration = TransformChannelConfiguration.of((context) -> new Vector3f(0), JointTransform.TransformType.IGNORE, JointTransform.TransformSpace.LOCAL);
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

        public JointTransformerFunction<P> build(){
            return JointTransformerFunction.of(this);
        }
    }

    public record TransformChannelConfiguration<X>(Function<FunctionInterpolationContext, X> transformFunction, JointTransform.TransformType transformType, JointTransform.TransformSpace transformSpace){

        public static <X> TransformChannelConfiguration<X> of(Function<FunctionInterpolationContext, X> transformFunction, JointTransform.TransformType transformType, JointTransform.TransformSpace transformSpace){
            return new TransformChannelConfiguration<>(transformFunction, transformType, transformSpace);
        }
    }

    @FunctionalInterface
    private interface Transformer<X> {
        void transform(JointTransform jointTransform, X value, JointTransform.TransformSpace transformSpace, JointTransform.TransformType transformType);
    }
}
