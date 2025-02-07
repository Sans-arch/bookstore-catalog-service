package com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class EditBookUseCaseInputDto {
    private Long id;
    private EditBookUseCaseInputBookDto bookNewContent;
}
