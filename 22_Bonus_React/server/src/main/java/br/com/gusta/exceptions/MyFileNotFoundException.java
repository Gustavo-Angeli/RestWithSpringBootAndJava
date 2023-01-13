package br.com.gusta.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyFileNotFoundException(String str) {
		super(str);
	}

	public MyFileNotFoundException(String str, Throwable throwable) {
		super(str, throwable);
	}

}
