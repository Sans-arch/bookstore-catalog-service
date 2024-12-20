package com.sansarch.bookstore_catalog_service.application.repository;

import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
