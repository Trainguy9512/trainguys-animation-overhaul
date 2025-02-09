package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.pose.sample.notify.NotifyListener;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

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

    public static <S extends Enum<S>> Builder<?, S> stateMachineBuilder(){
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
         * Adds a state to the state machine builder with its outgoing transitions.
         *
         * @param stateIdentifier       Enum identifier that is associated with the state machine's enum type
         * @param sampleable            Functional interface for sampling a pose from the state
         * @param stateTransitions      Outbound transition paths from this state to other states
         * @see StateTransition.Builder
         */
        @SuppressWarnings("unchecked")
        public B addState(S stateIdentifier, Sampleable sampleable, StateTransition<S>... stateTransitions){
            State<S> state = new State<S>(this.statesHashMap.isEmpty(), sampleable, Set.of(stateTransitions));

            // If this is the first state to be added, set it to be active.
            if (this.statesHashMap.isEmpty()){
                this.activeStates.add(stateIdentifier);
            }
            return (B) this;
        }

        /**
         * Binds an anim notify to fire every time a state's weight goes from 0 to greater than 0
         * @param stateIdentifier       State to bind anim notify to. Must already be added to builder.
         * @param notifyListener        Functional interface to fire on the notify call.
         */
        @SuppressWarnings("unchecked")
        public B bindNotifyToOnStateRelevant(S stateIdentifier, NotifyListener notifyListener){
            if(this.statesHashMap.containsKey(stateIdentifier)){
                this.statesHashMap.get(stateIdentifier).bindNotifyToOnStateRelevant(notifyListener);
            }
            return (B) this;
        }

        /**
         * Binds an anim notify to fire every time a state's weight goes from greater than 0
         * @param stateIdentifier       State to bind anim notify to. Must already be added to builder.
         * @param notifyListener        Functional interface to fire on the notify call.
         */
        @SuppressWarnings("unchecked")
        public B bindNotifyToOnStateIrrelevant(S stateIdentifier, NotifyListener notifyListener){
            if(this.statesHashMap.containsKey(stateIdentifier)){
                this.statesHashMap.get(stateIdentifier).bindNotifyToOnStateIrrelevant(notifyListener);
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

        // Get the current active state
        S currentActiveStateIdentifier = this.activeStates.getLast();
        State<S> currentActiveState = this.statesHashMap.get(currentActiveStateIdentifier);

        // Filter each potential state transition by whether it's valid, then filter by whether its condition predicate is true,
        // then shuffle it in order to make equal priority transitions randomized and re-order the valid transitions by filter order.
        Optional<StateTransition<S>> potentialStateTransition = currentActiveState.getPotentialTransitions().stream()
                .filter((transition) -> this.statesHashMap.containsKey(transition.target()))
                .filter((transition) -> transition.target() != currentActiveStateIdentifier)
                .filter((transition) -> transition.conditionPredicate().test(animationDriverContainer, this.getTimeElapsed()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                    Collections.shuffle(collected);
                    return collected;
                }))
                .stream()
                .sorted()
                .findFirst();


        // Set all states to inactive except the new destination state. Also set the transition to all states for when they're ticked

        if(potentialStateTransition.isPresent()){
            StateTransition<S> stateTransition = potentialStateTransition.get();
            this.resetTime();
            for(S stateIdentifier : this.statesHashMap.keySet()){
                this.statesHashMap.get(stateIdentifier).setCurrentTransition(stateTransition);
                this.statesHashMap.get(stateIdentifier).setIsActive(false);
            }
            //Enum<S> destinationStateIdentifier = stateTransition.;
            this.statesHashMap.get(stateTransition.target()).setIsActive(true);

            // Update the active states array
            // Make sure there already isn't this state present in active states
            this.activeStates.remove(stateTransition.target());
            this.activeStates.add(stateTransition.target());
        }

        // Tick each state
        for(State<S> state : this.statesHashMap.values()){
            state.tick(animationDriverContainer, poseSamplerStateContainer);
        }

        // Evaluated last, remove states from the active state list that have a weight of 0.
        List<S> statesToRemove = this.activeStates.stream().filter((stateIdentifer) -> this.statesHashMap.get(stateIdentifer).getWeight() != 0).toList();
        this.activeStates.removeAll(statesToRemove);
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
        private final Set<StateTransition<S>> potentialStateTransitions;

        private NotifyListener onStateRelevantNotifyListeners = null;
        private NotifyListener onStateIrrelevantNotifyListeners = null;

        private boolean isActive;
        private float weight;
        private StateTransition<S> currentTransition;

        private State(boolean isActive, Sampleable sampleable, Set<StateTransition<S>> potientialStateTransitions){
            this.sampleable = sampleable;
            this.potentialStateTransitions = potientialStateTransitions;
            this.isActive = isActive;
            this.weight = isActive ? 1 : 0;
        }

        private void tick(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
            if(this.currentTransition != null){
                float increaseDecreaseMultiplier = this.getIsActive() ? 1 : -1;
                float newWeight = Mth.clamp(this.getWeight() + ((1 / this.getCurrentTransition().transitionDuration()) * increaseDecreaseMultiplier), 0, 1);

                // Notify calls
                if(newWeight > 0 && this.getWeight() == 0){
                    this.onStateRelevantNotifyListeners.notify(animationDriverContainer, poseSamplerStateContainer);
                }
                if(newWeight == 0 && this.getWeight() > 0){
                    this.onStateIrrelevantNotifyListeners.notify(animationDriverContainer, poseSamplerStateContainer);
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

        private StateTransition<S> getCurrentTransition(){
            return this.currentTransition;
        }

        private void setCurrentTransition(StateTransition<S> stateTransition){
            this.currentTransition = stateTransition;
        }

        private float getWeight(){
            return this.weight;
        }

        private void setWeight(float weight){
            this.weight = weight;
        }

        private Set<StateTransition<S>> getPotentialTransitions(){
            return this.potentialStateTransitions;
        }

        private AnimationPose sample(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton){
            return this.sampleable.sample(animationDriverContainer, poseSamplerStateContainer, jointSkeleton);
        }

        private void bindNotifyToOnStateRelevant(@NotNull NotifyListener animNotify){
            this.onStateRelevantNotifyListeners = NotifyListener.Multi.combine(this.onStateRelevantNotifyListeners, animNotify);
        }

        private void bindNotifyToOnStateIrrelevant(@NotNull NotifyListener animNotify){
            this.onStateIrrelevantNotifyListeners = NotifyListener.Multi.combine(this.onStateIrrelevantNotifyListeners, animNotify);
        }

    }

    public record StateTransition<S extends Enum<S>> (S target, ConditionPredicate conditionPredicate, float transitionDuration, Easing easing, int priority) implements Comparable<StateTransition<S>> {

        public static <S extends Enum<S>> Builder<S> builder(S target, ConditionPredicate conditionPredicate){
            return new Builder<>(target, conditionPredicate);
        }

        @SuppressWarnings("comparable")
        @Override
        public int compareTo(@NotNull AnimationStateMachine.StateTransition stateTransition) {
            return Integer.compare(this.priority(), stateTransition.priority());
        }

        public static class Builder<S extends Enum<S>> {
            private final S target;
            private final ConditionPredicate conditionPredicate;
            private float transitionDuration;
            private Easing easing;
            private int priority;

            private Builder(S target, ConditionPredicate conditionPredicate){
                this.target = target;
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
            public Builder<S> setTransitionDuration(float transitionDuration){
                this.transitionDuration = transitionDuration;
                return this;
            }

            /**
             * Sets the easing for the state transition
             *
             * @param easing         Easing function
             */
            public Builder<S> setEasing(Easing easing){
                this.easing = easing;
                return this;
            }

            /**
             * Sets the transition priority for the state transition, for when multiple transitions originating from one state are simultaneously active. Lower integers specify a higher priority
             *
             * @param priority      Priority integer
             */
            public Builder<S> setPriority(int priority){
                this.priority = priority;
                return this;
            }

            public StateTransition<S> build(){
                return new StateTransition<>(this.target, this.conditionPredicate, this.transitionDuration, this.easing, this.priority);
            }
        }

        @FunctionalInterface
        public interface ConditionPredicate {
            boolean test(AnimationDriverContainer animationDriverContainer, float ticksElapsedInCurrentState);
        }
    }
}
