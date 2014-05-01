package com.accenture.examples.domain;

import junit.framework.TestCase;

/**
 * Test case for the {@link InternationalGreeting} class.
 * <p/>
 * <ul>
 * <li>Must extend {@link junit.framework.TestCase}</li>
 * <li>Should be named the same as the class under test, with "Test" appended at the end</li>
 * <li>Should have the same package name as the class under test</li>
 * </ul>
 */
public class InternationalGreetingTest extends TestCase {

    /**
     * Test method.
     * <p/>
     * <ul>
     * <li>Name should always start with "test"</li>
     * <li>Name should be descriptive and hint to what the test does</li>
     * <li>Should test only one condition per test method</li>
     * <li>Should assert values that are affected by the condition under test</li>
     * </ul>
     */
    public void testNameIsNull() {
        InternationalGreeting greeting = new InternationalGreeting();

        assertNull("Name should be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    public void testNameIsEmptyString() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setName("");

        assertNotNull("Name should not be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    public void testNameIsWhitespaceString() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setName("   ");

        assertNotNull("Name should not be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    public void testNameIsSet() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setName("Foo");

        assertNotNull("Name should not be null", greeting.getName());
        assertTrue("Name should be set", greeting.isNameSet());
        assertEquals("Name should match", "Foo", greeting.getName());
    }

    public void testLanguageIsNull() {
        InternationalGreeting greeting = new InternationalGreeting();

        assertNull("Language should be null", greeting.getLanguage());
        assertFalse("Language should not be set", greeting.isLanguageSet());
    }

    public void testLanguageIsEmptyString() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setLanguage("");

        assertNotNull("Language should not be null", greeting.getLanguage());
        assertFalse("Language should not be set", greeting.isLanguageSet());
    }

    public void testLanguageIsWhitespaceString() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setLanguage("   ");

        assertNotNull("Language should not be null", greeting.getLanguage());
        assertFalse("Language should not be set", greeting.isLanguageSet());
    }

    public void testLanguageIsSet() {
        InternationalGreeting greeting = new InternationalGreeting();
        greeting.setLanguage("en");

        assertNotNull("Language should not be null", greeting.getLanguage());
        assertTrue("Language should be set", greeting.isLanguageSet());
        assertEquals("Language should match", "en", greeting.getLanguage());
    }
}
