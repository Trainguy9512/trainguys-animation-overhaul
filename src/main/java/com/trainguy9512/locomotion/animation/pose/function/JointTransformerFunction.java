package com.trainguy9512.locomotion.animation.pose.function;

import com.trainguy9512.locomotion.animation.joint.JointTransform;
import com.trainguy9512.locomotion.animation.pose.AnimationPose;
import com.trainguy9512.locomotion.animation.pose.ComponentSpacePose;
import com.trainguy9512.locomotion.animation.pose.LocalSpacePose;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Function;

public class JointTransformerFunction<P extends AnimationPose> implements PoseFunction<P> {

    private final PoseFunction<P> input;
    private final String joint;
    private final TransformChannelConfiguration<Vector3f> translationConfiguration;
    private final TransformChannelConfiguration<Quaternionf> rotationConfiguration;
    private final Function<FunctionInterpolationContext, Float> weightFunction;

    private JointTransformerFunction(PoseFunction<P> input, String joint, TransformChannelConfiguration<Vector3f> translationConfiguration, TransformChannelConfiguration<Quaternionf> rotationConfiguration, Function<FunctionInterpolationContext, Float> weightFunction) {
        this.input = input;
        this.joint = joint;
        this.translationConfiguration = translationConfiguration;
        this.rotationConfiguration = rotationConfiguration;
        this.weightFunction = weightFunction;
    }

    private static <P extends AnimationPose> JointTransformerFunction<P> of(Builder<P> builder){
        return new JointTransformerFunction<>(builder.input, builder.joint, builder.translationConfiguration, builder.rotationConfiguration, builder.weightFunction);
    }

    @Override
    public @NotNull P compute(FunctionInterpolationContext context) {
        if(!context.dataContainer().getJointSkeleton().containsJoint(this.joint)){
            throw new IllegalArgumentException("Cannot run joint transformer function on joint " + this.joint + ", for it is not present within the skeleton.");
        }

        P pose = this.input.compute(context);
        float weight = this.weightFunction.apply(context);

        JointTransform jointTransform = pose.getJointTransform(this.joint);
        this.transformJoint(jointTransform, context, this.translationConfiguration, JointTransform::translate);
        this.transformJoint(jointTransform, context, this.rotationConfiguration, JointTransform::rotate);

        if(weight != 0){
            if(weight == 1){
                pose.setJointTransform(this.joint, jointTransform);
            } else {
                pose.setJointTransform(this.joint, pose.getJointTransform(this.joint).interpolated(jointTransform, weight));
            }
        }
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
        return new JointTransformerFunction<>(this.input.wrapUnique(), this.joint, this.translationConfiguration, this.rotationConfiguration, this.weightFunction);
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
        private Function<FunctionInterpolationContext, Float> weightFunction;

        private Builder(PoseFunction<P> poseFunction, String joint){
            this.joint = joint;
            this.input = poseFunction;
            this.translationConfiguration = TransformChannelConfiguration.of((context) -> new Vector3f(0), JointTransform.TransformType.IGNORE, JointTransform.TransformSpace.LOCAL);
            this.rotationConfiguration = TransformChannelConfiguration.of((context) -> new Quaternionf().identity(), JointTransform.TransformType.IGNORE, JointTransform.TransformSpace.LOCAL);
            this.weightFunction = evaluationState -> 1f;
        }

        public Builder<P> setTranslation(Function<FunctionInterpolationContext, Vector3f> transformFunction, JointTransform.TransformType transformType, JointTransform.TransformSpace transformSpace){
            this.translationConfiguration = TransformChannelConfiguration.of(transformFunction, transformType, transformSpace);
            return this;
        }

        public Builder<P> setRotation(Function<FunctionInterpolationContext, Quaternionf> transformFunction, JointTransform.TransformType transformType, JointTransform.TransformSpace transformSpace){
            this.rotationConfiguration = TransformChannelConfiguration.of(transformFunction, transformType, transformSpace);
            return this;
        }

        public Builder<P> setWeight(Function<FunctionInterpolationContext, Float> weightFunction){
            this.weightFunction = weightFunction;
            return this;
        }

        public JointTransformerFunction<P> build(){
            return JointTransformerFunction.of(this);
        }
    }

    private record TransformChannelConfiguration<X>(Function<FunctionInterpolationContext, X> transformFunction, JointTransform.TransformType transformType, JointTransform.TransformSpace transformSpace){

        private static <X> TransformChannelConfiguration<X> of(Function<FunctionInterpolationContext, X> transformFunction, JointTransform.TransformType transformType, JointTransform.TransformSpace transformSpace){
            return new TransformChannelConfiguration<>(transformFunction, transformType, transformSpace);
        }
    }

    @FunctionalInterface
    private interface Transformer<X> {
        void transform(JointTransform jointTransform, X value, JointTransform.TransformSpace transformSpace, JointTransform.TransformType transformType);
    }
}
