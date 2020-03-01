package org.brandonli.sfe.util.algorithms.des;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.BitSet;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.brandonli.sfe.util.algorithms.AlgUtil;

public class TripleDES {

	public static byte[] des_attack(String plaintext, int N_BYTE) throws Exception {

		byte[] encoded_keyOne = AlgUtil.generateRandKey(N_BYTE,  N_BYTE, 8);
		byte[] encoded_keyTwo = AlgUtil.generateRandKey(N_BYTE,  N_BYTE, 8);
		byte[] encoded_keyThree = AlgUtil.generateRandKey(N_BYTE,  N_BYTE, 8);

		SecretKey key1 = new SecretKeySpec(encoded_keyOne, "DES");
		SecretKey key2 = new SecretKeySpec(encoded_keyTwo, "DES");
		SecretKey key3 = new SecretKeySpec(encoded_keyThree, "DES");

		System.out.println(Arrays.toString(key1.getEncoded()));
		System.out.println(Arrays.toString(key2.getEncoded()));
		System.out.println(Arrays.toString(key3.getEncoded()));

		byte[] initVector = new byte[] { 0x10, 0x10, 0x01, 0x04, 0x01, 0x01, 0x01, 0x02 };
		AlgorithmParameterSpec algParamSpec = new IvParameterSpec(initVector);

		Cipher m_encrypter1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
		m_encrypter1.init(Cipher.ENCRYPT_MODE, key1, algParamSpec);

		Cipher m_encrypter2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
		m_encrypter2.init(Cipher.ENCRYPT_MODE, key2, algParamSpec);

		Cipher m_encrypter3 = Cipher.getInstance("DES/CBC/PKCS5Padding");
		m_encrypter3.init(Cipher.ENCRYPT_MODE, key3, algParamSpec);

		Cipher m_decrypter1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
		Cipher m_decrypter2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
		Cipher m_decrypter3 = Cipher.getInstance("DES/CBC/PKCS5Padding");

		byte[] clearText = plaintext.getBytes();

		byte[] encryptedText1 = m_encrypter1.doFinal(clearText);
		byte[] encryptedText2 = m_encrypter2.doFinal(encryptedText1);
		byte[] finalEncryptedText = m_encrypter3.doFinal(encryptedText2);

		BigInteger guess = new BigInteger("0");
		BigInteger N_COMB = new BigInteger("6277101735386680763835789423207666416102355444464034512896");
		System.out.println("All possible combiantions : " + N_COMB);

		BitSet encoding_attack_key1 = new BitSet(8 * N_BYTE);
		encoding_attack_key1.clear();

		BitSet encoding_attack_key2 = new BitSet(8 * N_BYTE);
		encoding_attack_key2.clear();

		BitSet encoding_attack_key3 = new BitSet(8 * N_BYTE);
		encoding_attack_key3.clear();

		byte[] mykey1 = new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };
		byte[] mykey2 = new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };
		byte[] mykey3 = new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };

		for (long i = 0; i < Math.pow(2.0, 56); i++) {

			String tmp = Long.toBinaryString(i);

			int z1 = 0;
			int j1 = tmp.length() - 1;

			SecretKey firstKey = getKey(i, z1, j1, encoding_attack_key1, N_BYTE, mykey1);

			for (int j = 0; j < (int) Math.pow(2.0, 56); j++) {

				int z2 = 0;
				int j2 = tmp.length() - 1;

				SecretKey secondKey = getKey(j, z2, j2, encoding_attack_key2, N_BYTE, mykey2);

				for (int k = 0; k < (int) Math.pow(2.0, 56); k++) {

					int z3 = 0;
					int j3 = tmp.length() - 1;

					SecretKey thirdKey = getKey(k, z3, j3, encoding_attack_key3, N_BYTE, mykey3);

					if (million(guess)) {
						System.out.println("Guess : " + guess + " out of : " + N_COMB + " ------- "
								+ (guess.divide(N_COMB).multiply(new BigInteger("100"))) + "% completed");

					}
					guess.add(BigInteger.ONE);

					m_decrypter1.init(Cipher.DECRYPT_MODE, firstKey, algParamSpec);
					m_decrypter2.init(Cipher.DECRYPT_MODE, secondKey, algParamSpec);
					m_decrypter3.init(Cipher.DECRYPT_MODE, thirdKey, algParamSpec);

					try {

						byte[] text_g = m_decrypter3
								.doFinal(m_decrypter2.doFinal(m_decrypter1.doFinal(finalEncryptedText)));
						String plain_text = new String(text_g);

						if (plain_text.equals(new String(clearText))) {

							System.out.println("Cracked All Keys");
							return plaintext.getBytes();

						}

					} catch (BadPaddingException ee) {

					}

					encoding_attack_key3.clear();

				}

				encoding_attack_key2.clear();

			}

			encoding_attack_key1.clear();

		}

		return null;

	}
	
	public static boolean million(BigInteger val) {
	    if(!val.mod(new BigInteger("1000000")).equals(BigInteger.ZERO))
	        return true;
	    return false;
	}

	public static SecretKey getKey(long i, int z, int j, BitSet encoding_attack_key, int N_BYTE, byte[] key) {

		String tmp = Long.toBinaryString(i);

		while (j >= 0) {

			if (z != 63 && z != 55 && z != 47 && z != 39 && z != 31 && z != 23 && z != 15 && z != 7) {
				if (tmp.charAt(j) == '1') {
					encoding_attack_key.set(z);

				}
				j--;
				z++;
			} else {
				z++;
			}

		}

		for (int k = 0; k < N_BYTE; k++) {

			if (encoding_attack_key.get(k * 8, (k * 8) + 7).cardinality() % 2 != 0) {

				encoding_attack_key.set((k * 8) + 7);

			}
		}

		int len = encoding_attack_key.toByteArray().length;
		key[0] = len >= 8 ? encoding_attack_key.toByteArray()[7] : 0x0;
		key[1] = len >= 7 ? encoding_attack_key.toByteArray()[6] : 0x0;
		key[2] = len >= 6 ? encoding_attack_key.toByteArray()[5] : 0x0;
		key[3] = len >= 5 ? encoding_attack_key.toByteArray()[4] : 0x0;
		key[4] = len >= 4 ? encoding_attack_key.toByteArray()[3] : 0x0;
		key[5] = len >= 3 ? encoding_attack_key.toByteArray()[2] : 0x0;
		key[6] = len >= 2 ? encoding_attack_key.toByteArray()[1] : 0x0;
		key[7] = len >= 1 ? encoding_attack_key.toByteArray()[0] : 0x0;

		SecretKey key_g = new SecretKeySpec(key, "DES");

		return key_g;

	}

	public static void main(String[] args) throws Exception {

		String plaintext = AlgUtil.generateRandomPlainText();
		int n_byte = 32;

		long startTime = System.nanoTime();

		byte[] ris = des_attack(plaintext, 32);
		long estimatedTime = (System.nanoTime() - startTime);

		AlgUtil.printResults(plaintext, estimatedTime, n_byte, ris);
		

	}

}
