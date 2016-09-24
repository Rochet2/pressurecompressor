/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import pressurecompressor.containers.BitStorage;
import pressurecompressor.containers.Dictionary;

/**
 * A class for compressing and decompressing data
 *
 * @author rimi
 */
public class Compressor {

    private final int codeBitLength;
    private final int dictionarySize;

    public Compressor(int codeBitLength) {
        this.codeBitLength = codeBitLength;
        this.dictionarySize = (int) Math.pow(2, codeBitLength) - 1;
    }

    /**
     * Compresses the given string. If input is null, empty or contains non
     * ascii bytes then an empty string is returned
     *
     * @param input
     * @return
     */
    public String compress(String input) {
        if (input == null || input.isEmpty()
                || StringUtility.hasNonAsciiCharacters(StringUtility.stringToBytes(input))) {
            return "";
        }
        BitStorage output = new BitStorage();
        Dictionary dictionary = createDictionary();
        {
            String w = "";
            for (byte b : StringUtility.stringToBytes(input)) {
                String k = StringUtility.bytesToString(new byte[]{b});
                String wk = w + k;
                if (dictionary.get(wk) != null) {
                    w = wk;
                } else {
                    dictionary.add(wk);
                    output.writeBack(dictionary.get(w), codeBitLength);
                    w = k;
                }
            }
        }
        output.writeBack(input.charAt(input.length() - 1), codeBitLength);
        return output.flushToString();
    }

    /**
     * Decompresses the given string. Input is assumed to be the output of the
     * compress function. Returns empty string if input is null or there are not
     * enough bits to decompress or if the algorithm fails
     *
     * @param input
     * @return
     */
    public String decompress(String input) {
        if (input == null) {
            return "";
        }
        BitStorage store = new BitStorage(input);
        if (!store.hasBitsToRead(codeBitLength)) {
            return "";
        }
        Dictionary dictionary = createDictionary();
        StringBuilder result = new StringBuilder();
        int k = store.readFront(codeBitLength);
        String entry = dictionary.get(k);
        if (entry == null) {
            return "";
        }
        result.append(entry);
        String w = Character.toString((char) k);
        while (store.hasBitsToRead(codeBitLength)) {
            k = store.readFront(codeBitLength);
            entry = dictionary.get(k);
            if (entry == null) {
                return "";
            }
            result.append(entry);
            dictionary.add(w + entry.charAt(0));
            w = entry;
        }
        return result.toString();
    }

    /**
     * Creates a dictionary of a specific size.
     *
     * @return
     */
    private Dictionary createDictionary() {
        Dictionary map = new Dictionary(dictionarySize);
        for (char i = 0; i <= Byte.MAX_VALUE; ++i) {
            String key = Character.toString(i);
            if (map.get(key) == null) {
                map.add(key);
            }
        }
        return map;
    }

}
