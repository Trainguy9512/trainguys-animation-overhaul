package com.trainguy9512.animationoverhaul;


import com.trainguy9512.animationoverhaul.animation.entity.PlayerPartAnimator;
import com.trainguy9512.animationoverhaul.util.data.LivingEntityAnimatorRegistry;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupDataLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimationOverhaulMain implements ModInitializer {


	public static final String MOD_ID = "animation_overhaul";


	public static Logger LOGGER = LogManager.getLogger();

	public static final LivingEntityAnimatorRegistry ENTITY_ANIMATORS = new LivingEntityAnimatorRegistry();
	public static Entity debugEntity;


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
		ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new TimelineGroupDataLoader());
		//ResourceRegistry.registerReloadListener(PackType.CLIENT_RESOURCES, new TimelineGroupDataLoader());
	}

	@Override
	public void onInitialize() {
		onClientInit();
	}


	private static void registerEntityAnimators(){
		//ENTITY_ANIMATORS.register(EntityType.PLAYER, new PlayerPartAnimator());
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