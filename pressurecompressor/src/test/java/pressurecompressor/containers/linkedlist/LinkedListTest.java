/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers.linkedlist;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rimi
 */
public class LinkedListTest {

    private LinkedList list;

    @Before
    public void setUp() {
        list = new LinkedList();
    }

    @Test
    public void lengthReturnsZeroAfterConstruction() {
        assertEquals(0, list.length());
    }

    @Test
    public void lengthReturnsThreeWhenThreeElementsInList() {
        list.pushBack(true);
        list.pushBack(false);
        list.pushBack(true);
        assertEquals(3, list.length());
    }

    @Test
    public void lengthReturnsOneElementAfterAddinTwoAndRemovingOneUsingPopFront() {
        list.pushBack(true);
        list.pushBack(false);
        list.popFront();
        assertEquals(1, list.length());
    }

    @Test
    public void lengthReturnsOneElementAfterAddinTwoAndRemovingOneUsingPopBack() {
        list.pushBack(true);
        list.pushBack(false);
        list.popBack();
        assertEquals(1, list.length());
    }

    @Test
    public void lengthIsZeroAfterRemovingMoreElementsThanInListUsingPopFront() {
        list.popFront();
        assertEquals(0, list.length());
    }

    @Test
    public void lengthIsZeroAfterRemovingMoreElementsThanInListUsingPopBack() {
        list.popBack();
        assertEquals(0, list.length());
    }

    @Test
    public void lengthIsOneAfterRemovingMoreElementsThanInListAndAddingOneElementUsingPopBack() {
        list.popBack();
        list.pushBack(true);
        assertEquals(1, list.length());
    }

    @Test
    public void lengthIsOneAfterRemovingMoreElementsThanInListAndAddingOneElementUsingPopFront() {
        list.popFront();
        list.pushBack(true);
        assertEquals(1, list.length());
    }

    @Test
    public void popBackReturnsTheValueOfTheElementWhenPoppingLastElementFromList() {
        list.pushBack(true);
        assertTrue(list.popBack());
    }

    @Test
    public void peekFrontReturnsNullWhenListEmpty() {
        assertNull(list.peekFront());
    }

    @Test
    public void peekFrontReturnsFrontElementWhenListHasMultipleElements() {
        list.pushBack(true);
        list.pushBack(false);
        assertTrue(list.peekFront().data);
    }

    @Test
    public void popBackReturnsFalseWhenListEmpty() {
        assertFalse(list.popBack());
    }

    @Test
    public void popBackReturnsTheBackNodeAndReducesTheSizeOfTheList() {
        list.pushBack(true);
        list.pushBack(false);
        assertFalse(list.popBack());
        assertEquals(1, list.length());
    }

    @Test
    public void popFrontReturnsFalseWhenListEmpty() {
        assertFalse(list.popFront());
    }

    @Test
    public void popFrontReturnsTheBackNodeAndReducesTheSizeOfTheList() {
        list.pushBack(true);
        list.pushBack(false);
        assertTrue(list.popFront());
        assertEquals(1, list.length());
    }

    @Test
    public void pushBackAddsTheGivenElementToTheBackOfTheListAndIncreasesListLength() {
        list.pushBack(true);
        assertEquals(1, list.length());
        list.pushBack(false);
        assertEquals(2, list.length());
        assertNotNull(list.peekFront());
        assertTrue(list.peekFront().data);
        assertFalse(list.popBack());
    }
}
