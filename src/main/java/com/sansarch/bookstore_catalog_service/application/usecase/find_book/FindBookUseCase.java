package com.sansarch.bookstore_catalog_service.application.usecase.find_book;

import com.sansarch.bookstore_catalog_service.application.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.application.repository.BookRepository;
import com.sansarch.bookstore_catalog_service.application.usecase.UseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto.FindBookUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto.FindBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.infra.book.repository.model.BookModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FindBookUseCase implements UseCase<FindBookUseCaseInputDto, FindBookUseCaseOutputDto> {

    private BookRepository bookRepository;

    @Override
    public FindBookUseCaseOutputDto execute(FindBookUseCaseInputDto input) {
        BookModel bookModel = bookRepository.findById(input.getId())
                .orElseThrow(() -> new BookNotFoundException(input.getId()));
        Book book = BookMapper.INSTANCE.bookModelToBookEntity(bookModel);
        return BookMapper.INSTANCE.bookEntityToFindBookOutputDto(book);
    }
}
