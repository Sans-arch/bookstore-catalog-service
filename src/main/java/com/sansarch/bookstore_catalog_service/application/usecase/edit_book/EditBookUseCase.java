package com.sansarch.bookstore_catalog_service.application.usecase.edit_book;

import com.sansarch.bookstore_catalog_service.application.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.application.repository.BookRepository;
import com.sansarch.bookstore_catalog_service.application.usecase.UseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.infra.book.repository.model.BookModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EditBookUseCase implements UseCase<EditBookUseCaseInputDto, EditBookUseCaseOutputDto> {

    private BookRepository bookRepository;

    @Override
    public EditBookUseCaseOutputDto execute(EditBookUseCaseInputDto input) {
        BookModel bookModel = this.bookRepository.findById(input.getId())
                .orElseThrow(() -> new BookNotFoundException(input.getId()));
        Book book = BookMapper.INSTANCE.bookModelToBookEntity(bookModel);

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

        this.bookRepository.save(BookMapper.INSTANCE.bookEntityToBookModel(book));
        return BookMapper.INSTANCE.bookModelToEditBookOutputDto(bookModel);
    }
}
