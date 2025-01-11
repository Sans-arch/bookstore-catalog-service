package com.sansarch.bookstore_catalog_service.application.usecase.registrate_book;

import com.sansarch.bookstore_catalog_service.application.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.application.repository.BookRepository;
import com.sansarch.bookstore_catalog_service.application.usecase.UseCase;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookOutputDto;
import com.sansarch.bookstore_catalog_service.infra.book.repository.model.BookModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RegisterBookUseCase implements UseCase<RegisterBookInputDto, RegisterBookOutputDto> {

    private BookRepository bookRepository;

    @Override
    public RegisterBookOutputDto execute(RegisterBookInputDto input) {
        Book book = BookMapper.INSTANCE.createBookInputDtoToBookEntity(input);
        BookModel bookModel = BookMapper.INSTANCE.bookEntityToBookModel(book);
        bookRepository.save(bookModel);
        return BookMapper.INSTANCE.bookModelToCreateBookOutputDto(bookModel);
    }
}
