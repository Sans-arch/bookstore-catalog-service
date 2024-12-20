package com.sansarch.bookstore_catalog_service.infra.http.config;

import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.InsufficientStockException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.OutOfStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDataResponse> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
        var errorData = new ErrorDataResponse(ex.getStatus(), ex.getMessage());
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatus())).body(errorData);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorDataResponse> handleInsufficientStockException(InsufficientStockException ex, WebRequest request) {
        var errorData = new ErrorDataResponse(ex.getStatus(), ex.getMessage());
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatus())).body(errorData);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorDataResponse> handleOutOfStockException(OutOfStockException ex, WebRequest request) {
        var errorData = new ErrorDataResponse(ex.getStatus(), ex.getMessage());
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatus())).body(errorData);
    }
}
