package com.sglj.fbf.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Random;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 加密处理工具类
 * @author guanhongwei
 *
 */
public class EncryptUtil {

	private static Log logger = LogFactory.getLog(EncryptUtil.class);

	/** RSA最大加密明文大小 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** RSA最大解密密文大小 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 接收端解密及验证签名
	 * 
	 * @param data
	 *            加密因子
	 * @param sign
	 *            需要验证的数字签名
	 * @param publicKeyCerFilePath
	 * @return
	 */
	public static boolean verify(String data, String sign, String publicKeyCerFilePath) {
		boolean flag = false;

		try {
			PublicKey publicKey = getPublicKey(publicKeyCerFilePath);

			Signature signature = Signature.getInstance("SHA1WithRSA"); // SHA1WithRSA：用SHA算法进行签名，用RSA算法进行加密
			signature.initVerify(publicKey);
			signature.update(data.getBytes("UTF-8"));

			byte[] bytes = Base64.decodeBase64(sign);
			flag = signature.verify(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("验证密文错误", e);
		}

		return flag;
	}

	private static PublicKey getPublicKey(String publicKeyCerFilePath) throws Exception {
		FileInputStream ksfis = new FileInputStream(publicKeyCerFilePath);
		BufferedInputStream ksbis = new BufferedInputStream(ksfis);

		CertificateFactory factory = CertificateFactory.getInstance("X.509");
		X509Certificate certificate = (X509Certificate) factory.generateCertificate(ksbis);
		PublicKey publicKey = certificate.getPublicKey();
		return publicKey;
	}

	/**
	 * 发送端签名及对签名进行加密
	 * 
	 * @param data
	 * @param privateKeyPfxFilePath
	 * @param privateKeyAlias
	 * @param keyPwd
	 * @return
	 */
	public static String sign(String data, String privateKeyPfxFilePath, String privateKeyAlias, String keyPwd) {
		try {
			PrivateKey privateKey = getPrivateKey(privateKeyPfxFilePath, privateKeyAlias, keyPwd);

			Signature signature = Signature.getInstance("SHA1WithRSA"); // SHA1WithRSA：用SHA算法进行签名，用RSA算法进行加密
			signature.initSign(privateKey);
			signature.update(data.getBytes("UTF-8"));

			Base64 base64 = new Base64();
			byte[] bytes = base64.encode(signature.sign());

			return new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加密异常", e);
		}

		return null;
	}

	private static PrivateKey getPrivateKey(String privateKeyPfxFilePath, String privateKeyAlias, String keyPwd) throws Exception {
		FileInputStream ksfis = new FileInputStream(privateKeyPfxFilePath);
		BufferedInputStream ksbis = new BufferedInputStream(ksfis);

		KeyStore keyStore = KeyStore.getInstance("PKCS12"); // 密钥仓库
		keyStore.load(ksbis, keyPwd.toCharArray()); // 读取密钥仓库
		PrivateKey privateKey = (PrivateKey) keyStore.getKey(privateKeyAlias, keyPwd.toCharArray());// 从密钥仓库得到私钥
		return privateKey;
	}

	public static String MD5Encode(String data) {
		try {
			byte[] bytes = MessageDigest.getInstance("MD5").digest(data.getBytes("UTF-8"));
			char[] chars = Hex.encodeHex(bytes);

			return new String(chars);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public static String RSAEncode(String data, String privateKeyPfxFilePath, String privateKeyAlias, String keyPwd) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");

			PrivateKey privateKey = getPrivateKey(privateKeyPfxFilePath, privateKeyAlias, keyPwd);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);

			byte[] bytes = RSAEncode(data.getBytes("UTF-8"), cipher);
			Base64 base64 = new Base64();
			return new String(base64.encode(bytes));

		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * RSA加密明文最大长度117字节，解密要求密文最大长度为128字节，所以在加密和解密的过程中需要分块进行。
	 * 
	 * RSA加密对明文的长度是有限制的，如果加密数据过大会抛出如下异常： javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 byte
	 * 
	 * @param data
	 * @param cipher
	 * @return
	 * @throws Exception
	 */
	public static byte[] RSAEncode(byte[] data, Cipher cipher) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		int len = data.length;
		int offset = 0;
		int i = 0;

		// 对数据分段加密
		while (len - offset > 0) {
			byte[] buffer;
			if (len - offset > MAX_ENCRYPT_BLOCK) {
				buffer = cipher.doFinal(data, offset, MAX_ENCRYPT_BLOCK);
			} else {
				buffer = cipher.doFinal(data, offset, len - offset);
			}

			out.write(buffer);
			i++;
			offset = i * MAX_ENCRYPT_BLOCK;
		}

		byte[] datas = out.toByteArray();
		out.close();

		return datas;
	}

	public static String RSADecode(String data, String publicKeyCerFilePath) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");

			PublicKey publicKey = getPublicKey(publicKeyCerFilePath);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			byte[] datas = Base64.decodeBase64(data.getBytes("UTF-8"));

			byte[] bytes = RSADecode(datas, cipher);
			return new String(bytes);

		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * RSA加密明文最大长度117字节，解密要求密文最大长度为128字节，所以在加密和解密的过程中需要分块进行。
	 * 
	 * RSA解密对密文的长度是有限制的，当要解密的byte数组超过128的时候抛出如下异常：javax.crypto.IllegalBlockSizeException: Data must not be longer than 128 bytes
	 * 
	 * @param data
	 * @param cipher
	 * @return
	 * @throws Exception
	 */
	public static byte[] RSADecode(byte[] data, Cipher cipher) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		int len = data.length;
		int offset = 0;
		int i = 0;

		// 对数据分段解密
		while (len - offset > 0) {
			byte[] buffer;
			if (len - offset > MAX_DECRYPT_BLOCK) {
				buffer = cipher.doFinal(data, offset, MAX_DECRYPT_BLOCK);
			} else {
				buffer = cipher.doFinal(data, offset, len - offset);
			}

			out.write(buffer);
			i++;
			offset = i * MAX_DECRYPT_BLOCK;
		}

		byte[] datas = out.toByteArray();
		out.close();

		return datas;
	}

