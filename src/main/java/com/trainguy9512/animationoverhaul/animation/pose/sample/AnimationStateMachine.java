package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Enum S is for state definitions

public class AnimationStateMachine<S extends Enum<S>> extends TimeBasedPoseSampler {

    /**
     * The hashmap containing all the possible states, with the keys being enums.
     */
    private final HashMap<Enum<S>, State<S>> statesHashMap;

    /**
     * The list of enum keys that point to states with a blend value greater than 0
     */
    private final ArrayList<Enum<S>> activeStates;

    //private int timeElapsedInState = 0;

    private AnimationStateMachine(Builder<?, S> builder) {
        super(builder);
        this.statesHashMap = builder.statesHashMap;
        this.activeStates = builder.activeStates;
    }

    public static <S extends Enum<S>> Builder<?, S> of(String identifier, S[] states){
        return new Builder<>(states);
    }


    public static class Builder<B extends Builder<B, S>, S extends Enum<S>> extends TimeBasedPoseSampler.Builder<B> {

        private final HashMap<Enum<S>, State<S>> statesHashMap = Maps.newHashMap();
        private final ArrayList<Enum<S>> activeStates = new ArrayList<>();


        protected Builder(Enum<S>[] states) {
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
        public B addStateTransition(Enum<S> origin, Enum<S> destination, StateTransition stateTransition){
            this.statesHashMap.get(origin).addStateTransition(destination, stateTransition);
            return (B) this;
        }

        @Override
        public AnimationStateMachine<S> build() {
            return new AnimationStateMachine<>(this);
        }
    }

    public State getState(S stateIdentifier){
        return this.statesHashMap.get(stateIdentifier);
    }

    public Enum<S> getActiveState(){
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

    private <L extends Enum<L>> AnimationPose<L> getPoseFromState(Enum<S> identifier, JointSkeleton<L> jointSkeleton){
        return this.statesHashMap.get(identifier).getAnimationPose(jointSkeleton);
    }

    @Override
    public <L extends Enum<L>> AnimationPose<L> sample(JointSkeleton<L> jointSkeleton) {
        if(!this.activeStates.isEmpty()){
            AnimationPose<L> animationPose = this.getPoseFromState(this.activeStates.get(0), jointSkeleton);
            if(this.activeStates.size() > 1){
                for(Enum<S> stateIdentifier : this.activeStates){
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
    public void tick(){
        // Don't evaluate if the state machine has no states
        if(this.statesHashMap.isEmpty()){
            AnimationOverhaulMain.LOGGER.warn("State machine {} not evaluated due to no active states", this.getIdentifier());
            return;
        }

        // Add to the current elapsed ticks
        super.tick();

        // Get the previous active state
        Enum<S> currentActiveStateIdentifier = this.activeStates.get(this.activeStates.size() - 1);
        State<S> currentActiveState = this.statesHashMap.get(currentActiveStateIdentifier);

        // Determine if the current state can transition, and get that state transition object
        boolean canEnterTransition = false;
        StateTransition stateTransition = null;
        Enum<S> destinationStateIdentifier = null;
        for(Enum<S> stateIdentifier : currentActiveState.getTransitionTargets()){
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
        for(State<S> state : this.statesHashMap.values()){
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

    public static class State<S extends Enum<S>> {

        private boolean isActive;
        private StateTransition currentTransition;

        private AnimationPose<?> animationPose;

        private float weight = 0;
        private final HashMap<Enum<S>, StateTransition> stateTransitions = Maps.newHashMap();

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
        public <L extends Enum<L>> AnimationPose<L> getAnimationPose(JointSkeleton<L> jointSkeleton){
            return this.animationPose != null ? (AnimationPose<L>) this.animationPose : AnimationPose.of(jointSkeleton);
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

        public void addStateTransition(Enum<S> target, StateTransition transition){
            this.stateTransitions.put(target, transition);
        }

        public Set<Enum<S>> getTransitionTargets(){
            return this.stateTransitions.keySet();
        }

        @Nullable
        public StateTransition getTransition(Enum<S> identifier){
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
        //private final Enum<S> destinationStateIdentifier;

        private boolean condition = false;

        private StateTransition(Builder builder) {
            this.transitionTime = builder.transitionTime;
            this.easing = builder.easing;
            this.priority = builder.priority;
        }

        public static Builder of(){
            return new Builder();
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

        public static class Builder {
            private float transitionTime = 1;
            private Easing easing = Easing.Linear.of();
            private int priority = 1;

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
}
