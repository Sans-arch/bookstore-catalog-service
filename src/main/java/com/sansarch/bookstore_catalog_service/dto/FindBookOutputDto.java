package com.sansarch.bookstore_catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FindBookOutputDto {
    private Long id;
    private String title;
    private String author;
    private Long stockAvailability;
}
