package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.AbstractMinecartAccess;
import com.trainguy.animationoverhaul.access.BlockEntityAccess;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Unique
@Mixin(AbstractMinecart.class)
public class MixinAbstractMinecart implements AbstractMinecartAccess {
    private float minecartSpeed = 0;
    private float trackBumpAmount = 0;

    public float getAnimationVariable(String animationVariable) {
        return switch (animationVariable) {
            case "minecartSpeed" -> minecartSpeed;
            case "trackBumpAmount" -> trackBumpAmount;
            default -> 0;
        };
    }

    public void setAnimationVariable(String animationVariable, float newValue) {
        switch (animationVariable) {
            case "minecartSpeed" -> minecartSpeed = newValue;
            case "trackBumpAmount" -> trackBumpAmount = newValue;
            default -> System.out.println("Invalid variable type: " + animationVariable);
        }
    }
}
