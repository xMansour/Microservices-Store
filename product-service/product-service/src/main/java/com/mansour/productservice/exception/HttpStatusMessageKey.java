package com.mansour.productservice.exception;

import org.springframework.http.HttpStatus;

public enum HttpStatusMessageKey {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "product.not.found");

    public HttpStatus httpStatusCode;
    public String messageKey;

    HttpStatusMessageKey(HttpStatus httpStatusCode, String messageKey) {
        this.httpStatusCode = httpStatusCode;
        this.messageKey = messageKey;
    }
}
