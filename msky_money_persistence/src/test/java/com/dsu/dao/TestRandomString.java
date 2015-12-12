/**
 * 
 */
package com.dsu.dao;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author nescafe
 * Test for generating random string
 */
public class TestRandomString {

	@Test
	public void testGenRandomString() {
		final int magicLen = 100;
		TestCase.assertEquals(magicLen, RandomString.generateRandomStringByLength(magicLen).length());
		TestCase.assertNotSame(RandomString.generateRandomStringByLength(magicLen), RandomString.generateRandomStringByLength(magicLen));
		TestCase.assertNotSame(RandomString.MAX_GENERATED_LENGTH, RandomString.generateRandomStringByLength(RandomString.MAX_GENERATED_LENGTH));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testZeroLenGenRandomString() {
		RandomString.generateRandomStringByLength(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeLenGenRandomString() {
		RandomString.generateRandomStringByLength(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMaxLenGenRandomString() {
		RandomString.generateRandomStringByLength(RandomString.MAX_GENERATED_LENGTH + 1);
	}
}
