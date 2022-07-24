package com.example.clonegithubissue.exception;

import org.springframework.http.HttpStatus;

public abstract class DuplicateDataException extends BusinessException {

	@Override
	protected HttpStatus getHttpStatus() {
		return HttpStatus.CONFLICT;
	}
}
