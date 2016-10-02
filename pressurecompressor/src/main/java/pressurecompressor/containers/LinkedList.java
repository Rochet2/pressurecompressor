/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

import pressurecompressor.containers.nodetypes.Node;

/**
 * A linked list implementation
 *
 * @author rimi
 * @param <E> type of contained elements
 */
public class LinkedList<E> {

    private Node<E> front;
    private Node<E> back;
    private int length;

    /**
     * Creates a new empty linked list
     */
    public LinkedList() {
        this.front = null;
        this.back = null;
        this.length = 0;
    }

    /**
     * Returns the amount of elements stored in the linked list
     *
     * @return
     */
    public int length() {
        return length;
    }

    /**
     * Returns the node at the front of the list without removing it from the
     * list
     *
     * @return
     */
    public Node<E> peekFront() {
        return front;
    }

    /**
     * Adds an element to the back of the list with the given value
     *
     * @param e
     * @return
     */
    public Node<E> pushBack(E e) {
        ++length;
        if (length == 1) {
            front = new Node<>(e);
            back = front;
            return back;
        }
        Node<E> prev = back;
        back = new Node<>(e, prev);
        if (prev != null) {
            prev.next = back;
        }
        return back;
    }

    /**
     * Removes an element from the front of the list and returns the value of it
     *
     * @return
     */
    public E popFront() {
        if (length <= 1) {
            E data = null;
            if (front != null) {
                data = front.data;
            }
            length = 0;
            back = null;
            front = null;
            return data;
        }
        --length;
        E data = front.data;
        front = front.next;
        if (front.previous != null) {
            front.previous.next = null;
        }
        front.previous = null;
        return data;
    }

    /**
     * Removes an element from the back of the list and returns the value of it
     *
     * @return
     */
    public E popBack() {
        if (length <= 1) {
            E data = null;
            if (back != null) {
                data = back.data;
            }
            length = 0;
            back = null;
            front = null;
            return data;
        }
        --length;
        E data = back.data;
        back = back.previous;
        if (back.next != null) {
            back.next.previous = null;
        }
        back.next = null;
        return data;
    }
}
