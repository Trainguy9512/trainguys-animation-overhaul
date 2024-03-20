package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import net.minecraft.util.Mth;
import java.util.ArrayList;

public class AnimationMontageTrack extends PoseSampler {

    private final ArrayList<AnimationMontage> activeMontages = new ArrayList<AnimationMontage>();

    protected AnimationMontageTrack(Builder<?> builder) {
        super(builder);
    }

    public static Builder<?> of(){
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
    public void tick(){
        // Only run if there's actually montages currently loaded
        if(this.isActive()){
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
    public <L extends Enum<L>> AnimationPose<L> sampleFromInputPose(AnimationPose<L> inputPose, JointSkeleton<L> jointSkeleton) {
        return getBlendedPose(inputPose, jointSkeleton);
    }

    public boolean isActive(){
        return !this.activeMontages.isEmpty();
    }

    private <L extends Enum<L>> AnimationPose<L> getBlendedPose(AnimationPose<L> inputPose, JointSkeleton<L> jointSkeleton){
        // Initialize the animation pose
        AnimationPose<L> animationPose = AnimationPose.of(jointSkeleton);

        // Only do this stuff if there's any loaded animation montages
        if(this.isActive()){
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

    /**
     * Plays the given montage. Should be accessed within the tick phase for intended functionality
     *
     * @param animationMontage the animation montage
     */
    public void playMontage(AnimationMontage animationMontage){
        // If the length of the montage is 2, remove entry 0 (the back layer)
        if(activeMontages.size() == 2){
            activeMontages.remove(0);
        }

        // Add the montage to the front layer
        activeMontages.add(animationMontage.copy());

        // If there is a back layer, force deactivate the back layer based on the front layer's blend in time
        if(activeMontages.size() == 2){
            activeMontages.get(0).forceInactive(activeMontages.get(1).getBlendDuration(true));
        }
    }
}
