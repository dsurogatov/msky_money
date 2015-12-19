/**
 * 
 */
package org.dsu.controller.test;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

/**
 * @author nescafe
 * Some constants and utils methods for testing controllers
 */
public final class TestUtil {
	
	private TestUtil(){}

	 public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
             MediaType.APPLICATION_JSON.getSubtype(),                        
             Charset.forName("utf8")                     
             );
}
