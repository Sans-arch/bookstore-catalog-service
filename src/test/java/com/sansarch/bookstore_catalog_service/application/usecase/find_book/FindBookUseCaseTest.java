package com.sansarch.bookstore_catalog_service.application.usecase.find_book;

import com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto.FindBookUseCaseInputDto;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBookUseCaseTest {

    @InjectMocks private FindBookUseCase usecase;
    @Mock private BookRepository bookRepository;

    @Test
    void shouldFindBook() {
        FindBookUseCaseInputDto input = new FindBookUseCaseInputDto(1L);
        Book book = Book
                .builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .price(BigDecimal.TEN)
                .stockAvailability(10L)
                .build();

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        var output = usecase.execute(input);

        assertNotNull(output);
        assertEquals(output.getId(), input.getId());
        assertEquals(output.getAuthor(), book.getAuthor());
        assertEquals(output.getPrice(), book.getPrice());
        assertEquals(output.getTitle(), book.getTitle());
        assertEquals(output.getStockAvailability(), book.getStockAvailability());
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenFindingNonExistingBook() {
        FindBookUseCaseInputDto input = new FindBookUseCaseInputDto(2L);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> usecase.execute(input));
    }
}