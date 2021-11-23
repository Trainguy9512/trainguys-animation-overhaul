package com.trainguy.animationoverhaul.mixin.debug;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy.animationoverhaul.access.EntityAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

@Mixin(DebugScreenOverlay.class)
public abstract class MixinDebugScreenOverlay extends GuiComponent {
    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private Font font;

    @Shadow @Final private static int COLOR_GREY;

    @Shadow protected abstract int colorLerp(int i, int j, float f);

    @Inject(method = "drawSystemInformation", at = @At("HEAD"), cancellable = true)
    private void drawTimerDebugInfo(PoseStack poseStack, CallbackInfo ci){
        boolean shouldRenderDebugTimers = true;
        if(shouldRenderDebugTimers){
            Entity entity = Minecraft.getInstance().player;
            assert entity != null;
            TreeMap<String, Float> animationTimers = new TreeMap<>(((EntityAccess)entity).getAnimationTimers());

            DecimalFormat format = new DecimalFormat("0.00");
            if(animationTimers.size() > 0){

                for(int i = 0; i < animationTimers.size(); i++){
                    String key = animationTimers.keySet().stream().toList().get(i);
                    float value = animationTimers.get(key);
                    boolean isWithinRange = value <= 1 && value >= 0;
                    int color = isWithinRange ? (int) (this.colorLerp(6553600, 65280, value)) : COLOR_GREY;

                    String string;
                    int j = 9;
                    int m = 2 + j * i;
                    m += j + 4;
                    if(isWithinRange && !this.minecraft.options.renderFpsChart){
                        string = key + ":";
                    } else {
                        string = key + ": " + format.format(value);
                    }
                    int k = this.font.width(isWithinRange ? string + " 0.00" : string);
                    int l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                    Objects.requireNonNull(this.font);
                    fill(poseStack, l - 1, m - 1, l + k + 1, m + j - 1, -1873784752);
                    this.font.draw(poseStack, string, (float)l, (float)m, COLOR_GREY);

                    if(isWithinRange){
                        k = this.font.width("0.00");
                        k *= value;
                        l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                        int k2 = (int) (k / value);
                        int l2 = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                        fill(poseStack, l - 1, m, l + k, m + j - 2, -2);
                        fill(poseStack, l2 - 1, m, l2 + k2, m + j - 2, COLOR_GREY);
                    }
                }


                String string = "Selected entity: " + entity.getName().getString() + " (" + entity.getType().toShortString() + ")";
                int j = 9;
                int m = 2;
                int k = this.font.width(string);
                int l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                Objects.requireNonNull(this.font);
                fill(poseStack, l - 1, m - 1, l + k + 1, m + j - 1, -1873784752);
                this.font.draw(poseStack, string, (float)l, (float)m, COLOR_GREY);

            } else {
                String string = "Animation timers not initiated!";
                Objects.requireNonNull(this.font);
                int j = 9;
                int k = this.font.width(string);
                int l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                int m = 2;
                fill(poseStack, l - 1, m - 1, l + k + 1, m + j - 1, -1873784752);
                this.font.draw(poseStack, string, (float)l, (float)m, COLOR_GREY);
            }


            ci.cancel();
        }
    }
}
