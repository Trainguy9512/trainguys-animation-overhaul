package com.trainguy9512.animationoverhaul.animation.pose;

import com.trainguy9512.animationoverhaul.util.animation.Locator;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import net.minecraft.client.model.geom.ModelPart;

public class BakedAnimationPose {

    private AnimationPose pose;
    private AnimationPose poseOld;
    public boolean hasPose;

    public BakedAnimationPose(){
        this.hasPose = false;
    }

    public void pushToOld(){
        this.poseOld = this.pose.getCopy();
    }

    public void setPose(AnimationPose animationPose){
        this.pose = animationPose;
    }

    public AnimationPose getBlendedPose(float partialTicks){
        return AnimationPose.blendLinear(this.poseOld, this.pose, 1 - partialTicks);
    }

    public void bakeToModelParts(ModelPart rootModelPart, float partialTicks){
        AnimationPose blendedPose = getBlendedPose(partialTicks);
        for(LocatorSkeleton.LocatorEntry locatorEntry : pose.getSkeleton().getLocatorEntries()){
            if(locatorEntry.getModelPartIdentifier() != null){
                ModelPart finalModelPart = rootModelPart;
                for(String individualPartString : locatorEntry.getModelPartIdentifier().split("\\.")){
                    finalModelPart = finalModelPart.getChild(individualPartString);
                }
                finalModelPart.loadPose(blendedPose.getLocatorPose(locatorEntry.getLocator()).asPartPose());
            }
        }
    }

    /*
    private static Locator lerpLocator(float value, Locator locatorOld, Locator locator){
        Locator locatorNew = new Locator(locator.getIdentifier());
        locatorNew.translateX = Mth.lerp(value, locatorOld.translateX, locator.translateX);
        locatorNew.translateY = Mth.lerp(value, locatorOld.translateY, locator.translateY);
        locatorNew.translateZ = Mth.lerp(value, locatorOld.translateZ, locator.translateZ);
        locatorNew.rotateX = Mth.rotLerp(value, locatorOld.rotateX, locator.rotateX);
        locatorNew.rotateY = Mth.rotLerp(value, locatorOld.rotateY, locator.rotateY);
        locatorNew.rotateZ = Mth.rotLerp(value, locatorOld.rotateZ, locator.rotateZ);
        return locatorNew;
    }

     */

    public Locator getLocator(String identifier){
        return this.pose.getSkeleton().getLocator(identifier, false);
        /*
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.pose.getSkeleton().getLocatorEntries()){
            if(Objects.equals(locatorEntry.getLocator().getIdentifier(), identifier)){
                return Locator.fromPartPose(lerpPartPose(partialTicks, this.poseOld.get(locatorEntry), this.pose.get(locatorEntry)), identifier);
            }
        }
        return new Locator("null");

         */
    }

    public boolean containsLocator(String identifier){
        return this.pose.getSkeleton().containsLocator(identifier);
    }
}
