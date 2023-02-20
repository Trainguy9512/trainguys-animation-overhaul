package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class AnimationPose<L extends Enum<L>> {

    private final LocatorSkeleton<L> locatorSkeleton;
    private final HashMap<Enum<L>, MutablePartPose> pose = Maps.newHashMap();

    private AnimationPose(LocatorSkeleton<L> locatorSkeleton){
        this.locatorSkeleton = locatorSkeleton;
        for(Enum<L> locator : locatorSkeleton.getLocators()){
            this.setLocatorPose(locator, MutablePartPose.ZERO);
        }
    }

    public static <L extends Enum<L>> AnimationPose<L> of(LocatorSkeleton<L> locatorSkeleton){
        return new AnimationPose<>(locatorSkeleton);
    }

    public AnimationPose<L> getCopy(){
        AnimationPose<L> copiedAnimationPose = new AnimationPose<>(this.locatorSkeleton);
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            copiedAnimationPose.setLocatorPose(locator, this.getLocatorPose(locator));
        }
        return copiedAnimationPose;
    }

    public LocatorSkeleton<L> getSkeleton(){
        return this.locatorSkeleton;
    }

    public void applyDefaultPoseOffset(){
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            MutablePartPose offset = MutablePartPose.fromPartPose(this.getSkeleton().getLocatorDefaultPose(locator));
            this.setLocatorPose(locator, getLocatorPose(locator).add(offset));
        }
    }

    public void setLocatorPose(Enum<L> locator, MutablePartPose mutablePartPose){
        this.pose.put(locator, mutablePartPose);
    }

    public MutablePartPose getLocatorPose(Enum<L> locator){
        return this.pose.getOrDefault(locator, MutablePartPose.ZERO).getCopy();
    }

    /*
    public void subtractPose(AnimationPose animationPose){
        for(LocatorSkeleton.LocatorEntry locatorEntry : animationPose.getSkeleton().getLocatorEntries()){
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose);
        }
    }

     */

    public void blend(AnimationPose<L> animationPose, float alpha, Easing easing){
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            MutablePartPose mutablePartPoseA = this.getLocatorPose(locator);
            MutablePartPose mutablePartPoseB = animationPose.getLocatorPose(locator);
            this.setLocatorPose(locator, mutablePartPoseA.getCopy().blend(mutablePartPoseB, alpha, easing));
        }
    }

    public void blendLinear(AnimationPose<L> animationPose, float alpha){
        this.blend(animationPose, alpha, Easing.Linear.of());
    }

    public AnimationPose<L> getBlended(AnimationPose<L> animationPose, float alpha, Easing easing){
        AnimationPose<L> newAnimationPose = this.getCopy();
        newAnimationPose.blend(animationPose, alpha, easing);
        return newAnimationPose;
    }

    public AnimationPose<L> getBlendedLinear(AnimationPose<L> animationPose, float alpha){
        return this.getBlended(animationPose, alpha, Easing.Linear.of());
    }

    public void blendByLocators(AnimationPose<L> animationPose, @NotNull List<Enum<L>> locators, float alpha, Easing easing){
        for(Enum<L> locator : locators){
            MutablePartPose mutablePartPoseA = this.getLocatorPose(locator);
            MutablePartPose mutablePartPoseB = animationPose.getLocatorPose(locator);
            this.setLocatorPose(locator, mutablePartPoseA.getCopy().blend(mutablePartPoseB, alpha, easing));
        }
    }

    public AnimationPose<L> getBlendedByLocators(AnimationPose<L> animationPose, @NotNull List<Enum<L>> locators, float alpha, Easing easing){
        AnimationPose<L> newAnimationPose = this.getCopy();
        newAnimationPose.blendByLocators(animationPose, locators, alpha, easing);
        return newAnimationPose;
    }

    public AnimationPose<L> getBlendedByLocatorsLinear(AnimationPose<L> animationPose, List<Enum<L>> locators, float alpha){
        return this.getBlendedByLocators(animationPose, locators, alpha, Easing.Linear.of());
    }

    public AnimationPose<L> getSelectedByLocators(AnimationPose<L> animationPose, List<Enum<L>> locators){
        return this.getBlendedByLocatorsLinear(animationPose, locators, 1);
    }

    public void subtract(AnimationPose<L> animationPose){
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            this.setLocatorPose(locator, this.getLocatorPose(locator).subtract(animationPose.getLocatorPose(locator)));
        }
    }

    public AnimationPose<L> getSubtracted(AnimationPose<L> animationPose){
        AnimationPose<L> newAnimationPose = this.getCopy();
        newAnimationPose.subtract(animationPose);
        return newAnimationPose;
    }

    public void add(AnimationPose<L> animationPose){
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            this.setLocatorPose(locator, this.getLocatorPose(locator).add(animationPose.getLocatorPose(locator)));
        }
    }

    public AnimationPose<L> getAdded(AnimationPose<L> animationPose){
        AnimationPose<L> newAnimationPose = this.getCopy();
        newAnimationPose.add(animationPose);
        return newAnimationPose;
    }

    public void mirror(){
        this.mirrorBlended(1);
    }

    public void mirrorBlended(float alpha){
        HashMap<Enum<L>, MutablePartPose> mirroredPose = Maps.newHashMap();
        for(Enum<L> locator : this.getSkeleton().getLocators()){
            MutablePartPose mutablePartPose = this.getLocatorPose(locator);
            MutablePartPose mirroredMutablePartPose = this.getLocatorPose(this.getSkeleton().getMirroredLocator(locator));
            mirroredPose.put(locator, mutablePartPose.getCopy().blendLinear(mirroredMutablePartPose, alpha));
        }
        for(Enum<L> locator : mirroredPose.keySet()){
            this.setLocatorPose(locator, mirroredPose.get(locator));
        }

        /*
        for(String identifier : this.pose.keySet()){
            MutablePartPose mutablePartPose = this.pose.get(identifier);
            MutablePartPose mirroredMutablePartPose = this.pose.get(this.locatorSkeleton.getMirroredLocator(identifier));

            MutablePartPose newMutablePartPose = mutablePartPose.getCopy().blendLinear(mirroredMutablePartPose.getMirrored(), alpha);
            mirroredPose.put(identifier, newMutablePartPose);
        }
        this.pose.replaceAll((key, pose) -> mirroredPose.get(key));

         */
    }

    public static <L extends Enum<L>> AnimationPose<L> fromChannelTimeline(LocatorSkeleton<L> locatorSkeleton, ResourceLocation resourceLocation, float time){
        AnimationPose<L> animationPose = AnimationPose.of(locatorSkeleton);
        for(Enum<L> locator : locatorSkeleton.getLocators()){
            animationPose.setLocatorPose(locator, MutablePartPose.getMutablePartPoseFromChannelTimeline(resourceLocation, locator.toString(), time));
        }
        return animationPose;
    }
}
