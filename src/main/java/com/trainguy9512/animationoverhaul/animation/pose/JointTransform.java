package com.trainguy9512.animationoverhaul.animation.pose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.data.AnimationSequenceData;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import com.trainguy9512.animationoverhaul.util.time.Timeline;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import org.joml.*;

public class JointTransform {

    private Matrix4f transform;

    public static final JointTransform ZERO = JointTransform.of(PartPose.ZERO);

    private JointTransform(Matrix4f matrix4f) {
        this.transform = matrix4f;
    }

    public JointTransform(JointTransform other){
        this.transform = new Matrix4f(other.transform);
    }

    public static JointTransform of(PartPose partPose){
        return of(new Vector3f(partPose.x(), partPose.y(), partPose.z()), new Vector3f(partPose.xRot(), partPose.yRot(), partPose.zRot()), new Vector3f(partPose.xScale(), partPose.yScale(), partPose.zScale()));
    }

    public static JointTransform of(Vector3f translation, Vector3f rotationEuler, Vector3f scale){
        return of(translation, new Quaternionf().rotationXYZ(rotationEuler.x(), rotationEuler.y(), rotationEuler.z()), scale);
    }

    public static JointTransform of(Vector3f translation, Quaternionf rotation, Vector3f scale){
        return of(new Matrix4f().translate(translation).rotate(rotation).scale(scale));
    }

    public static JointTransform getJointTransformFromAnimationSequence(ResourceLocation resourceLocation, String joint, float time){
        if(AnimationSequenceData.INSTANCE.isValid(resourceLocation)){
            Timeline<JointTransform> timeline = AnimationSequenceData.INSTANCE.get(resourceLocation).getJointTimeline(joint);
            return new JointTransform(timeline.getValueAt(time));
        } else {
            return JointTransform.ZERO;
        }
    }

    public static JointTransform of(Matrix4f matrix4f){
        return new JointTransform(matrix4f);
    }

    public Matrix4f getTransform(){
        return this.transform;
    }

    public JointTransform setTransform(Matrix4f matrix4f){
        this.getTransform().set(matrix4f);
        return this;
    }

    public Vector3f getTranslation(){
        return this.getTransform().getTranslation(new Vector3f());
    }

    public void setTranslation(Vector3f translation){
        this.getTransform().setTranslation(translation);
    }

    public Quaternionf getRotation(){
        return this.getTransform().getNormalizedRotation(new Quaternionf());
    }

    public Vector3f getEulerRotationZYX(){
        return this.getTransform().getEulerAnglesZYX(new Vector3f());
    }

    public void setRotation(Quaternionf quaternionRotation){
        this.setRotation(quaternionRotation.getEulerAnglesXYZ(new Vector3f()));
    }

    public void setRotation(Vector3f eulerRotation){
        this.getTransform().setRotationXYZ(eulerRotation.x(), eulerRotation.y(), eulerRotation.z());
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

    public void transform(Matrix4f transform, AnimationPose.TransformSpace transformSpace){
        switch (transformSpace){
            case ENTITY, PARENT -> this.getTransform().mul(transform);
            case LOCAL -> this.getTransform().mulLocal(transform);
        }
    }

    public void translate(Vector3f translation, AnimationPose.TransformSpace transformSpace){
        if(translation.x() != 0 || translation.y() != 0 || translation.z() != 0){
            switch (transformSpace){
                case ENTITY, PARENT -> this.getTransform().translateLocal(translation);
                case LOCAL -> this.getTransform().translate(translation);
            }
        }
    }

    public void rotate(Quaternionf rotation, AnimationPose.TransformSpace transformSpace){
        switch (transformSpace){
            case ENTITY, PARENT -> this.setRotation(new Matrix4f(this.transform).getNormalizedRotation(new Quaternionf()).premul(rotation));
            case LOCAL -> this.getTransform().rotate(rotation);
        }
    }

    public void rotate(Vector3f rotationEuler, AnimationPose.TransformSpace transformSpace){
        this.rotate(new Quaternionf().rotationXYZ(rotationEuler.x(), rotationEuler.y(), rotationEuler.z()), transformSpace);
    }

    public void multiplyTransform(JointTransform jointTransform){
        this.translate(jointTransform.getTranslation(), AnimationPose.TransformSpace.ENTITY);
        this.rotate(jointTransform.getRotation(), AnimationPose.TransformSpace.ENTITY);
    }

    public void inverseMultiplyTransform(JointTransform jointTransform){
        this.translate(jointTransform.getTranslation().negate(), AnimationPose.TransformSpace.ENTITY);
        this.rotate(jointTransform.getRotation().invert(), AnimationPose.TransformSpace.ENTITY);
    }

    public void mirror(){
        Vector3f translation = this.getTransform().getTranslation(new Vector3f());
        translation.set(translation.x() * -1, translation.y(), translation.z());
        this.setTranslation(translation);

        Vector3f rotation = this.getEulerRotationZYX();
        this.setRotation(new Vector3f(rotation.x(), -rotation.y(), -rotation.z()));
    }

    public JointTransform blend(JointTransform jointTransform, float alpha, Easing easing){
        alpha = easing.ease(alpha);
        this.getTransform().lerp(jointTransform.getTransform(), alpha);
        return this;
    }

    public JointTransform blendLinear(JointTransform jointTransform, float alpha){
        return this.blend(jointTransform, alpha, Easing.Linear.of());
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

    public void translatePoseStackInverse(PoseStack poseStack){
        Vector3f translation = this.getTranslation().negate();
        poseStack.translate(translation.x() / 16.0F, (translation.y() / 16.0F), (translation.z() / 16.0F));
    }

    public void rotatePoseStackInverse(PoseStack poseStack){
        poseStack.mulPose(this.getRotation());
        /*
        if (this.xRot != 0.0F) {
            poseStack.mulPose(Axis.XP.rotation(-this.xRot));
        }

        if (this.yRot != 0.0F) {
            poseStack.mulPose(Axis.YP.rotation(-this.yRot));
        }

        if (this.zRot != 0.0F) {
            poseStack.mulPose(Axis.ZP.rotation(-this.zRot));
        }

         */
    }

    public void transformPoseStack(PoseStack poseStack, float transformMultiplier){
        Matrix4f matrix4f = new Matrix4f(this.getTransform());
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
}
