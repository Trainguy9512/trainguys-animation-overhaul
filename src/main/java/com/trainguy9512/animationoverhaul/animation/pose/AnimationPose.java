package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.util.data.TransformChannel;
import com.trainguy9512.animationoverhaul.util.time.ChannelTimeline;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AnimationPose {

    private final LocatorSkeleton locatorSkeleton;
    private final HashMap<String, MutablePartPose> pose = Maps.newHashMap();

    public AnimationPose(LocatorSkeleton locatorSkeleton){
        this.locatorSkeleton = locatorSkeleton;
        for(LocatorSkeleton.LocatorEntry locatorEntry : locatorSkeleton.getLocatorEntries()){
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose.ZERO);
        }
    }

    public AnimationPose getCopy(){
        AnimationPose copiedAnimationPose = new AnimationPose(this.locatorSkeleton);
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            copiedAnimationPose.setLocatorPose(locatorEntry.getLocatorIdentifier(), this.getLocatorPose(locatorEntry.getLocatorIdentifier()));
        }
        return copiedAnimationPose;
    }

    public LocatorSkeleton getSkeleton(){
        return this.locatorSkeleton;
    }

    public void applyDefaultPoseOffset(){
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            MutablePartPose offset = MutablePartPose.fromPartPose(locatorEntry.getDefaultPose());
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose.add(getLocatorPose(locatorEntry.getLocatorIdentifier()), offset));
        }
    }

    public void setLocatorPose(String locatorIdentifier, MutablePartPose mutablePartPose){
        this.pose.put(locatorIdentifier, mutablePartPose);
    }

    public MutablePartPose getLocatorPose(String locatorIdentifier){
        return this.pose.getOrDefault(locatorIdentifier, MutablePartPose.ZERO).getCopy();
    }

    /*
    public void subtractPose(AnimationPose animationPose){
        for(LocatorSkeleton.LocatorEntry locatorEntry : animationPose.getSkeleton().getLocatorEntries()){
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose);
        }
    }

     */

    public AnimationPose blend(AnimationPose animationPose, float alpha, Easing easing){
        // Simple inverting solution for now.
        //alpha = 1 - alpha;
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            MutablePartPose mutablePartPoseA = animationPose.getLocatorPose(locatorEntry.getLocatorIdentifier());
            MutablePartPose mutablePartPoseB = this.getLocatorPose(locatorEntry.getLocatorIdentifier());

            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), mutablePartPoseB.getCopy().blend(mutablePartPoseA, alpha, easing));
        }
        return this;
    }

    public AnimationPose blendLinear(AnimationPose animationPose, float alpha){
        return this.blend(animationPose, alpha, Easing.Linear.of());
    }

    public AnimationPose blendBoolean(AnimationPose animationPose, boolean value){
        return this.blendLinear(animationPose, value ? 0 : 1);
    }

    public AnimationPose mirror(){
        return mirrorBlended(1);
    }

    public AnimationPose mirrorBlended(float alpha){
        HashMap<String, MutablePartPose> mirroredPose = Maps.newHashMap();
        for(String identifier : this.pose.keySet()){
            MutablePartPose mutablePartPose = this.pose.get(identifier);
            MutablePartPose mirroredMutablePartPose = this.pose.get(this.locatorSkeleton.getMirroredLocator(identifier));
            boolean mirrorXTranslation = Objects.equals(identifier, this.locatorSkeleton.getMirroredLocator(identifier));

            //TODO: Add proper mutable part pose blend function
            MutablePartPose newMutablePartPose = mutablePartPose.getCopy().blendLinear(mirroredMutablePartPose.getMirrored(), alpha);
            mirroredPose.put(identifier, newMutablePartPose);
        }
        this.pose.replaceAll((key, pose) -> mirroredPose.get(key));
        return this;
    }

    public static AnimationPose fromChannelTimeline(LocatorSkeleton locatorSkeleton, ResourceLocation resourceLocation, float time){
        AnimationPose animationPose = new AnimationPose(locatorSkeleton);
        for(LocatorSkeleton.LocatorEntry locatorEntry : locatorSkeleton.getLocatorEntries()){
            String locator = locatorEntry.getLocatorIdentifier();
            animationPose.setLocatorPose(locator, MutablePartPose.getMutablePartPoseFromChannelTimeline(resourceLocation, locator, time));
        }
        return animationPose;
    }

    /*
    public static AnimationPose makeDynamicAdditivePose(AnimationPose referencePose, AnimationPose additivePose){

    }

     */
}
