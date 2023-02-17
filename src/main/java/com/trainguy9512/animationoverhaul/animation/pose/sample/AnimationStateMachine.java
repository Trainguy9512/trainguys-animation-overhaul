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

public class AnimationStateMachine extends TimeBasedAnimationState {

    private final HashMap<String, State> statesHashMap = Maps.newHashMap();
    private final ArrayList<String> activeStates = new ArrayList<String>();

    //private int timeElapsedInState = 0;

    private AnimationStateMachine(String identifier){
        super(identifier);
    }

    /**
     * Creates an animation state machine
     *
     * @param identifier Unique identifier, must be unique between this and other sampleable animation sampleables present in the entity animator
     * @return The animation state machine
     */
    public static AnimationStateMachine of(String identifier){
        return new AnimationStateMachine(identifier);
    }

    @Nullable
    public State getState(String stateIdentifier){
        return this.statesHashMap.get(stateIdentifier);
    }

    public boolean containsState(String stateIdentifier){
        return this.statesHashMap.containsKey(stateIdentifier);
    }

    private Set<String> getAllTransitions(){
        HashSet<String> transitions = new HashSet<>(Set.of());
        for(String identifier : this.statesHashMap.keySet()){
            transitions.addAll(this.statesHashMap.get(identifier).getTransitions());
        }
        return transitions;
    }

