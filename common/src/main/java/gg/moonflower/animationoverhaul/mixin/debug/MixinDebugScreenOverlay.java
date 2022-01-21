package gg.moonflower.animationoverhaul.mixin.debug;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import gg.moonflower.animationoverhaul.access.EntityAccess;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import gg.moonflower.pollen.api.client.render.ShapeRenderer;
import gg.moonflower.pollen.pinwheel.api.common.animation.AnimationData;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.text.DecimalFormat;
import java.util.*;

@Mixin(DebugScreenOverlay.class)
public abstract class MixinDebugScreenOverlay extends GuiComponent {

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private Font font;

    @Shadow @Final private static int COLOR_GREY;

    @Shadow protected abstract int colorLerp(int i, int j, float f);

    @Shadow private HitResult block;

    @Shadow private HitResult liquid;

    @Inject(method = "drawSystemInformation", at = @At("HEAD"), cancellable = true)
    private void drawTimerDebugInfo(PoseStack poseStack, CallbackInfo ci){

        if(this.minecraft.options.fov == 72){
            poseStack.translate(this.minecraft.getWindow().getGuiScaledWidth() / 4F, 0, 0);
            poseStack.scale(0.75F, 0.75F, 0.75F);

            drawTimerDebug(poseStack);

            poseStack.scale(1/0.75F, 1/0.75F, 1/0.75F);
            poseStack.translate(-this.minecraft.getWindow().getGuiScaledWidth() / 4F, 0, 0);
            ci.cancel();
        }
    }

    private void drawTimerDebug(PoseStack poseStack){
        boolean shouldRenderDebugTimers = true;
        Entity entity = AnimationOverhaulMain.debugEntity;

        entity = minecraft.player;

        if(entity != null){

            EntityAnimationData entityAnimationData = ((EntityAccess)entity).getEntityAnimationData();
            TreeMap<String, EntityAnimationData.Data<?>> debugData = entityAnimationData.getDebugData();

            DecimalFormat format = new DecimalFormat("0.00");
            if(debugData.size() > 0){

                for(int i = 0; i < debugData.size(); i++){
                    String key = debugData.keySet().stream().toList().get(i);
                    EntityAnimationData.Data<?> data = debugData.get(key);

                    boolean isFloat = data.get() instanceof Float;
                    boolean isWithinRange = false;
                    if(isFloat){
                        float value = (float) data.get();
                        isWithinRange = value <= 1 && value >= 0;
                    }

                    String string;
                    int j = 9;
                    int m = 2 + j * i;
                    m += j + 4;
                    if(isWithinRange && !this.minecraft.options.renderFpsChart && isFloat){
                        string = key;
                    } else if(isFloat) {
                        string = key + " " + format.format(data.get());
                    } else {
                        string = key + " " + data.get().toString();
                    }
                    int k = this.font.width(isWithinRange ? string + " 0.00" : string);
                    int l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                    Objects.requireNonNull(this.font);
                    fill(poseStack, l - 1, m - 1, l + k + 1, m + j - 1, -1873784752);
                    this.font.draw(poseStack, string, (float)l, (float)m, COLOR_GREY);

                    if(isWithinRange){
                        float value = (float) data.get();
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
        }
    }
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void testCompassGadget(PoseStack poseStack, CallbackInfo ci){
        if(this.minecraft.options.fov == 73){
            ci.cancel();
        }
    }
}
