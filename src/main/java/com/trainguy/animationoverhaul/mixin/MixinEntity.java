package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.EntityAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

import java.util.HashMap;

@Mixin(Entity.class)
public class MixinEntity implements EntityAccess {

    private HashMap<String, Float> animationTimers = new HashMap<String, Float>();

    // Animation timers
    public void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement){
        incrementAnimationTimer(identifier, isIncreasing, increment, decrement, 0, 1);
    }
    public void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement, float min, float max){
        float previousTimerValue = getAnimationTimer(identifier);
        float delta = Minecraft.getInstance().getDeltaFrameTime();
        setAnimationTimer(identifier, Mth.clamp(previousTimerValue + (isIncreasing ? increment * delta : decrement * delta), min, max));
    }
    public void setAnimationTimer(String identifier, float value){
        animationTimers.put(identifier, value);
    }
    public float getAnimationTimer(String identifier){
        if(!animationTimers.containsKey(identifier)){
            animationTimers.put(identifier, 0F);
        }
        return animationTimers.get(identifier);
    }
}
