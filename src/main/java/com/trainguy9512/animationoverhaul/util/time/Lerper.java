package com.trainguy9512.animationoverhaul.util.time;

@FunctionalInterface
public interface Lerper<T>  {

    T lerp(T a, T b, float t);

}
