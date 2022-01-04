package gg.moonflower.animationoverhaul.core.fabric;

import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import net.fabricmc.api.ModInitializer;

public class AnimationOverhaulFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AnimationOverhaulMain.PLATFORM.setup();
    }
}
