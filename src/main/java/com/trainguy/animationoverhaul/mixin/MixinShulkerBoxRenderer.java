package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.trainguy.animationoverhaul.access.BlockEntityAccess;
import com.trainguy.animationoverhaul.util.Easing;
import com.trainguy.animationoverhaul.util.timeline.Timeline;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.ShulkerBoxRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxRenderer.class)
public class MixinShulkerBoxRenderer {

    @Shadow
    @Final
    private ShulkerModel<?> model;

    private final Easing openEasing = Easing.easeInOut(0.37f, 0.65f);

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(ShulkerBoxBlockEntity shulkerBoxBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, CallbackInfo ci) {

        Direction direction = Direction.UP;
        if (shulkerBoxBlockEntity.hasLevel()) {
            BlockState blockState = shulkerBoxBlockEntity.getLevel().getBlockState(shulkerBoxBlockEntity.getBlockPos());
            if (blockState.getBlock() instanceof ShulkerBoxBlock) {
                direction = blockState.getValue(ShulkerBoxBlock.FACING);
            }
        }

        DyeColor dyeColor = shulkerBoxBlockEntity.getColor();
        Material material2;
        if (dyeColor == null) {
            material2 = Sheets.DEFAULT_SHULKER_TEXTURE_LOCATION;
        } else {
            material2 = Sheets.SHULKER_TEXTURE_LOCATION.get(dyeColor.getId());
        }

        poseStack.pushPose();
        poseStack.translate(0.5D, 0.5D, 0.5D);
        float g = 0.9995F;
        poseStack.scale(0.9995F, 0.9995F, 0.9995F);
        poseStack.mulPose(direction.getRotation());
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.translate(0.0D, -1.0D, 0.0D);
        ModelPart modelPart = this.model.getLid();

        float openAmount = shulkerBoxBlockEntity.getProgress(f);

        float openAmountEased = this.openEasing.ease(openAmount);

        // will be bumped to somewhere between 120 and 210   by the continuous rotation
        float openRotation = 120f * openAmountEased * 0.017453292F;

        float lidOpenHeight = Mth.lerp(openAmountEased, 24f, 16f);

        float delta = Minecraft.getInstance().getDeltaFrameTime();
        float oldTime = ((BlockEntityAccess) (shulkerBoxBlockEntity)).getAnimationVariable("timer");
        float time = oldTime + delta;
        ((BlockEntityAccess) (shulkerBoxBlockEntity)).setAnimationVariable("timer", time);

        float lidBob = Mth.sin(time * 0.125f) * openAmountEased * 2f;

        float lidRotation = (((time * 0.04f) % Mth.HALF_PI) * openAmountEased) % (Mth.HALF_PI);

        modelPart.setPos(0.0F, lidOpenHeight + lidBob, 0.0F);
        modelPart.yRot = openRotation + lidRotation;


        VertexConsumer vertexConsumer = material2.buffer(multiBufferSource, RenderType::entityCutoutNoCull);
        this.model.renderToBuffer(poseStack, vertexConsumer, i, j, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();

        ci.cancel();
    }
}
