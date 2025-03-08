package com.trainguy9512.locomotion.mixin.debug;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.ItemTransform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemTransform.class)
public class MixinItemTransform {
    @Inject(
            method = "apply",
            at = @At("HEAD"),
            cancellable = true
    )
    private void removeItemTransform(boolean leftHand, PoseStack poseStack, CallbackInfo ci){
        ci.cancel();
    }
}
