package gg.moonflower.animationoverhaul.mixin.pestomodels;

import gg.moonflower.animationoverhaul.pestomodels.ModelOutputImpl;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PartDefinition.class)
public class MixinPartDefinition {
    @Shadow @Final private PartPose partPose;

    @Inject(method = "addOrReplaceChild", at = @At("HEAD"))
    private void addModelPartOutput(String string, CubeListBuilder cubeListBuilder, PartPose partPose, CallbackInfoReturnable<PartDefinition> cir){
        ModelOutputImpl.outputPartDefinition(string, partPose, cubeListBuilder, this.partPose);
    }
}
