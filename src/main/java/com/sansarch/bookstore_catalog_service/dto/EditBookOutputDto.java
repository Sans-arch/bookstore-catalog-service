package com.sansarch.bookstore_catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EditBookOutputDto {
    private Long id;
    private String title;
    private String author;
    private Long stockAvailability;
}
