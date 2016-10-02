/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author rochet2_2
 */
public class ByteSequenceTest {

    private ByteSequence bs0;
    private ByteSequence bs1;
    private ByteSequence bs2;
    private ByteSequence bs3;
    private ByteSequence bs1copy;

    @Before
    public void setUp() {
        this.bs0 = new ByteSequence();
        this.bs1 = new ByteSequence(new byte[]{1});
        this.bs2 = new ByteSequence(new byte[]{2});
        this.bs3 = new ByteSequence(new byte[]{3});
        this.bs1copy = new ByteSequence(new byte[]{1});
    }

    @Test
    public void getBytesReturnsSequenceBytes() {
        assertArrayEquals(new byte[0], bs0.getBytes());
        assertArrayEquals(new byte[]{1}, bs1.getBytes());
        assertArrayEquals(new byte[]{2}, bs2.getBytes());
    }

    @Test
    public void testEquals() {
        assertFalse(bs1 == null);
        assertFalse(bs1.equals(null));
        assertFalse(bs1.equals(bs2));
        assertTrue(bs1.equals(bs1));
        assertTrue(bs1.equals(bs1copy));
    }

    @Test
    public void joinReturnsByteSequencesAsOneSequence() {
        assertArrayEquals(new byte[]{1, 2}, ByteSequence.join(bs1, bs2).getBytes());
    }

}
