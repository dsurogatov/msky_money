/**
 * 
 */
package com.dsu.domain.api;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author nescafe
 *
 */
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractIdEntity implements NamedEntity {

	@Column(name = "s_name", nullable = false, length = 1000)
	private String name;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NamedEntity [name=" + name + ", id=" + id + "]";
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AbstractNamedEntity))
			return false;
		if (!super.equals(o))
			return false;

		AbstractNamedEntity that = (AbstractNamedEntity) o;

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
}