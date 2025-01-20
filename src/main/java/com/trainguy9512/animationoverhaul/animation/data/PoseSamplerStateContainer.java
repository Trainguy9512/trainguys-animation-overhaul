package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.sample.PoseSampler;

import java.util.Collection;
import java.util.HashMap;

public class PoseSamplerStateContainer<L extends Enum<L>> {

    private final HashMap<PoseSamplerKey<?>, PoseSampler> poseSamplerMap;

    public PoseSamplerStateContainer() {
        this.poseSamplerMap = Maps.newHashMap();
    }

    /**
     * Returns this animation data container's hash map of pose sampler keys to pose samplers currently loaded.
     *
     * @return {@link HashMap} of {@link PoseSamplerKey} keys to {@link PoseSampler} values.
     */
    private HashMap<PoseSamplerKey<?>, PoseSampler> getPoseSamplerMap(){
        return this.poseSamplerMap;
    }

    /**
     * Returns a collection of every pose sampler currently loaded into this animation data container.
     *
     * @return {@link Collection} of {@link PoseSampler} values.
     */
    public Collection<PoseSampler> getPoseSamplers(){
        return this.getPoseSamplerMap().values();
    }

    /**
     * Iterates over every currently loaded pose sampler and executes the  method
     *
     * @implNote Only do this once per game tick! For entities, this is handled in the entity joint animator dispatcher.
     */
    public void tickAllPoseSamplers(){
        for(PoseSampler poseSampler : this.getPoseSamplers()){
            poseSampler.tick();
        }
    }

    /**
     * Returns a pose sampler from the given key. If one is not currently loaded into
     * this animation data container, then a new one is created from the key's default
     * value and loaded into this animation data container and returned.
     *
     * @param animationPoseSamplerKey the {@link PoseSamplerKey} attached to the desired {@link PoseSampler}
     *
     * @return a {@link PoseSampler} object reference
     */
    @SuppressWarnings("unchecked")
    public <P extends PoseSampler> P getPoseSampler(PoseSamplerKey<P> animationPoseSamplerKey){
        if(!this.getPoseSamplerMap().containsKey(animationPoseSamplerKey)){
            this.getPoseSamplerMap().put(animationPoseSamplerKey, animationPoseSamplerKey.constructPoseSampler());
        }
        return (P) this.getPoseSamplerMap().get(animationPoseSamplerKey);
    }
}
