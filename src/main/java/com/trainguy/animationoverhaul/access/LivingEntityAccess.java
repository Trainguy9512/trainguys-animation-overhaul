package com.trainguy.animationoverhaul.access;

import net.minecraft.core.BlockPos;

public interface LivingEntityAccess {
    float getAnimationVariable(String animationVariable);
    void setAnimationVariable(String animationVariable, float newValue);
    void setRecordPlayerNearbyValues(String songName, boolean songPlaying, BlockPos songOrigin);
    boolean getIsSongPlaying();
    BlockPos getSongOrigin();
    String getSongName();
}
