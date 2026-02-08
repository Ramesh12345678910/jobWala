package com.project.auth_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlingMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> map=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                map.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handlingMethodArgumentTypeMisMatchException(MethodArgumentTypeMismatchException ex){
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<?> handlingAlreadyRegisteredException(AlreadyRegisteredException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Registration found");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handilingUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
    }
    @ExceptionHandler(AccountBlockedException.class)
    public ResponseEntity<?> handlingAccountBlockedException(AccountBlockedException ex){
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }
}
