package com.sansarch.bookstore_catalog_service.service;

import com.sansarch.bookstore_catalog_service.dto.*;
import com.sansarch.bookstore_catalog_service.entity.Book;
import com.sansarch.bookstore_catalog_service.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CatalogService {

    private BookRepository bookRepository;

    public CreateBookOutputDto addBookToCatalog(CreateBookInputDto input) {
        Book book = BookMapper.INSTANCE.createBookInputDtoToBookEntity(input);
        bookRepository.save(book);
        return BookMapper.INSTANCE.bookEntityToCreateBookOutputDto(book);
    }

    public List<ListBooksOutputDto> listAllBooks() {
        return BookMapper.INSTANCE.bookEntityListToListBooksOutputDtoList(bookRepository.findAll());
    }

    public FindBookOutputDto findBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return BookMapper.INSTANCE.bookEntityToFindBookOutputDto(book);
    }

    public EditBookOutputDto editBook(Long id, EditBookInputDto input) {
        var book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if (input.getTitle() != null) {
            book.setTitle(input.getTitle());
        }

        if (input.getAuthor() != null) {
            book.setAuthor(input.getAuthor());
        }

        if (input.getStockAvailability() != null) {
            book.setStockAvailability(input.getStockAvailability());
        }

        if (input.getPrice() != null) {
            book.setPrice(input.getPrice());
        }

        this.bookRepository.save(book);

        return BookMapper.INSTANCE.bookEntityToEditBookOutputDto(book);
    }
}
