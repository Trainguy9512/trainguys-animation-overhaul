package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;
import com.trainguy9512.animationoverhaul.animation.pose.sample.Sampleable;
import com.trainguy9512.animationoverhaul.animation.pose.sample.SampleableFromInput;

import java.util.HashMap;

public class PoseSamplerStateContainer {

    private final HashMap<PoseSamplerKey<?>, PoseSampler> poseSamplers;
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
                .sorted((poseSampler) -> )
                .forEach((poseSampler -> poseSampler.tick(animationDriverContainer, this)));
    }

    /**
     * Returns a pose sampler from the given key. If one is not currently loaded into
     * this pose sampler state container, then a new one is created from the key's default
     * value and loaded into this animation data container and returned.
     *
     * @param poseSamplerKey the {@link PoseSamplerKey} attached to the desired {@link PoseSampler}
     *
     * @return a {@link PoseSampler} object reference
     */
    @SuppressWarnings("unchecked")
    public <P extends PoseSampler> P getPoseSampler(PoseSamplerKey<P> poseSamplerKey){
        return (P) this.poseSamplers.computeIfAbsent(poseSamplerKey, PoseSamplerKey::constructPoseSampler);
    }

    public AnimationPose sample(PoseSamplerKey<? extends Sampleable> poseSamplerKey){
        return this.getPoseSampler(poseSamplerKey).sample(this.jointSkeleton);
    }

    public AnimationPose sample(PoseSamplerKey<? extends SampleableFromInput> poseSamplerKey, AnimationPose animationPose){
        return this.getPoseSampler(poseSamplerKey).sample(this.jointSkeleton, animationPose);
    }
}
