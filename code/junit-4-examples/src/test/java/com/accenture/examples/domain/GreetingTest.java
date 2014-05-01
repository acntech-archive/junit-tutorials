package com.accenture.examples.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test case for the {@link Greeting} class.
 * <p/>
 * <ul>
 * <li>No longer needs to extend {@link junit.framework.TestCase}</li>
 * <li>Should be named the same as the class under test, with "Test" appended at the end</li>
 * <li>Should have the same package name as the class under test</li>
 * </ul>
 */
public class GreetingTest {

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
    public void nameIsNull() {
        Greeting greeting = new Greeting();

        assertNull("Name should be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    @Test
    public void nameIsEmptyString() {
        Greeting greeting = new Greeting();
        greeting.setName("");

        assertNotNull("Name should not be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    @Test
    public void nameIsWhitespaceString() {
        Greeting greeting = new Greeting();
        greeting.setName("   ");

        assertNotNull("Name should not be null", greeting.getName());
        assertFalse("Name should not be set", greeting.isNameSet());
    }

    @Test
    public void nameIsSet() {
        Greeting greeting = new Greeting();
        greeting.setName("Foo");

        assertNotNull("Name should not be null", greeting.getName());
        assertTrue("Name should be set", greeting.isNameSet());
        assertEquals("Name should match", "Foo", greeting.getName());
    }
}
