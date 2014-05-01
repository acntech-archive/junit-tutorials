package com.accenture.examples.units;

import com.accenture.examples.domain.InternationalGreeting;

import java.util.HashMap;
import java.util.Map;

public class ComplexFunctionalUnit {

    private static final Map<String, String> INTERNATIONAL_GREETING_PREFIX = new HashMap<String, String>();

    static {
        INTERNATIONAL_GREETING_PREFIX.put("en", "Hello");
        INTERNATIONAL_GREETING_PREFIX.put("no", "Hei");
        INTERNATIONAL_GREETING_PREFIX.put("hi", "Namaste");
        INTERNATIONAL_GREETING_PREFIX.put("fr", "Bonjour");
        INTERNATIONAL_GREETING_PREFIX.put("es", "Hola");
    }

    public String getGreeting(InternationalGreeting greeting) {
        if (greeting == null) {
            throw new IllegalArgumentException("Greeting is null");
        } else {
            String language = getAndVerifyLanguage(greeting);
            String greetingPrefix = getGreetingPrefix(language);
            String name = getAndVerifyName(greeting);
            return greetingPrefix + " " + name + "!";
        }
    }

    private String getAndVerifyName(InternationalGreeting greeting) {
        if (!greeting.isNameSet()) {
            throw new IllegalArgumentException("Name is not set");
        } else {
            return greeting.getName();
        }
    }

    private String getAndVerifyLanguage(InternationalGreeting greeting) {
        if (!greeting.isLanguageSet()) {
            throw new IllegalArgumentException("Language is not set");
        } else {
            return greeting.getLanguage();
        }
    }

    private String getGreetingPrefix(String language) {
        String greetingPrefix = INTERNATIONAL_GREETING_PREFIX.get(language);
        if (greetingPrefix == null) {
            throw new IllegalArgumentException("Language is invalid");
        } else {
            return greetingPrefix;
        }
    }
}
