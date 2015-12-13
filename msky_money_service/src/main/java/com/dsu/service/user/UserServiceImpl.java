/**
 * 
 */
package com.dsu.service.user;

import static com.dsu.dto.converter.ConverterUtils.toDTO;
import static com.dsu.dto.converter.ConverterUtils.toDTOList;
import static com.dsu.dto.converter.ConverterUtils.toEntity;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsu.dao.user.UserDao;
import com.dsu.dto.model.UserDTO;

/**
 * @author nescafe
 * Service contains the main methods for working with User entity
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#findById(java.lang.Long)
	 */
	@Override
	public UserDTO findById(Long id) {
		return toDTO(dao.findById(id));
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#findByFields(java.lang.String)
	 */
	@Override
	public List<UserDTO> findByFields(String findingValue) {
		if (StringUtils.isBlank(findingValue)) {
			return findAll();
		}
		return toDTOList(dao.findByFields(findingValue));
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#findAll()
	 */
	@Override
	public List<UserDTO> findAll() {
		return toDTOList(dao.findAll());
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#create(java.lang.Object)
	 */
	@Override
	public UserDTO create(UserDTO instance) {
		return toDTO(dao.save(toEntity(instance)));
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#update(java.lang.Object)
	 */
	@Override
	public UserDTO update(UserDTO instance) {
		return toDTO(dao.save(toEntity(instance)));
	}

	/* (non-Javadoc)
	 * @see com.dsu.service.api.CrudService#delete(java.lang.Object)
	 */
	@Override
	public void delete(UserDTO instance) {
		dao.delete(instance.getId());
	}

}
