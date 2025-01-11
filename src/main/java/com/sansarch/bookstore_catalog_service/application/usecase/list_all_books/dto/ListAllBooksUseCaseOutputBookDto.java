package com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ListAllBooksUseCaseOutputBookDto {
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private Long stockAvailability;
}
