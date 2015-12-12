package com.dsu.dao.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/hsqldb-persistence-test-context.xml")
@Rollback
@Transactional
public class HsqldbUserDaoTest {
	
	@Autowired
	private UserDaoTest userDaoTest;

	@Test
	public void testDao() {
		userDaoTest.commonTestDao();
		userDaoTest.findColsTestDao();
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void uniqueLoginTestDao() {
		userDaoTest.uniqueLoginTestDao();
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void nullNameTestDao() {
		userDaoTest.nullNameTestDao();
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void nullLoginTestDao() {
		userDaoTest.nullLoginTestDao();
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void nullPasswordTestDao() {
		userDaoTest.nullPasswordTestDao();
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void longLengthPasswordTestDao() {
		userDaoTest.longLengthPasswordTestDao();
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void longLengthNameTestDao() {
		userDaoTest.longLengthNameTestDao();
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void longLengthLoginTestDao() {
		userDaoTest.longLengthLoginTestDao();
	}
	
	@Test(expected=JpaSystemException.class)
	public void changePersistentIdTestDao() {
		userDaoTest.changePersistentIdTestDao();
	}
}
