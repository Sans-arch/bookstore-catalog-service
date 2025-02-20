package com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock;

import com.sansarch.bookstore_catalog_service.application.usecase.UseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto.DeductStockUseCaseInputBookDto;
import com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto.DeductStockUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto.DeductStockUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.InsufficientStockException;
import com.sansarch.bookstore_catalog_service.domain.book.exception.OutOfStockException;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Component
public class DeductStockUseCase implements UseCase<DeductStockUseCaseInputDto, DeductStockUseCaseOutputDto> {

    private BookRepository bookRepository;

    @Override
    @Transactional
    public DeductStockUseCaseOutputDto execute(DeductStockUseCaseInputDto input) {
        for (DeductStockUseCaseInputBookDto requestedItem : input.getBooksToBeDeducted()) {
            Book book = bookRepository.findById(requestedItem.getBookId())
                    .orElseThrow(() -> new BookNotFoundException(requestedItem.getBookId()));

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
            bookRepository.save(book);

            if (book.getStockAvailability() == 0) {
                log.info("Book with id {} is now out of stock", book.getId());
            }
        }

        return new DeductStockUseCaseOutputDto();
    }
}
