/**
 * 
 */
package com.dsu.dto.api;

/**
 * @author nescafe
 * Define DTO with a field name
 */
public interface NamedDTO extends IdDTO {

	/**
	 * @return name 
	 */
	String getName();
}
