/**
 * 
 */
package com.dsu.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dsu.dao.transmission.TransmissionDao;
import com.dsu.domain.model.Transmission;
import com.dsu.domain.model.TransmissionTest;

import junit.framework.TestCase;

/**
 * @author nescafe
 * Tests for transmission dao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/persistence-test-context.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
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

	
	@Test
	public void testTransmissionDaoImpl() {
		Transmission trans0 = TransmissionTest.createTranssmission();
		
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
