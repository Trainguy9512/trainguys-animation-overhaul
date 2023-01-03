package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;

import java.util.HashMap;

public class AnimationStateMachine extends SampleableAnimationState {

    private final HashMap<String, State> statesHashMap = Maps.newHashMap();

    private AnimationStateMachine(String identifier){
        super(identifier);
    }

    public static AnimationStateMachine of(String identifier){
        return new AnimationStateMachine(identifier);
    }

    public AnimationStateMachine addState(String identifier, State state){
        this.statesHashMap.put(identifier, state);
        return this;
    }

    public AnimationPose sample(LocatorSkeleton locatorSkeleton) {
        return new AnimationPose(locatorSkeleton);
    }

    public void tick(){

    }

    public class State {

    }

    public class StateTransition {
        private StateTransition(){

        }
    }
}
