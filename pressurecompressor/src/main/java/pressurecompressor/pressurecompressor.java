/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 *
 * @author rimi
 */
public class pressurecompressor {

    /**
     * Creates a file or overwrites existing file with given content
     *
     * @param fileName name of the file to write to
     * @param content content of the file
     */
    public static void Write(String fileName, String content) {
        try (PrintWriter writer = new PrintWriter(fileName, "ISO-8859-1")) {
            writer.println(content);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            System.out.println("Could not write to " + fileName + ": " + ex.getMessage());
        }
    }

    public static String Read(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            if (line != null) {
                sb.append(line);
            }

            line = br.readLine();
            while (line != null) {
                sb.append(System.lineSeparator());
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException ex) {
            System.out.println("Could not read " + fileName + ": " + ex.getMessage());
        }
        return null;
    }

    public static void printHelp() {
        System.out.println("Usage: compress|decompress file bits");
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

    public static void main(String[] args) throws IOException {

        if (true) {
            // test code here
            Compressor comp = new Compressor(8);
            System.out.println(Arrays.toString(args));
            Write("testfile.txt", Arrays.toString(args));
            String input = "^WED^WE^WEE^WEB^WET";
            System.out.println("original: " + input);

            String compressed = comp.compress(input);
            System.out.println("input length: " + input.length());
            System.out.println("compressed length: " + compressed.length());
            System.out.println("compressed string is " + (compressed.length() / (float) input.length() * 100) + "% of the original");

            String uncompressed = comp.decompress(compressed);
            System.out.println("uncompressed: " + uncompressed);
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
        Compressor comp = new Compressor(bits);
        String input = Read(args[1]);
        if ("compress".startsWith(args[0])) {
            String output = comp.compress(input);
            Write(args[1] + ".compr", output);
            printResults(input.length(), output.length());
        } else if ("decompress".startsWith(args[0])) {
            String output = comp.decompress(input);
            Write(args[1] + ".decom", output);
            printResults(input.length(), output.length());
        } else {
            printHelp();
        }
    }
}
