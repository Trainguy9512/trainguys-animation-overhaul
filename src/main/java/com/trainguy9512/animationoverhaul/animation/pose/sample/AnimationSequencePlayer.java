package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class AnimationSequencePlayer extends TimeBasedAnimationState {

    private boolean looping = true;
    private boolean mirrored = false;
    ResourceLocation timelineGroupResourceLocation;

    private AnimationSequencePlayer(String identifier, ResourceLocation resourceLocation) {
        super(identifier);
        this.timelineGroupResourceLocation = resourceLocation;
    }

    public static AnimationSequencePlayer of(String identifier, ResourceLocation resourceLocation){
        return new AnimationSequencePlayer(identifier, resourceLocation);
    }

    @Override
    public void tick(){
        super.tick();
    }

    private float getTimeFromTicks(){
        float frameLength = TimelineGroupData.INSTANCE.get(timelineGroupResourceLocation).getFrameLength();
        return this.looping ?
                (this.getTimeElapsed() % frameLength) / frameLength :
                Mth.clamp(this.getTimeElapsed() / frameLength, 0, 1);
    }

    public AnimationSequencePlayer setLooping(boolean looping){
        this.looping = looping;
        return this;
    }

    public AnimationSequencePlayer setMirroed(boolean mirrored){
        this.mirrored = mirrored;
        return this;
    }

    public AnimationSequencePlayer setDefaultPlayRate(float newPlayRate){
        this.setPlayRate(newPlayRate);
        return this;
    }

    public void playFromStartOnStateActive(AnimationStateMachine animationStateMachine, String stateIdentifier){
        if(animationStateMachine.containsState(stateIdentifier)){
            if(animationStateMachine.getState(stateIdentifier).getWeight() == 0){
                this.resetTime();
            }
        }
    }

    @Override
    public AnimationPose sample(LocatorSkeleton locatorSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer){
        return AnimationPose.fromChannelTimeline(locatorSkeleton, TimelineGroupData.INSTANCE.get(timelineGroupResourceLocation), this.getTimeFromTicks(), this.mirrored);
        //return super.sample(locatorSkeleton);
    }
}
