package com.trainguy9512.animationoverhaul.animation.data;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.pose.sample.*;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import net.minecraft.util.Mth;

import java.util.*;
import java.util.function.Supplier;

public class AnimationDataContainer {

    private final HashMap<AnimationVariableKey<?>, AnimationVariable<?>> animationVariables;
    private final HashMap<String, PoseSampler> entitySampleableAnimationStates;
    private final CachedPoseContainer cachedPoseContainer = new CachedPoseContainer();

    public AnimationDataContainer(){
        this.animationVariables = Maps.newHashMap();
        this.entitySampleableAnimationStates = Maps.newHashMap();
    }

    public void tickAnimationStates(){
        for(PoseSampler poseSampler : entitySampleableAnimationStates.values()){
            poseSampler.tick();
        }
    }

    public <D extends PoseSampler> D getAnimationState(D sampleableAnimationState){
        for(String identifier : this.entitySampleableAnimationStates.keySet()){
            if (Objects.equals(sampleableAnimationState.getIdentifier(), identifier)){
                return (D) this.entitySampleableAnimationStates.get(identifier);
            }
        }
        this.entitySampleableAnimationStates.put(sampleableAnimationState.getIdentifier(), sampleableAnimationState);
        return sampleableAnimationState;
    }

    public <L extends Enum<L>> AnimationPose<L> sampleAnimationState(JointSkeleton<L> jointSkeleton, PoseSampler poseSampler){
        for(String identifier : this.entitySampleableAnimationStates.keySet()){
            if (Objects.equals(poseSampler.getIdentifier(), identifier)){
                return this.entitySampleableAnimationStates.get(identifier).sample(jointSkeleton, cachedPoseContainer);
            }
        }
        this.entitySampleableAnimationStates.put(poseSampler.getIdentifier(), poseSampler);
        return (this.entitySampleableAnimationStates.get(poseSampler.getIdentifier())).sample(jointSkeleton, cachedPoseContainer);
    }

    public <L extends Enum<L>> AnimationPose<L> sampleAnimationStateFromInputPose(AnimationPose<L> inputPose, JointSkeleton<L> jointSkeleton, PoseSampler poseSampler){
        for(String identifier : this.entitySampleableAnimationStates.keySet()){
            if (Objects.equals(poseSampler.getIdentifier(), identifier)){
                return this.entitySampleableAnimationStates.get(identifier).sampleFromInputPose(inputPose, jointSkeleton, cachedPoseContainer);
            }
        }
        this.entitySampleableAnimationStates.put(poseSampler.getIdentifier(), poseSampler);
        return (this.entitySampleableAnimationStates.get(poseSampler.getIdentifier())).sampleFromInputPose(inputPose, jointSkeleton, cachedPoseContainer);
    }

    public <L extends Enum<L>> void saveCachedPose(String identifier, AnimationPose<L> animationPose){
        this.cachedPoseContainer.saveCachedPose(identifier, animationPose);
    }

    public <L extends Enum<L>> AnimationPose<?> getCachedPose(String identifier, JointSkeleton<L> jointSkeleton){
        return this.cachedPoseContainer.getCachedPose(identifier, jointSkeleton);
    }

    public class CachedPoseContainer {
        private final HashMap<String, AnimationPose<?>> poses = Maps.newHashMap();

        public CachedPoseContainer(){
        }

        public void saveCachedPose(String identifier, AnimationPose<?> animationPose){
            this.poses.put(identifier, animationPose);
        }

        public AnimationPose<?> getCachedPose(String identifier, JointSkeleton<?> jointSkeleton){
            if(this.poses.containsKey(identifier)){
                return this.poses.get(identifier);
            }
            return AnimationPose.of(jointSkeleton);
        }
    }

    public <D> AnimationVariable<D> getAnimationVariable(AnimationVariableKey<D> dataKey){
        if(!animationVariables.containsKey(dataKey)){
            animationVariables.put(dataKey, new AnimationVariable<>(dataKey));
        }
        return (AnimationVariable<D>) animationVariables.get(dataKey);
    }

