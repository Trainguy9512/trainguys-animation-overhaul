package gg.moonflower.animationoverhaul.util.config;

import gg.moonflower.animationoverhaul.AnimationOverhaulMain;

import java.nio.file.Path;

public class AOConfig extends JsonFile {
    private transient AnimationOverhaulMain main;

    public AOConfig(Path file, AnimationOverhaulMain animationOverhaulMain) {
        super(file);
        this.main = main;
    }

    private boolean enableMobAnimations = true;
    private boolean enableDebugCape = true;

    public boolean isEnableMobAnimations() {
        return enableMobAnimations;
    }

    public boolean isEnableDebugCape() {
        return enableDebugCape;
    }
}
