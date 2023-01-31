package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.LocatorSkeleton;
import com.trainguy9512.animationoverhaul.util.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.util.data.TimelineGroupData;
import com.trainguy9512.animationoverhaul.util.time.TickTimeUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.HashMap;

public class AnimationSequencePlayer extends TimeBasedAnimationState {

    private boolean looping = true;
    private boolean mirrored = false;
    private ResourceLocation timelineGroupResourceLocation;
    private float frameLength;

    HashMap<String, AnimNotify> animNotifyMap = Maps.newHashMap();

    private AnimationSequencePlayer(String identifier, ResourceLocation resourceLocation) {
        super(identifier);
        this.timelineGroupResourceLocation = resourceLocation;
        this.frameLength = TimelineGroupData.INSTANCE.get(timelineGroupResourceLocation).getFrameLength();
    }

    public static AnimationSequencePlayer of(String identifier, ResourceLocation resourceLocation){
        return new AnimationSequencePlayer(identifier, resourceLocation);
    }

    @Override
    public void tick(){
        for(AnimNotify animNotify : animNotifyMap.values()){
            if(animNotify.isActive()){
                animNotify.setActive(false);
            } else if(this.looping){
                if(((this.getTimeElapsed() % this.frameLength) + this.getPlayRate()) > animNotify.getFrame() && (this.getTimeElapsed() % this.frameLength) < animNotify.getFrame()){
                    animNotify.setActive(true);
                }
            } else if((this.getTimeElapsed() + this.getPlayRate()) > animNotify.getFrame() && this.getTimeElapsed() < animNotify.getFrame()){
                animNotify.setActive(true);
            }
        }
        super.tick();
    }

    private float getTimeFromTicks(){
        return this.looping ?
                (this.getTimeElapsed() % this.frameLength) / this.frameLength :
                Mth.clamp(this.getTimeElapsed() / this.frameLength, 0, 1);
    }

    public float getTimeElapsedLooped(){
        return this.getTimeElapsed() % this.frameLength;
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

    public AnimationSequencePlayer addAnimNotify(String identifier, float frame){
        this.animNotifyMap.put(identifier, new AnimNotify(TickTimeUtils.ticksFromMayaFrames(frame)));
        return this;
    }

    public boolean isAnimNotityActive(String identifier){
        if(this.animNotifyMap.containsKey(identifier)){
            return this.animNotifyMap.get(identifier).isActive();
        }
        return false;
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

    private class AnimNotify {

        private boolean active = false;
        private final float frame;

        public AnimNotify(float frame){
            this.frame = frame;
        }

        public void setActive(boolean active){
            this.active = active;
        }

        public boolean isActive(){
            return this.active;
        }

        public float getFrame(){
            return this.frame;
        }
    }
}
