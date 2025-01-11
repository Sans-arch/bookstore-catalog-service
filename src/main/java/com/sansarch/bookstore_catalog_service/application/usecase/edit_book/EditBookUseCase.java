package com.sansarch.bookstore_catalog_service.application.usecase.edit_book;

import com.sansarch.bookstore_catalog_service.application.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.application.usecase.UseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EditBookUseCase implements UseCase<EditBookUseCaseInputDto, EditBookUseCaseOutputDto> {

    private BookRepository bookRepository;

    @Override
    public EditBookUseCaseOutputDto execute(EditBookUseCaseInputDto input) {
        Book book = bookRepository.findById(input.getId())
                .orElseThrow(() -> new BookNotFoundException(input.getId()));

        var newContent = input.getBookNewContent();

        if (newContent.getTitle() != null) {
            book.changeTitle(newContent.getTitle());
        }

        if (newContent.getAuthor() != null) {
            book.changeAuthor(newContent.getAuthor());
        }

        if (newContent.getStockAvailability() != null) {
            book.changeStockAvailability(newContent.getStockAvailability());
        }

        if (newContent.getPrice() != null) {
            book.changePrice(newContent.getPrice());
        }

        bookRepository.save(book);
        return BookMapper.INSTANCE.bookEntityToEditBookOutputDto(book);
    }
}
