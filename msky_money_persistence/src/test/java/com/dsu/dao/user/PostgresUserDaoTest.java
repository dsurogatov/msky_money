/**
 * 
 */
package com.dsu.dao.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
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
@Rollback(true)
@Transactional
public class PostgresUserDaoTest extends UserDaoTest {

	@Test
	public void testDao() {
		commonTestDao();
		findColsTestDao();
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void nullNameTestDao() {
		_nullNameTestDao();
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void nullLoginTestDao() {
		_nullLoginTestDao();
	}
	
	@Test
	public void nullIdTestDao() {
		_nullIdTestDao();
	}
}
