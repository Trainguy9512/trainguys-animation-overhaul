package gg.moonflower.animationoverhaul.core.forge;

import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import net.minecraftforge.fml.common.Mod;

@Mod(AnimationOverhaulMain.MOD_ID)
public class AnimationOverhaulForge {
    public AnimationOverhaulForge() {
        AnimationOverhaulMain.PLATFORM.setup();
    }
}
