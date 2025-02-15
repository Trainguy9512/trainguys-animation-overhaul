package com.trainguy9512.animationoverhaul.animation.joint;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.data.AnimationSequenceData;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import com.trainguy9512.animationoverhaul.util.time.Timeline;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import org.joml.*;

public final class JointTransform {

    private final Matrix4f transform;

    public static final JointTransform ZERO = JointTransform.ofPartPose(PartPose.ZERO);

    private JointTransform(Matrix4f transform) {
        this.transform = transform;
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

    public static JointTransform ofAnimationSequenceJoint(ResourceLocation animationSequence, String jointIdentifier, float timePercentage){
        if(AnimationSequenceData.INSTANCE.isValid(animationSequence)){
            Timeline<JointTransform> timeline = AnimationSequenceData.INSTANCE.get(animationSequence).getJointTimeline(jointIdentifier);
            return timeline.getValueAtPercentage(timePercentage);
        } else {
            return JointTransform.ZERO;
        }
    }

    public static JointTransform of(Matrix4f matrix4f){
        return new JointTransform(matrix4f);
    }

    /*



    GETTER



    public Matrix4f getTransform(){
        return new Matrix4f(this.transform);
    }
     */
    public Vector3f getTranslation(){
        return this.transform.getTranslation(new Vector3f());
    }

    public Quaternionf getRotation(){
        return this.transform.getNormalizedRotation(new Quaternionf());
    }

    public Vector3f getEulerRotationZYX(){
        return this.transform.getEulerAnglesZYX(new Vector3f());
    }

    public JointTransform withTranslation(Vector3f translation){
        return JointTransform.of(new Matrix4f(this.transform).setTranslation(translation));
    }

    public JointTransform withRotation(Quaternionf quaternionRotation){
        return this.withRotation(quaternionRotation.getEulerAnglesXYZ(new Vector3f()));
    }

    public JointTransform withRotation(Vector3f eulerRotation){
        return JointTransform.of(new Matrix4f(this.transform).setRotationXYZ(eulerRotation.x(), eulerRotation.y(), eulerRotation.z()));
    }

    public PartPose asPartPose(){
        Vector3f rotation = this.getEulerRotationZYX();
        Vector3f translation = this.getTranslation();
        return PartPose.offsetAndRotation(
                translation.x(),
                translation.y(),
                translation.z(),
                rotation.x(),
                rotation.y(),
                rotation.z()
        );
    }

    public JointTransform translated(Vector3f translation, TransformSpace transformSpace){
        if(translation.x() != 0 || translation.y() != 0 || translation.z() != 0){
            return switch (transformSpace){
                case ENTITY, PARENT -> of(this.transform.translateLocal(translation, new Matrix4f()));
                case LOCAL -> of(this.transform.translate(translation, new Matrix4f()));
            };
        }
        return this;
    }

    public JointTransform rotated(Quaternionf rotation, TransformSpace transformSpace){
        return switch (transformSpace){
            case ENTITY, PARENT -> this.withRotation(this.transform.getNormalizedRotation(new Quaternionf()).premul(rotation));
            case LOCAL -> JointTransform.of(this.transform.rotate(rotation, new Matrix4f()));
        };
    }

    public JointTransform rotated(Vector3f rotationEuler, TransformSpace transformSpace){
        return this.rotated(new Quaternionf().rotationXYZ(rotationEuler.x(), rotationEuler.y(), rotationEuler.z()), transformSpace);
    }

    public JointTransform multipliedBy(Matrix4f transform, TransformSpace transformSpace){
        return switch (transformSpace){
            case ENTITY, PARENT -> JointTransform.of(this.transform.mul(transform, new Matrix4f()));
            case LOCAL -> JointTransform.of(this.transform.mulLocal(transform, new Matrix4f()));
        };
    }

    //TODO: Why does this use translated and rotated?
    public JointTransform multipliedBy(JointTransform jointTransform){
        this.translated(jointTransform.getTranslation(), TransformSpace.ENTITY);
        this.rotated(jointTransform.getRotation(), TransformSpace.ENTITY);
        return this;
    }

    public JointTransform inverseMultipliedBy(JointTransform jointTransform){
        this.translated(jointTransform.getTranslation().negate(), TransformSpace.ENTITY);
        this.rotated(jointTransform.getRotation().invert(), TransformSpace.ENTITY);
        return this;
    }

    public JointTransform mirrored(){
        Vector3f mirroredTranslation = this.getTranslation().mul(-1, 1, 1);
        Vector3f mirroredRotation = this.getEulerRotationZYX().mul(1, -1, -1);
        return this.withTranslation(mirroredTranslation).withRotation(mirroredRotation);
    }

    public JointTransform interpolated(JointTransform other, float alpha){
        return JointTransform.of(this.transform.lerp(other.transform, alpha, new Matrix4f()));
    }

    public void translateAndRotatePoseStack(PoseStack poseStack){
        translatePoseStack(poseStack);
        rotatePoseStack(poseStack);
    }

    public void translatePoseStack(PoseStack poseStack){
        Vector3f translation = this.getTranslation();
        poseStack.translate(translation.x() / 16.0F, (translation.y() / 16.0F), (translation.z() / 16.0F));
    }

    public void rotatePoseStack(PoseStack poseStack){
        Vector3f rotation = this.getEulerRotationZYX();
        poseStack.mulPose(new Quaternionf().rotationZYX(rotation.z(), rotation.y(), rotation.x()));
    }

    public void inverseTranslatePoseStack(PoseStack poseStack){
        Vector3f translation = this.getTranslation().negate();
        poseStack.translate(translation.x() / 16.0F, (translation.y() / 16.0F), (translation.z() / 16.0F));
    }

    public void inverseRotatePoseStack(PoseStack poseStack){
        poseStack.mulPose(this.getRotation());
    }

    public void transformPoseStack(PoseStack poseStack, float transformMultiplier){
        Matrix4f matrix4f = new Matrix4f(this.transform);
        poseStack.mulPose(matrix4f.setTranslation(this.getTranslation().div(new Vector3f(transformMultiplier))));
    }

    public void transformPoseStack(PoseStack poseStack){
        this.transformPoseStack(poseStack, 1F);
    }

    public void transformModelPart(ModelPart modelPart){
        Vector3f translation = this.getTranslation();
        Vector3f rotation = this.getEulerRotationZYX();
        modelPart.setPos(translation.x(), translation.y(), translation.z());
        modelPart.setRotation(rotation.x(), rotation.y(), rotation.z());
    }

    public enum TransformSpace {
        ENTITY,
        PARENT,
        LOCAL
    }
}
