package fr.esgi.solid_library_kata.core.utils;

import java.io.FileWriter;
import java.io.IOException;

public class ResourceWriter implements JsonWriter {
    @Override
    public void write(String filename, String content) {
        try {
            var fileWriter = new FileWriter(filename);
            fileWriter.write(content);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
