package com.sansarch.bookstore_catalog_service.service;

import com.sansarch.bookstore_catalog_service.dto.*;
import com.sansarch.bookstore_catalog_service.entity.Book;
import com.sansarch.bookstore_catalog_service.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.exception.InsufficientStockException;
import com.sansarch.bookstore_catalog_service.exception.OutOfStockException;
import com.sansarch.bookstore_catalog_service.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

    @Transactional
    public void deductStock(List<StockDeductionInputDto> input) {
        for (StockDeductionInputDto requestedItem : input) {
            var book = this.bookRepository.findById(requestedItem.getBookId())
                    .orElseThrow(() -> new BookNotFoundException(requestedItem.getBookId()));

            if (book.getStockAvailability() == 0) {
                log.info("Book with id {} is out of stock", book.getId());
                throw new OutOfStockException(book.getId());
            }

            if (book.getStockAvailability() < requestedItem.getQuantity()) {
                log.info("Requested quantity exceeds available stock for book with id {}", book.getId());
                throw new InsufficientStockException(
                        book.getId(),
                        book.getStockAvailability(),
                        requestedItem.getQuantity());
            }

            book.setStockAvailability(book.getStockAvailability() - requestedItem.getQuantity());
            this.bookRepository.save(book);

            if (book.getStockAvailability() == 0) {
                log.info("Book with id {} is now out of stock", book.getId());
            }
        }
    }
}
