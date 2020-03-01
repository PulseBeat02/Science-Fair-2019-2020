package org.brandonli.sfe.util.algorithms.cc;

import java.util.Random;

import org.brandonli.sfe.util.algorithms.AlgUtil;

public class CaesarCipher {

	public static void main(String[] args) {

		String code = AlgUtil.generateRandomPlainText(); // Testing Password
		start(code); // Start Testing

	}

	public static String encrypt(String s, int k) { // Encrypt a string with a given shift (includes ASCII)
		final char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) { // Loop through each character, shift up by k characters
			arr[i] = (char) ((arr[i] + k) % 128);
		}
		return String.valueOf(arr); // Return new encrypted String
	}

	public static String decrypt(String encrypted, String answer) { // Decrypt a Password (Brute-force)
		final char[] arr = encrypted.toCharArray();

		for (int k = 0; k < 128; k++) { // Loop through each ASCII character, then shift
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (char) ((arr[i] + 128 - k % 128) % 128);
			}

			if (new String(arr).equals(encrypted)) // If the String is found, return the answer
				return answer;

		}

		return String.valueOf(arr); // Cover for errors
	}

	public static void start(String code) {

//		String encryptedContents = "";
		
		float sum = 0;

		for (int i = 0; i < 5; i++) { // Encrypt Contents
			
			String plaintext = AlgUtil.generateRandomPlainText();
			
			System.out.println("==========================================================");
			System.out.println("TEST TRIAL #" + (i + 1));
			System.out.println("==========================================================");

			final Random r = new Random();
			int key = r.nextInt(128);
			plaintext = encrypt(code, key); // Encrypt code many times with random shifts
			
			final long startTime = System.nanoTime(); // Start time
			decrypt(plaintext, code);
			final long endTime = System.nanoTime(); // End time
			
			long time = (endTime - startTime)/1000;
			
			AlgUtil.printResults(plaintext, time, -1, new byte[] {(byte) key});
			
			sum += time;
			
			System.out.println();
			System.out.println();

		}

		// final Random r = new Random();
		//encryptedContents = encrypt(code, r.nextInt(128)); // Encrypt code many times with random shifts

		// Calculating the time it takes for each password to be cracked takes too
		// short, so
		// we calculate the time it takes for it to break 100 passwords.

//		final long startTime = System.nanoTime(); // Start time
//
//		for (int i = 0; i < encryptedContents.length; i++) {
//			// Decrypt all passwords
//			// in the string array
//
//			decrypt(encryptedContents, code); // Decrypt
//
//		}

		//final long endTime = System.nanoTime(); // End time
		
		System.out.println("Average: " + sum/5 + " microseconds"); // Get the time it took, and then subtract it from the old
															// time.
		// Because there are 1000 nano-seconds in a microsecond, we divide by 1000

	}

}
