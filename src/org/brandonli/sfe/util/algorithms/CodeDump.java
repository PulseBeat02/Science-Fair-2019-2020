package org.brandonli.sfe.util.algorithms;

public class CodeDump {

	/*
	 * Thread[] bruteForceThreads = new Thread[16];
	 * 
	 * for (int i = 0; i < encryptedContents.length; i++) {
	 * 
	 * final String encryptedMessage = encryptedContents[i];
	 * 
	 * for (int t = 0; t < 16; t++) {
	 * 
	 * System.out.println(t);
	 * 
	 * Thread thread = null;
	 * 
	 * thread = new Thread(new BruteForceThread(encryptedMessage, code, t));
	 * 
	 * bruteForceThreads[t] = thread; bruteForceThreads[t].start();
	 * 
	 * }
	 * 
	 * // decrypt(cipher, encryptedMessage, code);
	 * 
	 * }
	 * 
	 * final long endTime = System.nanoTime();
	 * 
	 * System.out.println((endTime - startTime) / 1000);
	 * 
	 * Blowfish class BruteForceThread implements Runnable {
	 * 
	 * private Cipher c; private String encryptedMessage; private String message;
	 * private int threadID;
	 * 
	 * BruteForceThread(String en, String m, int id) throws
	 * NoSuchAlgorithmException, NoSuchPaddingException {
	 * 
	 * this.c = Cipher.getInstance("Blowfish"); this.encryptedMessage = en;
	 * this.setMessage(m); this.threadID = id;
	 * 
	 * }
	 * 
	 * @Override public void run() {
	 * 
	 * try { BlowFish.decrypt(c, encryptedMessage, encryptedMessage, threadID); }
	 * catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * Thread.currentThread().interrupt(); return;
	 * 
	 * }
	 * 
	 * public String getMessage() { return message; }
	 * 
	 * public void setMessage(String message) { this.message = message; }
	 * 
	 * }
	 * 
	 * public static void decrypt(Cipher c, String encryptedMessage, String message,
	 * int threadID) throws IllegalBlockSizeException, BadPaddingException,
	 * InvalidKeyException { // 32 bit
	 * 
	 * // 255 is equivalent to binary of "11111111"
	 * 
	 * // Assuming we are cracking Blowfish 32-bit, there are 2^32 combinations //
	 * We could split this into four loops, each looping from 0 to 255 in order //
	 * to get all combinations. (First byte, second byte, third byte, fourth byte)
	 * 
	 * int guess = 0;
	 * 
	 * for (int b0 = threadID * 16; b0 < (threadID + 1) * 16; b0++) {
	 * 
	 * for (int b1 = 0; b1 < 256; b1++) {
	 * 
	 * for (int b2 = 0; b2 < 256; b2++) {
	 * 
	 * for (int b3 = 0; b3 < 256; b3++) {
	 * 
	 * if (isFound) return;
	 * 
	 * byte[] key = { (byte) b0, (byte) b1, (byte) b2, (byte) b3 }; // Get the byte
	 * array for the key
	 * 
	 * // System.out.println("Thread: " + threadID + " : " + Arrays.toString(key));
	 * 
	 * c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "Blowfish")); // Decrypt
	 * using the key
	 * 
	 * byte[] decrypted = {};
	 * 
	 * try {
	 * 
	 * decrypted = c.doFinal(encryptedMessage.getBytes()); // Start decryption
	 * 
	 * } catch (BadPaddingException e) {
	 * 
	 * continue;
	 * 
	 * }
	 * 
	 * if (Arrays.equals(decrypted, message.getBytes())) { // If the message equals
	 * to the original // // message
	 * 
	 * isFound = true;
	 * 
	 * System.out.println("Found Plain Text"); // Say that you printed the password
	 * return; // Return from the function
	 * 
	 * }
	 * 
	 * guess++;
	 * 
	 * if (guess % 10 == 0) {
	 * 
	 * System.out.println("Guess : " + guess + " out of : " + "4294967296");
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
