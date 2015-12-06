/**
 * 
 */
package com.dsu.dao.transmission;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsu.domain.model.Transmission;
import com.dsu.domain.model.TransmissionTest;

import junit.framework.TestCase;

/**
 * @author nescafe
 * Tests for transmission dao
 */
@Component
@Ignore("This is base class with helpers method")
public class TransmissionDaoTest extends TestCase {

	@Autowired
	TransmissionDao dao;
	
	private Date presentDate;
	private BigDecimal val;
	private String description;
	
	@Before
	public void setUp() throws Exception {

		presentDate = new Date();
		
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    sdf.setTimeZone(TimeZone.getTimeZone("PST"));
//	    Date NOW = sdf.parse("2015-05-23 00:00:00");

		val = BigDecimal.valueOf(100.1234);
		description = "ddd";
	}

	
	protected void _testTransmissionDaoImpl() {
		Transmission trans0 = TransmissionTest.createTranssmission();
		//System.out.println("*************" + trans0 + presentDate);
		
		trans0 = dao.save(trans0);
		assertNotNull(trans0.getId());
		
		trans0.setDate(presentDate);
		trans0.setValue(val);
		trans0.setDescription(description);
		Transmission trans1 = dao.save(trans0);
		assertEquals(trans0, trans1);
		assertEquals(trans1.getDate(), presentDate);
		assertEquals(trans1.getValue(), val);
		assertEquals(trans1.getDescription(), description);
		TransmissionTest.assertEntityPropertiesEquals(trans0, trans1);
		
		trans1 = dao.findById(trans0.getId());
		assertEquals(trans0, trans1);
		TransmissionTest.assertEntityPropertiesEquals(trans0, trans1);
		
		List<Transmission> transmList = dao.findAll();
		assertEquals(transmList.size(), 1);
		assertEquals(dao.count(), 1);
		
		dao.delete(trans0);
		assertEquals(dao.findAll().size(), 0);
		assertEquals(dao.count(), 0);
	}
}
