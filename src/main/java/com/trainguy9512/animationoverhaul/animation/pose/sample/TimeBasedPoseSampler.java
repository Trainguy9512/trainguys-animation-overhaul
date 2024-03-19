package com.trainguy9512.animationoverhaul.animation.pose.sample;

import java.util.List;

public class TimeBasedPoseSampler extends PoseSampler {

    private float timeElapsed;
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


    public <S extends Enum<S>> void playFromStartOnStateActive(AnimationStateMachine<S> animationStateMachine, S stateIdentifier){
        this.playFromStartOnStateActive(animationStateMachine, List.of(stateIdentifier));
    }

    public <S extends Enum<S>> void playFromStartOnStateActive(AnimationStateMachine<S> animationStateMachine, List<S> stateIdentifiers){
        boolean statesActive = false;
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

    public <S extends Enum<S>> void progressTimeIfStateActive(AnimationStateMachine<S> animationStateMachine, S stateIdentifier){
        this.progressTimeIfStateActive(animationStateMachine, List.of(stateIdentifier));
    }

    public <S extends Enum<S>> void progressTimeIfStateActive(AnimationStateMachine<S> animationStateMachine, List<S> stateIdentifiers){
        boolean statesActive = false;
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
