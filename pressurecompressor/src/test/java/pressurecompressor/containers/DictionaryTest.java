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
    private ByteSequence bs1;
    private ByteSequence bs2;
    private ByteSequence bs3;

    @Before
    public void setUp() {
        this.dict = new Dictionary(3);
        this.bs1 = new ByteSequence(new byte[]{1});
        this.bs2 = new ByteSequence(new byte[]{2});
        this.bs3 = new ByteSequence(new byte[]{3});
    }

    @Test
    public void isFullReturnsFalseWhenDictHasSpace() {
        assertFalse(dict.isFull());
        dict.add(bs1);
        assertFalse(dict.isFull());
        dict.add(bs2);
        assertFalse(dict.isFull());
    }

    @Test
    public void isFullReturnsTrueWhenDictIsFull() {
        dict.add(bs1);
        dict.add(bs2);
        dict.add(bs3);
        assertTrue(dict.isFull());
    }

    @Test
    public void isFullReturnsTrueInstantlyWithZeroSizeDict() {
        assertTrue(new Dictionary(0).isFull());
    }

    @Test
    public void getWithNonExistentStringReturnsNull() {
        assertNull(dict.get(bs1));
    }

    @Test
    public void getWithExistingStringReturnsCorrespondingIndex() {
        ByteSequence[] input = {bs1, bs2, bs3};
        for (ByteSequence bs : input) {
            dict.add(bs);
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
        dict.add(bs1);
        assertNull(dict.get(1));
    }

    @Test
    public void getWithExactElementCountReturnsElement() {
        ByteSequence input = bs1;
        dict.add(input);
        assertEquals(input, dict.get(0));
    }

    @Test
    public void getWithNegativeElementCountReturnsNull() {
        dict.add(bs1);
        assertNull(dict.get(-1));
    }

    @Test
    public void getWithJustAboveExactElementCountReturnsNullWhenTwoElementsInDict() {
        dict.add(bs1);
        dict.add(bs2);
        assertNull(dict.get(2));
    }

    @Test
    public void getWithValidIndexReturnsElementWhenTwoElementsInDict() {
        dict.add(bs1);
        dict.add(bs2);
        assertEquals(bs1, dict.get(0));
        assertEquals(bs2, dict.get(1));
    }

    @Test
    public void getWithNegativeElementCountReturnsNullWhenTwoElementsInList() {
        dict.add(bs1);
        dict.add(bs2);
        assertNull(dict.get(-1));
    }

    @Test
    public void getWithExistingIndexReturnsCorrespondingString() {
        ByteSequence[] input = {bs1, bs2, bs3};
        for (ByteSequence bs : input) {
            dict.add(bs);
        }
        for (int i = 0; i < input.length; ++i) {
            assertEquals(input[i], dict.get(i));
        }
    }

    @Test
    public void addDoesNotAddWhenFull() {
        dict = new Dictionary(0);
        dict.add(bs1);
        assertTrue(dict.isFull());
        assertNull(dict.get(bs1));
    }

    @Test
    public void addDoesNotAddNull() {
        dict = new Dictionary(1);
        dict.add(null);
        assertFalse(dict.isFull());
        assertNull(dict.get(null));
    }

}
