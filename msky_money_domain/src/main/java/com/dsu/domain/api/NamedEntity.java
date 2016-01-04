/**
 * 
 */
package com.dsu.domain.api;

/**
 * @author nescafe
 * Interface for entities with a field name
 */
public interface NamedEntity extends IdEntity {

	/** Get the name of an entity
	 * @return - value of a name field
	 */
	String getName();
}
