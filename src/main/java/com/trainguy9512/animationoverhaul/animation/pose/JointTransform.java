package com.trainguy9512.animationoverhaul.animation.pose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.animation.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.animation.data.TransformChannel;
import com.trainguy9512.animationoverhaul.util.time.ChannelTimeline;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import org.joml.*;

public class JointTransform {

    Matrix4f matrix4f;

    public static final JointTransform ZERO = JointTransform.of(PartPose.ZERO);

    private JointTransform(Matrix4f matrix4f) {
        this.matrix4f = matrix4f;
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

    public static JointTransform of(Matrix4f matrix4f){
        return new JointTransform(matrix4f);
    }

    public Matrix4f getTransform(){
        return new Matrix4f(this.matrix4f);
    }

    public JointTransform setTransform(Matrix4f matrix4f){
        this.matrix4f.set(matrix4f);
        return this;
    }

    public Vector3f getTranslation(){
        return this.matrix4f.getTranslation(new Vector3f());
    }

    public void setTranslation(Vector3f vector3f){
        this.matrix4f.setTranslation(vector3f);
    }

    public Quaternionf getRotation(){
        return this.matrix4f.getNormalizedRotation(new Quaternionf());
    }

    public Vector3f getEulerRotationZYX(){
        return this.matrix4f.getEulerAnglesZYX(new Vector3f());
    }

    public void setRotation(Quaternionf quaternionf){
        this.setEulerRotationXYZ(quaternionf.getEulerAnglesXYZ(new Vector3f()));
    }

    public void setEulerRotationXYZ(Vector3f vector3f){
        this.getTransform().setRotationXYZ(vector3f.x(), vector3f.y(), vector3f.z());
    }

    public PartPose asPartPose(){
        //Vector3f vector3f = this.rotation.getEulerAnglesXYZ(new Vector3f());
        Vector3f rotation = this.getEulerRotationZYX();
        return PartPose.offsetAndRotation(
                this.getTranslation().x(),
                this.getTranslation().y(),
                this.getTranslation().z(),
                rotation.x(),
                rotation.y(),
                rotation.z()
        );
    }

    public JointTransform transform(Matrix4f transform, AnimationPose.TransformSpace transformSpace){
        switch (transformSpace){
            case ENTITY, PARENT -> this.getTransform().mul(transform);
            case LOCAL -> this.getTransform().mulLocal(transform);
        }
        return this;
    }

    public JointTransform translate(Vector3f translation, AnimationPose.TransformSpace transformSpace){
        if(translation.x() != 0 || translation.y() != 0 || translation.z() != 0){
            if(transformSpace == AnimationPose.TransformSpace.LOCAL){
                //translation.rotateZ(this.getEulerRotationZYX().z());
                //translation.rotateY(this.getEulerRotationZYX().y());
                //translation.rotateX(this.getEulerRotationZYX().x());

                //translation.rotate(this.rotation);
                this.getTransform().translate(translation);
            } else {
                this.getTransform().translateLocal(translation);
            }
        }
        return this;
    }

    public JointTransform rotate(Quaternionf rotation, AnimationPose.TransformSpace transformSpace){
        if(transformSpace == AnimationPose.TransformSpace.LOCAL){
            this.getTransform().rotate(rotation);
        } else {
            //this.getTransformReference().rotateLocal(rotation);
            this.setRotation(this.getTransformCopy().getNormalizedRotation(new Quaternionf()).premul(rotation));
        }
        return this;
    }

    public JointTransform rotate(Vector3f rotation, AnimationPose.TransformSpace transformSpace){
        return this.rotate(new Quaternionf().rotationXYZ(rotation.x(), rotation.y(), rotation.z()), transformSpace);
    }

    public JointTransform multiplyPose(JointTransform partPose){
        this.translate(partPose.getTranslation(), AnimationPose.TransformSpace.ENTITY);
        this.rotate(partPose.getRotation(), AnimationPose.TransformSpace.ENTITY);
        return this;
    }

    public JointTransform inverseMultiplyPose(JointTransform partPose){
        this.translate(partPose.getTranslation().negate(), AnimationPose.TransformSpace.ENTITY);
        this.rotate(partPose.getRotation().invert(), AnimationPose.TransformSpace.ENTITY);
        return this;
    }

    public JointTransform getMirrored(){
        JointTransform jointPose = this.getCopy();

        Vector3f translation = this.getTransformCopy().getTranslation(new Vector3f());
        translation.set(translation.x() * -1, translation.y(), translation.z());
        this.setTranslation(translation);

        Vector3f rotation = jointPose.getEulerRotationZYX();
        jointPose.setEulerRotationXYZ(new Vector3f(rotation.x(), -rotation.y(), -rotation.z()));
        return jointPose;
    }

    public JointTransform blend(JointTransform partPose, float alpha, Easing easing){

        alpha = easing.ease(alpha);
        //Quaternionf quaternionA = new Quaternionf().rotationXYZ(this.xRot, this.yRot, this.zRot);
        //Quaternionf quaternionB = new Quaternionf().rotationXYZ(partPose.xRot, partPose.yRot, partPose.zRot);

        this.getTransform().lerp(partPose.getTransformCopy(), alpha);
        //this.rotation.slerp(partPose.rotation, alpha);
        //this.translation = this.translation.lerp(partPose.getTranslation(), alpha);
        //this.x = Mth.lerp(alpha, this.x, partPose.x);
        //this.y = Mth.lerp(alpha, this.y, partPose.y);
        //this.z = Mth.lerp(alpha, this.z, partPose.z);
        /*
        this.xRot = Mth.lerp(alpha, this.xRot, partPose.xRot);
        this.yRot = Mth.lerp(alpha, this.yRot, partPose.yRot);
        this.zRot = Mth.lerp(alpha, this.zRot, partPose.zRot);
         */
        return this;
    }

    public JointTransform blendLinear(JointTransform partPose, float alpha){
        return this.blend(partPose, alpha, Easing.Linear.of());
    }

    public static JointTransform getJointPoseFromChannelTimeline(ResourceLocation resourceLocation, String partName, float time){
        if(TimelineGroupData.INSTANCE.getHashMap().containsKey(resourceLocation)){
            ChannelTimeline channelTimeline = TimelineGroupData.INSTANCE.get(resourceLocation).getPartTimeline(partName);
            return new JointTransform(
                    channelTimeline.getValueAt(TransformChannel.x, time),
                    channelTimeline.getValueAt(TransformChannel.y, time),
                    channelTimeline.getValueAt(TransformChannel.z, time),
                    channelTimeline.getRotationAt(time)
            );
        } else {
            return JointTransform.ZERO;
        }
    }

    public static JointTransform fromPartPose(PartPose partPose){
        return fromTranslationAndRotation(
                partPose.x(),
                partPose.y(),
                partPose.z(),
                partPose.xRot(),
                partPose.yRot(),
                partPose.zRot()
        );
    }

    public JointTransform getCopy(){
        return new JointTransform(new Matrix4f(this.getTransformCopy()));
    }

    public void translatePoseStack(PoseStack poseStack){
        Vector3f translation = this.getTranslation();
        poseStack.translate(translation.x() / 16.0F, (translation.y() / 16.0F), (translation.z() / 16.0F));
    }

    public void rotatePoseStack(PoseStack poseStack){

        Vector3f vector3f = this.getEulerRotationZYX();
        poseStack.mulPose(new Quaternionf().rotationZYX(vector3f.z(), vector3f.y(), vector3f.x()));


        //poseStack.mulPose(this.rotation);


        /*
        if (this.zRot != 0.0F) {
            poseStack.mulPose(Axis.ZP.rotation(this.zRot));
        }

        if (this.yRot != 0.0F) {
            poseStack.mulPose(Axis.YP.rotation(this.yRot));
        }

        if (this.xRot != 0.0F) {
            poseStack.mulPose(Axis.XP.rotation(this.xRot));
        }

         */
    }

    public void translateAndRotatePoseStack(PoseStack poseStack){
        translatePoseStack(poseStack);
        rotatePoseStack(poseStack);
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
        poseStack.mulPose(this.getTransformCopy().setTranslation(this.getTranslation().div(new Vector3f(transformMultiplier))));
    }

    public void transformPoseStack(PoseStack poseStack){
        this.transformPoseStack(poseStack, 1F);
    }

    public void transformModelPart(ModelPart modelPart){


        modelPart.setPos(this.getTranslation().x(), this.getTranslation().y(), this.getTranslation().z());
        modelPart.setRotation(this.getEulerRotationZYX().x(), this.getEulerRotationZYX().y(), this.getEulerRotationZYX().z());
    }
}