	private static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7',
	     '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
	     'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
	     'y', 'z', 'A', 'B','C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
	     'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
	     'Z','!','@','#','$','%','^','&','*','~','|'};

	/**
	 * 获取随机密码
	 * 
	 * @param type
	 *            1. 数字 2. 字母 3. 数字 字母 4. 数字 字母 特殊字符 其他 数字 小写字母
	 * @param passLength
	 * @return
	 */
	public static String getPassword(int type, int length) {
		StringBuilder password = new StringBuilder("");
		Random random = new Random();

		switch (type) {
		case 1:
			for (int m = 1; m <= length; m++) {
				password.append(chars[random.nextInt(10)]);
			}
			break;
		case 2:
			for (int m = 1; m <= length; m++) {
				password.append(chars[10 + random.nextInt(52)]);
			}
			break;
		case 3:
			for (int m = 1; m <= length; m++) {
				password.append(chars[random.nextInt(62)]);
			}
			break;
		case 4:
			for (int m = 1; m <= length; m++) {
				password.append(chars[random.nextInt(72)]);
			}
			break;

		default:
			for (int m = 1; m <= length; m++) {
				password.append(chars[random.nextInt(36)]);
			}
		}

		return password.toString();
	}

	public static void coverJksToPfx(File jksKeyStoreFile, File pfxKeyStoreFile, String keyStorePwd) {
		try {
			KeyStore inputKeyStore = KeyStore.getInstance("JKS");
			FileInputStream fis = new FileInputStream(jksKeyStoreFile);

			char[] password = null;
			if ((keyStorePwd == null) || keyStorePwd.trim().equals("")) {
				password = null;
			} else {
				password = keyStorePwd.toCharArray();
			}

			inputKeyStore.load(fis, password);
			fis.close();

			KeyStore outputKeyStore = KeyStore.getInstance("PKCS12");
			outputKeyStore.load(null, password);

			Enumeration<String> enums = inputKeyStore.aliases();
			while (enums.hasMoreElements()) { // we are read in just one certificate.
				String keyAlias = enums.nextElement();

				System.out.println("alias=[" + keyAlias + "]");

				if (inputKeyStore.isKeyEntry(keyAlias)) {
					Key key = inputKeyStore.getKey(keyAlias, password);
					Certificate[] certChain = inputKeyStore.getCertificateChain(keyAlias);
					outputKeyStore.setKeyEntry(keyAlias, key, password, certChain);
				}

			}

			FileOutputStream out = new FileOutputStream(pfxKeyStoreFile);
			outputKeyStore.store(out, password);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("密钥文件转换异常", e);
		}

	}

	public static void main(String args[]) {
		System.out.println(EncryptUtil.MD5Encode("68777800"));

		System.out.println(EncryptUtil.getPassword(1, 6));
		System.out.println(EncryptUtil.getPassword(2, 6));
		System.out.println(EncryptUtil.getPassword(3, 6));
		System.out.println(EncryptUtil.getPassword(4, 6));
		System.out.println(EncryptUtil.getPassword(5, 6));

	}

}
