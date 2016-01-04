/**
 * 
 */
package com.dsu.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;

import com.dsu.domain.api.AbstractIdEntity;

/**
 * @author nescafe
 *
 */
@Entity
public class Transmission extends AbstractIdEntity {

	@Basic
	private Date date;
	@Basic
	private BigDecimal value;
	@Basic
	private String description;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String comment) {
		this.description = comment;
	}
	
	@Override
	public String toString() {
		return "Transmission [id=" + getId() + ", date=" + date + ", value=" + value + ", comment=" + description + "]";
	}
	
}
