package com.trainguy.animationoverhaul;

import com.trainguy.animationoverhaul.commands.DebugCommands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimationOverhaul implements ModInitializer {

	public static final String MOD_ID = "animation_overhaul";
	public static Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		//CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {if (!dedicated) {DebugCommands.registerDebugCommands(dispatcher);}});

		/*

		bezier debug stuff

		AnimCurve animCurve = new AnimCurve(3);
		//CubicBezier bezier = new CubicBezier(0, 0, 1, 1F);
		CubicBezier bezier = new CubicBezier(0.34F, 0, 0.06F, 1);
		System.out.println(
				"\n" +
				animCurve.setValue(0).oscillate(3, 0.1F).getValue() + "\n" +
				animCurve.setValue(1F).oscillate(3, 0.1F).getValue() + "\n" +
				animCurve.setValue(2F).oscillate(3, 0.1F).getValue() + "\n" +
				animCurve.setValue(3F).oscillate(3, 0.1F).getValue() + "\n" +
				"repeat" + "\n" +
				animCurve.setValue(0).repeat(1).getValue() + "\n" +
				animCurve.setValue(0.25F).repeat(1).getValue() + "\n" +
				animCurve.setValue(0.5F).repeat(1).getValue() + "\n" +
				animCurve.setValue(0.75F).repeat(1).getValue() + "\n" +
				animCurve.setValue(1F).repeat(1).getValue() + "\n" +
				animCurve.setValue(1.25F).repeat(1).getValue() + "\n" +
				"bezier" + "\n" +
				bezier.getValue(0) + "\n" +
				bezier.getValue(0.25F) + "\n" +
				bezier.getValue(0.5F) + "\n" +
				bezier.getValue(0.75F) + "\n" +
				bezier.getValue(1) + "\n"
		);

		 */

	}
}
