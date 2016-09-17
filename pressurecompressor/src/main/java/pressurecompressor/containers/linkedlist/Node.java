/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers.linkedlist;

/**
 * An element used in linked lists
 *
 * @author rimi
 */
public class Node {

    public boolean data;
    public Node previous;
    public Node next;

    /**
     * Creates a new node containing given value
     *
     * @param data
     */
    public Node(boolean data) {
        this(data, null);
    }

    /**
     * Creates a new node containing given value and having given previous node
     *
     * @param data
     * @param previous
     */
    public Node(boolean data, Node previous) {
        this.data = data;
        this.previous = previous;
    }
}
