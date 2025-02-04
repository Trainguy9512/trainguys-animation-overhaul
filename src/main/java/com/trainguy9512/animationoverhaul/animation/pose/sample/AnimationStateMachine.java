package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.sample.notify.AnimNotify;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Enum S is for state definitions

/**
 * Transitions between a series of states each with their own poses loaded
 * @param <S> Enum state identifiers
 */
public class AnimationStateMachine<S extends Enum<S>> extends TimeBasedPoseSampler implements Sampleable {

    /**
     * The hashmap containing all the possible states, with the keys being enums.
     */
    private final HashMap<S, State<S>> statesHashMap;

    /**
     * The list of enum keys that point to states with a blend value greater than 0
     */
    private final ArrayList<S> activeStates;

    private AnimationStateMachine(Builder<?, S> builder) {
        super(builder);
        this.statesHashMap = builder.statesHashMap;
        this.activeStates = builder.activeStates;
    }

    public static <S extends Enum<S>> Builder<?, S> builder(S[] states){
        return new Builder<>();
    }


    public static class Builder<B extends Builder<B, S>, S extends Enum<S>> extends TimeBasedPoseSampler.Builder<B> {

        private final HashMap<S, State<S>> statesHashMap;
        private final ArrayList<S> activeStates;


        protected Builder() {
            super();
            this.statesHashMap = Maps.newHashMap();
            this.activeStates = new ArrayList<>();
        }

        /**
         * Adds a potential transition between the specified origin state and destination state using the provided state transition
         *
         * @param origin                Origin state enum
         * @param destination           Destination state enum
         * @param stateTransition       The state transition object with parameters such as transition time, easing, and priority. Created using its builder class
         * @return                      This animation state machine builder
         * @see StateTransition.Builder
         */
        @Deprecated
        @SuppressWarnings("unchecked")
        public B addStateTransition(S origin, S destination, StateTransition stateTransition){
            this.statesHashMap.get(origin).addStateTransition(destination, stateTransition);
            return (B) this;
        }

        /**
         * Adds a state to the state machine builder with its outgoing transitions.
         *
         * @param stateIdentifier       Enum identifier that is associated with the state machine's enum type
         * @param sampleable            Functional interface for sampling a pose from the state
         * @param stateTransitions      Outbound transition paths from this state to other states
         * @return                      This animation state machine builder
         * @see StateTransition.Builder
         */
        @SuppressWarnings("unchecked")
        public B addState(S stateIdentifier, Sampleable sampleable, StateTransition... stateTransitions){
            State<S> state = new State<>(this.statesHashMap.isEmpty(), sampleable);
            for(StateTransition stateTransition : stateTransitions){
                state.addStateTransition(stateIdentifier, stateTransition);
            }
            if (this.statesHashMap.isEmpty()){
                this.activeStates.add(stateIdentifier);
            }
            return (B) this;
        }

        /**
         * Binds an anim notify to fire every time a state's weight goes from 0 to greater than 0
         * @param stateIdentifier       State to bind anim notify to. Must already be added to builder.
         * @param animNotify            Functional interface to fire on the notify call.
         */
        @SuppressWarnings("unchecked")
        public B bindAnimNotifyOnStateRelevant(S stateIdentifier, AnimNotify animNotify){
            if(this.statesHashMap.containsKey(stateIdentifier)){
                this.statesHashMap.get(stateIdentifier).bindAnimNotifyOnRelevant(animNotify);
            }
            return (B) this;
        }

        /**
         * Binds an anim notify to fire every time a state's weight goes from greater than 0 to 0
         * @param stateIdentifier       State to bind anim notify to. Must already be added to builder.
         * @param animNotify            Functional interface to fire on the notify call.
         */
        @SuppressWarnings("unchecked")
        public B bindAnimNotifyOnStateIrrelevant(S stateIdentifier, AnimNotify animNotify){
            if(this.statesHashMap.containsKey(stateIdentifier)){
                this.statesHashMap.get(stateIdentifier).bindAnimNotifyOnIrrelevant(animNotify);
            }
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
        return this.activeStates.getLast();
    }

