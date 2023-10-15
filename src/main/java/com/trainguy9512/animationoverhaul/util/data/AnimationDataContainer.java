package com.trainguy9512.animationoverhaul.util.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import com.trainguy9512.animationoverhaul.util.time.TickTimeUtils;
import net.minecraft.util.Mth;

import java.util.*;

public class AnimationDataContainer {

    private final HashMap<DataKey<?>, Variable<?>> entityAnimationVariables;
    private final HashMap<String, SampleableAnimationState> entitySampleableAnimationStates;
    private CachedPoseContainer cachedPoseContainer = new CachedPoseContainer();

    public AnimationDataContainer(){
        this.entityAnimationVariables = Maps.newHashMap();
        this.entitySampleableAnimationStates = Maps.newHashMap();
    }

    public void tickAnimationStates(){
        for(SampleableAnimationState sampleableAnimationState : entitySampleableAnimationStates.values()){
            sampleableAnimationState.tick();
        }
    }

    public <D extends SampleableAnimationState> D getAnimationState(D sampleableAnimationState){
        for(String identifier : this.entitySampleableAnimationStates.keySet()){
            if (Objects.equals(sampleableAnimationState.getIdentifier(), identifier)){
                return (D) this.entitySampleableAnimationStates.get(identifier);
            }
        }
        this.entitySampleableAnimationStates.put(sampleableAnimationState.getIdentifier(), sampleableAnimationState);
        return sampleableAnimationState;
    }

    public <L extends Enum<L>> AnimationPose<L> sampleAnimationState(LocatorSkeleton<L> locatorSkeleton, SampleableAnimationState sampleableAnimationState){
        for(String identifier : this.entitySampleableAnimationStates.keySet()){
            if (Objects.equals(sampleableAnimationState.getIdentifier(), identifier)){
                return this.entitySampleableAnimationStates.get(identifier).sample(locatorSkeleton, cachedPoseContainer);
            }
        }
        this.entitySampleableAnimationStates.put(sampleableAnimationState.getIdentifier(), sampleableAnimationState);
        return (this.entitySampleableAnimationStates.get(sampleableAnimationState.getIdentifier())).sample(locatorSkeleton, cachedPoseContainer);
    }

    public <L extends Enum<L>> AnimationPose<L> sampleAnimationStateFromInputPose(AnimationPose<L> inputPose, LocatorSkeleton<L> locatorSkeleton, SampleableAnimationState sampleableAnimationState){
        for(String identifier : this.entitySampleableAnimationStates.keySet()){
            if (Objects.equals(sampleableAnimationState.getIdentifier(), identifier)){
                return this.entitySampleableAnimationStates.get(identifier).sampleFromInputPose(inputPose, locatorSkeleton, cachedPoseContainer);
            }
        }
        this.entitySampleableAnimationStates.put(sampleableAnimationState.getIdentifier(), sampleableAnimationState);
        return (this.entitySampleableAnimationStates.get(sampleableAnimationState.getIdentifier())).sampleFromInputPose(inputPose, locatorSkeleton, cachedPoseContainer);
    }

    public <L extends Enum<L>> void saveCachedPose(String identifier, AnimationPose<L> animationPose){
        this.cachedPoseContainer.saveCachedPose(identifier, animationPose);
    }

    public <L extends Enum<L>> AnimationPose<?> getCachedPose(String identifier, LocatorSkeleton<L> locatorSkeleton){
        return this.cachedPoseContainer.getCachedPose(identifier, locatorSkeleton);
    }

    public class CachedPoseContainer {
        private final HashMap<String, AnimationPose<?>> poses = Maps.newHashMap();

        public CachedPoseContainer(){
        }

        public void saveCachedPose(String identifier, AnimationPose<?> animationPose){
            this.poses.put(identifier, animationPose);
        }

        public AnimationPose<?> getCachedPose(String identifier, LocatorSkeleton<?> locatorSkeleton){
            if(this.poses.containsKey(identifier)){
                return this.poses.get(identifier);
            }
            return AnimationPose.of(locatorSkeleton);
        }
    }

