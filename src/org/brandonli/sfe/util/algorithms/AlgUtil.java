package org.brandonli.sfe.util.algorithms;

import java.util.Arrays;
import java.util.Random;

public class AlgUtil {

	public static byte[] concatenate(byte[] first, byte[] second) {
		byte[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	public static void printResults(String plaintext, long time, int N_BYTE, byte[] key) {

		System.out.println("==========================================================");
		System.out.println("RESULTS");
		System.out.println("==========================================================");
		System.out.println("PLAINTEXT (Original Text): " + plaintext);
		System.out.println("ESTIMATED TIME IN MICROSECONDS: " + time);
		System.out.println("NFREE BYTE KEY SIZE: " + N_BYTE);
		System.out.println("KEY: " + Arrays.toString(key));

	}

	public static String generateRandomPlainText() {

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String str = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return str;

	}

	public static byte hexStringToByte(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		
		if (b.length == 0) return 0x0;
		
		return b[0];
	}

	public static String even(String s) {

		if (s.length() % 2 != 0) {

			("0" + s).substring(s.length());

		}

		return s;

	}

	public static byte[] generateRandKey(int bytes, int index, int sizeKey) {

		byte[] key = new byte[sizeKey];

		Random rand = new Random();

		for (int i = key.length - index; i < key.length; i++) {

			String r = Integer.toHexString(rand.nextInt(0x7F) + 0x1);

			if (r.equals("7"))
				r = "8";

			key[i] = hexStringToByte(r);

		}

		return key;

	}

}
