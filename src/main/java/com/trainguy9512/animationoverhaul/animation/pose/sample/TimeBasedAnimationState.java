package com.trainguy9512.animationoverhaul.animation.pose.sample;

import java.util.List;

public class TimeBasedAnimationState extends SampleableAnimationState {

    private float timeElapsed = 0;
    private float playRate = 1;
    private boolean playing = true;

    public TimeBasedAnimationState(String identifier) {
        super(identifier);
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
