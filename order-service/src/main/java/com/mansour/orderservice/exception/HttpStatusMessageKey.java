package com.mansour.orderservice.exception;

import org.springframework.http.HttpStatus;

public enum HttpStatusMessageKey {
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "order.not.found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal.server.error"),
    COULD_NOT_PLACE_ORDER(HttpStatus.BAD_REQUEST, "could.not.place.order"),
    INSUFFICIENT_INVENTORY(HttpStatus.BAD_REQUEST, "insufficient.inventory");

    public HttpStatus httpStatusCode;
    public String messageKey;

    HttpStatusMessageKey(HttpStatus httpStatusCode, String messageKey) {
        this.httpStatusCode = httpStatusCode;
        this.messageKey = messageKey;
    }
}
