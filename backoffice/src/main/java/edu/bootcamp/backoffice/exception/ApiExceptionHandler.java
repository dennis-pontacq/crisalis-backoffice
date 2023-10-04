package edu.bootcamp.backoffice.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.bootcamp.backoffice.exception.custom.EmptyElementException;
import edu.bootcamp.backoffice.exception.custom.InvalidPasswordException;
import edu.bootcamp.backoffice.exception.custom.NotCreatedException;
import edu.bootcamp.backoffice.exception.custom.UnauthorizedException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		EmptyElementException.class, 
		NotCreatedException.class,
		NullPointerException.class,
		InvalidPasswordException.class
	})
	@ResponseBody
	public ErrorMessage badRequest(HttpServletRequest request, Exception ex) {
		return new ErrorMessage(ex, request.getRequestURI());
	}
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({
		UnauthorizedException.class
	})
	@ResponseBody
	public void unauthorized(){
		//Empty
	}
}
