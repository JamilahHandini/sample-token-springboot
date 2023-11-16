package com.tujuhsembilan.library.utility.error;

import java.util.Locale;

import org.springframework.http.HttpStatus;

public class NotFoundErrorResponse extends I18nErrorResponse {

    public NotFoundErrorResponse(Object... args) {
        super(HttpStatus.NOT_FOUND, "system.generic.not-found-error", args);
    }

    public NotFoundErrorResponse(Locale locale, Object... args) {
        super(HttpStatus.NOT_FOUND, "system.generic.not-found-error", locale, args);
    }

}
