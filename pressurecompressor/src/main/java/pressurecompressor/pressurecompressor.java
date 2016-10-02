/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author rimi
 */
public class pressurecompressor {

    /**
     * Prints usage of the program
     */
    public static void printHelp() {
        System.out.println("Program parameters: compress|decompress file");
    }

    public static void printResults(int input, int output) {
        System.out.println("Input length: " + input);
        System.out.println("Output length: " + output);
        if (input == 0) {
            System.out.println("input was empty");
        } else if (output == 0) {
            System.out.println("input was invalid");
        } else {
            System.out.println("Output is " + output / (float) input * 100 + "% of input");
        }
    }

    private static byte[] tryCompress(byte[] input) {
        Compressor comp = new Compressor();
        byte[] selected = null;
        // for (int i = 0; i <= Byte.MAX_VALUE; ++i) {
        for (int i = 12; i <= 12; ++i) {
            byte[] compressed = comp.compress(input, (byte) (i));
            if (compressed == null || compressed.length == 0) {
                continue;
            }
            if (selected == null || selected.length > compressed.length) {
                selected = compressed;
            }
        }
        return selected;
    }

    public static void main(String[] args) {

        if (true) {
            System.out.println(Arrays.toString(args));
            // String input = "^WED^WE^WEE^WEB^WET";
            // System.out.println("original: " + input);
            // System.out.println("input length: " + input.length());

            Compressor comp = new Compressor();
            byte[] bytes = new byte[1000000];
            new Random().nextBytes(bytes);
            for (int i = 0; i < bytes.length; ++i) {
                bytes[i] &= 0xF;
            }
            byte[] selected = tryCompress(bytes);

            System.out.println("original length: " + bytes.length);
            System.out.println("compressed length: " + selected.length);
            System.out.println("compressed string is " + (selected.length / (float) bytes.length * 100) + "% of the original");
            long aikaAlussa = System.currentTimeMillis();
            byte[] uncompressed = comp.decompress(selected);
            long aikaLopussa = System.currentTimeMillis();
            System.out.println("time spent: " + (aikaLopussa - aikaAlussa) + "ms");
            // System.out.println("uncompressed: " + new String(uncompressed));
            return;
        }

        if (args.length < 2 || args.length > 3) {
            printHelp();
            return;
        }

        byte bits = 8;
        if (args.length >= 3) {
            byte wantedBits = Byte.parseByte(args[2]);
            if (bits < wantedBits) {
                bits = wantedBits;
            }
        }
        Compressor comp = new Compressor();
        byte[] input = FileUtility.readFile(args[1]);
        if ("compress".startsWith(args[0])) {
            byte[] output = tryCompress(input);
            if (output == null) {
                System.out.println("Compression failed or input is empty");
            } else {
                FileUtility.writeFile(args[1] + ".compr", output);
                printResults(input.length, output.length);
            }
        } else if ("decompress".startsWith(args[0])) {
            byte[] output = comp.decompress(input);
            if (output == null || output.length == 0) {
                System.out.println("Decompression failed or input is empty");
            } else {
                FileUtility.writeFile(args[1] + ".decom", output);
                printResults(input.length, output.length);
            }
        } else {
            printHelp();
        }
    }
}
