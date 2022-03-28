package gg.moonflower.animationoverhaul.mixin.debug;

import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import gg.moonflower.animationoverhaul.util.config.AOConfig;
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
    private void useDebugCapeTexture(CallbackInfoReturnable<ResourceLocation> cir) {
        if (gg.moonflower.animationoverhaul.AnimationOverhaulMain.getConfig().isEnableDebugCape()) {
            cir.setReturnValue(debugCapeLocation);
        }
    }
}
