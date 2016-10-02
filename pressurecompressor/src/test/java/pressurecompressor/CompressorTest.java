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
        byte[] output = comp.compress(null);
        assertArrayEquals(new byte[0], output);
    }

    @Test
    public void compressReturnsEmptyStringWhenEmptyStringGiven() {
        byte[] output = comp.compress(new byte[0]);
        assertArrayEquals(new byte[0], output);
    }

    @Test
    public void decompressReturnsEmptyStringWhenNullGiven() {
        byte[] output = comp.decompress(null);
        assertArrayEquals(new byte[0], output);
    }

    @Test
    public void decompressReturnsEmptyStringWhenEmptyStringGiven() {
        byte[] output = comp.decompress(new byte[0]);
        assertArrayEquals(new byte[0], output);
    }

    @Test
    public void uncompressingAfteCompressingReturnsSameStringWhenAllCharactersWithNoRepetitionGiven() {
        byte[] input = new byte[(int) Math.pow(2, Byte.SIZE) - 1];
        for (int i = 0; i < Math.pow(2, Byte.SIZE) - 1; ++i) {
            input[i] = (byte) i;
        }
        byte[] compressed = comp.compress(input);
        byte[] uncompressed = comp.decompress(compressed);
        assertArrayEquals(input, uncompressed);
    }

    @Test
    public void uncompressingReturnsEmptyStringWhenInvalidSecondCharacterGiven() {
        BitStorage store = new BitStorage();
        store.writeBack(100, 8);
        store.writeBack(-1, 8);
        byte[] uncompressed = comp.decompress(store.flushToBytes());
        assertArrayEquals(new byte[0], uncompressed);
    }

    @Test
    public void compressingAndUncompressingSmallTextWithRepetitionReturnsSameText() {
        String input = "This is a small text that has some small repetition";
        byte[] compressed = comp.compress(input.getBytes());
        byte[] uncompressed = comp.decompress(compressed);
        assertEquals(input, new String(uncompressed));
    }

    @Test
    public void compressingAndUncompressingSmallTextWithScandicLettersReturnsSameText() {
        String input = "Onpa mukava päivä tänään, vähän kylmä";
        byte[] compressed = comp.compress(input.getBytes());
        byte[] uncompressed = comp.decompress(compressed);
        assertEquals(input, new String(uncompressed));
    }

}
