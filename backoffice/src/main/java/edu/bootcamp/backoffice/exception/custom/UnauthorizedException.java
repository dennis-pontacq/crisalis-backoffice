package edu.bootcamp.backoffice.exception.custom;

public class UnauthorizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPTION = "Credentials invalid (401)";
	
	public UnauthorizedException(String detalle) {
		super(DESCRIPTION + ". " + detalle);
	}
	
}
