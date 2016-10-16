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


## Pseudocode O analysis
Compression:
```
N = input length
BL = byte length
A = base character set size
M = dictionary size = 2^(byte length)

dictionary = makeDictionary() // O(M+A)
sequence = {} // O(1)
for (byte in input) // O(N)*
{
  if dictionary.get(sequence+byte) // O(M)
    sequence = sequence+byte // O(1)
  else
    output(dictionary.get(sequence)) // O(M)
    dictionary.add(sequence+byte) // O(A)
    sequence = byte // O(1)
}
return getOutput() // O(N)

Total: O(M+A+N*(2*M+A)+N) = O((M+A)*N+N+M+A) = O((M+A+1)*N) = O(N)
```

When calculating total we leave out the constants
Then we can see that N is really dependent on predefined constants M and BL as multipliers
Such multipliers do not affect the time complexity so we can remove them. Result is O(N) time complexity for compression

Decompression:
```

N = input length

BL = byte length
A = base character set size
M = dictionary size = 2^BL

bitStorage(input) // O(N)
makeDictionary() // O(A)
prevCode = bitStorage.read() // O(1)
sequence = dictionary.get(prevCode) // O(M)
output(sequence) // O(1)
for (code in bitStorage) // O(N)*
{
  if dicitonary.get(code) // O(1)
    temp = dictionary.get(prevCode)+dicitonary.get(code)[0] // O(1) O(1)
    output(dicitonary.get(code)) // O(1)
    dictionary.add(temp) // O(A)
  else
    temp = dictionary.get(prevCode)+dictionary.get(prevCode)[0] // O(1) O(1)
    output(dictionary.get(prevCode)+dictionary.get(prevCode)[0]) // O(1)
    dictionary.add(temp) // O(A)
  prevCode = code // O(1)
}
return getOutput() // O(2*N)

Total: O(N+A+M+N*A+2*N) = O((A+3)*N+M+A) = O(A*N) = O(N)
```
Even though the `O(A*N)` looks bad, the algorithm only does the O(A) operation when the dictionary is full (every 2^BLth operation) so it is not as bad as it looks.
I just simplified the calculations a bit because it does not really matter.
End result is that the algorithm for decompressing is O(N)
