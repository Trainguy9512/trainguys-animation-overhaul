package gg.moonflower.animationoverhaul.mixin.pestomodels;

import gg.moonflower.animationoverhaul.access.CubeDeformationAccess;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(CubeDeformation.class)
public class MixinCubeDeformation implements CubeDeformationAccess {

    @Shadow @Final private float growX;

    @Override
    public float getGrow() {
        return this.growX;
    }
}
