package com.trainguy9512.locomotion.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.locomotion.LocomotionMain;
import com.trainguy9512.locomotion.util.Timeline;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public class AnimationSequenceData {

    public static AnimationSequenceData INSTANCE = new AnimationSequenceData();

    public static String FIRST_PERSON_PLAYER_PATH = "sequences/player/first_person/";

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
        return getNativeResourceLocation(LocomotionMain.MOD_ID, path);
    }

    public static ResourceLocation getNativeResourceLocation(String path, String name){
        return ResourceLocation.fromNamespaceAndPath(LocomotionMain.MOD_ID, path.concat(name).concat(".json"));
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

    public record AnimationSequence(
            Map<String, Timeline<Vector3f>> translationTimelines,
            Map<String, Timeline<Quaternionf>> rotationTimelines,
            Map<String, Timeline<Vector3f>> scaleTimelines,
            Map<String, Timeline<Boolean>> visibilityTimelines,
            float length
    ) {

        public AnimationSequence(Builder builder){
            this(builder.translationTimelines, builder.rotationTimelines, builder.scaleTimelines, builder.visibilityTimelines, builder.length);
        }

        public static AnimationSequence.Builder builder(float frameLength){
            return new AnimationSequence.Builder(frameLength);
        }

        public static class Builder{
            private final Map<String, Timeline<Vector3f>> translationTimelines;
            private final Map<String, Timeline<Quaternionf>> rotationTimelines;
            private final Map<String, Timeline<Vector3f>> scaleTimelines;
            private final Map<String, Timeline<Boolean>> visibilityTimelines;
            private final float length;

            protected Builder(float length){
                this.translationTimelines = Maps.newHashMap();
                this.rotationTimelines = Maps.newHashMap();
                this.scaleTimelines = Maps.newHashMap();
                this.visibilityTimelines = Maps.newHashMap();
                this.length = length;
            }

            public void putJointTranslationTimeline(String jointName, Timeline<Vector3f> timeline){
                this.translationTimelines.put(jointName, timeline);
            }

            public void putJointRotationTimeline(String jointName, Timeline<Quaternionf> timeline){
                this.rotationTimelines.put(jointName, timeline);
            }

            public void putJointScaleTimeline(String jointName, Timeline<Vector3f> timeline){
                this.scaleTimelines.put(jointName, timeline);
            }

            public void putJointVisibilityTimeline(String jointName, Timeline<Boolean> timeline){
                this.visibilityTimelines.put(jointName, timeline);
            }

            public AnimationSequence build(){
                return new AnimationSequence(this);
            }

        }
    }
}
