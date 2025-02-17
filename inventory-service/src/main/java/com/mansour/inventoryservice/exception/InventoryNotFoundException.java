package com.mansour.inventoryservice.exception;

import com.mansour.inventoryservice.internationalization.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;

public class InventoryNotFoundException extends RuntimeException {
    public final HttpStatusCode httpStatusCode;

    public InventoryNotFoundException(HttpStatusMessageKey httpStatusMessageKey) {
        super(Messages.getMessageForLocale(httpStatusMessageKey.messageKey, LocaleContextHolder.getLocale()));
        httpStatusCode = httpStatusMessageKey.httpStatusCode;
    }
}
