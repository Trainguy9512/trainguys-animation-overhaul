package com.trainguy9512.animationoverhaul.animation.data;

import java.util.function.Supplier;

public class AnimationDataKey<D> {

    private final Supplier<D> defaultValue;

    public AnimationDataKey(Supplier<D> defaultValue) {
        this.defaultValue = defaultValue;
    }
}
