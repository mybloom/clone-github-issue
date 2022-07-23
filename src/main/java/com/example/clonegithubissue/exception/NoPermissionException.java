package com.example.clonegithubissue.exception;

import org.springframework.http.HttpStatus;

public abstract class NoPermissionException extends BusinessException {

	@Override
	protected HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
