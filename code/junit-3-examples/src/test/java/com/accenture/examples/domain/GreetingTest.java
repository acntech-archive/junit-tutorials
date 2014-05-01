package com.accenture.examples.domain;

import junit.framework.TestCase;

/**
 * Test case for the {@link Greeting} class.
 * <p/>
 * <ul>
 * <li>Must extends {@link junit.framework.TestCase}</li>
 * <li>Should be named the same as the class under test, with "Test" appended at the end</li>
 * <li>Should have the same package name as the class under test</li>
 * </ul>
 */
public class GreetingTest extends TestCase {

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
        Greeting greeting = new Greeting();

        assertNull("Name should be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    public void testNameIsEmptyString() {
        Greeting greeting = new Greeting();
        greeting.setName("");

        assertNotNull("Name should not be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    public void testNameIsWhitespaceString() {
        Greeting greeting = new Greeting();
        greeting.setName("   ");

        assertNotNull("Name should not be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    public void testNameIsSet() {
        Greeting greeting = new Greeting();
        greeting.setName("Foo");

        assertNotNull("Name should not be null", greeting.getName());
        assertTrue("Name should be set", greeting.isNameSet());
        assertEquals("Name should match", "Foo", greeting.getName());
    }
}
