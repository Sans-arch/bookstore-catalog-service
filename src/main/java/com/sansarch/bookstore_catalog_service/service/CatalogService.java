package com.sansarch.bookstore_catalog_service.service;

import com.sansarch.bookstore_catalog_service.dto.CreateBookInputDto;
import com.sansarch.bookstore_catalog_service.dto.CreateBookOutputDto;
import com.sansarch.bookstore_catalog_service.entity.Book;
import com.sansarch.bookstore_catalog_service.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CatalogService {

    private BookRepository bookRepository;

    public CreateBookOutputDto addBookToCatalog(CreateBookInputDto input) {
        Book book = BookMapper.INSTANCE.createBookInputDtoToBookEntity(input);
        bookRepository.save(book);

        return BookMapper.INSTANCE.bookEntityToCreateBookOutputDto(book);
    }
}
