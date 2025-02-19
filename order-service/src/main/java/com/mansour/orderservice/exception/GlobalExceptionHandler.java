package com.mansour.orderservice.exception;

import com.mansour.orderservice.dto.ApiResponse;
import com.mansour.orderservice.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception exception, HttpServletRequest request) {
        log.error("GlobalExceptionHandler#handleException Exception: {}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatusMessageKey.INTERNAL_SERVER_ERROR.httpStatusCode)
                .body(ResponseUtil.error(
                        exception.getMessage(),
                        exception.getMessage(),
                        HttpStatusMessageKey.INTERNAL_SERVER_ERROR.httpStatusCode.value(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleOrderNotFoundException(OrderNotFoundException exception, HttpServletRequest request) {
        log.error("GlobalExceptionHandler#handleOrderNotFoundException OrderNotFoundException: {}", exception.getMessage());
        return ResponseEntity
                .status(exception.httpStatusCode)
                .body(ResponseUtil.error(
                        exception.getMessage(),
                        exception.getMessage(),
                        exception.httpStatusCode.value(),
                        request.getRequestURI()
                ));

    }

    @ExceptionHandler(InsufficientInventoryException.class)
    public ResponseEntity<ApiResponse<Object>> handleInsufficientInventoryException(InsufficientInventoryException exception, HttpServletRequest request) {
        log.error("GlobalExceptionHandler#handleInsufficientInventoryException InsufficientInventoryException: {}", exception.getMessage());
        return ResponseEntity
                .status(exception.httpStatusCode)
                .body(ResponseUtil.error(
                        exception.getMessage(),
                        exception.getMessage(),
                        exception.httpStatusCode.value(),
                        request.getRequestURI()
                ));
    }
}
