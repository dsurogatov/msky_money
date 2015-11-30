/**
 * 
 */
package com.dsu.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import junit.framework.TestCase;

/**
 * @author nescafe
 *
 */
public class TransmissionTest extends TestCase {
	
	public static Transmission createTranssmission() {
		Transmission trans = new Transmission();
		
		LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
		Date date = Date.from(firstDay_2014.atStartOfDay(ZoneId.systemDefault()).toInstant());
		trans.setDate(date);
		
		trans.setValue(BigDecimal.valueOf(10.1111));
		trans.setDescription("desc");
		
		return trans;
	}
	
	public static void assertEntityPropertiesEquals(Transmission trans0, Transmission trans1) {
		assertEquals(trans0.getId(), trans1.getId());
		assertEquals(trans0.getDate(), trans1.getDate());
		assertEquals(trans0.getValue(), trans1.getValue());
		assertEquals(trans0.getDescription(), trans1.getDescription());
	}
	
	public void testCreateTransmission() {
		Transmission trans0 = createTranssmission();
		assertNull(trans0.getId());
		
		Transmission trans1 = createTranssmission();
		assertNull(trans1.getId());
		assertEquals(trans0, trans1);
		
		assertEntityPropertiesEquals(trans0, trans1);
		
		Date oldDate = trans0.getDate();
		trans1.setDate(new Date());
		assertNotSame(trans0.getDate(), trans1.getDate());
		
		trans0.setValue(BigDecimal.valueOf(10));
		trans1.setValue(BigDecimal.valueOf(11));
		assertNotSame(trans0.getValue(), trans1.getValue());
		
		trans0.setDescription("aaa");
		trans0.setDescription("bbb");
		assertNotSame(trans0.getDescription(), trans1.getDescription());
		
		trans1.setDate(oldDate);
		trans1.setValue(BigDecimal.valueOf(10));
		trans1.setDescription("bbb");
		assertEntityPropertiesEquals(trans0, trans1);
		
		trans1.setDate(null);
		trans1.setValue(null);
		trans1.setDescription(null);
		assertNull(trans1.getDate());
		assertNull(trans1.getValue());
		assertNull(trans1.getDescription());
		
		trans0.setId(1l);
		assertEquals(trans0.getId(), Long.valueOf(1l));
		
		trans0.setId(null);
		assertNull(trans0.getId());		
		
	}

}
