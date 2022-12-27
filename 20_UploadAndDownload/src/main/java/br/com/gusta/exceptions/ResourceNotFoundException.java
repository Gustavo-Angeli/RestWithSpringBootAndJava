package br.com.gusta.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String str) {
		super(str);
	}
	public ResourceNotFoundException() {
		super("No records for this id");
	}
	
	private static final long serialVersionUID = 1L;
	
}
