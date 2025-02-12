package com.mansour.orderservice.exception;

import org.springframework.http.HttpStatus;

public enum HttpStatusMessageKey {
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "order.not.found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal.server.error");

    public HttpStatus httpStatusCode;
    public String messageKey;

    HttpStatusMessageKey(HttpStatus httpStatusCode, String messageKey) {
        this.httpStatusCode = httpStatusCode;
        this.messageKey = messageKey;
    }
}
