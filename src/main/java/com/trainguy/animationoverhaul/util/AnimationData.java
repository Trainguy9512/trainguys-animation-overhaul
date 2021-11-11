package com.trainguy.animationoverhaul.util;

import com.trainguy.animationoverhaul.util.timeline.ChannelTimeline;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class AnimationData {

    // Entity type entries -> Specific animation entries -> Part entries -> Timeline
    private Map<String, Map<String, TimelineGroup>> animationEntries;

    public AnimationData(){
    }

    public AnimationData put(String identifier){
        EntityType.byString("BEE");
        return this;
    }

    public static class TimelineGroup {

        private Map<String, ChannelTimeline<Float>> partTimelines;


        public TimelineGroup(){
        }

        public ChannelTimeline<Float> getPartTimeline(String partName){
            return partTimelines.getOrDefault(partName, null);
        }
    }
}
