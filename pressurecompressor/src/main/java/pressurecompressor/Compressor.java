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

    public static final byte minimumCodeLength = Byte.SIZE;
    public static final byte maximumCodeLength = 2 * Byte.SIZE;

    /**
     * Compresses the given input. If input is null or empty then an empty
     * output is returned. Uses given amount of bits to code each character.
     *
     * @param input
     * @param codeBitLength can be from 8 to 14
     * @return null if failed, array otherwise
     */
    public byte[] compress(byte[] input, byte codeBitLength) {
        if (input == null) {
            System.out.println("Input is null");
            return null;
        }
        if (input.length == 0) {
            return new byte[0];
        }
        if (codeBitLength < minimumCodeLength || codeBitLength > maximumCodeLength) {
            System.out.println("Input has invalid code bit length");
            System.out.println("Was " + codeBitLength + " and expected between " + minimumCodeLength + " and " + maximumCodeLength);
            return null;
        }
        BitStorage output = new BitStorage();
        output.writeBack(codeBitLength, Byte.SIZE);
        Dictionary dictionary = createDictionary((int) Math.pow(2, codeBitLength));
        ByteSequence sequence = new ByteSequence();
        for (byte b : input) {
            ByteSequence B = new ByteSequence(new byte[]{b});
            ByteSequence sequencePlusB = ByteSequence.join(sequence, B);

            if (dictionary.get(sequencePlusB) != null) {
                sequence = sequencePlusB;
            } else {
                output.writeBack(dictionary.get(sequence), codeBitLength);
                if (dictionary.isFull()) {
                    dictionary.reset();
                }
                dictionary.add(sequencePlusB);
                sequence = B;
            }
        }
        output.writeBack(dictionary.get(sequence), codeBitLength);
        return output.flushToBytes();
    }

    /**
     * Decompresses the given input. Input is assumed to be the output of the
     * compress function. Returns empty output if input is null or there are not
     * enough bits to decompress or if the algorithm fails
     *
     * @param input
     * @return null if failed, array otherwise
     */
    public byte[] decompress(byte[] input) {
        if (input == null) {
            System.out.println("Input is null");
            return null;
        }
        BitStorage inputBytes = new BitStorage(input);
        if (!inputBytes.hasBitsToRead(Byte.SIZE)) {
            System.out.println("Input is too small to be valid");
            return null;
        }
        int codeBitLength = inputBytes.readFront(Byte.SIZE);
        if (codeBitLength < minimumCodeLength || codeBitLength > maximumCodeLength) {
            System.out.println("Input has invalid code bit length");
            System.out.println("Was " + codeBitLength + " and expected between " + minimumCodeLength + " and " + maximumCodeLength);
            return null;
        }
        if (!inputBytes.hasBitsToRead(codeBitLength)) {
            return new byte[0];
        }
        Dictionary dictionary = createDictionary((int) Math.pow(2, codeBitLength));
        LinkedList<ByteSequence> output = new LinkedList<>();
        int previousCode = inputBytes.readFront(codeBitLength);
        ByteSequence sequenceOrB = dictionary.get(previousCode);
        if (sequenceOrB == null) {
            System.out.println("Input has invalid token");
            return null;
        }
        output.pushBack(sequenceOrB);
        while (inputBytes.hasBitsToRead(codeBitLength)) {
            int codeToSequenceOrB = inputBytes.readFront(codeBitLength);
            sequenceOrB = dictionary.get(codeToSequenceOrB);
            if (sequenceOrB != null) {
                output.pushBack(sequenceOrB);
                ByteSequence previousSequenceOrB = dictionary.get(previousCode);
                if (dictionary.isFull()) {
                    dictionary.reset();
                }
                dictionary.add(ByteSequence.join(previousSequenceOrB, new ByteSequence(new byte[]{sequenceOrB.getBytes()[0]})));
            } else {
                ByteSequence previousSequenceOrB = dictionary.get(previousCode);
                ByteSequence sequence = ByteSequence.join(previousSequenceOrB, new ByteSequence(new byte[]{previousSequenceOrB.getBytes()[0]}));
                output.pushBack(sequence);
                if (dictionary.isFull()) {
                    dictionary.reset();
                }
                dictionary.add(sequence);
            }
            previousCode = codeToSequenceOrB;
        }
        return toBytes(output);
    }

    /**
     * Creates a dictionary of a specific size. The returned dictionary contains
     * all one byte size sequences
     *
     * @param dictionarySize
     * @return
     */
    private Dictionary createDictionary(int dictionarySize) {
        Dictionary dictionary = new Dictionary(dictionarySize);
        dictionary.reset();
        return dictionary;
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
