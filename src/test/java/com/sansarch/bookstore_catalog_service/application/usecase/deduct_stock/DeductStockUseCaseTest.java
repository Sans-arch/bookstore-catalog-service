package com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock;

import com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto.DeductStockUseCaseInputBookDto;
import com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto.DeductStockUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.InsufficientStockException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.OutOfStockException;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeductStockUseCaseTest {

    @InjectMocks private DeductStockUseCase usecase;
    @Mock private BookRepository bookRepository;

    @Test
    void shouldDeductStockOfExistingBooks() {
        Book book1 = Book.builder().id(1L).title("Book 1").stockAvailability(10L).price(BigDecimal.TWO).build();
        Book book2 = Book.builder().id(2L).title("Book 1").stockAvailability(20L).price(BigDecimal.TWO).build();
        Book book3 = Book.builder().id(3L).title("Book 1").stockAvailability(30L).price(BigDecimal.TWO).build();
        List<DeductStockUseCaseInputBookDto> deductBooksDtos = List.of(
                DeductStockUseCaseInputBookDto.builder().bookId(1L).quantity(7L).build(),
                DeductStockUseCaseInputBookDto.builder().bookId(2L).quantity(19L).build(),
                DeductStockUseCaseInputBookDto.builder().bookId(3L).quantity(25L).build()
        );
        DeductStockUseCaseInputDto input = new DeductStockUseCaseInputDto(deductBooksDtos);

        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book1));
        when(bookRepository.findById(2L)).thenReturn(java.util.Optional.of(book2));
        when(bookRepository.findById(3L)).thenReturn(java.util.Optional.of(book3));
        usecase.execute(input);

        assertEquals(3L, book1.getStockAvailability());
        assertEquals(1L, book2.getStockAvailability());
        assertEquals(5L, book3.getStockAvailability());
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenSomeBookDoesNotExist() {
        Book book1 = Book
                .builder()
                .id(1L)
                .title("Some title")
                .stockAvailability(40L)
                .build();
        List<DeductStockUseCaseInputBookDto> deductBooksDtos = List.of(
                DeductStockUseCaseInputBookDto.builder().bookId(1L).quantity(5L).build(),
                DeductStockUseCaseInputBookDto.builder().bookId(2L).quantity(10L).build(),
                DeductStockUseCaseInputBookDto.builder().bookId(3L).quantity(15L).build(),
                DeductStockUseCaseInputBookDto.builder().bookId(4L).quantity(20L).build()
        );
        DeductStockUseCaseInputDto input = new DeductStockUseCaseInputDto(deductBooksDtos);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> usecase.execute(input));
    }

    @Test
    void shouldThrowOutOfStockExceptionWhenBookIsOutOfStock() {
        Book book = Book
                .builder()
                .id(1L)
                .title("Book 1")
                .stockAvailability(0L)
                .price(BigDecimal.TWO)
                .build();
        List<DeductStockUseCaseInputBookDto> deductBooksDtos = List.of(
                DeductStockUseCaseInputBookDto.builder().bookId(1L).quantity(5L).build()
        );
        DeductStockUseCaseInputDto input = new DeductStockUseCaseInputDto(deductBooksDtos);

        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
        assertThrows(OutOfStockException.class, () -> usecase.execute(input));
    }

    @Test
    void shouldThrowInsufficientStockExceptionWhenRequestedQuantityExceedsStock() {
        Book book = Book
                .builder()
                .id(1L)
                .title("Book 1")
                .stockAvailability(3L)
                .price(BigDecimal.TWO)
                .build();
        List<DeductStockUseCaseInputBookDto> deductBooksDtos = List.of(
                DeductStockUseCaseInputBookDto.builder().bookId(1L).quantity(5L).build()
        );
        DeductStockUseCaseInputDto input = new DeductStockUseCaseInputDto(deductBooksDtos);

        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
        assertThrows(InsufficientStockException.class, () -> usecase.execute(input));
    }

    @Test
    void shouldDeductStockToZero() {
        Book book = Book
                .builder()
                .id(1L)
                .title("Book 1")
                .stockAvailability(5L)
                .price(BigDecimal.TWO)
                .build();
        List<DeductStockUseCaseInputBookDto> deductBooksDtos = List.of(
                DeductStockUseCaseInputBookDto.builder().bookId(1L).quantity(5L).build()
        );
        DeductStockUseCaseInputDto input = new DeductStockUseCaseInputDto(deductBooksDtos);

        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
        usecase.execute(input);

        assertEquals(0L, book.getStockAvailability());
    }
}