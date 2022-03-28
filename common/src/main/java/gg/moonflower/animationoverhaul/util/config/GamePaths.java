package gg.moonflower.animationoverhaul.util.config;

import java.nio.file.Path;

import gg.moonflower.animationoverhaul.mixin.config.MixinPlayerSkinProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

public class GamePaths {
    private GamePaths() {
    }

    public static Path getGameDirectory() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static Path getAssetsDirectory() {
        return ((MixinPlayerSkinProvider)Minecraft.getInstance().getSkinManager()).getSkinCacheDirectory().getParentFile().toPath();
    }
}
