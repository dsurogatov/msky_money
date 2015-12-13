/**
 * 
 */
package com.dsu.dto.converter;

import com.dsu.domain.api.Idable;
import com.dsu.dto.api.IdableDTO;

/**
 * @author nescafe
 * Defines base methods to covert between entity and dto 
 */
public interface Converter<I extends Idable, D extends IdableDTO> {

	/** Convert entity to DTO object
	 * @param entity
	 * @return converted DTO object
	 */
	D toDTO(I entity);
	
	/** Convert DTO object to entity
	 * @param dto
	 * @return converted entity
	 */
	I toEntity(D dto);
}
