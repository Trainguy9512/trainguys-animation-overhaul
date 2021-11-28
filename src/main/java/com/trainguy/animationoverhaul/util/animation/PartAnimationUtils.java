package com.trainguy.animationoverhaul.util.animation;

import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.data.TransformChannel;
import com.trainguy.animationoverhaul.util.math.RotationMatrix;
import com.trainguy.animationoverhaul.util.time.ChannelTimeline;
import net.minecraft.client.model.geom.ModelPart;

import java.util.HashMap;
import java.util.List;

@Deprecated
public class PartAnimationUtils {

    @Deprecated
    public static void animateMultiplePartsAdditive(List<ModelPart> partList, AnimationData.TimelineGroup timelineGroup, HashMap<ModelPart, String[]> modelPartStringDictionary, float time, float weight, boolean mirrored){
        /*
        for(ModelPart modelPart : partList){
            if(modelPartStringDictionary.containsKey(modelPart) && weight > 0){
                int mirroredPartKey = mirrored ? 1 : 0;
                String partKey = modelPartStringDictionary.get(modelPart)[mirroredPartKey];
                if(timelineGroup.containsPart(partKey)){
                    ChannelTimeline<Float> channelTimeline = timelineGroup.getPartTimeline(partKey);
                    animatePartAdditive(modelPart, channelTimeline, time, weight, mirrored);
                } else {
                    AnimationOverhaul.LOGGER.error("Timeline group does not contain part {}", partKey);
                }
            }
        }
        */
    }

    public static void animatePartAdditive(ModelPart modelPart, ChannelTimeline<Float> channelTimeline, float time, float weight, boolean mirrored){
        int mirrorMultiplier = mirrored ? -1 : 1;
        modelPart.x += channelTimeline.getValueAt(TransformChannel.x, time) * weight * mirrorMultiplier;
        modelPart.y += channelTimeline.getValueAt(TransformChannel.y, time) * weight;
        modelPart.z += channelTimeline.getValueAt(TransformChannel.z, time) * weight;
        rotateModelWorldSpace(modelPart, channelTimeline.getValueAt(TransformChannel.xRot, time) * weight, channelTimeline.getValueAt(TransformChannel.yRot, time) * weight * mirrorMultiplier, channelTimeline.getValueAt(TransformChannel.zRot, time) * weight * mirrorMultiplier);
    }

    public static void rotateModelWorldSpace(ModelPart modelPart, float x, float y, float z){
        Vector3f baseRotation = new Vector3f(modelPart.xRot, modelPart.yRot, modelPart.zRot);
        Vector3f multRotation = new Vector3f(x, y, z);

        RotationMatrix baseRotationMatrix = RotationMatrix.fromEulerAngles(baseRotation);
        //RotationMatrix inverseRotationMatrix = RotationMatrix.getInverse(baseRotationMatrix);
        RotationMatrix multRotationMatrix = RotationMatrix.fromEulerAngles(multRotation);
        //RotationMatrix copyRotationMatrix = RotationMatrix.fromEulerAngles(baseRotation);

        //baseRotationMatrix.mult(inverseRotationMatrix);
        baseRotationMatrix.mult(multRotationMatrix);
        //baseRotationMatrix.mult(copyRotationMatrix);

        Vector3f finalRotation = baseRotationMatrix.toEulerAngles();
        modelPart.xRot = finalRotation.x();
        modelPart.yRot = finalRotation.y();
        modelPart.zRot = finalRotation.z();
    }
}
