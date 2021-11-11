package com.trainguy.animationoverhaul.util;

import com.google.common.collect.Maps;
import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.util.timeline.ChannelTimeline;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnimationData {

    public static AnimationData loadedData;

    //TODO: Set this as a class,

    // Entity type entries -> Specific animation entries -> Part entries -> Timeline
    private Map<String, Map<String, TimelineGroup>> animationEntries = Maps.newHashMap();

    public AnimationData(){
    }

    public void put(String entityKey, String animationKey, TimelineGroup timelineGroup){
        Map<String, TimelineGroup> individualAnimationMap = Maps.newHashMap();
        individualAnimationMap.put(animationKey, timelineGroup);

        animationEntries.put(entityKey, individualAnimationMap);
    }

    public TimelineGroup get(String entityKey, String animationKey){
        if(animationEntries.containsKey(entityKey)){
            if(animationEntries.get(entityKey).containsKey(animationKey)){
                return animationEntries.get(entityKey).get(animationKey);
            } else {
                AnimationOverhaul.LOGGER.error("Animation key {} for entity key {} not found within loaded animation data!", animationKey, entityKey);
                return null;
            }
        } else {
            AnimationOverhaul.LOGGER.error("Entity key {} not found within loaded animation data!", entityKey);
            return null;
        }
    }

    public static class TimelineGroup {

        private Map<String, ChannelTimeline<Float>> partTimelines = Maps.newHashMap();
        private final float frameLength;

        public TimelineGroup(float timelineFrameLength){
            frameLength = timelineFrameLength;
        }

        public ChannelTimeline<Float> getPartTimeline(String partName){
            return partTimelines.get(partName);
        }

        public float getFrameLength(){
            return frameLength;
        }

        public boolean containsPart(String partName){
            return partTimelines.containsKey(partName);
        }

        public void addPartTimeline(String partName, ChannelTimeline<Float> partTimeline){
            partTimelines.put(partName, partTimeline);
        }
    }
}
