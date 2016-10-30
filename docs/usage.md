# Usage

## Using the executable

- Open terminal and run the program with `java -jar pressurecompressor.jar` to see the program help.
- Open terminal and run the program with `java -jar pressurecompressor.jar path/to/FileToCompressOrDecompress` to compress or decompress. See program help for more options
- Files with `.lzw` ending are decompressed automatically
- I would not recommend compressing extremely large files. The algorithm matches the theorethical time complexities, but in reality it may not be very efficient.

## Commands for source code

These are commands for the source code. You can run tests, get test coverage reports and generate jar files.
To use these commands you must download the source code from github.
I assume you are using linux have maven installed so that you can use it directly with `mvn` command.

*You must execute these commands from terminal in the pressurecompressor subfolder that contains the `pom.xml`*

- `mvn install` compiles the project
- `mvn test` runs JUnit tests
- `mvn test org.pitest:pitest-maven:mutationCoverage` creates pit test coverage raport to `/target/pit-reports/*/index.html`
- `mvn generate-sources javadoc:javadoc` creates javadoc to `target/site/apidocs`
- `mvn clean | mvn package` creates a publishable self contained jar executable to `target/pressurecompressor.jar`
