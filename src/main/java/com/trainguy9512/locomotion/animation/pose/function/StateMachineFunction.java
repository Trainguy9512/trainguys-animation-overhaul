package com.trainguy9512.locomotion.animation.pose.function;

import com.trainguy9512.locomotion.animation.data.OnTickDataContainer;
import com.trainguy9512.locomotion.animation.pose.AnimationPose;
import com.trainguy9512.locomotion.animation.pose.LocalSpacePose;
import com.trainguy9512.locomotion.animation.pose.function.notify.NotifyListener;
import com.trainguy9512.locomotion.util.Easing;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

public class StateMachineFunction<S extends Enum<S>> extends TimeBasedPoseFunction<LocalSpacePose> {

    /**
     * The hashmap containing all the possible states, with the keys being enums.
     */
    //private final HashMap<S, AnimationStateMachine.State<S>> states;

    /**
     * The list of enum keys that point to states with a blend value greater than 0
     */
    private final ArrayList<S> activeStates;

    protected StateMachineFunction() {
        super(isPlayingFunction, playRateFunction, resetStartTimeOffsetTicks);
        this.states = states;
        this.activeStates = activeStates;
    }

    @Override
    public @NotNull LocalSpacePose compute(FunctionInterpolationContext context) {
        return null;
    }

    @Override
    public void tick(FunctionEvaluationState evaluationState) {

    }

    @Override
    public PoseFunction<LocalSpacePose> wrapUnique() {
        return null;
    }

    public static SequencePlayerFunction.Builder<?> builder(ResourceLocation animationSequence){
        return new SequencePlayerFunction.Builder<>(animationSequence);
    }

    public static class Builder<B extends Builder<B>> extends TimeBasedPoseFunction.Builder<B>{

        private final ResourceLocation animationSequence;
        private boolean looping;

        protected Builder(ResourceLocation animationSequence){
            super();
            this.animationSequence = animationSequence;
            this.looping = false;
        }

        /**
         * Sets whether the animation sequence function will loop or not when the end of the animation is reached.
         * @implNote                The animation sequence will always be looped in full, the reset start time only
         *                          affects where the animation starts when reset.
         */
        @SuppressWarnings("unchecked")
        public B setLooping(boolean looping){
            this.looping = looping;
            return (B) this;
        }

        public SequencePlayerFunction build(){
            return new SequencePlayerFunction(this.isPlayingFunction, this.playRateFunction, this.resetStartTimeOffsetTicks, this.animationSequence, this.looping);
        }
    }

    public static class State<S extends Enum<S>> {

        private final PoseFunction<LocalSpacePose> inputFunction;
        private final Set<StateTransition<S>> potentialStateTransitions;

        private boolean isActive;
        private float weight;
        private StateTransition<S> currentTransition;

        private State(PoseFunction<LocalSpacePose> inputFunction, Set<StateTransition<S>> potentialStateTransitions, boolean isActive){
            this.inputFunction = inputFunction;
            this.potentialStateTransitions = potentialStateTransitions;
            this.isActive = isActive;
            this.weight = isActive ? 1 : 0;
            this.currentTransition = null;
        }

        private void tick(OnTickDataContainer dataContainer){
            if(this.currentTransition != null){
                float increaseDecreaseMultiplier = this.getIsActive() ? 1 : -1;
                float newWeight = Mth.clamp(this.getWeight() + ((1 / this.getCurrentTransition().transitionDurationTicks()) * increaseDecreaseMultiplier), 0, 1);
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

        private AnimationPose computeInput(FunctionInterpolationContext context){
            return this.inputFunction.compute(context);
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
