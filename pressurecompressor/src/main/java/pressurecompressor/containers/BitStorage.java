/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

import pressurecompressor.containers.linkedlist.LinkedList;
import pressurecompressor.containers.linkedlist.Node;

/**
 * A class for storing and handling bits
 *
 * @author rimi
 */
public class BitStorage {

    private final LinkedList bitStorage;

    /**
     * Creates a new bit storage
     */
    public BitStorage() {
        this.bitStorage = new LinkedList();
    }

    /**
     * Writes a given number of bits from given data to the back of the storage
     *
     * @param data
     * @param numberOfBitsToWrite
     */
    public void writeBack(int data, int numberOfBitsToWrite) {
        for (int i = 0; i < numberOfBitsToWrite; ++i) {
            bitStorage.pushBack(((data >> i) & 1) == 1);
        }
    }

    /**
     * Reads given amount of bits from the front of the storage and converts
     * them to an integer
     *
     * @param numberOfBitsToRead
     * @return
     */
    public int readFront(int numberOfBitsToRead) {
        int data = 0;
        for (int i = 0; i < numberOfBitsToRead; ++i) {
            if (hasBitsToRead(1) && bitStorage.popFront()) {
                data |= 1 << i;
            }
        }
        return data;
    }

    /**
     * Returns false if the storage contains less bits than expected, otherwise
     * returns true
     *
     * @param amount expected amount of bits to contain
     * @return
     */
    public boolean hasBitsToRead(int amount) {
        return bitStorage.length() >= amount;
    }

    /**
     * Returns the amount of bits the storage contains
     *
     * @return
     */
    public int getBitCount() {
        return bitStorage.length();
    }

    /**
     * Returns the stored bits reinterpreted as ASCII text and empties the
     * storage
     *
     * @return
     */
    public String flushToString() {
        StringBuilder sb = new StringBuilder();
        while (hasBitsToRead(1)) {
            int value = readFront(Byte.SIZE);
            sb.append((char) value);
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the storage. The string is a list of 1
     * and 0 indicating if a bit is set or not
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(bitStorage.length());
        Node node = bitStorage.peekFront();
        while (node != null) {
            sb.append(node.data ? 1 : 0);
            node = node.next;
        }
        return sb.toString();
    }
}
