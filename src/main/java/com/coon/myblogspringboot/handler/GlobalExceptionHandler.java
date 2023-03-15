package com.coon.myblogspringboot.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 Exception 받을시 여기로
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String handleArgumentException(Exception e){
        return "<h1>" + e.getMessage() + "</h1>";
    }
}
