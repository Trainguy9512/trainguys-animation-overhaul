package com.trainguy.animationoverhaul.access;

public interface EntityAccess {
    void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement);
    void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement, float min, float max);
    void setAnimationTimer(String identifier, float value);
    float getAnimationTimer(String identifier);
}
