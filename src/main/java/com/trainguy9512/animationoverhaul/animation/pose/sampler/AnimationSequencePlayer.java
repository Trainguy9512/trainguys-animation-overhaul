package com.trainguy9512.animationoverhaul.animation.pose.sampler;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.driver.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;
import com.trainguy9512.animationoverhaul.animation.pose.AnimationPose;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import com.trainguy9512.animationoverhaul.animation.data.AnimationSequenceData;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.notify.NotifyListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.TreeMap;

public class AnimationSequencePlayer extends TimeBasedPoseSampler implements Sampleable {

    private final boolean looping;
    private final ResourceLocation resourceLocation;
    private final float frameLength;
    private final float startTime;
    private final float endTime;
    TreeMap<Float, NotifyListener> animationTickNotifyListeners;

    private AnimationSequencePlayer(Builder<?> builder) {
        super(builder);
        this.looping = builder.looping;
        this.resourceLocation = builder.resourceLocation;
        this.frameLength = builder.frameLength;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.animationTickNotifyListeners = builder.animationTickNotifyListeners;
        this.timeElapsed = startTime;
    }

    public static Builder<?> builder(ResourceLocation resourceLocation){
        return new Builder<>(resourceLocation);
    }

    public static class Builder<B extends Builder<B>> extends TimeBasedPoseSampler.Builder<B> {

        private boolean looping = true;
        private final ResourceLocation resourceLocation;
        private final float frameLength;
        private float startTime = 0f;
        private float endTime;
        TreeMap<Float, NotifyListener> animationTickNotifyListeners = Maps.newTreeMap();

        protected Builder(ResourceLocation resourceLocation) {
            super();
            this.resourceLocation = resourceLocation;
            this.frameLength = AnimationSequenceData.INSTANCE.get(this.resourceLocation).frameLength();
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

        /**
         * Binds a notify to fire every time the animation sequence player hits the specified frame of animation measured in ticks
         * @param tick                  Frame of the animation to bind to, in ticks.
         * @param notifyListener        Notify listener to notify
         */
        @SuppressWarnings("unchecked")
        public B bindNotifyToAnimationTick(float tick, NotifyListener notifyListener){
            animationTickNotifyListeners.merge(tick, notifyListener, (time, notify) -> NotifyListener.Multi.combine(notify, notifyListener));
            return (B) this;
        }

        @Override
        public AnimationSequencePlayer build() {
            return new AnimationSequencePlayer(this);
        }
    }

    @Override
    public void tick(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer){
        for(float tick : this.animationTickNotifyListeners.keySet()){
            if(this.looping){
                if(((this.getTimeElapsed() % this.frameLength) + this.getPlayRate()) > tick && (this.getTimeElapsed() % this.frameLength) < tick){
                   animationTickNotifyListeners.get(tick).notify(animationDriverContainer, poseSamplerStateContainer);
                }
            } else if((this.getTimeElapsed() + this.getPlayRate()) > tick && this.getTimeElapsed() < tick){
                animationTickNotifyListeners.get(tick).notify(animationDriverContainer, poseSamplerStateContainer);
            }
        }
        super.tick(animationDriverContainer, poseSamplerStateContainer);
    }

    @Override
    public AnimationPose sample(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer, JointSkeleton jointSkeleton) {
        return AnimationPose.fromAnimationSequence(jointSkeleton, this.resourceLocation, this.processTime(this.getTimeElapsed()));
    }

    private float processTime(float inputTime){
        return this.looping ?
                (((inputTime) % (this.endTime - this.startTime)) + this.startTime) / this.frameLength :
                Mth.clamp(inputTime, 0, this.endTime) / this.frameLength;
    }

    public float getTimeElapsedLooped(){
        return this.getTimeElapsed() % this.frameLength;
    }

    @Override
    public void resetTime() {
        this.setTimeElapsed(this.startTime);
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
