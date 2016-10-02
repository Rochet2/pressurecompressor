/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import java.util.Arrays;
import pressurecompressor.containers.BitStorage;

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

    public static void main(String[] args) {
        {
            BitStorage bgf = new BitStorage();
            bgf.writeBack(3, 4);
            int readFront = bgf.readFront(4);
            System.out.println(readFront);
        }
        
        {
            Compressor comp = new Compressor();
            String input = "Onpa mukava päivä tänään, vähän kylmä";
            byte[] compressed = comp.compress(input.getBytes(), 10);
            byte[] uncompressed = comp.decompress(compressed);
            System.out.println(new String(uncompressed));
        }

        if (true) {
            System.out.println(Arrays.toString(args));
            String input = "^WED^WE^WEE^WEB^WET";
            System.out.println("original: " + input);
            System.out.println("input length: " + input.length());

            Compressor comp = new Compressor();
            byte[] selected = null;
            for (int i = 0; i < 4; ++i) {
                byte[] compressed = comp.compress(input.getBytes(), 8 + i);
                if (selected == null || selected.length > compressed.length) {
                    selected = compressed;
                }
                System.out.println("compressed bits: " + 8 + i);
                System.out.println("compressed length: " + compressed.length);
                System.out.println("compressed string is " + (compressed.length / (float) input.length() * 100) + "% of the original");
            }

            byte[] uncompressed = comp.decompress(selected);
            System.out.println("uncompressed: " + new String(uncompressed));
            return;
        }

        if (args.length < 2 || args.length > 3) {
            printHelp();
            return;
        }

        int bits = 8;
        if (args.length >= 3) {
            int wantedBits = Integer.parseInt(args[2]);
            if (bits < wantedBits) {
                bits = wantedBits;
            }
        }
        Compressor comp = new Compressor();
        byte[] input = FileUtility.readFile(args[1]);
        if ("compress".startsWith(args[0])) {
            byte[] output = comp.compress(input, bits);
            FileUtility.writeFile(args[1] + ".compr", output);
            printResults(input.length, output.length);
        } else if ("decompress".startsWith(args[0])) {
            byte[] output = comp.decompress(input);
            FileUtility.writeFile(args[1] + ".decom", output);
            printResults(input.length, output.length);
        } else {
            printHelp();
        }
    }
}
