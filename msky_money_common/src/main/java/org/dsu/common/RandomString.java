package org.dsu.common;

import java.util.Random;

public class RandomString {

	private static final char[] symbols;
	public static int MAX_GENERATED_LENGTH = 10000;

	static {
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch)
			tmp.append(ch);
		for (char ch = 'a'; ch <= 'z'; ++ch)
			tmp.append(ch);
		for (char ch = 'A'; ch <= 'Z'; ++ch)
			tmp.append(ch);
		for (char ch = 'а'; ch <= 'я'; ++ch) {
			tmp.append(ch);
		}
		symbols = tmp.toString().toCharArray();
	}

	private final Random random = new Random();

	private final char[] buf;
	
	public static String generateRandomStringByLength(int length) {
		return new RandomString(length).nextString();
	}
	
	public RandomString(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length < 1: " + length);
		}
		if (length > MAX_GENERATED_LENGTH) {
			throw new IllegalArgumentException("length > " + MAX_GENERATED_LENGTH + ": " + length);
		}
		buf = new char[length];
	}

	public String nextString() {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}
}
