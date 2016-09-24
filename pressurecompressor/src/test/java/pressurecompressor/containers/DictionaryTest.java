/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rimi
 */
public class DictionaryTest {

    private Dictionary dict;

    @Before
    public void setUp() {
        dict = new Dictionary(3);
    }

    @Test
    public void isFullReturnsFalseWhenDictHasSpace() {
        assertFalse(dict.isFull());
        dict.add("1");
        assertFalse(dict.isFull());
        dict.add("2");
        assertFalse(dict.isFull());
    }

    @Test
    public void isFullReturnsTrueWhenDictIsFull() {
        dict.add("1");
        dict.add("2");
        dict.add("3");
        assertTrue(dict.isFull());
    }

    @Test
    public void isFullReturnsTrueInstantlyWithZeroSizeDict() {
        assertTrue(new Dictionary(0).isFull());
    }

    @Test
    public void getWithNonExistentStringReturnsNull() {
        assertNull(dict.get("nonexistent"));
    }

    @Test
    public void getWithExistingStringReturnsCorrespondingIndex() {
        String[] input = {"a", "b", "c"};
        for (String string : input) {
            dict.add(string);
        }
        for (int i = 0; i < input.length; ++i) {
            assertEquals(i, dict.get(input[i]).intValue());
        }
    }

    @Test
    public void getWithNonExistentIntReturnsNull() {
        assertNull(dict.get(0));
    }

    @Test
    public void getWithNegativeIntReturnsNull() {
        assertNull(dict.get(-1));
    }

    @Test
    public void getWithOutOfUpperBoundIntReturnsNull() {
        assertNull(dict.get(1));
    }

    @Test
    public void getWithJustAboveExactElementCountReturnsNull() {
        dict.add("test");
        assertNull(dict.get(1));
    }

    @Test
    public void getWithExactElementCountReturnsElement() {
        String input = "test";
        dict.add(input);
        assertEquals(input, dict.get(0));
    }

    @Test
    public void getWithNegativeElementCountReturnsNull() {
        dict.add("test");
        assertNull(dict.get(-1));
    }

    @Test
    public void getWithJustAboveExactElementCountReturnsNullWhenTwoElementsInDict() {
        dict.add("abc");
        dict.add("def");
        assertNull(dict.get(2));
    }

    @Test
    public void getWithValidIndexReturnsElementWhenTwoElementsInDict() {
        dict.add("abc");
        dict.add("def");
        assertEquals("abc", dict.get(0));
        assertEquals("def", dict.get(1));
    }

    @Test
    public void getWithNegativeElementCountReturnsNullWhenTwoElementsInList() {
        dict.add("abc");
        dict.add("def");
        assertNull(dict.get(-1));
    }

    @Test
    public void getWithExistingIndexReturnsCorrespondingString() {
        String[] input = {"a", "b", "c"};
        for (String string : input) {
            dict.add(string);
        }
        for (int i = 0; i < input.length; ++i) {
            assertEquals(input[i], dict.get(i));
        }
    }

    @Test
    public void addDoesNotAddWhenFull() {
        dict = new Dictionary(0);
        dict.add("test");
        assertTrue(dict.isFull());
        assertNull(dict.get("test"));
    }

    @Test
    public void addDoesNotAddNull() {
        dict = new Dictionary(1);
        dict.add(null);
        assertFalse(dict.isFull());
        assertNull(dict.get(null));
    }

    @Test
    public void toStringReturnsEmptyStringWithEmptyDictionary() {
        assertEquals("", dict.toString());
    }

    @Test
    public void toStringReturnsExpectedStringWithMultipleElementsInDictionary() {
        String[] inputs = {
            "abc",
            "def"
        };
        String expected = "";
        for (int i = 0; i < inputs.length; ++i) {
            expected += "[";
            expected += i;
            expected += "]";
            expected += "=";
            expected += inputs[i];
            expected += " ";
        }
        for (String string : inputs) {
            dict.add(string);
        }
        assertEquals(expected, dict.toString());
    }

}
