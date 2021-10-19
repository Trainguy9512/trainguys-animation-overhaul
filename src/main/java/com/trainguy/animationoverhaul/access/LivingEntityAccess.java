package com.trainguy.animationoverhaul.access;

import com.trainguy.animationoverhaul.commands.CommandModifyParameter;
import com.trainguy.animationoverhaul.util.LivingEntityAnimParams;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

public interface LivingEntityAccess {
    //float getAnimationVariable(String animationVariable);
    //void setAnimationVariable(String animationVariable, float newValue);
    void setRecordPlayerNearbyValues(String songName, boolean songPlaying, BlockPos songOrigin);
    boolean getIsSongPlaying();
    BlockPos getSongOrigin();
    String getSongName();
    String getPreviousEquippedArmor();
    void setEquippedArmor(String currentArmor);

    void setAnimationParamaters(float animationPosition, float animationSpeed, float tickAtFrame, float tickDifference, float delta, float headYRot, float headXRot);
    LivingEntityAnimParams getAnimationParameters();
}
