/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

import pressurecompressor.containers.nodetypes.Node;
import pressurecompressor.containers.nodetypes.Pair;

/**
 * A class that handles keeping a dictionary of elements mapped to numbers.
 * There is an LRU cache for when the dictionary gets full
 *
 * @author rimi
 */
public class Dictionary {

    // Front has least recently used
    private final LinkedList<Integer> lru;
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
        this.lru = new LinkedList<>();
        this.bytes = new Object[amountOfElements];
    }

    /**
     * An utility function for getting the element at given index and casting it
     * to element type
     *
     * @param index
     * @return
     */
    private Pair<ByteSequence, Node<Integer>> getElementAt(int index) {
        return (Pair<ByteSequence, Node<Integer>>) bytes[index];
    }

    /**
     * Returns the index of the given element from dictionary or null. Also
     * updates the element to be on top of LRU cache.
     *
     * @param bs
     * @return
     */
    public Integer get(ByteSequence bs) {
        if (bs == null) {
            return null;
        }
        for (int i = 0; i < elements; ++i) {
            if (getElementAt(i).first.equals(bs)) {
                if (getElementAt(i).second != null) {
                    lru.remove(getElementAt(i).second);
                    lru.pushBack(i);
                }
                return i;
            }
        }
        return null;
    }

    /**
     * Returns the element at given index or null Also updates the element to be
     * on top of LRU cache.
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
        if (getElementAt(index).second != null) {
            lru.remove(getElementAt(index).second);
            lru.pushBack(index);
        }
        return getElementAt(index).first;
    }

    /**
     * Adds a element to the dictionary if the element is not null. If the
     * dictionary is completely full, no elements are added. Adds the element to
     * the top of the LRU cache if LRU is applied. May remove an older element
     * from LRU cache if dictionary is full.
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
        return elements >= bytes.length;
    }

}
