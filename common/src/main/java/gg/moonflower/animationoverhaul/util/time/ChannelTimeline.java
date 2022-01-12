package gg.moonflower.animationoverhaul.util.time;

import gg.moonflower.animationoverhaul.util.data.TransformChannel;
import net.minecraft.util.Mth;

import java.util.List;
import java.util.TreeMap;

public class ChannelTimeline {

    private static final List<TransformChannel> rotations = List.of(TransformChannel.xRot, TransformChannel.yRot, TransformChannel.zRot);

    private TreeMap<TransformChannel, TreeMap<Float, Timeline.Keyframe<Float>>> keyframeChannels = new TreeMap();

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
        if(rotations.contains(transformChannel)){
            if(firstValue - secondValue < -Mth.PI){
                secondValue -= 2 * Mth.PI;
            }
            if(firstValue - secondValue > Mth.PI){
                secondValue += 2 * Mth.PI;
            }
        }
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
