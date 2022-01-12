package gg.moonflower.animationoverhaul.util.data;

import com.google.common.collect.Maps;
import net.minecraft.util.Mth;

import java.util.HashMap;
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

        public float getLerped(float partialTicks){
            if(this.value instanceof Float){
                return Mth.lerp(partialTicks, (Float)this.valueOld, (Float)this.value);
            }
            return 0;
        }
    }
}
