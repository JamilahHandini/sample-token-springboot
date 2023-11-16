package com.tujuhsembilan.library.utility.error;

import java.util.Locale;

import com.tujuhsembilan.template.components.MessageUtilComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.Builder;

public class I18nErrorResponse extends ResponseStatusException {

    @Autowired
    private MessageUtilComponent messageUtilComponent;

    private String messageCode;
    private Object[] args;
    private Locale locale;

    @Builder
    public I18nErrorResponse(HttpStatus status, String messageCode, Locale locale, Object... args) {
        super(status);

        this.messageCode = messageCode;
        this.args = args;
        this.locale = locale;
    }

    @Builder
    public I18nErrorResponse(HttpStatus status, String messageCode, Object... args) {
        super(status);

        this.messageCode = messageCode;
        this.args = args;
        this.locale = LocaleContextHolder.getLocale();
    }

    @Override
    public String getReason() {
        return messageUtilComponent.getMessage(locale, messageCode, args);
    }
}
