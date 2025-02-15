package com.trainguy9512.animationoverhaul.animation.pose;


import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;

public class BakedAnimationPose {

    private AnimationPose pose;
    private AnimationPose poseOld;

    public BakedAnimationPose(JointSkeleton jointSkeleton){
        this.pose = AnimationPose.of(jointSkeleton);
        this.poseOld = AnimationPose.of(jointSkeleton);
    }

    /**
     * Pushes the current pose to the previous pose, and sets the provided animation pose to a copy of the new current pose.
     * @param animationPose New current animation pose
     */
    public void pushPose(AnimationPose animationPose){
        this.poseOld = pose;
        this.pose = new AnimationPose(animationPose);
    }

    /**
     * Returns an interpolated animation pose using the provided partial ticks. Used when extracting the render state.
     * @param partialTicks Time since the previous tick
     * @return Interpolated animation pose
     */
    public AnimationPose getBlendedPose(float partialTicks){
        // uncomment this for debugging
        //partialTicks = 1;
        return this.poseOld.getBlendedLinear(this.pose, partialTicks).getConvertedToEntitySpace();
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
