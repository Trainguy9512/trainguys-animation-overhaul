package gg.moonflower.animationoverhaul.util.data;

import com.google.common.collect.Maps;
import gg.moonflower.animationoverhaul.util.time.Easing;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class EntityAnimationData {

    private final HashMap<DataKey<?>, Data<?>> entityAnimationDataMap;

    public EntityAnimationData(){
        this.entityAnimationDataMap = Maps.newHashMap();
    }

    public <D> Data<D> get(DataKey<D> dataKey){
        if(!entityAnimationDataMap.containsKey(dataKey)){
            entityAnimationDataMap.put(dataKey, new Data<>(dataKey));
        }
        return (Data<D>) entityAnimationDataMap.get(dataKey);
    }

    public TreeMap<String, Data<?>> getDebugData(){
        TreeMap<String, Data<?>> finalList = Maps.newTreeMap();
        for(DataKey<?> dataKey : this.entityAnimationDataMap.keySet()){
            Data<?> data = entityAnimationDataMap.get(dataKey);

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

    /**
     * Increments a float value based on a condition, incremented in ticks
     *
     * @param dataKey           Float data key
     * @param condition         Boolean condition to decide whether the value should increment or decrement
     * @param ticksToIncrement  Time in ticks to increment from 0 to 1
     * @param ticksToDecrement  Time in ticks to decrement from 1 to 0
     */
    public void incrementInTicksFromCondition(DataKey<Float> dataKey, boolean condition, float ticksToIncrement, float ticksToDecrement){
        Data<Float> data = this.get(dataKey);
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
    public void incrementInFramesFromCondition(DataKey<Float> dataKey, boolean condition, float framesToIncrement, float framesToDecrement){
        this.incrementInTicksFromCondition(dataKey, condition, framesToIncrement / 1.2F, framesToDecrement / 1.2F);
    }

    /**
     * Increments or resets a value based on a condition, incremented in ticks
     *
     * @param dataKey           Float data key
     * @param condition         Boolean condition to decide whether the value should reset to 0 or increment
     * @param ticksToIncrement  Time in ticks to increment from 0 to 1
     */
    public void incrementInTicksOrResetFromCondition(DataKey<Float> dataKey, boolean condition, float ticksToIncrement){
        Data<Float> data = this.get(dataKey);
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
    public void incrementInFramesOrResetFromCondition(DataKey<Float> dataKey, boolean condition, float framesToIncrement){
        this.incrementInTicksOrResetFromCondition(dataKey, condition, framesToIncrement / 1.2F);
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
        Data<Float> dataMain = this.get(dataKeyMain);
        Data<Integer> dataIndex = this.get(dataKeyIndex);
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

    public static class Data<D>{

        private D value;
        private D valueOld;
        private final D defaultValue;

        public Data(DataKey<D> dataKey){
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
