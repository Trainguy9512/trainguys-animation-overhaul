package com.trainguy9512.locomotion.animation.data;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.trainguy9512.locomotion.LocomotionMain;
import com.trainguy9512.locomotion.animation.joint.JointChannel;
import com.trainguy9512.locomotion.util.Interpolator;
import com.trainguy9512.locomotion.util.Timeline;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

public class AnimationSequenceDataLoader implements SimpleResourceReloadListener<Map<ResourceLocation, JsonElement>> {

    //<Map<ResourceLocation, JsonElement>>

    private static final Integer FORMAT_VERSION_1 = 1;
    private static final Integer FORMAT_VERSION_4 = 4;

    @Override
    public CompletableFuture<Map<ResourceLocation, JsonElement>> load(ResourceManager resourceManager, Executor executor) {
        Gson gson = new Gson();

        Map<ResourceLocation, Resource> passedFiles = resourceManager.listResources("sequences", (string) -> string.toString().endsWith(".json"));

        return CompletableFuture.supplyAsync(() -> {
            Map<ResourceLocation, JsonElement> jsonData = Maps.newHashMap();
            passedFiles.forEach((resourceLocation, resource) -> {
                try {
                    BufferedReader reader = resource.openAsReader();
                    JsonElement jsonElement = GsonHelper.fromJson(gson, reader, JsonElement.class);
                    jsonData.put(resourceLocation, jsonElement);
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return jsonData;
        }, executor);

        /*
        return CompletableFuture.supplyAsync(() -> {
            Map<ResourceLocation, JsonElement> map = Maps.newHashMap();
            for(ResourceLocation resourceLocation : passedFiles.keySet()){
                String resourceLocationPath = resourceLocation.getPath();
                try {
                    Optional<Resource> resourceOptional = resourceManager.getResource(resourceLocation);
                    Resource resource = resourceOptional.orElse(null);
                    try {
                        InputStream inputStream = resource.open();
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                            try {
                                JsonElement jsonElement = GsonHelper.fromJson(gson, reader, JsonElement.class);
                                if (jsonElement != null) {
                                    map.put(resourceLocation, jsonElement);
                                } else {
                                    LocomotionMain.LOGGER.error("Couldn't load data file {} as it's null or empty", resourceLocation);
                                }
                            } catch (Throwable bufferedReaderThrowable) {
                                try {
                                    reader.close();
                                } catch (Throwable var16) {
                                    bufferedReaderThrowable.addSuppressed(var16);
                                }
                                throw bufferedReaderThrowable;
                            }
                            reader.close();
                        } catch (Throwable inputStreamThrowable) {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable closeInputStreamThrowable) {
                                    inputStreamThrowable.addSuppressed(closeInputStreamThrowable);
                                }
                            }
                            throw inputStreamThrowable;
                        }
                        inputStream.close();
                    } catch (Throwable resourceThrowable) {
                        if (resource != null) {
                            try {
                                //resource.close();
                            } catch (Throwable closeResourceThrowable) {
                                resourceThrowable.addSuppressed(closeResourceThrowable);
                            }
                        }
                        throw resourceThrowable;
                    }
                    //resource.close();
                } catch (IOException e) {
                    LocomotionMain.LOGGER.error("Error parsing data upon grabbing resource for resourceLocation " + resourceLocation);
                }
            }
            return map;
        }, executor);
         */
    }

    @Override
    public CompletableFuture<Void> apply(Map<ResourceLocation, JsonElement> jsonData, ResourceManager resourceManager, Executor executor) {
        return CompletableFuture.runAsync(() -> {
            LocomotionMain.LOGGER.info("Loading {} animation sequences...", jsonData.size());
            AnimationSequenceData data = new AnimationSequenceData();
            jsonData.forEach((resourceLocation, sequenceElement) -> {
                JsonObject sequenceJSON = sequenceElement.getAsJsonObject();

                float sequenceFormatVersion = FORMAT_VERSION_1;
                if(sequenceJSON.has("format_version")){
                    sequenceFormatVersion = Float.parseFloat(sequenceJSON.get("format_version").getAsString());
                }

                if(sequenceFormatVersion >= FORMAT_VERSION_4) {
                    float sequenceLength = sequenceJSON.get("length").getAsFloat();
                    Map<String, JsonElement> joints = sequenceJSON.getAsJsonObject("joints").asMap();

                    AnimationSequenceData.AnimationSequence.Builder sequenceBuilder = AnimationSequenceData.AnimationSequence.builder(sequenceLength);
                    joints.forEach((joint, jointElement) -> {
                        JsonObject jointJSON = jointElement.getAsJsonObject();

                        Timeline<Vector3f> translationTimeline = createTimelineFromJSONChannel(
                                jointJSON.getAsJsonObject("translation").asMap(),
                                Interpolator.VECTOR,
                                sequenceLength,
                                AnimationSequenceDataLoader::extractVectorKeyframeValue
                        );
                        Timeline<Quaternionf> rotationTimeline = createTimelineFromJSONChannel(
                                jointJSON.getAsJsonObject("rotation").asMap(),
                                Interpolator.QUATERNION,
                                sequenceLength,
                                AnimationSequenceDataLoader::extractQuaternionKeyframeValue
                        );
                        Timeline<Vector3f> scaleTimeline = createTimelineFromJSONChannel(
                                jointJSON.getAsJsonObject("scale").asMap(),
                                Interpolator.VECTOR,
                                sequenceLength,
                                AnimationSequenceDataLoader::extractVectorKeyframeValue
                        );
                        Timeline<Boolean> visibilityTimeline = createTimelineFromJSONChannel(
                                jointJSON.getAsJsonObject("visibility").asMap(),
                                Interpolator.BOOLEAN_KEYFRAME,
                                sequenceLength,
                                JsonElement::getAsBoolean
                        );

                        sequenceBuilder.putJointTranslationTimeline(joint, translationTimeline);
                        sequenceBuilder.putJointRotationTimeline(joint, rotationTimeline);
                        sequenceBuilder.putJointScaleTimeline(joint, scaleTimeline);
                        sequenceBuilder.putJointVisibilityTimeline(joint, visibilityTimeline);
                        //LocomotionMain.LOGGER.info("joint {} translate {}", joint, translationTimeline.getValueAtTime(0));
                    });
                    data.put(resourceLocation, sequenceBuilder.build());
                    LocomotionMain.LOGGER.info("Successfully loaded animation {}", resourceLocation);
                } else {
                    LocomotionMain.LOGGER.warn("Skipping the loading of animation {} (Animation format version was {}, not up to date with {})", resourceLocation, sequenceFormatVersion, FORMAT_VERSION_4);
                }
            });
            LocomotionMain.LOGGER.info("Finished loading animations!");
            AnimationSequenceData.INSTANCE.clearAndReplace(data);
        });
    }

    private static <X> Timeline<X> createTimelineFromJSONChannel(Map<String, JsonElement> keyframes, Interpolator<X> interpolator, float sequenceLength, Function<JsonElement, X> jsonKeyframeValueExtractor){
        Timeline<X> timeline = Timeline.of(interpolator, sequenceLength);
        keyframes.forEach((keyframeString, keyframeValueElement) -> {
            float keyframe = Float.parseFloat(keyframeString);
            timeline.addKeyframe(keyframe, jsonKeyframeValueExtractor.apply(keyframeValueElement));
        });
        return timeline;
    }

    private static Vector3f extractVectorKeyframeValue(JsonElement keyframeElement){
        JsonArray components = keyframeElement.getAsJsonArray();
        return new Vector3f(
                components.get(0).getAsFloat(),
                components.get(1).getAsFloat(),
                components.get(2).getAsFloat()
        );
    }

    private static Quaternionf extractQuaternionKeyframeValue(JsonElement keyframeElement){
        JsonArray components = keyframeElement.getAsJsonArray();
        return new Quaternionf().rotationZYX(
                components.get(2).getAsFloat() * Mth.DEG_TO_RAD,
                components.get(1).getAsFloat() * Mth.DEG_TO_RAD,
                components.get(0).getAsFloat() * Mth.DEG_TO_RAD
        );
    }

    @Override
    public ResourceLocation getFabricId() {
        return ResourceLocation.fromNamespaceAndPath(LocomotionMain.MOD_ID, "animation_sequence_loader");
    }
}
