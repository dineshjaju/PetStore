package com.petstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pet Not Found")
public class PetNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

}
