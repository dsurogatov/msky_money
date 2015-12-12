/**
 * 
 */
package com.dsu.dao.api;

import java.util.List;

/**
 * @author nescafe
 * Interface Dao for create update delete operations with entity
 */
public interface CrudDao<I> {

    I findById(Long id);
    List<I> findAll();
    I save(I transientInstance);
    int count();
    void delete(Long id);
    void flush();

}
