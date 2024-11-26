package com.sansarch.bookstore_catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ListBooksOutputDto {
    private Long id;
    private String title;
    private String author;
    private Long stockAvailability;
}
