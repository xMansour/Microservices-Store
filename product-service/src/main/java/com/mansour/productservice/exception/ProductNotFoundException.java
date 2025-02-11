package com.mansour.productservice.exception;

import com.mansour.productservice.internationalization.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;

public class ProductNotFoundException extends RuntimeException {
    public final HttpStatusCode httpStatusCode;

    public ProductNotFoundException(HttpStatusMessageKey httpStatusMessageKey) {
        super(Messages.getMessageForLocale(httpStatusMessageKey.messageKey, LocaleContextHolder.getLocale()));
        this.httpStatusCode = httpStatusMessageKey.httpStatusCode;
    }
}
