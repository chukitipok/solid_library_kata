package fr.esgi.solid_library_kata.core.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class FileUtils {

    public static File getResourceFile(String filename) {
        try {
            var loader = FileUtils.class.getClassLoader();
            var resource = loader.getResource(filename);

            if (resource == null) {
                throw new IllegalArgumentException("File not found.");
            }

            return new File(resource.toURI());

        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("File not found.");
        }
    }

    public static String getResourceContent(String filename) {
        try {
            var file = getResourceFile(filename);
            return Files.readString(file.toPath());

        } catch (IOException e) {
            return "";
        }
    }
}
