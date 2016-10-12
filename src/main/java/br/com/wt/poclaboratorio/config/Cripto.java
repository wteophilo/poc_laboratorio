package br.com.wt.poclaboratorio.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cripto {
	private MessageDigest message;

	public Cripto() {
		try {
			this.message = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para cifrar a mensagem utilizando o MD5
	 * @param senha
	 * @see MessageDigest
	 * @see BigInteger
	 * 
	 */
	 public String encrypt(String senha) {
		String senhaEncrypt = "";
		message.update(senha.getBytes(), 0, senha.length());
		senhaEncrypt = new BigInteger(1, message.digest()).toString(16);
		return senhaEncrypt;
	}
}