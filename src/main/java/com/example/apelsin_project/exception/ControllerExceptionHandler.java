package com.example.apelsin_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> getMessage(ResourceNotFoundException exception){
//        ErrorMessage errorMessage=new ErrorMessage();
//        errorMessage.setName("Xato");
//        errorMessage.setDescription("Muammo izohi:");
//        errorMessage.setTime(LocalDate.now());
        ResponseEntity<Object> responseEntity=new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
