package com.sansarch.bookstore_catalog_service.application.service;

import com.sansarch.bookstore_catalog_service.application.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.application.repository.BookRepository;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.CheckStockUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.EditBookUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseInputBookDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.FindBookUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto.FindBookUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto.FindBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.ListAllBooksUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto.ListAllBooksUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.RegisterBookUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.InsufficientStockException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.OutOfStockException;
import com.sansarch.bookstore_catalog_service.infra.book.dto.StockDeductionInputDto;
import com.sansarch.bookstore_catalog_service.infra.book.repository.model.BookModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CatalogService {

    private BookRepository bookRepository;
    private CheckStockUseCase checkStockUseCase;
    private RegisterBookUseCase registerBookUseCase;
    private ListAllBooksUseCase listAllBooksUseCase;
    private FindBookUseCase findBookUseCase;
    private EditBookUseCase editBookUseCase;

    public RegisterBookOutputDto addBookToCatalog(RegisterBookInputDto input) {
        return registerBookUseCase.execute(input);
    }

    public ListAllBooksUseCaseOutputDto listAllBooks() {
        return listAllBooksUseCase.execute(null);
    }

    public FindBookUseCaseOutputDto findBook(Long id) {
        return findBookUseCase.execute(new FindBookUseCaseInputDto(id));
    }

    public EditBookUseCaseOutputDto editBook(Long id, EditBookUseCaseInputBookDto payload) {
        return editBookUseCase.execute(new EditBookUseCaseInputDto(id, payload));
    }

    @Transactional
    public void deductStock(List<StockDeductionInputDto> input) {
        for (StockDeductionInputDto requestedItem : input) {
            BookModel bookModel = this.bookRepository.findById(requestedItem.getBookId())
                    .orElseThrow(() -> new BookNotFoundException(requestedItem.getBookId()));
            Book book = BookMapper.INSTANCE.bookModelToBookEntity(bookModel);

            if (book.getStockAvailability() == 0) {
                log.info("Book with id {} is out of stock", book.getId());
                throw new OutOfStockException(book.getId());
            }

            if (book.getStockAvailability() < requestedItem.getQuantity()) {
                log.info("Requested quantity exceeds available stock for book with id {}", book.getId());
                throw new InsufficientStockException(
                        book.getId(),
                        book.getStockAvailability(),
                        requestedItem.getQuantity());
            }

            book.setStockAvailability(book.getStockAvailability() - requestedItem.getQuantity());
            BookModel updatedBookModel = BookMapper.INSTANCE.bookEntityToBookModel(book);
            this.bookRepository.save(updatedBookModel);

            if (book.getStockAvailability() == 0) {
                log.info("Book with id {} is now out of stock", book.getId());
            }
        }
    }

    public CheckStockOutputDto checkStockAvailability(List<CheckStockInputDto> items) {
        return checkStockUseCase.execute(items);
    }
}
