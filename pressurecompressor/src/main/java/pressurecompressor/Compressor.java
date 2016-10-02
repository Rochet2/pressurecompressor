/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import pressurecompressor.containers.BitStorage;
import pressurecompressor.containers.ByteSequence;
import pressurecompressor.containers.Dictionary;
import pressurecompressor.containers.LinkedList;
import pressurecompressor.containers.nodetypes.Node;

/**
 * A class for compressing and decompressing data
 *
 * @author rimi
 */
public class Compressor {

    /**
     * Compresses the given input. If input is null or empty then an empty
     * output is returned. Uses given amount of bits to code each character.
     *
     * @param input
     * @param codeBitLength
     * @return
     */
    public byte[] compress(byte[] input, byte codeBitLength) {
        if (input == null || input.length == 0) {
            return new byte[0];
        }
        if (codeBitLength < Byte.SIZE || codeBitLength > 14) {
            return new byte[0];
        }
        BitStorage output = new BitStorage();
        output.writeBack(codeBitLength, Byte.SIZE);
        Dictionary dictionary = createDictionary((int) Math.pow(2, codeBitLength));
        ByteSequence w = new ByteSequence();
        for (byte b : input) {
            ByteSequence k = new ByteSequence(new byte[]{b});
            ByteSequence wk = ByteSequence.join(w, k);

            if (dictionary.get(wk) != null) {
                w = wk;
            } else {
                output.writeBack(dictionary.get(w), codeBitLength);
                if (dictionary.isFull()) {
                    dictionary.reset();
                }
                dictionary.add(wk);
                w = k;
            }
        }
        output.writeBack(dictionary.get(w), codeBitLength);
        return output.flushToBytes();
    }

    /**
     * Decompresses the given input. Input is assumed to be the output of the
     * compress function. Returns empty output if input is null or there are not
     * enough bits to decompress or if the algorithm fails
     *
     * @param input
     * @return
     */
    public byte[] decompress(byte[] input) {
        if (input == null) {
            return new byte[0];
        }
        BitStorage store = new BitStorage(input);
        if (!store.hasBitsToRead(Integer.SIZE)) {
            return new byte[0];
        }
        int codeBitLength = store.readFront(Byte.SIZE);
        if (codeBitLength <= Byte.SIZE || codeBitLength > 14) {
            return new byte[0];
        }
        if (!store.hasBitsToRead(codeBitLength)) {
            return new byte[0];
        }
        Dictionary dictionary = createDictionary((int) Math.pow(2, codeBitLength));
        LinkedList<ByteSequence> result = new LinkedList<>();
        int k = store.readFront(codeBitLength);
        ByteSequence entry = dictionary.get(k);
        if (entry == null) {
            return new byte[0];
        }
        result.pushBack(entry);
        ByteSequence w = entry;
        while (store.hasBitsToRead(codeBitLength)) {
            k = store.readFront(codeBitLength);
            entry = dictionary.get(k);
            if (entry == null) {
                return new byte[0];
            }
            result.pushBack(entry);
            if (dictionary.isFull()) {
                dictionary.reset();
            }
            dictionary.add(ByteSequence.join(w, new ByteSequence(new byte[]{entry.getBytes()[0]})));
            w = entry;
        }
        return toBytes(result);
    }

    /**
     * Creates a dictionary of a specific size. The returned dictionary contains
     * all one byte size sequences
     *
     * @param dictionarySize
     * @return
     */
    private Dictionary createDictionary(int dictionarySize) {
        Dictionary map = new Dictionary(dictionarySize);
        map.reset();
        return map;
    }

    /**
     * Returns a byte array containing all the bytes from the input
     *
     * @param bytes
     * @return
     */
    private static byte[] toBytes(LinkedList<ByteSequence> bytes) {
        int byteCount = 0;
        Node<ByteSequence> curr = bytes.peekFront();
        while (curr != null) {
            byteCount += curr.data.getBytes().length;
            curr = curr.next;
        }
        byte[] temp = new byte[byteCount];
        int i = 0;
        curr = bytes.peekFront();
        while (curr != null) {
            for (byte b : curr.data.getBytes()) {
                temp[i++] = b;
            }
            curr = curr.next;
        }
        return temp;
    }

}
