package com.dws.challenge.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(AmountNotMatchException.class)
    public ResponseEntity<String> handleAmountNotMatchException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Amount Not Match Exception : " + ex.getMessage());
    }

}
