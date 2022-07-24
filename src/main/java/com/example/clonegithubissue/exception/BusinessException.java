package com.example.clonegithubissue.exception;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {

	protected abstract HttpStatus getHttpStatus();

	protected abstract String getBodyMessage();

}
