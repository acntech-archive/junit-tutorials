package com.accenture.examples.services;

import com.accenture.examples.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test case for the {@link ConstructorInjectedService} class.
 * <p/>
 * <ul>
 * <li>Dependencies such as {@link com.accenture.examples.utils.StringUtil} must be stubbed/mocked out in order to isolate the test subject</li>
 * <li>In order to inject mocks it forces the developer to think about dependency injection</li>
 * </ul>
 */
public class ConstructorInjectedServiceTest {

    private ConstructorInjectedService service;
    private StringUtil stringUtil;

    /**
     * Setting up test subject and mocks.
     * <p/>
     * Here the mock is passed as a constructor argument to the test subject.
     */
    @Before
    public void setUp() {
        stringUtil = createNiceMock(StringUtil.class);
        service = new ConstructorInjectedService(stringUtil);
    }

    /**
     * Testing any trivial cases first, before using mocking.
     */
    @Test(expected = IllegalArgumentException.class)
    public void callingServiceWithNullStringShouldThrowException() {
        service.createCamelCaseFromSentence(null);
    }

    /**
     * Testing using mocks.
     * <p/>
     * <ul>
     * <li>Setting up any mocks before invoking methods on the test subject</li>
     * <li>Using regular JUnit asserts on the return value from the test subject</li>
     * <li>Finally verify that there has been the expected interactions with the mock</li>
     * </ul>
     */
    @Test
    public void serviceShouldReturnNullWhenStringUtilSplitReturnsNull() {
        // Prepare
        expect(stringUtil.split(anyString(), anyString())).andReturn(null);
        replay(stringUtil);

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertNull(camelCase);
        verify(stringUtil);
    }

    @Test
    public void serviceShouldReturnNullWhenStringUtilSplitReturnsEmptyArray() {
        // Prepare
        expect(stringUtil.split(anyString(), anyString())).andReturn(new String[]{});
        expect(stringUtil.join(isA(String[].class), anyString())).andReturn(null);
        replay(stringUtil);

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertNull(camelCase);
        verify(stringUtil);
    }

    @Test
    public void serviceShouldReturnCamelCaseWhenStringUtilSplitReturnsArray() {
        // Prepare
        expect(stringUtil.split(anyString(), anyString())).andReturn(new String[]{"the", "test", "string"});
        expect(stringUtil.capitalize("the")).andReturn("The");
        expect(stringUtil.capitalize("test")).andReturn("Test");
        expect(stringUtil.capitalize("string")).andReturn("String");
        expect(stringUtil.join(isA(String[].class), anyString())).andReturn("TheTestString");
        replay(stringUtil);

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertEquals("TheTestString", camelCase);
        verify(stringUtil);
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringUtilCapitalizeThrowsException() {
        // Prepare
        expect(stringUtil.split(anyString(), anyString())).andReturn(new String[]{"the", "test", "string"});
        expect(stringUtil.capitalize("the")).andReturn("The");
        expect(stringUtil.capitalize("test")).andThrow(new IllegalArgumentException("a sensible message"));
        replay(stringUtil);

        // Execute
        service.createCamelCaseFromSentence("the test string");

        // Verify
        verify(stringUtil);
    }
}
