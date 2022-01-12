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

public class TrapDoorBlockRenderer implements TickableBlockRenderer {

    private static final BlockDataKey<Float> OPEN_TIMER;
    private static final BlockDataKey<Float> OPEN_TIMER_PREVIOUS;
    private static final BlockDataKey<Boolean> OPEN;

    public static final Block[] TRAPDOORS = new Block[]{
            Blocks.ACACIA_TRAPDOOR,
            Blocks.BIRCH_TRAPDOOR,
            Blocks.DARK_OAK_TRAPDOOR,
            Blocks.JUNGLE_TRAPDOOR,
            Blocks.OAK_TRAPDOOR,
            Blocks.SPRUCE_TRAPDOOR,
            Blocks.CRIMSON_TRAPDOOR,
            Blocks.WARPED_TRAPDOOR,
            Blocks.IRON_TRAPDOOR
    };

    private static final ChannelTimeline openTimeline = new ChannelTimeline()
            .addKeyframe(TransformChannel.xRot, 0, -90F)
            .addKeyframe(TransformChannel.xRot, 1, 0F, new Easing.CubicBezier(0.71F, 1.95F, 0.57F, 0.75F));

    private static final ChannelTimeline closeTimeline = new ChannelTimeline()
            .addKeyframe(TransformChannel.xRot, 0, -90F)
            .addKeyframe(TransformChannel.xRot, 1, 0F, Easing.CubicBezier.getInverseBezier(0.71F, 1.95F, 0.57F, 0.75F));

    @Override
    public void tick(Level level, BlockPos blockPos, DataContainer dataContainer) {
        BlockData<Float> pressedTimerData = dataContainer.get(OPEN_TIMER);
        BlockData<Float> pressedTimerPreviousData = dataContainer.get(OPEN_TIMER_PREVIOUS);
        boolean pressed = dataContainer.get(OPEN).get();

        pressedTimerPreviousData.set(pressedTimerData.get());
        pressedTimerData.set(Mth.clamp(pressedTimerData.get() + ((pressed ? 1 : -1) * (1F/5F)), 0, 1));
    }

    @Override
    public void receiveUpdate(Level level, BlockPos pos, BlockState oldState, BlockState newState, DataContainer dataContainer) {
        BlockData<Boolean> pressed = dataContainer.get(OPEN);
        if(newState.getBlock() instanceof TrapDoorBlock){
            pressed.set(newState.getValue(TrapDoorBlock.OPEN));
        } else {
            pressed.set(false);
        }
        //TickableBlockRenderer.super.receiveUpdate(level, pos, oldState, newState, container);
    }

    @Override
    public void render(Level level, BlockPos blockPos, DataContainer dataContainer, MultiBufferSource multiBufferSource, PoseStack poseStack, float v, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, int packedLight, int packedOverlay) {
        BlockState blockState = level.getBlockState(blockPos);

        if(level.getBlockState(blockPos).getBlock() instanceof TrapDoorBlock){

        }
        float openTimerNew = dataContainer.get(OPEN_TIMER).get();
        float openTimerPrevious = dataContainer.get(OPEN_TIMER_PREVIOUS).get();
        float openTimer = Mth.lerp(v, openTimerPrevious, openTimerNew);
        boolean open = dataContainer.get(OPEN).get();

        int zRot = switch(blockState.getValue(TrapDoorBlock.HALF)){
            case TOP -> 180;
            case BOTTOM -> 0;
        };
        int yRot = switch(blockState.getValue(TrapDoorBlock.FACING)){
            case DOWN -> 0;
            case UP -> 0;
            case NORTH -> 0;
            case SOUTH -> 180;
            case EAST -> -90;
            case WEST -> 90;
        };

        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(yRot));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(zRot));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        poseStack.pushPose();

        ChannelTimeline channelTimeline = open ? openTimeline : closeTimeline;


        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.translate(0, -13F/32F, 13F/32F);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(channelTimeline.getValueAt(TransformChannel.xRot, openTimer)));
        poseStack.translate(0, 13F/32F, -13F/32F);
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(level.getBlockState(blockPos).getBlock().defaultBlockState().setValue(TrapDoorBlock.OPEN, true), poseStack, multiBufferSource, packedLight, packedOverlay);

        poseStack.popPose();
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    static {
        OPEN_TIMER = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(TRAPDOORS).build();
        OPEN_TIMER_PREVIOUS = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(TRAPDOORS).build();
        OPEN = BlockDataKey.of(() -> {
            return false;
        }).setBlocks(TRAPDOORS).build();
    }
}
