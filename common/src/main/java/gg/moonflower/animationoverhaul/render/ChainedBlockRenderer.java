package gg.moonflower.animationoverhaul.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import gg.moonflower.pollen.pinwheel.api.client.blockdata.BlockData;
import gg.moonflower.pollen.pinwheel.api.client.blockdata.BlockDataKey;
import gg.moonflower.pollen.pinwheel.api.client.render.BlockRenderer;
import gg.moonflower.pollen.pinwheel.api.client.render.TickableBlockRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.lwjgl.system.CallbackI;

import java.util.Objects;
import java.util.Random;

public class ChainedBlockRenderer implements TickableBlockRenderer {
        private static final Random RANDOM = new Random();
        private static final BlockPos.MutableBlockPos CHAIN_POS = new BlockPos.MutableBlockPos();
        private static final BlockDataKey<Integer> TIME;
        private static final BlockDataKey<BlockPos> TOP;
        private static final BlockDataKey<Boolean> ATTACHED;

        public static final Block[] CHAINED_BLOCKS = new Block[]{
                Blocks.CHAIN,
                Blocks.LANTERN,
                Blocks.SOUL_LANTERN
        };

        public ChainedBlockRenderer() {
        }

        public void tick(Level level, BlockPos pos, BlockRenderer.DataContainer container) {
            BlockData<Integer> time = container.get(TIME);
            BlockData<Boolean> attached = container.get(ATTACHED);
            BlockData<BlockPos> top = container.get(TOP);
            time.set((Integer)time.get() + 1);
            CHAIN_POS.set(pos).move(Direction.DOWN);
            if (!level.getBlockState(CHAIN_POS).is(Blocks.CHAIN)) {
                boolean shouldAttach = level.getBlockState(pos).is(Blocks.CHAIN) && Block.canSupportCenter(level, CHAIN_POS, Direction.UP);
                if (!((Boolean)attached.get()).equals(shouldAttach)) {
                    attached.set(shouldAttach);
                    if (level.getBlockState(CHAIN_POS.set(pos).move(Direction.UP)).is(Blocks.CHAIN)) {
                        container.updateNeighbor(Direction.DOWN);
                    }
                }
            }

            CHAIN_POS.set(pos).move(Direction.UP);
            if (!level.getBlockState(CHAIN_POS).is(Blocks.CHAIN) && !Objects.equals(top.get(), pos)) {
                top.set(pos);
                if (level.getBlockState(CHAIN_POS.set(pos).move(Direction.DOWN)).is(Blocks.CHAIN)) {
                    container.updateNeighbor(Direction.UP);
                }
            }

        }

        public void receiveUpdate(Level level, BlockPos pos, BlockState oldState, BlockState newState, BlockRenderer.DataContainer container) {
            BlockData<BlockPos> top = container.get(TOP);
            BlockData<Boolean> attached = container.get(ATTACHED);
            CHAIN_POS.set(pos).move(Direction.DOWN);
            if (level.getBlockState(CHAIN_POS).is(Blocks.CHAIN) && !((Boolean)attached.get()).equals(container.get(ATTACHED, CHAIN_POS).get())) {
                attached.set((Boolean)container.get(ATTACHED, CHAIN_POS).get());
                container.updateNeighbor(Direction.UP);
            }

            CHAIN_POS.set(pos).move(Direction.UP);
            if (!level.getBlockState(CHAIN_POS).is(Blocks.CHAIN)) {
                top.set(pos);
                container.updateNeighbor(Direction.DOWN);
            } else if (level.getBlockState(CHAIN_POS).is(Blocks.CHAIN)) {
                top.set((BlockPos)container.get(TOP, CHAIN_POS).get());
                container.updateNeighbor(Direction.DOWN);
            }
            BlockTags.ACACIA_LOGS.getValues().toArray();

        }

        public void render(Level level, BlockPos blockPos, BlockRenderer.DataContainer container, MultiBufferSource buffer, PoseStack poseStack, float partialTicks, Camera camera, GameRenderer gameRenderer, LightTexture lightmap, Matrix4f projection, int packedLight, int packedOverlay) {
            if (!(Boolean)container.get(ATTACHED).get()) {
                BlockPos top = container.get(TOP).get() != null && level.getBlockState((BlockPos)container.get(TOP).get()).is(Blocks.CHAIN) ? (BlockPos)container.get(TOP).get() : blockPos;
                int distance = top.getY() - blockPos.getY();
                float time = (float)(Integer)container.get(TIME, top).get() + partialTicks;

                float xPos = Mth.sin((time / 16F) - distance * 0.25F) * Math.min(distance, 5F) / 5F * 1.1F/16F;
                float xPosBelow = Mth.sin((time / 16F) - (distance + 1) * 0.25F) * Math.min((distance + 1), 5F) / 5F * 1.1F/16F;
                float zRot = (float) -Math.atan((xPos - xPosBelow));

                poseStack.translate(0.5D, 1, 0.5D);

                poseStack.translate(xPos, 0, 0);
                poseStack.mulPose(Vector3f.ZP.rotation(zRot));

                poseStack.translate(-0.5D, -1, -0.5D);
            }
            /*
            if (!(Boolean)container.get(ATTACHED).get()) {
                BlockPos top = container.get(TOP).get() != null && level.getBlockState((BlockPos)container.get(TOP).get()).is(Blocks.CHAIN) ? (BlockPos)container.get(TOP).get() : pos;
                int distance = top.getY() - pos.getY() + 1;
                float time = (float)(Integer)container.get(TIME, top).get() + partialTicks;
                poseStack.translate(0.5D, (double)distance, 0.5D);
                poseStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(time / 20.0F)));
                poseStack.translate(-0.5D, (double)(-distance), -0.5D);
            }
             */

            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(level.getBlockState(blockPos), poseStack, buffer, packedLight, packedOverlay);
        }

        public RenderShape getRenderShape(BlockState state) {
            if (state.is(Blocks.CHAIN) && state.getValue(ChainBlock.AXIS) == Direction.Axis.Y) {
                return RenderShape.INVISIBLE;
            } else {
                return (state.is(Blocks.LANTERN) || state.is(Blocks.SOUL_LANTERN)) && (Boolean)state.getValue(LanternBlock.HANGING) ? RenderShape.INVISIBLE : RenderShape.MODEL;
            }
        }

static {
        TIME = BlockDataKey.of(() -> {
        return RANDOM.nextInt(32767);
        }).setBlocks(CHAINED_BLOCKS).build();
        TOP = BlockDataKey.of(() -> {
        return BlockPos.of(0);
        }).setBlocks(CHAINED_BLOCKS).build();
        ATTACHED = BlockDataKey.of(() -> {
        return false;
        }).setBlocks(CHAINED_BLOCKS).build();
        }
}
