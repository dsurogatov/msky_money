/**
 * 
 */
package com.dsu.service.exception;

/**
 * @author nescafe
 * Main project's exception
 */
public class MskyMoneyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private ExceptionType type;
	
	public MskyMoneyException(ExceptionType type) {
		this(type, null);
	}

	public MskyMoneyException(ExceptionType type, Exception couse) {
		super(couse);
		this.type = type;
	}

	/** Get exception's type
	 * @return
	 */
	public ExceptionType getType() {
		return type;
	}
}
