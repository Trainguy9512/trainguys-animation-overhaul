package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Unique
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements LivingEntityAccess {
    public float crouchAmount;
    public float idleAmount;
    public float verticalMovementRotation;
    public float sprintAmount;
    public float inWaterAmount;

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
            case "crouchAmount" -> crouchAmount;
            case "idleAmount" -> idleAmount;
            case "sprintAmount" -> sprintAmount;
            case "inWaterAmount" -> inWaterAmount;
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
            case "crouchAmount" -> crouchAmount = newValue;
            case "idleAmount" -> idleAmount = newValue;
            case "sprintAmount" -> sprintAmount = newValue;
            case "inWaterAmount" -> inWaterAmount = newValue;
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
}
