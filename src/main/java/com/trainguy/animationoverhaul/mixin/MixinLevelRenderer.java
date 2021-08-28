package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
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

    @Inject(method = "playStreamingMusic", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;notifyNearbyEntities(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Z)V"))
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
