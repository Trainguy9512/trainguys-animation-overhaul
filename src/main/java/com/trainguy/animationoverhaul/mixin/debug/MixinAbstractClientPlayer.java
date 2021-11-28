package com.trainguy.animationoverhaul.mixin.debug;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public class MixinAbstractClientPlayer {

    private static final ResourceLocation debugCapeLocation = new ResourceLocation("textures/testcape.png");

    @Inject(method = "getCloakTextureLocation", at = @At("HEAD"), cancellable = true)
    private void useDebugCapeTexture(CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue(debugCapeLocation);
    }
}
