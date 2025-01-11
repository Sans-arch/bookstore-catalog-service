package com.sansarch.bookstore_catalog_service.application.usecase.check_stock;

import com.sansarch.bookstore_catalog_service.application.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.application.repository.BookRepository;
import com.sansarch.bookstore_catalog_service.application.usecase.UseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockOutputBookAvailabilityDto;
import com.sansarch.bookstore_catalog_service.infra.book.repository.model.BookModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CheckStockUseCase implements UseCase<List<CheckStockInputDto>, CheckStockOutputDto> {

    private BookRepository bookRepository;

    @Override
    public CheckStockOutputDto execute(List<CheckStockInputDto> input) {
        boolean allAvailable = false;
        List<CheckStockOutputBookAvailabilityDto> booksAvailability = new ArrayList<>();

        for (CheckStockInputDto item : input) {
            BookModel bookModel = bookRepository.findById(item.getBookId()).orElseThrow(() -> new BookNotFoundException(item.getBookId()));
            var book = BookMapper.INSTANCE.bookModelToBookEntity(bookModel);

            var bookAvailabilityDto = new CheckStockOutputBookAvailabilityDto(book.getId(), true, book.getStockAvailability());

            if (book.getStockAvailability() == 0) {
                bookAvailabilityDto.setAvailable(false);
            }

            if (book.getStockAvailability() < item.getQuantity()) {
                bookAvailabilityDto.setAvailable(false);
            }

            booksAvailability.add(bookAvailabilityDto);
        }

        return new CheckStockOutputDto(allAvailable, booksAvailability);
    }
}
