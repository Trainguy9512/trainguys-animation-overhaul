package com.trainguy.animationoverhaul.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.commands.CommandSourceStack;

public class DebugCommands {
    public static void registerDebugCommands(CommandDispatcher<CommandSourceStack> dispatcher){
        CommandModifyParameter.register(dispatcher);
    }
}
