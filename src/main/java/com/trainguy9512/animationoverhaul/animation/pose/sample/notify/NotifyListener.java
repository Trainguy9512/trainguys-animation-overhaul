package com.trainguy9512.animationoverhaul.animation.pose.sample.notify;

import com.trainguy9512.animationoverhaul.animation.data.AnimationDriverContainer;
import com.trainguy9512.animationoverhaul.animation.data.PoseSamplerStateContainer;

import java.util.EventListener;

@FunctionalInterface
public interface NotifyListener extends EventListener {

    void notify(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer);

    public class Multi implements NotifyListener {

        private final NotifyListener a;
        private final NotifyListener b;

        public Multi(NotifyListener a, NotifyListener b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void notify(AnimationDriverContainer animationDriverContainer, PoseSamplerStateContainer poseSamplerStateContainer) {
            this.a.notify(animationDriverContainer, poseSamplerStateContainer);
            this.b.notify(animationDriverContainer, poseSamplerStateContainer);
        }

        public static NotifyListener combine(NotifyListener a, NotifyListener b){
            if (a == null) {
                return b;
            } else {
                return b == null ? a : new Multi(a, b);
            }
        }
    }
}
