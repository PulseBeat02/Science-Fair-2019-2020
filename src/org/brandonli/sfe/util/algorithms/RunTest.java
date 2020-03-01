package org.brandonli.sfe.util.algorithms;

import java.io.PrintStream;

import org.brandonli.sfe.util.algorithms.aes.AES;
import org.brandonli.sfe.util.algorithms.bf.BlowFish;
import org.brandonli.sfe.util.algorithms.cc.CaesarCipher;
import org.brandonli.sfe.util.algorithms.des.DES;

public class RunTest {

	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub

		PrintStream fileOut = new PrintStream("./encryption.log");
		System.setOut(fileOut);


		System.out.println();

		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("Running Tests for All Encryption");
		System.out.println("+++++++++++++++++++++++++++++++++++");

		System.out.println();

		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("Running Test For Random Plaintext");
		System.out.println("+++++++++++++++++++++++++++++++++++");

		System.out.println();

		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("AES");
		System.out.println("+++++++++++++++++++++++++++++++++++");

		System.out.println("AES (8-bit)");
		AES.start(1);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("AES (16-bit)");
		AES.start(2);

		System.out.println();
		System.out.println("-----------------------------------------------");

		System.out.println("AES (24-bit)");
		AES.start(3);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println();

		System.out.println("AES (32-bit)");
		AES.start(4);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		
		System.out.println("Higher Versions not Supported: Too Much Time");

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("Blowfish");
		System.out.println("+++++++++++++++++++++++++++++++++++");

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("Blowfish (8-bit)");
		BlowFish.start(1);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("Blowfish (16-bit)");
		BlowFish.start(2);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("Blowfish (24-bit)");
		BlowFish.start(3);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("Blowfish (32-bit)");
		BlowFish.start(4);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		
		System.out.println("Higher Versions not Supported: Too Much Time");

//		System.out.println("Blowfish (64-bit)");
//		System.out.println(
//				"Blowfish 64-bit is not able to be brute-forced by using Java and a home computer. Base 64 introduces many issues such as Bithday Attack, however, it is still very secure. Further implementations (including TwoFish and ThreeFish), help extend the security. Some implementations even support up to 4096 bits!");

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("Caesar Cipher");
		System.out.println("+++++++++++++++++++++++++++++++++++");

		CaesarCipher.start("qwerty");

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("DES");
		System.out.println("+++++++++++++++++++++++++++++++++++");

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("DES (8-bit)");
		DES.start(1);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("DES (16-bit)");
		DES.start(2);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("DES (24-bit)");
		DES.start(3);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

		System.out.println("DES (32-bit)");
		DES.start(4);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		
		System.out.println("Higher Versions not Supported: Too Much Time");

//		System.out.println("DES (64-bit)");
//		System.out.println("DES-64 Takes Over Two Millenniums to break with one computer, way too much key space!");
//		// DES.start(8, 64);
//
//		System.out.println();
//		System.out.println("-----------------------------------------------");
//		System.out.println();
//
//		System.out.println("2DES (128-bit)");
//		System.out.println(
//				"DES-128 Takes Over 164646653302 Aeons to Brute-Force, however has key faults, (One Aeon is 10 * 10^9)");
//		System.out.println("For example, consider A (plaintext) -> B (1st Encryption) -> Z (2nd Encryption)");
//		System.out.println(
//				"It takes 2^56 operations to get from A to B, and 2^56 more to get from Z to B. Find common value. and done.");
//		System.out.println("This takes 2^56 + 2^56 operations or 2^57 keys");
//
//		// TwoDES.main(args);
//
//		System.out.println();
//		System.out.println("-----------------------------------------------");
//		System.out.println();
//
//		System.out.println("3DES (192-bit)");
//		System.out.println(
//				"This is a standard. A meet in the middle attack would reduce it to 2^112, but it is still very secure. It takes even longer to break, however.");
//		// TripleDES.main(args);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();

	}

}
