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
public class BitStorageTest {

    private BitStorage store;

    @Before
    public void setUp() {
        store = new BitStorage();
    }

    @Test
    public void flushToStringReturnsEmptyStringWhenStorageEmpty() {
        assertEquals("", store.flushToString());
    }

    @Test
    public void flushToStringReturnsBitsInterpretedAsStringWhenStorageContainsElements() {
        String expected = "abc";
        for (char c : expected.toCharArray()) {
            store.writeBack(c, Byte.SIZE);
        }
        assertEquals(expected, store.flushToString());
    }

    @Test
    public void getBitCountReturnsAmountOfBitsInStore() {
        assertEquals(0, store.getBitCount());
        store.writeBack(0, 3);
        assertEquals(3, store.getBitCount());
        store.writeBack(0, 2);
        assertEquals(5, store.getBitCount());
        store.readFront(4);
        assertEquals(1, store.getBitCount());
        store.readFront(5);
        assertEquals(0, store.getBitCount());
    }

    @Test
    public void hasBitsToReadReturnsFalseWhenEmptyAndExpectingBits() {
        assertFalse(store.hasBitsToRead(1));
    }

    @Test
    public void hasBitsToReadReturnsTrueWhenEmptyAndNotExpectingBits() {
        assertTrue(store.hasBitsToRead(0));
    }

    @Test
    public void hasBitsToReadReturnsTrueWhenEmptyAndExpectingNegativeBits() {
        assertTrue(store.hasBitsToRead(-5));
    }

    @Test
    public void hasBitsToReadReturnsTrueWhenExpectingExactlyContainedAmountOfBits() {
        store.writeBack(0, 5);
        assertTrue(store.hasBitsToRead(5));
    }

    @Test
    public void hasBitsToReadReturnsTrueWhenExpectingLessThanContainedAmountOfBits() {
        store.writeBack(0, 7);
        assertTrue(store.hasBitsToRead(5));
    }

    @Test
    public void hasBitsToReadReturnsFalseWhenExpectingMoreThanContainedAmountOfBits() {
        store.writeBack(0, 3);
        assertFalse(store.hasBitsToRead(5));
    }

    @Test
    public void readFrontReadsZerosWhenStoreEmpty() {
        assertEquals(0, store.readFront(10));
    }

    @Test
    public void readFrontReadsStoredBits() {
        store.writeBack(0, 2);
        store.writeBack(1, 1);
        assertEquals(1, store.readFront(3));
    }

    @Test
    public void toStringReturnsEmptyStringWhenStoreEmpty() {
        assertEquals("", store.toString());
    }

    @Test
    public void toStringReturnsTheStoredBitsAsStringOfNumbers() {
        store.writeBack(1, 1);
        store.writeBack(1, 1);
        store.writeBack(0, 1);
        store.writeBack(1, 1);
        assertEquals("1101", store.toString());
    }

    @Test
    public void writeBackWritesToTheBackOfTheStore() {
        store.writeBack(1, 1);
        store.writeBack(0, 1);
        assertEquals("10", store.toString());
    }

    @Test
    public void writeBackWritesNothingWhenGivenZeroBitsToWrite() {
        store.writeBack(1, 0);
        assertEquals("", store.toString());
    }

    @Test
    public void writeBackWritesNothingWhenGivenNegativeBitsToWrite() {
        store.writeBack(1, -1);
        assertEquals("", store.toString());
    }
}
