package com.trainguy9512.animationoverhaul.animation.pose.sample;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.AnimationOverhaulMain;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.util.animation.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationDataContainer;
import com.trainguy9512.animationoverhaul.animation.data.TimelineGroupData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.HashMap;

public class AnimationSequencePlayer extends TimeBasedPoseSampler {

    private boolean looping;
    private ResourceLocation resourceLocation;
    private float frameLength;
    private float startTime;
    private float endTime;

    HashMap<String, AnimNotify> animNotifyMap = Maps.newHashMap();

    private AnimationSequencePlayer(Builder<?> builder) {
        super(builder);
        this.looping = builder.looping;
        this.resourceLocation = builder.resourceLocation;
        this.frameLength = builder.frameLength;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;

        this.timeElapsed = startTime;
    }

    public static Builder<?> of(ResourceLocation resourceLocation){
        return new Builder<>(resourceLocation);
    }


    public static class Builder<B extends Builder<B>> extends TimeBasedPoseSampler.Builder<B> {

        private boolean looping = true;
        private final ResourceLocation resourceLocation;
        private final float frameLength;
        private float startTime = 0f;
        private float endTime;

        protected Builder(ResourceLocation resourceLocation) {
            super();
            this.resourceLocation = resourceLocation;
            this.frameLength = TimelineGroupData.INSTANCE.get(this.resourceLocation).getFrameLength();
            this.endTime = frameLength;
        }

        @SuppressWarnings("unchecked")
        public B setLooping(boolean looping){
            this.looping = looping;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setStartTime(float startTime){
            this.startTime = startTime;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setEndTime(float endTime){
            this.endTime = endTime;
            return (B) this;
        }

        @Override
        public AnimationSequencePlayer build() {
            return new AnimationSequencePlayer(this);
        }
    }

    @Override
    public void tick(AnimationDataContainer animationDataContainer){
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
        super.tick(animationDataContainer);
    }

    private float getTimeFromTicks(){
        return this.looping ?
                (((this.getTimeElapsed()) % (this.endTime - this.startTime)) + this.startTime) / this.frameLength :
                Mth.clamp(this.getTimeElapsed(), 0, this.endTime) / this.frameLength;
    }

    public float getTimeElapsedLooped(){
        return this.getTimeElapsed() % this.frameLength;
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
    public <L extends Enum<L>> AnimationPose<L> sample(JointSkeleton<L> jointSkeleton){
        return AnimationPose.fromChannelTimeline(jointSkeleton, this.resourceLocation, this.getTimeFromTicks());
        //return super.sample(locatorSkeleton);
    }

    //TODO: Rewrite this with functions
    private static class AnimNotify {

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
