package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

// Enum S is for state definitions

public class AnimationStateMachine<S extends AnimationStateMachine.StateEnum> extends TimeBasedPoseSampler implements Sampleable {

    /**
     * The hashmap containing all the possible states, with the keys being enums.
     */
    private final HashMap<S, State<S>> statesHashMap;

    /**
     * The list of enum keys that point to states with a blend value greater than 0
     */
    private final ArrayList<S> activeStates;

    //private int timeElapsedInState = 0;

    private AnimationStateMachine(Builder<?, S> builder) {
        super(builder);
        this.statesHashMap = builder.statesHashMap;
        this.activeStates = builder.activeStates;
    }

    public static <S extends StateEnum> Builder<?, S> of(String identifier, S[] states){
        return new Builder<>(states);
    }


    public static class Builder<B extends Builder<B, S>, S extends StateEnum> extends TimeBasedPoseSampler.Builder<B> {

        private final HashMap<S, State<S>> statesHashMap = Maps.newHashMap();
        private final ArrayList<S> activeStates = new ArrayList<>();


        protected Builder(S[] states) {
            super();

            for(int i = 0; i < states.length; i++){
                State<S> state = new State<>(i == 0 ? 1 : 0);
                this.statesHashMap.put(states[i], state);
            }
            this.activeStates.add(states[0]);
        }

        /**
         * Adds a potential transition between the specified origin state and destination state using the provided state transition
         *
         * @param origin                Origin state enum
         * @param destination           Destination state enum
         * @param stateTransition       The state transition object with parameters such as transition time, easing, and priority. Created using its builder class
         * @return The animation state machine builder
         * @see StateTransition.Builder
         */
        @SuppressWarnings("unchecked")
        public B addStateTransition(S origin, S destination, StateTransition stateTransition){
            this.statesHashMap.get(origin).addStateTransition(destination, stateTransition);
            return (B) this;
        }

        @Override
        public AnimationStateMachine<S> build() {
            return new AnimationStateMachine<>(this);
        }
    }

    public State<S> getState(S stateIdentifier){
        return this.statesHashMap.get(stateIdentifier);
    }

    public ArrayList<S> getActiveStates(){
        return this.activeStates;
    }

    public S getActiveState(){
        return this.activeStates.get(this.activeStates.size() - 1);
    }

