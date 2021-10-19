package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
public class MixinCapeLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    ResourceLocation debugCapeLocation = new ResourceLocation("textures/block/stone.png");

    public MixinCapeLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderLayerParent) {
        super(renderLayerParent);
    }

    /**
     * @author Trainguy
     */
    @Overwrite
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = abstractClientPlayer.getItemBySlot(EquipmentSlot.CHEST);
        if (!itemStack.is(Items.ELYTRA)) {
            // Debug renderer for testing capes
            poseStack.pushPose();
            poseStack.translate(0.0D, 0.0D, 0.125D);
            double d = Mth.lerp((double)h, abstractClientPlayer.xCloakO, abstractClientPlayer.xCloak) - Mth.lerp((double)h, abstractClientPlayer.xo, abstractClientPlayer.getX());
            double e = Mth.lerp((double)h, abstractClientPlayer.yCloakO, abstractClientPlayer.yCloak) - Mth.lerp((double)h, abstractClientPlayer.yo, abstractClientPlayer.getY());
            double m = Mth.lerp((double)h, abstractClientPlayer.zCloakO, abstractClientPlayer.zCloak) - Mth.lerp((double)h, abstractClientPlayer.zo, abstractClientPlayer.getZ());
            float n = abstractClientPlayer.yBodyRotO + (abstractClientPlayer.yBodyRot - abstractClientPlayer.yBodyRotO);
            double o = (double)Mth.sin(n * 0.017453292F);
            double p = (double)(-Mth.cos(n * 0.017453292F));
            float q = (float)e * 10.0F;
            q = Mth.clamp(q, -6.0F, 32.0F);
            float r = (float)(d * o + m * p) * 100.0F;
            r = Mth.clamp(r, 0.0F, 150.0F);
            float s = (float)(d * p - m * o) * 100.0F;
            s = Mth.clamp(s, -20.0F, 20.0F);
            if (r < 0.0F) {
                r = 0.0F;
            }

            float t = Mth.lerp(h, abstractClientPlayer.oBob, abstractClientPlayer.bob);
            q += Mth.sin(Mth.lerp(h, abstractClientPlayer.walkDistO, abstractClientPlayer.walkDist) * 6.0F) * 32.0F * t;
            if (abstractClientPlayer.isCrouching()) {
                q += 25.0F;
            }

            poseStack.mulPose(Vector3f.XP.rotationDegrees(6.0F + r / 2.0F + q));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(s / 2.0F));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - s / 2.0F));
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entitySolid(debugCapeLocation));
            this.getParentModel().renderCloak(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }
}
