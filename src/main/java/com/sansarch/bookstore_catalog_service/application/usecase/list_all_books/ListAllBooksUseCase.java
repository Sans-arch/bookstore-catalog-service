package com.sansarch.bookstore_catalog_service.application.usecase.list_all_books;

import com.sansarch.bookstore_catalog_service.application.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.application.usecase.UseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto.ListAllBooksUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto.ListAllBooksUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ListAllBooksUseCase implements UseCase<ListAllBooksUseCaseInputDto, ListAllBooksUseCaseOutputDto> {

    private BookRepository bookRepository;

    @Override
    public ListAllBooksUseCaseOutputDto execute(ListAllBooksUseCaseInputDto input) {
        var allBooks = BookMapper.INSTANCE.bookListToListAllBooksOutputDtoList(bookRepository.findAll());
        return new ListAllBooksUseCaseOutputDto(allBooks);
    }
}
