package com.accenture.acntech.examples;

import com.accenture.acntech.examples.services.PowerMockWithEasyMockService;

public class App {

    public static void main(String[] args) {
        System.out.println("Running App!");

        PowerMockWithEasyMockService service = new PowerMockWithEasyMockService();
        System.out.println("Result: " + service.createCamelCaseFromSentence("the test string"));
    }
}
