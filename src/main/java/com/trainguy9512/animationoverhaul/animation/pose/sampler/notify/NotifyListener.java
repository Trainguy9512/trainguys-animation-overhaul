package com.trainguy9512.animationoverhaul.animation.pose.sampler.notify;

import java.util.EventListener;

@FunctionalInterface
public interface NotifyListener extends EventListener {

    void notify(DriverAnimationContainer driverContainer, PoseSamplerStateContainer poseSamplerStateContainer);

    public class Multi implements NotifyListener {

        private final NotifyListener a;
        private final NotifyListener b;

        public Multi(NotifyListener a, NotifyListener b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void notify(DriverAnimationContainer driverContainer, PoseSamplerStateContainer poseSamplerStateContainer) {
            this.a.notify(driverContainer, poseSamplerStateContainer);
            this.b.notify(driverContainer, poseSamplerStateContainer);
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
