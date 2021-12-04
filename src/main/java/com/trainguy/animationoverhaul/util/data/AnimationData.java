package com.trainguy.animationoverhaul.util.data;

import com.google.common.collect.Maps;
import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.util.time.ChannelTimeline;

import java.util.Map;

public class AnimationData {

    public static AnimationData loadedData = new AnimationData();

    //TODO: Set this as a class,

    // Entity type entries -> Specific animation entries -> Part entries -> Timeline
    private final Map<String, Map<String, TimelineGroup>> animationEntries = Maps.newHashMap();

    public AnimationData(){
    }

    public void put(String entityKey, String animationKey, TimelineGroup timelineGroup){
        Map<String, TimelineGroup> individualAnimationMap;
        if(animationEntries.containsKey(entityKey)){
            individualAnimationMap = animationEntries.get(entityKey);
        } else {
            individualAnimationMap = Maps.newHashMap();
        }
        individualAnimationMap.put(animationKey, timelineGroup);
        animationEntries.put(entityKey, individualAnimationMap);
    }

    public TimelineGroup get(String entityKey, String animationKey){
        if(animationEntries.containsKey(entityKey)){
            if(animationEntries.get(entityKey).containsKey(animationKey)){
                return animationEntries.get(entityKey).get(animationKey);
            } else {
                AnimationOverhaul.LOGGER.error("Animation key {} for entity key {} not found within loaded animation data! Valid entries include {}", animationKey, entityKey, animationEntries.get(entityKey).keySet());
                return null;
            }
        } else {
            AnimationOverhaul.LOGGER.error("Entity key {} not found within loaded animation data!", entityKey);
            return null;
        }
    }

    public void clearAndReplace(AnimationData newAnimationData){
        this.animationEntries.clear();
        for(String entityKey : newAnimationData.getHashMap().keySet()){
            for(String animationKey : newAnimationData.getHashMap().get(entityKey).keySet()){
                this.put(entityKey, animationKey, newAnimationData.getHashMap().get(entityKey).get(animationKey));
            }
        }
    }

    public Map<String, Map<String, TimelineGroup>> getHashMap(){
        return animationEntries;
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
