package com.trainguy9512.animationoverhaul.util.animation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.trainguy9512.animationoverhaul.util.math.RotationMatrix;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class Locator {

    public float translateX;
    public float translateY;
    public float translateZ;
    public float rotateX;
    public float rotateY;
    public float rotateZ;
    private String identifier;

    public Locator(String identifier){
        this.translateX = 0F;
        this.translateY = 0F;
        this.translateZ = 0F;
        this.rotateX = 0F;
        this.rotateY = 0F;
        this.rotateZ = 0F;
        this.identifier = identifier;
    }

    public Locator getCopy(){
        Locator locatorCopy = new Locator(this.identifier);
        locatorCopy.translateX = this.translateX;
        locatorCopy.translateY = this.translateY;
        locatorCopy.translateZ = this.translateZ;
        locatorCopy.rotateX = this.rotateX;
        locatorCopy.rotateY = this.rotateY;
        locatorCopy.rotateZ = this.rotateZ;
        return locatorCopy;
    }

    @Deprecated
    public void bakeToModelPart(ModelPart modelPart){
        modelPart.x = this.translateX;
        modelPart.y = this.translateY;
        modelPart.z = this.translateZ;
        modelPart.xRot = this.rotateX;
        modelPart.yRot = this.rotateY;
        modelPart.zRot = this.rotateZ;
    }

    public void additiveApplyPose(PartPose partPose){
        this.translateX += partPose.x;
        this.translateY += partPose.y;
        this.translateZ += partPose.z;
        this.rotateX += partPose.xRot;
        this.rotateY += partPose.yRot;
        this.rotateZ += partPose.zRot;
    }

    public PartPose getPartPose(){
        return PartPose.offsetAndRotation(this.translateX, this.translateY, this.translateZ, this.rotateX, this.rotateY, this.rotateZ);
    }

    public static Locator fromPartPose(PartPose partPose, String identifier){
        Locator locator = new Locator(identifier);
        locator.translateX = partPose.x;
        locator.translateY = partPose.y;
        locator.translateZ = partPose.z;
        locator.rotateX = partPose.xRot;
        locator.rotateY = partPose.yRot;
        locator.rotateZ = partPose.zRot;
        return locator;
    }

    public void rotateWorldSpace(float x, float y, float z){
        Vector3f baseRotation = new Vector3f(this.rotateX, this.rotateY, this.rotateZ);
        Vector3f multRotation = new Vector3f(x, y, z);

        RotationMatrix baseRotationMatrix = RotationMatrix.fromEulerAngles(baseRotation);
        RotationMatrix multRotationMatrix = RotationMatrix.fromEulerAngles(multRotation);

        baseRotationMatrix.mult(multRotationMatrix);

        Vector3f finalRotation = baseRotationMatrix.toEulerAngles();
        this.rotateX = finalRotation.x();
        this.rotateY = finalRotation.y();
        this.rotateZ = finalRotation.z();
    }

    public void reset(){
        this.translateX = 0;
        this.translateY = 0;
        this.translateZ = 0;
        this.rotateX = 0;
        this.rotateY = 0;
        this.rotateZ = 0;
    }

    public void weightedClearTransforms(float weight){
        this.translateX = Mth.lerp(weight, this.translateX, 0);
        this.translateY = Mth.lerp(weight, this.translateY, 0);
        this.translateZ = Mth.lerp(weight, this.translateZ, 0);
        this.rotateX = Mth.lerp(weight, this.rotateX, 0);
        this.rotateY = Mth.lerp(weight, this.rotateY, 0);
        this.rotateZ = Mth.lerp(weight, this.rotateZ, 0);
    }

    public String getIdentifier(){
        return this.identifier;
    }

}
