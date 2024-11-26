package com.sansarch.bookstore_catalog_service.config;

import com.sansarch.bookstore_catalog_service.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDataResponse> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
        var errorData = new ErrorDataResponse(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorData);
    }
}
