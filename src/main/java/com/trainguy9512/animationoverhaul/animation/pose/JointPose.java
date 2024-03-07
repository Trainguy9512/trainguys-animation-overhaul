package com.trainguy9512.animationoverhaul.animation.pose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.util.data.TransformChannel;
import com.trainguy9512.animationoverhaul.util.time.ChannelTimeline;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import org.joml.*;

public class JointPose {
    //public Vector3f translation;
    //public Quaternionf rotation;
    private Matrix4f matrix4f;
    private AnimationPose.PoseSpace poseSpace = AnimationPose.PoseSpace.LOCAL;
    /*
    public float xRot = 0;
    public float yRot = 0;
    public float zRot = 0;
     */


    public static final JointPose ZERO = new JointPose(fromPartPose(PartPose.ZERO));

    private JointPose(float x, float y, float z, float xRot, float yRot, float zRot){
        this(x, y, z, new Quaternionf().rotationXYZ(xRot, yRot, zRot));
        this.setEulerRotationXYZ(new Vector3f(xRot, yRot, zRot));
    }

    private JointPose(float x, float y, float z, Quaternionf rotation){
        this(new Vector3f(x, y, z), rotation, new Vector3f(1, 1, 1));
    }

    private JointPose(Vector3f translation, Quaternionf rotation, Vector3f scale){
        this(new Matrix4f().translate(translation).rotate(rotation).scale(scale));
    }

    private JointPose(Matrix4f matrix4f){
        this.matrix4f = new Matrix4f(matrix4f);
    }

    public JointPose(JointPose jointPose){
        this.matrix4f = jointPose.getTransformCopy();
        this.poseSpace = jointPose.getPoseSpace();
    }

    public static JointPose fromMatrix4f(Matrix4f matrix4f){
        return new JointPose(matrix4f);
    }

    public static JointPose fromTranslation(float x, float y, float z){
        return fromTranslationAndRotation(x, y, z, 0, 0, 0);
    }

    public static JointPose fromRotation(float xRot, float yRot, float zRot){
        return fromTranslationAndRotation(0, 0, 0, xRot, yRot, zRot);
    }

    public static JointPose fromTranslationAndRotation(float x, float y, float z, float xRot, float yRot, float zRot){
        return new JointPose(x, y, z, xRot, yRot, zRot);
    }

    public static JointPose fromTranslationAndRotation(float x, float y, float z, Quaternionf rotation){
        return new JointPose(x, y, z, rotation);
    }

    /*
    public static Quaternionf getQuaternionFromEuler(Vector3f eulerRotation){
        return new Quaternionf().rotationXYZ(eulerRotation.x(), eulerRotation.y(), eulerRotation.z());
    }

    public static Vector3f getEulerFromQuaternion(Quaternionf quaternionf){
        return quaternionf.getEulerAnglesZYX(new Vector3f());
    }

     */

    public AnimationPose.PoseSpace getPoseSpace(){
        return this.poseSpace;
    }

    public JointPose setPoseSpace(AnimationPose.PoseSpace poseSpace){
        this.poseSpace = poseSpace;
        return this;
    }

    public Matrix4f getTransformCopy(){
        return new Matrix4f(this.matrix4f);
    }

    public Matrix4f getTransformReference(){
        return this.matrix4f;
    }

    public JointPose setTransform(Matrix4f matrix4f){
        this.matrix4f.set(matrix4f);
        return this;
    }

    public Vector3f getTranslation(){
        return matrix4f.getTranslation(new Vector3f());
    }

    public JointPose setTranslation(Vector3f vector3f){
        this.matrix4f.setTranslation(vector3f);
        return this;
    }

    public Quaternionf getRotation(){
        return this.matrix4f.getNormalizedRotation(new Quaternionf());
    }

    public Vector3f getEulerRotationZYX(){
        return this.getTransformCopy().getEulerAnglesZYX(new Vector3f());
    }

    public JointPose setRotation(Quaternionf quaternionf){
        return setEulerRotationXYZ(quaternionf.getEulerAnglesXYZ(new Vector3f()));
    }

