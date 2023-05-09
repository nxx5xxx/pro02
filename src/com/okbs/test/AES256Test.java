package com.okbs.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.okbs.service.AES256;

public class AES256Test {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidParameterSpecException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
		String testPw = "1234";
		String key = "%04x";
		//%x 16진수 ->%와 x사이에 숫자넣으면 접두사와함께 출력됨 그방식을 이용한 16진수 출력방식 
		//MD5방식
		System.out.println("MD5 : " + testPw +" 암호화 :" + AES256.md5(testPw));
		//sha256방식
		System.out.println("sha256 : " + testPw + " 암호화 : " + AES256.sha256(testPw));

		//AES256방식
		String encText = AES256.encryptAES256(testPw, key);
		System.out.println("AES256 encText (암호화) : "+encText);
		String desText = AES256.decryptAES256(encText, key);
		System.out.println("AES256 desText (복호화) : "+desText);
		
		String admin = AES256.encryptAES256("1234", key);
		System.out.println("AES256 encText (암호화) : "+admin);
		String admindes = AES256.decryptAES256(encText, key);
		System.out.println("AES256 desText (복호화) : "+admindes);
		
		String test2 = AES256.encryptAES256("2345", key); 
		System.out.println("2345 (암호화) : "+test2);
		String test2Dec = AES256.decryptAES256(test2, key);
		System.out.println("2345암호화 된것을 복호화 : "+test2Dec);
		
		
	}

}
