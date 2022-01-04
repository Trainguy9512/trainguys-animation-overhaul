package gg.moonflower.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import gg.moonflower.animationoverhaul.animations.LivingEntityAnimator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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
                /*
                ResourceLocation entityAnimatorResourceLocation = new ResourceLocation(entity.getType().toShortString());
                if(AnimationOverhaulMain.ENTITY_ANIMATORS.containsKey(entityAnimatorResourceLocation)){
                    LivingEntityAnimator<LivingEntity, EntityModel<LivingEntity>> livingEntityAnimator = AnimationOverhaulMain.ENTITY_ANIMATORS.get(new ResourceLocation(entity.getType().toShortString()));
                    assert livingEntityAnimator != null;
                    livingEntityAnimator.adjustTimers((LivingEntity) entity);
                }

                 */

                EntityType<?> entityType = entity.getType();
                if(AnimationOverhaulMain.ENTITY_ANIMATORS.contains(entityType)){
                    LivingEntityAnimator livingEntityAnimator = AnimationOverhaulMain.ENTITY_ANIMATORS.get(entityType);
                    livingEntityAnimator.adjustTimers((LivingEntity) entity);
                }
            }
        }
    }
}
