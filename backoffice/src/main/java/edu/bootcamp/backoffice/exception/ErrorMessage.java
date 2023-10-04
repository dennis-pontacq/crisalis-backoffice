package edu.bootcamp.backoffice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	private String exception;
	private String message;
	private String path;
	
	public ErrorMessage(Exception ex, String path) {
		this.exception = ex.getClass().getSimpleName();
		//if(ex instanceof NullPointerException) {
		//	this.message = ex.getLocalizedMessage();
		//} else 
		this.message = ex.getMessage();
		this.path = path;
	}
	
}
