package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class AnimationSequencePlayer extends SampleableAnimationState {

    private float playRate = 1;
    private boolean looping = true;
    private boolean mirrored = false;
    ResourceLocation timelineGroupResourceLocation;

    private float timeElapsed;

    private AnimationSequencePlayer(String identifier, ResourceLocation resourceLocation) {
        super(identifier);
        this.timelineGroupResourceLocation = resourceLocation;
    }

    public static AnimationSequencePlayer of(String identifier, ResourceLocation resourceLocation){
        return new AnimationSequencePlayer(identifier, resourceLocation);
    }


    @Override
    public void tick(){

        this.timeElapsed += this.playRate;
    }

    private float getTimeFromTicks(){
        float frameLength = TimelineGroupData.INSTANCE.get(timelineGroupResourceLocation).getFrameLength();
        return this.looping ?
                (timeElapsed % frameLength) / frameLength :
                Mth.clamp(timeElapsed / frameLength, 0, 1);
    }

    public AnimationSequencePlayer setLooping(boolean looping){
        this.looping = looping;
        return this;
    }

    public AnimationSequencePlayer setMirroed(boolean mirrored){
        this.mirrored = mirrored;
        return this;
    }

    public AnimationSequencePlayer setPlayRate(float newPlayRate){
        this.playRate = newPlayRate;
        return this;
    }

    public void playFromStart(){
        this.timeElapsed = 0;
    }

    @Override
    public AnimationPose sample(LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer){
        return AnimationPose.fromChannelTimeline(locatorSkeleton, TimelineGroupData.INSTANCE.get(timelineGroupResourceLocation), this.getTimeFromTicks(), this.mirrored);
        //return super.sample(locatorSkeleton);
    }
}
