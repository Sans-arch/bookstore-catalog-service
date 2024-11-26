package com.sansarch.bookstore_catalog_service.repository;

import com.sansarch.bookstore_catalog_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
