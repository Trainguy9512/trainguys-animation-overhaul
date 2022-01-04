package gg.moonflower.animationoverhaul.mixin.models;

import gg.moonflower.animationoverhaul.access.ModelAccess;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Unique
@Mixin(PiglinModel.class)
public class MixinPiglinModel implements ModelAccess {

    private ModelPart rootModelPart;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void getRootModelPart(ModelPart modelPart, CallbackInfo ci){
        this.rootModelPart = modelPart;
    }

    @Override
    public ModelPart getModelPart(String identifier) {
        return this.rootModelPart.getChild(identifier);
    }
}
