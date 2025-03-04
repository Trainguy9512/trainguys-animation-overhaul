package com.trainguy9512.locomotion.animation.joint;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.locomotion.animation.data.AnimationSequenceData;
import com.trainguy9512.locomotion.util.Timeline;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import org.joml.*;

public final class JointTransform {

    private final Matrix4f transform;

    public static final JointTransform ZERO = JointTransform.ofPartPose(PartPose.ZERO);

    private JointTransform(Matrix4f transform) {
        this.transform = transform;
    }

    public static JointTransform of(Matrix4f transform){
        return new JointTransform(new Matrix4f(transform));
    }

    public static JointTransform of(JointTransform jointTransform){
        return JointTransform.of(jointTransform.transform);
    }

    public static JointTransform ofPartPose(PartPose partPose){
        return ofTranslationRotationScaleEuler(new Vector3f(partPose.x(), partPose.y(), partPose.z()), new Vector3f(partPose.xRot(), partPose.yRot(), partPose.zRot()), new Vector3f(partPose.xScale(), partPose.yScale(), partPose.zScale()));
    }

    public static JointTransform ofTranslationRotationScaleEuler(Vector3f translation, Vector3f rotationEuler, Vector3f scale){
        return ofTranslationRotationScaleQuaternion(translation, new Quaternionf().rotationXYZ(rotationEuler.x(), rotationEuler.y(), rotationEuler.z()), scale);
    }

    public static JointTransform ofTranslationRotationScaleQuaternion(Vector3f translation, Quaternionf rotation, Vector3f scale){
        return of(new Matrix4f().translate(translation).rotate(rotation).scale(scale));
    }

    public static JointTransform ofAnimationSequenceJoint(ResourceLocation animationSequence, String jointIdentifier, float timeInTicks, boolean looping){
        Timeline<JointTransform> timeline = AnimationSequenceData.INSTANCE.getOrThrow(animationSequence).getJointTransformTimeline(jointIdentifier);
        return looping ? timeline.getValueAtFrameLooped(timeInTicks) : timeline.getValueAtFrame(timeInTicks);
    }

    public Matrix4f getTransform(){
        return new Matrix4f(this.transform);
    }

    public Vector3f getTranslation(){
        return this.transform.getTranslation(new Vector3f());
    }

    public Quaternionf getRotation(){
        return this.transform.getNormalizedRotation(new Quaternionf());
    }

    public Vector3f getEulerRotationZYX(){
        return this.transform.getEulerAnglesZYX(new Vector3f());
    }

    public Vector3f getScale(){
        return this.transform.getScale(new Vector3f());
    }

    public PartPose asPartPose(){
        Vector3f rotation = this.getEulerRotationZYX();
        Vector3f translation = this.getTranslation();
        return PartPose
                .offsetAndRotation(
                        translation.x(),
                        translation.y(),
                        translation.z(),
                        rotation.x(),
                        rotation.y(),
                        rotation.z()
                );
    }

    public void translate(Vector3f translation, TransformSpace transformSpace, TransformType transformType){
        switch (transformType){
            case ADD -> {
                if(translation.x() != 0 || translation.y() != 0 || translation.z() != 0){
                    switch (transformSpace){
                        case LOCAL -> this.transform.translate(translation);
                        case COMPONENT, PARENT -> this.transform.translateLocal(translation);
                    }
                }
            }
            case REPLACE -> this.transform.setTranslation(translation);
        }
    }

    public void rotate(Quaternionf rotation, TransformSpace transformSpace, TransformType transformType){
        switch (transformType){
            case ADD -> {
                switch (transformSpace){
                    //case COMPONENT, PARENT -> this.transform.rotation(this.transform.getNormalizedRotation(new Quaternionf()).premul(rotation));
                    case LOCAL -> this.transform.rotate(rotation);
                    case COMPONENT, PARENT -> {
                        Quaternionf currentRotation = this.transform.getUnnormalizedRotation(new Quaternionf());
                        rotation.mul(currentRotation, currentRotation);
                        this.transform.translationRotateScale(this.getTranslation(), currentRotation, this.getScale());
                    }
                }
            }
            case REPLACE -> this.transform.translationRotateScale(this.getTranslation(), rotation, this.getScale());
        }
    }

