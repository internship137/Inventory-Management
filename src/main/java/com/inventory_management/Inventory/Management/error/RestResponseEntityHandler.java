package com.inventory_management.Inventory.Management.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@ResponseStatus
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

    // error handling

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception, WebRequest request){
        ErrorMessage message= new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    // validation handling

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception){
//        ErrorMessage message = new ErrorMessage(new Date(),"Validation Error",
//                exception.getBindingResult().getFieldError().getDefaultMessage());
//        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//
//    }



}
