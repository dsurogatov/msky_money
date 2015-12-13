/**
 * 
 */
package com.dsu.dto.api;

/**
 * @author nescafe
 * Define DTO with a field name
 */
public interface NamableDTO extends IdableDTO {

	/**
	 * @return name 
	 */
	String getName();
}
