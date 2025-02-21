package com.trainguy9512.animationoverhaul.animation.pose.sampler;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationSequenceData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.TreeMap;

//TODO: 2-dimensional blendspace stuff.
public class AnimationBlendSpacePlayer extends TimeBasedPoseSampler implements Sampleable {

    private final TreeMap<Float, BlendSpaceEntry> blendSpaceEntryTreeMap;
    private float currentValue = 0;
    private float playRateMultiplier = 1;

    private AnimationBlendSpacePlayer(Builder<?> builder) {
        super(builder);
        this.blendSpaceEntryTreeMap = builder.blendSpaceEntryTreeMap;
        this.currentValue = builder.currentValue;
        this.playRateMultiplier = builder.playRateMultiplier;
    }

    public static Builder<?> builder(){
        return new Builder<>();
    }


    public static class Builder<B extends Builder<B>> extends TimeBasedPoseSampler.Builder<B> {

        private final TreeMap<Float, BlendSpaceEntry> blendSpaceEntryTreeMap = new TreeMap<>();
        private float currentValue = 0f;
        private float playRateMultiplier = 1f;

        protected Builder() {
            super();
        }

        @SuppressWarnings("unchecked")
        public B addBlendSpaceEntry(float position, ResourceLocation resourceLocation, float playRate){
            this.blendSpaceEntryTreeMap.put(position, new BlendSpaceEntry(resourceLocation, playRate));
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setDefaultValue(float currentValue){
            this.currentValue = currentValue;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setPlayRateMultiplier(float playRateMultiplier){
            this.playRateMultiplier = playRateMultiplier;
            return (B) this;
        }

        @Override
        public AnimationBlendSpacePlayer build() {
            return new AnimationBlendSpacePlayer(this);
        }
    }

    public AnimationBlendSpacePlayer setPlayRateMultipler(float newPlayRate){
        this.playRateMultiplier = newPlayRate;
        return this;
    }

    public void setValue(float value){
        this.currentValue = value;
    }

    private float getPlayRateBlended(){
        if(this.blendSpaceEntryTreeMap.entrySet().isEmpty()){
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
    public AnimationPose sample(DriverAnimationContainer driverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton) {
        if(this.blendSpaceEntryTreeMap.entrySet().isEmpty()){
            return AnimationPose.of(jointSkeleton);
        }

        var firstEntry = this.blendSpaceEntryTreeMap.floorEntry(this.currentValue);
        var secondEntry = this.blendSpaceEntryTreeMap.ceilingEntry(this.currentValue);

        if (firstEntry == null)
            return secondEntry.getValue().sampleEntry(jointSkeleton, this.getTimeElapsed());
        if (secondEntry == null)
            return firstEntry.getValue().sampleEntry(jointSkeleton, this.getTimeElapsed());

        // If they're both the same frame
        if (firstEntry.getKey().equals(secondEntry.getKey()))
            return firstEntry.getValue().sampleEntry(jointSkeleton, this.getTimeElapsed());

        float relativeTime = (this.currentValue - firstEntry.getKey()) / (secondEntry.getKey() - firstEntry.getKey());
        return firstEntry.getValue().sampleEntry(jointSkeleton, this.getTimeElapsed()).interpolated(
                secondEntry.getValue().sampleEntry(jointSkeleton, this.getTimeElapsed()),
                relativeTime
        );
    }

    @Override
    public void tick(DriverAnimationContainer driverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
        // Update current value

        // Advance time
        this.setPlayRate(this.getPlayRateBlended() * this.playRateMultiplier);
        super.tick(driverContainer, poseSamplerStateContainer);
    }

    private record BlendSpaceEntry(ResourceLocation resourceLocation, float playRate) {

        private float getPlayRate() {
            return this.playRate;
        }

        private float getTimeFromTicks(float time) {
            float frameLength = AnimationSequenceData.INSTANCE.get(this.resourceLocation).frameLength();
            return (time % frameLength) / frameLength;
        }

        private AnimationPose sampleEntry(JointSkeleton jointSkeleton, float time) {
            return AnimationPose.fromAnimationSequence(jointSkeleton, this.resourceLocation, this.getTimeFromTicks(time));
        }
    }
}
