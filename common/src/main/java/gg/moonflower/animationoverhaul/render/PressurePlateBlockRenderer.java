package gg.moonflower.animationoverhaul.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import gg.moonflower.animationoverhaul.util.data.TransformChannel;
import gg.moonflower.animationoverhaul.util.time.ChannelTimeline;
import gg.moonflower.animationoverhaul.util.time.Easing;
import gg.moonflower.pollen.pinwheel.api.client.blockdata.BlockData;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class PressurePlateBlockRenderer implements TickableBlockRenderer {

    private static final BlockDataKey<Float> PRESSED_TIMER;
    private static final BlockDataKey<Float> PRESSED_TIMER_PREVIOUS;
    private static final BlockDataKey<Boolean> PRESSED;

    public static final Block[] PRESSURE_PLATES = new Block[]{
            Blocks.ACACIA_PRESSURE_PLATE,
            Blocks.BIRCH_PRESSURE_PLATE,
            Blocks.CRIMSON_PRESSURE_PLATE,
            Blocks.OAK_PRESSURE_PLATE,
            Blocks.DARK_OAK_PRESSURE_PLATE,
            Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE,
            Blocks.JUNGLE_PRESSURE_PLATE,
            Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE,
            Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE,
            Blocks.SPRUCE_PRESSURE_PLATE,
            Blocks.STONE_PRESSURE_PLATE,
            Blocks.WARPED_PRESSURE_PLATE
    };

    private static final ChannelTimeline pressDownTimeline = new ChannelTimeline()
            .addKeyframe(TransformChannel.y, 0, 0F)
            .addKeyframe(TransformChannel.y, 1, -1/2F/16F, new Easing.CubicBezier(0.18F, 0.59F, 0.45F, 1.13F));

    private static final ChannelTimeline pressUpTimeline = new ChannelTimeline()
            .addKeyframe(TransformChannel.y, 0, 0F)
            .addKeyframe(TransformChannel.y, 1, -1/2F/16F, new Easing.CubicBezier(0.51F, -0.8F, 0.61F, 0.13F));

    @Override
    public void tick(Level level, BlockPos blockPos, DataContainer dataContainer) {
        BlockData<Float> pressedTimerData = dataContainer.get(PRESSED_TIMER);
        BlockData<Float> pressedTimerPreviousData = dataContainer.get(PRESSED_TIMER_PREVIOUS);
        boolean pressed = dataContainer.get(PRESSED).get();

        pressedTimerPreviousData.set(pressedTimerData.get());
        pressedTimerData.set(Mth.clamp(pressedTimerData.get() + ((pressed ? 1 : -1) * (1F/4F)), 0, 1));
    }

    @Override
    public void receiveUpdate(Level level, BlockPos pos, BlockState oldState, BlockState newState, DataContainer dataContainer) {
        BlockData<Boolean> pressed = dataContainer.get(PRESSED);
        if(newState.getBlock() instanceof PressurePlateBlock){
            pressed.set(newState.getValue(PressurePlateBlock.POWERED));
        } else if(newState.getBlock() instanceof WeightedPressurePlateBlock){
            pressed.set(newState.getValue(WeightedPressurePlateBlock.POWER) > 0);
        } else {
            pressed.set(false);
        }
        //TickableBlockRenderer.super.receiveUpdate(level, pos, oldState, newState, container);
    }

    @Override
    public void render(Level level, BlockPos blockPos, DataContainer dataContainer, MultiBufferSource multiBufferSource, PoseStack poseStack, float v, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, int packedLight, int packedOverlay) {
        float pressedTimerNew = dataContainer.get(PRESSED_TIMER).get();
        float pressedTimerPrevious = dataContainer.get(PRESSED_TIMER_PREVIOUS).get();
        float pressedTimer = Mth.lerp(v, pressedTimerPrevious, pressedTimerNew);

        boolean pressed = dataContainer.get(PRESSED).get();

        ChannelTimeline channelTimeline = pressed ? pressDownTimeline : pressUpTimeline;
        poseStack.translate(0, channelTimeline.getValueAt(TransformChannel.y, pressedTimer), 0);

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(level.getBlockState(blockPos).getBlock().defaultBlockState(), poseStack, multiBufferSource, packedLight, packedOverlay);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    static {
        PRESSED_TIMER = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(PRESSURE_PLATES).build();
        PRESSED_TIMER_PREVIOUS = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(PRESSURE_PLATES).build();
        PRESSED = BlockDataKey.of(() -> {
            return false;
        }).setBlocks(PRESSURE_PLATES).build();
    }
}
