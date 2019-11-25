package org.brandonli.sfe.util.algorithms;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES {

	public static void main(String[] args) throws Exception {

		String text = "qwerty";
		
		start(text);

	}

	public static void start(String message) throws Exception {

		final byte[][] encryptedContents = new byte[100][];

		for (int i = 0; i < encryptedContents.length; i++) {

			encryptedContents[i] = encrypt(message);

		}
		
		final long startTime = System.nanoTime(); // Start time
		
		for (int i = 0; i < encryptedContents.length; i++) {
			
			byte[] encryptedText = encryptedContents[i];
			decrypt(encryptedText);
			
		}
		
		final long endTime = System.nanoTime(); // End time

		System.out.println((endTime - startTime) / 1000); // Get the time it took, and then subtract it from the old time.
		// Because there are 1000 nano-seconds in a microsecond, we divide by 1000

	}

	public static byte[] encrypt(String message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}

		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);

		final byte[] plainTextBytes = message.getBytes("utf-8");
		final byte[] cipherText = cipher.doFinal(plainTextBytes);
		// final String encodedCipherText = new sun.misc.BASE64Encoder()
		// .encode(cipherText);

		return cipherText;
	}

	public static String decrypt(byte[] message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}

		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		decipher.init(Cipher.DECRYPT_MODE, key, iv);

		// final byte[] encData = new
		// sun.misc.BASE64Decoder().decodeBuffer(message);
		final byte[] plainText = decipher.doFinal(message);

		return new String(plainText, "UTF-8");
	}

}
