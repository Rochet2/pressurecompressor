/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

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
        FileUtility.writeFile("testFile.txt", input);
        byte[] output = FileUtility.readFile("testFile.txt");
        assertArrayEquals(input, output);
    }

}
