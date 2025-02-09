package com.trainguy9512.animationoverhaul.animation.pose.sample.notify;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDataKey;
import com.trainguy9512.animationoverhaul.animation.pose.sample.TimeBasedPoseSampler;

/**
 * Class with preset anim notify functions
 */
public class NotifyListeners {

    /**
     * Provides an anim notify that resets the time on the time-based pose sampler associated with the provided key.
     * @param poseSamplerKey    Time-based pose sampler key to reset the time on.
     */
    public static NotifyListener resetTimeNotifyListener(AnimationDataKey<TimeBasedPoseSampler> poseSamplerKey){
        return (animationDriverContainer, poseSamplerStateContainer) -> poseSamplerStateContainer.getPoseSampler(poseSamplerKey).resetTime();
    }
}
