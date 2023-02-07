package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.List;

public class AnimationMontageTrack extends SampleableAnimationState {

    private final ArrayList<AnimationMontage> activeMontages = new ArrayList<AnimationMontage>();

    public AnimationMontageTrack(String identifier) {
        super(identifier);
    }

    public static AnimationMontageTrack of(String identifier){
        return new AnimationMontageTrack(identifier);
    }

    @Override
    public void tick(){
        // Only run if there's actually montages currently loaded
        if(activeMontages.size() > 0){
            ArrayList<AnimationMontage> montagesToRemove = new ArrayList<>();
            for(AnimationMontage animationMontage : activeMontages){
                animationMontage.tick();
                // If the montage has a weight of 0, add it to the list of montages to remove
                if(animationMontage.getBlendWeight() == 0){
                    montagesToRemove.add(animationMontage);
                }
            }
            for(AnimationMontage animationMontage : montagesToRemove){
                this.activeMontages.remove(animationMontage);
            }
        }

    }

    @Override
    public AnimationPose sample(LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer){
        return new AnimationPose(locatorSkeleton);
    }

    @Override
    public AnimationPose sampleFromInputPose(AnimationPose inputPose, LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        return getBlendedPose(inputPose, locatorSkeleton);
    }

    private AnimationPose getBlendedPose(AnimationPose inputPose, LocatorSkeleton locatorSkeleton){
        // Initialize the animation pose
        AnimationPose animationPose = new AnimationPose(locatorSkeleton);

        // Only do this stuff if there's any loaded animation montages
        if(this.activeMontages.size() > 0){
            // Iterate over each montage and get the blended animation between top and bottom layers
            for(int i = 0; i < this.activeMontages.size(); i++){
                AnimationMontage animationMontage = this.activeMontages.get(i);
                if(i == 0){
                    animationPose = animationMontage.getAnimationPose(locatorSkeleton);
                } else {
                    animationPose = AnimationPose.blendLinear(
                            animationMontage.getAnimationPose(locatorSkeleton),
                            animationPose,
                            animationMontage.getBlendWeight()
                    );
                }
            }
            float totalBlendWeight = 0;
            for(AnimationMontage animationMontage : this.activeMontages){
                totalBlendWeight = Mth.clamp(totalBlendWeight + animationMontage.getBlendWeight(), 0, 1);
            }
            //AnimationOverhaulMain.LOGGER.info("{}, {}", this.activeMontages.get(0).timeElapsed, totalBlendWeight);
            animationPose = AnimationPose.blendLinear(
                    animationPose,
                    inputPose,
                    totalBlendWeight
            );
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
