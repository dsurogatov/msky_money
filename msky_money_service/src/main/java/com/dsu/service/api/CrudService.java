/**
 * 
 */
package com.dsu.service.api;

import java.util.List;

/**
 * @author nescafe
 * Define base service method for crud's dao
 */
public interface CrudService<I> {

	I findById(Long id);
	List<I> findByFields(String findingValue);
    List<I> findAll();
    I create(I instance);
    I update(I instance);
    void delete(I instance);
    
}
