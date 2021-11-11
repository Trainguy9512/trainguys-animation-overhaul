package com.trainguy.animationoverhaul.util;

import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.util.timeline.ChannelTimeline;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.List;

public class PartAnimationUtils {

    public static void animateMultiplePartsAdditive(List<ModelPart> partList, AnimationData.TimelineGroup timelineGroup, HashMap<ModelPart, String[]> modelPartStringDictionary, float time, float weight, boolean mirrored){
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
    }

    public static void animatePartAdditive(ModelPart modelPart, ChannelTimeline<Float> channelTimeline, float time, float weight, boolean mirrored){
        int mirrorMultiplier = mirrored ? -1 : 1;
        modelPart.x += channelTimeline.getValueAt(TransformChannel.x, time) * weight * mirrorMultiplier;
        modelPart.y += channelTimeline.getValueAt(TransformChannel.y, time) * weight;
        modelPart.z += channelTimeline.getValueAt(TransformChannel.z, time) * weight;
        modelPart.xRot += channelTimeline.getValueAt(TransformChannel.xRot, time) * weight;
        modelPart.yRot += channelTimeline.getValueAt(TransformChannel.yRot, time) * weight * mirrorMultiplier;
        modelPart.zRot += channelTimeline.getValueAt(TransformChannel.zRot, time) * weight * mirrorMultiplier;
    }
}
