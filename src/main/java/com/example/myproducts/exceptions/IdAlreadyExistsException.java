package com.example.myproducts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Id already exists")
public class IdAlreadyExistsException extends RuntimeException{
}
