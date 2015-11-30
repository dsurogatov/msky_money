/**
 * 
 */
package com.dsu.service.transmission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsu.dao.transmission.TransmissionDao;
import com.dsu.domain.model.Transmission;

/**
 * @author nescafe
 *
 */
@Service
@Transactional
public class TransmissionServiceImpl implements TransmissionService {
	
	@Autowired
	private TransmissionDao dao;

	public List<Transmission> getTransmissions() {
		//throw new RuntimeException("dsdd");
//		List<Transmission> lst = dao.getTransmissions();
//		for(Transmission trsm : lst){
//			dao.save(trsm);
//		}
		//dao.save(lst.get(0));
		return dao.findAll();
	}

}
