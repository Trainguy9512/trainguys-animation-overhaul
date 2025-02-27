package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;
import com.trainguy9512.animationoverhaul.util.Timeline;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class AnimationSequenceData {

    public static AnimationSequenceData INSTANCE = new AnimationSequenceData();

    public static String FIRST_PERSON_PLAYER_KEY = "player/first_person/";

    //TODO: Set this as a class, (2025 update: ???)

    // Entity type entries -> Specific animation entries -> Part entries -> Timeline
    private final HashMap<ResourceLocation, AnimationSequence> animationSequences;

    public AnimationSequenceData(){
        this.animationSequences = Maps.newHashMap();
    }

    public void put(ResourceLocation resourceLocation, AnimationSequence animationSequence){
        animationSequences.put(resourceLocation, animationSequence);
    }

    public AnimationSequence getOrThrow(ResourceLocation resourceLocation){
        if(animationSequences.containsKey(resourceLocation)){
            return animationSequences.get(resourceLocation);
        } else {
            throw new IllegalArgumentException("Tried to access animation sequence from resource location " + resourceLocation + ", but it was not found in the loaded data.");
        }
    }

    public static ResourceLocation getNativeResourceLocation(String path){
        return ResourceLocation.fromNamespaceAndPath(AnimationOverhaulMain.MOD_ID, path);
    }

    public static ResourceLocation getNativeResourceLocation(String key, String path){
        return ResourceLocation.fromNamespaceAndPath(AnimationOverhaulMain.MOD_ID, key.concat(path));
    }

    public AnimationSequence getOrThrow(String namespace, String path){
        return getOrThrow(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    public void clearAndReplace(AnimationSequenceData newAnimationData){
        this.animationSequences.clear();
        for(ResourceLocation resourceLocation : newAnimationData.getHashMap().keySet()){
            this.put(resourceLocation, newAnimationData.getOrThrow(resourceLocation));
        }
    }

    public HashMap<ResourceLocation, AnimationSequence> getHashMap(){
        return animationSequences;
    }

    public record AnimationSequence(Map<String, Timeline<JointTransform>> jointTimelines, float frameLength) {

        public AnimationSequence(Builder builder){
            this(builder.jointTimelines, builder.frameLength);
        }

        public static AnimationSequence.Builder builder(float frameLength){
            return new AnimationSequence.Builder(frameLength);
        }

        public Timeline<JointTransform> getJointTransformTimeline(String joint){
            return this.jointTimelines.getOrDefault(joint, Timeline.ofJointTransform(0));
        }

        public static class Builder{
            private final Map<String, Timeline<JointTransform>> jointTimelines;
            private final float frameLength;

            protected Builder(float frameLength){
                this.jointTimelines = Maps.newHashMap();
                this.frameLength = frameLength;
            }

            public void addTimelineForJoint(String jointName, Timeline<JointTransform> timeline){
                this.jointTimelines.put(jointName, timeline);
            }

            public AnimationSequence build(){
                return new AnimationSequence(this);
            }

        }
    }
}