    public <D> Variable<D> get(DataKey<D> dataKey){
        if(!entityAnimationVariables.containsKey(dataKey)){
            entityAnimationVariables.put(dataKey, new Variable<>(dataKey));
        }
        return (Variable<D>) entityAnimationVariables.get(dataKey);
    }

    public TreeMap<String, Variable<?>> getDebugData(){
        TreeMap<String, Variable<?>> finalList = Maps.newTreeMap();
        for(DataKey<?> dataKey : this.entityAnimationVariables.keySet()){
            Variable<?> data = entityAnimationVariables.get(dataKey);

            String[] typeSplitted = data.get().getClass().toString().split("\\.");
            String type = typeSplitted[typeSplitted.length - 1];

            typeSplitted = type.split("\\$");
            type = typeSplitted[typeSplitted.length - 1];

            String debugIdentifier = dataKey.getIdentifier() + " (" + type + "):";
            finalList.put(debugIdentifier, data);
        }
        return finalList;
    }

    public <D> void setValue(DataKey<D> dataKey, D value){
        this.get(dataKey).set(value);
    }

    public <D> D getValue(DataKey<D> dataKey){
        return this.get(dataKey).get();
    }

    /**
     * Obtains a float value by linearly interpolating the current and old values
     *
     * @param   dataKey         Float data key
     * @param   partialTicks    Time in ticks since the last full tick
     * @return  Final interpolated float
     */
    public float getLerped(DataKey<Float> dataKey, float partialTicks){
        return Mth.lerp(partialTicks, this.get(dataKey).getOld(), this.get(dataKey).get());
    }

    /**
     * Obtains a float value by interpolating the current and old values and then applying an easing
     *
     * @param   dataKey         Float data key
     * @param   easing          Easing object to apply the lerped float value onto
     * @param   partialTicks    Time in ticks since the last full tick
     * @return  Final eased float
     */
    public float getEased(DataKey<Float> dataKey, Easing easing, float partialTicks){
        return easing.ease(this.getLerped(dataKey, partialTicks));
    }

    public float getEased(DataKey<Float> dataKey, Easing easing){
        return getEased(dataKey, easing, 1);
    }

    public float getDataValueEasedQuad(AnimationDataContainer.DataKey<Float> dataKey){
        return getEased(dataKey, Easing.CubicBezier.bezierInOutQuad());
    }

    public float getDataValueEasedCondition(AnimationDataContainer.DataKey<Float> dataKey, Easing easing1, Easing easing2, boolean condition){
        return getEased(dataKey, condition ? easing1 : easing2);
    }

    /**
     * Increments a float value based on a condition, incremented in ticks
     *
     * @param dataKey           Float data key
     * @param condition         Boolean condition to decide whether the value should increment or decrement
     * @param ticksToIncrement  Time in ticks to increment from 0 to 1
     * @param ticksToDecrement  Time in ticks to decrement from 1 to 0
     */
    public void incrementInTicksFromCondition(DataKey<Float> dataKey, boolean condition, float ticksToIncrement, float ticksToDecrement){
        ticksToIncrement = Math.max(1, ticksToIncrement);
        ticksToDecrement = Math.max(1, ticksToDecrement);
        Variable<Float> data = this.get(dataKey);
        data.set(Mth.clamp((data.get()) + (condition ? 1/ticksToIncrement : -1/ticksToDecrement), 0, 1));
    }

    /**
     * Increments a float value based on a condition, incremented in frames
     *
     * @param dataKey           Float data key
     * @param condition         Boolean condition to decide whether the value should increment or decrement
     * @param framesToIncrement  Time in frames to increment from 0 to 1
     * @param framesToDecrement  Time in frames to decrement from 1 to 0
     */
    @Deprecated
    public void incrementInFramesFromCondition(DataKey<Float> dataKey, boolean condition, float framesToIncrement, float framesToDecrement){
        this.incrementInTicksFromCondition(dataKey, condition, TickTimeUtils.ticksFromMayaFrames(framesToIncrement), TickTimeUtils.ticksFromMayaFrames(framesToDecrement));
    }

