package com.trainguy9512.animationoverhaul.mixin.models;

import com.trainguy9512.animationoverhaul.access.ModelAccess;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Unique
@Mixin(PlayerModel.class)
public class MixinPlayerModel implements ModelAccess {

    private ModelPart rootModelPart;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void getRootModelPart(ModelPart modelPart, boolean bl, CallbackInfo ci){
        this.rootModelPart = modelPart;
    }

    @Override
    public ModelPart getModelPart(String identifier) {
        return this.rootModelPart.getChild(identifier);
    }

    @Override
    public ModelPart getRootModelPart() {
        return this.rootModelPart;
    }
}
