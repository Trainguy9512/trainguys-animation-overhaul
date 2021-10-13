package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.BlockEntityAccess;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.DoubleBlockCombiner.NeighborCombineResult;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChestRenderer.class)
public class MixinChestRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {
    @Shadow
    private boolean xmasTextures;

    @Shadow
    @Final
    private ModelPart doubleLeftLid;

    @Shadow
    @Final
    private ModelPart doubleLeftLock;

    @Shadow
    @Final
    private ModelPart doubleLeftBottom;

    @Shadow
    @Final
    private ModelPart doubleRightBottom;

    @Shadow
    @Final
    private ModelPart doubleRightLock;

    @Shadow
    @Final
    private ModelPart doubleRightLid;

    @Shadow
    @Final
    private ModelPart lid;

    @Shadow
    @Final
    private ModelPart lock;

    @Shadow
    @Final
    private ModelPart bottom;

    private static final float SHAKE_DURATION = 8f;

    /**
     * @author Trainguy
     */
    @Overwrite
    public void render(T blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int brightness) {
        Level level = blockEntity.getLevel();

        BlockState blockState = level != null ? blockEntity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        Block block = blockState.getBlock();

        if (block instanceof AbstractChestBlock<?> abstractChestBlock) {
            ChestType chestType = blockState.hasProperty(ChestBlock.TYPE) ? blockState.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
            boolean isDoubleChest = chestType != ChestType.SINGLE;

            poseStack.pushPose();

            float rotY = (blockState.getValue(ChestBlock.FACING)).toYRot();
            poseStack.translate(0.5, 0.5, 0.5);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(-rotY));
            poseStack.translate(-0.5, -0.5, -0.5);

            NeighborCombineResult neighborCombineResult;

            if (level != null) {
                neighborCombineResult = abstractChestBlock.combine(blockState, level, blockEntity.getBlockPos(), true);
            } else {
                neighborCombineResult = (NeighborCombineResult<T>) DoubleBlockCombiner.Combiner::acceptNone;
            }


            float openAmount = ((Float2FloatFunction) neighborCombineResult.apply(ChestBlock.opennessCombiner(blockEntity))).get(f);

            Material material = Sheets.chooseMaterial(blockEntity, chestType, this.xmasTextures);
            VertexConsumer vertexConsumer = material.buffer(multiBufferSource, RenderType::entityCutout);


            float delta = Minecraft.getInstance().getDeltaFrameTime();
            float previousIsDoubleChest = ((BlockEntityAccess) blockEntity).getAnimationVariable("isDoubleChest");
            float currentIsDoubleChest = isDoubleChest ? 1 : 0;
            ((BlockEntityAccess) blockEntity).setAnimationVariable("isDoubleChest", currentIsDoubleChest);

            float previousOpenAmount = ((BlockEntityAccess) blockEntity).getAnimationVariable("previousOpenAmount");
            boolean isOpening = previousOpenAmount < openAmount;
            ((BlockEntityAccess) blockEntity).setAnimationVariable("previousOpenAmount", openAmount);


            float entityChestShakeProgress = ((BlockEntityAccess) blockEntity).getAnimationVariable("chestShakeProgress");
            if (currentIsDoubleChest != previousIsDoubleChest) {
                entityChestShakeProgress = 1;
            }

            float currentChestShakeProgress = Mth.clamp(entityChestShakeProgress - (1 / SHAKE_DURATION * delta), 0, 1);
            ((BlockEntityAccess) blockEntity).setAnimationVariable("chestShakeProgress", currentChestShakeProgress);
            float chestShakeVerticalMovement = Mth.clamp(Mth.sin((1 - currentChestShakeProgress) * Mth.PI * 1.25F + Mth.PI / 3F), 0, 1) * 0.0625F;

            ModelPart lid = getChestModelPart(chestType, this.lid, this.doubleLeftLid, this.doubleRightLid);
            ModelPart lock = getChestModelPart(chestType, this.lock, this.doubleLeftLock, this.doubleRightLock);
            ModelPart bottom = getChestModelPart(chestType, this.bottom, this.doubleLeftBottom, this.doubleRightBottom);

            poseStack.translate(0, chestShakeVerticalMovement, 0);
            lid.xRot = getCestLidRotation(openAmount, currentChestShakeProgress, isOpening);
            lock.xRot = lid.xRot;

            lid.render(poseStack, vertexConsumer, i, brightness);
            lock.render(poseStack, vertexConsumer, i, brightness);
            bottom.render(poseStack, vertexConsumer, i, brightness);

            poseStack.popPose();
        }
    }

    private float getChestShakeLidRotation(float currentChestShakeProgress) {
        return Mth.abs(Mth.sin((1 - currentChestShakeProgress) * Mth.PI) * (1 - 1.5F * (1 - currentChestShakeProgress))) * -0.125F;
    }

    private ModelPart getChestModelPart(ChestType chestType, ModelPart single, ModelPart left, ModelPart right) {
        return switch (chestType) {
            case SINGLE -> single;
            case LEFT -> left;
            case RIGHT -> right;
        };
    }

    private float getCestLidRotation(float openAmount, float shakeProgress, boolean isOpening) {
        float chestLidOpenRotation = getChestLidOpenRotation(openAmount);
        float chestLidCloseRotation = getChestLidCloseRotation(openAmount);
        float openRotation = (openAmount == 1 ? Mth.PI / -2 : isOpening ? chestLidOpenRotation : chestLidCloseRotation);

        return getChestShakeLidRotation(shakeProgress) + openRotation;
    }

    private float getChestLidOpenRotation(float openAmount) {
        return (Mth.sin(
                (float) (6 * Math.pow(openAmount, 3))
        ) * (Mth.sin(openAmount * Mth.PI / 2 + Mth.PI) + 1) + Mth.sin(openAmount * Mth.PI / 2)) * -Mth.HALF_PI;
    }

    private float getChestLidCloseRotation(float openAmount) {
        return (Mth.abs(Mth.sin((float) (Math.pow(openAmount, 1 / 2F) * 1.7F * Mth.PI))) * (Mth.sin(openAmount * Mth.PI / 2 - Mth.PI / 2) + 1) * 1.25F) * -Mth.HALF_PI;
    }

    private void render(T blockEntity, PoseStack poseStack, VertexConsumer vertexConsumer, ModelPart lid, ModelPart lock, ModelPart bottom, float f, int i, int j) {
        lid.xRot = -(f * 1.5707964F);
        lock.xRot = lid.xRot;
        lid.render(poseStack, vertexConsumer, i, j);
        lock.render(poseStack, vertexConsumer, i, j);
        bottom.render(poseStack, vertexConsumer, i, j);
    }
}
