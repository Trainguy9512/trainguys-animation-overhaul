package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;
import com.trainguy9512.animationoverhaul.animation.pose.sample.Sampleable;
import com.trainguy9512.animationoverhaul.animation.pose.sample.SampleableFromInput;

import java.util.HashMap;

public class PoseSamplerStateContainer {

    private final HashMap<AnimationDataKey<? extends PoseSampler>, PoseSampler> poseSamplers;
    private final JointSkeleton jointSkeleton;

    public PoseSamplerStateContainer(JointSkeleton jointSkeleton) {
        this.poseSamplers = Maps.newHashMap();
        this.jointSkeleton = jointSkeleton;
    }

    /**
     * Iterates over every currently loaded pose sampler and executes the {@link PoseSampler#tick(AnimationDriverContainer, PoseSamplerStateContainer)} method
     * <p>
     * The update order is as follows: State machines first, then all others.
     *
     * @param animationDriverContainer Extracted animation data
     * @implNote Only do this once per game tick! For entities, this is handled in the entity joint animator dispatcher.
     */
    public void tick(AnimationDriverContainer animationDriverContainer){
        this.tickUpdateOrderGroup(animationDriverContainer, PoseSampler.UpdateCategory.STATE_MACHINES);
        this.tickUpdateOrderGroup(animationDriverContainer, PoseSampler.UpdateCategory.OTHER);
    }

    private void tickUpdateOrderGroup(AnimationDriverContainer animationDriverContainer, PoseSampler.UpdateCategory updateOrder){
        this.poseSamplers.values().stream()
                .filter((poseSampler -> poseSampler.getUpdateCategory() == updateOrder))
                .sorted()
                .forEach((poseSampler -> poseSampler.tick(animationDriverContainer, this)));
    }

    /**
     * Returns a pose sampler from the given key. If one is not currently loaded into
     * this pose sampler state container, then a new one is created from the key's default
     * value and loaded into this animation data container and returned.
     *
     * @param poseSamplerKey the {@link AnimationDataKey<PoseSampler>} attached to the desired {@link PoseSampler}
     *
     * @return a {@link PoseSampler} object reference
     */
    @SuppressWarnings("unchecked")
    public <P extends PoseSampler> P getPoseSampler(AnimationDataKey<P> poseSamplerKey){
        return (P) this.poseSamplers.computeIfAbsent(poseSamplerKey, AnimationDataKey::createInstance);
    }

    public <P extends PoseSampler & Sampleable> AnimationPose sample(AnimationDataKey<P> poseSamplerKey, AnimationDriverContainer animationDriverContainer){
        return this.getPoseSampler(poseSamplerKey).sample(animationDriverContainer, this, this.jointSkeleton);
    }

    public <P extends PoseSampler & SampleableFromInput> AnimationPose sample(AnimationDataKey<P> poseSamplerKey, AnimationDriverContainer animationDriverContainer, AnimationPose animationPose){
        return this.getPoseSampler(poseSamplerKey).sample(animationDriverContainer, this, this.jointSkeleton, animationPose);
    }
}
