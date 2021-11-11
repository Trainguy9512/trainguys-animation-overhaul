package com.trainguy.animationoverhaul;

import com.google.gson.Gson;
import com.trainguy.animationoverhaul.commands.DebugCommands;
import com.trainguy.animationoverhaul.util.AnimationDataLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

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
