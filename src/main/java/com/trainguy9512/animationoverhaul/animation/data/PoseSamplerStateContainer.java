package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.sample.AnimationStateMachine;
import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;

import java.util.Collection;
import java.util.HashMap;

public class PoseSamplerStateContainer {

    private final HashMap<PoseSamplerKey<?>, PoseSampler> poseSamplers;
    private final HashMap<PoseSamplerKey<AnimationStateMachine<?>>, AnimationStateMachine> stateMachines;

    public PoseSamplerStateContainer() {
        this.poseSamplers = Maps.newHashMap();
        this.stateMachines = Maps.newHashMap();
    }

    /**
     * Iterates over every currently loaded pose sampler and executes the {@link PoseSampler#tick(AnimationDataContainer, PoseSamplerStateContainer)} method
     * <p>
     * The update order is as follows: State machines first, then all others.
     *
     * @param animationDataContainer Extracted animation data
     * @implNote Only do this once per game tick! For entities, this is handled in the entity joint animator dispatcher.
     */
    public void tick(AnimationDataContainer animationDataContainer){
        this.tickUpdateOrderGroup(animationDataContainer, PoseSampler.UpdateOrder.STATE_MACHINES);
        this.tickUpdateOrderGroup(animationDataContainer, PoseSampler.UpdateOrder.OTHER);
    }

    private void tickUpdateOrderGroup(AnimationDataContainer animationDataContainer, PoseSampler.UpdateOrder updateOrder){
        this.poseSamplers.values().stream()
                .filter((poseSampler -> poseSampler.getUpdateOrder() == updateOrder))
                .forEach((poseSampler -> poseSampler.tick(animationDataContainer, this)));
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
        this.poseSamplers.putIfAbsent(poseSamplerKey, poseSamplerKey.constructPoseSampler());
        return (P) this.poseSamplers.get(poseSamplerKey);
    }
}
