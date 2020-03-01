# **Science-Fair-2019-2020 - Cybersecurity**

## **Introduction**

For this year's Regional Science Fair project I will be working on a project related to Cybersecurity. Cybersecurity is a big concern in the modern world, as many large companies such as [Amazon](https://en.wikipedia.org/wiki/Amazon_%28company%29), [Google](https://en.wikipedia.org/wiki/Google), and [Facebook](https://en.wikipedia.org/wiki/Facebook), are vulnerable to attacks by hackers. Consider the following example, on 2013 to 2014, **3 billion Yahoo Accounts** were breached. The information revealed included real names, address, dates of births, and telephone numbers. The accounts were encrypted by **[Bcrypt](https://en.wikipedia.org/wiki/Bcrypt)**, an algorithm dervived by [Blowfish](https://en.wikipedia.org/wiki/Blowfish_%28cipher%29) Encryption. Therefore, in order to test the security of many different encryption standards, I programmed many applications to brute force modern encryption standards, such as [Advanced Encryption Standard](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard) (AES), [Data Encryption Standard](https://en.wikipedia.org/wiki/Data_Encryption_Standard) (DES), Blowfish, and [Caesar Cipher](https://en.wikipedia.org/wiki/Caesar_cipher). To summarize what each of these algorithms are:

- **Advanced Encryption Standard (AES)**, is a very strong Encryption Standard used by many people today. Created by Vincent Rijmen, the size of the key usually ranges around 16 bytes to 32 bytes. The algorithm (to simplify), includes putting the plaintext into a matrix, implementing an IV, and preforming rounds on the matrix (such as moving a byte up, down, etc). Currently, it takes long than the universe to crack AES-128 even with the strongest computers, so it a very secure way to store your data. AES-256 is considered unbreakable, for a practical purposes. (Our definition of unbreable here means so long that when you study AES-256, you shouldn't consider trying to break it).
- **Data Encryption Standard (DES)**, is another strong encryption standard, created by IBM. The key sizes are in multiples of 8 bytes, however, one byte is always used as a parity bit, or in other words, to check to make sure the key itself is correct. There are many forms of DES, including DES itself, 2DES, 3DES with two keys, and 3DES with three keys. 2DES, however, is almost the same as DES, and isn't more secure because of the following example, it takes 2^56  operations to encrypt from A to B, and another to encrypt from Z to B, so it takes around  2^57 operations total. 3DES, however, is a standard. The algorithm is relatively simple, divide the message into blocks of 64 bits, and encrypt each block.
- **Blowfish**, is another secure key cipher, designed by Bruce Schneir. The key sizes vary from 32 bits to 448 bits. There are other forms of Blowfish, such as TwoFish, and ThreeFish. The algorithm is based on the use of S-Boxes.
- **Caesar Cipher**, is a weak cipher composed by Julius Caesar. You probably heard of the implementation before, such as reversing the alphabet of **ABCDEFGHIJKLMNOPQRSTUVWXYZ** to **ZYXWVUTSRQPONMLKJIHGFEDCBA** and rewriting the message with the new letters. Instead each letter is given a shift, and this shift is applied to each letter of the message. This produces the new ciphertext. The algorithm is so weak, however, due to only 26 possible shifts.

## **Tests**

I will be preforming tests with short sized keys, For example, I will preform tests with:

- 8 Bit Keys
- 16 Bit Keys
- 24 Bit Keys
- 32 Bit Keys

Because my computer cannot handle any larger sized keys, these will be the keys I will be analyzing.
All tests will be run 5 times, each with different plaintexts, and different keys. I will track the time in microseconds and print it out to a file.

## **Code**

Take a look at [here](https://github.com/PulseBeat02/Science-Fair-2019-2020/blob/master/src/org/brandonli/sfe/util/algorithms/RunTest.java), to see how I start my program to run all tests.

 **Advanced Encryption Standard**
AES: [AES.java](https://github.com/PulseBeat02/Science-Fair-2019-2020/blob/master/src/org/brandonli/sfe/util/algorithms/aes/AES.java)

 **Data Encryption Standard**
DES: [DES.java](https://github.com/PulseBeat02/Science-Fair-2019-2020/blob/master/src/org/brandonli/sfe/util/algorithms/des/DES.java)
TripleDES: [TripleDES.java](https://github.com/PulseBeat02/Science-Fair-2019-2020/blob/master/src/org/brandonli/sfe/util/algorithms/des/TripleDES.java)
TwoDES: [TwoDES.java](https://github.com/PulseBeat02/Science-Fair-2019-2020/blob/master/src/org/brandonli/sfe/util/algorithms/des/TwoDES.java)

**Blowfish**
Blowfish: [Blowfish.java](https://github.com/PulseBeat02/Science-Fair-2019-2020/blob/master/src/org/brandonli/sfe/util/algorithms/bf/BlowFish.java)

**Caesar Cipher**
Caesar Cipher: [CaesarCipher.java](https://github.com/PulseBeat02/Science-Fair-2019-2020/blob/master/src/org/brandonli/sfe/util/algorithms/cc/CaesarCipher.java)

## **Images**

![Logging](https://i.imgur.com/YLP7RlB.png)
Logging progress on brute forcing AES-32.

