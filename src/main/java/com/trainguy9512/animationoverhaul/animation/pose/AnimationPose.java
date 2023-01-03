package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.util.animation.Locator;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.util.data.TransformChannel;
import com.trainguy9512.animationoverhaul.util.time.ChannelTimeline;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.util.Mth;

import java.util.HashMap;

public class AnimationPose {

    private final LocatorSkeleton locatorSkeleton;
    private final HashMap<Locator, MutablePartPose> pose = Maps.newHashMap();

    public AnimationPose(LocatorSkeleton locatorSkeleton){
        this.locatorSkeleton = locatorSkeleton;
        for(LocatorSkeleton.LocatorEntry locatorEntry : locatorSkeleton.getLocatorEntries()){
            pose.put(locatorEntry.getLocator(), MutablePartPose.ZERO);
        }
    }

    public AnimationPose getCopy(){
        AnimationPose copiedAnimationPose = new AnimationPose(this.locatorSkeleton);
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            copiedAnimationPose.setLocatorPose(locatorEntry.getLocator(), this.getLocatorPose(locatorEntry.getLocator()));
        }
        return copiedAnimationPose;
    }

    public LocatorSkeleton getSkeleton(){
        return this.locatorSkeleton;
    }

    public void applyDefaultPoseOffset(){
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            MutablePartPose offset = MutablePartPose.fromPartPose(locatorEntry.getDefaultPose());
            setLocatorPose(locatorEntry.getLocator(), MutablePartPose.add(getLocatorPose(locatorEntry.getLocator()), offset));
        }
    }

    public void setLocatorPose(Locator locator, MutablePartPose mutablePartPose){
        this.pose.put(locator, mutablePartPose);
    }

    public MutablePartPose getLocatorPose(Locator locator){
        return this.pose.get(locator);
    }

    public static AnimationPose blend(AnimationPose animationPoseA, AnimationPose animationPoseB, float alpha, Easing easing){
        float easedAlpha = easing.ease(alpha);
        for(LocatorSkeleton.LocatorEntry locatorEntry : animationPoseA.getSkeleton().getLocatorEntries()){
            MutablePartPose mutablePartPoseA = animationPoseA.getLocatorPose(locatorEntry.getLocator());
            MutablePartPose mutablePartPoseB = animationPoseB.getLocatorPose(locatorEntry.getLocator());

            if(mutablePartPoseA.xRot - mutablePartPoseB.xRot > Mth.PI){
                mutablePartPoseA = MutablePartPose.fromTranslationAndRotation(mutablePartPoseA.x, mutablePartPoseA.y, mutablePartPoseA.z, mutablePartPoseA.xRot - Mth.TWO_PI, mutablePartPoseA.yRot, mutablePartPoseA.zRot);
            }
            if(mutablePartPoseA.xRot - mutablePartPoseB.xRot < -Mth.PI){
                mutablePartPoseA = MutablePartPose.fromTranslationAndRotation(mutablePartPoseA.x, mutablePartPoseA.y, mutablePartPoseA.z, mutablePartPoseA.xRot + Mth.TWO_PI, mutablePartPoseA.yRot, mutablePartPoseA.zRot);
            }
            if(Math.abs(mutablePartPoseA.xRot - mutablePartPoseB.xRot) > Mth.PI){
                AnimationOverhaulMain.LOGGER.warn("Snapping on the X axis of {} degrees", Math.toDegrees(Math.abs(mutablePartPoseA.xRot - mutablePartPoseB.xRot)));
            }
            if(Math.abs(mutablePartPoseA.yRot - mutablePartPoseB.yRot) > Mth.PI){
                AnimationOverhaulMain.LOGGER.warn("Snapping on the Y axis of {} degrees", Math.toDegrees(Math.abs(mutablePartPoseA.yRot - mutablePartPoseB.yRot)));
            }
            if(Math.abs(mutablePartPoseA.zRot - mutablePartPoseB.zRot) > Mth.PI){
                AnimationOverhaulMain.LOGGER.warn("Snapping on the Z axis of {} degrees", Math.toDegrees(Math.abs(mutablePartPoseA.zRot - mutablePartPoseB.zRot)));
            }
            animationPoseA.setLocatorPose(locatorEntry.getLocator(), MutablePartPose.fromTranslationAndRotation(
                    Mth.lerp(easedAlpha, mutablePartPoseB.x, mutablePartPoseA.x),
                    Mth.lerp(easedAlpha, mutablePartPoseB.y, mutablePartPoseA.y),
                    Mth.lerp(easedAlpha, mutablePartPoseB.z, mutablePartPoseA.z),
                    Mth.rotLerp(easedAlpha, mutablePartPoseB.xRot, mutablePartPoseA.xRot),
                    Mth.rotLerp(easedAlpha, mutablePartPoseB.yRot, mutablePartPoseA.yRot),
                    Mth.rotLerp(easedAlpha, mutablePartPoseB.zRot, mutablePartPoseA.zRot))
            );
        }
        return animationPoseA;
    }

    public static AnimationPose blendLinear(AnimationPose animationPoseA, AnimationPose animationPoseB, float alpha){
        return blend(animationPoseA, animationPoseB, alpha, Easing.Linear.linear());
    }

    public static AnimationPose blendBoolean(AnimationPose animationPoseA, AnimationPose animationPoseB, boolean value){
        return blendLinear(animationPoseA, animationPoseB, value ? 0 : 1);
    }

    public static AnimationPose fromChannelTimeline(LocatorSkeleton locatorSkeleton, TimelineGroupData.TimelineGroup timelineGroup, float time, boolean mirrored){
        AnimationPose animationPose = new AnimationPose(locatorSkeleton);
        float mirrorMultiplier = mirrored ? -1 : 1;
        for(LocatorSkeleton.LocatorEntry locatorEntry : locatorSkeleton.getLocatorEntries()){
            Locator locator = mirrored ? locatorEntry.getLocatorMirrored() : locatorEntry.getLocator();
            if(timelineGroup != null){
                if(timelineGroup.containsPart(locator.getIdentifier())){
                    ChannelTimeline channelTimeline = timelineGroup.getPartTimeline(locator.getIdentifier());
                    MutablePartPose mutablePartPose = MutablePartPose.fromTranslationAndRotation(
                            channelTimeline.getValueAt(TransformChannel.x, time) * mirrorMultiplier,
                            channelTimeline.getValueAt(TransformChannel.y, time),
                            channelTimeline.getValueAt(TransformChannel.z, time),
                            channelTimeline.getValueAt(TransformChannel.xRot, time),
                            channelTimeline.getValueAt(TransformChannel.yRot, time) * mirrorMultiplier,
                            channelTimeline.getValueAt(TransformChannel.zRot, time) * mirrorMultiplier
                    );
                    animationPose.setLocatorPose(locator, mutablePartPose);
                }
            }
        }
        return animationPose;
    }

    public static AnimationPose fromChannelTimeline(LocatorSkeleton locatorSkeleton, TimelineGroupData.TimelineGroup timelineGroup, float time){
        return fromChannelTimeline(locatorSkeleton, timelineGroup, time, false);
    }
}
