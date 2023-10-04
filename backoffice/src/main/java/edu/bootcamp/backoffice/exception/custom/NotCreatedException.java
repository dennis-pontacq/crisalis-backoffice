 package edu.bootcamp.backoffice.exception.custom;

public class NotCreatedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPTION = "Entity create error (400)";
	
	public NotCreatedException(String detalle) {
		super(DESCRIPTION + ". " + detalle);
	}
	
}
