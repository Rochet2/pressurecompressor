/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor;

import java.util.Random;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 *
 * @author rimi
 */
public class ProfilingTest {

    @Test
    public void outputSpeedsOnRandomRepetitiveInput() {
        byte byteCodeLength = 10;
        int inputIncrement = 5000;
        int maxInputLength = 100000;

        byte[] dataSeed = FileUtility.readFile("src/test/java/pressurecompressor/humanWrittenFile");

        Compressor comp = new Compressor();

        StringBuilder compressionResults = new StringBuilder();
        StringBuilder compressionTimes = new StringBuilder();
        StringBuilder decompressionTimes = new StringBuilder();

        compressionResults.append("inputLength").append("\t").append("outputPercentageOfOriginal");
        compressionTimes.append("inputLength").append("\t").append("compressionTimeInMs");
        decompressionTimes.append("inputLength").append("\t").append("decompressionTimeInMs");
        {
            compressionResults.append("\n").append("codeLength: ").append(byteCodeLength).append("\n");
            compressionTimes.append("\n").append("codeLength: ").append(byteCodeLength).append("\n");
            decompressionTimes.append("\n").append("codeLength: ").append(byteCodeLength).append("\n");
            for (int inputLength = inputIncrement; inputLength <= maxInputLength; inputLength += inputIncrement) {

                // Generate random bytes based on seed
                byte[] input = new byte[inputLength];
                for (int i = 0; i < input.length; ++i) {
                    input[i] = dataSeed[i % dataSeed.length];
                }

                byte[] result = null;
                {
                    long beginTime = System.currentTimeMillis();
                    result = comp.compress(input, byteCodeLength);
                    long endTime = System.currentTimeMillis();
                    compressionTimes.append(inputLength).append("\t").append(endTime - beginTime).append("\n");
                    compressionResults.append(inputLength).append("\t").append(result.length / (float) input.length * 100).append("\n");
                }
                {
                    long beginTime = System.currentTimeMillis();
                    result = comp.decompress(result);
                    long endTime = System.currentTimeMillis();
                    assertArrayEquals(result, input);
                    decompressionTimes.append(inputLength).append("\t").append(endTime - beginTime).append("\n");
                }
            }
        }

        FileUtility.writeFile("target/speedRaportRepetitive.txt", compressionResults.append("\n")
                .append(compressionTimes).append("\n").
                append(decompressionTimes).toString().getBytes());
    }

    @Test
    public void outputSpeedsOnRandomInput() {
        byte byteCodeLength = 10;
        int inputIncrement = 5000;
        int maxInputLength = 100000;

        Compressor comp = new Compressor();

        StringBuilder compressionResults = new StringBuilder();
        StringBuilder compressionTimes = new StringBuilder();
        StringBuilder decompressionTimes = new StringBuilder();

        compressionResults.append("inputLength").append("\t").append("outputPercentageOfOriginal");
        compressionTimes.append("inputLength").append("\t").append("compressionTimeInMs");
        decompressionTimes.append("inputLength").append("\t").append("decompressionTimeInMs");
        {
            compressionResults.append("\n").append("codeLength:\t").append(byteCodeLength).append("\n");
            compressionTimes.append("\n").append("codeLength:\t").append(byteCodeLength).append("\n");
            decompressionTimes.append("\n").append("codeLength:\t").append(byteCodeLength).append("\n");
            for (int inputLength = inputIncrement; inputLength <= maxInputLength; inputLength += inputIncrement) {

                // Generate random bytes
                byte[] input = new byte[inputLength];
                new Random().nextBytes(input);

                byte[] result = null;
                {
                    long beginTime = System.currentTimeMillis();
                    result = comp.compress(input, byteCodeLength);
                    long endTime = System.currentTimeMillis();
                    compressionTimes.append(inputLength).append("\t").append(endTime - beginTime).append("\n");
                    compressionResults.append(inputLength).append("\t").append(result.length / (float) input.length * 100).append("\n");
                }
                {
                    long beginTime = System.currentTimeMillis();
                    result = comp.decompress(result);
                    long endTime = System.currentTimeMillis();
                    assertArrayEquals(result, input);
                    decompressionTimes.append(inputLength).append("\t").append(endTime - beginTime).append("\n");
                }
            }
        }

        FileUtility.writeFile("target/speedRaport.txt", compressionResults.append("\n")
                .append(compressionTimes).append("\n").
                append(decompressionTimes).toString().getBytes());
    }

}
