package com.trainguy.animationoverhaul;

import com.trainguy.animationoverhaul.util.AnimationDataLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimationOverhaul implements ModInitializer {

	public static final String MOD_ID = "animation_overhaul";
	public static Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		registerTimelineGroupLoader();
	}

	private void registerTimelineGroupLoader(){
		ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new AnimationDataLoader());
	}
}
