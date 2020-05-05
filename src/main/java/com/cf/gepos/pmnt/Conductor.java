package com.cf.gepos.pmnt;

import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Function;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.StringFixedSaltGenerator;

public class Conductor {

	private StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	private String salt = "A salt is added to the password key";

	public Conductor(Context context) {
		try {
			encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
			encryptor.setIvGenerator(new RandomIvGenerator());
			String kkey = Files.readString(context.getKeyfile()).strip();
			encryptor.setPassword(kkey);
			encryptor.setSaltGenerator(new StringFixedSaltGenerator(salt));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in Encryption process");
		}
	}

	public String process(Function<StandardPBEStringEncryptor, String> fn) {
		return fn.apply(encryptor);
	}
}
