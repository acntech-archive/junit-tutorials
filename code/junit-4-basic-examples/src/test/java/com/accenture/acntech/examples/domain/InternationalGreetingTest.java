package com.accenture.acntech.examples.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test case for the {@link InternationalGreeting} class.
 * <p/>
 * <ul>
 * <li>No longer needs to extend {@link junit.framework.TestCase}</li>
 * <li>Should be named the same as the class under test, with "Test" appended at the end</li>
 * <li>Should have the same package name as the class under test</li>
 * </ul>
 */
public class InternationalGreetingTest {

    /**
     * Test method.
     * <p/>
     * <ul>
     * <li>Must be annotated with the {@link Test} annotation</li>
     * <li>Name can be anything</li>
     * <li>Name should be descriptive and hint to what the test does</li>
     * <li>Should test only one condition per test method</li>
     * <li>Should assert values that are affected by the condition under test</li>
     * <li>Asserts should be static imports</li>
     * </ul>
     */
    @Test
    public void nameIsNull() {
        InternationalGreeting greeting = new InternationalGreeting();

        assertNull("Name should be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    @Test
    public void nameIsEmptyString() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setName("");

        assertNotNull("Name should not be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    @Test
    public void nameIsWhitespaceString() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setName("   ");

        assertNotNull("Name should not be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    @Test
    public void nameIsSet() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setName("Foo");

        assertNotNull("Name should not be null", greeting.getName());
        assertTrue("Name should be set", greeting.isNameSet());
        assertEquals("Name should match", "Foo", greeting.getName());
    }

    @Test
    public void languageIsNull() {
        InternationalGreeting greeting = new InternationalGreeting();

        assertNull("Language should be null", greeting.getLanguage());
        assertFalse("Language should not be set", greeting.isLanguageSet());
    }

    @Test
    public void languageIsEmptyString() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setLanguage("");

        assertNotNull("Language should not be null", greeting.getLanguage());
        assertFalse("Language should not be set", greeting.isLanguageSet());
    }

    @Test
    public void languageIsWhitespaceString() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setLanguage("   ");

        assertNotNull("Language should not be null", greeting.getLanguage());
        assertFalse("Language should not be set", greeting.isLanguageSet());
    }

    @Test
    public void languageIsSet() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setLanguage("en");

        assertNotNull("Language should not be null", greeting.getLanguage());
        assertTrue("Language should be set", greeting.isLanguageSet());
        assertEquals("Language should match", "en", greeting.getLanguage());
    }
}
