package com.accenture.examples.services;

import com.accenture.examples.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Test case for the {@link AnnotationInjectedService} class.
 * <p/>
 * This test case simulates a service where the dependency is injected by means of container mechanisms such as Java EE CDI or Spring DI.
 */
public class AnnotationInjectedServiceTest {

    /**
     * The {@link InjectMocks} annotation identifies the class under test.
     */
    @InjectMocks
    private AnnotationInjectedService service;

    /**
     * The {@link Mock} annotation is used to mark classes that should be mocked by Mockito.
     */
    @Mock
    private StringUtil stringUtil;

    /**
     * Setting up test subject and mocks.
     * <p/>
     * The call to {@link org.mockito.MockitoAnnotations#initMocks} invokes Mockito's mocking mechanism.
     */
    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        initMocks(this);
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
