package com.trainguy9512.animationoverhaul.animation.pose;

import com.trainguy9512.animationoverhaul.util.animation.Locator;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import net.minecraft.client.model.geom.ModelPart;

public class BakedAnimationPose<L extends Enum<L>> {

    private AnimationPose<L> pose;
    private AnimationPose<L> poseOld;
    public boolean hasPose;

    public BakedAnimationPose(){
        this.hasPose = false;
    }

    public void pushToOld(){
        this.poseOld = this.pose.getCopy();
    }

    public void setPose(AnimationPose<L> animationPose){
        this.pose = animationPose;
    }

    public AnimationPose<L> getBlendedPose(float partialTicks){
        // uncomment this for debugging
        //partialTicks = 0;
        return this.poseOld.getBlendedLinear(this.pose, partialTicks);
    }

    public void bakeToModelParts(ModelPart rootModelPart, float partialTicks){
        AnimationPose<L> blendedPose = getBlendedPose(partialTicks);
        for(Enum<L> locator : this.pose.getSkeleton().getLocators()){
            if(this.pose.getSkeleton().getLocatorUsesModelPart(locator)){
                ModelPart finalModelPart = rootModelPart;
                for(String individualPartString : this.pose.getSkeleton().getLocatorModelPartIdentifier(locator).split("\\.")){
                    finalModelPart = finalModelPart.getChild(individualPartString);
                }
                finalModelPart.loadPose(blendedPose.getLocatorPose(locator).asPartPose());
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
    public String getLocator(String identifier){
        return this.pose.getSkeleton().containsLocator(identifier) ? identifier : "null";
    }

     */

    /*
    public boolean containsLocator(String identifier){
        return this.pose.getSkeleton().containsLocator(identifier);
    }

     */
}
