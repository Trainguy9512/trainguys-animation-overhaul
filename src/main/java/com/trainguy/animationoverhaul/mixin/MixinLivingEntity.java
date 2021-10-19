package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.commands.CommandModifyParameter;
import com.trainguy.animationoverhaul.util.LivingEntityAnimParams;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Unique
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements LivingEntityAccess {
    @Shadow public abstract Iterable<ItemStack> getArmorSlots();

    @Shadow public abstract void animateHurt();

    private LivingEntityAnimParams animationParameters;
    private HashMap<String, Float> animationTimers = new HashMap<String, Float>();


    public String songName;
    public boolean songPlaying;
    public BlockPos songOrigin = new BlockPos(0, 0, 0);


    public float crouchTimer;
    public float verticalMovementRotation;
    public float sprintTimer;
    public float inWaterAmount;
    public float underWaterAmount;
    public float eatingAmount;
    public float directionShift;

    public float minecartRidingAmount;

    public float dancingAmount;
    public float dancingFrequency;

    public float idleAmount = 1;
    public float battleIdleAmount;

    public float attackAmount = 1;
    public float attackIndex;

    public float armorEquipAmount = 0;

    public float leftArmItemPoseAmount;
    public float rightArmItemPoseAmount;

    public float bowPoseAmount;
    public float bowPullAmount;

    public float spearPoseAmount;

    public float leftArmCrossbowPoseAmount;
    public float rightArmCrossbowPoseAmount;

    public float shieldPoseAmount;

    public float leftArmSpyglassPoseAmount;
    public float rightArmSpyglassPoseAmount;


    public String equippedArmor = "";

    //TODO: replace this junk with hash maps! they are better !

    public float getAnimationVariable(String variableType){
        return switch (variableType) {
            case "dancingAmount" -> dancingAmount;
            case "dancingFrequency" -> dancingFrequency;
            case "crouchTimer" -> crouchTimer;
            case "idleAmount" -> idleAmount;
            case "directionShift" -> directionShift;
            case "minecartRidingAmount" -> minecartRidingAmount;
            case "battleIdleAmount" -> battleIdleAmount;
            case "sprintTimer" -> sprintTimer;
            case "inWaterAmount" -> inWaterAmount;
            case "underWaterAmount" -> underWaterAmount;
            case "eatingAmount" -> eatingAmount;
            case "attackAmount" -> attackAmount;
            case "attackIndex" -> attackIndex;
            case "armorEquipAmount" -> armorEquipAmount;
            case "verticalMovementRotation" -> verticalMovementRotation;
            case "leftArmItemPoseAmount" -> leftArmItemPoseAmount;
            case "rightArmItemPoseAmount" -> rightArmItemPoseAmount;
            case "bowPoseAmount" -> bowPoseAmount;
            case "bowPullAmount" -> bowPullAmount;
            case "spearPoseAmount" -> spearPoseAmount;
            case "leftArmCrossbowPoseAmount" -> leftArmCrossbowPoseAmount;
            case "rightArmCrossbowPoseAmount" -> rightArmCrossbowPoseAmount;
            case "shieldPoseAmount" -> shieldPoseAmount;
            case "leftArmSpyglassPoseAmount" -> leftArmSpyglassPoseAmount;
            case "rightArmSpyglassPoseAmount" -> rightArmSpyglassPoseAmount;
            default -> 0;
        };
    }
    public void setAnimationVariable(String variableType, float newValue){
        switch (variableType) {
            case "dancingAmount" -> dancingAmount = newValue;
            case "dancingFrequency" -> dancingFrequency = newValue;
            case "crouchTimer" -> crouchTimer = newValue;
            case "idleAmount" -> idleAmount = newValue;
            case "directionShift" -> directionShift = newValue;
            case "minecartRidingAmount" -> minecartRidingAmount = newValue;
            case "battleIdleAmount" -> battleIdleAmount = newValue;
            case "sprintTimer" -> sprintTimer = newValue;
            case "eatingAmount" -> eatingAmount = newValue;
            case "inWaterAmount" -> inWaterAmount = newValue;
            case "underWaterAmount" -> underWaterAmount = newValue;
            case "attackAmount" -> attackAmount = newValue;
            case "attackIndex" -> attackIndex = newValue;
            case "armorEquipAmount" -> armorEquipAmount = newValue;
            case "verticalMovementRotation" -> verticalMovementRotation = newValue;
            case "leftArmItemPoseAmount" -> leftArmItemPoseAmount = newValue;
            case "rightArmItemPoseAmount" -> rightArmItemPoseAmount = newValue;
            case "bowPoseAmount" -> bowPoseAmount = newValue;
            case "bowPullAmount" -> bowPullAmount = newValue;
            case "spearPoseAmount" -> spearPoseAmount = newValue;
            case "leftArmCrossbowPoseAmount" -> leftArmCrossbowPoseAmount = newValue;
            case "rightArmCrossbowPoseAmount" -> rightArmCrossbowPoseAmount = newValue;
            case "shieldPoseAmount" -> shieldPoseAmount = newValue;
            case "leftArmSpyglassPoseAmount" -> leftArmSpyglassPoseAmount = newValue;
            case "rightArmSpyglassPoseAmount" -> rightArmSpyglassPoseAmount = newValue;
            default -> System.out.println("Invalid variable type: " + variableType);
        }
    }

    public void setRecordPlayerNearbyValues(String songName, boolean songPlaying, BlockPos songOrigin){
        if(songPlaying){
            this.songName = songName;
            this.dancingFrequency = switch(songName){
                case "music_disc_13" -> 1F;
                case "music_disc_cat" -> 1F;
                case "music_disc_blocks" -> 1F;
                case "music_disc_chirp" -> 1F;
                case "music_disc_far" -> 1F;
                case "music_disc_mall" -> 1F;
                case "music_disc_mellohi" -> 1F;
                case "music_disc_stal" -> 1F;
                case "music_disc_strad" -> 1F;
                case "music_disc_ward" -> 1F;
                case "music_disc_11" -> 1F;
                case "music_disc_wait" -> 1F;
                case "music_disc_pigstep" -> 1F;
                default -> throw new IllegalStateException("Unexpected song name value: " + songName);
            };
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

    public void setEquippedArmor(String currentArmor){
        this.equippedArmor = currentArmor;
    }
    public String getPreviousEquippedArmor(){
        return this.equippedArmor;
    }

    public void setAnimationParamaters(float animationPosition, float animationSpeed, float tickAtFrame, float tickDifference, float delta, float headYRot, float headXRot){
        this.animationParameters = new LivingEntityAnimParams(animationPosition, animationSpeed, tickAtFrame, tickDifference, delta, headYRot, headXRot);
    }
    public LivingEntityAnimParams getAnimationParameters(){
        return this.animationParameters;
    }
    public void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement){
        incrementAnimationTimer(identifier, isIncreasing, increment, decrement, 0, 1);
    }
    public void incrementAnimationTimer(String identifier, boolean isIncreasing, float increment, float decrement, float min, float max){
        float previousTimerValue = getAnimationTimer(identifier);
        float delta = animationParameters.getDelta();
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
