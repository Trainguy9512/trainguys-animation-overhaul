package gg.moonflower.animationoverhaul.util.config;

import java.nio.file.Path;

import gg.moonflower.animationoverhaul.mixin.config.MixinPlayerSkinProvider;
import net.minecraft.client.Minecraft;

public class GamePaths {
    private GamePaths() {
    }

    public static Path getGameDirectory() {
        return Minecraft.getInstance().gameDirectory.toPath();
    }
    public static Path getConfigDirectory() {
        return Minecraft.getInstance().gameDirectory.toPath().resolve("config");
    }

    public static Path getAssetsDirectory() {
        return ((MixinPlayerSkinProvider)Minecraft.getInstance().getSkinManager()).getSkinCacheDirectory().getParentFile().toPath();
    }
}
