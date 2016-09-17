/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers.linkedlist;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rimi
 */
public class NodeTest {

    @Test
    public void constructorSetsGivenDataToNode() {
        assertTrue(new Node(true).data);
        assertFalse(new Node(false).data);
    }

    @Test
    public void constructorSetsNullNextAndPreviousToNodeWhenNoneGiven() {
        assertNull(new Node(true).next);
        assertNull(new Node(true).previous);
    }

    @Test
    public void constructorSetsGivenDataAndPreviousToNodeWhenNodeGiven() {
        Node previous = new Node(true);
        Node node = new Node(false, previous);

        assertFalse(node.data);
        assertNull(node.next);
        assertEquals(previous, node.previous);
    }

    @Test
    public void constructorSetsGivenDataAndPreviousToNodeWhenNullGiven() {
        Node node = new Node(false, null);

        assertFalse(node.data);
        assertNull(node.next);
        assertNull(node.previous);
    }
}
