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
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class LeverBlockRenderer implements TickableBlockRenderer {

    private static final BlockDataKey<Float> PULL_TIMER;
    private static final BlockDataKey<Float> PULL_TIMER_PREVIOUS;
    private static final BlockDataKey<Boolean> PULLED;

    public static final Block[] LEVERS = new Block[]{
            Blocks.LEVER
    };
    private static final ChannelTimeline<Float> pullUpTimeline = ChannelTimeline.floatChannelTimeline()
            .addKeyframe(TransformChannel.xRot, 0, 90F)
            .addKeyframe(TransformChannel.xRot, 1, 0F, new Easing.CubicBezier(0.58F, 1.5F, 0.74F, 1F));

    private static final ChannelTimeline<Float> pullDownTimeline = ChannelTimeline.floatChannelTimeline()
            .addKeyframe(TransformChannel.xRot, 0, 90F)
            .addKeyframe(TransformChannel.xRot, 1, 0F, Easing.CubicBezier.getInverseBezier(0.58F, 1.5F, 0.74F, 1F));


    public static final ModelResourceLocation LEVER_BASE_LOCATION = new ModelResourceLocation("lever", "face=wall,facing=west,powered=true");
    public static final ModelResourceLocation LEVER_ARM_LOCATION = new ModelResourceLocation("lever", "face=wall,facing=west,powered=false");

    @Override
    public void tick(Level level, BlockPos blockPos, DataContainer dataContainer) {
        BlockData<Float> pressedTimerData = dataContainer.get(PULL_TIMER);
        BlockData<Float> pressedTimerPreviousData = dataContainer.get(PULL_TIMER_PREVIOUS);
        boolean pressed = dataContainer.get(PULLED).get();

        pressedTimerPreviousData.set(pressedTimerData.get());
        pressedTimerData.set(Mth.clamp(pressedTimerData.get() + ((pressed ? 1 : -1) * (1F/5F)), 0, 1));
    }

    @Override
    public void receiveUpdate(Level level, BlockPos pos, BlockState oldState, BlockState newState, DataContainer dataContainer) {
        BlockData<Boolean> pressed = dataContainer.get(PULLED);
        pressed.set(newState.getValue(LeverBlock.POWERED));
        //TickableBlockRenderer.super.receiveUpdate(level, pos, oldState, newState, container);
    }

    @Override
    public void render(Level level, BlockPos blockPos, DataContainer dataContainer, MultiBufferSource multiBufferSource, PoseStack poseStack, float v, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, int packedLight, int packedOverlay) {

        BakedModel leverBaseBakedModel = Minecraft.getInstance().getModelManager().getModel(LEVER_BASE_LOCATION);
        BakedModel leverArmBakedModel = Minecraft.getInstance().getModelManager().getModel(LEVER_ARM_LOCATION);

        BlockState blockState = level.getBlockState(blockPos);

        float pullTimerNew = dataContainer.get(PULL_TIMER).get();
        float pullTimerPrevious = dataContainer.get(PULL_TIMER_PREVIOUS).get();
        float pullTimer = Mth.lerp(v, pullTimerPrevious, pullTimerNew);
        boolean pulled = dataContainer.get(PULLED).get();

        int xRot = switch(blockState.getValue(LeverBlock.FACE)){
            case FLOOR -> 0;
            case WALL -> -90;
            case CEILING -> -180;
        };
        int yRot = switch(blockState.getValue(LeverBlock.FACING)){
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

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(Sheets.cutoutBlockSheet()), null, leverBaseBakedModel, 1, 1, 1, packedLight, packedOverlay);
        poseStack.pushPose();

        ChannelTimeline<Float> channelTimeline = pulled ? pullUpTimeline : pullDownTimeline;

        poseStack.translate(0.5F, 1F/16F, 0.5F);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(channelTimeline.getValueAt(TransformChannel.xRot, pullTimer)));
        poseStack.translate(-0.5F, -1F/16F, -0.5F);

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(Sheets.cutoutBlockSheet()), null, leverArmBakedModel, 1, 1, 1, packedLight, packedOverlay);
        poseStack.popPose();
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    static {
        PULL_TIMER = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(LEVERS).build();
        PULL_TIMER_PREVIOUS = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(LEVERS).build();
        PULLED = BlockDataKey.of(() -> {
            return false;
        }).setBlocks(LEVERS).build();
    }
}
