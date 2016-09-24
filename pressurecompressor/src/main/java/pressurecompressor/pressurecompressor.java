/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

/**
 *
 * @author rimi
 */
public class pressurecompressor {

    public static void main(String[] args) {
        String input = "^WED^WE^WEE^WEB^WET";
        System.out.println("original: " + input);

        Compressor comp = new Compressor(8);
        String compressed = comp.compress(input);
        System.out.println("input length: " + input.length());
        System.out.println("compressed length: " + compressed.length());
        System.out.println("compressed string is " + (compressed.length() / (float) input.length() * 100) + "% of the original");

        String uncompressed = comp.decompress(compressed);
        System.out.println("uncompressed: " + uncompressed);
    }
}
