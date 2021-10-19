package com.trainguy.animationoverhaul.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.trainguy.animationoverhaul.access.EntityAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MinecartRenderer.class)
public abstract class MixinMinecartRenderer<T extends AbstractMinecart> extends EntityRenderer<T> {
    @Shadow @Final protected EntityModel<T> model;

    protected MixinMinecartRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Shadow protected abstract void renderMinecartContents(T abstractMinecart, float f, BlockState blockState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i);

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void renderOverride(T abstractMinecart, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        //TODO: come back and fix this mess! i made it a while back, and it still has old stuff! not to mention all the spaghetti code that is minecart rendering... sheesh

        super.render(abstractMinecart, f, g, poseStack, multiBufferSource, i);
        poseStack.pushPose();
        long l = (long)abstractMinecart.getId() * 493286711L;
        l = l * l * 4392167121L + l * 98761L;
        float h = (((float)(l >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float j = (((float)(l >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float k = (((float)(l >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        poseStack.translate((double)h, (double)j, (double)k);
        double d = Mth.lerp((double)g, abstractMinecart.xOld, abstractMinecart.getX());
        double e = Mth.lerp((double)g, abstractMinecart.yOld, abstractMinecart.getY());
        double m = Mth.lerp((double)g, abstractMinecart.zOld, abstractMinecart.getZ());
        double n = 0.30000001192092896D;
        Vec3 vec3 = abstractMinecart.getPos(d, e, m);
        float o = Mth.lerp(g, abstractMinecart.xRotO, abstractMinecart.getXRot());
        if (vec3 != null) {
            Vec3 vec32 = abstractMinecart.getPosOffs(d, e, m, 0.30000001192092896D);
            Vec3 vec33 = abstractMinecart.getPosOffs(d, e, m, -0.30000001192092896D);
            if (vec32 == null) {
                vec32 = abstractMinecart.getPosOffs(d, e - 1, m, 0.30000001192092896D);
                if (vec32 == null) {
                    vec32 = abstractMinecart.getPosOffs(d, e + 1, m, 0.30000001192092896D);
                    if (vec32 == null) {
                        vec32 = vec3;
                    }
                }
            }

            if (vec33 == null) {
                vec33 = abstractMinecart.getPosOffs(d, e - 1, m, -0.30000001192092896D);
                if (vec33 == null) {
                    vec33 = abstractMinecart.getPosOffs(d, e + 1, m, -0.30000001192092896D);
                    if (vec33 == null) {
                        vec33 = vec3;
                    }
                }
            }

            poseStack.translate(vec3.x - d, (vec32.y + vec33.y) / 2.0D - e, vec3.z - m);
            Vec3 vec34 = vec33.add(-vec32.x, -vec32.y, -vec32.z);
            if (vec34.length() != 0.0D) {
                vec34 = vec34.normalize();
                f = (float)(Math.atan2(vec34.z, vec34.x) * 180.0D / 3.141592653589793D);
                o = (float)(Math.atan(vec34.y) * 73.0D);
            }
        }
        // Animation stuff
        float delta = Minecraft.getInstance().getDeltaFrameTime();
        float currentTickMinecartSpeed = (Mth.abs((float) (abstractMinecart.getX() - abstractMinecart.xOld)) + Mth.abs((float) (abstractMinecart.getZ() - abstractMinecart.zOld))) * 2.5F;

        float minecartSpeedWeight = ((EntityAccess)abstractMinecart).getAnimationTimer("minecart_speed");
        minecartSpeedWeight = minecartSpeedWeight == currentTickMinecartSpeed ? minecartSpeedWeight : currentTickMinecartSpeed < minecartSpeedWeight ? Math.max(currentTickMinecartSpeed, minecartSpeedWeight - 0.25F * delta) : Math.min(currentTickMinecartSpeed, minecartSpeedWeight + 0.25F * delta);
        ((EntityAccess)abstractMinecart).setAnimationTimer("minecart_speed", minecartSpeedWeight);

        float minecartTrackBumpAmount = ((EntityAccess)abstractMinecart).getAnimationTimer("track_bump_amount");
        minecartTrackBumpAmount = Mth.clamp(minecartTrackBumpAmount - 0.05F * delta, 0, 1);
        Random posRandom = new Random(Mth.floor(d) + Mth.floor(m));
        if((posRandom.nextInt(10) == 0 || abstractMinecart.getPosOffs(d, e - 1, m, -0.30000001192092896D) == null) && minecartTrackBumpAmount == 0){
            minecartTrackBumpAmount = 1;
        }
        ((EntityAccess)abstractMinecart).setAnimationTimer("track_bump_amount", minecartTrackBumpAmount);
        //minecartTrackBumpAmount = AnimCurveUtils.linearToEaseInOutWeight(minecartTrackBumpAmount, 2) + 0.2F;
        minecartTrackBumpAmount *= minecartSpeedWeight;

        float tickFrame = abstractMinecart.tickCount + g;
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.sin(Mth.PI * tickFrame / 3 - Mth.PI / 2) * minecartTrackBumpAmount * 10 * 0.25F));
        poseStack.translate(0, Math.abs(Mth.sin(Mth.PI * tickFrame / 3 - Mth.PI / 2)) * minecartTrackBumpAmount * 0.125 * 0.25, 0);

        poseStack.translate(0.0D, 0.375D, 0.0D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - f));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(-o));
        float p = (float)abstractMinecart.getHurtTime() - g;
        float q = abstractMinecart.getDamage() - g;
        if (q < 0.0F) {
            q = 0.0F;
        }

        if (p > 0.0F) {
            poseStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(p) * p * q / 10.0F * (float)abstractMinecart.getHurtDir()));
        }

        int r = abstractMinecart.getDisplayOffset();
        BlockState blockState = abstractMinecart.getDisplayBlockState();
        if (blockState.getRenderShape() != RenderShape.INVISIBLE) {
            poseStack.pushPose();
            float s = 0.75F;

            poseStack.translate(0, Math.abs(Mth.sin(Mth.PI * tickFrame / 3)) * minecartTrackBumpAmount * 0.0625 * 0.25, 0);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.sin(Mth.PI * tickFrame / 3) * minecartTrackBumpAmount * 10 * 0.25F));

            poseStack.translate(0, Math.abs(Mth.sin(Mth.PI * tickFrame / 4F)) * minecartTrackBumpAmount * 0.0625 * 0.25, 0);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(Mth.PI * tickFrame / 4F) * minecartTrackBumpAmount * 10 * 0.25F));

            poseStack.scale(0.75F, 0.75F, 0.75F);
            poseStack.translate(-0.5D, (double)((float)(r - 8) / 16.0F), -0.5D);

            this.renderMinecartContents(abstractMinecart, g, blockState, poseStack, multiBufferSource, i);
            poseStack.popPose();
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        this.model.setupAnim(abstractMinecart, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(this.getTextureLocation(abstractMinecart)));
        this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();

        ci.cancel();
    }
}
