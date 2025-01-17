package com.trainguy9512.animationoverhaul.mixin.debug;

import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerSkin.class)
public class MixinPlayerSkin {

    private static final ResourceLocation debugCapeLocation = ResourceLocation.fromNamespaceAndPath(AnimationOverhaulMain.MOD_ID,"textures/testcape.png");

    @Inject(method = "capeTexture", at = @At("HEAD"), cancellable = true)
    private void useDebugCapeTexture(CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue(debugCapeLocation);
    }
}
