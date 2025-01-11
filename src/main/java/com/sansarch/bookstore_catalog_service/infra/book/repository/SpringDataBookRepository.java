package com.sansarch.bookstore_catalog_service.infra.book.repository;

import com.sansarch.bookstore_catalog_service.infra.book.repository.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBookRepository extends JpaRepository<BookModel, Long> {
}
