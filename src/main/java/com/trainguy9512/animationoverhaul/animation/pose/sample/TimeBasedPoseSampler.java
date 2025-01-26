package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerKey;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeBasedPoseSampler extends PoseSampler {

    protected float timeElapsed;
    private float playRate;
    private boolean playing;
    private final HashMap<PoseSamplerKey<? extends AnimationStateMachine<?>>, List<AnimationStateMachine.StateEnum>> playFromStartOnActiveStates;
    private final HashMap<PoseSamplerKey<? extends AnimationStateMachine<?>>, List<AnimationStateMachine.StateEnum>> progressTimeOnActiveStates;

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
        private final HashMap<PoseSamplerKey<? extends AnimationStateMachine<?>>, List<AnimationStateMachine.StateEnum>> playFromStartOnActiveStates;
        private final HashMap<PoseSamplerKey<? extends AnimationStateMachine<?>>, List<AnimationStateMachine.StateEnum>> progressTimeOnActiveStates;

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
        public <S extends AnimationStateMachine.StateEnum> B addPlayFromStartOnActiveStates(PoseSamplerKey<? extends AnimationStateMachine<?>> animationStateMachineKey, AnimationStateMachine.StateEnum... states){
            this.playFromStartOnActiveStates.putIfAbsent( animationStateMachineKey, new ArrayList<>());
            for(AnimationStateMachine.StateEnum state : states){
                this.playFromStartOnActiveStates.get(animationStateMachineKey).add(state);
            }
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public <S extends AnimationStateMachine.StateEnum> B addProgressTimeOnActiveStates(PoseSamplerKey<? extends AnimationStateMachine<?>> animationStateMachineKey, AnimationStateMachine.StateEnum... states){
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


    /**
     * Iterates over all state machines/state enums listed for playing from start on state active, and if the state is currently not active then it repeatedly resets until the state is active.
     */
    public void playFromStartOnStateActive(PoseSamplerStateContainer poseSamplerStateContainer){
        if(!this.playFromStartOnActiveStates.isEmpty()) {
            for (PoseSamplerKey<? extends AnimationStateMachine<?>> animationStateMachineKey : this.playFromStartOnActiveStates.keySet()) {
                AnimationStateMachine<?> animationStateMachine = poseSamplerStateContainer.getPoseSampler(animationStateMachineKey);

                for (AnimationStateMachine.StateEnum stateEnum : this.playFromStartOnActiveStates.get(animationStateMachineKey)) {
                    if (animationStateMachine.getActiveStates().contains(stateEnum)) {
                        return;
                    }
                }
            }
            this.resetTime();
        }
    }

    /**
     * Iterates over all state machine/state enums listed for progressing time on state active, and if any of the states are active then time is progressed.
     */
    public void progressTimeIfStateActive(PoseSamplerStateContainer poseSamplerStateContainer){
        if(!this.progressTimeOnActiveStates.isEmpty()){
            for(PoseSamplerKey<? extends AnimationStateMachine<?>> animationStateMachineKey : this.progressTimeOnActiveStates.keySet()){
                AnimationStateMachine<?> animationStateMachine = poseSamplerStateContainer.getPoseSampler(animationStateMachineKey);

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

    @Override
    public void tick(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
        progressTimeIfStateActive(poseSamplerStateContainer);
        if(this.getIsPlaying()){
            this.timeElapsed += this.playRate;
        }
        playFromStartOnStateActive(poseSamplerStateContainer);
    }
}
