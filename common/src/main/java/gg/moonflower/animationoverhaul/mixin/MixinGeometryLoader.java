package gg.moonflower.animationoverhaul.mixin;

import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import gg.moonflower.pollen.pinwheel.api.client.geometry.GeometryModel;
import gg.moonflower.pollen.pinwheel.api.client.geometry.GeometryModelManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(GeometryModelManager.class)
public class MixinGeometryLoader {
    @Shadow @Final private static Map<ResourceLocation, GeometryModel> MODELS;

    @Inject(method = "getModel", at = @At("HEAD"))
    private static void bbbb(ResourceLocation location, CallbackInfoReturnable<GeometryModel> cir){
        AnimationOverhaulMain.LOGGER.warn(MODELS.keySet());
    }
}
