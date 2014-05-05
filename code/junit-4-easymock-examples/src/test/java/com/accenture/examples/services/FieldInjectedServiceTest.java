package com.accenture.examples.services;

import com.accenture.examples.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test case for the {@link FieldInjectedService} class.
 * <p/>
 * This test case simulates a service where the dependency is injected by means of container mechanisms such as Java EE CDI or Spring DI.
 */
public class FieldInjectedServiceTest {

    private FieldInjectedService service;
    private StringUtil stringUtil;

    /**
     * Setting up test subject and mocks.
     * <p/>
     * Here the mock is set directly into the field using reflection.
     */
    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        stringUtil = createNiceMock(StringUtil.class);
        service = new FieldInjectedService();
        Field field = service.getClass().getDeclaredField("stringUtil");
        field.setAccessible(Boolean.TRUE);
        field.set(service, stringUtil);
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
