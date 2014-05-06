package com.accenture.acntech.examples.services;

import com.accenture.acntech.examples.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.powermock.api.easymock.PowerMock.*;

/**
 * Test case for the {@link PowerMockWithEasyMockService} class.
 * <p/>
 * The {@link StringUtil} dependency is now a static utility class, and can not be mocked using EasyMock.
 * <p/>
 * Instead we use PowerMock, which is able to mock final classes, static methods, private methods and more.
 * <ul>
 * <li>Make JUnit run with {@link PowerMockRunner} using the {@link RunWith} annotation</li>
 * <li>Using the {@link PrepareForTest} annotation to tell PowerMock which classes it should prepare for mocking</li>
 * </ul>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StringUtil.class})
public class PowerMockWithEasyMockServiceTest {

    private PowerMockWithEasyMockService service;

    /**
     * Setting up test subject and mocks.
     * <p/>
     * Using PowerMock's bridge with EasyMock we use {@link org.powermock.api.easymock.PowerMock#mockStatic} to mock all static methods in {@link StringUtil}.
     */
    @Before
    public void setUp() {
        service = new PowerMockWithEasyMockService();
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
     * <li>The PowerMock/EasyMock bridge means regular EasyMock set-up methods are used to prepare the mocks</li>
     * <li></li>Setting up any mocks before invoking methods on the test subject</li>
     * <li>The {@link org.powermock.api.easymock.PowerMock#replayAll} method must be used to initiate the mocks</li>
     * <li>Using regular JUnit asserts on the return value from the test subject</li>
     * <li>Finally verify that there has been the expected interactions with the mock</li>
     * <li>The {@link org.powermock.api.easymock.PowerMock#verifyAll} method must be used to verify the mocks</li>
     * </ul>
     */
    @Test
    public void serviceShouldReturnNullWhenStringUtilSplitReturnsNull() {
        // Prepare
        expect(StringUtil.split(anyString(), anyString())).andReturn(null);
        replayAll();

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertNull(camelCase);
        verifyAll();
    }

    @Test
    public void serviceShouldReturnNullWhenStringUtilSplitReturnsEmptyArray() {
        // Prepare
        expect(StringUtil.split(anyString(), anyString())).andReturn(new String[]{});
        expect(StringUtil.join(isA(String[].class), anyString())).andReturn(null);
        replayAll();

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertNull(camelCase);
        verifyAll();
    }

    @Test
    public void serviceShouldReturnCamelCaseWhenStringUtilSplitReturnsArray() {
        // Prepare
        expect(StringUtil.split(anyString(), anyString())).andReturn(new String[]{"the", "test", "string"});
        expect(StringUtil.capitalize("the")).andReturn("The");
        expect(StringUtil.capitalize("test")).andReturn("Test");
        expect(StringUtil.capitalize("string")).andReturn("String");
        expect(StringUtil.join(isA(String[].class), anyString())).andReturn("TheTestString");
        replayAll();

        // Execute
        String camelCase = service.createCamelCaseFromSentence("the test string");

        // Verify
        assertEquals("TheTestString", camelCase);
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringUtilCapitalizeThrowsException() {
        // Prepare
        expect(StringUtil.split(anyString(), anyString())).andReturn(new String[]{"the", "test", "string"});
        expect(StringUtil.capitalize("the")).andReturn("The");
        expect(StringUtil.capitalize("test")).andThrow(new IllegalArgumentException("a sensible message"));
        replayAll();

        // Execute
        service.createCamelCaseFromSentence("the test string");

        // Verify
        verifyAll();
    }
}
