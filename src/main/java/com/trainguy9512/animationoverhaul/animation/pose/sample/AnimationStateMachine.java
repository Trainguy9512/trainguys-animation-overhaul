package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Enum S is for state defintions, Enum T is for transition definitions

public class AnimationStateMachine<S extends Enum<S>> extends TimeBasedAnimationState {

    private final HashMap<S, State> statesHashMap = Maps.newHashMap();
    private final ArrayList<S> activeStates = new ArrayList<>();

    //private int timeElapsedInState = 0;

    public AnimationStateMachine(String identifier){
        super(identifier);
    }

    /**
     * Creates an animation state machine
     *
     * @param identifier Unique identifier, must be unique between this and other sampleable animation sampleables present in the entity animator
     * @return The animation state machine
     */
    public static <S extends Enum<S>> AnimationStateMachine<S> of(String identifier, S[] states){
        return new AnimationStateMachine<S>(identifier).addStates(states);
    }

    public State getState(S stateIdentifier){
        return this.statesHashMap.get(stateIdentifier);
    }

    public S getActiveState(){
        return this.activeStates.get(this.activeStates.size() - 1);
    }

    public boolean containsState(Enum<S> stateIdentifier){
        return this.statesHashMap.containsKey(stateIdentifier);
    }

    /*
    private Set<Enum<S>> getAllTransitions(){
        HashSet<Enum<S>> transitions = new HashSet<>(Set.of());
        for(Enum<?> identifier : this.statesHashMap.keySet()){
            transitions.addAll(this.statesHashMap.get(identifier).getTransitionTargets());
        }
        return transitions;
    }
     */

    private boolean isValidTransition(Enum<S> origin, Enum<S> destination){
        for(Enum<S> originStateIdentifier : this.statesHashMap.keySet()){
            for(Enum<S> destinationStateIdentifier : this.statesHashMap.get(originStateIdentifier).getTransitionTargets()){
                if(origin == originStateIdentifier && destination == destinationStateIdentifier){
                    return true;
                }
            }
        }
        return false;
    }

    /*
    private boolean containsStateTransition(Enum<?> identifier){
        if(this.getAllTransitions().contains(identifier)){
            for(Enum<?> stateIdentifier : this.statesHashMap.keySet()){
                if(this.statesHashMap.get(stateIdentifier).getTransitionTargets().contains(identifier)){
                    return true;
                }
            }
        }
        return false;
    }

     */

    @Nullable
    private StateTransition getStateTransition(Enum<S> origin, Enum<S> destination){
        if(isValidTransition(origin, destination)){
            return this.statesHashMap.get(origin).getTransition(destination);
        }
        /*
        for(String stateIdentifier : this.statesHashMap.keySet()){
            if(this.statesHashMap.get(stateIdentifier).getTransitionTargets().contains(identifier)){
                return this.statesHashMap.get(stateIdentifier).getTransition(identifier);
            }
        }

         */
        AnimationOverhaulMain.LOGGER.error("Could not get state transition origin {} destination {} in state machine {}", origin, destination, this.getIdentifier());
        return null;
    }

    /**
     * Adds states to the state machine via a list of string identifiers, called on construction. The default identifier will be the first list entry
     *
     * @param identifiers List of identifiers
     * @return The animation state machine
     */
    private AnimationStateMachine<S> addStates(S[] identifiers){

        for(int i = 0; i < identifiers.length; i++){
            State state = new State(i == 0 ? 1 : 0);
            this.statesHashMap.put(identifiers[i], state);
            if(i == 0){
                this.activeStates.add(identifiers[i]);
            }
        }
        return this;
    }

    /**
     * Adds a potential transition between two states with specified transition time and easing. Must be called after states are defined in the constructor
     *
     * @param origin         Origin state identifer
     * @param desination     Destination state identifier
     * @param transitionTime Time in ticks to transition from origin to destination
     * @param easing         Easing function to use
     * @param priority       Priority in which states are chosen, in the case that multiple transitions are available. Lowest gets priority
     * @return The animation state machine
     */
    public AnimationStateMachine<S> addStateTransition(S origin, S desination, float transitionTime, Easing easing, int priority){
        this.statesHashMap.get(origin).addStateTransition(desination, new StateTransition(transitionTime, easing, priority));
        return this;
    }

