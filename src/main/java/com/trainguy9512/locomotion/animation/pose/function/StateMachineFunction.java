package com.trainguy9512.locomotion.animation.pose.function;

import com.google.common.collect.Maps;
import com.trainguy9512.locomotion.LocomotionMain;
import com.trainguy9512.locomotion.animation.data.OnTickDataContainer;
import com.trainguy9512.locomotion.animation.data.driver.Driver;
import com.trainguy9512.locomotion.animation.pose.LocalSpacePose;
import com.trainguy9512.locomotion.util.Easing;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StateMachineFunction<S extends Enum<S>> extends TimeBasedPoseFunction<LocalSpacePose> {

    /**
     * The hashmap containing all the possible states, with the keys being enums.
     */
    private final Map<S, State<S>> states;

    /**
     * The list of enum keys that point to states with a blend value greater than 0
     */
    private final List<S> activeStates;

    protected StateMachineFunction(Map<S, State<S>> states, List<S> activeStates) {
        super(evaluationState -> true, evaluationState -> 1f, 0);
        this.states = states;
        this.activeStates = activeStates;
    }

    @Override
    public @NotNull LocalSpacePose compute(FunctionInterpolationContext context) {
        // Throw an error if the active states are empty, this should never happen but this should help with debugging.
        if(this.activeStates.isEmpty()){
            throw new IllegalStateException("State machine's active states list found to be empty.");
        }

        // Blend each active state's pose.
        LocalSpacePose pose = this.states.get(this.activeStates.getFirst()).inputFunction.compute(context);
        for(S stateIdentifier : this.activeStates){
            // We already got the first active state's pose.
            if(stateIdentifier != this.activeStates.getFirst()){
                pose = pose.interpolated(
                        this.states.get(stateIdentifier).inputFunction.compute(context),
                        this.states.get(stateIdentifier).currentTransition.easing().ease(
                                this.states.get(stateIdentifier).weight.getValueInterpolated(context.partialTicks())
                        )
                );
            }
        }
        return pose;
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {
        // Don't evaluate if the state machine has no states
        if(this.activeStates.isEmpty()){
            throw new IllegalStateException("State machine's active states list found to be empty.");
        }

        // Add to the current elapsed ticks
        super.tick(evaluationState);

        // Get the current active state
        S currentActiveStateIdentifier = this.activeStates.getLast();
        State<S> currentActiveState = this.states.get(currentActiveStateIdentifier);

        // Filter each potential state transition by whether it's valid, then filter by whether its condition predicate is true,
        // then shuffle it in order to make equal priority transitions randomized and re-order the valid transitions by filter order.
        Optional<StateTransition<S>> potentialStateTransition = currentActiveState.potentialStateTransitions.stream()
                .filter(transition -> {
                    if(this.states.containsKey(transition.target)){
                        if(transition.target() != currentActiveStateIdentifier){
                            return transition.conditionPredicate().test(StateTransition.TransitionContext.of(evaluationState.dataContainer(), this.timeTicksElapsed, this.states.get(this.activeStates.getLast()).weight.getValueCurrent()));
                        }
                    }
                    return false;
                })
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                    Collections.shuffle(collected);
                    return collected;
                }))
                .stream()
                .sorted()
                .findFirst();

        // Set all states to inactive except the new destination state. Also set the transition to all states for when they're ticked
        potentialStateTransition.ifPresent(stateTransition -> {
            this.resetTime();
            this.states.forEach((stateIdentifier, state) -> {
                state.currentTransition = stateTransition;
                state.isActive = state == this.states.get(stateTransition.target());
            });

            // Update the active states array
            // Make sure there already isn't this state present in active states
            this.activeStates.remove(stateTransition.target());
            this.activeStates.addLast(stateTransition.target());
        });

        // Tick each state
        this.states.forEach((stateIdentifier, state) -> state.tick(evaluationState));

        // Evaluated last, remove states from the active state list that have a weight of 0.
        List<S> statesToRemove = this.activeStates.stream().filter((stateIdentifier) -> this.states.get(stateIdentifier).weight.getValuePrevious() == 0 && this.states.get(stateIdentifier).weight.getValueCurrent() == 0).toList();
        this.activeStates.removeAll(statesToRemove);
    }

    @Override
    public PoseFunction<LocalSpacePose> wrapUnique() {
        Builder<S> builder = this.builder();
        this.states.forEach((stateIdentifier, state) -> builder.addState(stateIdentifier, state.inputFunction.wrapUnique(), state.resetUponEntry, state.potentialStateTransitions));
        return builder.build();
    }

    public static <S extends Enum<S>> Builder<S> builder(S[] values){
        return new Builder<>();
    }

    public Builder<S> builder(){
        return new Builder<>();
    }

    public static class Builder<S extends Enum<S>> {

        private final Map<S, State<S>> states;
        private final List<S> activeStates;


        protected Builder() {
            this.states = Maps.newHashMap();
            this.activeStates = new ArrayList<>();
        }

        /**
         * Adds a state to the state machine builder with its outgoing transitions.
         *
         * @param stateIdentifier       Enum identifier that is associated with the state machine's enum type
         * @param inputFunction         Pose function for this state
         * @param stateTransitions      Outbound transition paths from this state to other states
         */
        @SafeVarargs
        public final Builder<S> addState(S stateIdentifier, PoseFunction<LocalSpacePose> inputFunction, boolean resetUponEntry, StateTransition<S>... stateTransitions){
            return this.addState(stateIdentifier, inputFunction, resetUponEntry, Set.of(stateTransitions));
        }

        /**
         * Adds a state to the state machine builder with its outgoing transitions.
         *
         * @param stateIdentifier       Enum identifier that is associated with the state machine's enum type
         * @param inputFunction         Pose function for this state
         * @param stateTransitions      Outbound transition paths from this state to other states
         */
        public final Builder<S> addState(S stateIdentifier, PoseFunction<LocalSpacePose> inputFunction, boolean resetUponEntry, Set<StateTransition<S>> stateTransitions){
            State<S> state = new State<>(inputFunction, stateTransitions, resetUponEntry, this.states.isEmpty());

            // If the state machine already has this state defined, then throw an error.
            if(this.states.containsKey(stateIdentifier)){
                throw new IllegalStateException("Cannot add state " + stateIdentifier.toString() + " twice to the same state machine.");
            }

            // If this is the first state to be added, set it to be active.
            if (this.activeStates.isEmpty()){
                this.activeStates.add(stateIdentifier);
            }
            this.states.put(stateIdentifier, state);
            return this;
        }

        public StateMachineFunction<S> build(){
            return new StateMachineFunction<>(this.states, this.activeStates);
        }
    }

    private static class State<S extends Enum<S>> {

        private final PoseFunction<LocalSpacePose> inputFunction;
        private final Set<StateTransition<S>> potentialStateTransitions;
        private final boolean resetUponEntry;

        private boolean isActive;
        private final Driver<Float> weight;
        private StateTransition<S> currentTransition;

        private State(PoseFunction<LocalSpacePose> inputFunction, Set<StateTransition<S>> potentialStateTransitions, boolean resetUponEntry, boolean isActive){
            this.inputFunction = inputFunction;
            this.potentialStateTransitions = potentialStateTransitions;
            this.resetUponEntry = resetUponEntry;

            this.isActive = isActive;
            this.weight = isActive ? Driver.floatDriver(() -> 1f) : Driver.floatDriver(() -> 0f);
            this.currentTransition = null;
        }

        private void tick(FunctionEvaluationState evaluationState){
            if(this.currentTransition != null){
                this.weight.pushToPrevious();
                float increaseDecreaseMultiplier = this.isActive ? 1 : -1;
                float newWeight = Mth.clamp(this.weight.getValuePrevious() + ((1 / this.currentTransition.transitionDurationTicks()) * increaseDecreaseMultiplier), 0, 1);

                if(this.resetUponEntry && newWeight > 0 && this.weight.getValuePrevious() == 0){
                    evaluationState = evaluationState.markedForReset();
                }

                this.weight.loadValue(newWeight);
            }
            if(this.weight.getValueCurrent() > 0){
                this.inputFunction.tick(evaluationState);
            }
        }

    }

    public record StateTransition<S extends Enum<S>> (S target, Predicate<TransitionContext> conditionPredicate, float transitionDurationTicks, Easing easing, int priority) implements Comparable<StateTransition<S>> {

        public static <S extends Enum<S>> Builder<S> builder(S target, Predicate<TransitionContext> conditionPredicate){
            return new Builder<>(target, conditionPredicate);
        }

        @Override
        public int compareTo(@NotNull StateTransition stateTransition) {
            return Integer.compare(this.priority(), stateTransition.priority());
        }

        public static class Builder<S extends Enum<S>> {
            private final S target;
            private final Predicate<TransitionContext> conditionPredicate;
            private float transitionDurationTicks;
            private Easing easing;
            private int priority;

            private Builder(S target, Predicate<TransitionContext> conditionPredicate){
                this.target = target;
                this.conditionPredicate = conditionPredicate;
                this.transitionDurationTicks = 1;
                this.easing = Easing.LINEAR;
                this.priority = 50;
            }

            /**
             * Sets the transition time for the state transition.
             *
             * @param transitionDurationTicks   Transition duration, in ticks
             */
            public Builder<S> setTransitionDuration(float transitionDurationTicks){
                this.transitionDurationTicks = transitionDurationTicks;
                return this;
            }

            /**
             * Sets the easing for the transition.
             *
             * @param easing                    Easing function
             */
            public Builder<S> setEasing(Easing easing){
                this.easing = easing;
                return this;
            }

            /**
             * Sets the transition priority for the state transition, for when more than one transition is active on the same tick.
             * <p>
             * Lower integers specify a higher priority. If more than one transition has the same priority, then it is picked at random.
             * <p>
             * Default priority is <code>50</code>.
             *
             * @param priority                  Priority integer
             */
            public Builder<S> setPriority(int priority){
                this.priority = priority;
                return this;
            }

            public StateTransition<S> build(){
                return new StateTransition<>(this.target, this.conditionPredicate, this.transitionDurationTicks, this.easing, this.priority);
            }
        }

        public record TransitionContext(OnTickDataContainer dataContainer, float ticksElapsedInCurrentState, float currentStateWeight){
            public static TransitionContext of(OnTickDataContainer dataContainer, float ticksElapsedInCurrentState, float currentStateWeight){
                return new TransitionContext(dataContainer, ticksElapsedInCurrentState, currentStateWeight);
            }
        }
    }
}
