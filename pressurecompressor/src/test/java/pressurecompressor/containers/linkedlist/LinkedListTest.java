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

    private LinkedList<Object> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
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
        Object expected = new Object();
        list.pushBack(expected);
        assertEquals(expected, list.popBack());
    }

    @Test
    public void peekFrontReturnsNullWhenListEmpty() {
        assertNull(list.peekFront());
    }

    @Test
    public void peekFrontReturnsFrontElementWhenListHasMultipleElements() {
        Object expected1 = new Object();
        Object expected2 = new Object();
        list.pushBack(expected1);
        list.pushBack(expected2);
        assertEquals(expected1, list.peekFront().data);
    }

    @Test
    public void popBackReturnsNullWhenListEmpty() {
        assertNull(list.popBack());
    }

    @Test
    public void popBackReturnsTheBackNodeAndReducesTheSizeOfTheList() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        list.pushBack(obj1);
        list.pushBack(obj2);
        assertEquals(2, list.length());
        assertEquals(obj2, list.popBack());
        assertEquals(1, list.length());
    }

    @Test
    public void popFrontReturnsNullWhenListEmpty() {
        assertNull(list.popFront());
    }

    @Test
    public void popFrontReturnsTheBackNodeAndReducesTheSizeOfTheList() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        list.pushBack(obj1);
        list.pushBack(obj2);
        assertEquals(2, list.length());
        assertEquals(obj1, list.popFront());
        assertEquals(1, list.length());
    }

    @Test
    public void pushBackAddsTheGivenElementToTheBackOfTheListAndIncreasesListLength() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        list.pushBack(obj1);
        assertEquals(1, list.length());
        list.pushBack(obj2);
        assertEquals(2, list.length());
        assertNotNull(list.peekFront());
        assertEquals(obj1, list.peekFront().data);
        assertEquals(obj2, list.popBack());
    }
}
