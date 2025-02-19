package com.trainguy9512.animationoverhaul.util;

import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;

import java.util.TreeMap;

/**
 * @author Marvin Schürz
 */
public class Timeline<T> {

    private TreeMap<Float, Keyframe<T>> keyframes = new TreeMap();
    private final Interpolator<T> lerper;

    public Timeline(Interpolator<T> lerper) {
        this.lerper = lerper;
    }

    /**
     * Returns the value at the given frame
     * @param time  Time in keyframes
     * @return
     */
    public T getValueAtFrame(float time) {
        var firstKeyframe = keyframes.floorEntry(time);
        var secondKeyframe = keyframes.ceilingEntry(time);

        if (firstKeyframe == null)
            return secondKeyframe.getValue().getValue();
        if (secondKeyframe == null)
            return firstKeyframe.getValue().getValue();

        //same frame
        if (firstKeyframe.getKey().equals(secondKeyframe.getKey()))
            return firstKeyframe.getValue().getValue();



        float relativeTime = (time - firstKeyframe.getKey()) / (secondKeyframe.getKey() - firstKeyframe.getKey());


        return lerper.interpolate(
                firstKeyframe.getValue().getValue(),
                secondKeyframe.getValue().getValue(),
                secondKeyframe.getValue().getEasing().ease(relativeTime)
        );
    }

    /**
     * Returns the value at the given time from 0 to 1, with 0 being the beginning and 1 being the end
     * @param time  Time from 0 to 1
     * @return
     */
    public T getValueAtPercentage(float time) {
        return getValueAtFrame(time * this.keyframes.lastKey() + this.keyframes.firstKey());
    }

    public Timeline<T> addKeyframe(float time, T value) {
        return addKeyframe(time, value, Easing.LINEAR);
    }

    public Timeline<T> addKeyframe(float time, T value, Easing easing) {
        keyframes.put(time, new Keyframe<T>(value, easing));
        return this;
    }

    public static class Keyframe<T> {
        private final T value;
        Easing easing;

        public Keyframe(T value, Easing easing) {
            this.value = value;
            this.easing = easing;
        }

        public T getValue() {
            return value;
        }

        public Easing getEasing() {
            return easing;
        }

    }

    public static Timeline<Float> floatTimeline() {
        return new Timeline<>((a, b, t) -> a + (b - a) * t);
    }

    public static Timeline<JointTransform> jointTransformTimeline() {
        return new Timeline<>(JointTransform::interpolated);
    }
}
