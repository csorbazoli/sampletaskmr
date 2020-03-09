package com.arvoia.sampletask.utils;

import java.util.logging.Logger;

import com.arvoia.sampletask.exception.CipherException;

public class SimpleCipherTool {

	private String cipherKey;
	private static final Logger LOG = Logger.getLogger(SimpleCipherTool.class.getName());
	private static final char INDEX_CHAR = 'a';

	public SimpleCipherTool(String key) {
		if (isEmptyString(key)) {
			throw new CipherException("Cipher is blank or null");
		}
		this.cipherKey = key;
	}

	/**
	 * Ciphers the input using cipher key
	 * 
	 * @param str
	 * @return
	 * @throws CipherException
	 */
	public String cipher(String str) throws CipherException {

		return encryptOrDecryptString(true, str);
	}

	/**
	 * De-ciphers the input using cipherkey
	 * 
	 * @param decipheredText
	 * @return
	 * @throws CipherException
	 */
	public String decipher(String decipheredText) throws CipherException {

		return encryptOrDecryptString(false, decipheredText);
	}

	/**
	 * encrypts or decrypts the input string
	 * 
	 * @param encryptFlag
	 * @param inputStr
	 * @return encrypted or decrypted string
	 * @throws CipherException
	 */
	private String encryptOrDecryptString(boolean encryptFlag, String inputStr) throws CipherException {
		if (isEmptyString(inputStr)) {
			throw new CipherException("Input String is blank or empty");
		}
		StringBuilder outputStr = new StringBuilder();
		int keyIndex = 0;
		for (char c : inputStr.toCharArray()) {
			if (keyIndex >= cipherKey.length()) {
				keyIndex = 0;
			}
			if (Character.isLowerCase(c) && Character.isLetter(c)) {
				int offSetCharPos = getCipherOffSetValForEnCryptorDecrypt(encryptFlag, c, keyIndex);
				outputStr.append((char) (offSetCharPos));
			} else {
				outputStr.append(c);
			}
			keyIndex++;
		}
		
		return outputStr.toString();
	}

	/**
	 * Method derives the cipher offset int val for encyption of decruption
	 * 
	 * @param encryptFlag
	 * @param inputChar
	 * @param cipherKeyIndex
	 * @return
	 */
	private int getCipherOffSetValForEnCryptorDecrypt(boolean encryptFlag, char inputChar, int cipherKeyIndex) {

		int offSetCharPos ;
		// Value to shift a=1,b=2 etc
		int valueToShift = (cipherKey.charAt(cipherKeyIndex) - INDEX_CHAR) + 1;
		if (encryptFlag) {
			offSetCharPos = getCharOffSetForEncyption(inputChar, valueToShift);
		} else {
			offSetCharPos = getCharOffSetForDecryption(inputChar, valueToShift);
		}
		return offSetCharPos;
	}

	/**
	 * 
	 * @param input
	 * @param valueToShift
	 * @return
	 */
	private int getCharOffSetForEncyption(char input, int valueToShift) {
		int cipherOffSet = ((input - INDEX_CHAR) + valueToShift) % 26;
		cipherOffSet = cipherOffSet + INDEX_CHAR;
		return cipherOffSet;
	}

	/**
	 * 
	 * @param input
	 * @param valueToShift
	 * @return
	 */
	private int getCharOffSetForDecryption(char input, int valueToShift) {
		int cipherOffSet = (input - INDEX_CHAR - valueToShift) % 26 + INDEX_CHAR;
		if (cipherOffSet <= 96) {
			cipherOffSet = 122 - (97 - cipherOffSet) + 1;
		}
		return cipherOffSet;
	}

	/**
	 * this method guess/finds the possible cipher key combination
	 * 
	 * @param input
	 * @param cipheredText
	 * @return
	 */

	// TODO to be removed
	private String findOrGuessKey(String input, String cipheredText) {
		StringBuilder outputStr = new StringBuilder();
		StringBuilder distinctKeyStr = new StringBuilder();
		for (int i = 0; i < cipheredText.length(); i++) {
			char c = cipheredText.charAt(i);
			char in = input.charAt(i);

			int cipherInt = (c - 'a') - (in - 'a');
			if (cipherInt < 0) {
				cipherInt = 26 + cipherInt;
			}
			outputStr.append(cipherInt != 0 ? (char) ('a' + cipherInt - 1) : (char) ('a' + cipherInt));

		}
		outputStr.chars().distinct().forEach(ch -> 
			distinctKeyStr.append((char) ch)
		);
		LOG.info("Possible Key combination " + outputStr.toString());
		LOG.info("Distinct Key combination " + distinctKeyStr.toString());
		return outputStr.toString();
	}

	private boolean isEmptyString(String inputStr) {
		return inputStr == null || inputStr.trim().length() == 0;
	}
}
