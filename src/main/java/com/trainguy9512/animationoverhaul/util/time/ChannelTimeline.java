package com.trainguy9512.animationoverhaul.util.time;

import com.trainguy9512.animationoverhaul.animation.data.TransformChannel;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

import java.util.List;
import java.util.TreeMap;

public class ChannelTimeline {

    private static final List<TransformChannel> rotations = List.of(TransformChannel.xRot, TransformChannel.yRot, TransformChannel.zRot);

    private TreeMap<TransformChannel, TreeMap<Float, Timeline.Keyframe<Float>>> keyframeChannels = new TreeMap();

    private float getKeyframeValue(TransformChannel transformChannel, float time, boolean first){
        TreeMap<Float, Timeline.Keyframe<Float>> keyframes = keyframeChannels.get(transformChannel);
        var firstKeyframe = keyframes.floorEntry(time);
        var secondKeyframe = keyframes.ceilingEntry(time);
        if(first){
            if (firstKeyframe == null)
                return secondKeyframe.getValue().getValue();
            return firstKeyframe.getValue().getValue();
        } else {
            if (secondKeyframe == null)
                return firstKeyframe.getValue().getValue();
            return secondKeyframe.getValue().getValue();
        }
    }

    public Quaternionf getRotationAtFrame(float time){
        TreeMap<Float, Timeline.Keyframe<Float>> keyframes = keyframeChannels.get(TransformChannel.xRot);
        var firstKeyframe = keyframes.floorEntry(time);
        var secondKeyframe = keyframes.ceilingEntry(time);

        if(secondKeyframe == null){
            secondKeyframe = firstKeyframe;
        }
        if(firstKeyframe == null){
            firstKeyframe = secondKeyframe;
        }


        /*
        Quaternionf rotationFirst = new Quaternionf()
                .rotationZYX(
                        this.getKeyframeValue(TransformChannel.zRot, time, true),
                        this.getKeyframeValue(TransformChannel.yRot, time, true),
                        this.getKeyframeValue(TransformChannel.xRot, time, true)
                );
        Quaternionf rotationSecond = new Quaternionf()
                .rotationZYX(
                        this.getKeyframeValue(TransformChannel.zRot, time, false),
                        this.getKeyframeValue(TransformChannel.yRot, time, false),
                        this.getKeyframeValue(TransformChannel.xRot, time, false)
                        );
         */

        Quaternionf rotationFirst = new Quaternionf()
                .rotationZYX(
                        this.getKeyframeValue(TransformChannel.zRot, time, true),
                        this.getKeyframeValue(TransformChannel.yRot, time, true),
                        this.getKeyframeValue(TransformChannel.xRot, time, true)
                );
        Quaternionf rotationSecond = new Quaternionf()
                .rotationZYX(
                        this.getKeyframeValue(TransformChannel.zRot, time, false),
                        this.getKeyframeValue(TransformChannel.yRot, time, false),
                        this.getKeyframeValue(TransformChannel.xRot, time, false)
                );


        /*
        Quaternionf rotationFirst = new Quaternionf()
                .rotationY(this.getKeyframeValue(TransformChannel.yRot, time, true))
                .rotationZ(this.getKeyframeValue(TransformChannel.zRot, time, true))
                .rotationX(this.getKeyframeValue(TransformChannel.xRot, time, true));
        Quaternionf rotationSecond = new Quaternionf()
                .rotationY(this.getKeyframeValue(TransformChannel.yRot, time, false))
                .rotationZ(this.getKeyframeValue(TransformChannel.zRot, time, false))
                .rotationX(this.getKeyframeValue(TransformChannel.xRot, time, false));

         */

        if (firstKeyframe.getKey().equals(secondKeyframe.getKey()))
            return rotationFirst;

        float relativeTime = (time - firstKeyframe.getKey()) / (secondKeyframe.getKey() - firstKeyframe.getKey());
        return rotationFirst.slerp(rotationSecond, secondKeyframe.getValue().getEasing().ease(relativeTime));
    }

    public Quaternionf getRotationAt(float t) {
        TreeMap<Float, Timeline.Keyframe<Float>> keyframes = keyframeChannels.get(TransformChannel.xRot);
        return getRotationAtFrame(t * keyframes.lastKey());
    }

    public float getValueAtFrame(TransformChannel transformChannel, float time) {
        TreeMap<Float, Timeline.Keyframe<Float>> keyframes = keyframeChannels.get(transformChannel);
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


        float firstValue = firstKeyframe.getValue().getValue();
        float secondValue = secondKeyframe.getValue().getValue();
        /*
        if(rotations.contains(transformChannel)){
            if(firstValue - secondValue < -Mth.PI){
                secondValue -= 2 * Mth.PI;
            }
            if(firstValue - secondValue > Mth.PI){
                secondValue += 2 * Mth.PI;
            }
        }

         */
        return Mth.lerp(
                secondKeyframe.getValue().getEasing().ease(relativeTime),
                firstValue,
                secondValue
        );
    }

    public float getValueAt(TransformChannel transformChannel, float t) {
        TreeMap<Float, Timeline.Keyframe<Float>> keyframes = keyframeChannels.get(transformChannel);
        return getValueAtFrame(transformChannel, t * keyframes.lastKey());
    }

    public ChannelTimeline() {
        // Initiate a tree map for each channel transform
        for(TransformChannel transformChannel : TransformChannel.values()){
            this.keyframeChannels.put(transformChannel, new TreeMap<Float, Timeline.Keyframe<Float>>());
        }
    }

    public ChannelTimeline addKeyframe(TransformChannel transformChannel, float time, float value) {
        return addKeyframe(transformChannel, time, value, new Easing.Linear());
    }

    public ChannelTimeline addKeyframe(TransformChannel transformChannel, float time, float value, Easing easing) {
        keyframeChannels.get(transformChannel).put(time, new Timeline.Keyframe<Float>(value, easing));
        return this;
    }
}
