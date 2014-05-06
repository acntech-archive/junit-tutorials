package com.accenture.examples.utils;

public class StringUtil {

    public String[] split(String string, String regex) {
        if (string == null || regex == null) {
            throw new IllegalArgumentException("Input is null");
        } else {
            return string.split(regex);
        }
    }

    public String join(String[] stringArray, String glue) {
        if (stringArray == null || glue == null) {
            throw new IllegalArgumentException("Input is null");
        } else if (stringArray.length == 0) {
            return null;
        } else {
            String string = stringArray[0];
            for (int i = 1; i < stringArray.length; i++) {
                string += glue + stringArray[i];
            }
            return string;
        }
    }

    public String capitalize(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Input is null");
        } else if (string.trim().isEmpty()) {
            return string;
        } else {
            string = string.trim().toLowerCase();
            return Character.toUpperCase(string.charAt(0)) + string.substring(1);
        }
    }
}