    /**
     * Adds a potential transition between two states with linear easing and specified transition time. Must be called after states are defined in the constructor
     *
     * @param origin         Origin state identifer
     * @param desination     Destination state identifier
     * @param transitionTime Time in ticks to transition from origin to destination
     * @param priority       Priority in which states are chosen, in the case that multiple transitions are available. Lowest gets priority
     * @return The animation state machine
     */
    public AnimationStateMachine<S> addStateTransition(S origin, S desination, float transitionTime, int priority){
        return this.addStateTransition(origin, desination, transitionTime, Easing.Linear.of(), priority);
    }

    /**
     * Adds a potential transition between two states with linear easing and specified transition time. Must be called after states are defined in the constructor
     *
     * @param origin         Origin state identifer
     * @param desination     Destination state identifier
     * @param transitionTime Time in ticks to transition from origin to destination
     * @return The animation state machine
     */
    public AnimationStateMachine<S> addStateTransition(S origin, S desination, float transitionTime){
        return this.addStateTransition(origin, desination, transitionTime, Easing.Linear.of(), 50);
    }

    public AnimationStateMachine<S> setTransitionCondition(Enum<S> origin, Enum<S> destination, boolean condition){
        if(isValidTransition(origin, destination)){
            StateTransition stateTransition = this.getStateTransition(origin, destination);
            if(stateTransition != null){
                stateTransition.setCondition(condition);
            }
        }
        return this;
    }

    public <L extends Enum<L>> AnimationStateMachine<S> setPose(S identifier, AnimationPose<L> animationPose){
        if(this.statesHashMap.containsKey(identifier)){
            this.statesHashMap.get(identifier).setAnimationPose(animationPose);
        } else {
            AnimationOverhaulMain.LOGGER.error("Tried setting pose to invalid state {} in state machine {}", identifier, this.getIdentifier());
        }
        return this;
    }

    private <L extends Enum<L>> AnimationPose<L> getPoseFromState(Enum<S> identifier, LocatorSkeleton<L> locatorSkeleton){
        return (AnimationPose<L>) this.statesHashMap.get(identifier).getAnimationPose(locatorSkeleton);
    }

    @Override
    public <L extends Enum<L>> AnimationPose<L> sample(LocatorSkeleton<L> locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        if(this.activeStates.size() > 0){
            AnimationPose<L> animationPose = this.getPoseFromState(this.activeStates.get(0), locatorSkeleton);
            if(this.activeStates.size() > 1){
                for(Enum<S> stateIdentifier : this.activeStates){
                    animationPose.blend(
                            this.getPoseFromState(stateIdentifier, locatorSkeleton),
                            this.statesHashMap.get(stateIdentifier).getWeight(),
                            this.statesHashMap.get(stateIdentifier).getCurrentTransition().getEasing());
                }
            }
            //AnimationOverhaulMain.LOGGER.info(this.activeStates.toString());
            return animationPose;
        }
        AnimationOverhaulMain.LOGGER.warn("No active states in state machine {}", this.getIdentifier());
        return AnimationPose.of(locatorSkeleton);
    }