    public JointPose setEulerRotationXYZ(Vector3f vector3f){
        this.getTransformReference().setRotationXYZ(vector3f.x(), vector3f.y(), vector3f.z());
        return this;
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

    //TODO: Use TranslateLocal Matrix4F

    public JointPose transform(Matrix4f transform, AnimationPose.TransformSpace transformSpace){
        switch (transformSpace){
            case ENTITY, PARENT -> this.getTransformReference().mul(transform);
            case LOCAL -> this.getTransformReference().mulLocal(transform);
        }
        return this;
    }

    public JointPose translate(Vector3f translation, AnimationPose.TransformSpace transformSpace){
        if(translation.x() != 0 || translation.y() != 0 || translation.z() != 0){
            if(transformSpace == AnimationPose.TransformSpace.LOCAL){
                //translation.rotateZ(this.getEulerRotationZYX().z());
                //translation.rotateY(this.getEulerRotationZYX().y());
                //translation.rotateX(this.getEulerRotationZYX().x());

                //translation.rotate(this.rotation);
                this.getTransformReference().translate(translation);
            } else {
                this.getTransformReference().translateLocal(translation);
            }
        }
        return this;
    }

    public JointPose rotate(Quaternionf rotation, AnimationPose.TransformSpace transformSpace){
        if(transformSpace == AnimationPose.TransformSpace.LOCAL){
            this.getTransformReference().rotateLocal(rotation);
        } else {
            this.getTransformReference().rotate(rotation);
        }
        return this;
    }

    public JointPose rotate(Vector3f rotation, AnimationPose.TransformSpace transformSpace){
        return this.rotate(new Quaternionf().rotationXYZ(rotation.x(), rotation.y(), rotation.z()), transformSpace);
    }

    public JointPose multiplyPose(JointPose partPose){
        this.translate(partPose.getTranslation(), AnimationPose.TransformSpace.ENTITY);
        this.rotate(partPose.getRotation(), AnimationPose.TransformSpace.ENTITY);
        return this;
    }

    public JointPose inverseMultiplyPose(JointPose partPose){
        this.translate(partPose.getTranslation().negate(), AnimationPose.TransformSpace.ENTITY);
        this.rotate(partPose.getRotation().invert(), AnimationPose.TransformSpace.ENTITY);
        return this;
    }

    public JointPose getMirrored(){
        JointPose jointPose = this.getCopy();

        Vector3f translation = this.getTransformCopy().getTranslation(new Vector3f());
        translation.set(translation.x() * -1, translation.y(), translation.z());
        this.setTranslation(translation);

        Vector3f rotation = jointPose.getEulerRotationZYX();
        jointPose.setEulerRotationXYZ(new Vector3f(rotation.x(), -rotation.y(), -rotation.z()));
        return jointPose;
    }

    public JointPose blend(JointPose partPose, float alpha, Easing easing){

        alpha = easing.ease(alpha);
        //Quaternionf quaternionA = new Quaternionf().rotationXYZ(this.xRot, this.yRot, this.zRot);
        //Quaternionf quaternionB = new Quaternionf().rotationXYZ(partPose.xRot, partPose.yRot, partPose.zRot);

        this.getTransformReference().lerp(partPose.getTransformCopy(), alpha);
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

    public JointPose blendLinear(JointPose partPose, float alpha){
        return this.blend(partPose, alpha, Easing.Linear.of());
    }

    public static JointPose getJointPoseFromChannelTimeline(ResourceLocation resourceLocation, String partName, float time){
        if(TimelineGroupData.INSTANCE.getHashMap().containsKey(resourceLocation)){
            ChannelTimeline channelTimeline = TimelineGroupData.INSTANCE.get(resourceLocation).getPartTimeline(partName);
            return new JointPose(
                    channelTimeline.getValueAt(TransformChannel.x, time),
                    channelTimeline.getValueAt(TransformChannel.y, time),
                    channelTimeline.getValueAt(TransformChannel.z, time),
                    channelTimeline.getRotationAt(time)
            );
        } else {
            return JointPose.ZERO;
        }
    }

    public static JointPose fromPartPose(PartPose partPose){
        return fromTranslationAndRotation(
                partPose.x,
                partPose.y,
                partPose.z,
                partPose.xRot,
                partPose.yRot,
                partPose.zRot
        );
    }

    public JointPose getCopy(){
        return new JointPose(new Matrix4f(this.getTransformCopy()));
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
        poseStack.mulPoseMatrix(this.getTransformCopy());
        //poseStack.translate(this.translation.x / transformMultiplier, this.translation.y / transformMultiplier, this.translation.z / transformMultiplier);
        //this.rotatePoseStack(poseStack);

        //poseStack.mulPose(this.rotation);
        /*
        if (this.xRot != 0.0f || this.yRot != 0.0f || this.zRot != 0.0f) {
            poseStack.mulPose(new Quaternionf().rotationZYX(this.zRot, this.yRot, this.xRot));
        }

         */
    }

    public void transformPoseStack(PoseStack poseStack){
        this.transformPoseStack(poseStack, 16F);
    }

    public void transformModelPart(ModelPart modelPart){


        modelPart.setPos(this.getTranslation().x(), this.getTranslation().y(), this.getTranslation().z());
        modelPart.setRotation(this.getEulerRotationZYX().x(), this.getEulerRotationZYX().y(), this.getEulerRotationZYX().z());
    }
}
