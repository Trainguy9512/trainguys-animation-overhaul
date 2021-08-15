package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRenderer {
    @Inject(method = "scale", at = @At("HEAD"))
    private void addRunningRotation(AbstractClientPlayer abstractClientPlayer, PoseStack poseStack, float f, CallbackInfo ci){
        float h = Mth.lerp(f, abstractClientPlayer.yBodyRotO, abstractClientPlayer.yBodyRot);
        float j = Mth.lerp(f, abstractClientPlayer.yHeadRotO, abstractClientPlayer.yHeadRot);
        float k = j - h;
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(Mth.clamp(abstractClientPlayer.animationSpeed * 10 - 9, 0, 1), 0, k / -4)));
    }
}
