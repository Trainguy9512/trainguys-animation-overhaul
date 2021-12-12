package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import com.trainguy.animationoverhaul.animations.LivingEntityAnimator;
import net.minecraft.client.Camera;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(LevelRenderer.class)
public class MixinLevelRenderer {
    @Shadow private ClientLevel level;


    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void adjustTimersForAllEntities(PoseStack poseStack, float f, long l, boolean bl, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo ci){
        for(Entity entity : this.level.entitiesForRendering()){
            if(entity instanceof LivingEntity){
                ResourceLocation entityAnimatorResourceLocation = new ResourceLocation(entity.getType().toShortString());
                if(AnimationOverhaul.ENTITY_ANIMATORS.containsKey(entityAnimatorResourceLocation)){
                    LivingEntityAnimator<LivingEntity, EntityModel<LivingEntity>> livingEntityAnimator = AnimationOverhaul.ENTITY_ANIMATORS.get(new ResourceLocation(entity.getType().toShortString()));
                    assert livingEntityAnimator != null;
                    livingEntityAnimator.adjustTimers((LivingEntity) entity);
                }
            }
        }
    }

    //@Inject(method = "playStreamingMusic", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;notifyNearbyEntities(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Z)V"))
    private void notifyNearbyEntitiesWithSongName(SoundEvent soundEvent, BlockPos blockPos, CallbackInfo ci){
        List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, (new AABB(blockPos)).inflate(3.0D));

        String songName = "";
        if (soundEvent != null) {
            RecordItem recordItem = RecordItem.getBySound(soundEvent);
            assert recordItem != null;
            songName = recordItem.toString();
        }

        for (LivingEntity livingEntity : list) {
            ((LivingEntityAccess)livingEntity).setRecordPlayerNearbyValues(songName, soundEvent != null, blockPos);
        }
    }
}
