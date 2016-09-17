# pressurecompressor
https://www.cs.helsinki.fi/courses/58161/2016/s/a/1

## commands
- `mvn install` compiles
- `mvn test` runs JUnit tests
- `mvn cobertura:cobertura` creates cobertura raport to `/target/site/cobertura/index.html`
- `mvn test org.pitest:pitest-maven:mutationCoverage` creates pit raport to `/target/pit-reports/*/index.html`
- `mvn clean | mvn package` creates a publishable jar to `target/pressurecompressor-1.0-SNAPSHOT.jar`

