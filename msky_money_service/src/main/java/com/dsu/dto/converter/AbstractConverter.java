/**
 * 
 */
package com.dsu.dto.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.beanutils.BeanUtils;

import com.dsu.domain.api.Idable;
import com.dsu.dto.api.IdableDTO;
import com.dsu.service.exception.ExceptionType;
import com.dsu.service.exception.MskyMoneyException;

/**
 * @author nescafe
 *
 */
public abstract class AbstractConverter<I extends Idable, D extends IdableDTO> implements Converter<I, D> {
	
	private Class<I> entityClass;
	private Class<D> dtoClass;

	/** Get entity.class instance
	 * @return class<I> instance
	 */
	@SuppressWarnings("unchecked")
	protected Class<I> getEntityClass() {
		if (entityClass == null) {
			this.entityClass = (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	/** Get dto.class instance
	 * @return class<D> instance
	 */
	@SuppressWarnings("unchecked")
	protected Class<D> getDTOClass() {
		if (dtoClass == null) {
			this.dtoClass = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		}
		return dtoClass;
	}

	@Override
	public D toDTO(I entity) {
		if (entity == null) {
			return null;
		}
		
		try {
			D dto = getDTOClass().newInstance();
			BeanUtils.copyProperties(dto, entity);
			return dto;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new MskyMoneyException(ExceptionType.INTERNAL_ERROR, e);
		}
	}

	@Override
	public I toEntity(D dto) {
		if (dto == null) {
			return null;
		}
		
		try {
			I entity = getEntityClass().newInstance();
			BeanUtils.copyProperties(entity, dto);
			return entity;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new MskyMoneyException(ExceptionType.INTERNAL_ERROR, e);
		}
	}

}
