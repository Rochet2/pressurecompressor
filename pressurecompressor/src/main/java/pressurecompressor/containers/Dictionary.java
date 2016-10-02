/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

import pressurecompressor.containers.nodetypes.Node;
import pressurecompressor.containers.nodetypes.Pair;

/**
 * A class that handles keeping a dictionary of strings mapped to numbers
 *
 * @author rimi
 */
public class Dictionary {

    // Front has least recently used
    private final LinkedList<Integer> lru;
    private final int maxSize;
    private final Object[] bytes; // Pair<ByteSequence, Node<Integer>>
    private int elements;

    /**
     * Creates a new dictionary that can contain at maximum given amount of
     * elements
     *
     * @param amountOfElements
     */
    public Dictionary(int amountOfElements) {
        this.elements = 0;
        this.maxSize = amountOfElements;
        this.lru = new LinkedList<>();
        this.bytes = new Object[this.maxSize];
        ByteSequence[] array = new ByteSequence[100];
    }

    private Pair<ByteSequence, Node<Integer>> g(int index) {
        return (Pair<ByteSequence, Node<Integer>>) bytes[index];
    }

    /**
     * Returns the index of the given string from dictionary or null
     *
     * @param bs
     * @return
     */
    public Integer get(ByteSequence bs) {
        if (bs == null) {
            return null;
        }
        for (int i = 0; i < elements; ++i) {
            if (g(i).first.equals(bs)) {
                if (g(i).second != null) {
                    lru.remove(g(i).second);
                    lru.pushBack(i);
                }
                return i;
            }
        }
        return null;
    }

    /**
     * Returns the element at given index or null
     *
     * @param index Indexes start from 0
     * @return
     */
    public ByteSequence get(int index) {
        if (index >= elements) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        if (g(index).second != null) {
            lru.remove(g(index).second);
            lru.pushBack(index);
        }
        return g(index).first;
    }

    /**
     * Adds a element to the dictionary if the element is not null. May remove
     * an older element.
     *
     * @param bs
     * @param applyLru
     */
    public void add(ByteSequence bs, boolean applyLru) {
        if (bs == null) {
            return;
        }
        if (isFull()) {
            Integer index = lru.popFront();
            if (index == null) {
                return;
            }
            bytes[index] = new Pair<>(bs, applyLru ? lru.pushBack(index) : null);
        } else {
            bytes[elements] = new Pair<>(bs, applyLru ? lru.pushBack(elements) : null);
            ++elements;
        }
    }

    /**
     * Returns true if the dictionary is full and false otherwise
     *
     * @return
     */
    public boolean isFull() {
        return elements >= maxSize;
    }

}
