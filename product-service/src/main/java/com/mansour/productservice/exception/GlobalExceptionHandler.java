package com.mansour.productservice.exception;

import com.mansour.productservice.dto.ApiResponse;
import com.mansour.productservice.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleProductNotFoundException(ProductNotFoundException exception, HttpServletRequest request) {
        log.error("ProductNotFoundException: {}", exception.getMessage());
        return ResponseEntity
                .status(exception.httpStatusCode)
                .body(ResponseUtil.error(
                        exception.getMessage(),
                        exception.getMessage(),
                        exception.httpStatusCode.value(),
                        request.getRequestURI())
                );
    }
}
