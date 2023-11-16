package com.tujuhsembilan.library.utility.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BaseResponseBuilder {
    private HttpStatus status;
    private String message;
}
