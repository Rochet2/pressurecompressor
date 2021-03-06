/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for file operations
 *
 * @author rimi
 */
public class FileUtility {

    /**
     * Returns all bytes (raw) from the given file. Returns an empty array if
     * there were errors
     *
     * @param filePath
     * @return
     */
    public static byte[] readFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            byte[] data = Files.readAllBytes(path);
            return data;
        } catch (IOException ex) {
            System.out.println("Error reading file: "+ex.toString());
        }
        return null;
    }

    /**
     * Writes given bytes to the file
     *
     * @param filePath
     * @param bytes
     */
    public static void writeFile(String filePath, byte[] bytes) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
        } catch (IOException ex) {
            System.out.println("Error writing file: "+ex.toString());
        }
    }
}