    public void scale(Vector3f scale, TransformSpace transformSpace, TransformType transformType){
        switch (transformType){
            case ADD -> {
                switch (transformSpace){
                    //case COMPONENT, PARENT -> this.transform.rotation(this.transform.getNormalizedRotation(new Quaternionf()).premul(rotation));
                    case LOCAL -> this.transform.scale(scale);
                    case COMPONENT, PARENT -> this.transform.scaleLocal(scale.x, scale.y, scale.z);

                }
            }
            case REPLACE -> {
                Matrix4f matrix4f = new Matrix4f().identity();
                matrix4f.translationRotateScale(this.getTranslation(), this.getRotation(), scale);

                this.transform.set(matrix4f);
                //this.transform.translationRotateScale(this.getTranslation(), this.getRotation(), scale);
            }
        }
    }

    public void rotate(Vector3f rotationEuler, TransformSpace transformSpace, TransformType transformType){
        this.rotate(new Quaternionf().rotationXYZ(rotationEuler.x(), rotationEuler.y(), rotationEuler.z()), transformSpace, transformType);
    }

    public void multiply(Matrix4f transform, TransformSpace transformSpace){
        switch (transformSpace){
            case COMPONENT, PARENT -> JointTransform.of(this.transform.mul(transform));
            case LOCAL -> JointTransform.of(this.transform.mulLocal(transform));
        }
    }

    //TODO: Why does this use translated and rotated?
    public void multiply(JointTransform jointTransform){
        this.transform.mul(jointTransform.transform);
        //this.translate(jointTransform.getTranslation(), TransformSpace.COMPONENT, TransformType.ADD);
        //this.rotate(jointTransform.getRotation(), TransformSpace.COMPONENT, TransformType.ADD);
    }

    public void inverseMultiply(JointTransform jointTransform){
        this.translate(jointTransform.getTranslation().negate(), TransformSpace.COMPONENT, TransformType.ADD);
        this.rotate(jointTransform.getRotation().invert(), TransformSpace.COMPONENT, TransformType.ADD);
    }

    public JointTransform mirrored(){
        Vector3f mirroredTranslation = this.getTranslation().mul(-1, 1, 1);
        Vector3f mirroredRotation = this.getEulerRotationZYX().mul(1, -1, -1);
        return JointTransform.ofTranslationRotationScaleEuler(mirroredTranslation, mirroredRotation, new Vector3f(1));
    }

    public JointTransform interpolated(JointTransform other, float weight){
        Vector3f translation = this.transform.getTranslation(new Vector3f());
        Quaternionf rotation = this.transform.getNormalizedRotation(new Quaternionf());
        Vector3f scale = this.transform.getScale(new Vector3f());

        Vector3f otherTranslation = other.transform.getTranslation(new Vector3f());
        Quaternionf otherRotation = other.transform.getNormalizedRotation(new Quaternionf());
        Vector3f otherScale = other.transform.getScale(new Vector3f());

        translation.lerp(otherTranslation, weight);
        rotation.slerp(otherRotation, weight);
        scale.lerp(otherScale, weight);


        return JointTransform.of(new Matrix4f().translationRotateScale(translation, rotation, scale));
    }

    public void transformPoseStack(PoseStack poseStack, float transformMultiplier){
        Matrix4f matrix4f = new Matrix4f(this.transform);
        poseStack.mulPose(matrix4f.setTranslation(this.getTranslation().div(new Vector3f(transformMultiplier))));

        //Vector3f translation = this.getTranslation();
        //poseStack.translate(translation.x() / transformMultiplier, (translation.y() / transformMultiplier), (translation.z() / transformMultiplier));

        //Quaternionf rotation = this.getRotation();
        //poseStack.mulPose(rotation);

        //Vector3f scale = this.getScale();
        //poseStack.scale(scale.x(), scale.y(), scale.z());
    }

    public void transformPoseStack(PoseStack poseStack){
        this.transformPoseStack(poseStack, 1F);
    }

    public enum TransformSpace {
        COMPONENT,
        PARENT,
        LOCAL
    }

    public enum TransformType {
        IGNORE,
        REPLACE,
        ADD
    }
}
