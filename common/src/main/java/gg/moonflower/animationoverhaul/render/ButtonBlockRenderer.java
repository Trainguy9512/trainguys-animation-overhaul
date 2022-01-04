package gg.moonflower.animationoverhaul.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
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
import net.minecraft.world.level.block.state.properties.AttachFace;

public class ButtonBlockRenderer implements TickableBlockRenderer {

    private static final BlockDataKey<Float> PRESSED_TIMER;
    private static final BlockDataKey<Float> PRESSED_TIMER_PREVIOUS;
    private static final BlockDataKey<Boolean> PRESSED;

    public static final Block[] BUTTONS = new Block[]{
            Blocks.OAK_BUTTON,
            Blocks.SPRUCE_BUTTON,
            Blocks.BIRCH_BUTTON,
            Blocks.JUNGLE_BUTTON,
            Blocks.ACACIA_BUTTON,
            Blocks.DARK_OAK_BUTTON,
            Blocks.CRIMSON_BUTTON,
            Blocks.WARPED_BUTTON,
            Blocks.STONE_BUTTON,
            Blocks.POLISHED_BLACKSTONE_BUTTON
    };
    private static final ChannelTimeline<Float> pressDownTimeline = ChannelTimeline.floatChannelTimeline()
            .addKeyframe(TransformChannel.y, 0, 0F)
            .addKeyframe(TransformChannel.y, 1, -1.5F/16F, new Easing.CubicBezier(0.18F, 0.59F, 0.45F, 1.6F))
            .addKeyframe(TransformChannel.y, 2, -1.5F/16F, new Easing.CubicBezier(0.18F, 0.59F, 0.45F, 1.6F));

    private static final ChannelTimeline<Float> pressUpTimeline = ChannelTimeline.floatChannelTimeline()
            .addKeyframe(TransformChannel.y, 0, 0F)
            .addKeyframe(TransformChannel.y, 1, -1/4F/16F, Easing.CubicBezier.bezierInCirc())
            .addKeyframe(TransformChannel.y, 2, 0F, Easing.CubicBezier.bezierOutCirc())
            .addKeyframe(TransformChannel.y, 4, -1.5F/16F, new Easing.CubicBezier(0.17F,0.53F,0.47F,1F));



    @Override
    public void tick(Level level, BlockPos blockPos, DataContainer dataContainer) {
        BlockData<Float> pressedTimerData = dataContainer.get(PRESSED_TIMER);
        BlockData<Float> pressedTimerPreviousData = dataContainer.get(PRESSED_TIMER_PREVIOUS);
        boolean pressed = dataContainer.get(PRESSED).get();

        pressedTimerPreviousData.set(pressedTimerData.get());
        pressedTimerData.set(Mth.clamp(pressedTimerData.get() + ((pressed ? 1 : -1) * (1F/6F)), 0, 1));
    }

    @Override
    public void receiveUpdate(Level level, BlockPos pos, BlockState oldState, BlockState newState, DataContainer dataContainer) {
        BlockData<Boolean> pressed = dataContainer.get(PRESSED);
        pressed.set(newState.getValue(ButtonBlock.POWERED));
        //TickableBlockRenderer.super.receiveUpdate(level, pos, oldState, newState, container);
    }

    @Override
    public void render(Level level, BlockPos blockPos, DataContainer dataContainer, MultiBufferSource multiBufferSource, PoseStack poseStack, float v, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, int packedLight, int packedOverlay) {
        BlockState blockState = level.getBlockState(blockPos);

        float pressedTimerNew = dataContainer.get(PRESSED_TIMER).get();
        float pressedTimerPrevious = dataContainer.get(PRESSED_TIMER_PREVIOUS).get();
        float pressedTimer = Mth.lerp(v, pressedTimerPrevious, pressedTimerNew);
        boolean pressed = dataContainer.get(PRESSED).get();

        int xRot = switch(blockState.getValue(ButtonBlock.FACE)){
            case FLOOR -> 0;
            case WALL -> -90;
            case CEILING -> -180;
        };
        int yRot = switch(blockState.getValue(ButtonBlock.FACING)){
            case DOWN -> 0;
            case UP -> 0;
            case NORTH -> 0;
            case SOUTH -> 180;
            case EAST -> -90;
            case WEST -> 90;
        };

        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(yRot));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(xRot));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        poseStack.pushPose();

        ChannelTimeline<Float> channelTimeline = pressed ? pressDownTimeline : pressUpTimeline;
        poseStack.translate(0, channelTimeline.getValueAt(TransformChannel.y, pressedTimer), 0);

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(level.getBlockState(blockPos).getBlock().defaultBlockState().setValue(ButtonBlock.FACE, AttachFace.FLOOR), poseStack, multiBufferSource, packedLight, packedOverlay);

        poseStack.popPose();
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    static {
        PRESSED_TIMER = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(BUTTONS).build();
        PRESSED_TIMER_PREVIOUS = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(BUTTONS).build();
        PRESSED = BlockDataKey.of(() -> {
            return false;
        }).setBlocks(BUTTONS).build();
    }
}
