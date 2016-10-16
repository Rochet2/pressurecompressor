/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

/**
 * A class that handles keeping a dictionary of elements mapped to numbers.
 *
 * @author rimi
 */
public class Dictionary {

    private final ByteSequence[] bytes;
    private int elements;

    /**
     * Creates a new dictionary that can contain at maximum given amount of
     * elements
     *
     * @param amountOfElements
     */
    public Dictionary(int amountOfElements) {
        this.elements = 0;
        this.bytes = new ByteSequence[amountOfElements];
    }

    /**
     * Returns the index of the given element from dictionary or null.
     *
     * @param bs
     * @return
     */
    public Integer get(ByteSequence bs) {
        if (bs == null) {
            return null;
        }
        for (int i = 0; i < elements; ++i) {
            if (bytes[i].equals(bs)) {
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
        return bytes[index];
    }

    /**
     * Adds a element to the dictionary if the element is not null. If the
     * dictionary is completely full it is cleared.
     *
     * @param bs
     */
    public void add(ByteSequence bs) {
        if (bs == null) {
            return;
        }
        if (!isFull()) {
            bytes[elements] = bs;
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

    /**
     * Clears and initializes the storage
     */
    public void reset() {
        this.elements = 0;
        for (int i = 0; i < (int) Math.pow(2, Byte.SIZE); ++i) {
            if (isFull()) {
                break;
            }
            bytes[elements] = new ByteSequence(new byte[]{(byte) i});
            ++elements;
        }
    }

}
