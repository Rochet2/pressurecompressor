/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers.linkedlist;

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
     */
    public void pushBack(E b) {
        ++length;
        if (back == null) {
            front = back = new Node<E>(b);
            return;
        }
        back = new Node<E>(b, back);
        back.previous.next = back;
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
     * Removes an element from the list
     *
     * @param e
     * @return didRemove
     */
    public boolean remove(E e) {
        if (e == null) {
            return false;
        }

        Node<E> curr = front;
        while (curr != null) {
            if (e.equals(curr.data)) {
                --length;
                if (curr.previous != null) {
                    curr.previous.next = curr.next;
                }
                if (curr.next != null) {
                    curr.next.previous = curr.previous;
                }
                return true;
            }
        }
        return false;
    }
}
