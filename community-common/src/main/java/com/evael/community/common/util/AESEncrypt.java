package com.evael.community.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncrypt {

	public static String encrypt(String input, String key) {
		key = getKey(key);
		byte[] crypted = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			crypted = cipher.doFinal(input.getBytes());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return new String(Base64.getEncoder().encode(crypted));
	}

	public static String decrypt(String input, String key) {
		key = getKey(key);
		byte[] output = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			output = cipher.doFinal(Base64.getDecoder().decode(input));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return new String(output);
	}
	private static String getKey(String key) {
		if (key == null) {
			// TODO throw exception
		}
	
		if (key.length() > 16) {
			key = key.substring(0, 16);
		}
		return key;
	}

	
}
