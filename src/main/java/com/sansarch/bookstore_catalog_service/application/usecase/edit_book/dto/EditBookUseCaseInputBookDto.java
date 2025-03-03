package com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class EditBookUseCaseInputBookDto {
    private String title;
    private String author;
    private BigDecimal price;
    private Long stockAvailability;
}
