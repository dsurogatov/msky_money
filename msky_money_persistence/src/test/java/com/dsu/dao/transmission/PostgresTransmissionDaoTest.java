/**
 * 
 */
package com.dsu.dao.transmission;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nescafe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/postgres-persistence-test-context.xml")
@Rollback
@Transactional
public class PostgresTransmissionDaoTest extends TransmissionDaoTest {

	@Test
	public void testTransmissionDaoImpl() {
		_testTransmissionDaoImpl();
	}
}
