package fr.esgi.solid_library_kata.core.utils;

import java.util.List;

public interface JsonConverter {

    <T> List<T> convertArray(String content, Class<T[]> tClass);
}
