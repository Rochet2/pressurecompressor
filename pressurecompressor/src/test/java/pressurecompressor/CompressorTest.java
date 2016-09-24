/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pressurecompressor.containers.BitStorage;

/**
 *
 * @author rimi
 */
public class CompressorTest {

    private Compressor comp;

    @Before
    public void setUp() {
        comp = new Compressor(8);
    }

    @Test
    public void compressReturnsEmptyStringWhenNullGiven() {
        String output = comp.compress(null);
        assertEquals("", output);
    }

    @Test
    public void compressReturnsEmptyStringWhenEmptyStringGiven() {
        String output = comp.compress("");
        assertEquals("", output);
    }

    @Test
    public void decompressReturnsEmptyStringWhenNullGiven() {
        String output = comp.decompress(null);
        assertEquals("", output);
    }

    @Test
    public void decompressReturnsEmptyStringWhenEmptyStringGiven() {
        String output = comp.decompress("");
        assertEquals("", output);
    }

    @Test
    public void uncompressingAfteCompressingReturnsSameStringWhenAllCharactersWithNoRepetitionGiven() {
        byte[] bytes = new byte[128];
        for (int i = 0; i < 128; ++i) {
            bytes[i] = (byte) i;
        }
        String input = StringUtility.bytesToString(bytes);
        String compressed = comp.compress(input);
        String uncompressed = comp.decompress(compressed);
        assertEquals(input, uncompressed);
    }

    @Test
    public void uncompressingReturnsEmptyStringWhenInvalidFirstCharacterGiven() {
        BitStorage store = new BitStorage();
        store.writeBack(-1, 8);
        store.writeBack(100, 8);
        String uncompressed = comp.decompress(store.flushToString());
        assertEquals("", uncompressed);
    }

    @Test
    public void uncompressingReturnsEmptyStringWhenInvalidSecondCharacterGiven() {
        BitStorage store = new BitStorage();
        store.writeBack(100, 8);
        store.writeBack(-1, 8);
        String uncompressed = comp.decompress(store.flushToString());
        assertEquals("", uncompressed);
    }

    @Test
    public void compressingNonAsciiCharactersReturnsEmptyString() {
        byte[] bytes = new byte[256];
        for (int i = 0; i < 256; ++i) {
            bytes[i] = (byte) i;
        }
        String input = StringUtility.bytesToString(bytes);
        String compressed = comp.compress(input);
        assertEquals("", compressed);
    }

    @Test
    public void compressingAndUncompressingSmallTextWithRepetitionReturnsSameText() {
        String input = "This is a small text that has some small repetition";
        String compressed = comp.compress(input);
        String uncompressed = comp.decompress(compressed);
        assertEquals(input, uncompressed);
    }

    @Test
    public void compressingSmallTextWithRepetitionReturnsSmallerText() {
        String input = "This is a small string that has some small repetition";
        String compressed = comp.compress(input);
        assertTrue(compressed.length() < input.length());
    }

}
