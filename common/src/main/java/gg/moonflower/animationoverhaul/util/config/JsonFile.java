package gg.moonflower.animationoverhaul.util.config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

public class JsonFile {
    protected transient final Gson gson = new GsonBuilder()
            .registerTypeAdapter(getClass(), (InstanceCreator<JsonFile>)t -> this)
            .setPrettyPrinting()
            .create();

    private transient Path file;

    JsonFile() { }

    public JsonFile(Path file) {
        this.file = file;
    }

    public final void load() {
        if (Files.isReadable(file)) {
            try (Reader reader = Files.newBufferedReader(file)) {
                load(reader);
            } catch (Exception e) {
                //CaveDust.LOGGER.error("Invalid config", e);
            }
        }

        save();
    }

    public final void load(Reader reader) {
        gson.fromJson(reader, getClass());
    }

    public final void save() {
        try {
            Files.createDirectories(file.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(file)) {
                gson.toJson(this, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
