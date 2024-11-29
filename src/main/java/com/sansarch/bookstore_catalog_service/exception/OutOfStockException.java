package com.sansarch.bookstore_catalog_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OutOfStockException extends RuntimeException {
    private final int status = HttpStatus.CONFLICT.value();

    public OutOfStockException(Long bookId) {
        super(String.format("Book ID %d is out of stock", bookId));
    }
}
