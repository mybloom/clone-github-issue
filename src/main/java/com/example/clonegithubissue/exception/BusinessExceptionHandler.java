package com.example.clonegithubissue.exception;

import com.example.clonegithubissue.common.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse<String>> handleBusinessException(
		BusinessException exception) {
		return ResponseEntity.status(exception.getHttpStatus())
			.body(new ErrorResponse<>(exception.getBodyMessage()));
	}
}