    /**
     * Increments or resets a value based on a condition, incremented in ticks
     *
     * @param dataKey           Float data key
     * @param condition         Boolean condition to decide whether the value should reset to 0 or increment
     * @param ticksToIncrement  Time in ticks to increment from 0 to 1
     */
    public void incrementInTicksOrResetFromCondition(DataKey<Float> dataKey, boolean condition, float ticksToIncrement){
        Variable<Float> data = this.get(dataKey);
        if(condition){
            data.set(0F);
            data.set(0F);
        } else {
            this.incrementInTicksFromCondition(dataKey, true, ticksToIncrement, ticksToIncrement);
        }
    }

    /**
     * Increments or resets a value based on a condition, incremented in frames
     *
     * @param dataKey               Float data key
     * @param condition             Boolean condition to decide whether the value should reset to 0 or increment
     * @param framesToIncrement     Time in frames to increment from 0 to 1
     */
    @Deprecated
    public void incrementInFramesOrResetFromCondition(DataKey<Float> dataKey, boolean condition, float framesToIncrement){
        this.incrementInTicksOrResetFromCondition(dataKey, condition, TickTimeUtils.ticksFromMayaFrames(framesToIncrement));
    }

    /**
     * Increments or resets a value based on a condition, incremented in ticks
     *
     * @param dataKeyMain           Main float data key
     * @param dataKeyIndex          Index data key
     * @param numberOfAnimations    Number of animations to pick from
     * @param condition             Boolean condition to decide whether the value should reset to 0 or increment
     * @param ticksToIncrement      Time in ticks to increment from 0 to 1
     * @param random                Java random object used to pick a random index within numberOfAnimations
     */
    public void incrementInTicksOrResetRandomFromCondition(DataKey<Float> dataKeyMain, DataKey<Integer> dataKeyIndex, int numberOfAnimations, boolean condition, float ticksToIncrement, Random random){
        Variable<Float> dataMain = this.get(dataKeyMain);
        Variable<Integer> dataIndex = this.get(dataKeyIndex);
        if(condition){
            dataMain.set(0F);
            dataMain.set(0F);
            dataIndex.set(random.nextInt(numberOfAnimations));
        } else {
            this.incrementInTicksFromCondition(dataKeyMain, true, ticksToIncrement, ticksToIncrement);
        }
    }

    /**
     * Increments or resets a value based on a condition, incremented in frames
     *
     * @param dataKeyMain           Main float data key
     * @param dataKeyIndex          Index data key
     * @param numberOfAnimations    Number of animations to pick from
     * @param condition             Boolean condition to decide whether the value should reset to 0 or increment
     * @param framesToIncrement     Time in frames to increment from 0 to 1
     * @param random                Java random object used to pick a random index within numberOfAnimations
     */
    public void incrementInFramesOrResetRandomFromCondition(DataKey<Float> dataKeyMain, DataKey<Integer> dataKeyIndex, int numberOfAnimations, boolean condition, float framesToIncrement, Random random){
        incrementInTicksOrResetRandomFromCondition(dataKeyMain, dataKeyIndex, numberOfAnimations, condition, framesToIncrement / 1.2F, random);
    }

    public static class DataKey<D>{

        private final String identifier;
        private final D defaultValue;

        public DataKey(String identifier, D defaultValue){
            this.identifier = identifier;
            this.defaultValue = defaultValue;
        }

        public String getIdentifier(){
            return this.identifier;
        }
    }

    public static class Variable<D>{

        private D value;
        private D valueOld;
        private final D defaultValue;

        public Variable(DataKey<D> dataKey){
            this.value = dataKey.defaultValue;
            this.valueOld = dataKey.defaultValue;
            this.defaultValue = dataKey.defaultValue;
        }

        public D get(){
            return this.value;
        }

        public D getOld(){
            return this.valueOld;
        }

        public void set(D value){
            this.valueOld = this.value;
            if(value != null){
                this.value = value;
            } else {
                this.value = defaultValue;
            }
        }

        private boolean isFloat(){
            return this.value instanceof Float;
        }
    }
}
