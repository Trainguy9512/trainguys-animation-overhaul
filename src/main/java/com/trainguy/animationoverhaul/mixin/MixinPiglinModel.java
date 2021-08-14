package com.trainguy.animationoverhaul.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinModel.class)
public class MixinPiglinModel<T extends Mob> extends PlayerModel<T> {
    public MixinPiglinModel(ModelPart modelPart, boolean bl) {
        super(modelPart, bl);
    }

    @Inject(method = "setupAnim", at = @At("HEAD"), cancellable = true)
    private void animPiglinModel(T mob, float f, float g, float h, float i, float j, CallbackInfo ci){
        super.setupAnim(mob, f, g, h, i, j);
        ci.cancel();
    }
}
