package com.trainguy.animationoverhaul.util.animation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.util.math.RotationMatrix;
import net.minecraft.client.model.geom.ModelPart;

public class Locator {

    public float x;
    public float y;
    public float z;
    public float xRot;
    public float yRot;
    public float zRot;
    private String identifier;

    public Locator(String identifier){
        this.x = 0F;
        this.y = 0F;
        this.z = 0F;
        this.xRot = 0F;
        this.yRot = 0F;
        this.zRot = 0F;
        this.identifier = identifier;
    }

    public void translatePoseStack(PoseStack poseStack){
        poseStack.translate((this.x / 16.0F), (this.y / 16.0F), (this.z / 16.0F));
    }

    public void rotatePoseStack(PoseStack poseStack){
        if (this.zRot != 0.0F) {
            poseStack.mulPose(Vector3f.ZP.rotation(this.zRot));
        }

        if (this.yRot != 0.0F) {
            poseStack.mulPose(Vector3f.YP.rotation(this.yRot));
        }

        if (this.xRot != 0.0F) {
            poseStack.mulPose(Vector3f.XP.rotation(this.xRot));
        }
    }

    public void translateAndRotatePoseStack(PoseStack poseStack){
        translatePoseStack(poseStack);
        rotatePoseStack(poseStack);
    }

    public void bakeToModelPart(ModelPart modelPart){
        modelPart.x = this.x;
        modelPart.y = this.y;
        modelPart.z = this.z;
        modelPart.xRot = this.xRot;
        modelPart.yRot = this.yRot;
        modelPart.zRot = this.zRot;
    }

    public void rotateWorldSpace(float x, float y, float z){
        Vector3f baseRotation = new Vector3f(this.xRot, this.yRot, this.zRot);
        Vector3f multRotation = new Vector3f(x, y, z);

        RotationMatrix baseRotationMatrix = RotationMatrix.fromEulerAngles(baseRotation);
        RotationMatrix multRotationMatrix = RotationMatrix.fromEulerAngles(multRotation);

        baseRotationMatrix.mult(multRotationMatrix);

        Vector3f finalRotation = baseRotationMatrix.toEulerAngles();
        this.xRot = finalRotation.x();
        this.yRot = finalRotation.y();
        this.zRot = finalRotation.z();
    }

    public String getIdentifier(){
        return this.identifier;
    }

}
