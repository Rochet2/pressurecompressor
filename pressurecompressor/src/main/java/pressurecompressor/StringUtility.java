/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import java.nio.charset.StandardCharsets;

/**
 * A class for string operations
 *
 * @author rimi
 */
public class StringUtility {

    /**
     * Returns the bytes reinterpreted as ISO_8859_1 string.
     *
     * ISO_8859_1 is used because it uses 8 bits unlike ASCII, allowing the use
     * of the whole used space.
     *
     * @param bytes
     * @return ISO_8859_1 encoded string
     */
    static public String bytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.ISO_8859_1);
    }

    /**
     * Returns the ISO_8859_1 string reinterpreted as an array of bytes
     *
     * @param string ISO_8859_1 encoded string
     * @return
     */
    static public byte[] stringToBytes(String string) {
        return string.getBytes(StandardCharsets.ISO_8859_1);
    }

    /**
     * Returns true if the onre or more of the bytes are not a part of ASCII
     *
     * @param bytes
     * @return
     */
    static public boolean hasNonAsciiCharacters(byte[] bytes) {
        for (byte b : bytes) {
            if (b < 0 || b > Byte.MAX_VALUE) {
                return true;
            }
        }
        return false;
    }
}
