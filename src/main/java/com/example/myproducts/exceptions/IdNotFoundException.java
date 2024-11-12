package com.example.myproducts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Id is not found")
public class IdNotFoundException extends RuntimeException{
}
