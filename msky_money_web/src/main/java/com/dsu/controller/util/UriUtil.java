/**
 * 
 */
package com.dsu.controller.util;

import java.io.UnsupportedEncodingException;

import org.springframework.web.util.UriUtils;

import com.dsu.service.exception.ExceptionType;
import com.dsu.service.exception.MskyMoneyException;

/**
 * @author nescafe Class for encode/decode url parts
 */
public final class UriUtil {

	private UriUtil() {
	}

	public static String decode(String source) {
		try {
			return UriUtils.decode(source, "UTF-8");
		} catch (UnsupportedEncodingException | IllegalArgumentException e) {
			throw new MskyMoneyException(ExceptionType.DECODE_URI_FAILED);
		}
	}
}
