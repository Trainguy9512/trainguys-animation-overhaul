package com.trainguy9512.animationoverhaul.util.animation;

import com.google.common.collect.Maps;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LocatorSkeleton {

    private final HashMap<String, LocatorEntry> locatorHashMap = Maps.newHashMap();

    public LocatorSkeleton(){

    }

    public List<LocatorEntry> getLocatorEntries(){
        return locatorHashMap.values().stream().toList();
    }

    public PartPose getLocatorDefaultPose(String locatorIdentifier){
        return locatorHashMap.get(locatorIdentifier).defaultPose;
    }

    public void addLocatorModelPart(String locator, String locatorMirrored, String modelPartIdentifier, PartPose defaultPose){
        locatorHashMap.putIfAbsent(locator, new LocatorEntry(locator, locatorMirrored, modelPartIdentifier, defaultPose));
    }

    public void addLocatorModelPart(String locator, String modelPartIdentifier, PartPose defaultPose){
        addLocatorModelPart(locator, locator, modelPartIdentifier, defaultPose);
    }

    public void addLocatorModelPart(String locator, String locatorMirrored, String modelPartIdentifier){
        addLocatorModelPart(locator, locatorMirrored, modelPartIdentifier, PartPose.ZERO);
    }

    public void addLocatorModelPart(String locator, String modelPartIdentifier){
        addLocatorModelPart(locator, modelPartIdentifier, PartPose.ZERO);
    }

    public void addLocator(String locator, String locatorMirrored){
        locatorHashMap.putIfAbsent(locator, new LocatorEntry(locator, locatorMirrored, null, PartPose.ZERO));
    }

    public void addLocator(String locator){
        addLocator(locator, locator);
    }

    @Nullable
    public String getMirroredLocator(String identifier){
        return this.locatorHashMap.get(identifier).getMirroedLocatorIdentifier();
    }

    @Nullable
    private LocatorEntry getLocatorEntry(String identifier){
        return this.locatorHashMap.get(identifier);
    }

    public boolean containsLocator(String identifier){
        return this.locatorHashMap.containsKey(identifier);
    }

    public static class LocatorEntry {
        private final String locatorIdentifier;
        private final String mirroedLocatorIdentifier;
        @Nullable
        private final String modelPartIdentifier;
        private final boolean usesModelPart;
        private final PartPose defaultPose;

        public LocatorEntry(String locatorIdentifier, String mirroedLocatorIdentifier, @Nullable String modelPartIdentifier, PartPose defaultPose){
            this.locatorIdentifier = locatorIdentifier;
            this.mirroedLocatorIdentifier = mirroedLocatorIdentifier;
            this.modelPartIdentifier = modelPartIdentifier;
            this.usesModelPart = modelPartIdentifier != null;
            this.defaultPose = defaultPose;
        }

        public String getLocatorIdentifier(){
            return this.locatorIdentifier;
        }

        public String getMirroedLocatorIdentifier(){
            return this.mirroedLocatorIdentifier;
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
