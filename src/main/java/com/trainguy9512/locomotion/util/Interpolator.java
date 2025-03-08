package com.trainguy9512.locomotion.util;

import com.trainguy9512.locomotion.animation.pose.LocalSpacePose;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * @author Marvin Schürz
 */
@FunctionalInterface
public interface Interpolator<T>  {
    T interpolate(T a, T b, float time);

    Interpolator<Float> FLOAT = (a, b, time) -> a + (b - a) * time;
    Interpolator<Boolean> BOOLEAN_KEYFRAME = (a, b, time) -> time >= 1 ? b : a;
    Interpolator<Boolean> BOOLEAN_BLEND = (a, b, time) -> time >= 0.5f ? b : a;
    Interpolator<Vector3f> VECTOR = (a, b, time) -> a.lerp(b, time, new Vector3f());
    Interpolator<Quaternionf> QUATERNION = (a, b, time) -> a.slerp(b, time, new Quaternionf());
    Interpolator<LocalSpacePose> LOCAL_SPACE_POSE = LocalSpacePose::interpolated;

}
