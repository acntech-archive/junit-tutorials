package com.accenture.examples.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Regular JUnit test case for the {@link StringUtil} class.
 */
public class StringUtilTest {

    private StringUtil stringUtil;

    @Before
    public void setUp() {
        stringUtil = new StringUtil();
    }

    @Test(expected = IllegalArgumentException.class)
    public void splittingStringWhichIsNullShouldThrowException() {
        stringUtil.split(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void splittingWithRegexThatIsNullShouldThrowException() {
        stringUtil.split("the test string", null);
    }

    @Test
    public void splittingWithWhitespaceReturnsLengthThreeArrray() {
        String[] stringArray = stringUtil.split("the test string", " ");

        assertNotNull(stringArray);
        assertEquals(3, stringArray.length);
        assertEquals("the", stringArray[0]);
        assertEquals("test", stringArray[1]);
        assertEquals("string", stringArray[2]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void joiningStringWhichIsNullShouldThrowException() {
        stringUtil.join(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void joiningWithGlueThatIsNullShouldThrowException() {
        stringUtil.join(new String[]{}, null);
    }

    @Test
    public void joiningEmptyArrayShouldReturnNull() {
        String string = stringUtil.join(new String[]{}, "|");

        assertNull(string);
    }

    @Test
    public void joiningArrayWithOneElementShouldReturnElement() {
        String string = stringUtil.join(new String[]{"test"}, ":");

        assertEquals("test", string);
    }

    @Test
    public void joiningThreeElementArray() {
        String string = stringUtil.join(new String[]{"the", "test", "string"}, ":");

        assertEquals("the:test:string", string);
    }

    @Test(expected = IllegalArgumentException.class)
    public void capitalizingStringwhichIsNullShouldThrowException() {
        stringUtil.capitalize(null);
    }

    @Test
    public void capitalizingEmptyStringShouldReturnString() {
        String string = stringUtil.capitalize(" ");

        assertEquals(" ", string);
    }

    @Test
    public void capitalizingStringWithMixedCharacters() {
        String string = stringUtil.capitalize("abCdEFGh");

        assertEquals("Abcdefgh", string);
    }

    @Test
    public void capitalizingStringWithSentence() {
        String string = stringUtil.capitalize(" tHE teSt STring");

        assertEquals("The test string", string);
    }
}
