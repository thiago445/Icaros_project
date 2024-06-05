package com.Icaros.Services.Exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CREATED)
public class CreatedResponse extends DataIntegrityViolationException {

	public CreatedResponse(String message) {
		super(message);
	}
}