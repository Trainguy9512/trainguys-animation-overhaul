package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationSequenceData;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class AnimationMontage {
    private final ResourceLocation resourceLocation;

    private float length;
    private float startOffset;
    private float playRate = 1;
    private float blendInDuration = 1;
    private float blendOutDuration = 1;
    private Easing blendInEasing = Easing.LINEAR;
    private Easing blendOutEasing = Easing.LINEAR;

    public float timeElapsed = 0;
    private boolean active = true;
    private float blendWeight = 0;

    private AnimationMontage(ResourceLocation resourceLocation){
        this.resourceLocation = resourceLocation;
        this.length = AnimationSequenceData.INSTANCE.get(resourceLocation).frameLength();
    }

    public static AnimationMontage of(ResourceLocation resourceLocation){
        return new AnimationMontage(resourceLocation);
    }

    public void tick(){
        // Tick time forwards using the play rate
        this.timeElapsed += this.playRate;

        // Only make the active state changeable when its active. Once it becomes deactive, it will be destroyed upon blendWeight == 0
        if(isActive()){
            setActive(this.timeElapsed < this.length);
        }

        // Adjust the current blend weight
        this.setBlendWeight(Mth.clamp(isActive() ? getBlendWeight() + getBlendSpeed(true) : getBlendWeight() - getBlendSpeed(false), 0, 1));
    }

    public <L extends Enum<L>> AnimationPose getAnimationPose(JointSkeleton jointSkeleton){
        return AnimationPose.fromAnimationSequence(jointSkeleton, this.resourceLocation, (this.timeElapsed + this.startOffset) / AnimationSequenceData.INSTANCE.get(resourceLocation).frameLength());
    }

    /**
     * Forces the montage to be inactive, regardless of whether it's currently active or not.
     * Used when a new montage is pushed on the front layer of a montage track
     *
     * @param newBlendOutTime the new blend out time
     */
    public void forceInactive(float newBlendOutTime){
        setActive(false);
        setBlendDuration(newBlendOutTime, false);
    }

    private float getBlendWeight(){
        return this.blendWeight;
    }

    public float getBlendWeightEased(){
        return (isActive() ? this.blendInEasing : this.blendOutEasing).ease(getBlendWeight());
    }

    public boolean isActive(){
        return this.active;
    }

    private float getBlendSpeed(boolean in){
        return getBlendDuration(in) != 0 ? 1 / getBlendDuration(in) : 1;
    }

    public float getBlendDuration(boolean in){
        return in ? this.blendInDuration : this.blendOutDuration;
    }

    public void setBlendWeight(float blendWeight){
        this.blendWeight = blendWeight;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public void setBlendDuration(float blendDuration, boolean in, Easing easing){
        if(in){
            this.blendInDuration = blendDuration;
            this.blendInEasing = easing;
        } else {
            this.blendOutDuration = blendDuration;
            this.blendOutEasing = easing;
        }
    }

    public void setBlendDuration(float blendDuration, boolean in){
        setBlendDuration(blendDuration, in, Easing.LINEAR);
    }

    public AnimationMontage setLength(float length){
        this.length = length;
        return this;
    }

    public AnimationMontage setStartOffset(float startOffset){
        this.startOffset = startOffset;
        return this;
    }

    public AnimationMontage setPlayRate(float playRate){
        this.playRate = playRate;
        return this;
    }

    public AnimationMontage setBlendInDuration(float blendInDuration){
        this.blendInDuration = blendInDuration;
        return this;
    }

    public AnimationMontage setBlendOutDuration(float blendOutDuration){
        this.blendOutDuration = blendOutDuration;
        return this;
    }

    public AnimationMontage copy(){
        return AnimationMontage.of(this.resourceLocation)
                .setLength(this.length)
                .setStartOffset(this.startOffset)
                .setBlendInDuration(this.blendInDuration)
                .setBlendOutDuration(this.blendOutDuration)
                .setPlayRate(this.playRate);
    }
}