    public boolean containsState(StateEnum stateIdentifier){
        for(S state : this.statesHashMap.keySet()){
            if(state.equals(stateIdentifier)){
                return true;
            }
        }
        return false;
        //return this.statesHashMap.containsKey(stateIdentifier);
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

    private boolean isValidTransition(S origin, S destination){
        for(S originStateIdentifier : this.statesHashMap.keySet()){
            for(S destinationStateIdentifier : this.statesHashMap.get(originStateIdentifier).getTransitionTargets()){
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
    private StateTransition getStateTransition(S origin, S destination){
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

    private AnimationPose getPoseFromState(S identifier, JointSkeleton jointSkeleton){
        BiFunction<AnimationDriverContainer, JointSkeleton, AnimationPose> biFunction = identifier.getStatePose();
        return biFunction.apply(this.getAnimationDataContainer(), jointSkeleton);
    }

    @Override
    public AnimationPose sample(JointSkeleton jointSkeleton) {
        if(!this.activeStates.isEmpty()){
            AnimationPose<L> animationPose = this.getPoseFromState(this.activeStates.get(0), jointSkeleton);
            if(this.activeStates.size() > 1){
                for(S stateIdentifier : this.activeStates){
                    animationPose.blend(
                            this.getPoseFromState(stateIdentifier, jointSkeleton),
                            this.statesHashMap.get(stateIdentifier).getWeight(),
                            this.statesHashMap.get(stateIdentifier).getCurrentTransition().getEasing());
                }
            }
            //AnimationOverhaulMain.LOGGER.info(this.activeStates.toString());
            return animationPose;
        }
        AnimationOverhaulMain.LOGGER.warn("No active states in state machine {}", this.getIdentifier());
        return AnimationPose.of(jointSkeleton);
    }

    @Override
    public void tick(AnimationDriverContainer animationDriverContainer){
        // Don't evaluate if the state machine has no states
        if(this.statesHashMap.isEmpty()){
            AnimationOverhaulMain.LOGGER.warn("State machine {} not evaluated due to no active states", this.getIdentifier());
            return;
        }

        // Add to the current elapsed ticks
        super.tick(animationDriverContainer);

        // Get the previous active state
        S currentActiveStateIdentifier = this.activeStates.getLast();
        State<S> currentActiveState = this.statesHashMap.get(currentActiveStateIdentifier);

        // Determine if the current state can transition, and get that state transition object
        boolean canEnterTransition = false;
        StateTransition stateTransition = null;
        S destinationStateIdentifier = null;
        for(S stateIdentifier : currentActiveState.getTransitionTargets()){
            if(isValidTransition(currentActiveStateIdentifier, stateIdentifier)){
                StateTransition currentStateTransition = currentActiveState.getTransition(stateIdentifier);
                assert currentStateTransition != null;
                if(currentStateTransition.getCondition(animationDriverContainer)){
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
            for(S stateIdentifier : this.statesHashMap.keySet()){
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
        for(State<S> state : this.statesHashMap.values()){
            state.tick();
        }

        // Evaluated last, remove states from the active state list that have a weight of 0.
        ArrayList<S> statesToRemove = new ArrayList<>();
        for(S stateIdentifier : this.activeStates){
            if(this.statesHashMap.get(stateIdentifier).getWeight() == 0){
                statesToRemove.add(stateIdentifier);
            }
        }
        for(S stateIdentifier : statesToRemove){
            this.activeStates.remove(stateIdentifier);
        }
    }

    public String getActiveStatesDebugString(){
        return this.activeStates.toString();
    }

    @Override
    public UpdateOrder getUpdateOrder(){
        return UpdateOrder.STATE_MACHINES;
    }

    public static class State<S extends StateEnum> {

        private boolean isActive;
        private StateTransition currentTransition;

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
        public StateTransition getTransition(S identifier){
            if(this.stateTransitions.containsKey(identifier)){
                return this.stateTransitions.get(identifier);
            }
            return null;
        }

    }

    public static class StateTransition {

        private final float transitionTime;
        private final Easing easing;
        private final int priority;
        private final Predicate<AnimationDriverContainer> conditionPredicate;
        //private final Enum<S> destinationStateIdentifier;

        //private boolean condition = false;

        private StateTransition(Builder builder) {
            this.transitionTime = builder.transitionTime;
            this.easing = builder.easing;
            this.priority = builder.priority;
            this.conditionPredicate = builder.conditionPredicate;
        }

        public static Builder of(Predicate<AnimationDriverContainer> stateConditionPredicate){
            return new Builder(stateConditionPredicate);
        }

        public Easing getEasing(){
            return this.easing;
        }

        public float getTransitionTime() {
            return this.transitionTime;
        }

        public boolean getCondition(AnimationDriverContainer animationDriverContainer){
            return this.conditionPredicate.test(animationDriverContainer);
        }

        public int getPriority(){
            return this.priority;
        }

        public static class Builder {
            private float transitionTime = 1;
            private Easing easing = Easing.Linear.of();
            private int priority = 1;
            private final Predicate<AnimationDriverContainer> conditionPredicate;

            private Builder(Predicate<AnimationDriverContainer> conditionPredicate){
                this.conditionPredicate = conditionPredicate;
            }

            /**
             * Sets the transition time for the state transition
             *
             * @param transitionTime Transition time, in ticks
             * @return The state transition builder
             */
            public Builder setTransitionTime(float transitionTime){
                this.transitionTime = transitionTime;
                return this;
            }

            /**
             * Sets the easing for the state transition
             *
             * @param easing         Easing function
             * @return The state transition builder
             */
            public Builder setEasing(Easing easing){
                this.easing = easing;
                return this;
            }

            /**
             * Sets the transition priority for the state transition, for when multiple transitions originating from one state are simultaneously active. Lower integers specify a lower priority
             *
             * @param priority      Priority integer
             * @return The animation state machine
             */
            public Builder setPriority(int priority){
                this.priority = priority;
                return this;
            }

            public StateTransition build(){
                return new StateTransition(this);
            }
        }
    }

    public interface StateEnum {
        BiFunction<AnimationDriverContainer, JointSkeleton, AnimationPose> getStatePose();
    }
}
