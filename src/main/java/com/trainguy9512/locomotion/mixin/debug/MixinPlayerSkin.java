package com.trainguy9512.locomotion.mixin.debug;

import com.trainguy9512.locomotion.LocomotionMain;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerSkin.class)
public class MixinPlayerSkin {

    private static final ResourceLocation debugCapeLocation = ResourceLocation.fromNamespaceAndPath(LocomotionMain.MOD_ID,"textures/testcape.png");

    @Inject(method = "capeTexture", at = @At("HEAD"), cancellable = true)
    private void useDebugCapeTexture(CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue(debugCapeLocation);
    }
}
