package com.trainguy9512.animationoverhaul.util.data;

import java.util.List;

public enum TransformChannel {
    x,
    y,
    z,
    xRot,
    yRot,
    zRot;

    public static List<TransformChannel> rotationChannels(){
        return List.of(xRot, yRot, zRot);
    }

    public static List<TransformChannel> translateChannels(){
        return List.of(x, y, z);
    }
}
