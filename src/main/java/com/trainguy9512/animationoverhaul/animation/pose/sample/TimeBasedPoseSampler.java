package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.data.AnimationPoseSamplerKey;

import java.util.List;

public class TimeBasedPoseSampler extends PoseSampler {

    protected float timeElapsed;
    private float playRate;
    private boolean playing;

    protected TimeBasedPoseSampler(Builder<?> builder) {
        super(builder);
        this.timeElapsed = 0;
        this.playRate = builder.playRate;
        this.playing = builder.isPlaying;
    }

    public static Builder<?> of(){
        return new Builder<>();
    }


    public static class Builder<B extends Builder<B>> extends PoseSampler.Builder<B> {

        private float playRate = 1;
        private boolean isPlaying = true;

        protected Builder() {
            super();
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


    //TODO: Make this part of the configuration

    public <S extends AnimationStateMachine.StateEnum> void playFromStartOnStateActive(AnimationPoseSamplerKey<AnimationStateMachine<S>> animationStateMachineKey, S stateIdentifier){
        this.playFromStartOnStateActive(animationStateMachineKey, List.of(stateIdentifier));
    }

    public <S extends AnimationStateMachine.StateEnum> void playFromStartOnStateActive(AnimationPoseSamplerKey<AnimationStateMachine<S>> animationStateMachineKey, List<S> stateIdentifiers){
        boolean statesActive = false;
        AnimationStateMachine<S> animationStateMachine = this.getAnimationDataContainer().getPoseSampler(animationStateMachineKey);

        for(S stateIdentifier : stateIdentifiers){
            if(!statesActive){
                if(animationStateMachine.containsState(stateIdentifier)){
                    statesActive = animationStateMachine.getState(stateIdentifier).getWeight() != 0;
                }
            }
        }
        if(!statesActive){
            this.resetTime();
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
        if(this.getIsPlaying()){
            this.timeElapsed += this.playRate;
        }
    }
}
