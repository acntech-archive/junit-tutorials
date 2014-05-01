package com.accenture.examples.units;

public class SimpleFunctionalUnit {

    public String getGreeting(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        } else {
            return "Hello " + name + "!";
        }
    }
}
