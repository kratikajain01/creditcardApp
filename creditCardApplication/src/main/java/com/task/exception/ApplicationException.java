/**
 * 
 */
package com.task.exception;

/**
 * @author kratika.jain
 *
 * Custom Exception if credit Card already exists
 */
public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	String message;


	public ApplicationException(String message) {
		this.message = message;
	}


	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getMessage() {
		return message;

	}

	public String toString() {
		return message;
	}
}
