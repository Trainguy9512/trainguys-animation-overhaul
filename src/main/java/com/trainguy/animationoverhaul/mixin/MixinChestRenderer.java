package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.sun.jna.platform.win32.WinBase;
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
    @Shadow private boolean xmasTextures;

    @Shadow @Final private ModelPart doubleLeftLid;

    @Shadow @Final private ModelPart doubleLeftLock;

    @Shadow @Final private ModelPart doubleLeftBottom;

    @Shadow @Final private ModelPart doubleRightBottom;

    @Shadow @Final private ModelPart doubleRightLock;

    @Shadow @Final private ModelPart doubleRightLid;

    @Shadow @Final private ModelPart lid;

    @Shadow @Final private ModelPart lock;

    @Shadow @Final private ModelPart bottom;

    /**
     * @author Trainguy
     */
    @Overwrite
    public void render(T blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        Level level = blockEntity.getLevel();
        boolean bl = level != null;
        BlockState blockState = bl ? blockEntity.getBlockState() : (BlockState) Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = blockState.hasProperty(ChestBlock.TYPE) ? (ChestType)blockState.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        Block block = blockState.getBlock();
        if (block instanceof AbstractChestBlock) {
            AbstractChestBlock<?> abstractChestBlock = (AbstractChestBlock<BlockEntity>)block;
            boolean isDoubleChest = chestType != ChestType.SINGLE;
            poseStack.pushPose();
            float g = ((Direction)blockState.getValue(ChestBlock.FACING)).toYRot();
            poseStack.translate(0.5D, 0.5D, 0.5D);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(-g));
            poseStack.translate(-0.5D, -0.5D, -0.5D);
            DoubleBlockCombiner.NeighborCombineResult neighborCombineResult2;
            if (bl) {
                neighborCombineResult2 = abstractChestBlock.combine(blockState, level, blockEntity.getBlockPos(), true);
            } else {
                neighborCombineResult2 = (DoubleBlockCombiner.NeighborCombineResult<T>)DoubleBlockCombiner.Combiner::acceptNone;
            }

            float h = ((Float2FloatFunction)neighborCombineResult2.apply(ChestBlock.opennessCombiner((LidBlockEntity)blockEntity))).get(f);
            int k = ((Int2IntFunction)neighborCombineResult2.apply(new BrightnessCombiner())).applyAsInt(i);
            Material material = Sheets.chooseMaterial(blockEntity, chestType, this.xmasTextures);
            VertexConsumer vertexConsumer = material.buffer(multiBufferSource, RenderType::entityCutout);

            float delta = Minecraft.getInstance().getDeltaFrameTime();
            float previousIsDoubleChest = ((BlockEntityAccess)blockEntity).getAnimationVariable("isDoubleChest");
            float currentDoubleChest = isDoubleChest ? 1 : 0;
            ((BlockEntityAccess)blockEntity).setAnimationVariable("isDoubleChest", currentDoubleChest);

            float previousOpenAmount = ((BlockEntityAccess)blockEntity).getAnimationVariable("previousOpenAmount");
            boolean isOpening = previousOpenAmount < h;
            ((BlockEntityAccess)blockEntity).setAnimationVariable("previousOpenAmount", h);

            float chestLidOpenRotation = (Mth.sin((float) (6 * Math.pow(h, 3))) * (Mth.sin(h * Mth.PI / 2 + Mth.PI) + 1) + Mth.sin(h * Mth.PI / 2)) * -Mth.HALF_PI;
            float chestLidCloseRoation = (float) (Mth.abs(Mth.sin((float) (Math.pow(h, 1/2F) * 1.7F * Mth.PI))) * (Mth.sin(h * Mth.PI / 2 - Mth.PI / 2) + 1) * 1.25F) * -Mth.HALF_PI;

            float entityChestShakeProgress = ((BlockEntityAccess)blockEntity).getAnimationVariable("chestShakeProgress");
            if(currentDoubleChest != previousIsDoubleChest){
                entityChestShakeProgress = 1;
            }
            float currentChestShakeProgress = Mth.clamp(entityChestShakeProgress - (0.125F * delta), 0, 1);
            ((BlockEntityAccess)blockEntity).setAnimationVariable("chestShakeProgress", currentChestShakeProgress);
            float chestShakeVerticalMovement = Mth.clamp(Mth.sin((1 - currentChestShakeProgress) * Mth.PI * 1.25F + Mth.PI / 3F), 0, 1) * 0.0625F;
            float chestShakeLidRotation = Mth.abs(Mth.sin((1 - currentChestShakeProgress) * Mth.PI) * (1 - 1.5F * (1 - currentChestShakeProgress))) * -0.125F;

            ModelPart lid = switch(chestType){
                case SINGLE -> this.lid;
                case LEFT -> this.doubleLeftLid;
                case RIGHT -> this.doubleRightLid;
            };
            ModelPart lock = switch(chestType){
                case SINGLE -> this.lock;
                case LEFT -> this.doubleLeftLock;
                case RIGHT -> this.doubleRightLock;
            };
            ModelPart bottom = switch(chestType){
                case SINGLE -> this.bottom;
                case LEFT -> this.doubleLeftBottom;
                case RIGHT -> this.doubleRightBottom;
            };

            //lid.xRot = -(f * 1.5707964F);


            poseStack.translate(0, chestShakeVerticalMovement, 0);
            lid.xRot = chestShakeLidRotation + (h == 1 ? Mth.PI / -2 : isOpening ? chestLidOpenRotation : chestLidCloseRoation);
            lock.xRot = lid.xRot;

            lid.render(poseStack, vertexConsumer, i, j);
            lock.render(poseStack, vertexConsumer, i, j);
            bottom.render(poseStack, vertexConsumer, i, j);

            poseStack.popPose();
        }
    }

    private void render(T blockEntity, PoseStack poseStack, VertexConsumer vertexConsumer, ModelPart lid, ModelPart lock, ModelPart bottom, float f, int i, int j) {
        lid.xRot = -(f * 1.5707964F);
        lock.xRot = lid.xRot;
        lid.render(poseStack, vertexConsumer, i, j);
        lock.render(poseStack, vertexConsumer, i, j);
        bottom.render(poseStack, vertexConsumer, i, j);
    }
}
