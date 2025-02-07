package com.sansarch.bookstore_catalog_service.application.usecase.check_stock;

import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockInputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckStockUseCaseTest {

    @InjectMocks private CheckStockUseCase checkStockUseCase;
    @Mock private BookRepository bookRepository;

    @Test
    void shouldCheckStockWithAllItemsAvailable() {
        List<CheckStockInputDto> input = List.of(
                new CheckStockInputDto(1L, 20),
                new CheckStockInputDto(2L, 30)
        );
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book2));
        when(book1.getId()).thenReturn(1L);
        when(book2.getId()).thenReturn(2L);
        when(book1.getStockAvailability()).thenReturn(40L);
        when(book2.getStockAvailability()).thenReturn(40L);

        var output = checkStockUseCase.execute(input);

        assertTrue(output.getAllAvailable());
        assertEquals(1L, output.getBooksAvailability().getFirst().getBookId());
        assertEquals(2L, output.getBooksAvailability().getLast().getBookId());
        assertTrue(output.getBooksAvailability().getFirst().getIsAvailable());
        assertTrue(output.getBooksAvailability().getLast().getIsAvailable());
    }

    @Test
    void shouldCheckStockWithSomeItemsUnavailable() {
        List<CheckStockInputDto> input = List.of(
                new CheckStockInputDto(1L, 20),
                new CheckStockInputDto(2L, 30)
        );
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book2));
        when(book1.getId()).thenReturn(1L);
        when(book2.getId()).thenReturn(2L);
        when(book1.getStockAvailability()).thenReturn(10L);
        when(book2.getStockAvailability()).thenReturn(32L);

        var output = checkStockUseCase.execute(input);

        assertFalse(output.getAllAvailable());
        assertEquals(1L, output.getBooksAvailability().getFirst().getBookId());
        assertEquals(2L, output.getBooksAvailability().getLast().getBookId());
        assertFalse(output.getBooksAvailability().getFirst().getIsAvailable());
        assertTrue(output.getBooksAvailability().getLast().getIsAvailable());
    }

    @Test
    void shouldCheckStockWithAllItemsUnavailable() {
        List<CheckStockInputDto> input = List.of(
                new CheckStockInputDto(1L, 20),
                new CheckStockInputDto(2L, 30)
        );
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book2));
        when(book1.getId()).thenReturn(1L);
        when(book2.getId()).thenReturn(2L);
        when(book1.getStockAvailability()).thenReturn(0L);
        when(book2.getStockAvailability()).thenReturn(0L);

        var output = checkStockUseCase.execute(input);

        assertFalse(output.getAllAvailable());
        assertEquals(1L, output.getBooksAvailability().getFirst().getBookId());
        assertEquals(2L, output.getBooksAvailability().getLast().getBookId());
        assertFalse(output.getBooksAvailability().getFirst().getIsAvailable());
        assertFalse(output.getBooksAvailability().getLast().getIsAvailable());
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenSomeRequestedItemDoesNotExists() {
        List<CheckStockInputDto> input = List.of(
                new CheckStockInputDto(1L, 20),
                new CheckStockInputDto(2L, 30)
        );

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> checkStockUseCase.execute(input));
    }
}