package com.trainguy.animationoverhaul.mixin;

import com.trainguy.animationoverhaul.util.Easing;
import com.trainguy.animationoverhaul.util.timeline.Timeline;
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


    private static final Timeline<Float> wingBaseAnimation = Timeline.floatTimeline()
            .addKeyframe(0.0f, -0.27925267815589905f)
            .addKeyframe(25.0f, 0.27925267815589905f, new Easing.CubicBezier(0.3620256805419922f, 0.0f, 0.41991233825683594f, 1.0f))
            .addKeyframe(60.0f, -0.27925267815589905f, new Easing.CubicBezier(0.33333336966378346f, -0.0f, 0.6666666303362165f, 1.0f));


    private static final Timeline<Float> wingTipAnimation = Timeline.floatTimeline()
            .addKeyframe(0.0f, -0.15242473781108856f)
            .addKeyframe(14.0f, -0.39576929807662964f, new Easing.CubicBezier(0.2262556893484933f, 0.6213717372019372f, 0.6666667120797294f, 1.0f))
            .addKeyframe(34.0f, 0.09340483695268631f, new Easing.CubicBezier(0.33333330154418944f, 0.0f, 0.8417762756347656f, 0.7785819695767815f))
            .addKeyframe(47.0f, 0.1707504242658615f, new Easing.CubicBezier(0.17936295729417068f, 1.0318473417537886f, 0.7163332425631009f, 1.9879443980622955f))
            .addKeyframe(59.0f, -0.15242473781108856f, new Easing.CubicBezier(0.26340484619140625f, 0.20266712553966684f, 0.7642974853515625f, 0.8272092784563276f));

    private static final Timeline<Float> wingTipTiltAnimation = Timeline.floatTimeline()
            .addKeyframe(0.0f, -0.15242473781108856f)
            .addKeyframe(14.0f, -0.39576929807662964f, new Easing.CubicBezier(0.2262556893484933f, 0.6213717372019372f, 0.6666667120797294f, 1.0f))
            .addKeyframe(34.0f, 0.09340483695268631f, new Easing.CubicBezier(0.33333330154418944f, 0.0f, 0.8417762756347656f, 0.7785819695767815f))
            .addKeyframe(47.0f, 0.1707504242658615f, new Easing.CubicBezier(0.17936295729417068f, 1.0318473417537886f, 0.7163332425631009f, 1.9879443980622955f))
            .addKeyframe(59.0f, -0.15242473781108856f, new Easing.CubicBezier(0.26340484619140625f, 0.20266712553966684f, 0.7642974853515625f, 0.8272092784563276f))
            ;

    /**
     * @author Marvin Schürz
     */
    @Overwrite
    public void setupAnim(T phantom, float f, float g, float h, float i, float j) {
        float k = ((float) phantom.getUniqueFlapTickOffset() + h) * 7.448451F * 0.017453292F;

        this.leftWingBase.zRot = wingBaseAnimation.getValueAt((k / 4.5f) % 1);
        this.leftWingTip.zRot = wingTipAnimation.getValueAt((k / 4.5f) % 1);
        this.leftWingTip.xRot = -wingTipTiltAnimation.getValueAt((k / 4.5f) % 1) * 0.75f;

        this.rightWingBase.zRot = -this.leftWingBase.zRot;
        this.rightWingTip.zRot = -this.leftWingTip.zRot;
        this.rightWingTip.xRot = this.leftWingTip.xRot;
        this.tailBase.xRot = -(5.0F + Mth.cos(k * 2.0F) * 5.0F) * 0.017453292F;
        this.tailTip.xRot = -(5.0F + Mth.cos(k * 2.0F) * 5.0F) * 0.017453292F;
    }

}
