package fr.esgi.solid_library_kata.core.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceConverter implements JsonConverter {
    private final Gson gson;

    public ResourceConverter() {
        gson = new Gson();
    }

    public <T> List<T> convertArray(String filename, Class<T[]> tClass) {
        var content = FileUtils.getResourceContent(filename);

        if (content.isEmpty() || content.isBlank()) {
            return null;
        }

        var res = gson.fromJson(content, tClass);
        return new ArrayList<>(Arrays.asList(res));
    }
}
