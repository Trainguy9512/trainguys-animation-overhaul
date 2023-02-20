package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.TreeMap;

public class AnimationBlendSpacePlayer extends TimeBasedAnimationState {

    private final TreeMap<Float, BlendSpaceEntry> blendSpaceEntryTreeMap = new TreeMap<Float, BlendSpaceEntry>();
    private float currentValue = 0;
    private float playRateMultiplier = 1;

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

    public void setValue(float value){
        this.currentValue = value;
    }

    private float getPlayRateBlended(){
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
    public <L extends Enum<L>> AnimationPose<L> sample(LocatorSkeleton<L> locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        if(this.blendSpaceEntryTreeMap.entrySet().size() == 0){
            return AnimationPose.of(locatorSkeleton);
        }

        var firstEntry = this.blendSpaceEntryTreeMap.floorEntry(this.currentValue);
        var secondEntry = this.blendSpaceEntryTreeMap.ceilingEntry(this.currentValue);

        if (firstEntry == null)
            return secondEntry.getValue().sampleEntry(locatorSkeleton, this.getTimeElapsed());
        if (secondEntry == null)
            return firstEntry.getValue().sampleEntry(locatorSkeleton, this.getTimeElapsed());

        // If they're both the same frame
        if (firstEntry.getKey().equals(secondEntry.getKey()))
            return firstEntry.getValue().sampleEntry(locatorSkeleton, this.getTimeElapsed());

        float relativeTime = (this.currentValue - firstEntry.getKey()) / (secondEntry.getKey() - firstEntry.getKey());
        return firstEntry.getValue().sampleEntry(locatorSkeleton, this.getTimeElapsed()).getBlendedLinear(
                secondEntry.getValue().sampleEntry(locatorSkeleton, this.getTimeElapsed()),
                relativeTime
        );
    }

    @Override
    public void tick(){
        // Update current value

        // Advance time
        this.setPlayRate(this.getPlayRateBlended() * this.playRateMultiplier);
        super.tick();
    }

    private record BlendSpaceEntry(ResourceLocation resourceLocation, float playRate) {

        private float getPlayRate() {
            return this.playRate;
        }

        private float getTimeFromTicks(float time) {
            float frameLength = TimelineGroupData.INSTANCE.get(this.resourceLocation).getFrameLength();
            return (time % frameLength) / frameLength;
        }

        private<L extends Enum<L>>  AnimationPose<L> sampleEntry(LocatorSkeleton<L> locatorSkeleton, float time) {
            return AnimationPose.fromChannelTimeline(locatorSkeleton, this.resourceLocation, this.getTimeFromTicks(time));
        }
    }
}
