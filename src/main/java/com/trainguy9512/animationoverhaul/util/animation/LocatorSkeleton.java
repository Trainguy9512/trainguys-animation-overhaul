package com.trainguy9512.animationoverhaul.util.animation;

import com.google.common.collect.Maps;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LocatorSkeleton {

    private final HashMap<Locator, LocatorEntry> locatorHashMap = Maps.newHashMap();

    public LocatorSkeleton(){

    }

    public List<LocatorEntry> getLocatorEntries(){
        return locatorHashMap.values().stream().toList();
    }

    public PartPose getLocatorDefaultPose(Locator locator){
        return locatorHashMap.get(locator).defaultPose;
    }

    public void addLocatorModelPart(Locator locator, Locator locatorMirrored, String modelPartIdentifier, PartPose defaultPose){
        locatorHashMap.putIfAbsent(locator, new LocatorEntry(locator, locatorMirrored, modelPartIdentifier, defaultPose));
    }

    public void addLocatorModelPart(Locator locator, String modelPartIdentifier, PartPose defaultPose){
        addLocatorModelPart(locator, locator, modelPartIdentifier, defaultPose);
    }

    public void addLocatorModelPart(Locator locator, Locator locatorMirrored, String modelPartIdentifier){
        addLocatorModelPart(locator, locatorMirrored, modelPartIdentifier, PartPose.ZERO);
    }

    public void addLocatorModelPart(Locator locator, String modelPartIdentifier){
        addLocatorModelPart(locator, modelPartIdentifier, PartPose.ZERO);
    }

    public void addLocator(Locator locator, Locator locatorMirrored){
        locatorHashMap.putIfAbsent(locator, new LocatorEntry(locator, locatorMirrored, null, PartPose.ZERO));
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
        for(Locator locator : locatorHashMap.keySet()){
            if(Objects.equals(locator.getIdentifier(), identifier)){
                return locatorHashMap.get(locator);
            }
        }
        return null;
    }

    public boolean containsLocator(String identifier){
        for(Locator locator : locatorHashMap.keySet()){
            if(locator.getIdentifier().contains(identifier)){
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public void bakeRig(ModelPart rootModelPart){
        for(LocatorEntry locatorEntry : locatorHashMap.values()){
            if(locatorEntry.modelPartIdentifier != null){
                ModelPart finalModelPart = rootModelPart;
                for(String individualPartString : locatorEntry.modelPartIdentifier.split("\\.")){
                    finalModelPart = finalModelPart.getChild(individualPartString);
                }
                locatorEntry.bakeLocatorToModelPart(finalModelPart);
            }
        }
    }

    /*
    @Deprecated
    public void animateMultipleLocatorsAdditive(List<Locator> locators, TimelineGroupData.TimelineGroup timelineGroup, float time, float weightRotation, float weightTranslation, boolean mirrored){
        for(Locator locator : locators){
            if(this.locatorEntryHashMap.containsKey(locator)){
                LocatorEntry locatorEntry = locatorEntryHashMap.get(locator);
                Locator locatorToUse = mirrored ? locatorEntry.getLocatorMirrored() : locatorEntry.getLocator();
                if(timelineGroup != null){
                    if(timelineGroup.containsPart(locatorToUse.getIdentifier())){
                        ChannelTimeline channelTimeline = timelineGroup.getPartTimeline(locatorToUse.getIdentifier());
                        animateLocatorAdditive(locator, channelTimeline, time, weightRotation, weightTranslation, mirrored);
                    }
                }
            }
        }
    }

    @Deprecated
    public void animateMultipleLocatorsAdditive(List<Locator> locators, TimelineGroupData.TimelineGroup timelineGroup, float time, float weight, boolean mirrored){
        animateMultipleLocatorsAdditive(locators, timelineGroup, time, weight, weight, mirrored);
    }

    @Deprecated
    public void animateMultipleLocatorsAdditive(List<Locator> locators, TimelineGroupData.TimelineGroup timelineGroup, float time, float weight){
        animateMultipleLocatorsAdditive(locators, timelineGroup, time, weight, weight, false);
    }

    @Deprecated
    public void animateLocatorAdditive(Locator locator, ChannelTimeline channelTimeline, float time, float weightRotation, float weightTranslation, boolean mirrored){
        int mirrorMultiplier = mirrored ? -1 : 1;
        locator.translateX += channelTimeline.getValueAt(TransformChannel.x, time) * weightTranslation * mirrorMultiplier;
        locator.translateY += channelTimeline.getValueAt(TransformChannel.y, time) * weightTranslation;
        locator.translateZ += channelTimeline.getValueAt(TransformChannel.z, time) * weightTranslation;
        locator.rotateWorldSpace(channelTimeline.getValueAt(TransformChannel.xRot, time) * weightRotation, channelTimeline.getValueAt(TransformChannel.yRot, time) * weightRotation * mirrorMultiplier, channelTimeline.getValueAt(TransformChannel.zRot, time) * weightRotation * mirrorMultiplier);
    }

    @Deprecated
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

    @Deprecated
    public HashMap<LocatorSkeleton.LocatorEntry, PartPose> bakePose(){
        HashMap<LocatorSkeleton.LocatorEntry, PartPose> hashMap = Maps.newHashMap();
        for(Locator locator : locatorEntryHashMap.keySet()){
            hashMap.put(locatorEntryHashMap.get(locator), locator.getPartPose());
        }
        return hashMap;
    }

    public void applyOffsets(){
        for(Locator locator : this.locatorEntryHashMap.keySet()){
            locator.additiveApplyPose(this.locatorEntryHashMap.get(locator).defaultPose);
        }
    }

     */

    public static class LocatorEntry {
        private final Locator locator;
        private final Locator locatorMirrored;
        @Nullable
        private final String modelPartIdentifier;
        private final boolean usesModelPart;
        private final PartPose defaultPose;

        public LocatorEntry(Locator locator, Locator locatorMirrored, @Nullable String modelPartIdentifier, PartPose defaultPose){
            this.locator = locator;
            this.locatorMirrored = locatorMirrored;
            this.modelPartIdentifier = modelPartIdentifier;
            this.usesModelPart = modelPartIdentifier != null;
            this.defaultPose = defaultPose;
        }

        public void bakeLocatorToModelPart(ModelPart modelPart){
            if(usesModelPart && modelPart != null){
                this.locator.additiveApplyPose(this.defaultPose);
                this.locator.bakeToModelPart(modelPart);
            }
        }

        public Locator getLocator(){
            return this.locator;
        }

        public Locator getLocatorMirrored(){
            return this.locatorMirrored;
        }

        public String getModelPartIdentifier(){
            return this.modelPartIdentifier;
        }

        public boolean getUsesModelPart(){
            return this.usesModelPart;
        }

        public PartPose getDefaultPose(){
            return this.defaultPose;
        }
    }
}
