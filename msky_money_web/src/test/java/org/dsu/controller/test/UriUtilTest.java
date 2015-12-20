/**
 * 
 */
package org.dsu.controller.test;

import org.junit.Test;

import com.dsu.controller.util.UriUtil;
import com.dsu.service.exception.ExceptionType;
import com.dsu.service.exception.MskyMoneyException;

import junit.framework.TestCase;

/**
 * @author nescafe
 * Some tests for UriUtil
 */
public class UriUtilTest {

	@Test
	public void decode_NoExceptionTest() {
		UriUtil.decode("ffff");
		UriUtil.decode("фффф");
		TestCase.assertEquals(UriUtil.decode("dto%20%D1%80%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B9%3F"), "dto русский?");
	}
	
	@Test
	public void decode_WithExceptionTest() {
		try {
			UriUtil.decode(null);
		} catch (MskyMoneyException e){
			TestCase.assertEquals(e.getType(), ExceptionType.DECODE_URI_FAILED);
		}
		try {
			UriUtil.decode("");
		} catch (MskyMoneyException e){
			TestCase.assertEquals(e.getType(), ExceptionType.DECODE_URI_FAILED);
		}
	}
}
