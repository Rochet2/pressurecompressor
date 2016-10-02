/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

import pressurecompressor.containers.nodetypes.Node;

/**
 * A class for storing and handling bits
 *
 * @author rimi
 */
public class BitStorage {

    private final LinkedList<Boolean> bitStorage;

    /**
     * Creates a new bit storage
     */
    public BitStorage() {
        this.bitStorage = new LinkedList<>();
    }

    /**
     * Creates a new bit storage containing the bits from the given string
     *
     * @param input
     */
    public BitStorage(byte[] input) {
        this();
        for (byte b : input) {
            writeBack(b, Byte.SIZE);
        }
    }

    /**
     * Writes a given number of bits from given data to the back of the storage
     *
     * @param data
     * @param numberOfBitsToWrite
     */
    public void writeBack(int data, int numberOfBitsToWrite) {
        for (int i = numberOfBitsToWrite - 1; i >= 0; --i) {
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
        for (int i = numberOfBitsToRead - 1; i >= 0; --i) {
            if (!hasBitsToRead(1)) {
                continue;
            }
            Boolean b = bitStorage.popFront();
            if (b != null && b) {
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

    public byte[] flushToBytes() {
        byte[] bytes = new byte[(int) Math.ceil(bitStorage.length() / (double) Byte.SIZE)];
        int written = 0;
        while (hasBitsToRead(1)) {
            int value = readFront(Byte.SIZE);
            bytes[written++] = (byte) value;
        }
        return bytes;
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
        Node<Boolean> node = bitStorage.peekFront();
        while (node != null && node.data != null) {
            sb.append(node.data ? 1 : 0);
            node = node.next;
        }
        return sb.toString();
    }
}
