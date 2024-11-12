package com.example.myproducts.bll;

import com.example.myproducts.exceptions.IdAlreadyExistsException;
import com.example.myproducts.exceptions.IdNotFoundException;
import com.example.myproducts.exceptions.NameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    protected ResponseEntity<MyException> handleIdNotFoundException() {
        return new ResponseEntity<>(new MyException("Id is not found"), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IdAlreadyExistsException.class)
    protected ResponseEntity<MyException> handleIdAlreadyExistsException() {
        return new ResponseEntity<>(new MyException("Id already exists"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameException.class)
    protected ResponseEntity<MyException> handleNameException() {
        return new ResponseEntity<>(new MyException("Name must be specified and be less than 255 characters"), HttpStatus.BAD_REQUEST);
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
