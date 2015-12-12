/**
 * 
 */
package com.dsu.dao.user;

import java.util.List;

import com.dsu.dao.api.CrudDao;
import com.dsu.domain.model.User;

/**
 * @author nescafe
 *
 */
public interface UserDao extends CrudDao<User> {

	List<User> findByFields(String findedValue);
}
