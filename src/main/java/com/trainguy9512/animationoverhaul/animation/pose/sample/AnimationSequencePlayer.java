package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.TimelineGroupData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.HashMap;

public class AnimationSequencePlayer extends TimeBasedPoseSampler {

    private boolean looping = true;
    private ResourceLocation resourceLocation;
    private float frameLength;
    private float startTime = 0;
    private float endTime;

    HashMap<String, AnimNotify> animNotifyMap = Maps.newHashMap();

    private AnimationSequencePlayer(String identifier, ResourceLocation resourceLocation) {
        super(identifier);
        this.resourceLocation = resourceLocation;
        this.frameLength = TimelineGroupData.INSTANCE.get(this.resourceLocation).getFrameLength();
        this.endTime = this.frameLength;
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
                (((this.getTimeElapsed()) % (this.endTime - this.startTime)) + this.startTime) / this.frameLength :
                Mth.clamp(this.getTimeElapsed(), 0, this.endTime) / this.frameLength;
    }

    public float getTimeElapsedLooped(){
        return this.getTimeElapsed() % this.frameLength;
    }

    public AnimationSequencePlayer setLooping(boolean looping){
        this.looping = looping;
        return this;
    }

    public AnimationSequencePlayer setDefaultPlayRate(float newPlayRate){
        this.setPlayRate(newPlayRate);
        return this;
    }

    public AnimationSequencePlayer setStartTime(float startTimeTicks){
        this.startTime = startTimeTicks;
        return this;
    }

    public AnimationSequencePlayer setEndTime(float endTimeTicks){
        this.endTime = endTimeTicks;
        return this;
    }

    @Override
    public void resetTime() {
        this.setTimeElapsed(this.startTime);
    }

    public AnimationSequencePlayer addAnimNotify(String identifier, float tick){
        this.animNotifyMap.put(identifier, new AnimNotify(tick));
        return this;
    }

    public boolean isAnimNotityActive(String identifier){
        if(this.animNotifyMap.containsKey(identifier)){
            return this.animNotifyMap.get(identifier).isActive();
        }
        return false;
    }

    @Override
    public <L extends Enum<L>> AnimationPose<L> sample(JointSkeleton<L> jointSkeleton, AnimationDataContainer.CachedPoseContainer cachedPoseContainer){
        return AnimationPose.fromChannelTimeline(jointSkeleton, this.resourceLocation, this.getTimeFromTicks());
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
