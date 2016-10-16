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

    private static final String file_extension = ".lzw";
    private static final byte defaultCompressionBytes = 10;

    /**
     * Prints usage of the program
     */
    private static void printHelp() {
        System.out.println("You must give a file path to the program. Based on the file extension it is either compressed or decompressed.");
        System.out.println("If file extension is " + file_extension + " then it is decompressed. Otherwise it is compressed and output is written with that extension.");
        System.out.println("If compressing you can provide an additional argument that defines how many bits are used for each token written by compressor.");
    }

    public static void main(String[] args) {

        if (args.length < 1 || args.length > 2) {
            printHelp();
            return;
        }

        String filePath = args[0];
        byte codeLength = defaultCompressionBytes;
        if (args.length >= 2) {
            Byte.parseByte(args[1]);
        }

        Compressor comp = new Compressor();
        byte[] input = FileUtility.readFile(filePath);
        if (input == null) {
            System.out.println("Could not read given file.");
            return;
        }

        if (fileIsCompressed(filePath)) {
            byte[] output = comp.decompress(input);
            if (output == null) {
                System.out.println("Decompression failed");
            } else {
                FileUtility.writeFile(filePath.substring(0, filePath.length() - file_extension.length()), output);
                System.out.println("Decompression succeeded");
            }
        } else {
            byte[] output = comp.compress(input, codeLength);
            if (output == null) {
                System.out.println("Compression failed");
            } else {
                FileUtility.writeFile(filePath + ".lzw", output);
                System.out.println("Compression succeeded");
            }
        }
    }

    /**
     * Returns true if the file is compressed (based on it's file extension)
     *
     * @param filePath
     * @return
     */
    private static boolean fileIsCompressed(String filePath) {
        return filePath.endsWith(file_extension);
    }
}
