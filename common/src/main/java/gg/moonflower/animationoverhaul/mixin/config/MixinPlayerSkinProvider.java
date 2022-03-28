package gg.moonflower.animationoverhaul.mixin.config;

import java.io.File;
import net.minecraft.client.resources.SkinManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({SkinManager.class})
public interface MixinPlayerSkinProvider {
    @Accessor("skinsDirectory")
    File getSkinCacheDirectory();
}