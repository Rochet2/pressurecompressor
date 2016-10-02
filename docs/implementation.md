#Implementation

The program is used for byte packing. The packing is done by coding all bytes with more than 8 bits.
With the extra bits we can refer to previous sequences of bytes. If the data contains enough repetition, the packing will produce a smaller output than input.

For compressing the algorithm has a dictionary that maps groups of bytes from the input to a number. The dictionary has all the single byte values stored in it in the beginning.
Then the input is traversed through once. During that all sequences of bytes that are not already in the dictionary are added to it and outputted.
If a sequence is already in the dictionary, it is not outputted, but instead the number from the dictionary is outputted.

For decompressing, the dictionary is again used. In the beginning it has all single byte sequences. Then the first byte fetched from it and outputted.
Then as the input is traversed through once, the all sequences of bytes are added to the dictionary and all bytes from input are fetched from the dictionary and outputted.

The bytes are stored as a linked list of bits.
The dictionary is implemented as a static size array which is reinitialized with single byte sequences when it gets full.

The algorithm should have O(n) time complexity. After some analysis it seems that when input grows 10 times, the time spent grows 20 times.
The algorithm has O(n) space complexity.
These complexities come from the facts that the dictionary is fixed size and the length of byte sequences can be limited to a fixed size - meaning it does not depend on input.
Also a linked list is used when growing data structure is needed so there is no looping needed to recrate arrays of data.

The algorithm implementation is a bit stiff. It requires all the data to be in memory at once which is not required by the algorithm in theory.
The dictionary could have an LRU cache instead of clearing it when it is full.
With some experimenting using an LRU cache would result in more optimal compression than dictionary clearing.

The packing algorithm is bad when it has to compress data that has little to no repetition and the repetition is only small sequences.

```
USING LRU CACHE

input: random bytes
compression time: 50ms
original length: 1000
compressed length: 1001
compressed string is 100.100006% of the original

input: random bytes
time spent: 1315ms
original length: 10000
compressed length: 10001
compressed string is 100.01% of the original

input: random bytes
time spent: 27555ms
original length: 100000
compressed length: 100001
compressed string is 100.001% of the original

Results:
When input size multiplies by ten, the time spent is about twenty to thirty times more.
```

- https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch
