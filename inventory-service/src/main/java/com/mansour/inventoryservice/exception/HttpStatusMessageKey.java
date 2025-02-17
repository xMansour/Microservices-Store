package com.mansour.inventoryservice.exception;

import org.springframework.http.HttpStatus;

public enum HttpStatusMessageKey {
    INVENTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "inventory.not.found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal.server.error");

    public HttpStatus httpStatusCode;
    public String messageKey;

    HttpStatusMessageKey(HttpStatus httpStatusCode, String messageKey) {
        this.httpStatusCode = httpStatusCode;
        this.messageKey = messageKey;
    }
}
