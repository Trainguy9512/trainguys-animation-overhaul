package com.trainguy.animationoverhaul;

import com.mojang.brigadier.LiteralMessage;
import com.trainguy.animationoverhaul.commands.DebugCommands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimationOverhaul implements ModInitializer {

	public static final String MOD_ID = "animation_overhaul";
	public static Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {if (!dedicated) {DebugCommands.registerDebugCommands(dispatcher);}});
	}
}
