package com.tujuhsembilan.template.listener;

import com.tujuhsembilan.template.components.MessageUtilComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionListener {

    @Autowired
    MessageUtilComponent messageUtilComponent;

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<String> handleUnhandledError(Exception ex) {
        log.error("Got error of type " + ex.getClass().getName(), ex);

        return new ResponseEntity<>(messageUtilComponent.getMessage("system.generic.error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
