package com.dxc.dxconlinebanking.common;

import java.util.LinkedHashMap;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dxc.dxconlinebanking.auth.UsernameAlreadyExistsException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception) {
		var fieldErrors = new LinkedHashMap<String, String>();
		for (var error : exception.getBindingResult().getFieldErrors()) {
			String field = "passwordConfirmed".equals(error.getField())
					? "repeatPassword"
					: error.getField();
			fieldErrors.putIfAbsent(field, error.getDefaultMessage());
		}
		for (var error : exception.getBindingResult().getGlobalErrors()) {
			fieldErrors.putIfAbsent(error.getObjectName(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(new ApiError("Validation failed", fieldErrors));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException exception) {
		return ResponseEntity.badRequest().body(new ApiError(exception.getMessage()));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<ApiError> handleUnreadableRequest() {
		return ResponseEntity.badRequest().body(new ApiError("Invalid request format"));
	}

	@ExceptionHandler(UsernameAlreadyExistsException.class)
	ResponseEntity<ApiError> handleUsernameExists(UsernameAlreadyExistsException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(exception.getMessage()));
	}

	@ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
	ResponseEntity<ApiError> handleAuthentication(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiError(exception.getMessage()));
	}
}
