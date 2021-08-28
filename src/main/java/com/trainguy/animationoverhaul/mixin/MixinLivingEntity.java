package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Unique
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements LivingEntityAccess {
    public String songName;
    public boolean songPlaying;
    public BlockPos songOrigin;

    public float crouchAmount;
    public float verticalMovementRotation;
    public float sprintAmount;
    public float inWaterAmount;
    public float eatingAmount;

    public float dancingAmount;
    public float dancingFrequency;

    public float idleAmount = 1;
    public float battleIdleAmount;
    public float attackIndex;

    public float attackAmount = 1;
    public float useAlternateAttack;

    public float leftArmItemPoseAmount;
    public float rightArmItemPoseAmount;

    public float leftArmBowPoseAmount;
    public float rightArmBowPoseAmount;

    public float leftArmSpearPoseAmount;
    public float rightArmSpearPoseAmount;

    public float leftArmCrossbowPoseAmount;
    public float rightArmCrossbowPoseAmount;

    public float leftArmShieldPoseAmount;
    public float rightArmShieldPoseAmount;

    public float leftArmSpyglassPoseAmount;
    public float rightArmSpyglassPoseAmount;

    public float getAnimationVariable(String variableType){
        return switch (variableType) {
            case "dancingAmount" -> dancingAmount;
            case "dancingFrequency" -> dancingFrequency;
            case "crouchAmount" -> crouchAmount;
            case "idleAmount" -> idleAmount;
            case "battleIdleAmount" -> battleIdleAmount;
            case "sprintAmount" -> sprintAmount;
            case "inWaterAmount" -> inWaterAmount;
            case "eatingAmount" -> eatingAmount;
            case "attackAmount" -> attackAmount;
            case "useAlternateAttack" -> useAlternateAttack;
            case "verticalMovementRotation" -> verticalMovementRotation;
            case "leftArmItemPoseAmount" -> leftArmItemPoseAmount;
            case "rightArmItemPoseAmount" -> rightArmItemPoseAmount;
            case "leftArmBowPoseAmount" -> leftArmBowPoseAmount;
            case "rightArmBowPoseAmount" -> rightArmBowPoseAmount;
            case "leftArmSpearPoseAmount" -> leftArmSpearPoseAmount;
            case "rightArmSpearPoseAmount" -> rightArmSpearPoseAmount;
            case "leftArmCrossbowPoseAmount" -> leftArmCrossbowPoseAmount;
            case "rightArmCrossbowPoseAmount" -> rightArmCrossbowPoseAmount;
            case "leftArmShieldPoseAmount" -> leftArmShieldPoseAmount;
            case "rightArmShieldPoseAmount" -> rightArmShieldPoseAmount;
            case "leftArmSpyglassPoseAmount" -> leftArmSpyglassPoseAmount;
            case "rightArmSpyglassPoseAmount" -> rightArmSpyglassPoseAmount;
            default -> 0;
        };
    }
    public void setAnimationVariable(String variableType, float newValue){
        switch (variableType) {
            case "dancingAmount" -> dancingAmount = newValue;
            case "dancingFrequency" -> dancingFrequency = newValue;
            case "crouchAmount" -> crouchAmount = newValue;
            case "idleAmount" -> idleAmount = newValue;
            case "battleIdleAmount" -> battleIdleAmount = newValue;
            case "sprintAmount" -> sprintAmount = newValue;
            case "eatingAmount" -> eatingAmount = newValue;
            case "inWaterAmount" -> inWaterAmount = newValue;
            case "attackAmount" -> attackAmount = newValue;
            case "useAlternateAttack" -> useAlternateAttack = newValue;
            case "verticalMovementRotation" -> verticalMovementRotation = newValue;
            case "leftArmItemPoseAmount" -> leftArmItemPoseAmount = newValue;
            case "rightArmItemPoseAmount" -> rightArmItemPoseAmount = newValue;
            case "leftArmBowPoseAmount" -> leftArmBowPoseAmount = newValue;
            case "rightArmBowPoseAmount" -> rightArmBowPoseAmount = newValue;
            case "leftArmSpearPoseAmount" -> leftArmSpearPoseAmount = newValue;
            case "rightArmSpearPoseAmount" -> rightArmSpearPoseAmount = newValue;
            case "leftArmCrossbowPoseAmount" -> leftArmCrossbowPoseAmount = newValue;
            case "rightArmCrossbowPoseAmount" -> rightArmCrossbowPoseAmount = newValue;
            case "leftArmShieldPoseAmount" -> leftArmShieldPoseAmount = newValue;
            case "rightArmShieldPoseAmount" -> rightArmShieldPoseAmount = newValue;
            case "leftArmSpyglassPoseAmount" -> leftArmSpyglassPoseAmount = newValue;
            case "rightArmSpyglassPoseAmount" -> rightArmSpyglassPoseAmount = newValue;
            default -> System.out.println("Invalid variable type: " + variableType);
        }
    }

    public void setRecordPlayerNearbyValues(String songName, boolean songPlaying, BlockPos songOrigin){
        if(songPlaying){
            this.songName = songName;
        }
        this.songPlaying = songPlaying;
        this.songOrigin = songOrigin;
    }

    public boolean getIsSongPlaying(){
        return this.songPlaying;
    }
    public BlockPos getSongOrigin(){
        return this.songOrigin;
    }
    public String getSongName(){
        return this.songName;
    }
}
