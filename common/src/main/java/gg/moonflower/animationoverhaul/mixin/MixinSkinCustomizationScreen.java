package gg.moonflower.animationoverhaul.mixin;

import gg.moonflower.animationoverhaul.gui.TestScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.SkinCustomizationScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Unique
@Mixin(SkinCustomizationScreen.class)
public class MixinSkinCustomizationScreen {

    private Screen previousScreen;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void getPreviousScreen(Screen screen, Options options, CallbackInfo ci){
        this.previousScreen = screen;
    }

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void redirectSkinCustomizationScreen(CallbackInfo ci){
        Minecraft.getInstance().setScreen(new TestScreen(previousScreen));
        ci.cancel();
    }
}
