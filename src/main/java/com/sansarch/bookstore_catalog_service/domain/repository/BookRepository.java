package com.sansarch.bookstore_catalog_service.domain.repository;

import com.sansarch.bookstore_catalog_service.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
