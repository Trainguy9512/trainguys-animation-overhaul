package gg.moonflower.animationoverhaul.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Vector3f;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSelectionList;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class TestScreen extends Screen {

    public static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation("textures/block/deepslate.png");
    private final Screen lastScreen;
    private EntityConfigList entityConfigList;


    public TestScreen(Screen screen) {
        super(new TextComponent("Animation Settings"));
        this.lastScreen = screen;
    }

    @Override
    protected void init(){
        this.entityConfigList = new EntityConfigList();
        this.addWidget(entityConfigList);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderStoneBackground(/*Util.getMillis() / 20F / 20F*/0);
        TestScreen.drawCenteredString(poseStack, this.font, this.title, this.width / 2, 10, 0xFFFFFF);
        TestScreen.drawCenteredString(poseStack, this.font, "i: " + String.valueOf(mouseX), this.width / 2, 20, 0xFFFFFF);
        TestScreen.drawCenteredString(poseStack, this.font, "j: " + String.valueOf(mouseY), this.width / 2, 30, 0xFFFFFF);

        // left to right top to bottom
        float mouseXPercent = (float)mouseX / (float)this.width;
        float mouseYPercent = (float)mouseY / (float)this.height;

        int backgroundColor = 0x95000000;
        TestScreen.fill(poseStack, this.width / 3, 48, this.width, this.height - 24, backgroundColor);


        //Util.getMillis() / 20F

        poseStack.pushPose();
        poseStack.translate(Mth.lerp(0.5, this.width / 3F, this.width), Mth.lerp(0.9, 48, this.height - 24), 0.0);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(-10));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-45));
        poseStack.mulPose(Vector3f.YP.rotationDegrees((float) Mth.lerp(Math.sin(Util.getMillis() / 1000F), 0, 20)));
        float scale = 50;
        poseStack.scale(scale, scale, scale);
        poseStack.scale(-1, -1, -1);

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        BlockState basePlateState = Blocks.SMOOTH_STONE_SLAB.defaultBlockState();
        float basePlateSize = 2;
        poseStack.scale(3 / (basePlateSize + 1), 3 / (basePlateSize + 1), 3 / (basePlateSize + 1));
        poseStack.translate(-basePlateSize / 2 - 1, 0, -basePlateSize / 2 - 1);
        for(int x = 0; x < basePlateSize; x++){
            poseStack.translate(1, 0, 0);
            for(int z = 0; z < basePlateSize; z++){
                poseStack.translate(0, 0, 1);
                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(basePlateState, poseStack, bufferSource, Color.WHITE.getRGB(), OverlayTexture.NO_OVERLAY);
            }
            poseStack.translate(0, 0, -basePlateSize);
        }
        bufferSource.endBatch();
        poseStack.popPose();

        this.entityConfigList.render(poseStack, mouseX, mouseY, partialTicks);
    }

    public void renderStoneBackground(float i) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        float f = 32.0f;
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferBuilder.vertex(0.0, this.height, 0.0).uv(0.0f, (float)this.height / 32.0f + (float)i).color(64, 64, 64, 255).endVertex();
        bufferBuilder.vertex(this.width, this.height, 0.0).uv((float)this.width / 32.0f, (float)this.height / 32.0f + (float)i).color(64, 64, 64, 255).endVertex();
        bufferBuilder.vertex(this.width, 0.0, 0.0).uv((float)this.width / 32.0f, i).color(64, 64, 64, 255).endVertex();
        bufferBuilder.vertex(0.0, 0.0, 0.0).uv(0.0f, i).color(64, 64, 64, 255).endVertex();
        tesselator.end();
    }

    public class EntityConfigList extends AbstractSelectionList<EntityConfigEntry> {

        /*
        public AbstractSelectionList(Minecraft minecraft, int i, int j, int k, int l, int m)

        this.minecraft = minecraft;
        this.width = i;
        this.height = j;
        this.y0 = k;
        this.y1 = l;
        this.itemHeight = m;
        this.x0 = 0;
        this.x1 = i;
         */

        public EntityConfigList() {
            super(TestScreen.this.minecraft, 128, TestScreen.this.height, 48, TestScreen.this.height - 24, 24);

            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
        }



        public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
            //ScreenUtil.scissor(this.getRowLeft(), this.getTop(), this.getWidth(), this.getBottom() - this.getTop());
            super.render(poseStack, mouseX, mouseY, partialTicks);
            //RenderSystem.disableScissor();
        }

        @Override
        public void updateNarration(NarrationElementOutput narrationElementOutput) {

        }
    }

    public enum EntityConfigEntryType {
        HEADER,
        LABELED_OPTION
    }

    public static class EntityConfigEntry extends AbstractSelectionList.Entry<EntityConfigEntry> {

        private final EntityConfigEntryType entityConfigEntryType;
        private final String text;

        public EntityConfigEntry(EntityConfigEntryType entityConfigEntryType, String text){
            this.entityConfigEntryType = entityConfigEntryType;
            this.text = text;
        }

        @Override
        public void render(PoseStack poseStack, int index, int top, int left, int rowWidth, int rowHeight, int mouseX, int mouseY, boolean hovered, float partialTicks) {
            if(hovered){
                fill(poseStack, left, top, left + rowWidth, top + rowHeight, 0xFF9E0072);
            }
            TestScreen.drawCenteredString(poseStack, Minecraft.getInstance().font, text, left + rowWidth / 2, top + rowHeight / 2, 0xFFFFFF);
        }
    }
}
