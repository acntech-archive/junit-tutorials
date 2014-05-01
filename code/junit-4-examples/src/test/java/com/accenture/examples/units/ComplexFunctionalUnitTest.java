package com.accenture.examples.units;

import com.accenture.examples.domain.InternationalGreeting;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test case for the {@link ComplexFunctionalUnit} class.
 * <p/>
 * <ul>
 * <li>No longer needs to extends {@link junit.framework.TestCase}</li>
 * <li>Should be named the same as the class under test, with "Test" appended at the end</li>
 * <li>Should have the same package name as the class under test</li>
 * </ul>
 */
public class ComplexFunctionalUnitTest {

    private ComplexFunctionalUnit cfu;

    /**
     * Test case set-up method.
     * <p/>
     * This method is invoked before each of the test methods of this test case.
     * <p/>
     * <ul>
     * <li>Must be annotated with the {@link org.junit.Before} annotation</li>
     * <li>Should contain common scaffolding needed to run tests</li>
     * <li>Extensive scaffolding is a sign of too monolithic production code</li>
     * </ul>
     */
    @Before
    public void setUp() {
        cfu = new ComplexFunctionalUnit();
    }

    /**
     * Test method.
     * <p/>
     * <ul>
     * <li>Must be annotated with the {@link org.junit.Test} annotation</li>
     * <li>Name can be anything</li>
     * <li>Name should be descriptive and hint to what the test does</li>
     * <li>Should test only one condition per test method</li>
     * <li>Should assert values that are affected by the condition under test</li>
     * <li>Asserts should be static imports</li>
     * </ul>
     */
    @Test
    public void greetingIsNullShouldThrowException() {
        try {
            cfu.getGreeting(null);

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue("Wrong exception type", e instanceof IllegalArgumentException);
            assertEquals("Wrong exception message", "Greeting is null", e.getMessage());
        }
    }

    @Test
    public void languageIsNullShouldThrowException() {
        try {
            cfu.getGreeting(new InternationalGreeting());

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue("Wrong exception type", e instanceof IllegalArgumentException);
            assertEquals("Wrong exception message", "Language is not set", e.getMessage());
        }
    }

    @Test
    public void languageIsInvalidShouldThrowException() {
        InternationalGreeting ig = new InternationalGreeting();
        ig.setLanguage("wrong");
        try {
            cfu.getGreeting(ig);

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue("Wrong exception type", e instanceof IllegalArgumentException);
            assertEquals("Wrong exception message", "Language is invalid", e.getMessage());
        }
    }

    @Test
    public void nameIsNullShouldThrowException() {
        InternationalGreeting ig = new InternationalGreeting();
        ig.setLanguage("en");
        try {
            cfu.getGreeting(ig);

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue("Wrong exception type", e instanceof IllegalArgumentException);
            assertEquals("Wrong exception message", "Name is not set", e.getMessage());
        }
    }

    @Test
    public void englishGreetingIsSet() {
        InternationalGreeting g = new InternationalGreeting();
        g.setLanguage("en");
        g.setName("Foo");
        String greeting = cfu.getGreeting(g);

        assertNotNull("Greeting should not be null", greeting);
        assertEquals("Greeting should be equal", "Hello Foo!", greeting);
    }

    @Test
    public void norwegianGreetingIsSet() {
        InternationalGreeting g = new InternationalGreeting();
        g.setLanguage("no");
        g.setName("Foo");
        String greeting = cfu.getGreeting(g);

        assertNotNull("Greeting should not be null", greeting);
        assertEquals("Greeting should be equal", "Hei Foo!", greeting);
    }

    @Test
    public void hindiGreetingIsSet() {
        InternationalGreeting g = new InternationalGreeting();
        g.setLanguage("hi");
        g.setName("Foo");
        String greeting = cfu.getGreeting(g);

        assertNotNull("Greeting should not be null", greeting);
        assertEquals("Greeting should be equal", "Namaste Foo!", greeting);
    }
}
