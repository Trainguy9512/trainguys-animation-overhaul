package com.trainguy9512.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy9512.animationoverhaul.access.LivingEntityAccess;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class MixinInventoryScreen {
    @Inject(method = "renderEntityInInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;setRenderShadow(Z)V"))
    private static void setRendererToEntity(GuiGraphics guiGraphics, float f, float g, float h, Vector3f vector3f, Quaternionf quaternionf, Quaternionf quaternionf2, LivingEntity livingEntity, CallbackInfo ci){
        ((LivingEntityAccess)livingEntity).setUseInventoryRenderer(true);
    }
}
