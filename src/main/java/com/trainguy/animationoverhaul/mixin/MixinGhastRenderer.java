package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Ghast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Unique
@Mixin(GhastRenderer.class)
public class MixinGhastRenderer {
    private static final ResourceLocation GHAST_LOCATION = new ResourceLocation("textures/entity/ghast/ghast.png");
    private static final ResourceLocation GHAST_SHOOTING_LOCATION = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");

    @Inject(method="getTextureLocation", at = @At("HEAD"), cancellable = true)
    private void overrideGhastTexture(Ghast ghast, CallbackInfoReturnable<ResourceLocation> cir){
        ResourceLocation ghastTextureLocation = GHAST_LOCATION;
        if(ghast.isCharging() || ghast.hurtTime > 5){
            ghastTextureLocation = GHAST_SHOOTING_LOCATION;
        }
        cir.setReturnValue(ghastTextureLocation);
    }

    @Inject(method="scale", at = @At("HEAD"), cancellable = true)
    private void overrideGhastRenderer(Ghast ghast, PoseStack poseStack, float f, CallbackInfo ci){

        float delta = Minecraft.getInstance().getDeltaFrameTime();

        float ghastHurtTime = ghast.hurtTime + (1 - f);
        if(ghast.hurtTime > 0 && ghast.deathTime == 0){
            float ghastHurtRotationCurve = Mth.sin((1 - ghastHurtTime / 10) * Mth.PI * 2) * (1 - (1 - ghastHurtTime / 10)) * 25;
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(ghastHurtRotationCurve));
        }
        poseStack.translate(0, Mth.sin((ghast.tickCount + f) / 12) * 0.75, 0);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin((ghast.tickCount + f) / 12 - 1.5F) * 10));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.sin((ghast.tickCount + f) / 20) * 10));
        poseStack.scale(4.5F, 4.5F, 4.5F);

        float currentVerticalMovementRotation = ((LivingEntityAccess)ghast).getAnimationVariable("verticalMovementRotation");
        currentVerticalMovementRotation = ghast.getDeltaMovement().y >= 0 ? Mth.clamp(currentVerticalMovementRotation - 2 * delta, -25, 25) : Mth.clamp(currentVerticalMovementRotation + 2 * delta, -25, 25);
        ((LivingEntityAccess)ghast).setAnimationVariable("verticalMovementRotation", currentVerticalMovementRotation);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(currentVerticalMovementRotation));

        ci.cancel();
    }
}
