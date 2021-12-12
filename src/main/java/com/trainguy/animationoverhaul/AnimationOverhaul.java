package com.trainguy.animationoverhaul;

import com.trainguy.animationoverhaul.animations.LivingEntityAnimator;
import com.trainguy.animationoverhaul.animations.PlayerAnimator;
import com.trainguy.animationoverhaul.animations.ZombifiedPiglinAnimator;
import com.trainguy.animationoverhaul.util.data.AnimationDataLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimationOverhaul implements ModInitializer {

	public static final String MOD_ID = "animation_overhaul";
	public static Logger LOGGER = LogManager.getLogger();
	public static final MappedRegistry<LivingEntityAnimator> ENTITY_ANIMATORS = FabricRegistryBuilder.createSimple(LivingEntityAnimator.class, new ResourceLocation(MOD_ID, "entity_animators")).buildAndRegister();

	public static EntityRendererProvider.Context context;
	public static Entity debugEntity;

	@Override
	public void onInitialize() {
		registerTimelineGroupLoader();
		registerEntityAnimators();

	}

	private void registerTimelineGroupLoader(){
		ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new AnimationDataLoader());
	}

	private void registerEntityAnimators(){
		Registry.register(ENTITY_ANIMATORS, EntityType.PLAYER.toShortString(), new PlayerAnimator());
		Registry.register(ENTITY_ANIMATORS, EntityType.ZOMBIFIED_PIGLIN.toShortString(), new ZombifiedPiglinAnimator());
	}
}
