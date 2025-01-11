package com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class RegisterBookInputDto {
    private String title;
    private String author;
    private BigDecimal price;
    private Integer stockAvailability;
}
