package com.accenture.examples.units;

import com.accenture.examples.domain.Greeting;

public class IntermediateFunctionalUnit {

    public String getGreeting(Greeting greeting) {
        if (greeting == null) {
            throw new IllegalArgumentException("Greeting is null");
        } else if (!greeting.isNameSet()) {
            throw new IllegalArgumentException("Name is not set");
        } else {
            return "Hello " + greeting.getName() + "!";
        }
    }
}
