package com.tujuhsembilan.library.utility.error;

import java.util.Locale;

import org.springframework.http.HttpStatus;

public class DuplicateErrorResponse extends I18nErrorResponse {

    public DuplicateErrorResponse(Object... args) {
        super(HttpStatus.BAD_REQUEST, "system.generic.duplicate-error", args);
    }

    public DuplicateErrorResponse(Locale locale, Object... args) {
        super(HttpStatus.BAD_REQUEST, "system.generic.duplicate-error", locale, args);
    }

}
