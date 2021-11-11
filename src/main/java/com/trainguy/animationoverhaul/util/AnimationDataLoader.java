package com.trainguy.animationoverhaul.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.trainguy.animationoverhaul.AnimationOverhaul;
import com.trainguy.animationoverhaul.util.timeline.ChannelTimeline;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AnimationDataLoader implements SimpleResourceReloadListener<Map<ResourceLocation, JsonElement>> {

    @Override
    public ResourceLocation getFabricId() {
        return null;
    }

    @Override
    public CompletableFuture<Map<ResourceLocation, JsonElement>> load(ResourceManager manager, ProfilerFiller profiler, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            Gson gson = new Gson();

            Collection<ResourceLocation> passedFiles = manager.listResources("animations", (string) -> {
                return string.endsWith(".json");
            });

            //String entity = "bee";
            //EntityType<?> entityType = EntityType.byString(entity).isPresent() ? EntityType.byString(entity).get() : null;

            //System.out.println(EntityType.getKey(entityType)[1]);
            //Map<ResourceLocation, JsonElement> tempMap = null;

            //Iterate over each found resource location and put its JSON element into a map
            Map<ResourceLocation, JsonElement> map = Maps.newHashMap();
            for(ResourceLocation resourceLocation : passedFiles){
                String resourceLocationPath = resourceLocation.getPath();
                try {
                    Resource resource = manager.getResource(resourceLocation);
                    try {
                        InputStream inputStream = resource.getInputStream();
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                            try {
                                JsonElement jsonElement = (JsonElement) GsonHelper.fromJson(gson, (Reader)reader, JsonElement.class);
                                if (jsonElement != null) {
                                    map.put(resourceLocation, jsonElement);
                                } else {
                                    AnimationOverhaul.LOGGER.error("Couldn't load data file {} as it's null or empty", resourceLocation);
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
                                resource.close();
                            } catch (Throwable closeResourceThrowable) {
                                resourceThrowable.addSuppressed(closeResourceThrowable);
                            }
                        }
                        throw resourceThrowable;
                    }
                    resource.close();
                } catch (IOException e) {
                    AnimationOverhaul.LOGGER.error("Error parsing data upon grabbing resource for resourceLocation " + resourceLocation);
                }
            }

            return map;
        }, executor);
    }

    @Override
    public CompletableFuture<Void> apply(Map<ResourceLocation, JsonElement> data, ResourceManager manager, ProfilerFiller profiler, Executor executor) {
        AnimationData newData = new AnimationData();
        for(ResourceLocation resourceLocationKey : data.keySet()){
            JsonElement animationJSON = data.get(resourceLocationKey);

            String entityKey = resourceLocationKey.toString().split("/")[1];
            String animationKey = resourceLocationKey.toString().split("/")[2].split("\\.")[0];
            float frameTime = animationJSON.getAsJsonObject().get("frame_length").getAsFloat();

            AnimationData.TimelineGroup timelineGroup = new AnimationData.TimelineGroup(frameTime);

            JsonArray partArrayJSON = animationJSON.getAsJsonObject().get("parts").getAsJsonArray();
            for(int partIndex = 0; partIndex < partArrayJSON.size(); partIndex++){
                JsonObject partJSON = partArrayJSON.get(partIndex).getAsJsonObject();
                String partName = partJSON.get("name").getAsString();
                //AnimationOverhaul.LOGGER.info(partName);

                ChannelTimeline<Float> channelTimeline = ChannelTimeline.floatChannelTimeline();

                JsonObject partKeyframesJSON = partJSON.get("keyframes").getAsJsonObject();
                for(Map.Entry<String, JsonElement> keyframeEntry : partKeyframesJSON.entrySet()){
                    int keyframeNumber = Integer.parseInt(keyframeEntry.getKey());
                    JsonElement keyframeJSON = keyframeEntry.getValue();
                    //AnimationOverhaul.LOGGER.info(keyframeNumber);

                    for(Map.Entry<String, JsonElement> attributeEntry : keyframeJSON.getAsJsonObject().entrySet()){
                        TransformChannel transformChannel = TransformChannel.valueOf(attributeEntry.getKey());
                        float keyframeValue = attributeEntry.getValue().getAsFloat();

                        channelTimeline = channelTimeline.addKeyframe(transformChannel, keyframeNumber, keyframeValue);
                        //AnimationOverhaul.LOGGER.info("Channel: {} Value: {}", transformChannel, keyframeValue);
                    }
                }
                timelineGroup.addPartTimeline(partName, channelTimeline);
            }

            newData.put(entityKey, animationKey, timelineGroup);
            //AnimationOverhaul.LOGGER.info(frameTime);
            //AnimationOverhaul.LOGGER.info("Entity key: {} Animation key: {}", entityKey, animationKey);
        }

        AnimationData.loadedData = newData;
        return CompletableFuture.completedFuture(null);
    }
}
