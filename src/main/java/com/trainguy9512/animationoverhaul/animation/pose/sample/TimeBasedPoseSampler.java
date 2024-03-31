package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.data.AnimationPoseSamplerKey;
import net.minecraft.stats.Stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeBasedPoseSampler extends PoseSampler {

    protected float timeElapsed;
    private float playRate;
    private boolean playing;
    private final HashMap<AnimationPoseSamplerKey<? extends AnimationStateMachine<?>>, List<AnimationStateMachine.StateEnum>> playFromStartOnActiveStates;
    private final HashMap<AnimationPoseSamplerKey<? extends AnimationStateMachine<?>>, List<AnimationStateMachine.StateEnum>> progressTimeOnActiveStates;

    protected TimeBasedPoseSampler(Builder<?> builder) {
        super(builder);
        this.timeElapsed = 0;
        this.playRate = builder.playRate;
        this.playing = builder.isPlaying;
        this.playFromStartOnActiveStates = builder.playFromStartOnActiveStates;
        this.progressTimeOnActiveStates = builder.progressTimeOnActiveStates;
    }

    public static Builder<?> of(){
        return new Builder<>();
    }


    public static class Builder<B extends Builder<B>> extends PoseSampler.Builder<B> {

        private float playRate = 1;
        private boolean isPlaying = true;
        private final HashMap<AnimationPoseSamplerKey<? extends AnimationStateMachine<?>>, List<AnimationStateMachine.StateEnum>> playFromStartOnActiveStates;
        private final HashMap<AnimationPoseSamplerKey<? extends AnimationStateMachine<?>>, List<AnimationStateMachine.StateEnum>> progressTimeOnActiveStates;

        protected Builder() {
            super();
            this.playFromStartOnActiveStates = Maps.newHashMap();
            this.progressTimeOnActiveStates = Maps.newHashMap();
        }

        @SuppressWarnings("unchecked")
        public B setPlayRate(float playRate){
            this.playRate = playRate;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setIsPlaying(boolean isPlaying){
            this.isPlaying = isPlaying;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public <S extends AnimationStateMachine.StateEnum> B addPlayFromStartOnActiveStates(AnimationPoseSamplerKey<? extends AnimationStateMachine<?>> animationStateMachineKey, AnimationStateMachine.StateEnum... states){
            this.playFromStartOnActiveStates.putIfAbsent( animationStateMachineKey, new ArrayList<>());
            for(AnimationStateMachine.StateEnum state : states){
                this.playFromStartOnActiveStates.get(animationStateMachineKey).add(state);
            }
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public <S extends AnimationStateMachine.StateEnum> B addProgressTimeOnActiveStates(AnimationPoseSamplerKey<? extends AnimationStateMachine<?>> animationStateMachineKey, AnimationStateMachine.StateEnum... states){
            this.progressTimeOnActiveStates.putIfAbsent( animationStateMachineKey, new ArrayList<>());
            for(AnimationStateMachine.StateEnum state : states){
                this.progressTimeOnActiveStates.get(animationStateMachineKey).add(state);
            }
            return (B) this;
        }

        @Override
        public TimeBasedPoseSampler build() {
            return new TimeBasedPoseSampler(this);
        }
    }

    public void setPlayRate(float playRate) {
        this.playRate = playRate;
    }

    public float getPlayRate(){
        return this.playRate;
    }

    public void setTimeElapsed(float timeElapsed){
        this.timeElapsed = timeElapsed;
    }

    public void resetTime(){
        this.setTimeElapsed(0);
    }

    public boolean getIsPlaying(){
        return this.playing;
    }

    public float getTimeElapsed(){
        return this.timeElapsed;
    }




    public void playFromStartOnStateActive(){
        if(!this.playFromStartOnActiveStates.isEmpty()) {
            for (AnimationPoseSamplerKey<? extends AnimationStateMachine<?>> animationStateMachineKey : this.playFromStartOnActiveStates.keySet()) {
                AnimationStateMachine<?> animationStateMachine = this.getAnimationDataContainer().getPoseSampler(animationStateMachineKey);

                for (AnimationStateMachine.StateEnum stateEnum : this.playFromStartOnActiveStates.get(animationStateMachineKey)) {
                    if (animationStateMachine.getActiveStates().contains(stateEnum)) {
                        return;
                    }
                }
            }
            this.resetTime();
        }
    }

    public void progressTimeIfStateActive(){
        if(!this.progressTimeOnActiveStates.isEmpty()){
            for(AnimationPoseSamplerKey<? extends AnimationStateMachine<?>> animationStateMachineKey : this.progressTimeOnActiveStates.keySet()){
                AnimationStateMachine<?> animationStateMachine = this.getAnimationDataContainer().getPoseSampler(animationStateMachineKey);

                for(AnimationStateMachine.StateEnum stateEnum : this.progressTimeOnActiveStates.get(animationStateMachineKey)){
                    if(animationStateMachine.getActiveStates().contains(stateEnum)){
                        this.playing = true;
                        return;
                    }
                }
            }
            this.playing = false;
        }
    }

    public <S extends AnimationStateMachine.StateEnum> void progressTimeIfStateActive(AnimationPoseSamplerKey<AnimationStateMachine<S>> animationStateMachineKey, S stateIdentifier){
        this.progressTimeIfStateActive(animationStateMachineKey, List.of(stateIdentifier));
    }

    public <S extends AnimationStateMachine.StateEnum> void progressTimeIfStateActive(AnimationPoseSamplerKey<AnimationStateMachine<S>> animationStateMachineKey, List<S> stateIdentifiers){
        boolean statesActive = false;
        AnimationStateMachine<S> animationStateMachine = this.getAnimationDataContainer().getPoseSampler(animationStateMachineKey);

        for(S stateIdentifier : stateIdentifiers){
            if(!statesActive){
                if(animationStateMachine.containsState(stateIdentifier)){
                    statesActive = animationStateMachine.getState(stateIdentifier).getIsActive();
                }
            }
        }

        this.playing = statesActive;
    }

    @Override
    public void tick(){
        progressTimeIfStateActive();
        if(this.getIsPlaying()){
            this.timeElapsed += this.playRate;
        }
        playFromStartOnStateActive();
    }
}
