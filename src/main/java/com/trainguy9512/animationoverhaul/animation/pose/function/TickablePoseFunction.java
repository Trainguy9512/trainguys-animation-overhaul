package com.trainguy9512.animationoverhaul.animation.pose.function;

import com.trainguy9512.animationoverhaul.animation.data.OnTickDataContainer;

public interface TickablePoseFunction extends PoseFunction {
    void tick(OnTickDataContainer dataContainer);
}
