package com.trainguy9512.animationoverhaul.animation.pose;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.util.data.TransformChannel;
import com.trainguy9512.animationoverhaul.util.math.RotationMatrix;
import com.trainguy9512.animationoverhaul.util.time.ChannelTimeline;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.core.Rotations;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Rotation;
import org.joml.*;

import java.lang.Math;

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

    public MutablePartPose translate(Vector3f translation, boolean localSpace){
        if(translation.x() != 0 || translation.y() != 0 || translation.z() != 0){
            if(localSpace){
                Quaternionf quaternionf = new Quaternionf().rotationZYX(this.zRot, this.yRot, this.xRot);
                translation.rotate(quaternionf);
            }
            this.x += translation.x();
            this.y += translation.y();
            this.z += translation.z();
        }
        return this.getCopy();
    }

    public MutablePartPose rotate(Vector3f rotation, boolean localSpace){
        if(rotation.x() != 0 || rotation.y() != 0 || rotation.z() != 0){
            if(localSpace){
                this.xRot += rotation.x();
                this.yRot += rotation.y();
                this.zRot += rotation.z();
            } else {
                Vector3f baseRotation = new Vector3f(this.xRot, this.yRot, this.zRot);

                RotationMatrix baseRotationMatrix = RotationMatrix.fromEulerAngles(baseRotation);
                RotationMatrix multRotationMatrix = RotationMatrix.fromEulerAngles(rotation);

                baseRotationMatrix.mult(multRotationMatrix);

                Vector3f finalRotation = baseRotationMatrix.toEulerAngles();
                this.xRot = finalRotation.x();
                this.yRot = finalRotation.y();
                this.zRot = finalRotation.z();
            }
        }
        return this;
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

    public MutablePartPose blend(MutablePartPose partPose, float alpha, Easing easing){
        /*
        if(partPose.xRot - this.xRot > Mth.PI){
            partPose.rotate(new Vector3f(-Mth.TWO_PI, 0, 0), true);
        }
        if(partPose.xRot - this.xRot < -Mth.PI){
            partPose.rotate(new Vector3f(Mth.TWO_PI, 0, 0), true);
        }
        if(partPose.yRot - this.yRot > Mth.PI){
            partPose.rotate(new Vector3f(0, -Mth.TWO_PI, 0), true);
        }
        if(partPose.yRot - this.yRot < -Mth.PI){
            partPose.rotate(new Vector3f(0, Mth.TWO_PI, 0), true);
        }
        if(partPose.zRot - this.zRot > Mth.PI){
            partPose.rotate(new Vector3f(0, 0, -Mth.TWO_PI), true);
        }
        if(partPose.zRot - this.zRot < -Mth.PI){
            partPose.rotate(new Vector3f(0, 0, Mth.TWO_PI), true);
        }
        if(Math.abs(partPose.xRot - this.xRot) > Mth.PI || Math.abs(partPose.xRot - this.xRot) < -Mth.PI){
            AnimationOverhaulMain.LOGGER.warn("Snapping on the X axis of {} degrees", Math.toDegrees(Math.abs(partPose.xRot - this.xRot)));
        }
        if(Math.abs(partPose.yRot - this.yRot) > Mth.PI || Math.abs(partPose.yRot - this.yRot) < -Mth.PI){
            AnimationOverhaulMain.LOGGER.warn("Snapping on the Y axis of {} degrees", Math.toDegrees(Math.abs(partPose.yRot - this.yRot)));
        }
        if(Math.abs(partPose.zRot - this.zRot) > Mth.PI || Math.abs(partPose.zRot - this.zRot) < -Mth.PI){
            AnimationOverhaulMain.LOGGER.warn("Snapping on the Z axis of {} degrees", Math.toDegrees(Math.abs(partPose.zRot - this.zRot)));
        }

         */
        alpha = easing.ease(alpha);

        Quaternionf quaternionA = new Quaternionf().rotationXYZ(this.xRot, this.yRot, this.zRot);
        Quaternionf quaternionB = new Quaternionf().rotationXYZ(partPose.xRot, partPose.yRot, partPose.zRot);
        quaternionA.slerp(quaternionB, alpha);

        Vector3f vector3f = new Vector3f();
        quaternionA.getEulerAnglesXYZ(vector3f);

        this.x = Mth.lerp(alpha, this.x, partPose.x);
        this.y = Mth.lerp(alpha, this.y, partPose.y);
        this.z = Mth.lerp(alpha, this.z, partPose.z);
        this.xRot = vector3f.x();
        this.yRot = vector3f.y();
        this.zRot = vector3f.z();
        /*
        this.xRot = Mth.lerp(alpha, this.xRot, partPose.xRot);
        this.yRot = Mth.lerp(alpha, this.yRot, partPose.yRot);
        this.zRot = Mth.lerp(alpha, this.zRot, partPose.zRot);
         */
        return this;
    }

    public MutablePartPose blendLinear(MutablePartPose partPose, float alpha){
        return this.blend(partPose, alpha, Easing.Linear.of());
    }

    public static MutablePartPose getMutablePartPoseFromChannelTimeline(ResourceLocation resourceLocation, String partName, float time){
        ChannelTimeline channelTimeline = TimelineGroupData.INSTANCE.get(resourceLocation).getPartTimeline(partName);
        return new MutablePartPose(
                channelTimeline.getValueAt(TransformChannel.x, time),
                channelTimeline.getValueAt(TransformChannel.y, time),
                channelTimeline.getValueAt(TransformChannel.z, time),
                channelTimeline.getValueAt(TransformChannel.xRot, time),
                channelTimeline.getValueAt(TransformChannel.yRot, time),
                channelTimeline.getValueAt(TransformChannel.zRot, time)
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
