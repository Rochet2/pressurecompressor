#Testing document

The algorithm has been tested on and off with JUnit and ad hoc tests.

The non JUnit testing:  
Large random inputs of bytes were given and compressed.
The compression ratio was close to none with dictionary having LRU cache and with dictionary clearing the output is about 150% of input.
When random inputs of bytes with fewer variation were used for compression, the output was around 60% of original.
Such stress testing with large random inputs revealed hidden bugs in the algorithm.
As an example of tests carried out: generate 100000 random bytes and compress and uncompress them.
The time complexity was measuredby running such tests by starting from an input of 100 random bytes and increasing the input size by multiplying it with 10.
The memory consumption was  measured by running the program with similar tests as time complexity was and the IDE's memory management statistics were viewed.
The program was also profiled some with the IDE's (netbeans) profiling tools and the bottleneck seems to be the dictionary's methods for getting indexes of byte sequences.

JUnit testing:  
Many parts of the program were tested with JUnit tests.
These tests had small text compressed and uncompressed and the output was verified to be the same as input.
Also parts such as the dictionary and other container had tests for adding and removing elements and using other member functions.
JUnit tests were useful in finding bugs with character encoding and during large refactorings of the code.
