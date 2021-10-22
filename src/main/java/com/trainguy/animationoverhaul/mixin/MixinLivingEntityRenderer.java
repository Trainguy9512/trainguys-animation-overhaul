package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.trainguy.animationoverhaul.access.LivingEntityAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    protected MixinLivingEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Shadow protected abstract float getBob(T livingEntity, float f);

    @Shadow protected M model;

    @Shadow public abstract M getModel();


    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"))
    private void setAnimationParameters(T livingEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        float animationPosition = livingEntity.animationPosition - livingEntity.animationSpeed * (1.0F - g);
        float animationSpeed = Mth.lerp(g, livingEntity.animationSpeedOld, livingEntity.animationSpeed);
        float tickAtFrame = this.getBob(livingEntity, g);
        float tickDifference = g;
        float delta = Minecraft.getInstance().getDeltaFrameTime();


        float h = Mth.rotLerp(g, livingEntity.yBodyRotO, livingEntity.yBodyRot);
        float j = Mth.rotLerp(g, livingEntity.yHeadRotO, livingEntity.yHeadRot);
        float k = j - h;
        float o;
        if (livingEntity.isPassenger() && livingEntity.getVehicle() instanceof LivingEntity) {
            LivingEntity livingEntity2 = (LivingEntity)livingEntity.getVehicle();
            h = Mth.rotLerp(g, livingEntity2.yBodyRotO, livingEntity2.yBodyRot);
            k = j - h;
            o = Mth.wrapDegrees(k);
            if (o < -85.0F) {
                o = -85.0F;
            }

            if (o >= 85.0F) {
                o = 85.0F;
            }

            h = j - o;
            if (o * o > 2500.0F) {
                h += o * 0.2F;
            }

            k = j - h;
        }

        float headXRot = (float) Math.toRadians(Mth.lerp(g, livingEntity.xRotO, livingEntity.getXRot()));
        float headYRot = (float) Math.toRadians(k);
        ((LivingEntityAccess)livingEntity).setAnimationParamaters(animationPosition, animationSpeed, tickAtFrame, tickDifference, delta, headYRot, headXRot, i);
    }
}
