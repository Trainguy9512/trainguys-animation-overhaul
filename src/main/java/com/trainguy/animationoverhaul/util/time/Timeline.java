package com.trainguy.animationoverhaul.util.time;

import java.util.TreeMap;

public class Timeline<T> {

    private TreeMap<Float, Keyframe<T>> keyframes = new TreeMap();

    private Lerper<T> lerper;

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


        return lerper.lerp(
                firstKeyframe.getValue().getValue(),
                secondKeyframe.getValue().getValue(),
                secondKeyframe.getValue().getEasing().ease(relativeTime)
        );
    }

    public T getValueAt(float t) {
        return getValueAtFrame(t * this.keyframes.lastKey());
    }

    public Timeline(Lerper<T> lerper) {
        this.lerper = lerper;
    }

    public Timeline<T> addKeyframe(float time, T value) {
        return addKeyframe(time, value, new Easing.Linear());
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
        return new Timeline<Float>((a, b, t) -> a + (b - a) * t);
    }

    public static Timeline<Float> oscillateLerpTimeline(Easing.CubicBezier bezier1){
        return oscillateLerpTimeline(bezier1, bezier1);
    }
    public static Timeline<Float> oscillateLerpTimeline(Easing.CubicBezier bezier1, Easing.CubicBezier bezier2){
        return floatTimeline()
                .addKeyframe(0, 0F)
                .addKeyframe(5, 1F, bezier1)
                .addKeyframe(10, 0F, bezier2);
    }
}
