package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
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
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), getLocatorPose(locatorEntry.getLocatorIdentifier()).add(offset));
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

    public void blend(AnimationPose animationPose, float alpha, Easing easing){
        // Simple inverting solution for now.
        //alpha = 1 - alpha;
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            MutablePartPose mutablePartPoseA = this.getLocatorPose(locatorEntry.getLocatorIdentifier());
            MutablePartPose mutablePartPoseB = animationPose.getLocatorPose(locatorEntry.getLocatorIdentifier());
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), mutablePartPoseA.getCopy().blend(mutablePartPoseB, alpha, easing));
        }
        //return this;
    }

    public void blendLinear(AnimationPose animationPose, float alpha){
        this.blend(animationPose, alpha, Easing.Linear.of());
    }

    public AnimationPose getBlended(AnimationPose animationPose, float alpha, Easing easing){
        AnimationPose newAnimationPose = this.getCopy();
        newAnimationPose.blend(animationPose, alpha, easing);
        return newAnimationPose;
    }

    public AnimationPose getBlendedLinear(AnimationPose animationPose, float alpha){
        return this.getBlended(animationPose, alpha, Easing.Linear.of());
    }

    public AnimationPose getBlendedByLocators(AnimationPose animationPose, List<String> locators, float alpha, Easing easing){
        AnimationPose newAnimationPose = this.getCopy();
        for(String locator : locators){
            if(newAnimationPose.locatorSkeleton.containsLocator(locator)){
                MutablePartPose mutablePartPoseA = newAnimationPose.getLocatorPose(locator);
                MutablePartPose mutablePartPoseB = animationPose.getLocatorPose(locator);
                newAnimationPose.setLocatorPose(locator, mutablePartPoseA.getCopy().blend(mutablePartPoseB, alpha, easing));
            }
        }
        return newAnimationPose;
    }

    public AnimationPose getBlendedByLocatorsLinear(AnimationPose animationPose, List<String> locators, float alpha){
        return this.getBlendedByLocators(animationPose, locators, alpha, Easing.Linear.of());
    }

    public AnimationPose getSelectedByLocators(AnimationPose animationPose, List<String> locators){
        return this.getBlendedByLocatorsLinear(animationPose, locators, 1);
    }

    public void subtract(AnimationPose animationPose){
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            String locator = locatorEntry.getLocatorIdentifier();
            this.setLocatorPose(locator, this.getLocatorPose(locator).subtract(animationPose.getLocatorPose(locator)));
        }
    }

    public AnimationPose getSubtracted(AnimationPose animationPose){
        AnimationPose newAnimationPose = this.getCopy();
        newAnimationPose.subtract(animationPose);
        return newAnimationPose;
    }


    public void add(AnimationPose animationPose){
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            String locator = locatorEntry.getLocatorIdentifier();
            this.setLocatorPose(locator, this.getLocatorPose(locator).add(animationPose.getLocatorPose(locator)));
        }
    }

    public AnimationPose getAdded(AnimationPose animationPose){
        AnimationPose newAnimationPose = this.getCopy();
        newAnimationPose.add(animationPose);
        return newAnimationPose;
    }

    public AnimationPose getMirrored(){
        return getMirroredBlended(1);
    }

    public AnimationPose getMirroredBlended(float alpha){
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
