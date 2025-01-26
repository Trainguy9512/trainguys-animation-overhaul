package com.trainguy9512.animationoverhaul.animation.data;

public interface AnimationDriverContainerState {
    public <D> D get(AnimationDriverKey<D> dataKey);
}
