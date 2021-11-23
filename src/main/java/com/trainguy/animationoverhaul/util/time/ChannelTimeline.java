package com.trainguy.animationoverhaul.util.time;

import com.trainguy.animationoverhaul.util.data.TransformChannel;

import java.util.TreeMap;

public class ChannelTimeline<T> {

    private TreeMap<TransformChannel, TreeMap<Float, Timeline.Keyframe<T>>> keyframeChannels = new TreeMap();

    private Lerper<T> lerper;

    public T getValueAtFrame(TransformChannel transformChannel, float time) {
        TreeMap<Float, Timeline.Keyframe<T>> keyframes = keyframeChannels.get(transformChannel);
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

    public T getValueAt(TransformChannel transformChannel, float t) {
        TreeMap<Float, Timeline.Keyframe<T>> keyframes = keyframeChannels.get(transformChannel);
        return getValueAtFrame(transformChannel, t * keyframes.lastKey());
    }

    public ChannelTimeline(Lerper<T> lerper) {
        this.lerper = lerper;
        // Initiate a tree map for each channel transform
        for(TransformChannel transformChannel : TransformChannel.values()){
            this.keyframeChannels.put(transformChannel, new TreeMap<Float, Timeline.Keyframe<T>>());
        }
    }

    public ChannelTimeline<T> addKeyframe(TransformChannel transformChannel, float time, T value) {
        return addKeyframe(transformChannel, time, value, new Easing.Linear());
    }

    public ChannelTimeline<T> addKeyframe(TransformChannel transformChannel, float time, T value, Easing easing) {
        keyframeChannels.get(transformChannel).put(time, new Timeline.Keyframe<T>(value, easing));
        return this;
    }

    public static ChannelTimeline<Float> floatChannelTimeline() {
        return new ChannelTimeline<>((a, b, t) -> a + (b - a) * t);
    }
}
