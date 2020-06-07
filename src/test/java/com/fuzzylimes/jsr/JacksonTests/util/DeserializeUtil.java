package com.fuzzylimes.jsr.JacksonTests.util;

import java.net.URL;

public class DeserializeUtil {

    public static URL getFile(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getClass().getResource(fileName);
    }
}
