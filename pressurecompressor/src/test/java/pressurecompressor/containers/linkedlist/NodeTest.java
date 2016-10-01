/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers.linkedlist;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author rimi
 */
public class NodeTest {
    private Object object1;
    private Object object2;
    private Object object3;
    private Node<Object> nodeObject1;
    private Node<Object> nodeObject2;
    private Node<Object> nodeObject3;
    private Node<Object> nodeNull;

    @Before
    public void setUp() {
        this.object1 = new Object();
        this.object2 = new Object();
        this.nodeObject1 = new Node<>(this.object1);
        this.nodeObject2 = new Node<>(this.object2, nodeObject1);
        this.nodeObject3 = new Node<>(this.object3, null);
        this.nodeNull = new Node<>(null);
    }

    @Test
    public void constructorSetsGivenDataToNode() {
        assertEquals(object1, nodeObject1.data);
        assertEquals(object2, nodeObject2.data);
        assertNull(nodeNull.data);
    }

    @Test
    public void constructorSetsNullNextAndPreviousToNodeWhenNoneGiven() {
        assertNull(nodeObject1.next);
        assertNull(nodeObject1.previous);
    }

    @Test
    public void constructorSetsGivenDataAndPreviousToNodeWhenNodeGiven() {
        assertEquals(object2, nodeObject2.data);
        assertNull(nodeObject2.next);
        assertEquals(nodeObject1, nodeObject2.previous);
    }

    @Test
    public void constructorSetsGivenDataAndPreviousToNodeWhenNullGiven() {
        assertEquals(object3, nodeObject3.data);
        assertNull(nodeObject3.next);
        assertNull(nodeObject3.previous);
    }
}
