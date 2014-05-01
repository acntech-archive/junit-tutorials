package com.accenture.examples;

import com.accenture.examples.domain.Greeting;
import com.accenture.examples.domain.InternationalGreeting;
import com.accenture.examples.units.ComplexFunctionalUnit;
import com.accenture.examples.units.IntermediateFunctionalUnit;
import com.accenture.examples.units.SimpleFunctionalUnit;

public class App {

    public static void main(String[] args) {
        System.out.println("Running App!");

        SimpleFunctionalUnit sfu = new SimpleFunctionalUnit();
        System.out.println(sfu.getGreeting("Foo"));

        IntermediateFunctionalUnit ifu = new IntermediateFunctionalUnit();
        Greeting g = new Greeting();
        g.setName("Foo");
        System.out.println(ifu.getGreeting(g));

        ComplexFunctionalUnit cfu = new ComplexFunctionalUnit();
        InternationalGreeting ig = new InternationalGreeting();
        ig.setName("Foo");
        ig.setLanguage("en");
        System.out.println(cfu.getGreeting(ig));
    }
}
