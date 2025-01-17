package com.sansarch.bookstore_catalog_service.application.usecase.check_stock;

import com.sansarch.bookstore_catalog_service.application.usecase.UseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockOutputBookAvailabilityDto;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.exception.BookNotFoundException;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
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
        List<CheckStockOutputBookAvailabilityDto> booksAvailability = new ArrayList<>();

        for (CheckStockInputDto item : input) {
            var book = bookRepository.findById(item.getBookId()).orElseThrow(() -> new BookNotFoundException(item.getBookId()));
            var bookAvailabilityDto = CheckStockOutputBookAvailabilityDto.builder()
                    .bookId(book.getId())
                    .isAvailable(true)
                    .requestedQuantity(item.getQuantity())
                    .stock(book.getStockAvailability())
                    .build();

            if (book.getStockAvailability() == 0) {
                bookAvailabilityDto.setIsAvailable(false);
            }

            if (book.getStockAvailability() < item.getQuantity()) {
                bookAvailabilityDto.setIsAvailable(false);
            }

            booksAvailability.add(bookAvailabilityDto);
        }

        boolean allAvailable = booksAvailability.stream().allMatch(CheckStockOutputBookAvailabilityDto::getIsAvailable);
        return new CheckStockOutputDto(allAvailable, booksAvailability);
    }
}
