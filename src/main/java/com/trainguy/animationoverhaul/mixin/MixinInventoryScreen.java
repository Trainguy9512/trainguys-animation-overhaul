package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.EntityAccess;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class MixinInventoryScreen {
    @Inject(method = "renderEntityInInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;setRenderShadow(Z)V"))
    private static void setRendererToEntity(int i, int j, int k, float f, float g, LivingEntity livingEntity, CallbackInfo ci){
        ((LivingEntityAccess)livingEntity).setUseInventoryRenderer(true);
    }
}
