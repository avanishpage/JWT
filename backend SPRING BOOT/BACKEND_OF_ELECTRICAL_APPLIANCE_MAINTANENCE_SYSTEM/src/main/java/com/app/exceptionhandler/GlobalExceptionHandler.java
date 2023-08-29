package com.app.exceptionhandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.app.dto.ApiResponse;
import com.app.exceptions.CustomerNotFoundException;
import com.app.exceptions.CustomerPasswordNotMatchingException;
import com.app.exceptions.*;
import com.app.exceptions.VendorNotFoundException;
import com.app.exceptions.VendorPasswordNotMatchingException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotVAlidException(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getFieldErrors();
		Map<String, String> map = fieldErrors.stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}

	// customer not found
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleCustomerNotFoundException(CustomerNotFoundException e) {
		return new ApiResponse(e.getMessage());
	}

	// vendor not found
	@ExceptionHandler(VendorNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleCustomerNotFoundException(VendorNotFoundException e) {
		return new ApiResponse(e.getMessage());
	}

	// vendor password mismatch
	@ExceptionHandler(VendorPasswordNotMatchingException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ApiResponse handleCustomerNotFoundException(VendorPasswordNotMatchingException e) {
		return new ApiResponse(e.getMessage());
	}

	// customer password mismatch
	@ExceptionHandler(CustomerPasswordNotMatchingException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ApiResponse handleCustomerNotFoundException(CustomerPasswordNotMatchingException e) {
		return new ApiResponse(e.getMessage());
	}


//	 any remaining runtime exception
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handleRuntimeException(RuntimeException e) {
		return new ApiResponse(e.getMessage());
	}

	// service not found
	@ExceptionHandler(ServiceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleCustomerNotFoundException(ServiceNotFoundException e) {
		return new ApiResponse(e.getMessage());
	}

	
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ApiResponse handleCustomerNotFoundException(OrderNotFoundException e) {
		return new ApiResponse(e.getMessage());
	}

	@ExceptionHandler(VendorNotMatchingException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ApiResponse handleCustomerNotFoundException(VendorNotMatchingException e) {
		return new ApiResponse(e.getMessage());
	}
	
	
}