    private boolean containsStateTransition(String identifier){
        if(this.getAllTransitions().contains(identifier)){
            for(String stateIdentifier : this.statesHashMap.keySet()){
                if(this.statesHashMap.get(stateIdentifier).getTransitions().contains(identifier)){
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    private StateTransition getStateTransition(String identifier){
        for(String stateIdentifier : this.statesHashMap.keySet()){
            if(this.statesHashMap.get(stateIdentifier).getTransitions().contains(identifier)){
                return this.statesHashMap.get(stateIdentifier).getTransition(identifier);
            }
        }
        AnimationOverhaulMain.LOGGER.error("Could not get state transition {} in state machine {}", identifier, this.getIdentifier());
        return null;
    }

    /**
     * Adds states to the state machine via a list of string identifiers, called on construction. The default identifier will be the first list entry
     *
     * @param identifiers       List of identifiers
     * @return The animation state machine
     */
    public AnimationStateMachine addStates(List<String> identifiers){
        for(int i = 0; i < identifiers.size(); i++){
            State state = new State(i == 0 ? 1 : 0);
            this.statesHashMap.put(identifiers.get(i), state);
            if(i == 0){
                this.activeStates.add(identifiers.get(i));
            }
        }
        return this;
    }

    /**
     * Sets the default state on construction
     *
     * @param identifier Identifier of default state. Must have already been added in construction
     * @return The animation state machine
     */
    public AnimationStateMachine setDefaultState(String identifier){
        if(this.statesHashMap.containsKey(identifier)){
            this.activeStates.clear();
            this.activeStates.add(identifier);
            for(String identifier1 : this.statesHashMap.keySet()){
                this.statesHashMap.get(identifier1).setWeight(Objects.equals(identifier1, identifier) ? 1 : 0);
            }
        } else {
            AnimationOverhaulMain.LOGGER.error("Failed to set default state for state machine {} due to state {} not being present", this.getIdentifier(), identifier);
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
    public AnimationStateMachine addStateTransition(String identifier, String origin, String desination, float transitionTime, Easing easing, int priority){
        if(this.statesHashMap.containsKey(origin) && this.statesHashMap.containsKey(origin)){
            this.statesHashMap.get(origin).addStateTransition(identifier, new StateTransition(transitionTime, easing, priority, desination));
        } else {
            AnimationOverhaulMain.LOGGER.error("Tried adding a state transition to state machine {} which contains invalid origin {} or destination {}", this.getIdentifier(), origin, desination);
        }
        return this;
    }

    /**
     * Adds a potential transition between two states with linear easing and specified transition time. Must be called after states are defined in the constructor
     *
     * @param identifier     Transition identifier
     * @param origin         Origin state identifer
     * @param desination     Destination state identifier
     * @param transitionTime Time in ticks to transition from origin to destination
     * @param priority       Priority in which states are chosen, in the case that multiple transitions are available. Lowest gets priority
     * @return The animation state machine
     */
    public AnimationStateMachine addStateTransition(String identifier, String origin, String desination, float transitionTime, int priority){
        return this.addStateTransition(identifier, origin, desination, transitionTime, Easing.Linear.of(), priority);
    }

    /**
     * Adds a potential transition between two states with linear easing and specified transition time. Must be called after states are defined in the constructor
     *
     * @param identifier     Transition identifier
     * @param origin         Origin state identifer
     * @param desination     Destination state identifier
     * @param transitionTime Time in ticks to transition from origin to destination
     * @return The animation state machine
     */
    public AnimationStateMachine addStateTransition(String identifier, String origin, String desination, float transitionTime){
        return this.addStateTransition(identifier, origin, desination, transitionTime, Easing.Linear.of(), 50);
    }

    public AnimationStateMachine setTransitionCondition(String transitionIdentifier, boolean condition){
        if(this.getAllTransitions().contains(transitionIdentifier)){
            if(this.containsStateTransition(transitionIdentifier)){
                StateTransition stateTransition = this.getStateTransition(transitionIdentifier);
                if(stateTransition != null){
                    stateTransition.setCondition(condition);
                } else {
                    AnimationOverhaulMain.LOGGER.error("State transition {} still returned null. ???", transitionIdentifier);
                }
            }
        } else {
            AnimationOverhaulMain.LOGGER.error("Cannot set state transition condition, transition identifier {} not found in state machine {}", transitionIdentifier, this.getIdentifier());
        }
        return this;
    }

    public void setPose(String stateIdentifier, AnimationPose animationPose){
        if(this.statesHashMap.containsKey(stateIdentifier)){
            this.statesHashMap.get(stateIdentifier).setAnimationPose(animationPose);
        } else {
            AnimationOverhaulMain.LOGGER.error("Tried setting pose to invalid state {} in state machine {}", stateIdentifier, this.getIdentifier());
        }
    }

    private AnimationPose getPoseFromState(String stateIdentifier, LocatorSkeleton locatorSkeleton){
        AnimationPose animationPose = this.statesHashMap.get(stateIdentifier).getAnimationPose();
        if(animationPose != null) {
            return animationPose;
        }
        AnimationOverhaulMain.LOGGER.warn("Animation state {} returned an invalid pose", stateIdentifier);
        return new AnimationPose(locatorSkeleton);
    }

    @Override
    public AnimationPose sample(LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer) {
        if(this.activeStates.size() > 0){
            AnimationPose animationPose = this.getPoseFromState(this.activeStates.get(0), locatorSkeleton);
            if(this.activeStates.size() > 1){
                for(String stateIdentifier : this.activeStates){
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
        return new AnimationPose(locatorSkeleton);
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
        State currentActiveState = this.statesHashMap.get(this.activeStates.get(this.activeStates.size() - 1));

        // Determine if the current state can transition, and get that state transition object
        boolean canEnterTransition = false;
        StateTransition stateTransition = null;
        for(String stateTransitionIdentifier : currentActiveState.getTransitions()){
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

        // Set all states to inactive except the new destination state. Also set the transition to all states for when they're ticked
        if(canEnterTransition){
            this.resetTime();
            for(String stateIdentifier : this.statesHashMap.keySet()){
                this.statesHashMap.get(stateIdentifier).setCurrentTransition(stateTransition);
                this.statesHashMap.get(stateIdentifier).setIsActive(false);
            }
            String destinationStateIdentifier = stateTransition.destinationStateIdentifier;
            this.statesHashMap.get(destinationStateIdentifier).setIsActive(true);

            // Update the active states array
            // Make sure there already isn't this state present in active states
            if(activeStates.contains(destinationStateIdentifier)){
                activeStates.remove(destinationStateIdentifier);
            }
            this.activeStates.add(destinationStateIdentifier);
        }

        // Tick each state
        for(State state : this.statesHashMap.values()){
            state.tick();
        }

        // Evaluated last, remove states from the active state list that have a weight of 0.
        ArrayList<String> statesToRemove = new ArrayList<>();
        for(String stateIdentifier : this.activeStates){
            if(this.statesHashMap.get(stateIdentifier).getWeight() == 0){
                statesToRemove.add(stateIdentifier);
            }
        }
        for(String stateIdentifier : statesToRemove){
            this.activeStates.remove(stateIdentifier);
        }
    }

    public String getActiveStatesDebugString(){
        return this.activeStates.toString();
    }

    public class State {

        private boolean isActive;
        private StateTransition currentTransition;

        private AnimationPose animationPose;

        private float weight;
        private final HashMap<String, StateTransition> stateTransitions = Maps.newHashMap();

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
        public AnimationPose getAnimationPose(){
            return this.animationPose;
        }

        public void setAnimationPose(AnimationPose animationPose){
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

        public void addStateTransition(String identifier, StateTransition transition){
            this.stateTransitions.put(identifier, transition);
        }

        public Set<String> getTransitions(){
            return this.stateTransitions.keySet();
        }

        @Nullable
        public StateTransition getTransition(String identifier){
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
        private final String destinationStateIdentifier;

        private boolean condition = false;

        private StateTransition(float transitionTime, Easing easing, int priority, String destinationStateIdentifier) {
            this.transitionTime = transitionTime;
            this.easing = easing;
            this.priority = priority;
            this.destinationStateIdentifier = destinationStateIdentifier;
        }

        public Easing getEasing(){
            return this.easing;
        }

        public float getTransitionTime() {
            return this.transitionTime;
        }

        public String getDestinationStateIdentifier(){
            return this.destinationStateIdentifier;
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
