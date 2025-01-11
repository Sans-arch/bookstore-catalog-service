package com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListAllBooksUseCaseOutputDto {
    private List<ListAllBooksUseCaseOutputBookDto> books;
}
