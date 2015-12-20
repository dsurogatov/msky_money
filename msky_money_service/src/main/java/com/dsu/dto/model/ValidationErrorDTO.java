/**
 * 
 */
package com.dsu.dto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nescafe
 * DTO for set validation of error
 */
public class ValidationErrorDTO {
	 
    private final List<FieldErrorDTO> fieldErrors = new ArrayList<>();
 
    public ValidationErrorDTO() {
 
    }
 
    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }

	public List<FieldErrorDTO> getFieldErrors() {
		return new ArrayList<FieldErrorDTO>(fieldErrors);
	}
 
}