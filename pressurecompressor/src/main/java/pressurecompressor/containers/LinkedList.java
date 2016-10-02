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
 * @param <E>
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
     * @param b
     * @return
     */
    public Node<E> pushBack(E b) {
        ++length;
        if (back == null) {
            front = back = new Node<>(b);
            return back;
        }
        back = new Node<>(b, back);
        back.previous.next = back;
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
        front.previous.next = null;
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
        back.next.previous = null;
        back.next = null;
        return data;
    }

    /**
     * Removes the element from the list
     *
     * @param elem
     */
    public void remove(Node<E> elem) {
        if (elem == null) {
            return;
        }
        --length;
        if (elem.previous != null) {
            elem.previous.next = elem.next;
        }
        if (elem.next != null) {
            elem.next.previous = elem.previous;
        }
    }
}
