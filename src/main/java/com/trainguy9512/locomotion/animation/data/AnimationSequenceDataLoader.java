package com.trainguy9512.locomotion.animation.data;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.trainguy9512.locomotion.LocomotionMain;
import com.trainguy9512.locomotion.animation.joint.JointTransform;
import com.trainguy9512.locomotion.util.Timeline;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AnimationSequenceDataLoader implements SimpleResourceReloadListener<Map<ResourceLocation, JsonElement>> {

    //<Map<ResourceLocation, JsonElement>>

    private static final String FORMAT_VERSION = "0.3";

    @Override
    public CompletableFuture<Map<ResourceLocation, JsonElement>> load(ResourceManager resourceManager, Executor executor) {
        Gson gson = new Gson();

        Map<ResourceLocation, Resource> passedFiles = resourceManager.listResources("sequences", (string) -> {
            return string.toString().endsWith(".json");
        });

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
    }

    @Override
    public CompletableFuture<Void> apply(Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, ResourceManager resourceManager, Executor executor) {
        return CompletableFuture.runAsync(() -> {
            AnimationSequenceData newData = new AnimationSequenceData();
            for(ResourceLocation resourceLocationKey : resourceLocationJsonElementMap.keySet()){
                JsonElement animationJSON = resourceLocationJsonElementMap.get(resourceLocationKey);


                String resourceNamespace = resourceLocationKey.toString().split(":")[0];
                String resourceBody = resourceLocationKey.toString().split(":")[1].split("\\.")[0].replace("sequences/", "");
                ResourceLocation finalResourceLocation = ResourceLocation.fromNamespaceAndPath(resourceNamespace, resourceBody);

                //String entityKey = resourceLocationKey.toString().split("/")[1];
                //String animationKey = resourceLocationKey.toString().split("/")[2].split("\\.")[0];
                String formatVersion;
                if(animationJSON.getAsJsonObject().has("format_version")){
                    formatVersion = animationJSON.getAsJsonObject().get("format_version").getAsString();
                } else {
                    formatVersion = "0.1";
                }

                if(Objects.equals(formatVersion, FORMAT_VERSION)){
                    float frameRate = animationJSON.getAsJsonObject().get("frame_rate").getAsFloat();
                    float frameToTickDivisor = frameRate / 20f;
                    float frameLengthInTicks = animationJSON.getAsJsonObject().get("frame_length").getAsFloat() / frameToTickDivisor;

                    AnimationSequenceData.AnimationSequence.Builder animationSequenceBuilder = AnimationSequenceData.AnimationSequence.builder(frameLengthInTicks);

                    JsonArray partArrayJSON = animationJSON.getAsJsonObject().get("parts").getAsJsonArray();

                    // Get the lowest keyframe in the animation. I can reasonably expect no animation will have a higher keyframe than 10,000,000 right? lol
                    float startKeyframe = 10000000;
                    for(int partIndex = 0; partIndex < partArrayJSON.size(); partIndex++){
                        JsonObject partKeyframesJSON = partArrayJSON.get(partIndex).getAsJsonObject().get("keyframes").getAsJsonObject();
                        for(String keyframe : partKeyframesJSON.keySet()){
                            startKeyframe = Math.min(Integer.parseInt(keyframe), startKeyframe);
                        }
                    }

                    for(int partIndex = 0; partIndex < partArrayJSON.size(); partIndex++){
                        JsonObject partJSON = partArrayJSON.get(partIndex).getAsJsonObject();
                        String partName = partJSON.get("name").getAsString();
                        //AnimationOverhaul.LOGGER.info(partName);

                        Timeline<JointTransform> timeline = Timeline.ofJointTransform(frameLengthInTicks);

                        JsonObject partKeyframesJSON = partJSON.get("keyframes").getAsJsonObject();
                        for(Map.Entry<String, JsonElement> keyframeEntry : partKeyframesJSON.entrySet()) {
                            int keyframeNumber = Integer.parseInt(keyframeEntry.getKey());
                            float keyframeInTicks = (keyframeNumber - startKeyframe) / frameToTickDivisor;
                            JsonElement keyframeJSON = keyframeEntry.getValue();
                            //AnimationOverhaul.LOGGER.info(keyframeNumber);
                            Vector3f translation = new Vector3f(
                                    keyframeJSON.getAsJsonObject().get("translate").getAsJsonArray().get(0).getAsFloat(),
                                    keyframeJSON.getAsJsonObject().get("translate").getAsJsonArray().get(1).getAsFloat(),
                                    keyframeJSON.getAsJsonObject().get("translate").getAsJsonArray().get(2).getAsFloat()
                            );
                            Quaternionf rotation = new Quaternionf().rotationZYX(
                                    keyframeJSON.getAsJsonObject().get("rotate").getAsJsonArray().get(2).getAsFloat(),
                                    keyframeJSON.getAsJsonObject().get("rotate").getAsJsonArray().get(1).getAsFloat(),
                                    keyframeJSON.getAsJsonObject().get("rotate").getAsJsonArray().get(0).getAsFloat()
                            );
                            Vector3f scale = new Vector3f(1);
                            timeline.addKeyframe(keyframeInTicks, JointTransform.ofTranslationRotationScaleQuaternion(translation, rotation, scale));
                        }
                        animationSequenceBuilder.addTimelineForJoint(partName, timeline);
                    }


                    newData.put(finalResourceLocation, animationSequenceBuilder.build());
                    //AnimationOverhaul.LOGGER.info(frameLengthInTicks);
                    //AnimationOverhaul.LOGGER.info("Entity key: {} Animation key: {}", entityKey, animationKey);


                    LocomotionMain.LOGGER.info("Successfully loaded animation {}", resourceLocationKey);
                } else {
                    LocomotionMain.LOGGER.error("Failed to load animation {} (Animation format version was {}, not up to date with {})", resourceLocationKey, formatVersion, FORMAT_VERSION);
                }
            }

            AnimationSequenceData.INSTANCE.clearAndReplace(newData);
        });
    }

    @Override
    public ResourceLocation getFabricId() {
        return ResourceLocation.fromNamespaceAndPath(LocomotionMain.MOD_ID, "timeline_group_loader");
    }
}
