package com.rohit.call.api.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rohit.call.service.exception.CallDetailsNotFoundException;
import com.rohit.call.service.exception.CallDetailsNotValid;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CallServicepAPIAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleRequestBody(MethodArgumentNotValidException ex) {

        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        
        String errorMessage = errorList.stream()
                .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining(", "));
        
        log.info("errorMessage : {} ", errorMessage);
        
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(CallDetailsNotFoundException.class) 
    public ResponseEntity<?> handleNotFound(CallDetailsNotFoundException ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(CallDetailsNotValid.class) 
    public ResponseEntity<?> handleNotFound(CallDetailsNotValid ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
