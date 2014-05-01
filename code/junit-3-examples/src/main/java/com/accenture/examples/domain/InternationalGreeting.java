package com.accenture.examples.domain;

public class InternationalGreeting extends Greeting {

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isLanguageSet() {
        return language != null && !language.trim().isEmpty();
    }
}
