package com.trainguy9512.locomotion.animation.pose.sampler.notify;

import com.trainguy9512.locomotion.animation.data.OnTickDataContainer;

import java.util.EventListener;

@FunctionalInterface
@Deprecated
public interface NotifyListener extends EventListener {

    void notify(OnTickDataContainer dataContainer);

    public class Multi implements NotifyListener {

        private final NotifyListener a;
        private final NotifyListener b;

        public Multi(NotifyListener a, NotifyListener b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void notify(OnTickDataContainer dataContainer) {
            this.a.notify(dataContainer);
            this.b.notify(dataContainer);
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
