package gg.moonflower.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import gg.moonflower.animationoverhaul.render.DebugGadgetRenderer;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class MixinGui {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void injectDebugGadgetRenderMethod(PoseStack poseStack, float f, CallbackInfo ci){
        poseStack.pushPose();
        DebugGadgetRenderer.INSTANCE.render(poseStack, f);
        poseStack.popPose();
    }

    @Inject(method = "tick()V", at = @At("HEAD"))
    private void injectDebugGadgetTickMethod(CallbackInfo ci){
        DebugGadgetRenderer.INSTANCE.tick();
    }
}
