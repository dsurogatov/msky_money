/**
 * 
 */
package com.dsu.dto;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dsu.domain.api.BaseEntity;
import com.dsu.dto.api.BaseDTO;
import com.dsu.dto.converter.ConverterUtils;
import com.dsu.service.exception.MskyMoneyException;

import junit.framework.TestCase;

/**
 * @author nescafe Test for converter utils
 */
public class ConverterUtilsTest {

	private static class TestDTO extends BaseDTO {

	}

	private static class TestEntity extends BaseEntity {

	}

	/**
	 * Test null values
	 */
	@Test
	public void converterTest() {
		TestCase.assertNull(ConverterUtils.toEntity(null));
		TestCase.assertNull(ConverterUtils.toDTO(null));
		TestCase.assertNull(ConverterUtils.toDTOList(null));
	}

	/**
	 * Test exception then converter have not finded
	 */
	@Test
	public void converterNotFindConverterTest() {
		try {
			ConverterUtils.toEntity(new TestDTO());
		} catch (Exception e) {
			assertThat(e, is(instanceOf(MskyMoneyException.class)));
		}

		try {
			ConverterUtils.toDTO(new TestEntity());
		} catch (Exception e) {
			assertThat(e, is(instanceOf(MskyMoneyException.class)));
		}

		try {
			List<TestEntity> testList = new ArrayList<TestEntity>();
			testList.add(new TestEntity());
			ConverterUtils.toDTOList(testList);
		} catch (Exception e) {
			assertThat(e, is(instanceOf(MskyMoneyException.class)));
		}
	}
}
