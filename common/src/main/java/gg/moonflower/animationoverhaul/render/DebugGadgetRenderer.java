package gg.moonflower.animationoverhaul.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import gg.moonflower.animationoverhaul.AnimationOverhaulMain;
import gg.moonflower.animationoverhaul.util.data.EntityAnimationData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class DebugGadgetRenderer extends GuiComponent {

    public static final DebugGadgetRenderer INSTANCE = new DebugGadgetRenderer();

    private final Minecraft minecraft;
    private Entity entity;
    private final EntityAnimationData animationData = new EntityAnimationData();

    private static final ResourceLocation COMPASS_TEXTURE = new ResourceLocation(AnimationOverhaulMain.MOD_ID, "textures/gui/gadgets/compass.png");
    private static final EntityAnimationData.DataKey<Float> COMPASS_ROTATION_SPEED = new EntityAnimationData.DataKey<>("compass_rotation_speed", 0F);
    private static final EntityAnimationData.DataKey<Float> COMPASS_ROTATION_POSITION = new EntityAnimationData.DataKey<>("compass_rotation_position", 0F);

    public DebugGadgetRenderer(){
        this.minecraft = Minecraft.getInstance();
        this.entity = this.minecraft.getCameraEntity();
    }

    public void tick(){
        this.entity = this.minecraft.getCameraEntity();
        if(this.entity != null){
            float targetCompassRotation = (this.entity.getYRot() + 180) % 360;
            float compassRotationSpeed = this.animationData.get(COMPASS_ROTATION_SPEED).get();
            float compassRotationPosition = this.animationData.get(COMPASS_ROTATION_POSITION).get();
            compassRotationSpeed += (targetCompassRotation > compassRotationPosition ? 1 : -1) * 1.1F;
            if(Mth.abs(targetCompassRotation - compassRotationPosition) < 20){
                compassRotationSpeed *= 0.75F;
            }
            compassRotationPosition += compassRotationSpeed;
            this.animationData.get(COMPASS_ROTATION_SPEED).set(compassRotationSpeed);
            this.animationData.get(COMPASS_ROTATION_POSITION).set(compassRotationPosition);
        }
    }

    public void render(PoseStack poseStack, float partialTicks){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, COMPASS_TEXTURE);

        poseStack.pushPose();
        poseStack.translate(0, 50, 0);
        this.blit(poseStack, 0, 0, 0, 0, 160, 256);

        poseStack.pushPose();
        poseStack.translate(80, 83, 0);

        poseStack.mulPose(Vector3f.ZP.rotationDegrees(animationData.getLerped(COMPASS_ROTATION_POSITION, partialTicks)));
        this.blit(poseStack, -3, -52, 160, 13, 6, 62);
        poseStack.popPose();
        poseStack.popPose();
    }
}
