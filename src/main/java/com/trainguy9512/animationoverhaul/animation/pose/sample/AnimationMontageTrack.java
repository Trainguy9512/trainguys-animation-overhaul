package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.AnimationSequenceData;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import java.util.ArrayList;

public class AnimationMontageTrack extends PoseSampler implements SampleableFromInput {

    private final ArrayList<AnimationMontage> activeMontages = new ArrayList<AnimationMontage>();

    protected AnimationMontageTrack(Builder<?> builder) {
        super(builder);
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
        // Only run if there's actually montages currently loaded
        if(this.hasActiveMontages()){
            ArrayList<AnimationMontage> montagesToRemove = new ArrayList<>();
            for(AnimationMontage animationMontage : activeMontages){
                animationMontage.tick();
                // If the montage has a weight of 0, add it to the list of montages to remove
                if(animationMontage.getBlendWeightEased() == 0){
                    montagesToRemove.add(animationMontage);
                }
            }
            for(AnimationMontage animationMontage : montagesToRemove){
                this.activeMontages.remove(animationMontage);
            }
        }

    }

    @Override
    public AnimationPose sample(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton, AnimationPose inputPose) {
        // Initialize the animation pose
        AnimationPose animationPose = AnimationPose.of(jointSkeleton);

        // Only do this stuff if there's any loaded animation montages
        if(this.hasActiveMontages()){
            // Iterate over each montage and get the blended animation between top and bottom layers
            for(int i = 0; i < this.activeMontages.size(); i++){
                AnimationMontage animationMontage = this.activeMontages.get(i);
                if(i == 0){
                    animationPose = animationMontage.getAnimationPose(jointSkeleton);
                } else {
                    animationPose.blendLinear(animationMontage.getAnimationPose(jointSkeleton), animationMontage.getBlendWeightEased()
                    );
                }
            }
            float totalBlendWeight = 0;
            for(AnimationMontage animationMontage : this.activeMontages){
                totalBlendWeight = Mth.clamp(totalBlendWeight + animationMontage.getBlendWeightEased(), 0, 1);
            }
            //AnimationOverhaulMain.LOGGER.info("{}, {}", this.activeMontages.get(0).timeElapsed, totalBlendWeight);
            animationPose = inputPose.getBlendedLinear(animationPose, totalBlendWeight);
        } else {
            return inputPose;
        }
        return animationPose;
    }

    public boolean hasActiveMontages(){
        return !this.activeMontages.isEmpty();
    }

    /**
     * Plays the given montage. Should be accessed within the tick phase for intended functionality
     *
     * @param animationMontage the animation montage
     */
    public void playMontage(AnimationMontage animationMontage){
        // If the length of the montage is 2, remove entry 0 (the back layer)
        if(activeMontages.size() == 2){
            activeMontages.removeFirst();
        }

        // Add the montage to the front layer
        activeMontages.add(animationMontage.copy());

        // If there is a back layer, force deactivate the back layer based on the front layer's blend in time
        if(activeMontages.size() == 2){
            activeMontages.get(0).forceInactive(activeMontages.get(1).getBlendDuration(true));
        }
    }

    public record MontageConfiguration(ResourceLocation animationSequence, float startTime, float endTime, float playRate, float transitionInDuration, float transitionOutDuration, Easing transitionInEasing, Easing transitionOutEasing, float ){

        private MontageConfiguration(Builder builder){
            this(builder.animationSequence, builder.startTime, builder.endTime, builder.playRate, builder.transitionInDuration, builder.transitionOutDuration, builder.transitionInEasing, builder.transitionOutEasing);
        }

        public static Builder builder(ResourceLocation animationSequence){
            return new Builder(animationSequence);
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
             * Sets the easing used for either the in or out transition.
             * @param easing        Easing interface to use.
             * @param in            Whether the easing will be for the in transition or out transition. True = in, false = out.
             */
            public Builder setTransitionEasing(Easing easing, boolean in){
                if(in){
                    this.transitionInEasing = easing;
                } else {
                    this.transitionOutEasing = easing;
                }
                return this;
            }

            /**
             * Sets the duration of either the in or out transition.
             * @param duration      Duration of the transition, in ticks.
             * @param in            Whether the duration will be for the in transition or out transition. True = in, false = out.
             */
            public Builder setTransitionDuration(float duration, boolean in){
                if(in){
                    this.transitionInDuration = duration;
                } else {
                    this.transitionOutDuration = duration;
                }
                return this;
            }


            public MontageConfiguration build(){
                return new MontageConfiguration(this);
            }
        }

    }
}
