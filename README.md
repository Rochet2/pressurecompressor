# pressurecompressor
https://www.cs.helsinki.fi/courses/58161/2016/s/a/1

# Get it
- Download from https://github.com/Rochet2/pressurecompressor/releases
- Open terminal and run the program with `java -jar pressurecompressor.jar path/to/FileToCompressOrDecompress`
- files with `.lzw` ending are decompressed automatically

## commands
- *You must execute these commands from terminal in the pressurecompressor subfolder that contains the `pom.xml`*
- `mvn install` compiles
- `mvn test` runs JUnit tests
- `mvn test org.pitest:pitest-maven:mutationCoverage` creates pit raport to `/target/pit-reports/*/index.html`
- `mvn clean | mvn package` creates a publishable jar to `target/pressurecompressor-1.0-SNAPSHOT.jar`
