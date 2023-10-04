 package edu.bootcamp.backoffice.exception.custom;

public class InvalidPasswordException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPTION = "Empty element (400)";
	
	public InvalidPasswordException(String detalle) {
		super(DESCRIPTION + ". " + detalle);
	}
	
}
