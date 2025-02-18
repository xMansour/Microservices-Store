package com.mansour.orderservice.exception;

import com.mansour.orderservice.internationalization.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;

public class CouldNotPlaceOrderException extends RuntimeException {
    public final HttpStatusCode httpStatusCode;

    public CouldNotPlaceOrderException(HttpStatusMessageKey httpStatusMessageKey) {
        super(Messages.getMessageForLocale(httpStatusMessageKey.messageKey, LocaleContextHolder.getLocale()));
        httpStatusCode = httpStatusMessageKey.httpStatusCode;
    }
}
