/**
 * 
 */
package com.dsu.service.api;

import java.util.List;

/**
 * @author nescafe
 * Define base service method for crud's dao
 */
public interface CrudService<DTO> {

	DTO findById(Long id);
	List<DTO> findByFields(String findingValue);
    List<DTO> findAll();
    DTO create(DTO instance);
    DTO update(DTO instance);
    void delete(Long id);
    
}
