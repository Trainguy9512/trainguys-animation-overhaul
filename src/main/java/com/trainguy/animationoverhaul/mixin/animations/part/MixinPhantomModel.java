package com.trainguy.animationoverhaul.mixin.animations.part;

import com.trainguy.animationoverhaul.util.time.Easing;
import com.trainguy.animationoverhaul.util.time.Timeline;
import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Phantom;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PhantomModel.class)
public class MixinPhantomModel<T extends Phantom> {

    @Shadow
    @Final
    private ModelPart leftWingBase;

    @Shadow
    @Final
    private ModelPart leftWingTip;

    @Shadow
    @Final
    private ModelPart rightWingBase;

    @Shadow
    @Final
    private ModelPart rightWingTip;

    @Shadow
    @Final
    private ModelPart tailBase;

    @Shadow
    @Final
    private ModelPart tailTip;


    @Shadow
    @Final
    private ModelPart root;
    private static final Timeline<Float> wingBaseAnimation = Timeline.floatTimeline()
            .addKeyframe(0.0f, -0.3698377013206482f)
            .addKeyframe(25.0f, 0.3319567143917084f, new Easing.CubicBezier(0.3620256805419922f, 0.0f, 0.3892283630371094f, 0.9190726741231952f))
            .addKeyframe(98.0f, -0.3698377013206482f, new Easing.CubicBezier(0.35406494140625f, -0.13698785980974518f, 0.3826091191540026f, 1.0f));


    private static final Timeline<Float> wingTipAnimation = Timeline.floatTimeline()
            .addKeyframe(0.0f, -0.05195769667625427f)
            .addKeyframe(12.0f, -0.32330122590065f, new Easing.CubicBezier(0.3401377995808919f, 0.14794764902559954f, 0.6338165203730265f, 0.8812431231162363f))
            .addKeyframe(48.0f, 0.16422587633132935f, new Easing.CubicBezier(0.2930042478773329f, -0.15866308406630236f, 0.44386185540093315f, 0.7251273099718529f))
            .addKeyframe(99.0f, -0.05195769667625427f, new Easing.CubicBezier(0.4333316578584559f, -0.6842472832264279f, 0.9405667174096201f, 0.8838679502398498f));

    private static final Timeline<Float> bodyAnimation = Timeline.floatTimeline()
            .addKeyframe(0.0f, -0.13767026364803314f)
            .addKeyframe(21.0f, 0.03545321151614189f, new Easing.CubicBezier(0.4396931330362956f, 0.0f, 0.6297589256649926f, 0.6352528557815906f))
            .addKeyframe(100.0f, -0.13767026364803314f, new Easing.CubicBezier(0.3991413357891614f, -1.4792506549951854f, 0.573518680620797f, 1.0f));

    /**
     * @author Marvin Schürz
     */
    @Overwrite
    public void setupAnim(T phantom, float f, float g, float h, float i, float j) {
        float k = ((float) phantom.getUniqueFlapTickOffset() + h) * 7.448451F * 0.017453292F;

        this.root.y = bodyAnimation.getValueAt((k / 4.5f) % 1) * -20;


        this.leftWingBase.zRot = wingBaseAnimation.getValueAt((k / 4.5f) % 1);
        this.leftWingTip.zRot = wingTipAnimation.getValueAt((k / 4.5f) % 1);

        this.rightWingBase.zRot = -this.leftWingBase.zRot;
        this.rightWingTip.zRot = -this.leftWingTip.zRot;
        this.tailBase.xRot = -(5.0F + Mth.cos(k * 2.0F) * 5.0F) * 0.017453292F;
        this.tailTip.xRot = -(5.0F + Mth.cos(k * 2.0F) * 5.0F) * 0.017453292F;
    }

}
