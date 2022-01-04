package gg.moonflower.animationoverhaul.mixin;

import gg.moonflower.animationoverhaul.access.EntityAccess;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;

@Unique
@Mixin(Entity.class)
public abstract class MixinEntity implements EntityAccess {

    @Shadow public abstract EntityType<?> getType();

    //private final HashMap<String, Float> animationTimers = new HashMap<String, Float>();
    private final EntityAnimationData entityAnimationData = new EntityAnimationData();

    /*
    public void incrementAnimationTimer(String identifier, boolean isIncreasing, int ticksToIncrement, int ticksToDecrement) {
        if(ticksToIncrement != 0 && ticksToDecrement != 0){
            incrementAnimationTimer(identifier, isIncreasing, (1/(float)ticksToIncrement), (1/(float)ticksToDecrement));
        } else {
            System.out.println("Invalid tick increment/decrement specified for timer increment for timer " + identifier + " in entity part animator " + this.getType().toShortString() + ", tick values must not be 0!");
        }
    }
    public void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement){
        incrementAnimationTimer(identifier, isIncreasing, increment, decrement, 0, 1);
    }
    public void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement, float min, float max){
        float previousTimerValue = getAnimationTimer(identifier);
        float delta = Minecraft.getInstance().getDeltaFrameTime();
        setAnimationTimer(identifier, Mth.clamp(previousTimerValue + (isIncreasing ? increment * delta : decrement * delta), min, max));
    }
    public void resetTimerOnCondition(String identifier, boolean condition, int ticksToIncrement){
        if(condition){
            setAnimationTimer(identifier, 0);
        } else {
            initAnimationTimer(identifier, 1);
            incrementAnimationTimer(identifier, true, ticksToIncrement, 10);
        }
    }
    public void setAnimationTimer(String identifier, float value){
        animationTimers.put(identifier, value);
    }
    public void initAnimationTimer(String identifier, float value){
        if(!animationTimers.containsKey(identifier)){
            animationTimers.put(identifier, value);
        }
    }
    public float getAnimationTimer(String identifier){
        initAnimationTimer(identifier, 0);
        return animationTimers.get(identifier);
    }
    public HashMap<String, Float> getAnimationTimers(){
        return animationTimers;
    }



     */


    public EntityAnimationData getEntityAnimationData(){
        return this.entityAnimationData;
    }
}
