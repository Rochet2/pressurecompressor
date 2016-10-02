/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers.nodetypes;

/**
 * An element used in linked lists
 *
 * @author rimi
 * @param <E> type of contained data
 */
public class Node<E> {

    public E data;
    public Node previous;
    public Node next;

    /**
     * Creates a new node containing given value
     *
     * @param data
     */
    public Node(E data) {
        this(data, null);
    }

    /**
     * Creates a new node containing given value and having given previous node
     *
     * @param data
     * @param previous
     */
    public Node(E data, Node previous) {
        this.data = data;
        this.previous = previous;
    }

}
