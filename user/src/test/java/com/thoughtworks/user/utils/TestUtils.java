package com.thoughtworks.user.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readResource(String filename, Class<T> cls) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(TestUtils.class.getClassLoader().getResource(filename), cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