    @Override
    public void tick(){
        // Don't evaluate if the state machine has no states
        if(this.statesHashMap.size() == 0 || this.activeStates.size() == 0){
            AnimationOverhaulMain.LOGGER.warn("State machine {} not evaluated due to no active states", this.getIdentifier());
            return;
        }

        // Add to the current elapsed ticks
        super.tick();

        // Get the previous active state
        Enum<S> currentActiveStateIdentifier = this.activeStates.get(this.activeStates.size() - 1);
        State currentActiveState = this.statesHashMap.get(currentActiveStateIdentifier);

        // Determine if the current state can transition, and get that state transition object
        boolean canEnterTransition = false;
        StateTransition stateTransition = null;
        S destinationStateIdentifier = null;
        for(S stateIdentifier : currentActiveState.getTransitionTargets()){
            if(isValidTransition(currentActiveStateIdentifier, stateIdentifier)){
                StateTransition currentStateTransition = currentActiveState.getTransition(stateIdentifier);
                assert currentStateTransition != null;
                if(currentStateTransition.getCondition()){
                    // If the loop has already determined a state transition to be true, update the transition IF the new one has a higher priority
                    if(canEnterTransition && currentStateTransition.getPriority() < stateTransition.getPriority()){
                        stateTransition = currentStateTransition;
                        destinationStateIdentifier = stateIdentifier;
                    } else if(!canEnterTransition){
                        stateTransition = currentStateTransition;
                        destinationStateIdentifier = stateIdentifier;
                        canEnterTransition = true;
                    }
                }
            }
        }
        /*
        for(String stateTransitionIdentifier : currentActiveState.getTransitionTargets()){
            StateTransition stateTransitionTest = currentActiveState.getTransition(stateTransitionIdentifier);
            if(stateTransitionTest.getCondition()){
                if(canEnterTransition){
                    if(stateTransitionTest.getPriority() < stateTransition.getPriority()){
                        stateTransition = stateTransitionTest;
                    }
                } else {
                    stateTransition = stateTransitionTest;
                    canEnterTransition = true;
                }
            }
        }

         */

        // Set all states to inactive except the new destination state. Also set the transition to all states for when they're ticked
        if(canEnterTransition){
            this.resetTime();
            for(Enum<S> stateIdentifier : this.statesHashMap.keySet()){
                this.statesHashMap.get(stateIdentifier).setCurrentTransition(stateTransition);
                this.statesHashMap.get(stateIdentifier).setIsActive(false);
            }
            //Enum<S> destinationStateIdentifier = stateTransition.;
            this.statesHashMap.get(destinationStateIdentifier).setIsActive(true);

            // Update the active states array
            // Make sure there already isn't this state present in active states
            this.activeStates.remove(destinationStateIdentifier);
            this.activeStates.add(destinationStateIdentifier);
        }

        // Tick each state
        for(State state : this.statesHashMap.values()){
            state.tick();
        }

        // Evaluated last, remove states from the active state list that have a weight of 0.
        ArrayList<Enum<S>> statesToRemove = new ArrayList<>();
        for(Enum<S> stateIdentifier : this.activeStates){
            if(this.statesHashMap.get(stateIdentifier).getWeight() == 0){
                statesToRemove.add(stateIdentifier);
            }
        }
        for(Enum<S> stateIdentifier : statesToRemove){
            this.activeStates.remove(stateIdentifier);
        }
    }

    public String getActiveStatesDebugString(){
        return this.activeStates.toString();
    }

    public class State {

        private boolean isActive;
        private StateTransition currentTransition;

        private AnimationPose<?> animationPose;

        private float weight = 0;
        private final HashMap<S, StateTransition> stateTransitions = Maps.newHashMap();

        private State(float defaultWeight){
            this.weight = defaultWeight;
        }

        private void tick(){
            if(this.currentTransition != null){
                float increaseDecreaseMultiplier = this.getIsActive() ? 1 : -1;
                this.setWeight(Mth.clamp(this.getWeight() + ((1 / this.getCurrentTransition().transitionTime) * increaseDecreaseMultiplier), 0, 1));
            }
        }

        @Nullable
        public AnimationPose<?> getAnimationPose(LocatorSkeleton<?> locatorSkeleton){
            return this.animationPose != null ? this.animationPose : AnimationPose.of(locatorSkeleton);
        }

        public void setAnimationPose(AnimationPose<?> animationPose){
            this.animationPose = animationPose;
        }

        public boolean getIsActive(){
            return this.isActive;
        }

        public void setIsActive(boolean isActive){
            this.isActive = isActive;
        }

        public StateTransition getCurrentTransition(){
            return this.currentTransition;
        }

        public void setCurrentTransition(StateTransition stateTransition){
            this.currentTransition = stateTransition;
        }

        public float getWeight(){
            return this.weight;
        }

        public void setWeight(float weight){
            this.weight = weight;
        }

        public void addStateTransition(S target, StateTransition transition){
            this.stateTransitions.put(target, transition);
        }

        public Set<S> getTransitionTargets(){
            return this.stateTransitions.keySet();
        }

        @Nullable
        public StateTransition getTransition(Enum<?> identifier){
            if(this.stateTransitions.containsKey(identifier)){
                return this.stateTransitions.get(identifier);
            }
            return null;
        }

    }

    public class StateTransition {

        private final float transitionTime;
        private final Easing easing;
        private final int priority;
        //private final Enum<S> destinationStateIdentifier;

        private boolean condition = false;

        private StateTransition(float transitionTime, Easing easing, int priority) {
            this.transitionTime = transitionTime;
            this.easing = easing;
            this.priority = priority;
        }

        public Easing getEasing(){
            return this.easing;
        }

        public float getTransitionTime() {
            return this.transitionTime;
        }

        public void setCondition(boolean condition){
            this.condition = condition;
        }

        public boolean getCondition(){
            return this.condition;
        }

        public int getPriority(){
            return this.priority;
        }

    }
}
