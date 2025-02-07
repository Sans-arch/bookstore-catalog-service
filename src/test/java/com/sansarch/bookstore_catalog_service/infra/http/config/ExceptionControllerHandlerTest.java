package com.sansarch.bookstore_catalog_service.infra.http.config;

import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.InsufficientStockException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.OutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExceptionControllerHandlerTest {

    private ExceptionControllerHandler exceptionControllerHandler;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        exceptionControllerHandler = new ExceptionControllerHandler();
        webRequest = mock(WebRequest.class);
    }

    @Test
    void shouldReturnNotFoundErrorResponseWhenBookNotFoundException() {
        BookNotFoundException exception = new BookNotFoundException(1L);

        ResponseEntity<ErrorDataResponse> response = exceptionControllerHandler.handleBookNotFoundException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book with id 1 not found", Objects.requireNonNull(response.getBody()).getDetail());
    }

    @Test
    void shouldReturnBadRequestErrorResponseWhenInsufficientStockException() {
        InsufficientStockException exception = new InsufficientStockException(1L, 10L, 20L);

        ResponseEntity<ErrorDataResponse> response = exceptionControllerHandler.handleInsufficientStockException(exception, webRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Book ID 1: Requested 20 exceeds available stock of 10.", Objects.requireNonNull(response.getBody()).getDetail());
    }

    @Test
    void shouldReturnBadRequestErrorResponseWhenOutOfStockException() {
        OutOfStockException exception = new OutOfStockException(1L);

        ResponseEntity<ErrorDataResponse> response = exceptionControllerHandler.handleOutOfStockException(exception, webRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Book ID 1 is out of stock", Objects.requireNonNull(response.getBody()).getDetail());
    }

}