package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.animations.LivingEntityAnimator;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void adjustTimersForAllEntities(float f, long l, PoseStack poseStack, CallbackInfo ci){
        for(Entity entity : this.minecraft.level.entitiesForRendering()){
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
}
