/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rochet2_2
 */
public class FileUtilityTest {

    @Test
    public void fileWriteAndReadDoesNotAlterInput() {
        byte[] input = new byte[256];
        for (int i = 0; i < 256; ++i) {
            input[i] = (byte) i;
        }
        FileUtility.writeFile("target/FileUtility_fileWriteAndReadDoesNotAlterInput.txt", input);
        byte[] output = FileUtility.readFile("target/FileUtility_fileWriteAndReadDoesNotAlterInput.txt");
        assertArrayEquals(input, output);
    }

    @Test
    public void readNonexistentFileReturnsNull() {
        try {
            Files.deleteIfExists(new File("target/FileUtility_readNonexistentFileReturnsNull.txt").toPath());
        } catch (IOException ex) {
            assertTrue("Could not delete file", false);
        }
        byte[] output = FileUtility.readFile("target/FileUtility_readNonexistentFileReturnsNull.txt");
        assertNull(output);
    }

    @Test
    public void readExistentFileReturnsArray() {
        byte[] input = new byte[0];
        FileUtility.writeFile("target/FileUtility_readExistentFileReturnsArray.txt", input);
        byte[] output = FileUtility.readFile("target/FileUtility_readExistentFileReturnsArray.txt");
        assertArrayEquals(input, output);
    }

}
