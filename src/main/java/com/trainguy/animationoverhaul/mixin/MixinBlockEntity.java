package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.BlockEntityAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockEntity.class)
public class MixinBlockEntity implements BlockEntityAccess {
    public float timer;
    public float chestOpenProgress;
    public float chestCloseProgress;
    public float chestShakeProgress = 1;
    public float isDoubleChest;
    public float previousOpenAmount;

    public float getAnimationVariable(String variableType){
        return switch (variableType) {
            case "timer" -> timer;
            case "chestOpenProgress" -> chestOpenProgress;
            case "chestCloseProgress" -> chestCloseProgress;
            case "chestShakeProgress" -> chestShakeProgress;
            case "isDoubleChest" -> isDoubleChest;
            case "previousOpenAmount" -> previousOpenAmount;
            default -> 0;
        };
    }
    public void setAnimationVariable(String variableType, float newValue){
        switch (variableType) {
            case "timer" -> timer = newValue;
            case "chestOpenProgress" -> chestOpenProgress = newValue;
            case "chestCloseProgress" -> chestCloseProgress = newValue;
            case "chestShakeProgress" -> chestShakeProgress = newValue;
            case "isDoubleChest" -> isDoubleChest = newValue;
            case "previousOpenAmount" -> previousOpenAmount = newValue;
            default -> System.out.println("Invalid variable type: " + variableType);
        }
    }
}
