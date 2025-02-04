package com.trainguy9512.animationoverhaul.animation.pose.sample.notify;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;

import java.awt.*;
import java.util.EventListener;

@FunctionalInterface
public interface AnimNotify extends EventListener {

    void notify(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer);

    public class Multi implements AnimNotify {

        private final AnimNotify a;
        private final AnimNotify b;

        public Multi(AnimNotify a, AnimNotify b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void notify(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer) {
            this.a.notify(animationDriverContainer, poseSamplerStateContainer);
            this.b.notify(animationDriverContainer, poseSamplerStateContainer);
        }

        public static AnimNotify add(AnimNotify a, AnimNotify b){
            if (a == null) {
                return b;
            } else {
                return b == null ? a : new Multi(a, b);
            }
        }
    }
}
