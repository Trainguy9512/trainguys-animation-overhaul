package com.trainguy9512.animationoverhaul.animation.pose.function.cache;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.LocalSpacePose;
import com.trainguy9512.animationoverhaul.animation.pose.function.PoseFunction;
import net.minecraft.core.HolderSet;

import java.util.Map;
import java.util.Optional;

public class SavedCachedPoseContainer {

    private final Map<String, CachedPoseFunction> savedCachedPoses;

    private SavedCachedPoseContainer(){
        this.savedCachedPoses = Maps.newHashMap();
    }

    public static SavedCachedPoseContainer of(){
        return new SavedCachedPoseContainer();
    }

    public void register(String identifier, PoseFunction<LocalSpacePose> poseFunction){
        if(this.savedCachedPoses.containsKey(identifier)){
            throw new IllegalArgumentException("Failed to register saved cached pose for identifier " + identifier + " due to it being already registered.");
        } else {
            this.savedCachedPoses.put(identifier, CachedPoseFunction.of(poseFunction));
        }
    }

    public PoseFunction<LocalSpacePose> getOrThrow(String identifier){
        return Optional.ofNullable(this.savedCachedPoses.get(identifier)).orElseThrow(() -> new IllegalStateException("Missing saved cached pose for identifier " + identifier));
    }

    public void clearCaches(){
        this.savedCachedPoses.values().forEach(CachedPoseFunction::clearCache);
    }
}
