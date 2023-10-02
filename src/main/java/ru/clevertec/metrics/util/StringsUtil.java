package ru.clevertec.metrics.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringsUtil {

    public String toLowerSnakeCase(String s) {
        return s.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
}
