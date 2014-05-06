package com.accenture.acntech.examples.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Test case for the {@link PrivateMethodUtil} class.
 * <p/>
 * <ul>
 * <li>Make JUnit run with {@link PowerMockRunner} using the {@link RunWith} annotation</li>
 * <li>Using the {@link PrepareForTest} annotation to tell PowerMock which classes it should prepare for mocking</li>
 * </ul>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PrivateMethodUtil.class})
public class PrivateMethodUtilTest {

    /**
     * Using Spy to mock out private method call.
     * <p/>
     * This should be avoided unless absolutely necessary, since it breaks the encapsulation of logic.
     *
     * @throws Exception
     */
    @Test
    public void mockingOutPrivateMethodCall() throws Exception {
        PrivateMethodUtil util = spy(new PrivateMethodUtil());
        when(util, method(PrivateMethodUtil.class, "splitWithStringUtil", String.class, String.class)).withArguments(anyString(), anyString()).thenReturn(new String[]{"the", "test", "array"});

        String[] parts = util.commaSeparated("any string");

        assertEquals(3, parts.length);
        assertEquals("the", parts[0]);
        assertEquals("test", parts[1]);
        assertEquals("array", parts[2]);
        verifyPrivate(util).invoke(method(PrivateMethodUtil.class, "splitWithStringUtil", String.class, String.class));
    }
}
