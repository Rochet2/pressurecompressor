/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

/**
 * A class for storing variable length byte arrays
 *
 * @author rochet2_2
 */
public class ByteSequence {

    private final byte[] bytes;

    /**
     * Creates a new empty byte sequence
     */
    public ByteSequence() {
        this(new byte[0]);
    }

    /**
     * Creates a new byte sequence containing given bytes
     *
     * @param bytes
     */
    public ByteSequence(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * Returns the bytes contained by the sequence
     *
     * @return
     */
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ByteSequence)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ByteSequence other = (ByteSequence) obj;
        if (other.bytes.length != this.bytes.length) {
            return false;
        }
        for (int i = 0; i < this.bytes.length; ++i) {
            if (this.bytes[i] != other.bytes[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Joins the two inputs into one
     *
     * @param a
     * @param b
     * @return
     */
    public static ByteSequence join(ByteSequence a, ByteSequence b) {
        if (a == null ||b == null)
            System.out.println("");
        byte[] temp = new byte[a.bytes.length + b.bytes.length];
        int index = 0;
        for (byte u : a.bytes) {
            temp[index++] = u;
        }
        for (byte u : b.bytes) {
            temp[index++] = u;
        }
        return new ByteSequence(temp);
    }
}
