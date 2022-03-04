package gg.moonflower.animationoverhaul.mixin.pestomodels;

import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MeshDefinition.class)
public class MixinMeshDefinition {

    @Inject(method = "getRoot", at = @At("HEAD"))
    private void startModel(CallbackInfoReturnable<PartDefinition> cir){
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------Begin new entity--------------");
        System.out.println("");
    }
}
