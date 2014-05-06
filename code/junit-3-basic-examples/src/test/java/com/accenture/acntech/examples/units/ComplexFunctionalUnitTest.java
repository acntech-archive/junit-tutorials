package com.accenture.acntech.examples.units;

import com.accenture.acntech.examples.domain.InternationalGreeting;
import junit.framework.TestCase;

/**
 * Test case for the {@link ComplexFunctionalUnit} class.
 * <p/>
 * <ul>
 * <li>Must extend {@link TestCase}</li>
 * <li>Should be named the same as the class under test, with "Test" appended at the end</li>
 * <li>Should have the same package name as the class under test</li>
 * </ul>
 */
public class ComplexFunctionalUnitTest extends TestCase {

    private ComplexFunctionalUnit cfu;

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
        cfu = new ComplexFunctionalUnit();
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
        cfu = null;
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
            cfu.getGreeting(null);

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue("Wrong exception type", e instanceof IllegalArgumentException);
            assertEquals("Wrong exception message", "Greeting is null", e.getMessage());
        }
    }

    public void testLanguageIsNullShouldThrowException() {
        try {
            cfu.getGreeting(new InternationalGreeting());

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue("Wrong exception type", e instanceof IllegalArgumentException);
            assertEquals("Wrong exception message", "Language is not set", e.getMessage());
        }
    }

    public void testLanguageIsInvalidShouldThrowException() {
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

    public void testNameIsNullShouldThrowException() {
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

    public void testEnglishGreetingIsSet() {
        InternationalGreeting g = new InternationalGreeting();
        g.setLanguage("en");
        g.setName("Foo");
        String greeting = cfu.getGreeting(g);

        assertNotNull("Greeting should not be null", greeting);
        assertEquals("Greeting should be equal", "Hello Foo!", greeting);
    }

    public void testNorwegianGreetingIsSet() {
        InternationalGreeting g = new InternationalGreeting();
        g.setLanguage("no");
        g.setName("Foo");
        String greeting = cfu.getGreeting(g);

        assertNotNull("Greeting should not be null", greeting);
        assertEquals("Greeting should be equal", "Hei Foo!", greeting);
    }

    public void testHindiGreetingIsSet() {
        InternationalGreeting g = new InternationalGreeting();
        g.setLanguage("hi");
        g.setName("Foo");
        String greeting = cfu.getGreeting(g);

        assertNotNull("Greeting should not be null", greeting);
        assertEquals("Greeting should be equal", "Namaste Foo!", greeting);
    }
}
