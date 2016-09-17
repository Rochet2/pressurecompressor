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
 */
public class LinkedList {

    private Node front;
    private Node back;
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
    public Node peekFront() {
        return front;
    }

    /**
     * Adds an element to the back of the list with the given value
     *
     * @param b
     */
    public void pushBack(boolean b) {
        ++length;
        if (back == null) {
            front = back = new Node(b);
            return;
        }
        back = new Node(b, back);
        back.previous.next = back;
    }

    /**
     * Removes an element from the front of the list and returns the value of it
     *
     * @return
     */
    public boolean popFront() {
        if (length <= 1) {
            boolean data = false;
            if (front != null) {
                data = front.data;
            }
            length = 0;
            back = null;
            front = null;
            return data;
        }
        --length;
        boolean data = front.data;
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
    public boolean popBack() {
        if (length <= 1) {
            boolean data = false;
            if (back != null) {
                data = back.data;
            }
            length = 0;
            back = null;
            front = null;
            return data;
        }
        --length;
        boolean data = back.data;
        back = back.previous;
        back.next.previous = null;
        back.next = null;
        return data;
    }
}
