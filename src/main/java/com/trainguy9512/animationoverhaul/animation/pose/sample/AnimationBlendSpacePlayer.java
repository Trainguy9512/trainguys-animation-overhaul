package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.TreeMap;

public class AnimationBlendSpacePlayer extends SampleableAnimationState {

    private final TreeMap<Float, BlendSpaceEntry> blendSpaceEntryTreeMap = new TreeMap<Float, BlendSpaceEntry>();
    private float playRateMultiplier = 1;
    private float currentValue = 0;
    private boolean mirrored = false;

    private float elapsedTime = 0;

    private AnimationBlendSpacePlayer(String identifier) {
        super(identifier);
    }

    public static AnimationBlendSpacePlayer of(String identifier){
        return new AnimationBlendSpacePlayer(identifier);
    }

    public AnimationBlendSpacePlayer addEntry(float position, ResourceLocation resourceLocation, float playRate){
        blendSpaceEntryTreeMap.put(position, new BlendSpaceEntry(resourceLocation, playRate));
        return this;
    }

    public AnimationBlendSpacePlayer setPlayRateMultipler(float newPlayRate){
        this.playRateMultiplier = newPlayRate;
        return this;
    }

    public AnimationBlendSpacePlayer setMirrored(boolean mirrored){
        this.mirrored = mirrored;
        return this;
    }

    public void setValue(float value){
        this.currentValue = value;
    }

    private float getPlayRate(){
        if(this.blendSpaceEntryTreeMap.entrySet().size() == 0){
            return 0;
        }

        var firstEntry = this.blendSpaceEntryTreeMap.floorEntry(this.currentValue);
        var secondEntry = this.blendSpaceEntryTreeMap.ceilingEntry(this.currentValue);

        if (firstEntry == null)
            return secondEntry.getValue().getPlayRate();
        if (secondEntry == null)
            return firstEntry.getValue().getPlayRate();

        // If they're both the same frame
        if (firstEntry.getKey().equals(secondEntry.getKey()))
            return firstEntry.getValue().getPlayRate();


        float relativeTime = (this.currentValue - firstEntry.getKey()) / (secondEntry.getKey() - firstEntry.getKey());
        return Mth.lerp(relativeTime, firstEntry.getValue().getPlayRate(), secondEntry.getValue().getPlayRate());
    }

    @Override
    public AnimationPose sample(LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        if(this.blendSpaceEntryTreeMap.entrySet().size() == 0){
            return new AnimationPose(locatorSkeleton);
        }

        var firstEntry = this.blendSpaceEntryTreeMap.floorEntry(this.currentValue);
        var secondEntry = this.blendSpaceEntryTreeMap.ceilingEntry(this.currentValue);

        if (firstEntry == null)
            return secondEntry.getValue().sampleEntry(locatorSkeleton, this.elapsedTime, this.mirrored);
        if (secondEntry == null)
            return firstEntry.getValue().sampleEntry(locatorSkeleton, this.elapsedTime, this.mirrored);

        // If they're both the same frame
        if (firstEntry.getKey().equals(secondEntry.getKey()))
            return firstEntry.getValue().sampleEntry(locatorSkeleton, this.elapsedTime, this.mirrored);

        float relativeTime = (this.currentValue - firstEntry.getKey()) / (secondEntry.getKey() - firstEntry.getKey());
        return AnimationPose.blendLinear(
                firstEntry.getValue().sampleEntry(locatorSkeleton, this.elapsedTime, this.mirrored),
                secondEntry.getValue().sampleEntry(locatorSkeleton, this.elapsedTime, this.mirrored),
                1 - relativeTime);
    }

    @Override
    public void tick(){
        // Update current value

        // Advance time
        this.elapsedTime += this.getPlayRate() * this.playRateMultiplier;
    }

    private class BlendSpaceEntry {
        private final ResourceLocation resourceLocation;
        private final float playRate;

        private BlendSpaceEntry(ResourceLocation resourceLocation, float playRate){
            this.resourceLocation = resourceLocation;
            this.playRate = playRate;
        }

        private float getPlayRate(){
            return this.playRate;
        }

        private float getTimeFromTicks(float time){
            float frameLength = TimelineGroupData.INSTANCE.get(this.resourceLocation).getFrameLength();
            return (time % frameLength) / frameLength;
        }

        private AnimationPose sampleEntry(LocatorSkeleton locatorSkeleton, float time, boolean mirrored){
            return AnimationPose.fromChannelTimeline(locatorSkeleton, TimelineGroupData.INSTANCE.get(resourceLocation), this.getTimeFromTicks(time), mirrored);
        }
    }
}