    public TreeMap<String, AnimationVariable<?>> getDebugData(){
        TreeMap<String, AnimationVariable<?>> finalList = Maps.newTreeMap();
        for(AnimationVariableKey<?> dataKey : this.animationVariables.keySet()){
            AnimationVariable<?> data = animationVariables.get(dataKey);

            String[] typeSplitted = data.get().getClass().toString().split("\\.");
            String type = typeSplitted[typeSplitted.length - 1];

            typeSplitted = type.split("\\$");
            type = typeSplitted[typeSplitted.length - 1];

            String debugIdentifier = dataKey.getDebugIdentifier() + " (" + type + "):";
            finalList.put(debugIdentifier, data);
        }
        return finalList;
    }

    public <D> void setValue(AnimationVariableKey<D> dataKey, D value){
        this.getAnimationVariable(dataKey).set(value);
    }

    public <D> D getValue(AnimationVariableKey<D> dataKey){
        return this.getAnimationVariable(dataKey).get();
    }


    /**
     * Increments a float value based on a condition, incremented in ticks
     *
     * @param dataKey           Float data key
     * @param condition         Boolean condition to decide whether the value should increment or decrement
     * @param ticksToIncrement  Time in ticks to increment from 0 to 1
     * @param ticksToDecrement  Time in ticks to decrement from 1 to 0
     */
    public void incrementInTicksFromCondition(AnimationVariableKey<Float> dataKey, boolean condition, float ticksToIncrement, float ticksToDecrement){
        ticksToIncrement = Math.max(1, ticksToIncrement);
        ticksToDecrement = Math.max(1, ticksToDecrement);
        AnimationVariable<Float> data = this.getAnimationVariable(dataKey);
        data.set(Mth.clamp((data.get()) + (condition ? 1/ticksToIncrement : -1/ticksToDecrement), 0, 1));
    }

    /**
     * Increments or resets a value based on a condition, incremented in ticks
     *
     * @param dataKey           Float data key
     * @param condition         Boolean condition to decide whether the value should reset to 0 or increment
     * @param ticksToIncrement  Time in ticks to increment from 0 to 1
     */
    public void incrementInTicksOrResetFromCondition(AnimationVariableKey<Float> dataKey, boolean condition, float ticksToIncrement){
        AnimationVariable<Float> data = this.getAnimationVariable(dataKey);
        if(condition){
            data.set(0F);
            data.set(0F);
        } else {
            this.incrementInTicksFromCondition(dataKey, true, ticksToIncrement, ticksToIncrement);
        }
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
    public void incrementInTicksOrResetRandomFromCondition(AnimationVariableKey<Float> dataKeyMain, AnimationVariableKey<Integer> dataKeyIndex, int numberOfAnimations, boolean condition, float ticksToIncrement, Random random){
        AnimationVariable<Float> dataMain = this.getAnimationVariable(dataKeyMain);
        AnimationVariable<Integer> dataIndex = this.getAnimationVariable(dataKeyIndex);
        if(condition){
            dataMain.set(0F);
            dataMain.set(0F);
            dataIndex.set(random.nextInt(numberOfAnimations));
        } else {
            this.incrementInTicksFromCondition(dataKeyMain, true, ticksToIncrement, ticksToIncrement);
        }
    }

    /*
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

     */


    public static class AnimationVariable<D>{

        private D value;
        private D valueOld;
        private final Supplier<D> defaultValue;

        private AnimationVariable(AnimationVariableKey<D> dataKey){
            this.defaultValue = dataKey.getDefaultValue();
            this.value = dataKey.getDefaultValue().get();
            this.valueOld = dataKey.getDefaultValue().get();
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
                this.value = defaultValue.get();
            }
        }

        public void setDefaultValue(){
            this.set(this.defaultValue.get());
        }

        public boolean isFloat(){
            return this.value instanceof Float;
        }
    }
}
