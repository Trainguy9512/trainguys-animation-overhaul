package com.trainguy9512.animationoverhaul.animation.pose.sampler.notify;

import com.trainguy9512.animationoverhaul.animation.data.key.AnimationDataKey;
import com.trainguy9512.animationoverhaul.animation.pose.sampler.TimeBasedPoseSampler;

/**
 * Class with preset anim notify functions
 */
public class NotifyListeners {

    /**
     * Provides an anim notify that resets the time on the time-based pose sampler associated with the provided key.
     * @param poseSamplerKey    Time-based pose sampler key to reset the time on.
     */
    public static NotifyListener resetTimeNotifyListener(AnimationDataKey<? extends TimeBasedPoseSampler> poseSamplerKey){
        return (onTickDataContainer) -> onTickDataContainer.getPoseSampler(poseSamplerKey).resetTime();
    }
}
