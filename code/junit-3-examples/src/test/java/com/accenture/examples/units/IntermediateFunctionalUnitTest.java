package com.accenture.examples.units;

import com.accenture.examples.domain.Greeting;
import junit.framework.TestCase;

/**
 * Test case for the {@link IntermediateFunctionalUnit} class.
 * <p/>
 * <ul>
 * <li>Must extend {@link junit.framework.TestCase}</li>
 * <li>Should be named the same as the class under test, with "Test" appended at the end</li>
 * <li>Should have the same package name as the class under test</li>
 * </ul>
 */
public class IntermediateFunctionalUnitTest extends TestCase {

    private IntermediateFunctionalUnit ifu;

    /**
     * Test case set-up method.
     * <p/>
     * This method is invoked before each of the test methods of this test case.
     * <p/>
     * <ul>
     * <li>Should contain common scaffolding needed to run tests</li>
     * <li>Extensive scaffolding is a sign of too monolithic production code</li>
     * </ul>
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        ifu = new IntermediateFunctionalUnit();
    }

    /**
     * Test case tear-down method.
     * <p/>
     * This method is invoked after each of the test methods of this test case.
     * <p/>
     * <ul>
     * <li>Should remove any persistent scaffolding created for the tests</li>
     * </ul>
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        ifu = null;
    }

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
    public void testGreetingIsNullShouldThrowException() {
        try {
            ifu.getGreeting(null);

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue("Wrong exception type", e instanceof IllegalArgumentException);
            assertEquals("Wrong exception message", "Greeting is null", e.getMessage());
        }
    }

    public void testNameIsNullShouldThrowException() {
        try {
            ifu.getGreeting(new Greeting());

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue("Wrong exception type", e instanceof IllegalArgumentException);
            assertEquals("Wrong exception message", "Name is not set", e.getMessage());
        }
    }

    public void testGreetingIsSet() {
        Greeting g = new Greeting();
        g.setName("Foo");
        String greeting = ifu.getGreeting(g);

        assertNotNull("Greeting should not be null", greeting);
        assertEquals("Greeting should be equal", "Hello Foo!", greeting);
    }
}
