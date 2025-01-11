package com.sansarch.bookstore_catalog_service.domain.book.repository;

import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(Long id);
    List<Book> findAll();
    void save(Book book);
}
