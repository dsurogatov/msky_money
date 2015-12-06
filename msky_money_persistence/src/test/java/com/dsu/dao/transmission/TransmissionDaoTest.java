/**
 * 
 */
package com.dsu.dao.transmission;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
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
//@Ignore("This is base class with helpers method")
public class TransmissionDaoTest {

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
		TestCase.assertNotNull(trans0.getId());
		
		trans0.setDate(presentDate);
		trans0.setValue(val);
		trans0.setDescription(description);
		Transmission trans1 = dao.save(trans0);
		TestCase.assertEquals(trans0, trans1);
		TestCase.assertEquals(trans1.getDate(), presentDate);
		TestCase.assertEquals(trans1.getValue(), val);
		TestCase.assertEquals(trans1.getDescription(), description);
		TransmissionTest.assertEntityPropertiesEquals(trans0, trans1);
		
		trans1 = dao.findById(trans0.getId());
		TestCase.assertEquals(trans0, trans1);
		TransmissionTest.assertEntityPropertiesEquals(trans0, trans1);
		
		List<Transmission> transmList = dao.findAll();
		TestCase.assertEquals(transmList.size(), 1);
		TestCase.assertEquals(dao.count(), 1);
		
		dao.delete(trans0);
		TestCase.assertEquals(dao.findAll().size(), 0);
		TestCase.assertEquals(dao.count(), 0);
	}
}
