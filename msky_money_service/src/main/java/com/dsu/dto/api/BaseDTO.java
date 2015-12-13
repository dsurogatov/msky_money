/**
 * 
 */
package com.dsu.dto.api;

import com.dsu.domain.api.BaseEntity;

/**
 * @author nescafe
 *
 */
public class BaseDTO implements IdableDTO {

	private Long id;

	/* (non-Javadoc)
	 * @see com.dsu.dto.IdableDTO#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BaseEntity))
			return false;

		BaseEntity that = (BaseEntity) o;

		if (getId() != null ? !getId().equals(that.getId())
				: that.getId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getId() != null ? getId().hashCode() : 0);
	}

}
