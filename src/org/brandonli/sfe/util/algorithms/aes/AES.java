package org.brandonli.sfe.util.algorithms.aes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.brandonli.sfe.util.algorithms.AlgUtil;

public class AES {

	public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {

		start(4); // Start Decyption // N_BYTE

	}

	public static void start(int N_BYTE) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

		float sum = 0;

		System.out.println();

		for (int i = 0; i < 5; i++) {

			String plaintext = AlgUtil.generateRandomPlainText();

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			byte[] encoded_key = AlgUtil.generateRandKey(N_BYTE, N_BYTE, 16);

			SecretKey key = new SecretKeySpec(encoded_key, "AES");

			Cipher m_encrypter = Cipher.getInstance("AES/ECB/PKCS5Padding");
			m_encrypter.init(Cipher.ENCRYPT_MODE, key);

			final byte[] encrypted = m_encrypter.doFinal(plaintext.getBytes()); // Get the byte array

			for (int j = 0; j < N_BYTE; j++) {

				encrypted[j] = 0;

			}

			String ciphertext = bytesToHex(encrypted);

			System.out.println("TEST TRIAL #" + (i + 1));

			long startTime = System.nanoTime();
			aes_bruteforce(cipher, ciphertext, plaintext, N_BYTE);
			long endTime = System.nanoTime();

			long estimatedTime = (endTime - startTime) / 1000;

			AlgUtil.printResults(plaintext, estimatedTime, N_BYTE, key.getEncoded());

			sum += estimatedTime;

			System.out.println();
			System.out.println();

		}

		System.out.println();
		System.out.println("Average: " + sum / 5 + " microseconds");

	}

	public static void aes_bruteforce(Cipher c, String ciphertext, String message, int N_BYTE)
			throws IllegalBlockSizeException, InvalidKeyException {

		long guess = 0;

		long N_COMB = (long) Math.pow(2, N_BYTE * 8);

		BitSet encoding_attack_key = new BitSet(32 * N_BYTE);
		encoding_attack_key.clear();

		byte[] mykey = new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };

		for (long i = 0; i < N_COMB; i++) {

			String tmp = Long.toBinaryString(i);

			int j = tmp.length() - 1;
			int z = 0;
			while (j >= 0) {
				if (tmp.charAt(j) == '1') {
					encoding_attack_key.set(z);

				}
				j--;
				z++;

			}

			if (guess % 1000000 == 0) {
				System.out.println("Guess : " + guess + " out of : " + N_COMB + " ------- "
						+ 100 * (guess + 0.0) / N_COMB + "% completed");

			}
			guess++;

			int len = encoding_attack_key.toByteArray().length;
			mykey[0] = len >= 16 ? encoding_attack_key.toByteArray()[15] : 0x0;
			mykey[1] = len >= 15 ? encoding_attack_key.toByteArray()[14] : 0x0;
			mykey[2] = len >= 14 ? encoding_attack_key.toByteArray()[13] : 0x0;
			mykey[3] = len >= 13 ? encoding_attack_key.toByteArray()[12] : 0x0;
			mykey[4] = len >= 12 ? encoding_attack_key.toByteArray()[11] : 0x0;
			mykey[5] = len >= 11 ? encoding_attack_key.toByteArray()[10] : 0x0;
			mykey[6] = len >= 10 ? encoding_attack_key.toByteArray()[9] : 0x0;
			mykey[7] = len >= 9 ? encoding_attack_key.toByteArray()[8] : 0x0;
			mykey[8] = len >= 8 ? encoding_attack_key.toByteArray()[7] : 0x0;
			mykey[9] = len >= 7 ? encoding_attack_key.toByteArray()[6] : 0x0;
			mykey[10] = len >= 6 ? encoding_attack_key.toByteArray()[5] : 0x0;
			mykey[11] = len >= 5 ? encoding_attack_key.toByteArray()[4] : 0x0;
			mykey[12] = len >= 4 ? encoding_attack_key.toByteArray()[3] : 0x0;
			mykey[13] = len >= 3 ? encoding_attack_key.toByteArray()[2] : 0x0;
			mykey[14] = len >= 2 ? encoding_attack_key.toByteArray()[1] : 0x0;
			mykey[15] = len >= 1 ? encoding_attack_key.toByteArray()[0] : 0x0;

			SecretKey key_g = new SecretKeySpec(mykey, "AES");
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key_g.getEncoded(), "AES"));
			try {
				byte[] text_g = c.doFinal(ciphertext.getBytes());
				String plain_text = new String(text_g);

				if (plain_text.equals(message)) {

					return;

				}

			} catch (BadPaddingException ee) {

			}

			encoding_attack_key.clear();

		}

	}

	public static String bytesToHex(byte[] data) { // A function used so I can print bytes to hex
		if (data == null) {
			return null;
		} else {
			final int len = data.length;
			String str = "";
			for (int i = 0; i < len; i++) {
				if ((data[i] & 0xFF) < 16)
					str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
				else
					str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
			}
			return str.toUpperCase();
		}
	}

}
