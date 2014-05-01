package com.accenture.examples.domain;

public class Greeting {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNameSet() {
        return name != null && !name.trim().isEmpty();
    }
}
