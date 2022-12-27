package br.com.gusta.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileStorageException(String str) {
		super(str);
	}

	public FileStorageException(String str, Throwable throwable) {
		super(str, throwable);
	}

}