    public boolean containsState(Enum<S> stateIdentifier){
        for(S state : this.statesHashMap.keySet()){
            if(state.equals(stateIdentifier)){
                return true;
            }
        }
        return false;
        //return this.statesHashMap.containsKey(stateIdentifier);
    }

    private boolean isValidTransition(S origin, S destination){
        if(this.statesHashMap.containsKey(origin) && this.statesHashMap.containsKey(destination)){
            for(S originStateIdentifier : this.statesHashMap.keySet()){
                for(S destinationStateIdentifier : this.statesHashMap.get(originStateIdentifier).getTransitionTargets()){
                    if(origin == originStateIdentifier && destination == destinationStateIdentifier){
                        return true;
                    }
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

    private AnimationPose getPoseFromState(S identifier, AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton){
        return this.statesHashMap.get(identifier).sample(animationDriverContainer, poseSamplerStateContainer, jointSkeleton);
    }

    @Override
    public AnimationPose sample(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton) {
        if(!this.activeStates.isEmpty()){
            AnimationPose animationPose = this.getPoseFromState(this.activeStates.getFirst(), animationDriverContainer, poseSamplerStateContainer, jointSkeleton);
            if(this.activeStates.size() > 1){
                for(S stateIdentifier : this.activeStates){
                    animationPose.blend(
                            this.getPoseFromState(stateIdentifier, animationDriverContainer, poseSamplerStateContainer, jointSkeleton),
                            this.statesHashMap.get(stateIdentifier).getWeight(),
                            this.statesHashMap.get(stateIdentifier).getCurrentTransition().easing());
                }
            }
            //AnimationOverhaulMain.LOGGER.info(this.activeStates.toString());
            return animationPose;
        }
        AnimationOverhaulMain.LOGGER.warn("No active states in state machine {}", this.getIdentifier());
        return AnimationPose.of(jointSkeleton);
    }

    @Override
    public void tick(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
        // Don't evaluate if the state machine has no states
        if(this.statesHashMap.isEmpty()){
            AnimationOverhaulMain.LOGGER.warn("State machine {} not evaluated due to no active states", this.getIdentifier());
            return;
        }

        // Add to the current elapsed ticks
        super.tick(animationDriverContainer, poseSamplerStateContainer);

        // Get the previous active state
        S currentActiveStateIdentifier = this.activeStates.getLast();
        State<S> currentActiveState = this.statesHashMap.get(currentActiveStateIdentifier);

        // Determine if the current state can transition, and get that state transition object
        // TODO: Rewrite this to have transitions of equal priority fire randomly.
        boolean canEnterTransition = false;
        StateTransition stateTransition = null;
        S destinationStateIdentifier = null;
        for(S stateIdentifier : currentActiveState.getTransitionTargets()){
            if(isValidTransition(currentActiveStateIdentifier, stateIdentifier)){
                StateTransition currentStateTransition = currentActiveState.getTransition(stateIdentifier);
                assert currentStateTransition != null;
                if(currentStateTransition.conditionPredicate().test(animationDriverContainer, this.getTimeElapsed())){
                    // If the loop has already determined a state transition to be true, update the transition IF the new one has a higher priority
                    if(canEnterTransition && currentStateTransition.priority() < stateTransition.priority()){
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
            state.tick(animationDriverContainer, poseSamplerStateContainer);
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
    public UpdateCategory getUpdateCategory(){
        return UpdateCategory.STATE_MACHINES;
    }

    public static class State<S extends Enum<S>> {

        private final Sampleable sampleable;
        private final HashMap<S, StateTransition> stateTransitions;

        private AnimNotify onStateRelevantAnimNotify = null;
        private AnimNotify onStateIrrelevantAnimNotify = null;

        private boolean isActive;
        private float weight;
        private StateTransition currentTransition;

        private State(boolean isActive, Sampleable sampleable){
            this.isActive = isActive;
            this.weight = isActive ? 1 : 0;
            this.sampleable = sampleable;
            this.stateTransitions = Maps.newHashMap();
        }

        private void tick(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
            if(this.currentTransition != null){
                float increaseDecreaseMultiplier = this.getIsActive() ? 1 : -1;
                float newWeight = Mth.clamp(this.getWeight() + ((1 / this.getCurrentTransition().transitionDuration) * increaseDecreaseMultiplier), 0, 1);

                // Notify calls
                if(newWeight > 0 && this.getWeight() == 0){
                    this.onStateRelevantAnimNotify.notify(animationDriverContainer, poseSamplerStateContainer);
                }
                if(newWeight == 0 && this.getWeight() > 0){
                    this.onStateIrrelevantAnimNotify.notify(animationDriverContainer, poseSamplerStateContainer);
                }

                this.setWeight(newWeight);
            }
        }

        private boolean getIsActive(){
            return this.isActive;
        }

        private void setIsActive(boolean isActive){
            this.isActive = isActive;
        }

        private StateTransition getCurrentTransition(){
            return this.currentTransition;
        }

        private void setCurrentTransition(StateTransition stateTransition){
            this.currentTransition = stateTransition;
        }

        private float getWeight(){
            return this.weight;
        }

        private void setWeight(float weight){
            this.weight = weight;
        }

        private void addStateTransition(S target, StateTransition transition){
            this.stateTransitions.put(target, transition);
        }

        private Set<S> getTransitionTargets(){
            return this.stateTransitions.keySet();
        }

        @Nullable
        public StateTransition getTransition(S identifier){
            if(this.stateTransitions.containsKey(identifier)){
                return this.stateTransitions.get(identifier);
            }
            return null;
        }

        private AnimationPose sample(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton){
            return this.sampleable.sample(animationDriverContainer, poseSamplerStateContainer, jointSkeleton);
        }

        private void bindAnimNotifyOnRelevant(@NotNull AnimNotify animNotify){
            this.onStateRelevantAnimNotify = AnimNotify.Multi.add(this.onStateRelevantAnimNotify, animNotify);
        }

        private void bindAnimNotifyOnIrrelevant(@NotNull AnimNotify animNotify){
            this.onStateIrrelevantAnimNotify = AnimNotify.Multi.add(this.onStateIrrelevantAnimNotify, animNotify);
        }

    }

    public record StateTransition(float transitionDuration, Easing easing, int priority, ConditionPredicate conditionPredicate) {

        public static Builder builder(ConditionPredicate conditionPredicate){
            return new Builder(conditionPredicate);
        }

        public static class Builder {
            private final ConditionPredicate conditionPredicate;
            private float transitionDuration;
            private Easing easing;
            private int priority;

            private Builder(ConditionPredicate conditionPredicate){
                this.conditionPredicate = conditionPredicate;
                this.transitionDuration = 1;
                this.easing = Easing.LINEAR;
                this.priority = 50;
            }

            /**
             * Sets the transition time for the state transition
             *
             * @param transitionDuration Transition duration, in ticks
             */
            public Builder setTransitionDuration(float transitionDuration){
                this.transitionDuration = transitionDuration;
                return this;
            }

            /**
             * Sets the easing for the state transition
             *
             * @param easing         Easing function
             */
            public Builder setEasing(Easing easing){
                this.easing = easing;
                return this;
            }

            /**
             * Sets the transition priority for the state transition, for when multiple transitions originating from one state are simultaneously active. Lower integers specify a lower priority
             *
             * @param priority      Priority integer
             */
            public Builder setPriority(int priority){
                this.priority = priority;
                return this;
            }

            public StateTransition build(){
                return new StateTransition(this.transitionDuration, this.easing, this.priority, this.conditionPredicate);
            }
        }

        @FunctionalInterface
        public interface ConditionPredicate {
            boolean test(AnimationDriverContainer animationDriverContainer, float ticksElapsedInCurrentState);
        }
    }
}
