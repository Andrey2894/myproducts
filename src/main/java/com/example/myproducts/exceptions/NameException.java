package com.example.myproducts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Name must be specified and be less than 255 characters")
public class NameException extends RuntimeException{
}
