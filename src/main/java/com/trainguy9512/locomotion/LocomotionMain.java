package com.trainguy9512.locomotion;


import com.trainguy9512.locomotion.animation.animator.JointAnimatorRegistry;
import com.trainguy9512.locomotion.animation.animator.entity.FirstPersonPlayerJointAnimator;
import com.trainguy9512.locomotion.animation.data.AnimationSequenceDataLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocomotionMain implements ModInitializer {


	public static final String MOD_ID = "locomotion";


	public static Logger LOGGER = LogManager.getLogger();


	public static void onClientInit() {
		registerTimelineGroupLoader();
		registerEntityAnimators();
		//registerBlockRenderers();
	}

	/*
	public static void onClientPostInit(Platform.ModSetupContext ctx) {
	}

	public static void onCommonInit() {
	}

	public static void onCommonPostInit(P.ModSetupContext ctx) {
	}

	 */


	private static void registerTimelineGroupLoader(){
		ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new AnimationSequenceDataLoader());
	}

	@Override
	public void onInitialize() {
		onClientInit();
	}


	private static void registerEntityAnimators(){
		JointAnimatorRegistry.registerFirstPersonPlayerJointAnimator(new FirstPersonPlayerJointAnimator());
	}

	/*
	private static void registerBlockRenderers(){
		registerBlockRenderer(new PressurePlateBlockRenderer(), PressurePlateBlockRenderer.PRESSURE_PLATES);
		registerBlockRenderer(new ButtonBlockRenderer(), ButtonBlockRenderer.BUTTONS);
		registerBlockRenderer(new TrapDoorBlockRenderer(), TrapDoorBlockRenderer.TRAPDOORS);
		registerBlockRenderer(new LeverBlockRenderer(), LeverBlockRenderer.LEVERS);
		registerBlockRenderer(new EndPortalFrameBlockRenderer(), EndPortalFrameBlockRenderer.END_PORTAL_BLOCKS);
		registerBlockRenderer(new ChainedBlockRenderer(), ChainedBlockRenderer.CHAINED_BLOCKS);
		//registerBlockRenderer(new FloatingPlantBlockRenderer(), FloatingPlantBlockRenderer.FLOATING_PLANTS);
	}
	 */


	/*
	private static void registerBlockRenderer(TickableBlockRenderer tickableBlockRenderer, Block[] blocks){
		for(Block block : blocks){
			BlockRendererRegistry.register(block, tickableBlockRenderer);
		}
	}
	 */

}