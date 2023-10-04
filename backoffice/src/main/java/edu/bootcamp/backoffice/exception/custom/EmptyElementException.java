 package edu.bootcamp.backoffice.exception.custom;

public class EmptyElementException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPTION = "Empty element (400)";
	
	public EmptyElementException(String detalle) {
		super(DESCRIPTION + ". " + detalle);
	}
	
}
