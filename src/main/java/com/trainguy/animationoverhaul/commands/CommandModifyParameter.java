package com.trainguy.animationoverhaul.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;

import java.util.Collection;
import java.util.Locale;

public class CommandModifyParameter {
    //broken, do not use
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("modifyparameter");
        for(Parameter parameter : Parameter.values()){
            command.then(Commands.argument("targets", EntityArgument.entities())
                    .then(Commands.literal(parameter.name().toLowerCase(Locale.ROOT))
                            .then(Commands.argument("value", FloatArgumentType.floatArg())
                                    .executes(context -> run(context, EntityArgument.getEntities(context, "targets"), parameter, FloatArgumentType.getFloat(context, "value"), false)))
                            .then(Commands.literal("clear")
                                    .executes(context -> run(context, EntityArgument.getEntities(context, "targets"), parameter, 0, true)))));
        }
        dispatcher.register(command);
    }
    public static int run(CommandContext<CommandSourceStack> context, Collection<? extends Entity> targetedEntities, CommandModifyParameter.Parameter parameter, float value, boolean clear) throws CommandSyntaxException {
        for(Entity entity : targetedEntities){
            if(entity.isAlive()){
                //((LivingEntityAccess)entity).setDebugValues(parameter, value, clear);
            }
        }
        if(clear){
            context.getSource().sendSuccess(new TextComponent("Cleared debug parameters for " + targetedEntities.size() + " entities"), true);
        } else {
            context.getSource().sendSuccess(new TextComponent("Set debug parameter " + parameter.toString().toLowerCase(Locale.ROOT) + " to value " + value + " for " + targetedEntities.size() + " entities"), true);
        }
        return 0;
    }
    public static enum Parameter {
        DISTANCE,
        MOVESPEED,
        HEADYROT,
        HEADXROT;
    }
}
