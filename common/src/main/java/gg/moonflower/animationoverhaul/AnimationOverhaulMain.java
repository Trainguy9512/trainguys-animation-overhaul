package gg.moonflower.animationoverhaul;

import gg.moonflower.animationoverhaul.animations.entity.CreeperPartAnimator;
import gg.moonflower.animationoverhaul.animations.entity.PlayerPartAnimator;
import gg.moonflower.animationoverhaul.render.*;
import gg.moonflower.animationoverhaul.util.data.TimelineGroupDataLoader;
import gg.moonflower.animationoverhaul.util.data.LivingEntityAnimatorRegistry;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.resource.ResourceRegistry;
import gg.moonflower.pollen.pinwheel.api.client.render.BlockRendererRegistry;
import gg.moonflower.pollen.pinwheel.api.client.render.TickableBlockRenderer;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimationOverhaulMain {


	public static final String MOD_ID = "animation_overhaul";
	public static final Platform PLATFORM = Platform.builder(MOD_ID)
			.clientInit(AnimationOverhaulMain::onClientInit)
			.clientPostInit(AnimationOverhaulMain::onClientPostInit)
			.commonInit(AnimationOverhaulMain::onCommonInit)
			.commonPostInit(AnimationOverhaulMain::onCommonPostInit)
			.build();


	public static Logger LOGGER = LogManager.getLogger();

	//public static final PollinatedRegistry<LivingEntityAnimator> ENTITY_ANIMATORS = PollinatedRegistry.createSimple(LivingEntityAnimator.class, new ResourceLocation(MOD_ID, "entity_animators"));
	public static final LivingEntityAnimatorRegistry ENTITY_ANIMATORS = new LivingEntityAnimatorRegistry();
	public static Entity debugEntity;

	public static void onClientInit() {
		registerTimelineGroupLoader();
		registerEntityAnimators();
		//registerBlockRenderers();
	}

	public static void onClientPostInit(Platform.ModSetupContext ctx) {
	}

	public static void onCommonInit() {
	}

	public static void onCommonPostInit(Platform.ModSetupContext ctx) {
	}

	private static void registerTimelineGroupLoader(){
		ResourceRegistry.registerReloadListener(PackType.CLIENT_RESOURCES, new TimelineGroupDataLoader());
	}

	private static void registerEntityAnimators(){
		ENTITY_ANIMATORS.register(EntityType.PLAYER, new PlayerPartAnimator());
		//ENTITY_ANIMATORS.register(EntityType.CREEPER, new CreeperPartAnimator());
	}

	private static void registerBlockRenderers(){
		registerBlockRenderer(new PressurePlateBlockRenderer(), PressurePlateBlockRenderer.PRESSURE_PLATES);
		registerBlockRenderer(new ButtonBlockRenderer(), ButtonBlockRenderer.BUTTONS);
		registerBlockRenderer(new TrapDoorBlockRenderer(), TrapDoorBlockRenderer.TRAPDOORS);
		registerBlockRenderer(new LeverBlockRenderer(), LeverBlockRenderer.LEVERS);
		registerBlockRenderer(new EndPortalFrameBlockRenderer(), EndPortalFrameBlockRenderer.END_PORTAL_BLOCKS);
		registerBlockRenderer(new ChainedBlockRenderer(), ChainedBlockRenderer.CHAINED_BLOCKS);
		//registerBlockRenderer(new FloatingPlantBlockRenderer(), FloatingPlantBlockRenderer.FLOATING_PLANTS);
	}

	private static void registerBlockRenderer(TickableBlockRenderer tickableBlockRenderer, Block[] blocks){
		for(Block block : blocks){
			BlockRendererRegistry.register(block, tickableBlockRenderer);
		}
	}
}
