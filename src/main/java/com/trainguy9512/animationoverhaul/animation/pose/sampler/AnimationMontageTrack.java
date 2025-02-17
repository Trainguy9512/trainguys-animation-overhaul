package com.trainguy9512.animationoverhaul.animation.pose.sampler;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataKey;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.AnimationSequenceData;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.Map;

public class AnimationMontageTrack extends PoseSampler implements SampleableFromInput {

    private final LinkedHashMap<MontageConfiguration, Float> montages;

    protected AnimationMontageTrack(Builder<?> builder) {
        super(builder);
        this.montages = Maps.newLinkedHashMap();
    }

    public static Builder<?> builder(){
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> extends PoseSampler.Builder<B> {

        protected Builder() {
            super();
        }

        @Override
        public AnimationMontageTrack build() {
            return new AnimationMontageTrack(this);
        }
    }

    @Override
    public void tick(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
        // Increment the elapsed time of each active montage
        this.montages.forEach((configuration, timeElapsed) -> this.montages.put(configuration, (timeElapsed == null ? 0 : timeElapsed) + configuration.playRate()));
        // Remove any montage that has an elapsed time greater than the montage's length
        this.montages.keySet().stream().filter((configuration) -> this.montages.get(configuration) > configuration.getLength()).forEach(this.montages::remove);
    }

    @Override
    public AnimationPose sample(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton, AnimationPose inputPose) {
        if(!this.montages.isEmpty()){
            AnimationPose pose = new AnimationPose(inputPose);
            for (Map.Entry<MontageConfiguration, Float> entry : this.montages.entrySet()){
                MontageConfiguration configuration = entry.getKey();
                float timeElapsed = entry.getValue();
                float weight = configuration.getWeight(timeElapsed);
                if(weight > 0){
                    pose = pose.interpolated(AnimationPose.fromAnimationSequence(jointSkeleton, configuration.animationSequence, (timeElapsed + configuration.startTime) / AnimationSequenceData.INSTANCE.get(configuration.animationSequence).frameLength()), weight);
                }
            }
            return pose;
        }
        return inputPose;
    }

    /**
     * Plays the given montage. Should be accessed within the tick phase for intended functionality
     *
     * @param montageConfigurationKey   Data key for a montage configuration
     */
    public void playMontage(AnimationDataKey<MontageConfiguration> montageConfigurationKey){
        // If the length of the montage is 2, remove entry 0 (the back layer)
        if(this.montages.size() == 2){
            this.montages.remove(this.montages.firstEntry().getKey());
        }

        // Add the montage to the front layer
        this.montages.putLast(montageConfigurationKey.createInstance(), 0f);
    }

    public record MontageConfiguration(ResourceLocation animationSequence, float startTime, float endTime, float playRate, float transitionInDuration, float transitionOutDuration, Easing transitionInEasing, Easing transitionOutEasing, float transitionOutStartTime){

        private MontageConfiguration(Builder builder){
            this(builder.animationSequence, builder.startTime, builder.endTime, builder.playRate, builder.transitionInDuration, builder.transitionOutDuration, builder.transitionInEasing, builder.transitionOutEasing, builder.transitionOutStartTime);
        }

        public static Builder builder(ResourceLocation animationSequence){
            return new Builder(animationSequence);
        }

        public float getLength(){
            return (this.endTime - this.startTime) + (this.transitionOutStartTime * this.transitionOutDuration) + transitionOutDuration;
        }

        public float getWeight(float timeElapsed){
            float offsetEndTransitionStartTime = this.getLength() - this.transitionOutDuration;
            if(timeElapsed < this.transitionInDuration){
                return this.transitionInEasing.ease(timeElapsed / this.transitionInDuration);
            } else if(timeElapsed > offsetEndTransitionStartTime){
                return this.transitionOutEasing.ease(1 - ((timeElapsed - offsetEndTransitionStartTime) / this.transitionOutDuration));
            }
            return 1;
        }

        public static class Builder {

            private final ResourceLocation animationSequence;
            private float startTime = 0;
            private float endTime;
            private float playRate = 1;
            private float transitionInDuration = 1;
            private float transitionOutDuration = 1;
            private Easing transitionInEasing = Easing.LINEAR;
            private Easing transitionOutEasing = Easing.LINEAR;
            private float transitionOutStartTime = 0;

            private Builder(ResourceLocation animationSequence){
                this.animationSequence = animationSequence;
                this.endTime = AnimationSequenceData.INSTANCE.get(this.animationSequence).frameLength();
            }

            /**
             * Sets the frame of animation on which the montage will start. Default is 0
             */
            public Builder setStartTime(float startTime){
                this.startTime = startTime;
                return this;
            }

            /**
             * Sets the frame of animation on which the montage will end. Default is the end of the animation sequence.
             */
            public Builder setEndTime(float endTime){
                this.endTime = endTime;
                return this;
            }

            /**
             * Sets the rate at which the montage will play. Default is 1.
             */
            public Builder setPlayRate(float playRate){
                this.playRate = playRate;
                return this;
            }

            /**
             * Sets the duration of either the in transition.
             */
            public Builder setTransitionInDuration(float duration){
                this.transitionInDuration = duration;
                return this;
            }

            /**
             * Sets the duration of either the out transition.
             */
            public Builder setTransitionOutDuration(float duration){
                this.transitionOutDuration = duration;
                return this;
            }

            /**
             * Sets the easing used for either the in transition.
             */
            public Builder setTransitionInEasing(Easing easing){
                this.transitionInEasing = easing;
                return this;
            }

            /**
             * Sets the easing used for either the out transition.
             */
            public Builder setTransitionOutEasing(Easing easing){
                this.transitionOutEasing = easing;
                return this;
            }

            /**
             * Sets the start time for the out transition.
             * @param transitionOutStartTime    For the transition to end at the montage end time, this would be -1. For the montage to start at the end time, this would be 0.
             */
            public Builder setTransitionOutStartTime(float transitionOutStartTime){
                this.transitionOutStartTime = transitionOutStartTime;
                return this;
            }

            public MontageConfiguration build(){
                return new MontageConfiguration(this);
            }
        }
    }
}
