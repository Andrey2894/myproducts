package com.example.myproducts.bll;

import com.example.myproducts.exceptions.IdNotFoundException;
import com.example.myproducts.exceptions.NameException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    protected ResponseEntity<MyException> handleIdNotFoundException() {
        return new ResponseEntity<>(new MyException("Id is not found"), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NameException.class)
    protected ResponseEntity<MyException> handleNameException() {
        return new ResponseEntity<>(new MyException("Name must be specified and be less than 255 characters"), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<MyException> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new MyException(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private static class MyException {
        private String message;

        public MyException(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
