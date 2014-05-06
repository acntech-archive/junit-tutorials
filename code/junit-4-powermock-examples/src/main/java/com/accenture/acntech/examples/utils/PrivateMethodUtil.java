package com.accenture.acntech.examples.utils;

public class PrivateMethodUtil {

    public String[] commaSeparated(String string) {
        return splitWithStringUtil(string, "\\s*,\\s*");
    }

    private String[] splitWithStringUtil(String string, String regex) {
        return StringUtil.split(string, regex);
    }
}
