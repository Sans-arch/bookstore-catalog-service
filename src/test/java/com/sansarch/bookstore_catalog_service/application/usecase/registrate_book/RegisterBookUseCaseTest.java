package com.sansarch.bookstore_catalog_service.application.usecase.registrate_book;

import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookInputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterBookUseCaseTest {

    @InjectMocks private RegisterBookUseCase registerBookUseCase;
    @Mock private BookRepository bookRepository;


    @Test
    void shouldRegisterNewBook() {
        var input = RegisterBookInputDto
                .builder()
                .title("Book Title")
                .author("John Doe")
                .price(BigDecimal.TEN)
                .stockAvailability(10)
                .build();
        var persistedBook = Book
                .builder()
                .id(1L)
                .title("Book Title")
                .author("John Doe")
                .price(BigDecimal.TEN)
                .stockAvailability(10)
                .build();
        when(bookRepository.save(any(Book.class))).thenReturn(persistedBook);

        var output = registerBookUseCase.execute(input);

        assertNotNull(output);
        assertNotNull(output.getId());
        assertEquals(input.getAuthor(), output.getAuthor());
        assertEquals(input.getPrice(), output.getPrice());
        assertEquals(input.getStockAvailability(), output.getStockAvailability());
        assertEquals(input.getTitle(), output.getTitle());
    }

    @Test
    void shouldRegisterNewBookWithZeroStockAvailability() {
        var input = RegisterBookInputDto
                .builder()
                .title("Book Title")
                .author("John Doe")
                .price(BigDecimal.TEN)
                .stockAvailability(0)
                .build();
        var persistedBook = Book
                .builder()
                .id(1L)
                .title("Book Title")
                .author("John Doe")
                .price(BigDecimal.TEN)
                .stockAvailability(0)
                .build();
        when(bookRepository.save(any(Book.class))).thenReturn(persistedBook);

        var output = registerBookUseCase.execute(input);

        assertNotNull(output);
        assertNotNull(output.getId());
        assertEquals(input.getAuthor(), output.getAuthor());
        assertEquals(input.getPrice(), output.getPrice());
        assertEquals(input.getStockAvailability(), output.getStockAvailability());
        assertEquals(input.getTitle(), output.getTitle());
    }
}