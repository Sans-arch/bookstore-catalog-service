package com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class FindBookUseCaseOutputDto {
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private Long stockAvailability;
}
