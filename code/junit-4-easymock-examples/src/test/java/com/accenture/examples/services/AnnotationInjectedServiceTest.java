package com.accenture.examples.services;

import com.accenture.examples.utils.StringUtil;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test case for the {@link AnnotationInjectedService} class.
 * <p/>
 * This test case simulates a service where the dependency is injected by means of container mechanisms such as Java EE CDI or Spring DI.
 * <p/>
 * <ul>
 * <li>Annotation based mocking is available using EasyMock version >= 3.2</li>
 * <li>Make JUnit run with {@link org.easymock.EasyMockRunner} using the {@link org.junit.runner.RunWith} annotation</li>
 * <li>No set-up method is used here as the {@link org.easymock.EasyMockRunner} will prepare test subject and mocks</li>
 * </ul>
 */
@RunWith(EasyMockRunner.class)
public class AnnotationInjectedServiceTest {

    /**
     * The {@link org.easymock.TestSubject} annotation identifies the class under test.
     */
    @TestSubject
    private AnnotationInjectedService service = new AnnotationInjectedService();

    /**
     * The {@link org.easymock.Mock} annotation is used to mark classes that should be mocked by EasyMock.
     */
    @Mock
    private StringUtil stringUtil;

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
