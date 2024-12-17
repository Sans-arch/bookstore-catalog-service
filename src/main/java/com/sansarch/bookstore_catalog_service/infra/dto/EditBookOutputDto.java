package com.sansarch.bookstore_catalog_service.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class EditBookOutputDto {
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private Long stockAvailability;
}
