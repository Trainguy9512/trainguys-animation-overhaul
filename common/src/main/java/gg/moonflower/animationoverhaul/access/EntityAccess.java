package gg.moonflower.animationoverhaul.access;

import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;

import java.util.HashMap;

public interface EntityAccess {
    // Legacy stuff, remove after finishing the removal of the part animator mixins
    //@Deprecated void incrementAnimationTimer(String identifier, boolean isIncreasing, int ticksToIncrement, int ticksToDecrement);
    //@Deprecated void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement);
    //@Deprecated void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement, float min, float max);
    //@Deprecated void resetTimerOnCondition(String identifier, boolean condition, int ticksToIncrement);

    //void initAnimationTimer(String identifier, float value);
    //void setAnimationTimer(String identifier, float value);
    //@Deprecated float getAnimationTimer(String identifier);
    //HashMap<String, Float> getAnimationTimers();

    EntityAnimationData getEntityAnimationData();
}
