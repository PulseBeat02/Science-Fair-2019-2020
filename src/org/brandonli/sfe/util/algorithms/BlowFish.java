package org.brandonli.sfe.util.algorithms;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class BlowFish {

	public static volatile boolean isFound = false;

	public static Cipher cipher;

	public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException {

		final String code = "qwerty"; // Password

		start(code); // Start Decyption

	}

	public static void start(String code) throws IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

		cipher = Cipher.getInstance("Blowfish");

		final String[] encryptedContents = new String[1];

		for (int i = 0; i < encryptedContents.length; i++) { // Encrypt Contents
			encryptedContents[i] = encrypt(cipher, code);
		}

		final long startTime = System.nanoTime();

		Thread[] bruteForceThreads = new Thread[16];

		for (int i = 0; i < encryptedContents.length; i++) {

			final String encryptedMessage = encryptedContents[i];

			for (int t = 0; t < 16; t++) {

				System.out.println(t);

				Thread thread = new Thread(new BruteForceThread(encryptedMessage, code, t));
				bruteForceThreads[t] = thread;
				bruteForceThreads[t].start();

			}

			// decrypt(cipher, encryptedMessage, code);

		}

		final long endTime = System.nanoTime();

		System.out.println((endTime - startTime) / 1000);

	}

	public static String encrypt(Cipher c, String message) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		final KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish"); // Get Blowfish Instance
		final SecretKey secretkey = keygenerator.generateKey(); // Generate a key
		final SecretKeySpec key = new SecretKeySpec(secretkey.getEncoded(), "Blowfish");

		c.init(Cipher.ENCRYPT_MODE, key); // Encrypt the message with the key
		final byte[] encrypted = c.doFinal(message.getBytes()); // Get the byte array

		return bytesToHex(encrypted);

	}

	public static void decrypt(Cipher c, String encryptedMessage, String message, int threadID)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException { // 32 bit

		// 255 is equivalent to binary of "11111111"

		// Assuming we are cracking Blowfish 32-bit, there are 2^32 combinations
		// We could split this into four loops, each looping from 0 to 255 in order
		// to get all combinations. (First byte, second byte, third byte, fourth byte)

		for (int b0 = threadID * 16; b0 < (threadID + 1) * 16; b0++) {

			for (int b1 = 0; b1 < 256; b1++) {

				for (int b2 = 0; b2 < 256; b2++) {

					for (int b3 = 0; b3 < 256; b3++) {

						if (isFound)
							return;

						byte[] key = { (byte) b0, (byte) b1, (byte) b2, (byte) b3 }; // Get the byte array for the key

						System.out.println("Thread: " + threadID + " : " + Arrays.toString(key)); // Print the current
																									// test case. (You
																									// can remove this
																									// for efficiency)

						c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "Blowfish")); // Decrypt using the key

						byte[] decrypted = {};

						try {

							decrypted = c.doFinal(encryptedMessage.getBytes()); // Start decryption

						} catch (BadPaddingException e) {

							continue;

						}

						if (Arrays.equals(decrypted, message.getBytes())) { // If the message equals to the original //
																			// message

							isFound = true;

							System.out.println("I Found the Text"); // Say that you printed the password
							return; // Return from the function

						}

					}

				}

			}

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

class BruteForceThread implements Runnable {

	private Cipher c;
	private String encryptedMessage;
	private String message;
	private int threadID;

	BruteForceThread(String en, String m, int id) throws NoSuchAlgorithmException, NoSuchPaddingException {

		this.c = Cipher.getInstance("Blowfish");
		;
		this.encryptedMessage = en;
		this.message = m;
		this.threadID = id;

	}

	@Override
	public void run() {

		try {
			BlowFish.decrypt(c, encryptedMessage, encryptedMessage, threadID);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.currentThread().interrupt();
		return;

	}

}
