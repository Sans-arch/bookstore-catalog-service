package com.sansarch.bookstore_catalog_service.application.usecase.edit_book;

import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseInputBookDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditBookUseCaseTest {

    @InjectMocks private EditBookUseCase usecase;
    @Mock private BookRepository bookRepository;

    @Test
    void shouldEditExistingBookWithValidNewContent() {
        Book book = Book
                .builder()
                .id(1L)
                .title("Existing title")
                .author("John Doe")
                .price(BigDecimal.ONE)
                .stockAvailability(20L)
                .build();
        EditBookUseCaseInputBookDto bookNewContent = EditBookUseCaseInputBookDto
                .builder()
                .title("New Title")
                .price(BigDecimal.TWO)
                .author("Jane Doe")
                .stockAvailability(40L)
                .build();
        EditBookUseCaseInputDto input = EditBookUseCaseInputDto
                .builder()
                .id(1L)
                .bookNewContent(bookNewContent)
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        var output = usecase.execute(input);

        assertNotNull(output);
        assertEquals(output.getId(), input.getId());
        assertEquals(output.getTitle(), input.getBookNewContent().getTitle());
        assertEquals(output.getPrice(), input.getBookNewContent().getPrice());
        assertEquals(output.getAuthor(), input.getBookNewContent().getAuthor());
        assertEquals(output.getStockAvailability(), input.getBookNewContent().getStockAvailability());
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenBookDoesNotExist() {
        EditBookUseCaseInputBookDto bookNewContent = EditBookUseCaseInputBookDto
                .builder()
                .title("New Title")
                .price(BigDecimal.TWO)
                .author("Jane Doe")
                .stockAvailability(40L)
                .build();
        EditBookUseCaseInputDto input = EditBookUseCaseInputDto
                .builder()
                .id(1L)
                .bookNewContent(bookNewContent)
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> usecase.execute(input));
    }

    @Test
    void shouldNotChangeExistingFieldsDataWhenSomeNewContentFieldsAreNull() {
        Book book = Book
                .builder()
                .id(1L)
                .title("Existing title")
                .author("John Doe")
                .price(BigDecimal.ONE)
                .stockAvailability(20L)
                .build();
        EditBookUseCaseInputBookDto bookNewContent = EditBookUseCaseInputBookDto
                .builder()
                .title(null)
                .price(null)
                .author(null)
                .stockAvailability(null)
                .build();
        EditBookUseCaseInputDto input = EditBookUseCaseInputDto
                .builder()
                .id(1L)
                .bookNewContent(bookNewContent)
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        var output = usecase.execute(input);

        assertNotNull(output);
        assertEquals(output.getId(), input.getId());
        assertNotEquals(output.getTitle(), input.getBookNewContent().getTitle());
        assertNotEquals(output.getPrice(), input.getBookNewContent().getPrice());
        assertNotEquals(output.getAuthor(), input.getBookNewContent().getAuthor());
        assertNotEquals(output.getStockAvailability(), input.getBookNewContent().getStockAvailability());

        assertEquals(output.getId(), book.getId());
        assertEquals(output.getTitle(), book.getTitle());
        assertEquals(output.getPrice(), book.getPrice());
        assertEquals(output.getAuthor(), book.getAuthor());
        assertEquals(output.getStockAvailability(), book.getStockAvailability());
    }
}