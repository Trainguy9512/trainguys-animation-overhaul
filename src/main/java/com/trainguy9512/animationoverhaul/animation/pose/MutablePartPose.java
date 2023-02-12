package com.trainguy9512.animationoverhaul.animation.pose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import org.joml.Quaternionf;

public class MutablePartPose {
    public float x = 0;
    public float y = 0;
    public float z = 0;
    public float xRot = 0;
    public float yRot = 0;
    public float zRot = 0;


    public static final MutablePartPose ZERO = fromPartPose(PartPose.ZERO);

    private MutablePartPose(float x, float y, float z, float xRot, float yRot, float zRot){
        this.x = x;
        this.y = y;
        this.z = z;
        this.xRot = xRot;
        this.yRot = yRot;
        this.zRot = zRot;
    }

    public static MutablePartPose fromTranslation(float x, float y, float z){
        return fromTranslationAndRotation(x, y, z, 0, 0, 0);
    }

    public static MutablePartPose fromRotation(float xRot, float yRot, float zRot){
        return fromTranslationAndRotation(0, 0, 0, xRot, yRot, zRot);
    }

    public static MutablePartPose fromTranslationAndRotation(float x, float y, float z, float xRot, float yRot, float zRot){
        return new MutablePartPose(x, y, z, xRot, yRot, zRot);
    }

    public PartPose asPartPose(){
        return PartPose.offsetAndRotation(
                this.x,
                this.y,
                this.z,
                this.xRot,
                this.yRot,
                this.zRot
        );
    }

    public static MutablePartPose add(MutablePartPose partPoseA, MutablePartPose partPoseB){
        return fromTranslationAndRotation(
                partPoseA.x + partPoseB.x,
                partPoseA.y + partPoseB.y,
                partPoseA.z + partPoseB.z,
                partPoseA.xRot + partPoseB.xRot,
                partPoseA.yRot + partPoseB.yRot,
                partPoseA.zRot + partPoseB.zRot
        );
    }

    public static MutablePartPose subtract(MutablePartPose partPoseA, MutablePartPose partPoseB){
        return fromTranslationAndRotation(
                partPoseA.x - partPoseB.x,
                partPoseA.y - partPoseB.y,
                partPoseA.z - partPoseB.z,
                partPoseA.xRot - partPoseB.xRot,
                partPoseA.yRot - partPoseB.yRot,
                partPoseA.zRot - partPoseB.zRot
        );
    }

    public static MutablePartPose fromPartPose(PartPose partPose){
        return fromTranslationAndRotation(
                partPose.x,
                partPose.y,
                partPose.z,
                partPose.xRot,
                partPose.yRot,
                partPose.zRot
        );
    }

    public MutablePartPose getCopy(){
        return MutablePartPose.fromPartPose(this.asPartPose());
    }

    public void translatePoseStack(PoseStack poseStack){
        poseStack.translate((this.x / 16.0F), (this.y / 16.0F), (this.z / 16.0F));
    }

    public void rotatePoseStack(PoseStack poseStack){
        if (this.zRot != 0.0F) {
            poseStack.mulPose(Axis.ZP.rotation(this.zRot));
        }

        if (this.yRot != 0.0F) {
            poseStack.mulPose(Axis.YP.rotation(this.yRot));
        }

        if (this.xRot != 0.0F) {
            poseStack.mulPose(Axis.XP.rotation(this.xRot));
        }
    }

    public void translateAndRotatePoseStack(PoseStack poseStack){
        translatePoseStack(poseStack);
        rotatePoseStack(poseStack);
    }

    public void translatePoseStackInverse(PoseStack poseStack){
        poseStack.translate((this.x / -16.0F), (this.y / -16.0F), (this.z / -16.0F));
    }

    public void rotatePoseStackInverse(PoseStack poseStack){
        if (this.xRot != 0.0F) {
            poseStack.mulPose(Axis.XP.rotation(-this.xRot));
        }

        if (this.yRot != 0.0F) {
            poseStack.mulPose(Axis.YP.rotation(-this.yRot));
        }

        if (this.zRot != 0.0F) {
            poseStack.mulPose(Axis.ZP.rotation(-this.zRot));
        }
    }

    public void transformPoseStack(PoseStack poseStack, float transformMultiplier){
        poseStack.translate(this.x / transformMultiplier, this.y / transformMultiplier, this.z / transformMultiplier);
        if (this.xRot != 0.0f || this.yRot != 0.0f || this.zRot != 0.0f) {
            poseStack.mulPose(new Quaternionf().rotationZYX(this.zRot, this.yRot, this.xRot));
        }
    }

    public void transformPoseStack(PoseStack poseStack){
        this.transformPoseStack(poseStack, 16F);
    }

    public void transformModelPart(ModelPart modelPart){
        modelPart.setPos(this.x, this.y, this.z);
        modelPart.setRotation(this.xRot, this.yRot, this.zRot);
    }
}
