package com.accenture.examples.units;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test case for the {@link SimpleFunctionalUnit} class.
 * <p/>
 * <ul>
 * <li>No longer needs to extend {@link junit.framework.TestCase}</li>
 * <li>Should be named the same as the class under test, with "Test" appended at the end</li>
 * <li>Should have the same package name as the class under test</li>
 * </ul>
 */
public class SimpleFunctionalUnitTest {

    private static String FOO_NAME;

    private SimpleFunctionalUnit sfu;

    /**
     * Static test case set-up method.
     * <p/>
     * This method is invoked once before any tests are executed in this test case.
     * <p/>
     * <ul>
     * <li>Must be annotated with the {@link org.junit.BeforeClass} annotation</li>
     * <li>Must be a static method</li>
     * <li>Should contain static common scaffolding needed to run tests</li>
     * <li>Extensive scaffolding is a sign of too monolithic production code</li>
     * </ul>
     */
    @BeforeClass
    public static void oneTimeSetUp() {
        FOO_NAME = "Foo";
    }

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
        sfu = new SimpleFunctionalUnit();
    }

    /**
     * Test case tear-down method.
     * <p/>
     * This method is invoked after each of the test methods of this test case.
     * <p/>
     * <ul>
     * <li>Must be annotated with the {@link org.junit.After} annotation</li>
     * <li>Should remove any persistent scaffolding created for the tests</li>
     * </ul>
     */
    @After
    public void tearDown() {
        sfu = null;
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
    public void nameIsNull() {
        assertNull("Greeting should be null", sfu.getGreeting(null));
    }

    @Test
    public void SHOWING_THAT_TEST_METHOD_NAMES_CAN_BE_ANYTHING() {
        assertNull("Greeting should be null", sfu.getGreeting(null));
    }

    @Test
    public void nameIsEmptyString() {
        assertNull("Greeting should be null", sfu.getGreeting(""));
    }

    @Test
    public void nameIsWhitespaceString() {
        assertNull("Greeting should be null", sfu.getGreeting("   "));
    }

    @Test
    public void nameIsSet() {
        String greeting = sfu.getGreeting(FOO_NAME);

        assertNotNull("Greeting should not be null", greeting);
        assertEquals("Greeting should be equal", "Hello Foo!", greeting);
    }
}
