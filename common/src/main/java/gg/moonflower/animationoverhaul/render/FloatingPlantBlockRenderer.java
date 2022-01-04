package gg.moonflower.animationoverhaul.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import gg.moonflower.pollen.pinwheel.api.client.blockdata.BlockDataKey;
import gg.moonflower.pollen.pinwheel.api.client.render.TickableBlockRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class FloatingPlantBlockRenderer implements TickableBlockRenderer {

    private static final Random RANDOM = new Random();
    private static final BlockDataKey<Integer> TIME;

    public static final Block[] FLOATING_PLANTS = new Block[]{
            Blocks.LILY_PAD
    };

    @Override
    public void tick(Level level, BlockPos blockPos, DataContainer dataContainer) {
        dataContainer.get(TIME).set(dataContainer.get(TIME).get() + 1);
    }

    @Override
    public void render(Level level, BlockPos blockPos, DataContainer dataContainer, MultiBufferSource multiBufferSource, PoseStack poseStack, float v, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, int packedLight, int packedOverlay) {
        float time = dataContainer.get(TIME).get() + v;

        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.sin(time / 13) * 4));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        poseStack.translate(
                Mth.sin(time / 9F) * (1.5F / 16F),
                Mth.sin(time / 20F) * (0.5F / 16F),
                Mth.sin(time / 7F) * (1.5F / 16F)
        );

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(level.getBlockState(blockPos), poseStack, multiBufferSource, packedLight, packedOverlay);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    static {
        TIME = BlockDataKey.of(() -> {
            return RANDOM.nextInt(32767);
        }).setBlocks(FLOATING_PLANTS).build();
    }
}
