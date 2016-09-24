/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rimi
 */
public class StringUtilityTest {

    private StringUtility util;

    @Before
    public void setUp() {
        util = new StringUtility();
    }

    @Test
    public void bytesToStringReturnsEmptyStringWhenEmptyByteArrayGiven() {
        byte[] input = {};
        String output = StringUtility.bytesToString(input);
        assertEquals("", output);
    }

    @Test
    public void bytesToStringReturnsOutputEquallyLongAsInput() {
        byte[] input = {0, 50, 100};
        String output = StringUtility.bytesToString(input);
        assertEquals(input.length, output.length());
    }

    @Test
    public void stringToBytesReturnsEmptyArrayWhenEmptyInputGiven() {
        String input = "";
        byte[] output = StringUtility.stringToBytes(input);
        assertArrayEquals(new byte[0], output);
    }

    @Test
    public void stringToBytesReturnsOutputEquallyLongAsInput() {
        String input = "abc";
        byte[] output = StringUtility.stringToBytes(input);
        assertEquals(input.length(), output.length);
    }

    @Test
    public void hasNonAsciiCharactersReturnsFalseWhenNoAsciiCharactersInInput() {
        String input = "abc";
        boolean output = StringUtility.hasNonAsciiCharacters(input.getBytes());
        assertFalse(output);
    }

    @Test
    public void hasNonAsciiCharactersReturnsFalseWhenEmptyInput() {
        String input = "";
        boolean output = StringUtility.hasNonAsciiCharacters(input.getBytes());
        assertFalse(output);
    }

    @Test
    public void hasNonAsciiCharactersReturnsTrueWhenAsciiCharactersInInput() {
        String input = "åäö";
        boolean output = StringUtility.hasNonAsciiCharacters(input.getBytes());
        assertTrue(output);
    }

}
