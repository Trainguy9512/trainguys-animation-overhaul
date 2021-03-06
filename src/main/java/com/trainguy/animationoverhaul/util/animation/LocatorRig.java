package com.trainguy.animationoverhaul.util.animation;

import com.google.common.collect.Maps;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.util.data.AnimationData;
import com.trainguy.animationoverhaul.util.data.TransformChannel;
import com.trainguy.animationoverhaul.util.math.RotationMatrix;
import com.trainguy.animationoverhaul.util.time.ChannelTimeline;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LocatorRig {

    private HashMap<Locator, LocatorEntry> locatorEntryHashMap = Maps.newHashMap();

    public LocatorRig(){

    }

    public void addLocatorModelPart(Locator locator, Locator locatorMirrored, ModelPart modelPart){
        locatorEntryHashMap.putIfAbsent(locator, new LocatorEntry(locator, locatorMirrored, modelPart));
    }

    public void addLocatorModelPart(Locator locator, ModelPart modelPart){
        addLocatorModelPart(locator, locator, modelPart);
    }

    public void addLocator(Locator locator, Locator locatorMirrored){
        locatorEntryHashMap.putIfAbsent(locator, new LocatorEntry(locator, locatorMirrored));
    }

    public void addLocator(Locator locator){
        addLocator(locator, locator);
    }

    public Locator getLocator(String identifier, boolean mirrored){
        if(this.containsLocator(identifier)){
            LocatorEntry locatorEntry = getLocatorEntry(identifier);
            if(locatorEntry != null){
                return mirrored ? locatorEntry.getLocatorMirrored() : locatorEntry.getLocator();
            }
        }
        return new Locator("null");
    }

    private LocatorEntry getLocatorEntry(String identifier){
        for(Locator locator : locatorEntryHashMap.keySet()){
            if(Objects.equals(locator.getIdentifier(), identifier)){
                return locatorEntryHashMap.get(locator);
            }
        }
        return null;
    }

    public boolean containsLocator(String identifier){
        for(Locator locator : locatorEntryHashMap.keySet()){
            if(locator.getIdentifier().contains(identifier)){
                return true;
            }
        }
        return false;
    }

    public void bakeRig(){
        for(LocatorEntry locatorEntry : locatorEntryHashMap.values()){
            locatorEntry.bakeLocatorToModelPart();
        }
    }

    public void animateMultipleLocatorsAdditive(List<Locator> locators, AnimationData.TimelineGroup timelineGroup, float time, float weightRotation, float weightTranslation, boolean mirrored){
        for(Locator locator : locators){
            if(this.locatorEntryHashMap.containsKey(locator)){
                LocatorEntry locatorEntry = locatorEntryHashMap.get(locator);
                Locator locatorToUse = mirrored ? locatorEntry.getLocatorMirrored() : locatorEntry.getLocator();
                if(timelineGroup.containsPart(locatorToUse.getIdentifier())){
                    ChannelTimeline<Float> channelTimeline = timelineGroup.getPartTimeline(locatorToUse.getIdentifier());
                    animateLocatorAdditive(locator, channelTimeline, time, weightRotation, weightTranslation, mirrored);
                }
            }
        }
    }

    public void animateMultipleLocatorsAdditive(List<Locator> locators, AnimationData.TimelineGroup timelineGroup, float time, float weight, boolean mirrored){
        animateMultipleLocatorsAdditive(locators, timelineGroup, time, weight, weight, mirrored);
    }

    public void animateMultipleLocatorsAdditive(List<Locator> locators, AnimationData.TimelineGroup timelineGroup, float time, float weight){
        animateMultipleLocatorsAdditive(locators, timelineGroup, time, weight, weight, false);
    }

    public void animateLocatorAdditive(Locator locator, ChannelTimeline<Float> channelTimeline, float time, float weightRotation, float weightTranslation, boolean mirrored){
        int mirrorMultiplier = mirrored ? -1 : 1;
        locator.x += channelTimeline.getValueAt(TransformChannel.x, time) * weightTranslation * mirrorMultiplier;
        locator.y += channelTimeline.getValueAt(TransformChannel.y, time) * weightTranslation;
        locator.z += channelTimeline.getValueAt(TransformChannel.z, time) * weightTranslation;
        locator.rotateWorldSpace(channelTimeline.getValueAt(TransformChannel.xRot, time) * weightRotation, channelTimeline.getValueAt(TransformChannel.yRot, time) * weightRotation * mirrorMultiplier, channelTimeline.getValueAt(TransformChannel.zRot, time) * weightRotation * mirrorMultiplier);
    }

    public void weightedClearTransforms(List<Locator> locators, float weight){
        for(Locator locator : locators){
            if(this.locatorEntryHashMap.containsKey(locator)){
                locator.weightedClearTransforms(weight);
            }
        }
    }

    public void resetRig(){
        for(Locator locator : locatorEntryHashMap.keySet()){
            locator.reset();
        }
    }

    private static class LocatorEntry {
        private final Locator locator;
        private final Locator locatorMirrored;
        private final ModelPart modelPart;
        private final boolean usesModelPart;

        public LocatorEntry(Locator locator, Locator locatorMirrored, ModelPart modelPart){
            this.locator = locator;
            this.locatorMirrored = locatorMirrored;
            this.modelPart = modelPart;
            this.usesModelPart = true;
        }

        public LocatorEntry(Locator locator, Locator locatorMirrored){
            this.locator = locator;
            this.locatorMirrored = locatorMirrored;
            this.modelPart = null;
            this.usesModelPart = false;
        }

        public void bakeLocatorToModelPart(){
            if(usesModelPart && this.modelPart != null){
                this.locator.bakeToModelPart(this.modelPart);
            }
        }

        public boolean usesModelPart(){
            return this.usesModelPart;
        }

        public Locator getLocator(){
            return this.locator;
        }

        public Locator getLocatorMirrored(){
            return this.locatorMirrored;
        }
    }
}
