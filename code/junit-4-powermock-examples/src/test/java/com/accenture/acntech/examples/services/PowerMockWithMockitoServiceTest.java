package com.accenture.acntech.examples.services;

import com.accenture.acntech.examples.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * Test case for the {@link PowerMockWithMockitoService} class.
 * <p/>
 * The {@link StringUtil} dependency is now a static utility class, and can not be mocked using Mockito.
 * <p/>
 * Instead we use PowerMock, which is able to mock final classes, static methods, private methods and more.
 * <ul>
 * <li>Make JUnit run with {@link PowerMockRunner} using the {@link RunWith} annotation</li>
 * <li>Using the {@link PrepareForTest} annotation to tell PowerMock which classes it should prepare for mocking</li>
 * </ul>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StringUtil.class})
public class PowerMockWithMockitoServiceTest {

    private PowerMockWithMockitoService service;

    /**
     * Setting up test subject and mocks.
     * <p/>
     * Using PowerMock's bridge with Mockito we use {@link org.powermock.api.mockito.PowerMockito#mockStatic} to mock all static methods in {@link StringUtil}.
     */
    @Before
    public void setUp() {
        service = new PowerMockWithMockitoService();
        mockStatic(StringUtil.class);
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
     * <li>The PowerMock/Mockito bridge means regular Mockito set-up methods are used to prepare the mocks</li>
     * <li></li>Setting up any mocks before invoking methods on the test subject</li>
     * <li>Using regular JUnit asserts on the return value from the test subject</li>
     * <li>Finally verify that there has been the expected interactions with the mock</li>
     * <li>The {@link org.powermock.api.mockito.PowerMockito#verifyStatic()} method must be used to verify the mocks</li>
     * </ul>
     */
    @Test
    public void serviceShouldReturnNullWhenStringUtilSplitReturnsNull() {
        // Prepare
        when(StringUtil.split(anyString(), anyString())).thenReturn(null);

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertNull(camelCase);
        verifyStatic();
    }

    @Test
    public void serviceShouldReturnNullWhenStringUtilSplitReturnsEmptyArray() {
        // Prepare
        when(StringUtil.split(anyString(), anyString())).thenReturn(new String[]{});
        when(StringUtil.join(any(String[].class), anyString())).thenReturn(null);

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertNull(camelCase);
        verifyStatic();
    }

    @Test
    public void serviceShouldReturnCamelCaseWhenStringUtilSplitReturnsArray() {
        // Prepare
        when(StringUtil.split(anyString(), anyString())).thenReturn(new String[]{"the", "test", "string"});
        when(StringUtil.capitalize("the")).thenReturn("The");
        when(StringUtil.capitalize("test")).thenReturn("Test");
        when(StringUtil.capitalize("string")).thenReturn("String");
        when(StringUtil.join(any(String[].class), anyString())).thenReturn("TheTestString");

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertEquals("TheTestString", camelCase);
        verifyStatic(times(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringUtilCapitalizeThrowsException() {
        // Prepare
        when(StringUtil.split(anyString(), anyString())).thenReturn(new String[]{"the", "test", "string"});
        when(StringUtil.capitalize("the")).thenReturn("The");
        when(StringUtil.capitalize("test")).thenThrow(new IllegalArgumentException("a sensible message"));

        // Execute
        service.createCamelCaseFromSentence("the test string");

        // Verify
        verifyStatic(times(3));
    }
}
