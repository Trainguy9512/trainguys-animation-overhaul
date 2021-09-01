package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BellRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(BellRenderer.class)
public class MixinBellRenderer {
    @Shadow @Final private ModelPart bellBody;

    @Shadow @Final public static Material BELL_RESOURCE_LOCATION;

    /**
     * @author Trainguy
     */
    @Overwrite
    public void render(BellBlockEntity bellBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        float g = (float)bellBlockEntity.ticks + f;
        float h = 0.0F;
        float k = 0.0F;
        if (bellBlockEntity.shaking) {
            float l = Mth.sin(1.5F * g / Mth.PI) * (Mth.sin(g * Mth.PI / 100 + Mth.PI) + 1) * 0.6F;
            if (bellBlockEntity.clickDirection == Direction.NORTH) {
                h = -l;
            } else if (bellBlockEntity.clickDirection == Direction.SOUTH) {
                h = l;
            } else if (bellBlockEntity.clickDirection == Direction.EAST) {
                k = -l;
            } else if (bellBlockEntity.clickDirection == Direction.WEST) {
                k = l;
            }
        }

        this.bellBody.xRot = h;
        this.bellBody.zRot = k;
        VertexConsumer vertexConsumer = BELL_RESOURCE_LOCATION.buffer(multiBufferSource, RenderType::entitySolid);
        this.bellBody.render(poseStack, vertexConsumer, i, j);
    }
}
