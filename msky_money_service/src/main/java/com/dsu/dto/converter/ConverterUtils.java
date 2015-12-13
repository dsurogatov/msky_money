/**
 * 
 */
package com.dsu.dto.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsu.domain.api.Idable;
import com.dsu.domain.model.User;
import com.dsu.dto.api.IdableDTO;
import com.dsu.dto.model.UserDTO;
import com.dsu.service.exception.ExceptionType;
import com.dsu.service.exception.MskyMoneyException;

/**
 * @author nescafe Some usefull methods to covert objects, entities and dtos
 * 
 **/
public class ConverterUtils {

	private static final Map<Class<? extends IdableDTO>, Converter<? extends Idable, ? extends IdableDTO>> dtoCoverters = new HashMap<>();
	private static final Map<Class<? extends Idable>, Converter<? extends Idable, ? extends IdableDTO>> entityCoverters = new HashMap<>();

	static {
		UserConverter userConverter = new UserConverter();
		dtoCoverters.put(UserDTO.class, userConverter);
		entityCoverters.put(User.class, userConverter);
	}

	/** Convert entity to dto
	 * @param entity
	 * @return
	 */
	public static <I extends Idable, D extends IdableDTO> D toDTO(I entity) {
		if (entity == null) {
			return null;
		}
		if (!entityCoverters.containsKey(entity.getClass())) {
			throw new MskyMoneyException(ExceptionType.CONVERTER_NOT_FINDED);
		}

		@SuppressWarnings("unchecked")
		Converter<I, D> converter = (Converter<I, D>) entityCoverters.get(entity.getClass());
		return converter.toDTO(entity);
	}
	
	/** Convert entity list to dto list
	 * @param entityList
	 * @return
	 */
	public static <I extends Idable, D extends IdableDTO> List<D> toDTOList(List<I> entityList) {
		if (entityList == null) {
			return null;
		}
		
		List<D> retList = new ArrayList<>();
		for(I entity : entityList) {
			retList.add(toDTO(entity));
		}
		return retList;
	}

	/** Convert dto to entity
	 * @param dto
	 * @return
	 */
	public static <I extends Idable, D extends IdableDTO> I toEntity(D dto) {
		if (dto == null) {
			return null;
		}
		if (!dtoCoverters.containsKey(dto.getClass())) {
			throw new MskyMoneyException(ExceptionType.CONVERTER_NOT_FINDED);
		}

		@SuppressWarnings("unchecked")
		Converter<I, D> converter = (Converter<I, D>) dtoCoverters.get(dto.getClass());
		return converter.toEntity(dto);
	}

	private ConverterUtils() {
	}
}
