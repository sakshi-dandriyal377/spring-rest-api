package com.springcrud.utils;

import java.util.function.Function;

public class ServiceUtils {

    private ServiceUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getenv(String variable) {
        return ((Function<String, String>) System::getenv).apply(variable);
    }
}
