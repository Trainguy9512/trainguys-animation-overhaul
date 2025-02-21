package com.trainguy9512.animationoverhaul.util;

import org.joml.Vector3f;

import java.util.Vector;

/**
 * @author Marvin Schürz
 */
@FunctionalInterface
public interface Interpolator<T>  {

    T interpolate(T a, T b, float time);


    Interpolator<Float> FLOAT = (a, b, time) -> a + (b - a) * time;
    Interpolator<Vector3f> VECTOR = (a, b, time) -> a.lerp(b, time, new Vector3f());

}
