package com.rightprice.auth.util;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

@Component
public class EcrptDerptjava {

	// public abstract void print();

	public static String encrypt(String key, String initVector, String value) {
		byte[] encrypted = null;
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			encrypted = cipher.doFinal(value.getBytes());

			return DatatypeConverter.printBase64Binary(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return DatatypeConverter.printBase64Binary(encrypted);
	}

	// public static String decrypt(String key, String initVector, String
	// encrypted) {
	// try {
	// IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	// SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	//
	// Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	// cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	//
	// byte[] original =
	// cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));
	//
	// return new String(original);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	//
	// return null;
	// }

	public static String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Key keys = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
