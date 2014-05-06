package com.accenture.acntech.examples.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Regular JUnit test case for the {@link StringUtil} class.
 */
public class StringUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void splittingStringWhichIsNullShouldThrowException() {
        StringUtil.split(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void splittingWithRegexThatIsNullShouldThrowException() {
        StringUtil.split("the test string", null);
    }

    @Test
    public void splittingWithWhitespaceReturnsLengthThreeArrray() {
        String[] stringArray = StringUtil.split("the test string", " ");

        assertNotNull(stringArray);
        assertEquals(3, stringArray.length);
        assertEquals("the", stringArray[0]);
        assertEquals("test", stringArray[1]);
        assertEquals("string", stringArray[2]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void joiningStringWhichIsNullShouldThrowException() {
        StringUtil.join(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void joiningWithGlueThatIsNullShouldThrowException() {
        StringUtil.join(new String[]{}, null);
    }

    @Test
    public void joiningEmptyArrayShouldReturnNull() {
        String string = StringUtil.join(new String[]{}, "|");

        assertNull(string);
    }

    @Test
    public void joiningArrayWithOneElementShouldReturnElement() {
        String string = StringUtil.join(new String[]{"test"}, ":");

        assertEquals("test", string);
    }

    @Test
    public void joiningThreeElementArray() {
        String string = StringUtil.join(new String[]{"the", "test", "string"}, ":");

        assertEquals("the:test:string", string);
    }

    @Test(expected = IllegalArgumentException.class)
    public void capitalizingStringwhichIsNullShouldThrowException() {
        StringUtil.capitalize(null);
    }

    @Test
    public void capitalizingEmptyStringShouldReturnString() {
        String string = StringUtil.capitalize(" ");

        assertEquals(" ", string);
    }

    @Test
    public void capitalizingStringWithMixedCharacters() {
        String string = StringUtil.capitalize("abCdEFGh");

        assertEquals("Abcdefgh", string);
    }

    @Test
    public void capitalizingStringWithSentence() {
        String string = StringUtil.capitalize(" tHE teSt STring");

        assertEquals("The test string", string);
    }
}
