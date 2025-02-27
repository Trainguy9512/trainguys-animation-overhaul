package com.trainguy9512.animationoverhaul.util;

import com.trainguy9512.animationoverhaul.animation.joint.JointTransform;

import java.util.TreeMap;

/**
 * @author Marvin Schürz
 */
public class Timeline<T> {

    private final TreeMap<Float, Keyframe<T>> keyframes;
    private final Interpolator<T> interpolator;
    private final float length;

    private Timeline(Interpolator<T> interpolator, float length) {
        this.keyframes = new TreeMap<>();
        this.interpolator = interpolator;
        this.length = length;
    }

    public static <T> Timeline<T> of(Interpolator<T> interpolator, float length){
        return new Timeline<>(interpolator, length);
    }

    /**
     * Returns the value at the given frame.
     * @param time      Time in ticks.
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


        return this.interpolator.interpolate(
                firstKeyframe.getValue().getValue(),
                secondKeyframe.getValue().getValue(),
                secondKeyframe.getValue().getEasing().ease(relativeTime)
        );
    }

    /**
     * Returns the value at the given time from 0 to 1, with 0 being the beginning and 1 being the end
     * @param time  Time from 0 to 1
     */
    public T getValueAtPercentage(float time) {
        return getValueAtFrame(time * this.keyframes.lastKey() + this.keyframes.firstKey());
    }

    /**
     * Returns the value at the looped given time.
     * @param time      Time in ticks.
     */
    public T getValueAtFrameLooped(float time) {
        return this.getValueAtFrame(time % this.length);
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

    public static Timeline<Float> ofFloat(float length) {
        return Timeline.of(Interpolator.FLOAT, length);
    }

    public static Timeline<JointTransform> ofJointTransform(float length) {
        return Timeline.of(JointTransform::interpolated, length);
    }
}
