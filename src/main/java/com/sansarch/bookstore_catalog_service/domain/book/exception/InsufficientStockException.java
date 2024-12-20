package com.sansarch.bookstore_catalog_service.domain.book.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InsufficientStockException extends RuntimeException {
    private final int status = HttpStatus.CONFLICT.value();

    public InsufficientStockException(Long bookId, Integer available, Integer requested) {
        super(String.format("Book ID %d: Requested %d exceeds available stock of %d.", bookId, requested, available));
    }
}
