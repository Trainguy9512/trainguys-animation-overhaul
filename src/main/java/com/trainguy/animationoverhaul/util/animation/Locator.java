package com.trainguy.animationoverhaul.util.animation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
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

    public void translateAndRotate(PoseStack poseStack) {
        poseStack.translate((double)(this.x / 16.0F), (double)(this.y / 16.0F), (double)(this.z / 16.0F));
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

    public void bakeToModelPart(ModelPart modelPart){
        modelPart.x = this.x;
        modelPart.y = this.y;
        modelPart.z = this.z;
        modelPart.xRot = this.xRot;
        modelPart.yRot = this.yRot;
        modelPart.zRot = this.zRot;
    }

    public String getIdentifier(){
        return this.identifier;
    }

}
