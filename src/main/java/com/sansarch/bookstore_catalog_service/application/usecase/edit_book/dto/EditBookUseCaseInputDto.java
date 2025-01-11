package com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EditBookUseCaseInputDto {
    private Long id;
    private EditBookUseCaseInputBookDto bookNewContent;
}
