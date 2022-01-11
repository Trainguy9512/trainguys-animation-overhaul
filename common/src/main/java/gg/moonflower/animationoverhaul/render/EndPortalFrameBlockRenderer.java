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
import net.minecraft.Util;
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

import java.util.Random;

public class EndPortalFrameBlockRenderer implements TickableBlockRenderer {

    private static final BlockDataKey<Float> RANDOM_FLOAT;
    private static final BlockDataKey<Float> EYE_TIMER;
    private static final BlockDataKey<Float> EYE_TIMER_PREVIOUS;
    private static final BlockDataKey<Float> ACTIVATE_TIMER;
    private static final BlockDataKey<Float> ACTIVATE_TIMER_PREVIOUS;
    private static final BlockDataKey<Boolean> FILLED;

    public static final Block[] END_PORTAL_BLOCKS = new Block[]{
            Blocks.END_PORTAL_FRAME
    };
    private static final ChannelTimeline<Float> eyeInsertTimeline = ChannelTimeline.floatChannelTimeline()
            .addKeyframe(TransformChannel.y, 0, 0F)
            .addKeyframe(TransformChannel.y, 1, -2F/16F)
            .addKeyframe(TransformChannel.y, 5, 0F, new Easing.CubicBezier(.35F,1.0F,.63F,1F));

    public static final ModelResourceLocation PORTAL_BASE_LOCATION = new ModelResourceLocation("end_portal_frame", "eye=false,facing=east");
    public static final ModelResourceLocation PORTAL_EYE_LOCATION = new ModelResourceLocation("end_portal_frame", "eye=false,facing=north");

    @Override
    public void tick(Level level, BlockPos blockPos, DataContainer dataContainer) {
        BlockData<Float> pressedTimerData = dataContainer.get(EYE_TIMER);
        BlockData<Float> pressedTimerPreviousData = dataContainer.get(EYE_TIMER_PREVIOUS);
        boolean pressed = dataContainer.get(FILLED).get();

        BlockData<Float> activateTimerData = dataContainer.get(ACTIVATE_TIMER);
        BlockData<Float> activateTimerPreviousData = dataContainer.get(ACTIVATE_TIMER_PREVIOUS);

        pressedTimerPreviousData.set(pressedTimerData.get());
        pressedTimerData.set(Mth.clamp(pressedTimerData.get() + ((pressed ? 1 : -1) * (1F/6F)), 0, 1));

        boolean activated =
                level.getBlockState(blockPos.offset(-1, 0, 0)).getBlock() == Blocks.END_PORTAL ||
                level.getBlockState(blockPos.offset(0, 0, -1)).getBlock() == Blocks.END_PORTAL ||
                level.getBlockState(blockPos.offset(1, 0, 0)).getBlock() == Blocks.END_PORTAL ||
                level.getBlockState(blockPos.offset(0, 0, 1)).getBlock() == Blocks.END_PORTAL;

        activateTimerPreviousData.set(activateTimerData.get());
        activateTimerData.set(Mth.clamp(activateTimerData.get() + ((activated ? 1 : -1) * (1F/12F)), 0, 1));
    }

    @Override
    public void receiveUpdate(Level level, BlockPos pos, BlockState oldState, BlockState newState, DataContainer dataContainer) {
        if(newState.getBlock() instanceof EndPortalFrameBlock){
            BlockData<Boolean> pressed = dataContainer.get(FILLED);
            pressed.set(newState.getValue(EndPortalFrameBlock.HAS_EYE));
        }
        //TickableBlockRenderer.super.receiveUpdate(level, pos, oldState, newState, container);
    }

    @Override
    public void render(Level level, BlockPos blockPos, DataContainer dataContainer, MultiBufferSource multiBufferSource, PoseStack poseStack, float v, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, int packedLight, int packedOverlay) {

        BakedModel portalBaseBakedModel = Minecraft.getInstance().getModelManager().getModel(PORTAL_BASE_LOCATION);
        BakedModel portalEyeBakedModel = Minecraft.getInstance().getModelManager().getModel(PORTAL_EYE_LOCATION);

        BlockState blockState = level.getBlockState(blockPos);

        float eyeTimerNew = dataContainer.get(EYE_TIMER).get();
        float eyeTimerPrevious = dataContainer.get(EYE_TIMER_PREVIOUS).get();
        float eyeTimer = Mth.lerp(v, eyeTimerPrevious, eyeTimerNew);
        float activateTimerNew = dataContainer.get(ACTIVATE_TIMER).get();
        float activateTimerPrevious = dataContainer.get(ACTIVATE_TIMER_PREVIOUS).get();
        float activateTimer = Mth.lerp(v, activateTimerPrevious, activateTimerNew);
        boolean filled = dataContainer.get(FILLED).get();

        int yRot = switch(blockState.getValue(EndPortalFrameBlock.FACING)){
            case DOWN -> 0;
            case UP -> 0;
            case NORTH -> 0;
            case SOUTH -> 180;
            case EAST -> -90;
            case WEST -> 90;
        };

        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(yRot));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(Sheets.cutoutBlockSheet()), null, portalBaseBakedModel, 1, 1, 1, packedLight, packedOverlay);
        if(filled){
            poseStack.pushPose();

            eyeTimer = activateTimer > 0 ? 0 : eyeTimer;
            poseStack.translate(0, eyeInsertTimeline.getValueAt(TransformChannel.y, eyeTimer) * 0.5F, 0);

            poseStack.translate(0, eyeInsertTimeline.getValueAt(TransformChannel.y, activateTimer), 0);
            poseStack.translate(0, Mth.abs(Mth.sin(Util.getMillis() / Mth.lerp(dataContainer.get(RANDOM_FLOAT).get(), 70F, 120F))) * -0.1F/16F * activateTimer, 0);


            Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(Sheets.cutoutBlockSheet()), null, portalEyeBakedModel, 1, 1, 1, packedLight, packedOverlay);
            poseStack.popPose();
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    static {
        RANDOM_FLOAT = BlockDataKey.of(() -> {
            return new Random().nextFloat();
        }).setBlocks(END_PORTAL_BLOCKS).build();
        EYE_TIMER = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(END_PORTAL_BLOCKS).build();
        EYE_TIMER_PREVIOUS = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(END_PORTAL_BLOCKS).build();
        ACTIVATE_TIMER = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(END_PORTAL_BLOCKS).build();
        ACTIVATE_TIMER_PREVIOUS = BlockDataKey.of(() -> {
            return 0F;
        }).setBlocks(END_PORTAL_BLOCKS).build();
        FILLED = BlockDataKey.of(() -> {
            return false;
        }).setBlocks(END_PORTAL_BLOCKS).build();
    }
}
