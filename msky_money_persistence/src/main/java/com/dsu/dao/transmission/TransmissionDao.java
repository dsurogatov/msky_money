/**
 * 
 */
package com.dsu.dao.transmission;

import java.util.List;

import com.dsu.dao.api.CrudDao;
import com.dsu.domain.model.Transmission;

/**
 * @author nescafe
 *
 */
public interface TransmissionDao extends CrudDao<Transmission> {

	List<Transmission> getTransmissions();
}
