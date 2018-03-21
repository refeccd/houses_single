package com.liaozan.common.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
public class HashUtils {
	private static final HashFunction FUNCTION = Hashing.sha256();

	private static final String SALT = "liaozan.com";

	public static String encryPassword(String password) {
		HashCode hashCode = FUNCTION.hashString(password + SALT, Charset.forName("UTF-8"));
		return hashCode.toString();
	}

}
