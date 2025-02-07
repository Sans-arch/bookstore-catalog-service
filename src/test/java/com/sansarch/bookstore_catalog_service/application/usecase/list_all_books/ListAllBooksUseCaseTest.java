package com.sansarch.bookstore_catalog_service.application.usecase.list_all_books;

import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto.ListAllBooksUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto.ListAllBooksUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListAllBooksUseCaseTest {

    @InjectMocks private ListAllBooksUseCase listAllBooksUseCase;
    @Mock private BookRepository bookRepository;


    @Test
    void shouldExecuteListAllBooksUseCaseWithExistingBooks() {
        ListAllBooksUseCaseInputDto input = mock(ListAllBooksUseCaseInputDto.class);
        var book1 = mock(Book.class);
        var book2 = mock(Book.class);

        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));
        ListAllBooksUseCaseOutputDto output = listAllBooksUseCase.execute(input);

        assertFalse(output.getBooks().isEmpty());
        assertEquals(2, output.getBooks().size());
    }

    @Test
    void shouldExecuteListAllBooksUseCaseWithoutExistingBooks() {
        ListAllBooksUseCaseInputDto input = mock(ListAllBooksUseCaseInputDto.class);

        when(bookRepository.findAll()).thenReturn(List.of());
        ListAllBooksUseCaseOutputDto output = listAllBooksUseCase.execute(input);

        assertTrue(output.getBooks().isEmpty());
    }
}