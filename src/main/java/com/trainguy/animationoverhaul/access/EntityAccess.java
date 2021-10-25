package com.trainguy.animationoverhaul.access;

import java.util.HashMap;

public interface EntityAccess {
    void incrementAnimationTimer(String identifier, boolean isIncreasing, int ticksToIncrement, int ticksToDecrement);
    void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement);
    void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement, float min, float max);
    void resetTimerOnCondition(String identifier, boolean condition, int ticksToIncrement);
    void setAnimationTimer(String identifier, float value);
    float getAnimationTimer(String identifier);
    HashMap<String, Float> getAnimationTimers();
}
