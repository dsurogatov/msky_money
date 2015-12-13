/**
 * 
 */
package com.dsu.dto.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dsu.domain.api.NamedEntity;

/**
 * @author nescafe
 *
 */
public class NamedDTO extends BaseDTO implements NamableDTO {

	@NotNull(message="Field name can't be empty")
	@Size(min=1, max=1000, message="Field name should be between 1 - 1000 characters.")
	private String name;
	
	/* (non-Javadoc)
	 * @see com.dsu.dto.NamableDTO#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof NamedEntity))
			return false;
		if (!super.equals(o))
			return false;

		NamedEntity that = (NamedEntity) o;

		if (getName() != null ? !getName().equals(that.getName()) : that
				.getName() != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "NamedDTO [id=" + getId() + ", name=" + name + "]";
	}
}
