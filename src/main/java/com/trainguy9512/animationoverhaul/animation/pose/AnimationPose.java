package com.trainguy9512.animationoverhaul.animation.pose;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.util.data.TransformChannel;
import com.trainguy9512.animationoverhaul.util.time.ChannelTimeline;
import com.trainguy9512.animationoverhaul.util.time.Easing;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimationPose {

    private final LocatorSkeleton locatorSkeleton;
    private final HashMap<String, MutablePartPose> pose = Maps.newHashMap();

    public AnimationPose(LocatorSkeleton locatorSkeleton){
        this.locatorSkeleton = locatorSkeleton;
        for(LocatorSkeleton.LocatorEntry locatorEntry : locatorSkeleton.getLocatorEntries()){
            pose.put(locatorEntry.getLocatorIdentifier(), MutablePartPose.ZERO);
        }
    }

    public AnimationPose getCopy(){
        AnimationPose copiedAnimationPose = new AnimationPose(this.locatorSkeleton);
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            copiedAnimationPose.setLocatorPose(locatorEntry.getLocatorIdentifier(), this.getLocatorPose(locatorEntry.getLocatorIdentifier()));
        }
        return copiedAnimationPose;
    }

    public LocatorSkeleton getSkeleton(){
        return this.locatorSkeleton;
    }

    public void applyDefaultPoseOffset(){
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            MutablePartPose offset = MutablePartPose.fromPartPose(locatorEntry.getDefaultPose());
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose.add(getLocatorPose(locatorEntry.getLocatorIdentifier()), offset));
        }
    }

    public void setLocatorPose(String locatorIdentifier, MutablePartPose mutablePartPose){
        this.pose.put(locatorIdentifier, mutablePartPose);
    }

    public MutablePartPose getLocatorPose(String locatorIdentifier){
        return this.pose.getOrDefault(locatorIdentifier, MutablePartPose.ZERO);
    }

    /*
    public void subtractPose(AnimationPose animationPose){
        for(LocatorSkeleton.LocatorEntry locatorEntry : animationPose.getSkeleton().getLocatorEntries()){
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose);
        }
    }

     */

    public AnimationPose blend(AnimationPose animationPose, float alpha, Easing easing){
        // Simple inverting solution for now.
        //alpha = 1 - alpha;
        float easedAlpha = easing.ease(alpha);
        for(LocatorSkeleton.LocatorEntry locatorEntry : this.getSkeleton().getLocatorEntries()){
            MutablePartPose mutablePartPoseA = animationPose.getLocatorPose(locatorEntry.getLocatorIdentifier());
            MutablePartPose mutablePartPoseB = this.getLocatorPose(locatorEntry.getLocatorIdentifier());

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
            this.setLocatorPose(locatorEntry.getLocatorIdentifier(), MutablePartPose.fromTranslationAndRotation(
                    Mth.lerp(easedAlpha, mutablePartPoseB.x, mutablePartPoseA.x),
                    Mth.lerp(easedAlpha, mutablePartPoseB.y, mutablePartPoseA.y),
                    Mth.lerp(easedAlpha, mutablePartPoseB.z, mutablePartPoseA.z),
                    Mth.rotLerp(easedAlpha, mutablePartPoseB.xRot, mutablePartPoseA.xRot),
                    Mth.rotLerp(easedAlpha, mutablePartPoseB.yRot, mutablePartPoseA.yRot),
                    Mth.rotLerp(easedAlpha, mutablePartPoseB.zRot, mutablePartPoseA.zRot))
            );
        }
        return this;
    }

    public AnimationPose blendLinear(AnimationPose animationPose, float alpha){
        return this.blend(animationPose, alpha, Easing.Linear.of());
    }

    public AnimationPose blendBoolean(AnimationPose animationPose, boolean value){
        return this.blendLinear(animationPose, value ? 0 : 1);
    }

    public AnimationPose mirror(){
        return mirrorBlended(1);
    }

    public AnimationPose mirrorBlended(float alpha){
        HashMap<String, MutablePartPose> mirroredPose = Maps.newHashMap();
        for(String identifier : this.pose.keySet()){
            MutablePartPose mutablePartPose = this.pose.get(identifier);
            MutablePartPose mirroredMutablePartPose = this.pose.get(this.locatorSkeleton.getMirroredLocator(identifier));
            float mirrorMultiplier = mutablePartPose == this.pose.get(identifier) ? -1 : 1;

            //TODO: Add proper mutable part pose blend function
            MutablePartPose newMutablePartPose = mirroredMutablePartPose.getCopy();
            newMutablePartPose.x = Mth.lerp(alpha, mutablePartPose.x, mirroredMutablePartPose.x * mirrorMultiplier);
            newMutablePartPose.y = Mth.lerp(alpha, mutablePartPose.y, mirroredMutablePartPose.y);
            newMutablePartPose.z = Mth.lerp(alpha, mutablePartPose.z, mirroredMutablePartPose.z);
            newMutablePartPose.xRot = Mth.rotLerp(alpha, mutablePartPose.xRot, mirroredMutablePartPose.xRot);
            newMutablePartPose.yRot = Mth.rotLerp(alpha, mutablePartPose.yRot, mirroredMutablePartPose.yRot * -1);
            newMutablePartPose.zRot = Mth.rotLerp(alpha, mutablePartPose.zRot, mirroredMutablePartPose.zRot * -1);
            mirroredPose.put(identifier, newMutablePartPose);
        }
        for(String identifier : this.pose.keySet()){
            this.pose.put(identifier, mirroredPose.get(identifier));
        }
        return this;
    }

    public static AnimationPose fromChannelTimeline(LocatorSkeleton locatorSkeleton, TimelineGroupData.TimelineGroup timelineGroup, float time, boolean mirrored){
        AnimationPose animationPose = new AnimationPose(locatorSkeleton);
        float mirrorMultiplier = mirrored ? -1 : 1;
        for(LocatorSkeleton.LocatorEntry locatorEntry : locatorSkeleton.getLocatorEntries()){
            String locator = mirrored ? locatorEntry.getMirroedLocatorIdentifier() : locatorEntry.getLocatorIdentifier();
            if(timelineGroup != null){
                if(timelineGroup.containsPart(locator)){
                    ChannelTimeline channelTimeline = timelineGroup.getPartTimeline(locator);
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

    /*
    public static AnimationPose makeDynamicAdditivePose(AnimationPose referencePose, AnimationPose additivePose){

    }

     */
}
