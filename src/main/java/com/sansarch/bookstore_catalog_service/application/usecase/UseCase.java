package com.sansarch.bookstore_catalog_service.application.usecase;

public interface UseCase<I, O> {
    O execute(I input);
}
