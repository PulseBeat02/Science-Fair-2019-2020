package org.brandonli.sfe.util.algorithms.des;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.brandonli.sfe.util.algorithms.AlgUtil;

import java.util.Arrays;
import java.util.BitSet;

public class DES {

	public static void main(String[] args) throws Exception {

		start(4);

	}

	public static byte[] des_attack(String plaintext, int N_BYTE) throws Exception {

		byte[] encoded_key = AlgUtil.generateRandKey(N_BYTE, N_BYTE, 8);
		
		System.out.println(Arrays.toString(encoded_key));

		SecretKey key = new SecretKeySpec(encoded_key, "DES");

		byte[] initVector = new byte[] { 0x10, 0x10, 0x01, 0x04, 0x01, 0x01, 0x01, 0x02 };
		AlgorithmParameterSpec algParamSpec = new IvParameterSpec(initVector);

		Cipher m_encrypter = Cipher.getInstance("DES/CBC/PKCS5Padding");
		m_encrypter.init(Cipher.ENCRYPT_MODE, key, algParamSpec);

		Cipher m_decrypter = Cipher.getInstance("DES/CBC/PKCS5Padding");

		byte[] clearText = plaintext.getBytes();

		byte[] encryptedText = m_encrypter.doFinal(clearText);

		int guess = 0;
		long N_COMB = (long) Math.pow(2.0, (N_BYTE) * 7);
		System.out.println(N_COMB);
		System.out.println("All possible combiantions : " + N_COMB);

		BitSet encoding_attack_key = new BitSet(8 * N_BYTE);
		encoding_attack_key.clear();
		byte[] mykey = new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };

		for (long i = 0; i < N_COMB; i++) {

			String tmp = Long.toBinaryString(i);

			int z = 0;
			int j = tmp.length() - 1;
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

			if (guess % 1000000 == 0) {
				System.out.println("Guess : " + guess + " out of : " + N_COMB + " ------- "
						+ 100 * (guess + 0.0) / N_COMB + "% completed");

			}
			guess++;

			int len = encoding_attack_key.toByteArray().length;
			mykey[0] = len >= 8 ? encoding_attack_key.toByteArray()[7] : 0x0;
			mykey[1] = len >= 7 ? encoding_attack_key.toByteArray()[6] : 0x0;
			mykey[2] = len >= 6 ? encoding_attack_key.toByteArray()[5] : 0x0;
			mykey[3] = len >= 5 ? encoding_attack_key.toByteArray()[4] : 0x0;
			mykey[4] = len >= 4 ? encoding_attack_key.toByteArray()[3] : 0x0;
			mykey[5] = len >= 3 ? encoding_attack_key.toByteArray()[2] : 0x0;
			mykey[6] = len >= 2 ? encoding_attack_key.toByteArray()[1] : 0x0;
			mykey[7] = len >= 1 ? encoding_attack_key.toByteArray()[0] : 0x0;

			SecretKey key_g = new SecretKeySpec(mykey, "DES");
			m_decrypter.init(Cipher.DECRYPT_MODE, key_g, algParamSpec);
			try {
				byte[] text_g = m_decrypter.doFinal(encryptedText);
				String plain_text = new String(text_g);

				if (plain_text.equals(new String(clearText))) {

					return mykey;

				}

			} catch (BadPaddingException ee) {

			}

			encoding_attack_key.clear();

		}
		return null;
	}

	public static void start(int n_byte) throws Exception {

//		int nbyte = 1;
//		byte[] b = generateRandomKey(8);

//		int nbyte = 2;
//		byte[] b = generateRandomKey(16);

//		int nbyte = 3;
//		byte[] b = generateRandomKey(32);

//		int nbyte = 4;

		float sum = 0;

		for (int repeat = 0; repeat < 5; repeat++) {
			
			String plaintext = AlgUtil.generateRandomPlainText();
			
			System.out.println("==========================================================");
			System.out.println("TEST TRIAL #" + (repeat + 1));
			System.out.println("==========================================================");

			long startTime = System.nanoTime();

			byte[] ris = des_attack(plaintext, n_byte);
			long estimatedTime = (System.nanoTime() - startTime)/1000;
			
			AlgUtil.printResults(plaintext, estimatedTime, n_byte, ris);

			sum += estimatedTime;

			System.out.println();
			System.out.println();

		}

		System.out.println("Average: " + sum / 5 + " microseconds");
		System.out.println();

	}

}
