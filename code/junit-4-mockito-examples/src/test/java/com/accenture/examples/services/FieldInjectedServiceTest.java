package com.accenture.examples.services;

import com.accenture.examples.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

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
        stringUtil = mock(StringUtil.class);
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
        // Execute
        service.createCamelCaseFromSentence(null);

        // Verify
        verifyZeroInteractions(stringUtil);
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
        when(stringUtil.split(anyString(), anyString())).thenReturn(null);

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertNull(camelCase);
        verify(stringUtil).split(anyString(), anyString());
        verifyNoMoreInteractions(stringUtil);
    }

    @Test
    public void serviceShouldReturnNullWhenStringUtilSplitReturnsEmptyArray() {
        // Prepare
        when(stringUtil.split(anyString(), anyString())).thenReturn(new String[]{});
        when(stringUtil.join(any(String[].class), anyString())).thenReturn(null);

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertNull(camelCase);
        verify(stringUtil).split(anyString(), anyString());
        verify(stringUtil).join(any(String[].class), anyString());
        verifyNoMoreInteractions(stringUtil);
    }

    @Test
    public void serviceShouldReturnCamelCaseWhenStringUtilSplitReturnsArray() {
        // Prepare
        when(stringUtil.split(anyString(), anyString())).thenReturn(new String[]{"the", "test", "string"});
        when(stringUtil.capitalize("the")).thenReturn("The");
        when(stringUtil.capitalize("test")).thenReturn("Test");
        when(stringUtil.capitalize("string")).thenReturn("String");
        when(stringUtil.join(any(String[].class), anyString())).thenReturn("TheTestString");

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertEquals("TheTestString", camelCase);
        verify(stringUtil).split(anyString(), anyString());
        verify(stringUtil, times(3)).capitalize(anyString());
        verify(stringUtil).join(any(String[].class), anyString());
        verifyNoMoreInteractions(stringUtil);
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringUtilCapitalizeThrowsException() {
        // Prepare
        when(stringUtil.split(anyString(), anyString())).thenReturn(new String[]{"the", "test", "string"});
        when(stringUtil.capitalize("the")).thenReturn("The");
        when(stringUtil.capitalize("test")).thenThrow(new IllegalArgumentException("a sensible message"));

        // Execute
        service.createCamelCaseFromSentence("the test string");

        // Verify
        verify(stringUtil).split(anyString(), anyString());
        verify(stringUtil, times(2)).capitalize(anyString());
        verify(stringUtil).join(any(String[].class), anyString());
        verifyNoMoreInteractions(stringUtil);
    }
}
