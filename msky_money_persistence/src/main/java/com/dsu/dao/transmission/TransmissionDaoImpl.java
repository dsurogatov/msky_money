/**
 * 
 */
package com.dsu.dao.transmission;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsu.dao.api.AbstractCrudDao;
import com.dsu.domain.model.Transmission;

/**
 * @author nescafe
 *
 */
@Repository
public class TransmissionDaoImpl extends AbstractCrudDao<Transmission> implements TransmissionDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dsu.dao.transmission.TransmissionDao#getTransmissions()
	 */
	@Override
	public List<Transmission> getTransmissions() {
		Transmission trans1 = new Transmission();
		//trans1.setId(1l);
		trans1.setDate(new Date());
		trans1.setValue(BigDecimal.valueOf(10));
		trans1.setDescription("aaa");

		Transmission trans2 = new Transmission();
		//trans2.setId(2l);
		trans2.setDate(new Date());
		trans2.setValue(BigDecimal.valueOf(11.2));
		trans2.setDescription("bbb");

		Transmission trans3 = new Transmission();
		//trans3.setId(3l);
		LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
		Date date = Date.from(firstDay_2014.atStartOfDay(ZoneId.systemDefault()).toInstant());
		trans3.setDate(date);
		trans3.setValue(BigDecimal.valueOf(77.2345));
		trans3.setDescription("ccc");

		List<Transmission> retList = new ArrayList<>();
		retList.add(trans1);
		retList.add(trans2);
		retList.add(trans3);
		return retList;
	}
}
